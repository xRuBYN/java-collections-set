package com.endava.internship.collections;

public class Node {
    private Node left;
    private Node right;
    private Student value;
    public Node(Student value) {
        this.left = null;
        this.right = null;
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Student getValue() {
        return value;
    }

    public void setValue(Student value) {
        this.value = value;
    }
}
