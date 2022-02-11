package datastructures;

public final class AVLTree<E extends Comparable<E>> implements Tree<E>  {

    BalancedNode<E> root;

    protected static class BalancedNode<E extends Comparable<E>> {
        E element;
        BalancedNode<E> left, right;
        int balance = 0;

        public BalancedNode(E element) {
            this.element = element;
        }

        void balance() {

        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }


    @Override
    public void add(E element) {
        if(root == null) root = new BalancedNode<>(element);
        else add(element, root);
    }

    protected void add(E element, BalancedNode<E> node) {
        if(node != null) {
            if (node.left == null && element.compareTo(node.element) < 0)
                node.left = new BalancedNode<>(element);
            else if (node.right == null && element.compareTo(node.element) > 0)
                node.right = new BalancedNode<>(element);
            else if (element.compareTo(node.element) < 0)
                add(element, node.left);
            else if (element.compareTo(node.element) > 0)
                add(element, node.right);
            node.balance();
        }
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public void remove(E e) {
    }

    @Override
    public List<E> preOrder() {
        return null;
    }

    @Override
    public List<E> postOrder() {
        return null;
    }

    @Override
    public List<E> inOrder() {
        return null;
    }

    @Override
    public E min() {
        return null;
    }

    @Override
    public E max() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
