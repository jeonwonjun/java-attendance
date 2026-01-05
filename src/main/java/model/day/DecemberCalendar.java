package model.day;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public enum DecemberCalendar {
    DEC_1(LocalDate.of(2024, 12, 1), "일요일"),
    DEC_7(LocalDate.of(2024, 12, 7), "토요일"),
    DEC_8(LocalDate.of(2024, 12, 8), "일요일"),
    DEC_14(LocalDate.of(2024, 12, 14), "토요일"),
    DEC_15(LocalDate.of(2024, 12, 15), "일요일"),
    DEC_21(LocalDate.of(2024, 12, 21), "토요일"),
    DEC_22(LocalDate.of(2024, 12, 22), "일요일"),
    DEC_28(LocalDate.of(2024, 12, 28), "토요일"),
    DEC_29(LocalDate.of(2024, 12, 29), "일요일"),
    DEC_25(LocalDate.of(2024, 12, 25), "성탄절"),
    ;

    private final LocalDate date;
    private final String description;

    DecemberCalendar(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    public static boolean isWeekendOrHoliday(LocalDate checkDate) {
        if (checkDate.getYear() != 2024 || checkDate.getMonthValue() != 12) {
            return false;
        }

        Set<LocalDate> holidaysAndWeekends = Arrays.stream(values())
                .map(DecemberCalendar::getDate)
                .collect(Collectors.toSet());

        return holidaysAndWeekends.contains(checkDate);
    }

    public static String findDescription(LocalDate checkDate) {
        return Arrays.stream(values())
                .filter(day -> day.date.equals(checkDate))
                .map(DecemberCalendar::getDescription)
                .findFirst()
                .orElseGet(() -> checkDate.getDayOfWeek()
                        .getDisplayName(TextStyle.FULL, Locale.KOREAN));
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
