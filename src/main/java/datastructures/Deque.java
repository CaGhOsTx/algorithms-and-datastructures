package datastructures;

public interface Deque<T> extends DataStructure<T> {
    void addFirst(T element);
    void addLast(T element);
    T removeFirst();
    T removeLast();
    T peekFirst();
    T peekLast();
}
