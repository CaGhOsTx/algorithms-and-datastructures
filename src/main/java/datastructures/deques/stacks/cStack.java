package datastructures.deques.stacks;

import datastructures.lists.ArrayList;
import datastructures.lists.List;

import java.util.Iterator;
import java.util.function.Supplier;

public class cStack<T> implements Stack<T> {

    List<T> list;

    public <C extends  List<T>>cStack(Supplier<C> implementingListClassConstructor) {
        list = implementingListClassConstructor.get();
    }

    public cStack(int initialCapacity) {
        list = new ArrayList<>(initialCapacity);
    }

    public void push(T element) {
        list.add(element);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public T pop() {
        if(isEmpty())
            throw new UnsupportedOperationException("Stack is empty");
        return list.remove(list.size() - 1);
    }

    public T peek() {
        return list.get(list.size() - 1);
    }

    public boolean contains(T element) {
        return list.contains(element);
    }

    public int size() {
        return list.size();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
