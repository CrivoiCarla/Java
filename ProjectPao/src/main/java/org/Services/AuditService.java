package org.Services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private static AuditService auditService;
    FileWriter writer;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private AuditService() {
        try {
            this.writer = new FileWriter("src\\main\\java\\org\\files\\audit.csv");
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static AuditService getInstance() {
        if (auditService == null) {
            auditService = new AuditService();
        }

        return auditService;
    }

    public void logAction(String action) throws IOException {
        this.writer.append(action);
        this.writer.append(",");
        this.writer.append(this.formatter.format(LocalDateTime.now()));
        this.writer.append("\n");
        this.writer.flush();
    }
}
