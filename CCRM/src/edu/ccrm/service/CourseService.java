package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;

public class CourseService {
    private final DataStore ds = DataStore.getInstance();
    public Course create(String code, String title, int credits, String instructor, Semester sem, String dept){
        Course c = new Course.Builder()
                .code(code).title(title).credits(credits).instructor(instructor).semester(sem).department(dept).build();
        return ds.addCourse(c);
    }
    public Collection<Course> list(){ return ds.listCourses(); }
    public Optional<Course> byCode(String code){ return ds.findCourseByCode(code); }
}
