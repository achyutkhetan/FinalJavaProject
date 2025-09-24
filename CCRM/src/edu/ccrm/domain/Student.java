package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.*;

public class Student extends Person {
    public enum Status { ACTIVE, INACTIVE }
    private String regNo;
    private String email;
    private Status status;
    private final Map<String, Enrollment> enrollments = new HashMap<>();
    public Student(String id, String regNo, String fullName, String email){
        super(id, fullName);
        this.regNo = regNo;
        this.email = email;
        this.status = Status.ACTIVE;
    }
    public String getRegNo(){ return regNo; }
    public String getEmail(){ return email; }
    public Status getStatus(){ return status; }
    public void deactivate(){ this.status = Status.INACTIVE; }
    public Collection<Enrollment> getEnrollments(){ return enrollments.values(); }
    public void addEnrollment(Enrollment e){ enrollments.put(e.getCourse().getCode(), e); }
    public void removeEnrollment(String courseCode){ enrollments.remove(courseCode); }
    @Override
    public String profile(){
        return String.format("Student[id=%s, reg=%s, name=%s, email=%s, status=%s]", id, regNo, fullName, email, status);
    }
    public String transcript(){
        StringBuilder sb = new StringBuilder();
        sb.append(profile()).append("\n");
        sb.append("Enrollments:\n");
        enrollments.values().forEach(e -> sb.append("  ").append(e).append("\n"));
        double gpa = Enrollment.calculateGPA(enrollments.values());
        sb.append(String.format("GPA: %.2f", gpa));
        return sb.toString();
    }
}
