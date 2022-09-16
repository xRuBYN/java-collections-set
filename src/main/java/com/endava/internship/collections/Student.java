package com.endava.internship.collections;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * The class that defines the element that will be contained by your collection
 */
public class Student implements Comparable<Student>//TODO consider implementing any interfaces necessary for your collection
{
    private String name;
    private LocalDate dateOfBirth;
    private String details;

    public Student(String name, LocalDate dateOfBirth, String details) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDetails() {
        return details;
    }

    /*
    TODO consider overriding any methods for this object to function properly within a collection:
        1. A student is considered unique by a combination of their name and dateOfBirth
        2. Student names are sorted alphabetically, if two students have the same name, then the older one is
        placed before the younger student in an ordered student list.
    */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getName().equals(student.getName()) && getDateOfBirth().equals(student.getDateOfBirth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDateOfBirth());
    }

    //if this element equal with compare element return 0
    //if this element less than compare element return negative value
    //if this element more than compare element return positive value
    @Override
    public int compareTo(Student o) {
        if (0 == this.name.compareTo(o.name)) {
            if (this.dateOfBirth.compareTo(o.dateOfBirth) < 0) {
                return -1;
            } else if (this.dateOfBirth.compareTo(o.dateOfBirth) > 0) {
                return 1;
            } else
                return 0;
        } else if (0 > this.name.compareTo(o.name)) {
            return -1;
        } else
            return 1;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", details='" + details + '\'' +
                '}';
    }
}
