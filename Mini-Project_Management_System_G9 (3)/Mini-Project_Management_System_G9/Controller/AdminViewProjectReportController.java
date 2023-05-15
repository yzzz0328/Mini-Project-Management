package Controller;

import Model.Admin;
import Model.ProjectModel;
import View.*;

import java.io.IOException;

// Chang Siu Hong
public class AdminViewProjectReportController {
    private Admin model;
    private AdminViewProjectReportView view;

    // Chang Siu Hong
    // parameterized constructor
    public AdminViewProjectReportController(Admin m, AdminViewProjectReportView v) {
        this.model = m;
        this.view = v;
    }

    // Chang Siu Hong
    // an initial controller with Admin parameter to handle the action whenever certain button 
    // is clicked.
    // In this method, our programs prompts users to select the type of projects report they wish to
    // to see and directs users to the respective view page by calling the controller with view.
    // When back button is pressed, user will be directed to the previous view page.
    public void initController(Admin a){
        view.getBackButton().addActionListener(e -> {
            view.dispose();
            AdminMainController smc = new AdminMainController(this.model);
            smc.initController();
        });
        view.getViewAllProjects().addActionListener(e -> {
            view.dispose();
            try {
                ProjectModel m = new ProjectModel();
                AdminViewAllProjectsView v = new AdminViewAllProjectsView();
                AdminViewAllProjectsController smc = new AdminViewAllProjectsController(m, v);
                smc.initController(this.model);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        view.getViewActiveProject().addActionListener(e -> {
            view.dispose();
            try {
                ProjectModel m = new ProjectModel();
                AdminViewActiveProjectsView v = new AdminViewActiveProjectsView();
                AdminViewActiveProjectsController smc = new AdminViewActiveProjectsController(m, v);
                smc.initController(this.model);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        view.getViewInactiveProject().addActionListener(e -> {
            view.dispose();
            try {
                ProjectModel m = new ProjectModel();
                AdminViewInactiveProjectsView v = new AdminViewInactiveProjectsView();
                AdminViewInactiveProjectsController smc = new AdminViewInactiveProjectsController(m, v);
                smc.initController(this.model);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        view.getViewAssginedProject().addActionListener(e -> {
            view.dispose();
            try {
                ProjectModel m = new ProjectModel();
                AdminViewAssignedProjectsView v = new AdminViewAssignedProjectsView();
                AdminViewAssignedProjectsController smc = new AdminViewAssignedProjectsController(m, v);
                smc.initController(this.model);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        view.getViewUnassignedProject().addActionListener(e -> {
            view.dispose();
            try {
                ProjectModel m = new ProjectModel();
                AdminViewUnassignedProjectsView v = new AdminViewUnassignedProjectsView();
                AdminViewUnassignedProjectsController smc = new AdminViewUnassignedProjectsController(m, v);
                smc.initController(this.model);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        view.getViewProjectWithComments().addActionListener(e -> {
            view.dispose();
            ProjectModel m = new ProjectModel();
            AdminViewCommentedProjectsView v = new AdminViewCommentedProjectsView();
            AdminViewCommentedProjectsController smc = new AdminViewCommentedProjectsController(m, v);
            smc.initController(this.model);
        });
        view.getViewProjectsWithSpecialization().addActionListener(e -> {
            view.dispose();
            ProjectModel m = new ProjectModel();
            AdminSpecializationSortedProjectsView v = null;
            try {
                v = new AdminSpecializationSortedProjectsView();
                AdminSpecializationSortedProjectsController smc = new AdminSpecializationSortedProjectsController(m, v);
                smc.initController(this.model);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        view.getViewLecturerProject().addActionListener(e -> {
            view.dispose();
            ProjectModel m = new ProjectModel();
            AdminLecturerSortedProjectsView v = null;
            try {
                v = new AdminLecturerSortedProjectsView();
                AdminLecturerSortedProjectsController smc = new AdminLecturerSortedProjectsController(m, v);
                smc.initController(this.model);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
