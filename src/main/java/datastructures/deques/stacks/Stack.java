package datastructures.deques.stacks;

import datastructures.DataStructure;

public interface Stack<T> extends Iterable<T>, DataStructure {
    void push(T element);
    T pop();
    T peek();
    int size();
    boolean isEmpty();
    boolean contains(T element);
}
