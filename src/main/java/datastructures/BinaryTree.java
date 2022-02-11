package datastructures;

import datastructures.lists.ArrayList;

public class BinaryTree<E extends Comparable<E>> implements Tree<E> {

    protected Node<E> root;

    protected static class Node<E extends Comparable<E>> {
        protected E element;
        protected Node<E> left, right;

        public Node(E element) {
            this.element = element;
            left = null;
            right = null;
        }

        protected boolean isLeaf() {
            return left == null && right == null;
        }

        protected void swapElements(Node<E> other) {
            E tmp = other.element;
            other.element = element;
            element = tmp;
        }

        Node<E> min(Node<E> subTree) {
            var tmp = subTree;
            while(tmp.left != null) tmp = tmp.left;
            return tmp;
        }

        Node<E> min() {
            return min(this);
        }

        Node<E> max() {
            return max(this);
        }

        Node<E> successor() {
            return min(right);
        }

        Node<E> max(Node<E> subTree) {
            var tmp = subTree;
            while(tmp.right != null) tmp = tmp.right;
            return tmp;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void add(E element) {
        if(root == null) root = new Node<>(element);
        else add(element, root);
    }

    protected Node<E> add(E element, Node<E> node) {
        if(node != null) {
            if (node.left == null && element.compareTo(node.element) < 0)
                return node.left = new Node<>(element);
            else if (node.right == null && element.compareTo(node.element) > 0)
                return node.right = new Node<>(element);
            else if (element.compareTo(node.element) < 0)
                add(element, node.left);
            else if (element.compareTo(node.element) > 0)
                add(element, node.right);
        }
        return null;
    }

    @Override
    public boolean contains(E element) {
        return contains(element, root);
    }

    private boolean contains(E element, Node<E> node) {
        if(node != null) {
            if (element.equals(node.element)) return true;
            if (element.compareTo(node.element) < 0)
                return contains(element, node.left);
            else if (element.compareTo(node.element) > 0)
                return contains(element, node.right);
        }
        return false;
    }

    @Override
    public void remove(E e) {
        if(!isEmpty()) {
            if(root.isLeaf() && root.element.equals(e)) root = null;
            else remove(e, root, null);
        }
    }

    private void remove(E e, Node<E> node, Node<E> parent) {
        if(node != null) {
            if (node.element.equals(e)) {
                if (node.isLeaf())
                    removeLeaf(node, parent);
                else if (node.left == null)
                    swapWithRightChild(node, parent);
                else if (node.right == null)
                    swapWithLeftChild(node, parent);
                else
                    swapWithSuccessorAndRemove(e, node);
            } else if (e.compareTo(node.element) < 0)
                remove(e, node.left, node);
            else if(e.compareTo(node.element) > 0)
                remove(e, node.right, node);
        }
    }

    private void swapWithRightChild(Node<E> node, Node<E> parent) {
        if(parent == null) {
            root = node.right;
        }
        else {
            if (parent.left == node) parent.left = node.right;
            else parent.right = node.right;
            node.right = null;
        }
    }

    private void swapWithLeftChild(Node<E> node, Node<E> parent) {
        if(parent == null) {
            root = node.left;
        }
        else {
            if (parent.right == node) parent.right = node.left;
            else parent.left = node.left;
            node.left = null;
        }
    }

    private void swapWithSuccessorAndRemove(E e, Node<E> node) {;
        node.swapElements(node.successor());
        remove(e, node.right, node);
    }

    private void removeLeaf(Node<E> node, Node<E> parent) {
        if (parent.left == node) parent.left = null;
        else parent.right = null;
    }

    @Override
    public List<E> preOrder() {
        if(isEmpty()) return List.of();
        return preOrder(new ArrayList<>(), root);
    }

    private List<E> preOrder(List<E> list, Node<E> node) {
        list.add(node.element);
        if(node.left != null)
            preOrder(list, node.left);
        if(node.right != null)
            preOrder(list, node.right);
        return list;
    }

    @Override
    public List<E> inOrder() {
        if(isEmpty()) return List.of();
        return inOrder(new ArrayList<>(), root);
    }

    @Override
    public E min() {
       return root.min().element;
    }

    @Override
    public E max() {
        return root.max().element;
    }

    private List<E> inOrder(List<E> list, Node<E> node) {
        if(node.left != null)
            inOrder(list, node.left);
        list.add(node.element);
        if(node.right != null)
            inOrder(list, node.right);
        return list;
    }

    @Override
    public List<E> postOrder() {
        if(isEmpty()) return List.of();
        return postOrder(new ArrayList<>(), root);
    }

    private List<E> postOrder(List<E> list, Node<E> node) {
        if(node.left != null)
            postOrder(list, node.left);
        if(node.right != null)
            postOrder(list, node.right);
        list.add(node.element);
        return list;
    }
}
