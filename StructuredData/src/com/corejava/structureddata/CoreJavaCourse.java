package com.corejava.structureddata;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CoreJavaCourse {
    
	
    List<Student> enrolledStudents;
    int courseId;
    String courseName;
    
    @XmlElementWrapper(name = "enrolledStudents")
    @XmlElement(name = "student")
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }


    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public int getCourseId() {
        return courseId;
    }


    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @XmlElement
    public String getCourseName() {
        return courseName;
    }


    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString(){
        String result = "The enrolled students are:" + System.lineSeparator();
        List<Student> javaStudents = getEnrolledStudents();
        for (Student student : javaStudents) {
          result += student.getStudentId() + " " + student.getName() + System.lineSeparator();
        }
        return result + "courseId: " + getCourseId() + "\ncourseName: " + getCourseName() + System.lineSeparator(); 
    }
}
