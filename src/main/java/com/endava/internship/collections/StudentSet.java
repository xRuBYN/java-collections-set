package com.endava.internship.collections;

import java.util.*;
import java.util.function.Consumer;

public class StudentSet implements Set<Student> {
    private BTree tree;

    public StudentSet() {
        tree = new BTree();
    }


    private class StudentItr implements Iterator<Student>
    {
        private Stack<Node> stack;
        private Student last;
        public StudentItr (){
            stack = new Stack<>();
            Node current = tree.head;

            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Student next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            last = stack.peek().value;
            Node tmp = stack.peek().right;
            stack.pop();

            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }
            return last;
        }


        @Override
        public void forEachRemaining(Consumer<? super Student> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    @Override
    public int size() {
        return tree.getSize();
    }

    @Override
    public boolean isEmpty() {
        if(tree.head == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if(o instanceof Student) {
            return tree.searchElement(tree.head,(Student)o);
        }
        return false;
    }

    @Override
    public Iterator<Student> iterator() {
        return new StudentItr();
    }

    @Override
    public Object[] toArray() {
        return tree.transform();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        List<T> list = new ArrayList<>();
        for (Student student : this) {
            list.add((T)student);
        }
        return list.toArray(ts);
    }

    @Override
    public boolean add(Student student) {
        return tree.addNode(student);
    }

    @Override
    public boolean remove(Object o) {
        if(o instanceof Student) {
            return tree.delete((Student) o);
        }
        return false;
    }

    @Override
    public void clear() {
        tree.head = null;
    }

    @Override
    public boolean addAll(Collection<? extends Student> collection) {
        boolean check = false;
        for(Object o : collection) {
            boolean b = tree.addNode((Student) o);
            if(b)
            {
                check = true;
            }
        }
        return check;
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
    Object[] arr;
    private int size;
    private int preSize;
    private Node insert(Node root, Student value) {
        if (root == null) {
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
        size = getSize();
        head = insert(head, student);
        if(size > preSize) {
            preSize = size;
            return true;
        }
        return false;
    }

    private int getSizeRec(Node root){
        if(root==null){
            return 0;
        }
        return 1 + getSizeRec(root.left) + getSizeRec(root.right);
    }
    public int getSize() {
        return getSizeRec(head);
    }

    public boolean delete(Student o) {
        head = deleteRec(head,o);
        return true;
    }
    private Node deleteRec(Node current, Student value) {
        Node parent = null;
        Node root = current;

        while(root != null && root.value.compareTo(value) != 0) {
            parent = root;

            if(root.value.compareTo(value) > 0) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        if(root == null) {
            return current;
        }

        if(root.left == null && root.right == null) {
            if(root != current) {
                if(parent.left == root) {
                    parent.left = null;

                } else {
                    parent.right = null;

                }
            }
            else {
                current = null;

            }
        }

        else if(current.left != null && current.right != null) {
            Node successor = getMinimumValue(root.right) ;
            Student student = successor.value;
            deleteRec(current,successor.value);
            root.value = student;
        }
        else
        {
            Node child = (root.left != null) ? root.left : root.right;

            if(root != current) {
                if(root == parent.left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
            else  {
                current = child;
            }
        }
        return current;
    }

    private Node getMinimumValue(Node current) {
        while(current.left != null) {
            current = current.left;
        }
        return current;
    }
    private int transformTreeInArray(Node current, Object[] arr, int i) {
        if(current == null) {
            return i;
        }
        if(current.left != null) {
            i = transformTreeInArray(current.left,arr,i);
        }
        arr[i] = current.value;
        i++;
        if(current.right != null) {
            i = transformTreeInArray(current.right,arr,i);
        }
        return i;
    }
    public Object[] transform() {
        size = getSize();
        arr = new Object[size];
        int i = 0;
        transformTreeInArray(head,arr,i);
        return arr;
    }


}
