package algorithms;

import datastructures.*;

import java.util.Comparator;
import java.util.function.Supplier;

import static datastructures.DataStructure.convert;
import static datastructures.List.sublist;
import static java.util.Comparator.naturalOrder;

public class Sorting {

    private static <T extends Comparable<T>> void swap(List<T> struct, int src, int pos) {
        T temp = struct.get(src);
        struct.set(src, struct.get(pos));
        struct.set(pos, temp);
    }

    public static <T extends Comparable<T>> void bubbleSort(List<T> list, Comparator<T> comp) {
        for (int i = 0; i < list.size(); i++)
            for (int j = 0; j < list.size() - i - 1; j++)
                if(comp.compare(list.get(j), list.get(j + 1)) > 0)
                    swap(list, j, j + 1);
    }

    public static <T extends Comparable<T>> void bubbleSort(List<T> list) {
        bubbleSort(list, naturalOrder());
    }

    public static <T extends Comparable<T>> void insertionSort(List<T> list, Comparator<T> comp) {
        for (int i = 0, min = i; i < list.size() - 1; min = ++i) {
            for (int j = i + 1; j < list.size(); j++)
                if(comp.compare(list.get(min), list.get(j)) > 0)
                    min = j;
            swap(list, min, i);
        }
    }

    public static <T extends Comparable<T>> void insertionSort(List<T> list) {
        insertionSort(list, naturalOrder());
    }

    public static <T extends Comparable<T>> void selectionSort(List<T> list, Comparator<T> comp) {
        for (int i = 1, j = 0; i < list.size(); j = i++) {
            T temp = list.get(i);
            while (j >= 0 && comp.compare(list.get(j), temp) > 0)
                list.set(j + 1, list.get(j--));
            list.set(j + 1, temp);
        }
    }

    public static <T extends Comparable<T>> void selectionSort(List<T> list) {
        selectionSort(list, naturalOrder());
    }

    public static <T extends Comparable<T>> void mergeSort(List<T> arr, Comparator<T> comp) {
        sort( arr, 0, arr.size(), comp);
    }

    public static <T extends Comparable<T>> void mergeSort(List<T> arr) {
        sort( arr, 0, arr.size(), naturalOrder());
    }

    private static <T extends Comparable<T>> void sort(List<T> list, int l, int r, Comparator<T> comp) {
        if (l + 1 < r) {
            int m = (l + r) >>> 1;
            sort(list, l, m, comp);
            sort(list, m, r, comp);
            Queue<T> left = convert(sublist(list, l, m)),
                right = convert(sublist(list, m, r));
            merge(list, l, left, right, comp);
        }
    }

    private static <T extends Comparable<T>> void merge(List<T> arr, int start, Queue<T> left, Queue<T> right, Comparator<T> comp) {
        while(!left.isEmpty() && !right.isEmpty())
            arr.set(start++, comp.compare(left.peek(), right.peek()) < 0 ? left.dequeue() : right.dequeue());
        while(!left.isEmpty())
            arr.set(start++, left.dequeue());
        while(!right.isEmpty())
            arr.set(start++, right.dequeue());
    }

    public static <T extends Comparable<T>> List<T> heapSort(List<T> arr) {
        return heapSort(arr, Comparator.reverseOrder());
    }
    public static <T extends Comparable<T>> List<T> heapSort(List<T> arr, Comparator<T> comp) {
        var heap = new Heap<>(comp);
        heap.addAll(arr);
        return heap.getSorted();
    }

    public static <T extends Comparable<T>> List<T> heapSort(List<T> arr, Supplier<Heap<T>> heapInstance) {
        var heap = heapInstance.get();
        heap.addAll(arr);
        return heap.getSorted();
    }
}
