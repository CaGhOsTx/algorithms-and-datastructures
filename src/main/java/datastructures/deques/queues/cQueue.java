package datastructures.deques.queues;

import datastructures.lists.ArrayList;
import datastructures.lists.List;

import java.util.Iterator;
import java.util.function.Supplier;

public class cQueue<T> implements Queue<T> {

    protected List<T> list;

    public <C extends  List<T>> cQueue(Supplier<C> implementingListClass) {
        list = implementingListClass.get();
    }

    public cQueue(int initialCapacity) {
        list = new ArrayList<>(initialCapacity);
    }
    @Override
    public void enqueue(T element) {
        list.add(element);
    }

    @Override
    public T dequeue() {
        return list.remove(0);
    }

    @Override
    public T peek() {
        return list.get(0);
    }

    @Override
    public boolean contains(T element) {
        return list.contains(element);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
