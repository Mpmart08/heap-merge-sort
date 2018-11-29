public class Node {

    private int value;
    private Node parent = null;
    private Node left = null;
    private Node right = null;

    public Node(int value) {

        this.value = value;
    }

    public int getValue() {

        return this.value;
    }

    public Node getParent() {

        return this.parent;
    }

    public Node getLeft() {

        return this.left;
    }

    public Node getRight() {

        return this.right;
    }

    public void setValue(int value) {

        this.value = value;
    }

    public void setParent(Node parent) {

        this.parent = parent;
    }

    public void setLeft(Node left) {

        this.left = left;
    }

    public void setRight(Node right) {

        this.right = right;
    }

}