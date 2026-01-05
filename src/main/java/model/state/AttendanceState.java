package model.state;

public enum AttendanceState {
    NORMAL("출석"),
    LATE("지각"),
    ABSENCE("결석");

    private final String attendanceState;

    AttendanceState(String attendanceState) {
        this.attendanceState = attendanceState;
    }

    public String getAttendanceState() {
        return attendanceState;
    }
}
