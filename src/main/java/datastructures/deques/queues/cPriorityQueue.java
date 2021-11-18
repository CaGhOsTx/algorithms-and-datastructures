package datastructures.deques.queues;

import datastructures.lists.List;

import java.util.Comparator;
import java.util.function.Supplier;

public class cPriorityQueue<T extends Comparable<T>> extends cQueue<T> implements PriorityQueue<T> {
    private final Comparator<T> comparator;

    public <C extends List<T>> cPriorityQueue(Supplier<C> supplier, Comparator<T> comparator) {
        super(supplier);
        this.comparator = comparator;
    }

    @Override
    public void enqueue(T element) {
        if(!tryInsert(element))
            super.list.add(element);
    }

    private boolean tryInsert(T element) {
        for (int i = 0; i < super.size(); i++) {
            if(comparator.compare(element, super.list.get(i)) > 0) {
                super.list.insert(i, element);
                return true;
            }
        }
        return false;
    }
}
