public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree(10);
        tree.insert(5);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);
        tree.insert(11);
        tree.insert(25);
        tree.insert(15);
        tree.inorder(tree.getRoot());
        tree.remove(11);
        System.out.println();
        tree.inorder(tree.getRoot());
        tree.remove(4);
        System.out.println();
        tree.inorder(tree.getRoot());
    }
}

//RedBlackTreeTester
