package Controller;

import Model.Admin;
import Model.ProjectModel;
import View.AdminViewAssignedProjectsView;
import View.AdminViewProjectReportView;

// Chang Siu Hong
public class AdminViewAssignedProjectsController {
        private ProjectModel model;
        private AdminViewAssignedProjectsView view;

        // Chang Siu Hong
        // parameterized constructor
        public AdminViewAssignedProjectsController(ProjectModel m, AdminViewAssignedProjectsView v) {
                this.model = m;
                this.view = v;
                initView();
        }

        // Chang Siu Hong
        // an initial view class when user enters the view
        public void initView() {
                view.getCaption().setText(setCaption());
        }

        // Chang Siu Hong
        // an initial controller with Admin parameter to handle the action whenever certain button 
        // is clicked.
        // In this method, our programs tries to display the list of assigned projects and
        // when back button is pressed, user will be directed to the previous view page.
        public void initController(Admin m) {
                view.getBackButton().addActionListener(e -> {
                        view.dispose();
                        AdminViewProjectReportView v = new AdminViewProjectReportView();
                        AdminViewProjectReportController smc = new AdminViewProjectReportController(m, v);
                        smc.initController(m);
                });
        }

        // Chang Siu Hong
        // this method gets the number of assigned projects and set the caption in the AdminViewAssignedProjectsView
        // view showing the number of existing assigned projects.
        public String setCaption() {
                int noOfRows = view.getProject_tablelist().length;
                return "There are " + noOfRows + " projects assigned to the students.";
        }
}