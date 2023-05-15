package Controller;

import Model.Admin;
import Model.ProjectModel;
import View.AdminViewActiveProjectsView;
import View.AdminViewProjectReportView;

// Chang Siu Hong
public class AdminViewActiveProjectsController {
        private ProjectModel model;
        private AdminViewActiveProjectsView view;

        // Chang Siu Hong
        // parameterized constructor
        public AdminViewActiveProjectsController(ProjectModel m, AdminViewActiveProjectsView v) {
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
        // In this method, our programs tries to display the list of active projects and
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
        // this method gets the number of active projects and set the caption in the AdminViewActiveProjectsView
        // view showing the number of existing active projects.
        public String setCaption() {
                int noOfRows = view.getProject_tablelist().length;
                return "There are " + noOfRows + " active projects created in total.";
        }
}