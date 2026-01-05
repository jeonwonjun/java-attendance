package model.student;

import model.state.StudentState;

public class Student {
    private static final int ONE = 1;
    private final String nickName;
    private int absenceCount;
    private int lateCount;
    private StudentState state;

    public Student(String nickName, int absenceCount, int lateCount, StudentState state) {
        this.nickName = nickName;
        this.absenceCount = absenceCount;
        this.lateCount = lateCount;
        this.state = state;
    }

    public void increaseAbsenceCount() {
        this.absenceCount += ONE;
    }

    public void decreaseAbsenceCount() {
        this.absenceCount -= ONE;
    }

    public void increaseLateCount() {
        this.lateCount += ONE;
    }

    public void decreaseLateCount() {
        this.lateCount -= ONE;
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

    public StudentState getState() {
        return state;
    }
}
