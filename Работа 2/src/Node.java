public class Node {
    private Node parent;
    private Node leftChild;
    private Node rightChild;
    private int heightProperty = 0;
    private int value;

    public Node(int value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public int getHeightProperty() {
        return heightProperty;
    }

    public void setHeightProperty(int heightProperty) {
        this.heightProperty = heightProperty;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
