package com.endava.internship.collections;


//import com.sun.source.tree.BinaryTree;
//import com.sun.source.tree.Tree;
//import com.sun.tools.javac.tree.JCTree;

import java.time.LocalDate;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        TreeSet<Student> set = new TreeSet<>();
        Student student = new Student("Rubin", LocalDate.of(2000, 12, 8), "student");
        Student student1 = new Student("Rubin", LocalDate.of(1999, 12, 8), "student");
        Student student2 = new Student("Aubin", LocalDate.of(2000, 12, 8), "student");
        Student student3 = new Student("Rubin", LocalDate.of(2000, 12, 8), "student");
        Student student4 = new Student("Catalin", LocalDate.of(2000, 12, 9), "student");
        set.add(student);


//        set.stream().forEach(System.out :: println);
//        System.out.println();
        StudentSet set1 = new StudentSet();
        boolean add = set1.add(student); //true
        boolean add1 = set1.add(student1); //true
        boolean add2 = set1.add(student2);//true
        boolean add3 = set1.add(student3); //false
        boolean add4 = set1.add(student1); //false
        boolean add5 = set1.add(student4); //true
        boolean add6 = set1.remove(student1); //true
        boolean add7 = set1.remove(student4); //true
        boolean add99 = set1.remove(student1); //false
        boolean add98 = set1.remove(student1); //false
        boolean add8 = set1.add(student1); //true
        boolean add9 = set1.add(student4); //true
        System.out.println(set1);
        set1.removeAll(set);
        System.out.println(set1);

        //set1.stream().forEach(System.out :: println);
        //traverseInOrder(set1.tree.head);
        //System.out.println(set1.contains(new Integer(23)));
//        Student student4 = new Student("Catalin", LocalDate.of(2000,12,9),"student");
//        System.out.println(set1.contains(student4));
//
//        LinkedList<Integer> list;
//        set1.remove(student1);
//        for(Object s : set1.toArray()) {
//            System.out.println(s);
//        }
//        Junior jun = new Junior("Rub", LocalDate.of(2000,12,9),"student");
//        Junior jun1 = new Junior("Rubs", LocalDate.of(2000,12,9),"student");
//        Junior jun2 = new Junior("Rubw", LocalDate.of(2000,12,9),"student");
//        Junior jun3 = new Junior("Rubr", LocalDate.of(2000,12,9),"student");
//        if(jun instanceof Junior) {
//            System.out.println("daaaa");
//        }
//        Set<Junior> set2 = new TreeSet<>();
//        set2.add(jun);
//        set2.add(jun1);
//        set2.add(jun2);
//        set2.add(jun3);
//        set1.addAll(set2);
//        for(Object s : set2.toArray()) {
//            System.out.println(s);
//        }
        List<Student> list1 = new LinkedList<>();

        Set<Integer> temp = new TreeSet<>();
        Iterator<Student> iterator = list1.iterator();

    }

}
