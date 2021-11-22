package datastructures;

public interface Queue<T> extends DataStructure<T> {
    void enqueue(T element);
    T dequeue();
    T peek();
    int size();
    boolean isEmpty();
    boolean contains(T element);
    void clear();
}
