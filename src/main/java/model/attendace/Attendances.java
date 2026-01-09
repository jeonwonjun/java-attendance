package model.attendace;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import util.ErrorMessage;

public class Attendances {
    private final List<Attendance> attendanceList;

    public Attendances(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public void add(Attendance attendance) {
        if (findByNickNameAndDate(attendance.getNickname(), attendance.getLocalDate()).isPresent()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_IS_ATTENDANCE.getMessage());
        }
        attendanceList.add(attendance);
    }

    public boolean hasNickname(String nickname) {
        return attendanceList.stream().anyMatch(a -> a.getNickname().equals(nickname));
    }

    // 특정 크루의 특정 날짜 기록 조회
    public Optional<Attendance> findByNickNameAndDate(String nickName, LocalDate date) {
        return attendanceList.stream()
                .filter(attendance -> attendance.getNickname().equals(nickName) && attendance.getLocalDate().equals(date))
                .findFirst();
    }

    // 특정 크루의 모든 출석 기록 조회(3번 기능)
    public List<Attendance> findAllByNickName(String nickName) {
        return attendanceList.stream()
                .filter(attendance -> attendance.getNickname().equals(nickName))
                .toList();
    }
}
