package datastructures.lists;

import datastructures.deques.Deque;
import datastructures.deques.stacks.Stack;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.IntStream;

public class ArrayList<T> implements List<T>, Stack<T>, Deque<T> {

    private static final int initialSize = 16;

    private T[] elements;
    private int size = 0;
    @SuppressWarnings("unchecked")
    public ArrayList(){
        elements = (T[]) new Object[initialSize];
    }
    @SuppressWarnings("unchecked")
    public ArrayList(int initialSize) {
        elements = (T[]) new Object[initialSize];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    private void ensureCapacity() {
        if(isFull())
            resize(elements.length << 1);
        else if(isHalfFullAndGreaterThanInitialSize())
            resize(elements.length >> 1);
    }
    @SuppressWarnings("unchecked")
    private void resize(int i) {
        T[] tmp = (T[]) new Object[i];
        System.arraycopy(elements, 0, tmp, 0, size);
        elements = tmp;
    }

    private boolean isHalfFullAndGreaterThanInitialSize() {
        return elements.length > initialSize && size <= elements.length >> 1;
    }

    private boolean isFull() {
        return size == elements.length;
    }

    @Override
    @SafeVarargs
    public final void addAll(T... elements) {
        for(T t : elements) {
            add(t);
        }
    }

    @Override
    public void set(int index, T element) {
        if(isOutOfBounds(index))
            throw new NullPointerException("Index " + index + " out of bounds for range 0 to " + size);
        elements[index] = element;
    }

    @Override
    public void insert(int index, T element) {
        ensureCapacity();
        pushTrailingElements(index, index + 1);
        elements[index] = element;
        size++;
    }

    private boolean isOutOfBounds(int index) {
        return index < 0 || index >= size;
    }

    @Override
    public T get(int index) {
        if(isOutOfBounds(index))
            throw new NullPointerException("Index " + index + " out of bounds for range 0 to " + size);
        return elements[index];
    }

    @Override
    public T remove(int index) {
        if(isOutOfBounds(index))
            throw new NullPointerException("Index " + index + " out of bounds for range 0 to " + size);
        pushTrailingElements(index + 1, index);
        T tmp = elements[size - 1];
        elements[--size] = null;
        ensureCapacity();
        return tmp;
    }

    private void pushTrailingElements(int src, int dest) {
        System.arraycopy(elements, src, elements, dest, size - src);
    }

    @Override
    public void removeAll(T element) {
        for(int i = 0; i < size; i++) {
            if(get(i).equals(element)) {
                remove(i--);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        elements = (T[]) new Object[initialSize];
        size = 0;
    }

    @Override
    public void push(T element) {
        addLast(element);
    }

    @Override
    public T pop() {
        return removeLast();
    }

    @Override
    public T peek() {
        return get(size - 1);
    }

    @Override
    public boolean contains(T element) {
        for(T t : this)
            if(t.equals(element)) return true;
        return false;
    }

    @Override
    public int indexOf(T element) {
        return IntStream.range(0,size)
                .filter(i -> get(i).equals(element))
                .findFirst()
                .orElseThrow();
    }
    @Override
    public String toString() {
        if(isEmpty())
            return "[]";
        var sb = new StringBuilder("[");
        for(T element : this)
            sb.append(element).append(", ");
        return sb.substring(0, sb.length() - 2) + "]";
    }

    @Override
    public void addFirst(T element) {
        insert(0, element);
    }

    @Override
    public void addLast(T element) {
        add(element);
    }

    @Override
    public T removeFirst() {
        return remove(0);
    }

    @Override
    public T removeLast() {
        return remove(size - 1);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override public boolean equals(Object o) {
        if(o == null)
            return false;
        if(o == this)
            return true;
        if(o instanceof ArrayList<?> cast) {
            if(cast.size() != size)
                return false;
            boolean equal = true;
            for(int i = 0; equal && i < size; i++)
                equal = get(i).equals(cast.get(i));
            return equal;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < size; i++)
            hash += Objects.hashCode(get(i)) * i;
        return hash;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() {
                return elements[i++];
            }
        };
    }
}
