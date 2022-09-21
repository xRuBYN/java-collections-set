package com.endava.internship.collections;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

public class BTreeTest {

    private Student student1;
    private Student student2;
    private Student student3;
    private Student student4;
    private Student student5;
    private Student student6;
    private Student student7;
    private Student student8;
    private BTree tree;
    @BeforeEach
    void setUp() {
        tree = new BTree();
        student1 = new Student("Rubin", LocalDate.of(2000, 12, 8), "student");
        student2 = new Student("Rubin", LocalDate.of(2000, 12, 8), "student");
        student3 = new Student("Rubin", LocalDate.of(1999, 12, 8), "student");
        student4 = new Student("Aubin", LocalDate.of(2000, 12, 8), "student");
        student5 = new Student("Catalin", LocalDate.of(2000, 12, 9), "student");
        student6 = new Student("BBB", LocalDate.of(2000, 12, 8), "student");
        student7 = new Student("AAA", LocalDate.of(2000, 12, 8), "student");
        student8 = new Student("WWW", LocalDate.of(1999, 12, 8), "student");
    }

    @Test
    public void whenWeAddAnElementInTreeSizeShouldBeMoreThanZero() {
        tree.addNode(student1);

        assertEquals(1,tree.getSize());
    }

    @Test
    public void whenWeAddExistentElementSizeNotShouldChange() {
        tree.addNode(student1);
        tree.addNode(student1);
        assertEquals(1,tree.getSize());
    }

    @Test
    public void whenWeDeleteAnExistentElementInOurSetMethodShouldReturnTrue() {
        tree.addNode(student1);
        tree.addNode(student3);
        tree.addNode(student4);
        tree.addNode(student5);
        assertTrue(tree.delete(student4));
        assertTrue(tree.delete(student3));
        assertTrue(tree.delete(student1));
    }

    @Test
    public void whenWeDeleteAnElementThanNotExistInOurSetMethodShouldReturnFalse() {
        tree.addNode(student1);
        assertFalse(tree.delete(student5));
    }

    @Test
    public void whenWeHaveTheRightAndLeftNodeAndWeWishDeleteThatNode() {
        tree.addNode(student6);
        tree.addNode(student7);
        tree.addNode(student8);
        assertTrue(tree.delete(student6));
    }

    @Test
    public void whenWeHaveOnlyOneElementInOurSetAndUseMethodDelete() {
        tree.addNode(student1);
        assertTrue(tree.delete(student1));
    }

    @Test
    public void whenWeTransformOurTreeInArrayWeShouldHaveSameResultWithTheSortedArray() {
        Object[] arr = {student4,student5,student3};
        tree.addNode(student5);
        tree.addNode(student3);
        tree.addNode(student4);
        Object[] arrSet = tree.transform();
        assertArrayEquals(arr,arrSet);
    }

    @Test
    public void whenWeSearchAnElementAndItExistSearchMethodeShouldReturnTrue() {
        tree.addNode(student5);
        tree.addNode(student3);
        assertTrue(tree.searchElement(tree.getHead(),student3));
    }

    @Test
    public void whenWeTraversInOrderOurTreeWe_expectedAnStringWithOurElements() {
        tree.addNode(student1);
        tree.addNode(student3);
        assertEquals("Student{name='Rubin', dateOfBirth=1999-12-08, details='student'}\n" +
                "Student{name='Rubin', dateOfBirth=2000-12-08, details='student'}\n",tree.traverseInOrder(tree.getHead()));
    }
}
