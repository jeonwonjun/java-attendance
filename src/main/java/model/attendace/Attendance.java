package model.attendace;

import java.time.LocalDateTime;
import model.state.AttendanceState;
import model.state.StudentState;

public class Attendance {
    private final String nickname;
    private LocalDateTime dateTime;
    private AttendanceState state;

    public Attendance(String nickname, LocalDateTime dateTime, AttendanceState state) {
        this.nickname = nickname;
        this.dateTime = dateTime;
        this.state = state;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public AttendanceState getState() {
        return state;
    }

}
