package com.endava.internship.collections;

public class Node {
    //Node head;
    Node left;
    Node right;
    Student value;

    public Node() {
    }

    public Node(Student value) {
        this.left = null;
        this.right = null;
        this.value = value;
    }

}
