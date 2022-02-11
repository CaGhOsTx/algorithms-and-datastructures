package datastructures.lists;

import datastructures.List;

public abstract class ArrayCollection<T> implements List<T> {
    protected final int initialSize;

    protected T[] elements;
    protected int size = 0;
    @SuppressWarnings("unchecked")
    public ArrayCollection(){
        elements = (T[]) new Object[initialSize = 16];
    }
    @SuppressWarnings("unchecked")
    public ArrayCollection(int initialSize) {
        this.initialSize = initialSize;
        elements = (T[]) new Object[initialSize];
    }

    protected void ensureCapacity() {
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

    @SuppressWarnings("unchecked")
    public void clear() {
        elements = (T[]) new Object[initialSize];
        size = 0;
    }

    @Override
    final public void add(T element) {
        ensureCapacity();
        addElement(element);
    }

    @Override
    final public T remove(int index) {
        T removed = removeElement(index);
        ensureCapacity();
        return removed;
    }

    protected abstract T removeElement(int index);
    protected abstract void addElement(T element);
}
