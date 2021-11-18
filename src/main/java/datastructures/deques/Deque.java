package datastructures.deques;

import java.io.Serializable;
import java.util.Spliterator;

public interface Deque<T> extends Iterable<T> {
    void addFirst(T element);
    void addLast(T element);
    T removeFirst();
    T removeLast();
    boolean isEmpty();
    boolean contains(T element);
}
