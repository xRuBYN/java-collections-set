package com.endava.internship.collections;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The class that defines the element that will be contained by your collection
 */
public class
Student implements Comparable<Student>//TODO consider implementing any interfaces necessary for your collection
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

    @Override
    public int compareTo(Student o) {
        if (0 == this.name.compareTo(o.name)) {
            return Integer.compare(this.dateOfBirth.compareTo(o.dateOfBirth), 0);
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
