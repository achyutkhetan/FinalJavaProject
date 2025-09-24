package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.Collection;

public class Enrollment {
    private final Course course;
    private final LocalDateTime enrolledAt;
    private Grade grade; // nullable
    public Enrollment(Course course){
        this.course = course;
        this.enrolledAt = LocalDateTime.now();
    }
    public Course getCourse(){ return course; }
    public void assignGrade(Grade g){ this.grade = g; }
    public Grade getGrade(){ return grade; }
    @Override public String toString(){
        return String.format("%s | enrolled=%s | grade=%s", course.getCode(), enrolledAt.toLocalDate(), grade==null?"N/A":grade);
    }

    public static double calculateGPA(Collection<Enrollment> enrollments){
        if (enrollments == null || enrollments.isEmpty()) return 0.0;
        double totalPoints = 0;
        int totalCredits = 0;
        for (Enrollment e : enrollments){
            if (e.grade == null) continue;
            totalPoints += e.grade.getPoints() * e.course.getCredits();
            totalCredits += e.course.getCredits();
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }
}
