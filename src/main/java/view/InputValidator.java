package view;

import java.util.List;
import util.ErrorMessage;

public class InputValidator {
    private static final String DATE_FORMAT = "^\\d+$";
    private static final String TIME_FORMAT = "^([01][0-9]|2[0-3]):[0-5][0-9]$";

    public void validateCrewExists(String nickName, List<String> crewList) {
        if (!crewList.contains(nickName)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NICKNAME_EXIST.getMessage());
        }
    }

    public void validateTimeFormat(String time) {
        if (!time.matches(TIME_FORMAT)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT.getMessage());
        }
    }

}
