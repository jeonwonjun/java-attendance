package util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import model.attendace.Attendance;
import model.attendace.Attendances;

public class FileScanner {
    private static final String ATTENDANCES_PATH = "src/main/resources/attendances.csv";
    private static final String DELIMITER = ",";

    public FileScanner() {
    }

    public List<Attendance> loadAttendances() {
        List<String> lines = readFile(ATTENDANCES_PATH);
        return lines.stream()
                .map(this::parseToAttendance)
                .collect(Collectors.toList());

    }

    private Attendance parseToAttendance(String line) {
        String[] parts = line.split(DELIMITER);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new Attendance(
                parts[0],
                LocalDateTime.parse(parts[1], formatter)
        );
    }

    public List<String> readFile(String filePath) {
        List<String> fileBody = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            // 1. 첫 번째 줄(헤더: nickname,datetime) 무시
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // 2. 줄 단위로 끝까지 읽기 (공백에 잘리지 않도록 nextLine() 사용)
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    fileBody.add(line);
                }
            }
            return fileBody;
        } catch (IOException e) {
            throw new IllegalStateException(ErrorMessage.INVALID_FILE_READ.getMessage());
        }
    }
}
