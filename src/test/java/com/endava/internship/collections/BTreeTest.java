package com.endava.internship.collections;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BTreeTest {

    private Student student1;
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
        student3 = new Student("Rubin", LocalDate.of(1999, 12, 8), "student");
        student4 = new Student("Aubin", LocalDate.of(2000, 12, 8), "student");
        student5 = new Student("Catalin", LocalDate.of(2000, 12, 9), "student");
        student6 = new Student("BBB", LocalDate.of(2000, 12, 8), "student");
        student7 = new Student("AAA", LocalDate.of(2000, 12, 8), "student");
        student8 = new Student("WWW", LocalDate.of(1999, 12, 8), "student");
    }

    @Test
    public void whenAddAnElementInTreeSizeShouldBeMoreThanZero() {
        tree.addNode(student1);

        assertEquals(1,tree.getSize());
    }

    @Test
    public void whenAddExistentElementSizeNotShouldChange() {
        tree.addNode(student1);
        tree.addNode(student1);
        assertEquals(1,tree.getSize());
    }

    @Test
    public void whenDeleteAnExistentElementInOurSetMethodShouldReturnTrue() {
        tree.addNode(student1);
        tree.addNode(student3);
        tree.addNode(student4);
        tree.addNode(student5);
        assertTrue(tree.delete(student4));
        assertTrue(tree.delete(student3));
        assertTrue(tree.delete(student1));
        assertEquals(1,tree.getSize());
    }

    @Test
    public void whenDeleteAnElementThanNotExistInOurSetMethodShouldReturnFalse() {
        tree.addNode(student1);
        assertFalse(tree.delete(student5));
        assertEquals(1,tree.getSize());
    }

    @Test
    public void whenHaveTheRightAndLeftNodeAndWeWishDeleteThatNode() {
        tree.addNode(student6);
        tree.addNode(student7);
        tree.addNode(student8);
        assertTrue(tree.delete(student6));
        assertEquals(2,tree.getSize());
    }

    @Test
    public void whenHaveOnlyOneElementInOurSetAndUseMethodDelete() {
        tree.addNode(student1);
        assertTrue(tree.delete(student1));
        assertEquals(0,tree.getSize());
    }

    @Test
    public void whenTransformOurTreeInArrayWeShouldHaveSameResultWithTheSortedArray() {
        Object[] arr = {student4,student5,student3};
        tree.addNode(student5);
        tree.addNode(student3);
        tree.addNode(student4);
        Object[] arrSet = tree.transform();
        assertArrayEquals(arr,arrSet);
    }

    @Test
    public void whenSearchAnElementAndItExistSearchMethodeShouldReturnTrue() {
        tree.addNode(student5);
        tree.addNode(student3);
        assertTrue(tree.searchElement(tree.getHead(),student3));
    }

    @Test
    public void whenTraversInOrderOurTreeWe_expectedAnStringWithOurElements() {
        tree.addNode(student1);
        tree.addNode(student3);
        assertEquals("Student{name='Rubin', dateOfBirth=1999-12-08, details='student'}\n" +
                "Student{name='Rubin', dateOfBirth=2000-12-08, details='student'}\n",tree.traverseInOrder(tree.getHead()));
    }
}
