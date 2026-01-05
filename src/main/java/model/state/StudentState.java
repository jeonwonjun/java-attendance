package model.state;

public enum AttendanceState {
    NORMAL(""),
    WARNING("경고"),
    MEETING("면담"),
    EXPULSION("제적");

    private final String state;

    AttendanceState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
