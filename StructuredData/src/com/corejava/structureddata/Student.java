package com.corejava.structureddata;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student { 
    private int studentId;
    private String name; 
    
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
