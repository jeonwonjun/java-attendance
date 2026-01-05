package model.attendace;

import java.util.List;
import util.ErrorMessage;

public class Attendances {
    private final List<Attendance> attendanceList;

    public Attendances(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public void add(Attendance attendance) {
        attendanceList.add(attendance);
    }

    public boolean hasNickName(String nickName) {
        Attendance hasNickName = attendanceList.stream()
                .filter(attendance -> attendance.getNickname().equals(nickName))
                .findFirst()
                .orElse(null);

        return hasNickName != null;
    }

    public Attendance findByNickName(String nickName) {
        return attendanceList.stream()
                .filter(attendance -> attendance.getNickname().equals(nickName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_NICKNAME_EXIST.getMessage()));
    }
}
