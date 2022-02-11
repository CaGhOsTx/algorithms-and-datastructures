package datastructures;

import java.util.Comparator;

public final class MinHeap<T extends Comparable<T>> extends Heap<T> {

    public MinHeap () {
        super(Comparator.naturalOrder());
    }
}
