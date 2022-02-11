package datastructures;

import java.util.function.Supplier;

public interface DataStructure<T> extends Iterable<T> {
    int size();
    @SuppressWarnings("unchecked")
    static <T> T[] toArray(DataStructure<T> dt) {
        var arr = (T[]) new Object[dt.size()];
        var it = dt.iterator();
        for (int i = 0; i < arr.length; i++)
            arr[i] = it.next();
        return arr;
    }
    @SuppressWarnings("unchecked")
    static <T, C extends DataStructure<T>> C convert(DataStructure<T> dt) {
        return (C) dt;
    }
}
