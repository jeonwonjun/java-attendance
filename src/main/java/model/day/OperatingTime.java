package model.day;

import java.time.LocalTime;
import model.state.AttendanceState;

public enum OperatingTime {
    MONDAY(LocalTime.of(13,0)),
    NON_MONDAY(LocalTime.of(10,0));

    private static final int lateTime = 5;
    private static final int absenceTime = 30;

    private final LocalTime startTime;

    OperatingTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public static AttendanceState decideAttendanceState(String description, LocalTime time) {
        if (isAbsence(description, time)) {
            return AttendanceState.ABSENCE;
        }

        if (isLate(description, time)) {
            return AttendanceState.LATE;
        }

        return AttendanceState.NORMAL;
    }

    private static boolean isAbsence(String description, LocalTime time) {
        if (description.equals("월요일")) {
            return (time == null) || time.isAfter(MONDAY.startTime.plusMinutes(absenceTime));
        }

        return (time == null) || time.isAfter(NON_MONDAY.startTime.plusMinutes(absenceTime));
    }

    private static boolean isLate(String description, LocalTime time) {
        if (description.equals("월요일")) {
            return time.isAfter(MONDAY.startTime.plusMinutes(lateTime)) && time.isBefore(MONDAY.startTime.plusMinutes(absenceTime));
        }
        return time.isAfter(NON_MONDAY.startTime.plusMinutes(lateTime)) && time.isBefore(NON_MONDAY.startTime.plusMinutes(absenceTime));
    }

}
