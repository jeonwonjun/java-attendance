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
}
