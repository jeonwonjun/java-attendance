package model.day;

import java.time.LocalTime;
import model.state.AttendanceState;
import util.ErrorMessage;

public enum OperatingTime {
    MONDAY(LocalTime.of(13,0), LocalTime.of(18, 0)),
    NON_MONDAY(LocalTime.of(10,0), LocalTime.of(18, 0));

    private static final int lateTime = 5;
    private static final int absenceTime = 30;

    private final LocalTime startTime;
    private final LocalTime endTime;

    OperatingTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static void validateServiceTime(String description, LocalTime time) {
        LocalTime start = NON_MONDAY.startTime;
        if (description.equals("월요일")) {
            start = MONDAY.startTime;
        }
        LocalTime end = NON_MONDAY.endTime;

        if (time.isBefore(start) || time.isAfter(end)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_OPEN.getMessage());
        }
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
