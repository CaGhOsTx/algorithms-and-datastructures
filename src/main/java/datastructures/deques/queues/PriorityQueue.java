package datastructures.deques.queues;

public interface PriorityQueue<T extends Comparable<T>> extends Queue<T> {
    @Override
    default void enqueue(T element) {
        throw new IllegalStateException("needs to be implemented");
    }
}
