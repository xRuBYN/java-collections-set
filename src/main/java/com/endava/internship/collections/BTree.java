package com.endava.internship.collections;

public class BTree {
    Node head;
    Object[] arr;
    private int size;
    private int preSize;

    private Node insert(Node root, Student value) {
        if (root == null) {
            size++;
            return new Node(value);

        } else if (root.value.compareTo(value) < 0) {
            root.right = insert(root.right, value);

        } else if (root.value.compareTo(value) > 0) {

            root.left = insert(root.left, value);
        }
        return root;

    }

    public void traverseInOrder(Node tree) {
        if (tree != null) {
            traverseInOrder(tree.left);
            System.out.println(tree.value);
            traverseInOrder(tree.right);
        }
    }

    public boolean searchElement(Node current, Student value) {
        if (current == null) {
            return false;
        }
        if (current.value.compareTo(value) == 0) {
            return true;
        }
        return (current.value.compareTo(value) > 0 ? true : false)
                ? searchElement(current.left, value)
                : searchElement(current.right, value);
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
        return 1 + getSizeRec(root.left) + getSizeRec(root.right);
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

        while (current != null && current.value.compareTo(value) != 0) {
            parent = current;

            if (current.value.compareTo(value) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return root;
        }

        if (current.left == null && current.right == null) {
            if (current != root) {
                if (parent.left == current) {
                    parent.left = null;

                } else {
                    parent.right = null;

                }
            } else {
                root = null;

            }
        } else if (root.left != null && root.right != null) {
            Node successor = getMinimumValue(current.right);
            Student student = successor.value;
            deleteRec(root, successor.value);
            current.value = student;
        } else {
            Node child = (current.left != null) ? current.left : current.right;

            if (current != root) {
                if (current == parent.left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } else {
                root = child;
            }
        }
        return root;
    }

    private Node getMinimumValue(Node current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private int transformTreeInArray(Node current, Object[] arr, int i) {
        if (current == null) {
            return i;
        }
        if (current.left != null) {
            i = transformTreeInArray(current.left, arr, i);
        }
        arr[i] = current.value;
        i++;
        if (current.right != null) {
            i = transformTreeInArray(current.right, arr, i);
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
