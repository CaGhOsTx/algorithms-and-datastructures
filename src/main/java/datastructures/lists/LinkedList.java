package datastructures.lists;

import datastructures.Deque;
import datastructures.List;
import datastructures.Queue;
import datastructures.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements List<T>, Stack<T>, Queue<T>, Deque<T> {

    protected Node<T> head;
    int size = 0;

    static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public int hashCode() {
            return Objects.hash(element, next);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", next=" + next +
                    '}';
        }
    }

    @Override
    public void addFirst(T element) {
        head = new Node<>(element, head);
        size++;
    }

    @Override
    public void addLast(T element) {
        add(element);
    }

    @Override
    public T removeFirst() {
        if(isEmpty())
            throw new IllegalStateException("Deque is empty");
        var el = head.element;
        head = head.next;
        size--;
        return el;
    }

    @Override
    public T removeLast() {
        if(isEmpty())
            throw new IllegalStateException("Deque is empty");
        return remove(size - 1);
    }

    @Override
    public T peekFirst() {
        if(isEmpty())
            throw new IllegalStateException("Deque is empty");
        return get(0);
    }

    @Override
    public T peekLast() {
        if(isEmpty())
            throw new IllegalStateException("Deque is empty");
        return get(size - 1);
    }

    @Override
    public void enqueue(T element) {
        addLast(element);
    }

    @Override
    public T dequeue() {
        if(isEmpty())
            throw new IllegalStateException("Queue is empty");
        return removeFirst();
    }

    @Override
    public void push(T element) {
        addFirst(element);
    }

    @Override
    public T pop() {
        if(isEmpty())
            throw new IllegalStateException("Stack is empty");
        return removeFirst();
    }

    @Override
    public T peek() {
        if(isEmpty())
            throw new IllegalStateException("Stack is empty");
        return head.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T element) {
        if(head == null)
            head = new Node<>(element, null);
        else {
            var tmp = head;
            while(tmp.next != null)
                tmp = tmp.next;
            tmp.next = new Node<>(element, null);
        }
        size++;
    }

    @SafeVarargs
    @Override
    public final void addAll(T... elements) {
        for(T element : elements) {
            add(element);
        }
    }

    @Override
    public void set(int index, T element) {
        checkForOutOfBounds(index);
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        var tmp = head;
        for (int i = 0; i < index; i++)
            tmp = tmp.next;
        tmp.element = element;
    }

    @Override
    public void insert(int index, T element) {
        checkForOutOfBounds(index);
        if(index == 0)
            addFirst(element);
        else {
            var tmp = head;
            for (int i = 0; i < index - 1; i++)
                tmp = tmp.next;
            tmp.next = new Node<>(element, tmp.next);
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
        checkForOutOfBounds(index);
        if(index == 0 || size == 1) return removeFirst();
        var tmp = head;
        for (int i = 0; i < index - 1; i++)
            tmp = tmp.next;
        return removeNext(tmp);
    }

    private T removeNext(Node<T> tmp) {
        var el = tmp.next.element;
        tmp.next = tmp.next.next;
        size--;
        return el;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void removeAll(T element) {
        var tmp = head;
        for (int i = 0; i < size - 1; i++) {
            if(tmp.next.element.equals(element))
                removeNext(tmp);
            tmp = tmp.next;
        }
    }

    private void checkForOutOfBounds(int index) {
        if(isOutOfBounds(index))
            throw new IllegalStateException(String.format("index %d is out of bounds for length %d%n", index, size));
    }

    private boolean isOutOfBounds(int index) {
        return index < 0 || index > size;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
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
    public boolean contains(T element) {
        for(T t : this)
            if(t.equals(element))
                return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedList<?> other = (LinkedList<?>) o;
        return size == other.size && Objects.equals(head, other.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, size);
    }

    @Override
    public int indexOf(T element) {
        var tmp = head;
        for (int i = 0; i < size; i++) {
            if(tmp.element.equals(element))
                return i;
            tmp = tmp.next;
        }
        throw new NoSuchElementException("list does not contain " + element);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                var temp = node.element;
                node = node.next;
                return temp;
            }
        };
    }

    @Override
    public Iterator<T> circularIterator() {
        return new Iterator<>() {
            Node<T> itr = head;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                T val = itr.element;
                itr = itr.next != null ? itr.next : head;
                return val;
            }
        };
    }
}
