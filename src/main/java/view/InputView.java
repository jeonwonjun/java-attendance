package view;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDate;
import model.day.DecemberCalendar;

public class InputView {
    public static String readMenu(LocalDate localDate) {
        System.out.printf("오늘은 %s월 %s일 %s입니다. 기능을 선택해 주세요.\n", localDate.getMonthValue(), localDate.getDayOfMonth(),
                DecemberCalendar.findDescription(localDate));
        System.out.println("1. 출석 확인");
        System.out.println("2. 출석 수정");
        System.out.println("3. 크루별 출석 기록 확인");
        System.out.println("4. 제적 위험자 확인");
        System.out.println("Q. 종료");
        return Console.readLine();
    }

    public static String readNickName() {
        System.out.println("\n닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public static String readTime() {
        System.out.println("등교 시간을 입력해 주세요.");
        return Console.readLine();
    }

    public static String readUpdateNickName() {
        System.out.println("\n출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public static String readUpdateDay() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
        return Console.readLine();
    }

    public static String readUpdateTime() {
        System.out.println("언제로 변경하시겠습니까?");
        return Console.readLine();
    }
}
