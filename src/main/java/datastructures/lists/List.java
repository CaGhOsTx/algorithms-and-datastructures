package datastructures.lists;

import datastructures.DataStructure;

import java.util.function.Supplier;

public interface List<T> extends Iterable<T>, DataStructure {
    int size();
    void add(T element);
    @SuppressWarnings("unchecked") void addAll(T... elements);
    void set(int index, T element);
    void insert(int index, T element);
    T get(int index);
    T remove(int index);
    boolean isEmpty();
    void removeAll(T element);
    void clear();
    boolean contains(T element);
    int indexOf(T element);

    @SafeVarargs
    static <T, C extends List<T>> List<T> of(Supplier<C> listSupplier, T... elements) {
        List<T> list = listSupplier.get();
        for(T t : elements)
            list.add(t);
        return list;
    }

    @SafeVarargs
    static <T, C extends List<T>> List<T> of(T... elements) {
        List<T> list = new ArrayList<>();
        for(T t : elements)
            list.add(t);
        return list;
    }
}
