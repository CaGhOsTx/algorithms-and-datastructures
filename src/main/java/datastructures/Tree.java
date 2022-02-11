package datastructures;

public interface Tree<E extends Comparable<E>> {
     default void addAll(List<E> elements) {
        for(var t : elements)
            add(t);
    }
     void add(E element);
     boolean contains(E element);
     void remove(E e);
     List<E> preOrder();
     List<E> postOrder();
     List<E> inOrder();
     E min();
     E max();
     boolean isEmpty();
}
