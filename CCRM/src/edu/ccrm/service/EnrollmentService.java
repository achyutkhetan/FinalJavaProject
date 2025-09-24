package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;

public class EnrollmentService {
    private final DataStore ds = DataStore.getInstance();
    private final int MAX_CREDITS = 18;
    public void enroll(Student s, Course c){
        // check duplicate
        if (s.getEnrollments().stream().anyMatch(e->e.getCourse().getCode().equals(c.getCode())))
            throw new DuplicateEnrollmentException("Already enrolled: " + c.getCode());
        int currentCredits = s.getEnrollments().stream().mapToInt(e->e.getCourse().getCredits()).sum();
        if (currentCredits + c.getCredits() > MAX_CREDITS)
            throw new MaxCreditLimitExceededException("Exceeds max credits: " + MAX_CREDITS);
        Enrollment en = new Enrollment(c);
        s.addEnrollment(en);
    }
    public void unenroll(Student s, String courseCode){
        s.removeEnrollment(courseCode);
    }
    public void assignGrade(Student s, String courseCode, Grade g){
        s.getEnrollments().stream().filter(e->e.getCourse().getCode().equals(courseCode)).findFirst()
            .ifPresent(e->e.assignGrade(g));
    }
}
