package Controller;

import Model.Student;
import View.StudentAccountView;

// Teo Yih Shing
public class StudentAccountController {
    private Student model;
    private StudentAccountView view;

    // Teo Yih Shing
    // parameterized constructor
    public StudentAccountController(Student model) {
        this.model = model;
        this.view = new StudentAccountView();
        initView();
    }

    // Teo Yih Shing
    // this method is used to initialize the student account details into StudentAccountView view in this class
    private void initView() {
        this.view.setName(this.model.getUserFullName());
        this.view.setID(this.model.getName());
        if (this.model.getProject().getStatus().equals("Active"))
            this.view.setProject(this.model.getProject().getName());
        this.view.setSpecialization(this.model.getSpecialization());
    }

    // Teo Yih Shing
    // this method is used to call the backActionListener when user click on the "Back" button
    public void initController(StudentMenuController c) {
        this.view.getBackButton().addActionListener(e -> backActionListener(c));
    }

    // Teo Yih Shing
    // this method is used to redirect the user go back to the previous screen
    private void backActionListener(StudentMenuController c) {
        c = new StudentMenuController(model);
        c.initController();
        this.view.dispose();
    }
}