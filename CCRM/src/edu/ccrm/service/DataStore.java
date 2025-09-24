package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DataStore {
    private static DataStore instance;
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    private final AtomicInteger idGen = new AtomicInteger(1000);
    private DataStore(){}
    public static synchronized DataStore getInstance(){
        if (instance==null) instance = new DataStore();
        return instance;
    }
    public Student addStudent(String regNo, String name, String email){
        String id = "S" + idGen.getAndIncrement();
        Student s = new Student(id, regNo, name, email);
        students.put(id, s);
        return s;
    }
    public Course addCourse(Course c){
        courses.put(c.getCode(), c);
        return c;
    }
    public Optional<Student> findStudentByReg(String regNo){
        return students.values().stream().filter(s->regNo.equals(s.getRegNo())).findFirst();
    }
    public Optional<Student> findStudentById(String id){ return Optional.ofNullable(students.get(id)); }
    public Optional<Course> findCourseByCode(String code){ return Optional.ofNullable(courses.get(code)); }
    public Collection<Student> listStudents(){ return students.values(); }
    public Collection<Course> listCourses(){ return courses.values(); }

    // Streams example: filter by department
    public List<Course> coursesByDepartment(String dept){
        return courses.values().stream().filter(c->c.getDepartment().equalsIgnoreCase(dept)).collect(Collectors.toList());
    }
}
