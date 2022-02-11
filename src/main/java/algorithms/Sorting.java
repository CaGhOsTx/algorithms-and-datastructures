package algorithms;

import java.util.Comparator;
import java.util.List;

public class Sorting {

    public static <T extends Comparable<T>> void selectionSort(List<T> list) {
        selectionSort(list, Comparator.naturalOrder());
    }

    public static <T> void selectionSort(List<T> list, Comparator<T> comp) {
        for (int i = 1, j = i; i < list.size(); j = ++i) {
            var curr = list.get(i);
            while(j > 0 && comp.compare(list.get(j - 1), curr) > 0)
                list.set(j, list.get(--j));
            list.set(j, curr);
        }
    }

    public static <T extends Comparable<T>> void insertionSort(List<T> list) {
        insertionSort(list, Comparator.naturalOrder());
    }

    public static <T> void insertionSort(List<T> list, Comparator<T> comp) {
        for (int i = 0; i < list.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++)
                if(comp.compare(list.get(j), list.get(i)) < 0)
                    minIndex = j;
            if(i != minIndex) swap(list, i, minIndex);
        }
    }

    public static <T extends Comparable<T>> void bubbleSort(List<T> list) {
        bubbleSort(list, Comparator.naturalOrder());
    }

    public static <T> void bubbleSort(List<T> list, Comparator<T> comp) {
        boolean swapped = false;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                swapped = false;
                if(comp.compare(list.get(j), list.get(j + 1)) > 0) {
                    swap(list, j, j + 1);
                    swapped = true;
                }
            }
            if(!swapped) break;
        }
    }

    private static <T> void swap(List<T> list, int a, int b) {
        var tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }
}
