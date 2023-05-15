package Controller;

import Model.Lecturer;
import Model.ProjectModel;
import Model.UserModel;
import View.LectureMenuView;
import View.LoginView;

import java.io.IOException;

// Teoh Ye Zhian
public class LectureMenuController {
    private Lecturer model;
    private LectureMenuView view;
    private ProjectModel pmodel;

    // Teoh Ye Zhian
    // parameterized constructor
    public LectureMenuController(Lecturer model) {
        this.model = model;
        this.view = new LectureMenuView();
        this.view.setWelcomeMessage("Hi, " + this.model.getUserFullName());
    }

    // Teoh Ye Zhian
    // an initial controller to handle the action whenever certain button is clicked.
    // In this method, our programs tries to direct lecturer back to previous page
    // by clicking the back button, activates the project by clicking "Activate" button, 
    // deactivates the project by clicking "Deactivate" button and by clicking the "Logout" 
    // button to logout.
    public void initController() {
        this.view.getlecturerCenterButton().addActionListener(e -> LCActionListener());
        this.view.getprojectButton().addActionListener(e -> {
            try {
                VBActionListener();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        this.view.getactivationButton().addActionListener(e -> {
            try {
                AActionListener();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        this.view.getassignationbutton().addActionListener(e -> {
            try {
                AssignactionListener();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        this.view.getLogoutButton().addActionListener(e -> LOActionListener());
    }

    // Teoh Ye Zhian
    // an action listener method that calls LectureAccountController controller.
    public void LCActionListener() {
        this.view.dispose();
        LectureAccountController lc = new LectureAccountController(model);
        lc.initController(this);
    }

    // Teoh Ye Zhian
    // an action listener method that calls LectureViewBoardController controller.
    public void VBActionListener() throws IOException {
        this.view.dispose();
        LectureViewBoardController lvbc = new LectureViewBoardController(model);
        lvbc.initController(this);
    }

    // Teoh Ye Zhian
    // an action listener method that calls LecturerProjectActivationController controller.
    public void AActionListener() throws IOException {
        this.view.dispose();
        this.pmodel = new ProjectModel();
        LecturerProjectActivationController lpac = new LecturerProjectActivationController(pmodel,
                this.getModel().getName());
        lpac.initController(this);
    }

    // Teoh Ye Zhian
    // an action listener method that calls LectureAssignController controller.
    public void AssignactionListener() throws IOException {
        this.view.dispose();
        this.pmodel = new ProjectModel();
        LectureAssignController lass = new LectureAssignController(pmodel, this.model.getName());
        lass.initController(this);
    }

    // Teoh Ye Zhian
    // an action listener method that calls LoginController controller.
    public void LOActionListener() {
        this.view.dispose();
        LoginView v = new LoginView();
        LoginController lc = new LoginController(v);
        lc.initController();
    }

    // Teoh Ye Zhian
    // this method returns the model.
    public Lecturer getModel() {
        return this.model;
    }
}
