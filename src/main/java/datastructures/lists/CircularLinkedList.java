package datastructures.lists;

import java.util.Iterator;

public class CircularLinkedList<T> extends LinkedList<T> {
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                T el = node.element;
                node = node.next == null ? head : node.next;
                return el;
            }
        };
    }
}
