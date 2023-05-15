package Controller;

import Model.Admin;
import Model.ProjectModel;
import Model.UserModel;
import View.*;

import java.io.IOException;

// Chang Siu Hong
public class AdminMainController {
        private Admin model;
        private AdminMainView view;

        // Chang Siu Hong
        // parameterized constructor
        public AdminMainController(Admin m) {
                this.model = m;
                this.view = new AdminMainView();
                this.view.setWelcomeMessage("Hi, " + this.model.getUserFullName());
        }

        // Chang Siu Hong
        // initial controller that performs certain functions whenever certain buttons
        // is pressed, i.e. Register button, Add Project button, View Project button, 
        // Delete Project button, View Project Report button and Logout button.
        public void initController() {
                view.getRegisterUser().addActionListener(e -> {
                        view.dispose();
                        AdminRegisterView v = new AdminRegisterView();
                        AdminRegisterController smc = new AdminRegisterController(this.model, v);
                        smc.initController();}
                );
                view.getAddProject().addActionListener(e -> {
                        view.dispose();
                        ProjectModel m = new ProjectModel();
                        AdminAddProjectView v = new AdminAddProjectView();
                        AdminAddProjectController smc = new AdminAddProjectController(m, v);
                        smc.initController(this);}
                );
                view.getViewProjects().addActionListener(e -> {
                        view.dispose();
                        try {
                                ProjectModel m = new ProjectModel();
                                AdminProjectListView v =  new AdminProjectListView();
                                AdminProjectListController aplc = new AdminProjectListController(m, v);
                                aplc.initController(this);
                        } catch (IOException ex) {
                                throw new RuntimeException(ex);
                        }
                }
                );
                view.getDeleteProject().addActionListener(e -> {
                        view.dispose();
                        try {
                                ProjectModel m = new ProjectModel();
                                AdminDeleteProjectView v = new AdminDeleteProjectView();
                                AdminDeleteProjectController smc = new AdminDeleteProjectController(m, v);
                                smc.initController(this);
                        } catch (IOException e1) {
                                e1.printStackTrace();}
                        }
                );
                view.getViewProjectReport().addActionListener(e -> {
                        view.dispose();
                        AdminViewProjectReportView v = new AdminViewProjectReportView();
                        AdminViewProjectReportController smc = new AdminViewProjectReportController(model, v);
                        smc.initController(this.model); }
                );
                view.getLogout().addActionListener(e -> {
                        view.dispose();
                        LoginView v = new LoginView();
                        LoginController smc = new LoginController(v);
                        smc.initController();}
                );
        }

        // Chang Siu Hong
        // this method returns admin model
        public Admin getModel() {
                return model;
        }

        // Chang Siu Hong
        // this method sets admin model
        public void setModel(Admin model) {
                this.model = model;
        }
}
       