import datastructures.BinaryTree;
import datastructures.List;
import datastructures.Tree;

import javax.swing.*;

public class Main extends JFrame {

    static JTree tree;

    public static void main(String[] args) {
        Tree<Integer> tree = new BinaryTree<>();
        List<Integer> list = List.of(26,58,84,81,94,69,15,29,39,52,33,85);
        tree.addAll(list);
        System.out.println(tree.inOrder());
    }


}
