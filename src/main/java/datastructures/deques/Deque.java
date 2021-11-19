package datastructures.deques;

import datastructures.DataStructure;

public interface Deque<T> extends Iterable<T>, DataStructure {
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
