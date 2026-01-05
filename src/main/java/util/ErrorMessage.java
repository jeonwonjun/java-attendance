package util;

public enum ErrorMessage {
    IS_HOLIDAY("%s은 등교일이 아닙니다."),
    INVALID_NICKNAME_EXIST("등록되지 않은 닉네임입니다."),
    INVALID_INPUT_FORMAT("잘못된 형식을 입력하였습니다."),
    INVALID_IS_ATTENDANCE("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요."),
    INVALID_OPEN("캠퍼스 운영 시간에만 출석이 가능합니다."),
    FUTURE_DAY("아직 수정할 수 없습니다.");

    private final String message;
    private static final String PREFIX = "ERROR ";

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
