package com.corejava.collections;

public class Student implements Comparable<Student>{
    private String name;
    private int grade;
    
    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public int compareTo(Student other) {
        if (this.grade > other.grade) {
            return 1;
        }
        else if (this.grade < other.grade) {
            return -1;
        }
        return this.name.compareTo(other.name);
    }
    
    @Override
    public String toString() {
        return String.format("[%s, %d]", name, grade);
    }
   
}
