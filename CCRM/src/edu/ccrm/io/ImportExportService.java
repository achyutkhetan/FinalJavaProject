package edu.ccrm.io;

import edu.ccrm.service.DataStore;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.service.DataStore;
import java.nio.file.*;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.*;

import edu.ccrm.service.DataStore;

public class ImportExportService {
    private final DataStore ds = DataStore.getInstance();
    public void importStudents(Path csv) throws IOException {
        try (Stream<String> lines = Files.lines(csv)){
            lines.skip(1).forEach(l -> {
                String[] parts = l.split(",");
                if (parts.length>=4){
                    ds.addStudent(parts[0], parts[1], parts[2]);
                }
            });
        }
    }
    public void exportStudents(Path out) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id,regNo,fullName,email");
        ds.listStudents().forEach(s -> lines.add(String.join(",", s.getId(), s.getRegNo(), s.getFullName(), s.getEmail())));
        Files.createDirectories(out.getParent());
        Files.write(out, lines);
    }
}
