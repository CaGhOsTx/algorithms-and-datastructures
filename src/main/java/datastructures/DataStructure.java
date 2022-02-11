package datastructures;

import algorithms.Sorting;

import java.util.Objects;
import java.util.function.Supplier;
public interface DataStructure<T> extends Iterable<T> {
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(T element);
    @SuppressWarnings("unchecked")
    void addAll(T... elements);
    void addAll(DataStructure<T> dataStructure);
    @SuppressWarnings("unchecked")
    default <U extends Comparable<U>> void sort() {
        try {
            Sorting.mergeSort(DataStructure.convert((DataStructure<U>) this));
        }
        catch (Exception e){
            throw new IllegalStateException("cannot be sorted");
        }
    }

    static <T extends Enum<T>,C extends List<T>> C fromEnum(Class<T> enumeration, Supplier<C> ds) {
        C list = Objects.requireNonNull(ds).get();
        list.addAll(Objects.requireNonNull(enumeration).getEnumConstants());
        return list;
    }

    static <T, C extends List<T>> C toList(T[] arr, Supplier<C> ds) {
        C list = Objects.requireNonNull(ds).get();
        list.addAll(arr);
        return list;
    }
    @SuppressWarnings("unchecked")
    static <T> T[] toArray(DataStructure<T> ds) {
        Objects.requireNonNull(ds);
        var arr = (T[]) new Object[ds.size()];
        var it = ds.iterator();
        for (int i = 0; i < arr.length; i++)
            arr[i] = it.next();
        return arr;
    }
    @SuppressWarnings("unchecked")
    static <T, C extends DataStructure<T>> C convert(DataStructure<T> ds) {
        return (C) Objects.requireNonNull(ds);
    }

    static <T, C extends DataStructure<T>> C convert(DataStructure<T> ds, Supplier<C> resultType) {
        var res = resultType.get();
        res.addAll(ds);
        return res;
    }
}
