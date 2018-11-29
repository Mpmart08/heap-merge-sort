public class Heap {

    private int size = 0;
    private Node root = null;

    public int heapRemove() throws Exception {

        if (size == 0) {

            throw new Exception("The heap is empty.");

        } else {

            int removedValue = root.getValue();
            Node last = getLastNode();
            root.setValue(last.getValue());
            deleteLastNode();

            // bubble down
            if (root != null) {
                Node node = root;
                Node left;
                Node right;
                int value;
                boolean outOfPlace = true;
                while (outOfPlace) {

                    left = node.getLeft();
                    right = node.getRight();
                    value = node.getValue();

                    if (left == null
                        || (value <= left.getValue() && (right == null || value <= right.getValue()))) {

                        outOfPlace = false;

                    } else {

                        if (right == null
                            || (left != null && left.getValue() <= right.getValue())) {

                            swap(node, left);
                            node = left;

                        } else {

                            swap(node, right);
                            node = right;
                        }
                    }
                }
            }

            size--;
            return removedValue;
        }
    }

    public void heapInsert(int insertedValue) {

        size++;
        Node node = new Node(insertedValue);
        Node parent = getLastNode();

        if (parent == null) {

            root = node;

        } else {

            node.setParent(parent);
            if (parent.getLeft() == null) {
                parent.setLeft(node);
            } else {
                parent.setRight(node);
            }

            // bubble up
            boolean outOfPlace = true;
            while (outOfPlace) {

                parent = node.getParent();
                if (parent != null && node.getValue() < parent.getValue()) {
                    swap(node, parent);
                    node = parent;
                } else {
                    outOfPlace = false;
                }
            }
        }
    }

    public void printHeap() {

        int mask = 1;
        for (int i = 1; i <= size; i++) {
            System.out.print(getNode(i).getValue());
            if (i < size) {
                if (mask == i) {
                    System.out.print("\n");
                    mask = (mask << 1) + 1;
                } else {
                    System.out.print(" ");
                }
            }
        }
    }

    private void deleteLastNode() {

        Node last = getLastNode();
        Node parent = last.getParent();

        if (parent == null) {
            root = null;
        } else if (parent.getRight() == null) {
            parent.setLeft(null);
        } else {
            parent.setRight(null);
        }
    }

    private Node getNode(int position) {

        Node node = root;

        for (int i = Integer.highestOneBit(position) >> 1; i > 0; i >>= 1) {

            // left
            if ((i & position) == 0) {

                Node left = node.getLeft();
                if (left == null) {
                    break;
                } else {
                    node = left;
                }

            // right
            } else {

                Node right = node.getRight();
                if (right == null) {
                    break;
                } else {
                    node = right;
                }
            }
        }

        return node;
    }

    private Node getLastNode() {

        return getNode(size);
    }

    private void swap(Node first, Node second) {

        int temp = first.getValue();
        first.setValue(second.getValue());
        second.setValue(temp);
    }

}