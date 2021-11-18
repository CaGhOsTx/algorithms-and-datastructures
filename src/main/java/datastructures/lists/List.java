package datastructures.lists;

public interface List<T> extends Iterable<T> {
    int size();
    void add(T element);
    @SuppressWarnings("unchecked") void addAll(T... element);
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
    static <T> List<T> of(T... elements) {
        List<T> tmp = new ArrayList<>();
        for(T t : elements)
            tmp.add(t);
        return tmp;
    }
}
