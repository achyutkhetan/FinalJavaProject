package edu.ccrm.io;

import edu.ccrm.config.AppConfig;

import java.nio.file.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class BackupService {
    private final Path base;
    private final DateTimeFormatter fmt;
    public BackupService(){
        AppConfig cfg = AppConfig.getInstance();
        this.base = cfg.getDataDir();
        this.fmt = cfg.getBackupFormatter();
    }
    public Path createBackup(Path... sources) throws IOException {
        String name = "backup_" + LocalDateTime.now().format(fmt);
        Path target = base.resolve(name);
        Files.createDirectories(target);
        for (Path s : sources){
            if (Files.exists(s)) {
                Path dest = target.resolve(s.getFileName());
                Files.copy(s, dest);
            }
        }
        return target;
    }
    public long recursiveSize(Path p) throws IOException {
        AtomicLong total = new AtomicLong(0);
        try (Stream<Path> walk = Files.walk(p)){
            walk.filter(Files::isRegularFile).forEach(fp -> {
                try { total.addAndGet(Files.size(fp)); } catch(Throwable t){}
            });
        }
        return total.get();
    }
}
