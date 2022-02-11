package datastructures;

import datastructures.lists.ArrayList;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public interface List<T> extends ImmutableList<T> {
    void add(T element);
    void set(int index, T element);
    void insert(int index, T element);
    T remove(int index);
    void removeAll(T element);

    @SuppressWarnings("unchecked")
    default <C extends Comparable<C>>void sort(BiConsumer<List<C>, Comparator<C>> sortingMethod) {
        sortingMethod.accept((List<C>) this, Comparator.naturalOrder());
    }

    default void sort(BiConsumer<List<T>, Comparator<T>> sortingMethod, Comparator<T> comparator) {
        sortingMethod.accept(this, comparator);
    }

    static <T> List<T> sublist(List<T> list, int start, int end) {
        var subset = new ArrayList<T>();
        for (int i = 0; i < end - start; i++)
            subset.add(list.get(start + i));
        return subset;
    }

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
        list.addAll(elements);
        return list;
    }
}
