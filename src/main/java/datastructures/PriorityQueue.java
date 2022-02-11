package datastructures;

import java.util.Comparator;

public interface PriorityQueue<T extends Comparable<T>> extends Queue<T> {

    @Override
    @SuppressWarnings("unchecked")
    default void addAll(T... elements) {
        for(T t : elements)
            enqueue(t);
    }

    @Override
    default void addAll(DataStructure<T> dataStructure) {
        for(T t : dataStructure)
            enqueue(t);
    }
}
