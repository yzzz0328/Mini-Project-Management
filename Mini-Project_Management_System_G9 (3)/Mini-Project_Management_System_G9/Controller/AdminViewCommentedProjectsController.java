package Controller;

import Model.Admin;
import Model.ProjectModel;
import View.AdminViewCommentedProjectsView;
import View.AdminViewProjectReportView;

// Lam Ting Le
public class AdminViewCommentedProjectsController {
    // Declare ProjectModel object
    private ProjectModel projectModel;

    // Declare AdminViewCommentedProjectsView object
    private AdminViewCommentedProjectsView view;

    // Lam Ting Le
    // Constructs a AdminViewCommentedProjectsController object with parameters
    public AdminViewCommentedProjectsController(ProjectModel projectModel, AdminViewCommentedProjectsView view) {
        this.projectModel = projectModel;
        this.view = view;
        initView();
    }

    // Lam Ting Le
    // Sets view
    public void initView() {
        view.getCaption().setText(setCaption());
    }

    // Lam Ting Le
    // Sets listener for button click to return to previous window
    public void initController(Admin m) {
        view.getBackButton().addActionListener(e -> {
            view.getFrame().dispose();
            AdminViewProjectReportView v = new AdminViewProjectReportView();
            AdminViewProjectReportController smc = new AdminViewProjectReportController(m, v);
            smc.initController(m);
        });
    }

    // Lam Ting Le
    // Sets the row number of the table into the caption
    public String setCaption() {
        int noOfRows = view.getTableList().length;
        return "There are " + noOfRows + " projects that contains comments.";
    }
}
