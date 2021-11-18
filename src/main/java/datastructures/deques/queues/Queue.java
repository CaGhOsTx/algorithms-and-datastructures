package datastructures.deques.queues;

public interface Queue<T> extends Iterable<T> {
    void enqueue(T element);
    T dequeue();
    T peek();
    boolean contains(T element);
    boolean isEmpty();
    void clear();
    int size();
}
