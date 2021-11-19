package datastructures.deques.queues;

import datastructures.DataStructure;

public interface Queue<T> extends Iterable<T>, DataStructure {
    void enqueue(T element);
    T dequeue();
    T peek();
    int size();
    boolean isEmpty();
    boolean contains(T element);
}
