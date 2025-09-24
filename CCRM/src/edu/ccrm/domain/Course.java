package edu.ccrm.domain;

import java.util.Objects;

public class Course {
    private final CourseCode code;
    private String title;
    private final int credits;
    private String instructor;
    private Semester semester;
    private String department;

    private Course(Builder b){
        this.code = new CourseCode(b.code);
        this.title = b.title;
        this.credits = b.credits;
        this.instructor = b.instructor;
        this.semester = b.semester;
        this.department = b.department;
    }
    public String getCode(){ return code.get(); }
    public String getTitle(){ return title; }
    public int getCredits(){ return credits; }
    public String getInstructor(){ return instructor; }
    public Semester getSemester(){ return semester; }
    public String getDepartment(){ return department; }

    @Override public String toString(){
        return String.format("%s - %s (%dcr) [%s] - %s", getCode(), title, credits, department, instructor);
    }

    public static class Builder {
        private String code;
        private String title;
        private int credits;
        private String instructor = "TBA";
        private Semester semester = Semester.SPRING;
        private String department = "GEN";
        public Builder code(String c){ this.code = c; return this; }
        public Builder title(String t){ this.title = t; return this; }
        public Builder credits(int cr){ this.credits = cr; return this; }
        public Builder instructor(String i){ this.instructor = i; return this; }
        public Builder semester(Semester s){ this.semester = s; return this; }
        public Builder department(String d){ this.department = d; return this; }
        public Course build(){
            Objects.requireNonNull(code);
            return new Course(this);
        }
    }
}
