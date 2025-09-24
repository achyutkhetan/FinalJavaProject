package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;

public class StudentService {
    private final DataStore ds = DataStore.getInstance();
    public Student create(String regNo, String name, String email){
        return ds.addStudent(regNo, name, email);
    }
    public Collection<Student> list(){ return ds.listStudents(); }
    public Optional<Student> byId(String id){ return ds.findStudentById(id); }
}
