public class BalanceableTree {
    private Node root;

    public BalanceableTree(int value) {
        root = new Node(value);
    }

    public Node getRoot() {
        return root;
    }

    public Node search(int key) {
        Node nodeFound = searchHelper(root, key);
        rebalanceAccess(nodeFound);
        return nodeFound;
    }

    private Node searchHelper(Node node, int key) {
        if (key == node.getValue()) {
            return node;
        } else if (key < node.getValue()) {
            if (node.getLeftChild() == null) {
                return node;
            }

            return searchHelper(node.getLeftChild(), key);
        } else {
            if (node.getRightChild() == null) {
                return node;
            }

            return searchHelper(node.getRightChild(), key);
        }
    }

    public int insert(int key) {
        Node newNode = new Node(key);

        Node foundNode = searchHelper(root, key);

        if (foundNode == null) {
            foundNode = foundNode.getParent();
        }

        if (foundNode.getValue() == key) {
            rebalanceAccess(foundNode);
        } else {
            if (key < foundNode.getValue()) {
                foundNode.setLeftChild(newNode);
            } else {
                foundNode.setRightChild(newNode);
            }

            newNode.setParent(foundNode);
            rebalanceInsert(newNode);
        }

        return key;
    }

    public int remove(int key) {
        Node foundNode = searchHelper(root, key);

        if (foundNode.getValue() == key) {
            return removeHelper(foundNode, key);
        } else {
            rebalanceAccess(foundNode);
        }

        return key;
    }

    public int removeHelper(Node node, int key) {
        Node successor = node;

        if (hasNoChildren(node)) {
            removeLeafNode(node, node.getValue());
        }

        if (hasOneChild(node)) {
            replaceNodeWithChildNode(node);
        }

        if (hasTwoChildren(node)) {
            successor = getInorderSuccessor(node.getRightChild());
            node.setValue(successor.getValue());
            removeHelper(successor, successor.getValue());
        }

        rebalanceDelete(successor.getParent());
        return node.getValue();
    }

    private boolean hasNoChildren(Node node) {
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            return true;
        }

        return false;
    }

    private boolean hasOneChild(Node node) {
        if ((node.getLeftChild() != null && node.getRightChild() == null) || (node.getLeftChild() == null && node.getRightChild() != null)) {
            return true;
        }

        return false;
    }

    private boolean hasTwoChildren(Node node) {
        if (node.getLeftChild() != null && node.getRightChild() != null) {
            return true;
        }

        return false;
    }

    private void removeLeafNode(Node node, int value) {
        Node parentNode;

        if (node.getParent() != null) {
            parentNode = node.getParent();
        } else {
            parentNode = node;
        }

        if (parentNode.getLeftChild() != null && parentNode.getLeftChild().getValue() == value) {
            parentNode.setLeftChild(null);
        } else {
            parentNode.setRightChild(null);
        }
    }

    private void replaceNodeWithChildNode(Node node) {
        Node child = (node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild());

        if (child != null) {
            child.setParent(node.getParent());
        }

        if (node == root) {
            root = child;
        } else {
            Node parent = node.getParent();

            if (node == parent.getLeftChild()) {
                parent.setLeftChild(child);
            } else {
                parent.setRightChild(child);
            }
        }
    }

    private Node getInorderSuccessor(Node node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }

        return node;
    }

    public void rotate(Node node) {
        Node x = node;
        Node y = x.getParent();
        Node z = y.getParent();

        if (z == null) {
            root = x;
            x.setParent(null);
        } else {
            relink(z, x, y == z.getLeftChild());
        }

        if (x == y.getLeftChild()) {
            relink(y, x.getRightChild(), true);
            relink(x, y, false);
        } else {
            relink(y, x.getLeftChild(), false);
            relink(x, y, true);
        }
    }

    public Node restructure(Node x) {
        Node y = x.getParent();
        Node z = y.getParent();

        if ((x == y.getRightChild()) == (y == z.getRightChild())) {
            rotate(y);
            return y;
        } else {
            rotate(x);
            rotate(x);
            return x;
        }
    }

    private void relink(Node parent, Node child, boolean makeLeftChild) {
        if (child != null) {
            child.setParent(parent);
        }

        if (makeLeftChild) {
            parent.setLeftChild(child);
        } else {
            parent.setRightChild(child);
        }
    }

    public boolean isInternal(Node node) {
        return hasOneChild(node) || hasTwoChildren(node);
    }

    public void rebalanceAccess(Node node) {}
    public void rebalanceInsert(Node node) {}
    public void rebalanceDelete(Node node) {}

    public void inorder(Node node) {
        if (node != null) {
            inorder(node.getLeftChild());
            printNode(node);
            inorder(node.getRightChild());
        }
    }

    private void printNode(Node node) {
        if (node.getValue() == getRoot().getValue()) {
            System.out.print("Root Node: " + node.getValue());
        } else {
            System.out.print("Current Node: " + node.getValue());
        }

        printNodeInformation(node.getParent(), " Parent: ");
        printNodeInformation(node.getLeftChild(), " Left Child: ");
        printNodeInformation(node.getRightChild(), " Right Child: ");
        System.out.print(" Height Property: " + node.getHeightProperty());

        System.out.println();
    }

    private void printNodeInformation(Node node, String string) {
        if (node != null) {
            System.out.print(string + node.getValue());
        }
    }

}
