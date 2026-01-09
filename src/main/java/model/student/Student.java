package model.student;

import model.state.AttendanceState;
import model.state.StudentState;

public class Student {
    private static final int ONE = 1;
    private static final int TRANSLATE_ABSENCE = 3;
    private static final int ABSENCE_COUNT = 6;
    private static final int MEETING_COUNT = 3;
    private static final int WARNING_COUNT = 2;
    private final String nickName;
    private int absenceCount;
    private int lateCount;
    private int normalCount;
    private StudentState state;

    public Student(String nickName, int absenceCount, int lateCount, int normalCount, StudentState state) {
        this.nickName = nickName;
        this.absenceCount = absenceCount;
        this.lateCount = lateCount;
        this.normalCount = normalCount;
        this.state = state;
    }

    public void addAttendanceState(AttendanceState state) {
        if (state == AttendanceState.NORMAL) {
            this.normalCount++;
        }
        if (state == AttendanceState.LATE) {
            this.lateCount++;
        }
        if (state == AttendanceState.NORMAL) {
            this.absenceCount++;
        }
        updateStudentState();
    }

    public void removeAttendanceState(AttendanceState state) {
        if (state == AttendanceState.NORMAL) {
            this.normalCount--;
        }
        if (state == AttendanceState.LATE) {
            this.lateCount--;
        }
        if (state == AttendanceState.NORMAL) {
            this.absenceCount--;
        }
        updateStudentState();
    }

    public void increaseAbsenceCount() {
        this.absenceCount += ONE;
    }

    public void increaseLateCount() {
        this.lateCount += ONE;
    }

    // 지각을 결석으로 변환한 총 결석 횟수 반환
    public int getTotalAbsenceCount() {
        return this.absenceCount + (this.lateCount / 3);
    }

    // 현재 상태를 카운트에 따라 동적으로 업데이트
    public void updateStudentState() {
        int totalAbsence = getTotalAbsenceCount();
        if (totalAbsence >= ABSENCE_COUNT) {
            this.state = StudentState.EXPULSION;
            return;
        }
        if (totalAbsence >= MEETING_COUNT) {
            this.state = StudentState.MEETING;
            return;
        }
        if (totalAbsence >= WARNING_COUNT) {
            this.state = StudentState.WARNING;
            return;
        }
        this.state = StudentState.NORMAL;
    }

    public String getRiskReport() {
        return String.format("- %s: 결석 %d회, 지각 %d회 (%s)",
                nickName, absenceCount, lateCount, state.getState());
    }

    public String getNickName() {
        return nickName;
    }

    public int getAbsenceCount() {
        return absenceCount;
    }

    public int getLateCount() {
        return lateCount;
    }

    public int getNormalCount() {
        return normalCount;
    }

    public StudentState getState() {
        return state;
    }
}
