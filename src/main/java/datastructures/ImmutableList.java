package datastructures;

import datastructures.lists.ArrayList;

public interface ImmutableList<E> extends DataStructure<E> {
    E get(int index);
    int indexOf(E element);
    @Override
    @SuppressWarnings("unchecked")
    default void addAll(E... elements) {
        throw new UnsupportedOperationException("List is immutable");
    }
    static <E> ImmutableList<E> of(E e1) {
        List<E> list = new ArrayList<>(1);
        list.add(e1);
        return DataStructure.convert(list);
    }
    static <E> ImmutableList<E> of(E e1, E e2) {
        List<E> list = new ArrayList<>(2);
        list.add(e1);list.add(e2);
        return DataStructure.convert(list);
    }
    static <E> ImmutableList<E> of(E e1, E e2, E e3) {
        List<E> list = new ArrayList<>(3);
        list.add(e1);list.add(e2);list.add(e3);
        return DataStructure.convert(list);
    }
    static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4) {
        List<E> list = new ArrayList<>(4);
        list.add(e1);list.add(e2);list.add(e3);list.add(e4);
        return DataStructure.convert(list);
    }
    static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5) {
        List<E> list = new ArrayList<>(5);
        list.add(e1);list.add(e2);
        list.add(e3);list.add(e4);list.add(e5);
        return DataStructure.convert(list);
    }
    static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
        List<E> list = new ArrayList<>(6);
        list.add(e1);list.add(e2);list.add(e3);
        list.add(e4);list.add(e5);list.add(e6);
        return DataStructure.convert(list);
    }
    static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
        List<E> list = new ArrayList<>(7);
        list.add(e1);list.add(e2);list.add(e3);
        list.add(e4);list.add(e5);list.add(e6);list.add(e7);
        return DataStructure.convert(list);
    }
    static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        List<E> list = new ArrayList<>(8);
        list.add(e1);list.add(e2);list.add(e3);list.add(e4);
        list.add(e5);list.add(e6);list.add(e7);list.add(e8);
        return DataStructure.convert(list);
    }
    static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        List<E> list = new ArrayList<>(9);
        list.add(e1);list.add(e2);list.add(e3);list.add(e4);
        list.add(e5);list.add(e6);list.add(e7);list.add(e8);list.add(e9);
        return DataStructure.convert(list);
    }
    static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        List<E> list = new ArrayList<>(10);
        list.add(e1);list.add(e2);list.add(e3);list.add(e4);list.add(e5);
        list.add(e6);list.add(e7);list.add(e8);list.add(e9);list.add(e10);
        return DataStructure.convert(list);
    }
    @SafeVarargs
    static <E> ImmutableList<E> of(E... elements) {
        List<E> list = new ArrayList<>(elements.length);
        list.addAll(elements);
        return DataStructure.convert(list);
    }
}
