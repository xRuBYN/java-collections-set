package com.endava.internship.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.function.Consumer;

public class StudentSet implements Set<Student> {
    private BTree tree;

    public StudentSet() {
        tree = new BTree();
    }

    @Override
    public int size() {
        //TODO
        return tree.getSize();
    }

    @Override
    public boolean isEmpty() {
        //TODO
        if(tree.head == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        //TODO
        if(o instanceof Student) {
            return tree.searchElement(tree.head,(Student)o);
        }
        return false;
    }

    @Override
    public Iterator<Student> iterator() {
        //TODO
        return null;
    }

    @Override
    public Object[] toArray() {
        //TODO
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        //TODO
        return null;
    }

    @Override
    public boolean add(Student student) {
        //TODO
        return tree.addNode(student);
    }

    @Override
    public boolean remove(Object o) {
        //TODO
        return false;
    }

    @Override
    public void clear() {
        //TODO
        tree.head = null;
    }

    @Override
    public boolean addAll(Collection<? extends Student> collection) {
        //TODO
        return false;
    }

    @Override
    public String toString() {
        tree.traverseInOrder(tree.head);
        return " ";
    }


    @Override
    public boolean containsAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }


}

class Node {
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

class BTree {
    Node head;
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


    public boolean _insert(Student value) {
        if(head == null) {
            head = new Node(value);
            return true;
        } else if (head.value.compareTo(value) < 0) {
            head.right = new Node(value);
            return true;
        } else if (head.value.compareTo(value) > 0) {
            head.left = new Node(value);
            return true;
        }
        return false;
    }

    public boolean addNode(Student student) {
        head = insert(head, student);
        if(size > preSize) {
            preSize = size;
            return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }
}