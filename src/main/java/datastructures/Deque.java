package datastructures;

import datastructures.DataStructure;

public interface Deque<T> extends DataStructure<T> {
    void addFirst(T element);
    void addLast(T element);
    T removeFirst();
    T removeLast();
    T peekFirst();
    T peekLast();
    int size();
    boolean isEmpty();
    boolean contains(T element);
}
