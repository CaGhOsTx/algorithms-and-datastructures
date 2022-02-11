package datastructures.lists;

import datastructures.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<T> implements Stack<T>, Queue<T>, Deque<T>, List<T> {

    protected Node<T> head;
    protected Node<T> tail;
    protected int size = 0;


    public static class Node<T> {
        T element;
        Node<T> next, previous;

        public Node(T element, Node<T> next, Node<T> previous) {
            this.element = Objects.requireNonNull(element);
            this.next = next;
            this.previous = previous;
        }

        @Override
        public int hashCode() {
            return Objects.hash(element, next, previous);
        }
    }

    @Override
    public void addFirst(T element) {
        if (head == null)
            head = tail = new Node<>(element, null, null);
        else {
            head = new Node<>(element, head, null);
            head.next.previous = head;
        }
        size++;
    }

    @Override
    public void addLast(T element) {
        if (tail == null)
            tail = head = new Node<>(element, null, null);
        else {
            tail = new Node<>(element, null, tail);
            tail.previous.next = tail;
        }
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty())
            throw new IllegalStateException("List is empty");
        var el = head.element;
        if (head.next == null) {
            head = tail = null;
        } else {
            head.next.previous = null;
            head = head.next;
        }
        size--;
        return el;
    }

    @Override
    public T removeLast() {
        if (isEmpty())
            throw new IllegalStateException("List is empty");
        var el = tail.element;
        if (tail.previous == null) {
            head = tail = null;
        } else {
            tail.previous.next = null;
            tail = tail.previous;
        }
        size--;
        return el;
    }

    @Override
    public T peekFirst() {
        return head.element;
    }

    @Override
    public T peekLast() {
        return tail.element;
    }

    @Override
    public void enqueue(T element) {
        addLast(Objects.requireNonNull(element));
    }

    @Override
    public T dequeue() {
        return removeFirst();
    }

    @Override
    public void push(T element) {
        addFirst(Objects.requireNonNull(element));
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");
        return head.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T element) {
        addLast(Objects.requireNonNull(element));
    }

    @SafeVarargs
    @Override
    public final void addAll(T... elements) {
        for (T t : Objects.requireNonNull(elements))
            addLast(t);
    }

    @Override
    public void addAll(DataStructure<T> dataStructure) {
        for (T t : Objects.requireNonNull(dataStructure))
            addLast(t);
    }

    @Override
    public void set(int index, T element) {
        checkForOutOfBounds(index);
        var tmp = head;
        for(int i = 0; i < index; i++)
            tmp = tmp.next;
        tmp.element = element;
    }

    private void checkForOutOfBounds(int index) {
        if (isOutOfBounds(index))
            throw new IllegalStateException(String.format("index %d is out of bounds for length %d%n", index, size));
    }

    private boolean isOutOfBounds(int index) {
        return index < 0 || index >= size;
    }

    @Override
    public void insert(int index, T element) {
        if (index == 0)
            addFirst(element);
        else if (index == size)
            addLast(element);
        else {
            var tmp = head;
            for (int i = 0; i < index; i++)
                tmp = tmp.next;
            tmp.previous.next = new Node<>(element, tmp, tmp.previous);
            tmp.previous = tmp.previous.next;
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkForOutOfBounds(index);
        var tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.element;
    }

    @Override
    public T remove(int index) {
        if(isEmpty())
            throw new IllegalStateException("List is empty");
        if (isOutOfBounds(index))
            throw new IllegalStateException("index" + index + " is out of bounds for length " + size);
        var tmp = head;
        if(index == 0)
            return removeFirst();
        for (int i = 0; i < index; i++)
            tmp = tmp.next;
        if(tmp.previous != null) tmp.previous.next = tmp.next;
        if(tmp.next != null)tmp.next.previous = tmp.previous;
        size--;
        return tmp.element;
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

    @Override
    public void removeAll(T element) {
        for (int i = 0; i < size; i++) {
            if(get(i).equals(element))
                remove(i);
        }
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if(get(i).equals(element))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoublyLinkedList<?> other = (DoublyLinkedList<?>) o;
        return size == other.size && Objects.equals(head, other.head) && Objects.equals(tail, other.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail, size);
    }

    @Override
    public int indexOf(T element) {
        int i = 0;
        for (T t : this) {
            if (t.equals(element))
                return i;
            i++;
        }
        throw new NoSuchElementException("list does not contain " + element);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> tmp = head;

            @Override
            public boolean hasNext() {
                return tmp != null;
            }

            @Override
            public T next() {
                var el = tmp.element;
                tmp = tmp.next;
                return el;
            }
        };
    }
}


