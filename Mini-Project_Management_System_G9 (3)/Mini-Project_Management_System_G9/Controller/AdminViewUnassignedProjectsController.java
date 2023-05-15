package Controller;

import Model.Admin;
import Model.ProjectModel;
import View.AdminViewProjectReportView;
import View.AdminViewUnassignedProjectsView;

// Chang Siu Hong
public class AdminViewUnassignedProjectsController {
        private ProjectModel model;
        private AdminViewUnassignedProjectsView view;

        // Chang Siu Hong
        // parameterized constructor
        public AdminViewUnassignedProjectsController(ProjectModel m, AdminViewUnassignedProjectsView v) {
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
        // an initial view class when user enters the view
        public void initController(Admin m) {
                view.getBackButton().addActionListener(e -> {
                        view.dispose();
                        AdminViewProjectReportView v = new AdminViewProjectReportView();
                        AdminViewProjectReportController smc = new AdminViewProjectReportController(m, v);
                        smc.initController(m);
                });
        }

        // Chang Siu Hong
        // this method gets the number of inactive projects and set the caption in the AdminViewInactiveProjectsView
        // view showing the number of existing inactive projects.
        public String setCaption() {
                int noOfRows = view.getProject_tablelist().length;
                return "There are " + noOfRows + " projects un-assigned to the students.";
        }
}