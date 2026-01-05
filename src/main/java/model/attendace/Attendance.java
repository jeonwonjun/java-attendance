package model.attendace;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import model.day.DecemberCalendar;
import model.day.OperatingTime;
import model.state.AttendanceState;
import util.ErrorMessage;

public class Attendance {
    private final String nickname;
    private LocalDate localDate;
    private LocalTime localTime;
    private AttendanceState state;

    public Attendance(String nickname, LocalDateTime dateTime) {
        this.nickname = nickname;
        this.localDate = dateTime.toLocalDate();
        this.localTime = dateTime.toLocalTime();
        this.state = decideState(this.localDate, this.localTime);
    }

    private AttendanceState decideState(LocalDate date, LocalTime time) {
        validateHoliday(date);
        String dayDescription = DecemberCalendar.findDescription(date);
        return OperatingTime.decideAttendanceState(dayDescription, time);
    }

    private void validateHoliday(LocalDate date) {
        if (isHoliday(date)) {
            String month = String.valueOf(date.getMonthValue());
            String day = String.valueOf(date.getDayOfMonth());
            String dayDescription = DecemberCalendar.findDescription(date);
            String formatter = ErrorMessage.IS_HOLIDAY.getMessage();
            String message = String.format(formatter, month, day, dayDescription);
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isHoliday(LocalDate localDate) {
        return DecemberCalendar.isWeekendOrHoliday(localDate);
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public AttendanceState getState() {
        return state;
    }

}
