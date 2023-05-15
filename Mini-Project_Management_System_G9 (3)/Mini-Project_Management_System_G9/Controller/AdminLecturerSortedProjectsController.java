package Controller;

import Model.Admin;
import Model.ProjectModel;
import View.AdminLecturerSortedProjectsView;
import View.AdminViewProjectReportView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

// Lam Ting Le
public class AdminLecturerSortedProjectsController {
    // Declare ProjectModel object
    private ProjectModel projectModel;
    // Declare AdminLecturerSortedProjectsView object
    private AdminLecturerSortedProjectsView view;

    // Lam Ting Le
    // Constructs a AdminLecturerSortedProjectsController object with parameters
    public AdminLecturerSortedProjectsController(ProjectModel projectModel, AdminLecturerSortedProjectsView view) {
        this.projectModel = projectModel;
        this.view = view;
    }

    // Lam Ting Le
    // Sets listener for button click to return to previous window
    // Sets listener for text field input to filter table data
    public void initController(Admin m) {
        view.getBackButton().addActionListener(e -> {
            view.getFrame().dispose();
            AdminViewProjectReportView v = new AdminViewProjectReportView();
            AdminViewProjectReportController smc = new AdminViewProjectReportController(m, v);
            smc.initController(m);
        });
        view.getFilterTextField().getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter(4);
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter(4);
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter(4);
                    }
                }
        );
    }

    // Lam Ting Le
    // Filter table row with specific column based on the text field input
    private void newFilter(int column) {
        RowFilter<DefaultTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(view.getFilterTextField().getText(), column);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        view.getSorter().setRowFilter(rf);
    }
}
