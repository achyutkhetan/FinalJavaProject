package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.service.*;
import edu.ccrm.io.*;

import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();
    private static final ImportExportService ioService = new ImportExportService();
    private static final BackupService backupService = new BackupService();

    public static void main(String[] args) {
        System.out.println("--- CCRM Console ---");
        AppConfig cfg = AppConfig.getInstance();
        menuLoop:
        while (true) {
            System.out.println("1) Manage Students  2) Manage Courses  3) Enroll  4) Transcript  5) Export & Backup  0) Exit");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> manageStudents();
                case "2" -> manageCourses();
                case "3" -> doEnroll();
                case "4" -> showTranscript();
                case "5" -> exportAndBackup();
                case "0" -> { System.out.println("Bye"); break menuLoop; }
                default -> System.out.println("Invalid");
            }
        }
    }

    private static void manageStudents(){
        System.out.println("Add student - enter regNo,name,email:");
        String line = sc.nextLine();
        String[] p = line.split(",");
        if (p.length<3) { System.out.println("bad"); return; }
        Student s = studentService.create(p[0].trim(), p[1].trim(), p[2].trim());
        System.out.println("Created: " + s.profile());
    }

    private static void manageCourses(){
        System.out.println("Add course - enter code,title,credits,dept:");
        String line = sc.nextLine();
        String[] p = line.split(",");
        if (p.length<4){ System.out.println("bad"); return; }
        Course c = courseService.create(p[0].trim(), p[1].trim(), Integer.parseInt(p[2].trim()), "TBD", Semester.SPRING, p[3].trim());
        System.out.println("Added: " + c);
    }

    private static void doEnroll(){
        System.out.println("Enter studentId and courseCode (comma):");
        String[] p = sc.nextLine().split(",");
        if (p.length<2) return;
        String sid = p[0].trim(), code = p[1].trim();
        Student s = studentService.byId(sid).orElse(null);
        Course c = courseService.byCode(code).orElse(null);
        if (s==null || c==null){ System.out.println("Not found"); return; }
        try {
            enrollmentService.enroll(s, c);
            System.out.println("Enrolled");
        } catch (RuntimeException ex){
            System.out.println("Err: " + ex.getMessage());
        }
    }

    private static void showTranscript(){
        System.out.println("Enter studentId:");
        String id = sc.nextLine().trim();
        studentService.byId(id).ifPresentOrElse(s -> System.out.println(s.transcript()), ()-> System.out.println("Not found"));
    }

    private static void exportAndBackup(){
        try {
            java.nio.file.Path out = Paths.get("ccrm_export_students.csv");
            ioService.exportStudents(out);
            java.nio.file.Path backup = backupService.createBackup(out);
            long size = backupService.recursiveSize(backup);
            System.out.println("Exported and backed up to: " + backup + " size=" + size + " bytes");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
