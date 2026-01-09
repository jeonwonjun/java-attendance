import controller.AttendanceController;

public class Application {

    public static void main(String[] args) {
        try {
            AttendanceController controller = new AttendanceController();
            controller.start();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
