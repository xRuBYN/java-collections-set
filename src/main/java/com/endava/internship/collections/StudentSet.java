package com.endava.internship.collections;

import java.util.NoSuchElementException;
import java.util.Collection;
import java.util.Stack;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
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
        return tree.getHead() == null;
    }

    @Override
    public boolean contains(Object o) {
        if (o instanceof Student) {
            return tree.searchElement(tree.getHead(), (Student) o);
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
                Node current = tree.getHead();
                while (current != null) {
                    stack.push(current);
                    current = current.getLeft();
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
                last = stack.peek().getValue();
                Node tmp = stack.peek().getRight();
                stack.pop();

                while (tmp != null) {
                    stack.push(tmp);
                    tmp = tmp.getLeft();
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
        tree.setHead(null);
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
        return tree.traverseInOrder(tree.getHead());
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
            if (!this.remove(student)) {
                return false;
            }
        }
        return true;
    }
}

