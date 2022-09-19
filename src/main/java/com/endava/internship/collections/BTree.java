package com.endava.internship.collections;

public class BTree {
    private Node head;
    private Object[] arr;
    private int size;
    private int preSize;

    private StringBuilder str;

    public BTree(){
        str = new StringBuilder();
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    private Node insert(Node root, Student value) {
        if (root == null) {
            size++;
            return new Node(value);
        } else if (root.getValue().compareTo(value) < 0) {
            root.setRight(insert(root.getRight(), value));
        } else if (root.getValue().compareTo(value) > 0) {
            root.setLeft(insert(root.getLeft(), value));
        }
        return root;
    }

    public StringBuilder traverseInOrder(Node tree) {
        if (tree != null) {
            traverseInOrder(tree.getLeft());
            str.append(tree.getValue()).append("\n");
            traverseInOrder(tree.getRight());
        }
        return str;
    }

    public boolean searchElement(Node current, Student value) {
        if (current == null) {
            return false;
        }
        if (current.getValue().compareTo(value) == 0) {
            return true;
        }
        return (current.getValue().compareTo(value) > 0)
                ? searchElement(current.getLeft(), value)
                : searchElement(current.getRight(), value);
    }

    public boolean addNode(Student student) {
        head = insert(head, student);
        if (size > preSize) {
            preSize = size;
            return true;
        }
        return false;
    }

    private int getSizeRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + getSizeRec(root.getLeft()) + getSizeRec(root.getRight());
    }

    public int getSize() {
        return getSizeRec(head);
    }

    public boolean delete(Student o) {
        head = deleteRec(head, o);
        preSize = size;
        size = getSizeRec(head);
        if (size < preSize) {
            preSize = size;
            return true;
        }
        return false;
    }

    private Node deleteRec(Node root, Student value) {
        Node parent = null;
        Node current = root;
        while (current != null && current.getValue().compareTo(value) != 0) {
            parent = current;
            if (current.getValue().compareTo(value) > 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        if (current == null) {
            return root;
        }
        if (current.getLeft() == null && current.getRight() == null) {
            if (current != root) {
                if (parent.getLeft() == current) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            } else {
                root = null;
            }
        } else if (root.getLeft() != null && root.getRight() != null) {
            Node successor = getMinimumValue(current.getRight());
            Student student = successor.getValue();
            deleteRec(root, successor.getValue());
            current.setValue(student);
        } else {
            Node child = (current.getLeft() != null) ? current.getLeft() : current.getRight();
            if (current != root) {
                if (current == parent.getLeft()) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
            } else {
                root = child;
            }
        }
        return root;
    }

    private Node getMinimumValue(Node current) {
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    private int transformTreeInArray(Node current, Object[] arr, int i) {
        if (current == null) {
            return i;
        }
        if (current.getLeft() != null) {
            i = transformTreeInArray(current.getLeft(), arr, i);
        }
        arr[i] = current.getValue();
        i++;
        if (current.getRight() != null) {
            i = transformTreeInArray(current.getRight(), arr, i);
        }
        return i;
    }

    public Object[] transform() {
        size = getSize();
        arr = new Object[size];
        int i = 0;
        transformTreeInArray(head, arr, i);
        return arr;
    }
}
