package datastructures.lists;

import datastructures.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.IntStream;

public class ArrayList<T> implements Stack<T>, Queue<T>, Deque<T>, List<T> {

    private final int initialSize;
    private T[] elements;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (T[]) new Object[initialSize = 16];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int initialSize) {
        this.initialSize = initialSize;
        elements = (T[]) new Object[initialSize];
    }

    @Override
    public void addAll(DataStructure<T> ds) {
        for (T t : ds)
            add(t);
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
        if (isEmpty())
            throw new IllegalStateException("Deque is empty");
        return remove(0);
    }

    @Override
    public T removeLast() {
        if (isEmpty())
            throw new IllegalStateException("Deque is empty");
        return remove(size - 1);
    }

    @Override
    public T peekFirst() {
        if (isEmpty())
            throw new IllegalStateException("Deque is empty");
        return get(0);
    }

    @Override
    public T peekLast() {
        if (isEmpty())
            throw new IllegalStateException("Deque is empty");
        return get(size - 1);
    }

    @Override
    public void enqueue(T element) {
        add(element);
    }

    @Override
    public T dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");
        return remove(0);
    }

    @Override
    public void push(T element) {
        insert(0, element);
    }

    @Override
    public T pop() {
        if (isEmpty())
            throw new IllegalStateException("Stack is empty");
        return remove(0);
    }

    @Override
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException("Stack is empty");
        return elements[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T element) {
        insert(size, element);
    }

    void ensureCapacity() {
        if (isFull())
            resize(elements.length << 1);
        else if (isHalfFullAndGreaterThanInitialSize())
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
    @SuppressWarnings("unchecked")
    public void addAll(T... elements) {
        for (T t : elements) {
            add(t);
        }
    }

    @Override
    public void set(int index, T element) {
        if (isOutOfBounds(index))
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for range 0 to " + size);
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
        if (isOutOfBounds(index))
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for range 0 to " + size);
        return elements[index];
    }

    @Override
    public T remove(int index) {
        if (isOutOfBounds(index))
            throw new IllegalStateException("Index " + index + " out of bounds for range 0 to " + size);
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
        for (int i = 0; i < size; i++) {
            if (get(i).equals(element)) {
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
    public boolean contains(T element) {
        for (T t : this)
            if (t.equals(element)) return true;
        return false;
    }

    @Override
    public int indexOf(T element) {
        return IntStream.range(0, size)
                .filter(i -> get(i).equals(element))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        var sb = new StringBuilder("[");
        for (T element : this)
            sb.append(element).append(", ");
        return sb.substring(0, sb.length() - 2) + "]";
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("UseOfConcreteClass")
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o instanceof ArrayList<?> cast) {
            if (cast.size() != size)
                return false;
            boolean equal = true;
            for (int i = 0; equal && i < size; i++)
                equal = get(i).equals(cast.get(i));
            return equal;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(initialSize, size);
        result = 31 * result + Arrays.hashCode(elements);
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int i = 0;
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