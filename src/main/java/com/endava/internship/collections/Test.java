package com.endava.internship.collections;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        TreeSet<Student> set = new TreeSet<>();
        Student student = new Student("Rubin",LocalDate.of(2000,12,8),"student");
        Student student1 = new Student("Rubin",LocalDate.of(1999,12,8),"student");
        Student student2 = new Student("Aubin",LocalDate.of(2000,12,8),"student");
        Student student3 = new Student("Rubin",LocalDate.of(2000,12,8),"student");
        set.add(student);
        set.add(student1);
        set.add(student2);
        set.add(student3);
        set.stream().forEach(System.out :: println);
        System.out.println();
        StudentSet set1 = new StudentSet();
        set1.add(student);
        set1.add(student1);
        set1.add(student2);
        set1.add(student3);
        set1.add(student1);
        System.out.println(set1);
        //set1.stream().forEach(System.out :: println);
        //traverseInOrder(set1.tree.head);
        //System.out.println(set1.contains(new Integer(23)));

        System.out.println(set1.contains(student1));

    }

}
