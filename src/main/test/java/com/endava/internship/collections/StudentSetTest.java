package com.endava.internship.collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentSetTest {

    private StudentSet set;
    private Student student1;
    private Student student2;
    private Student student3;
    private Student student4;
    private Student student5;
    private Student student6;
    private Student student7;
    @BeforeEach
    void setUp() {
        set = new StudentSet();
        student1 = new Student("Rubin", LocalDate.of(2000, 12, 8), "student");
        student2 = new Student("Rubin", LocalDate.of(2000, 12, 8), "student");
        student3 = new Student("Rubin", LocalDate.of(1999, 12, 8), "student");
        student4 = new Student("Aubin", LocalDate.of(2000, 12, 8), "student");
        student5 = new Student("Catalin", LocalDate.of(2000, 12, 9), "student");
        student6 = new Student("Watalin", LocalDate.of(2000, 12, 9), "student");
        student7 = new Student("Sorin", LocalDate.of(2000, 12, 9), "student");

    }

    @Test
    public void newlySetShouldBeNull() {
        assertTrue(set.isEmpty());
    }

    @Test
    public void emptySetShouldBeHaveZeroSize() {
        assertEquals(0,set.size());
    }

    @Test
    public void whenWeAdd_newElementTheSetShouldContainsElement() {
        set.add(student1);
        assertFalse(set.isEmpty());
    }

    @Test
    public void whenWeAddExistentElement_addShouldReturnFalse() {
        set.add(student1);
        assertFalse(set.add(student1));
    }

    @Test
    public void whenWeUseMethodToArrayWeCanHavaAnArrayOfObjects() {
        Object[] arr = {student4,student5,student3,student6};
        set.add(student6);
        set.add(student5);
        set.add(student3);
        set.add(student4);
        Object[] arrSet = set.toArray();
        assertArrayEquals(arr,arrSet);
    }

    @Test
    public void whenWeRemoveAnObjectWeShouldHaveTrueIfElementExistInSet() {
        set.add(student1);
        assertTrue(set.remove(student1));
    }

    @Test
    public void whenWeRemoveAnObjectWeShouldHaveFalseIfElementDoNotExistOrIsWrong() {
        set.add(student2);
        assertFalse(set.remove(student4));
        assertFalse(set.remove(new Integer(5)));
    }

    @Test
    public void whenTheSetContainsElementShouldReturnTrue() {
        set.add(student1);
        assertTrue(set.contains(student1));
    }

    @Test
    public void whenTheSetDoNotContainsElementOrWeSearchWrongElementItShouldReturnFalse() {
        set.add(student1);
        assertFalse(set.contains(student4));
        assertFalse(set.contains("Rubin"));
    }

    @Test
    public void whenWeCallClearStudentSizeShouldBeZero() {
        set.add(student1);
        set.add(student5);
        set.add(student3);
        set.add(student4);
        set.clear();
        assertEquals(0,set.size());
    }

    @Test
    public void whenWeCallRemoveAllElementIfTheyContainsMethodShouldReturnTrue() {
        set.add(student1);
        set.add(student5);
        set.add(student3);
        set.add(student4);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student5);
        list.add(student3);
        list.add(student4);
        assertTrue(set.removeAll(list));
    }

    @Test
    public void whenElementsOfCollectionNotInSetRemoveAllShouldReturnFalse() {
        set.add(student1);
        set.add(student5);
        set.add(student3);
        set.add(student4);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student5);
        list.add(student3);
        list.add(student7);
        assertFalse(set.removeAll(list));
    }

    @Test
    public void whenWeCallContainsAllElementIfTheyContainsMethodShouldReturnTrue() {
        set.add(student1);
        set.add(student5);
        set.add(student3);
        set.add(student4);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student5);
        list.add(student3);
        list.add(student4);
        assertTrue(set.containsAll(list));
    }

    @Test
    public void whenElementsOfCollectionNotInSetContainAllShouldReturnFalse() {
        set.add(student1);
        set.add(student5);
        set.add(student3);
        set.add(student4);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student5);
        list.add(student3);
        list.add(student7);
        assertFalse(set.containsAll(list));
    }

    @Test
    public void whenWeUseRetainAllWeShouldHaveIntersectionOfTheseCollection() {
        set.add(student1);
        set.add(student5);
        set.add(student3);
        set.add(student4);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student5);
        list.add(student3);
        list.add(student7);
        assertTrue(set.retainAll(list));
    }

    @Test
    public void whenWeUseAddAllOurSetShouldReturnTrue() {
        set.add(student1);
        set.add(student5);
        set.add(student3);
        set.add(student4);
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student5);
        list.add(student3);
        list.add(student7);
        assertTrue(set.addAll(list));
    }
}
