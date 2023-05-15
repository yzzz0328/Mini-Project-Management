package Controller;

import Model.Student;
import Model.UserModel;
import View.LoginView;
import View.StudentMenuView;

import java.io.IOException;

// Teo Yih Shing
public class StudentMenuController {
    private Student model;
    private StudentMenuView view;

    // Teo Yih Shing
    // parameterized constructor
    public StudentMenuController(Student model) {
        this.model = model;
        this.view = new StudentMenuView();
        this.view.setWelcomeMessage("Hi, " + this.model.getUserFullName());
    }

    // Teo Yih Shing
    // this method is used to call the SCActionListener when user click on the "Account" button,  
    // call the VBActionListener when user click on the "Viewboard" button, and
    // call the LOActionListener when user click on the "Logout" button
    public void initController() {
        this.view.getStudentCenterButton().addActionListener(e -> SCActionListener());
        this.view.getViewBoardButton().addActionListener(e -> {
            try {
                VBActionListener();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        this.view.getLogoutButton().addActionListener(e -> LOActionListener());
    }

    // Teo Yih Shing
    // this method is used to redirect user to the "Student View Account Screen"
    private void SCActionListener() {
        this.view.dispose();
        StudentAccountController sc = new StudentAccountController(model);
        sc.initController(this);
    }

    // Teo Yih Shing
    // this method is used to redirect user to the "Student Viewboard Screen"
    private void VBActionListener() throws IOException {
        this.view.dispose();
        StudentViewBoardController svbc = new StudentViewBoardController(model);
        svbc.initController(this);
    }

    // Teo Yih Shing
    // this method is used to logout and redirect user go back to the "Login Screen"
    private void LOActionListener() {
        this.view.dispose();
        LoginView v = new LoginView();
        LoginController lc = new LoginController(v);
        lc.initController();
    }

    // Teo Yih Shing
    // this method is used to get the Student model in this class
    public Student getModel() {
        return this.model;
    }
}