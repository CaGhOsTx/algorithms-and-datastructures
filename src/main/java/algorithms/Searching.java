package algorithms;

import datastructures.lists.ArrayCollection;

import java.util.function.ToIntFunction;

import static algorithms.Searching.LocationAlgorithm.*;

public class Searching {

    static class TwoPointerWrapper {
        private int left, right, middle;

        private TwoPointerWrapper(ArrayCollection<? extends Comparable<?>> collection) {
            this.left = 0;
            this.right = collection.size() - 1;
            updateMid();
        }

        static TwoPointerWrapper constructFrom(ArrayCollection<? extends Comparable<?>> collection) {
            return new TwoPointerWrapper(collection);
        }

        private void updateMid() {
            middle = left + right >>> 1;
        }

        int left() {
            return left;
        }

        void updateLeft(ToIntFunction<Integer> f) {
            left = f.applyAsInt(middle);
            updateMid();
        }

        int right() {
            return right;
        }

        void updateRight(ToIntFunction<Integer> f) {
            right = f.applyAsInt(middle);
            updateMid();
        }

        int middle() {
            return middle;
        }

        @Override
        public String toString() {
            return "State{" +
                    "left=" + left +
                    ", right=" + right +
                    ", middle=" + middle +
                    '}';
        }
    }

    enum LocationAlgorithm {
        FIRST_IN_ORDER {
            @Override
            <T extends Comparable<T>> boolean found(ArrayCollection<? extends T> arr, int m, T t) {
                return ENCOUNTER_ORDER.found(arr, m, t) && precedingElementSmaller(arr, m);
            }

            private <T extends Comparable<T>> boolean precedingElementSmaller(ArrayCollection<? extends T> arr, int m) {
                T current = arr.get(m), previous = arr.get(m - 1);
                return m == 0 || current.compareTo(previous) > 0;
            }

            @Override
            <T extends Comparable<T>> void narrow(TwoPointerWrapper pointers, T current, T target) {
                if(current.compareTo(target) < 0)
                    pointers.updateLeft(m -> m + 1);
                else pointers.updateRight(m -> m - 1);
            }
        },
        ENCOUNTER_ORDER {
            @Override
            <T extends Comparable<T>> boolean found(ArrayCollection<? extends T> arr, int m, T t) {
                return arr.get(m).equals(t);
            }

            @Override
            <T extends Comparable<T>> void narrow(TwoPointerWrapper pointers, T current, T target) {
                FIRST_IN_ORDER.narrow(pointers, current, target);
            }
        },
        LAST_IN_ORDER {
            @Override
            <T extends Comparable<T>> boolean found(ArrayCollection<? extends T> arr, int m, T t) {
                return ENCOUNTER_ORDER.found(arr, m, t) && succeedingElementGreater(arr, m);
            }

            private <T extends Comparable<T>> boolean succeedingElementGreater(ArrayCollection<? extends T> arr, int m) {
                T current = arr.get(m), next = arr.get(m + 1);
                return m == arr.size() - 1 || current.compareTo(next) < 0;
            }

            @Override
            <T extends Comparable<T>> void narrow(TwoPointerWrapper pointers, T current, T target) {
                if(current.compareTo(target) > 0)
                    pointers.updateRight(m -> m - 1);
                else pointers.updateLeft(m -> m + 1);
            }
        };

        abstract <T extends Comparable<T>> boolean found(ArrayCollection<? extends T> arr, int m, T t);

        abstract <T extends Comparable<T>> void narrow(TwoPointerWrapper pointers, T current, T target);

    }

    public static <T extends Comparable<T>> int binarySearch(ArrayCollection<? extends T> collection, T target) {
        return binarySearch(collection, target, ENCOUNTER_ORDER);
    }

    public static <T extends Comparable<T>> int findFirst(ArrayCollection<? extends T> collection, T target) {
        return binarySearch(collection, target, FIRST_IN_ORDER);
    }

    public static <T extends Comparable<T>> int findMiddle(ArrayCollection<? extends T> collection, T target) {
        var left = findFirst(collection, target);
        var right = findLast(collection, target);
        return right + left >>> 1;
    }

    public static <T extends Comparable<T>> int findLast(ArrayCollection<? extends T> collection, T target) {
        return binarySearch(collection, target, LAST_IN_ORDER);
    }

    private static <T extends Comparable<T>> int binarySearch(ArrayCollection<? extends T> collection, T target, LocationAlgorithm search) {
        if(!sorted(collection)) throw new IllegalStateException("collection is not sorted!");
        var pointers = TwoPointerWrapper.constructFrom(collection);
        while(pointers.left() <= pointers.right()) {
            if(search.found(collection, pointers.middle(), target))
                return pointers.middle();
            else
                search.narrow(pointers, collection.get(pointers.middle()), target);
        }
        return -1;
    }

    private static <T extends Comparable<T>> boolean sorted(ArrayCollection<? extends T> collection) {
        for (int i = 0; i < collection.size() - 1; i++)
            if(collection.get(i).compareTo(collection.get(i + 1)) > 0)
                return false;
        return true;
    }
}
