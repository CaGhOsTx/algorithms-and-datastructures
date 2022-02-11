package datastructures;

import java.util.Comparator;

public final class MaxHeap<T extends Comparable<T>> extends Heap<T> {

    public MaxHeap() {
        super(Comparator.reverseOrder());
    }
}
