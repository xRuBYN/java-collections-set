package com.endava.internship.collections;

import java.util.*;
import java.util.function.Consumer;

public class StudentSet implements Set<Student> {
    private BTree tree;

    public StudentSet() {
        tree = new BTree();
    }


    @Override
    public int size() {
        return tree.getSize();
    }

    @Override
    public boolean isEmpty() {
        if (tree.head == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (o instanceof Student) {
            return tree.searchElement(tree.head, (Student) o);
        }
        return false;
    }

    @Override
    public Iterator<Student> iterator() {
       return new Iterator<Student>() {
            private Stack<Node> stack;
            private Student last;

            {
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
        };

    }

    @Override
    public Object[] toArray() {
        return tree.transform();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        List<T> list = new ArrayList<>();
        for (Student student : this) {
            list.add((T) student);
        }
        return list.toArray(ts);
    }

    @Override
    public boolean add(Student student) {
        return tree.addNode(student);
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Student) {
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
        for (Object o : collection) {
            boolean b = tree.addNode((Student) o);
            if (b) {
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
        for (Object student : collection) {
            if (!contains(student)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean retain = false;
        for (Student student : this) {
            if (!collection.contains(student)) {
                remove(student);
                retain = true;
            }
        }
        return retain;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object student : collection) {
            if (!remove(student)) {
                return false;
            }
        }
        return true;
    }


}

