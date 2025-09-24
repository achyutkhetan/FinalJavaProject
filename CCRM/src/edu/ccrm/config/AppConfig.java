package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public final class AppConfig {
    private static AppConfig instance;
    private final Path dataDir;
    private final DateTimeFormatter backupFormatter;
    private AppConfig() {
        this.dataDir = Paths.get(System.getProperty("user.dir"), "ccrm_data");
        this.backupFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    }
    public static synchronized AppConfig getInstance() {
        if (instance == null) instance = new AppConfig();
        return instance;
    }
    public Path getDataDir() { return dataDir; }
    public DateTimeFormatter getBackupFormatter() { return backupFormatter; }
}
