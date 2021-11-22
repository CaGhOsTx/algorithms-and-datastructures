package datastructures.lists;

import datastructures.Deque;
import datastructures.List;
import datastructures.Queue;
import datastructures.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class DoublyLinkedList<T> implements List<T>, Stack<T>, Queue<T>, Deque<T> {

    Node<T> head, tail;
    int size = 0;

    static class Node<T> {
        T element;
        Node<T> next, previous;

        public Node(T element, Node<T> next, Node<T> previous) {
            this.element = element;
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
        if(head == null)
            head = tail = new Node<>(element, null, null);
        else {
            head = new Node<>(element, head, null);
            head.next.previous = head;
        }
        size++;
    }

    @Override
    public void addLast(T element) {
        if(tail == null)
            tail = head = new Node<>(element, null, null);
        else {
            tail = new Node<>(element, null, tail);
            tail.previous.next = tail;
        }
        size++;
    }

    @Override
    public T removeFirst() {
        if(isEmpty())
            throw new IllegalStateException("List is empty");
        var el = head.element;
        if(head.next == null) {
            head = tail = null;
        }
        else {
            head.next.previous = null;
            head = head.next;
        }
        size--;
        return el;
    }

    @Override
    public T removeLast() {
        if(isEmpty())
            throw new IllegalStateException("List is empty");
        var el = tail.element;
        if(tail.previous == null) {
            head = tail = null;
        }
        else {
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
        addLast(element);
    }

    @Override
    public T dequeue() {
        return removeFirst();
    }

    @Override
    public void push(T element) {
        addFirst(element);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public T peek() {
        if(isEmpty())
            throw new IllegalStateException("Queue is empty");
        return head.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T element) {
        addLast(element);
    }

    @SafeVarargs
    @Override
    public final void addAll(T... elements) {
        for(T t : elements) {
            addLast(t);
        }
    }

    @Override
    public void set(int index, T element) {
        optimalTraverse(index, element, tmp -> tmp.element = element);
    }

    private void optimalTraverse(int index, T element, Consumer<Node<T>> action) {
        checkForOutOfBounds(index);
        Node<T> tmp;
        if(size - index > index) {
            tmp = head;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
        }
        else {
            tmp = tail;
            for (int i = size - 1; i > index; i--) {
                tmp = tmp.previous;
            }
        }
        action.accept(tmp);
    }

    private void checkForOutOfBounds(int index) {
        if(isOutOfBounds(index))
            throw new IllegalStateException(String.format("index %d is out of bounds for length %d%n", index, size));
    }

    private boolean isOutOfBounds(int index) {
        return index < 0 || index >= size;
    }

    private T optimalTraverse(int index, Function<Node<T>, T> action) {
        checkForOutOfBounds(index);
        Node<T> tmp;
        if(size - index > index) {
            tmp = head;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
            return action.apply(tmp);
        }
        tmp = tail;
        for (int i = size - 1; i > index; i--) {
            tmp = tmp.previous;
        }
        return action.apply(tmp);
    }

    @Override
    public void insert(int index, T element) {
        if(index == 0)
            addFirst(element);
        else if(index == size)
            addLast(element);
        else {
            optimalTraverse(index, element,
                    tmp -> {
                        size++;
                        var node = new Node<>(element, tmp, tmp.previous);
                        if (tmp.previous != null) tmp.previous.next = node;
                        tmp.previous = node;
                    });
        }
    }

    @Override
    public T get(int index) {
       return optimalTraverse(index, n -> n.element);
    }

    @Override
    public T remove(int index) {
        return optimalTraverse(index, n -> {
            var el = n.element;
            if(n.previous == null)
                return removeFirst();
            else
                n.previous.next = n.next;
            if(n.next == null)
                return removeLast();
            else
                n.next.previous = n.previous;
            n.next = n.previous = null;
            size--;
            return el;
        });
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
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void removeAll(T element) {
        for (int i = 0, j = size - 1; i != j; i++, j--) {
            if(element.equals(get(i)))
                remove(i);
            if(element.equals(get(j)))
                remove(j);
        }
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0, j = size - 1; j >= i; i++, j--)
            if(element.equals(get(i)) || element.equals(get(j)))
                return true;
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
        for (int i = 0; i < size; i++) {
            if(element.equals(this.get(i)))
                return i;
        }
        throw new NoSuchElementException("list does not contain " + element);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                var el = current.element;
                current = current.next;
                return el;
            }
        };
    }
}