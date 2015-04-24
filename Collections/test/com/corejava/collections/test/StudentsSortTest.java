package com.corejava.collections.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.corejava.collections.Student;
import com.corejava.collections.StudentsSort;

public class StudentsSortTest {

    @Test
    public void test() {
        Student s1 = new Student("Zahari", 6);
        Student s2 = new Student("Borislav", 4);
        Student s3 = new Student("Anna", 3);
        Student s4 = new Student("Anna", 4);
        Student s5 = new Student("Konstantin", 6);
        Student s6 = new Student("Blagovesta", 4);
        Student s7 = new Student("Sonia", 6);
        Student s8 = new Student("Vasil", 5);

        List<Student> unsorted = Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8);
        List<Student> sorted = Arrays.asList(s3, s4, s6, s2, s8, s5, s7, s1);
        StudentsSort.sort(unsorted);
        assertEquals(sorted, unsorted);

    }
}
