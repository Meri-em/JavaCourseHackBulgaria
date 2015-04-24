package com.corejava.structureddata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;



public class Main {
    private static final String ACOREJAVAXML = "aCoreJavaCourseMashalled.xml";
    private static final String ACOREJSON = "aCoreJavaCourseMashalled.json";
    
    public static void main(String[] args) {
        
        long start = System.currentTimeMillis();
       
        List<Student> coreJavaStudents =  new ArrayList<Student>();
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        student1.setStudentId(1129);
        student1.setName("Darina Miteva");
        student2.setStudentId(1160);
        student2.setName("Nikola Dimitrov");
        student3.setStudentId(1180);
        student3.setName("Miroslav Nenov");
        CoreJavaCourse aCoreJavaCourse = new CoreJavaCourse();
        coreJavaStudents.add(student1);
        coreJavaStudents.add(student2);
        coreJavaStudents.add(student3);
        aCoreJavaCourse.enrolledStudents = coreJavaStudents;
        aCoreJavaCourse.courseId = 6789;
        aCoreJavaCourse.courseName = "CoreJava";

        
        try {
            //XML
            JAXBContext context = JAXBContext.newInstance(CoreJavaCourse.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(aCoreJavaCourse, new File(ACOREJAVAXML));
            
            Unmarshaller um = context.createUnmarshaller();
            CoreJavaCourse umXMLCoreJava =(CoreJavaCourse) um.unmarshal(new FileReader(ACOREJAVAXML));
            System.out.println(umXMLCoreJava);
            
            //JSON
            MappedNamespaceConvention con = new MappedNamespaceConvention();
            XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, new FileWriter(ACOREJSON));
            m.marshal(aCoreJavaCourse, xmlStreamWriter);
            
            byte[] content = Files.readAllBytes(Paths.get(ACOREJSON));
            JSONObject obj = new JSONObject(new String(content));
            XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(obj, con);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CoreJavaCourse umJSONCoreJava = (CoreJavaCourse) unmarshaller.unmarshal(xmlStreamReader);
            System.out.println(umJSONCoreJava);
            
            
        } catch (JAXBException e) {
            
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        catch (JSONException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
        
    }
}
