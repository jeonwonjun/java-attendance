package model.attendace;

import java.util.List;
import util.ErrorMessage;

public class Attendances {
    private final List<Attendance> attendanceList;

    public Attendances(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public Attendance findByNickName(String nickName) {
        return attendanceList.stream()
                .filter(attendance -> attendance.getNickname().equals(nickName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_NICKNAME_EXIST.getMessage()));
    }
}
