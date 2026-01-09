package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import model.attendace.Attendance;
import model.day.DecemberCalendar;
import model.state.AttendanceState;
import model.student.Student;
import model.student.Students;

public class OutputView {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MM월 dd일 E요일", Locale.KOREAN);
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm");

    // 1번 기능: 출석 확인 출력
    public static void printAttendance(Attendance attendance) {
        String date = attendance.getLocalDate().format(DATE_FORMATTER);
        String time = attendance.getLocalTime().format(TIME_FORMATTER);
        String state = attendance.getState().getAttendanceState();

        System.out.printf("\n%s %s (%s)\n\n", date, time, state);
    }

    // 2번 기능: 수정 완료 출력
    // 파라미터로 수정 전의 시간(beforeTime)과 상태(beforeState)를 함께 전달받는 설계
    public static void printUpdateAttendance(Attendance attendance, LocalTime beforeTime, AttendanceState beforeState) {
        String date = attendance.getLocalDate().format(DATE_FORMATTER);
        String beforeTimeStr = beforeTime.format(TIME_FORMATTER);
        String afterTimeStr = attendance.getLocalTime().format(TIME_FORMATTER);

        System.out.printf("\n%s %s (%s) -> %s (%s) 수정 완료!\n\n",
                date, beforeTimeStr, beforeState.getAttendanceState(),
                afterTimeStr, attendance.getState().getAttendanceState());
    }

    public static void printCrewRecordsHeader(String nickName) {
        System.out.printf("\n이번 달 %s의 출석 기록입니다. \n\n", nickName);
    }

    public static void printCrewRecords(List<Attendance> records, LocalDate endDate) {
        for (LocalDate date = LocalDate.of(2024, 12, 1); !date.isAfter(endDate); date = date.plusDays(1)) {
            if (DecemberCalendar.isWeekendOrHoliday(date)) {
                continue;
            }

            LocalDate currentDate = date;
            Optional<Attendance> attendance = records.stream()
                    .filter(r -> r.getLocalDate().equals(currentDate))
                    .findFirst();
            if (attendance.isPresent()) {
                Attendance att = attendance.get();
                String todayDate = att.getLocalDate().format(DATE_FORMATTER);
                String time = att.getLocalTime().format(TIME_FORMATTER);
                String state = att.getState().getAttendanceState();
                System.out.printf("%s %s (%s)\n", todayDate, time, state);
            }
            if (attendance.isEmpty()) {
                System.out.printf("%s --:-- (결석)\n", currentDate);
            }
        }
    }

    public static void printStudentStatistics(Student student) {
        System.out.println();
        System.out.printf("출석: %d회\n", student.getNormalCount());
        System.out.printf("지각: %d회\n", student.getLateCount());
        System.out.printf("결석: %d회\n", student.getAbsenceCount());

        if (!student.getState().getState().isEmpty()) {
            System.out.printf("\n%s 대상자입니다.\n\n", student.getState().getState());
        }
    }

    public static void printRiskStudents(List<Student> riskStudents) {
        System.out.println("\n제적 위험자 조회 결과");

        riskStudents.forEach(student -> System.out.println(student.getRiskReport()));
        System.out.println();
    }
}
