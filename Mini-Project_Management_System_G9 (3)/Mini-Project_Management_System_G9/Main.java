import Controller.LoginController;
import View.LoginView;

public class Main {
    private static LoginView view;

    public static void main(String[] args) {
        view = new LoginView();
        LoginController controller = new LoginController(view);
        controller.initController();
    }
}