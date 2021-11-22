package datastructures;

public interface Stack<T> extends DataStructure<T> {
    void push(T element);
    void clear();
    T pop();
    T peek();
    int size();
    boolean isEmpty();
    boolean contains(T element);
}
