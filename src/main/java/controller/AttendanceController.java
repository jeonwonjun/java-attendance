package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.attendace.Attendance;
import model.attendace.Attendances;
import model.state.AttendanceState;
import model.state.StudentState;
import model.student.Student;
import model.student.Students;
import util.ErrorMessage;
import util.FileScanner;
import view.InputView;
import camp.nextstep.edu.missionutils.DateTimes;
import view.OutputView;

public class AttendanceController {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private Attendances attendances;
    private Students students;
    private final LocalDate today = LocalDate.of(2024, 12, 16);

    public void start() {
        init();
        while (true) {
            try {
                String menu = InputView.readMenu(today);
                if (menu.equals("Q")) {
                    break;
                }
                validateInput(menu);
                handleMenu(menu);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    public void init() {
        FileScanner fileScanner = new FileScanner();
        List<Attendance> attendanceList = fileScanner.loadAttendances();
        this.attendances = new Attendances(attendanceList);

        List<String> crewNames = attendanceList.stream()
                .map(Attendance::getNickname)
                .distinct()
                .toList();

        this.students = new Students(initializeStudents(attendanceList, crewNames));
    }

    private List<Student> initializeStudents(List<Attendance> attendanceList, List<String> crewNames) {
        List<Student> studentList = new ArrayList<>();
        for (String name : crewNames) {
            Student student = new Student(name, 0, 0, 0, StudentState.NORMAL);

            attendanceList.stream()
                    .filter(a -> a.getNickname().equals(name))
                    .forEach(a -> student.addAttendanceState(a.getState()));

            studentList.add(student);
        }
        return studentList;
    }

    private void validateInput(String input) {
        if (input.matches("[^1-4]")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }
    }

    private void handleMenu(String menu) {
        if (menu.equals("1")) {
            checkAttendance();
        }
        if (menu.equals("2")) {
            updateAttendance();
        }
        if (menu.equals("3")) {
            checkCrewReport();
        }
        if (menu.equals("4")) {
            checkRiskStudents();
        }
    }

    private void checkAttendance() {
        String nickName = InputView.readNickName();
        Student student = students.findByNickName(nickName);

        LocalTime time = LocalTime.parse(InputView.readTime(), TIME_FORMATTER);
        Attendance attendance = new Attendance(nickName, LocalDateTime.of(today, time));

        attendances.add(attendance);

        student.addAttendanceState(attendance.getState());

        OutputView.printAttendance(attendance);
    }

    private void updateAttendance() {
        String nickName = InputView.readUpdateNickName();
        Student student = students.findByNickName(nickName);

        int day = Integer.parseInt(InputView.readUpdateDay());
        LocalDate targetDate = LocalDate.of(2024, 12, day);

        // 1. 기존 기록 찾기
        Attendance attendance = attendances.findByNickNameAndDate(nickName, targetDate)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_NICKNAME_EXIST.getMessage()));

        // 2. 수정 전 상태 보관
        LocalTime beforeTime = attendance.getLocalTime();
        AttendanceState beforeState = attendance.getState();

        // 3. 수정 진행
        LocalTime newTime = LocalTime.parse(InputView.readTime(), TIME_FORMATTER);
        attendance.updateTime(newTime);
        AttendanceState afterState = attendance.getState();

        // 4. Student 갱신
        student.removeAttendanceState(beforeState);
        student.addAttendanceState(afterState);

        OutputView.printUpdateAttendance(attendance, beforeTime, beforeState);
    }

    private void checkCrewReport() {
        String nickName = InputView.readNickName();
        Student student = students.findByNickName(nickName);

        List<Attendance> crewRecords = attendances.findAllByNickName(nickName);
        OutputView.printCrewRecordsHeader(nickName);
        // 어제까지의 기록을 보여줌 (DateTimes.now().toLocalDate().minusDays(1))
        OutputView.printCrewRecords(crewRecords, today.minusDays(1));
        OutputView.printStudentStatistics(student);
    }

    private void checkRiskStudents() {
        List<Student> risks = students.findExpulsionRiskStudents();
        OutputView.printRiskStudents(risks);
    }


}
