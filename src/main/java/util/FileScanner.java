package util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import model.attendace.Attendance;
import model.attendace.Attendances;

public class FileScanner {
    private static final String ATTENDANCES_PATH = "src/main/resources/attendances.csv";
    private static final String DELIMITER = ",";

    private FileScanner() {
    }

    public List<Attendance> loadAttendances() {
        List<String> lines = readFile(ATTENDANCES_PATH);
        return lines.stream()
                .map(this::parseToAttendance)
                .collect(Collectors.toList());

    }

    private Attendance parseToAttendance(String line) {
        String[] parts = line.split(DELIMITER);
        return new Attendance(
                parts[0],
                LocalDateTime.parse(parts[1])
        );
    }

    public List<String> readFile(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            List<String> fileBody = new ArrayList<>();
            scanner.next();
            while (scanner.hasNext()) {
                fileBody.add(scanner.next());
            }
            return fileBody;
        } catch (IOException e) {
            throw new IllegalStateException(ErrorMessage.INVALID_FILE_READ.getMessage());
        }
    }
}
