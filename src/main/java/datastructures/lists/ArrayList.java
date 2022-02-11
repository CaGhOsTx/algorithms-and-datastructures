package datastructures.lists;

import datastructures.Deque;
import datastructures.List;
import datastructures.Queue;
import datastructures.Stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.IntStream;

public class ArrayList<T> extends ArrayCollection<T> implements List<T>, Stack<T>, Queue<T>, Deque<T> {

    public ArrayList() {
        super();
    }

    public ArrayList(int initialSize) {
        super(initialSize);
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
        if (isEmpty()) throw new IllegalStateException("Deque is empty");
        return remove(0);
    }

    @Override
    public T removeLast() {
        if (isEmpty()) throw new IllegalStateException("Deque is empty");
        return remove(size - 1);
    }

    @Override
    public T peekFirst() {
        if (isEmpty()) throw new IllegalStateException("Deque is empty");
        return get(0);
    }

    @Override
    public T peekLast() {
        if (isEmpty()) throw new IllegalStateException("Deque is empty");
        return get(size - 1);
    }

    @Override
    public void enqueue(T element) {
        add(element);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        return remove(0);
    }

    @Override
    public void push(T element) {
        insert(0, element);
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return remove(0);
    }

    @Override
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return elements[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void addElement(T element) {
        elements[size++] = element;
    }

    @Override
    @SafeVarargs
    public final void addAll(T... elements) {
        for (T t : elements)
            add(t);
    }

    @Override
    public void set(int index, T element) {
        if (isOutOfBounds(index))
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for range 0 to " + size);
        elements[index] = element;
    }

    @Override
    public void insert(int index, T element) {
        super.ensureCapacity();
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
    protected T removeElement(int index) {
        if (isOutOfBounds(index))
            throw new IllegalStateException("Index " + index + " out of bounds for range 0 to " + size);
        pushTrailingElements(index + 1, index);
        T tmp = elements[size - 1];
        elements[--size] = null;
        return tmp;
    }

    private void pushTrailingElements(int src, int dest) {
        System.arraycopy(elements, src, elements, dest, size - src);
    }

    @Override
    public void removeAll(T element) {
        for (int i = 0; i < size; i++)
            if (get(i).equals(element)) remove(i--);
    }

    @Override
    public void clear() {
        super.clear();
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
        if (isEmpty()) return "[]";
        var sb = new StringBuilder("[");
        for (T element : this)
            sb.append(element).append(", ");
        return sb.substring(0, sb.length() - 2) + "]";
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o instanceof ArrayList<?> cast) {
            if (cast.size() != size) return false;
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

    @Override
    public Iterator<T> circularIterator() {
        return new Iterator<T>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                return elements[i < size ? i++ : (i = 0)];
            }
        };
    }
}
