package datastructures.deques.stacks;

public interface Stack<T> extends Iterable<T>{

    void push(T element);
    T pop();
    T peek();
    boolean contains(T element);
    boolean isEmpty();
    int size();

}
