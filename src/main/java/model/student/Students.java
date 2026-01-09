package model.student;

import java.util.List;
import util.ErrorMessage;

public class Students {
    private final List<Student> studentList;

    public Students(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Student findByNickName(String nickName) {
        return studentList.stream()
                .filter(student -> student.getNickName().equals(nickName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_NICKNAME_EXIST.getMessage()));
    }

    public List<Student> findExpulsionRiskStudents() {
        return studentList.stream()
                .filter(student -> student.getTotalAbsenceCount() >= 2)
                .sorted((s1, s2) -> {
                    int compareAbsence = Integer.compare(s2.getTotalAbsenceCount(), s1.getTotalAbsenceCount());
                    if (compareAbsence != 0) {
                        return compareAbsence;
                    }

                    return s1.getNickName().compareTo(s2.getNickName());
                })
                .toList();
    }
}
