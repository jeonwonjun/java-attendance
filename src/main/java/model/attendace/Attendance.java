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
    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private LocalTime localTime;
    private AttendanceState state;

    public Attendance(String nickname, LocalDateTime dateTime) {
        this.nickname = nickname;
        this.localDateTime = dateTime;
        this.localDate = dateTime.toLocalDate();
        this.localTime = dateTime.toLocalTime();
        this.state = decideState(this.localDate, this.localTime);
    }

    private AttendanceState decideState(LocalDate date, LocalTime time) {
        DecemberCalendar.validateIsWorkDay(date);
        String dayDescription = DecemberCalendar.findDescription(date);
        return OperatingTime.decideAttendanceState(dayDescription, time);
    }

    public void updateTime(LocalTime newTime) {
        this.localTime = newTime;
        this.localDateTime = LocalDateTime.of(this.localDate, newTime);
        this.state = decideState(this.localDate, newTime);
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime localDateTime() {
        return localDateTime;
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
