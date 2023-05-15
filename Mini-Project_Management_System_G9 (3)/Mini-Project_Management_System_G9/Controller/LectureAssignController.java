package Controller;

import Controller.LectureMenuController;
import Model.ProjectModel;
import Model.Student;
import View.LectureAssignView;

import java.io.IOException;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.lang.model.util.ElementScanner14;
import javax.swing.JOptionPane;

// Teoh Ye Zhian
public class LectureAssignController {
    private ProjectModel model;
    private Student sModel = new Student();
    private LectureAssignView view;
    private LectureViewBoardController lvbc;
    private LectureMenuController lmc;

    // Teoh Ye Zhian
    // parameterized constructor
    public LectureAssignController(ProjectModel m, String lecturer_ID) throws IOException {
        this.model = m;
        this.view = new LectureAssignView(lecturer_ID);
        initView();
    }

    // Teoh Ye Zhian
    // an initial view class when user enters the view
    public void initView() {
        view.getProject_Details().setToolTipText(model.getID() + "   " + model.getName());
    }

    // Teoh Ye Zhian
    // an initial controller with LectureMenuController parameter to handle the action 
    // whenever certain button is clicked.
    // In this method, our programs tries to set the assign and unassign status once the
    // project is selected. If lecturer chosses back button, our programs tries to direct 
    // lecturer back to previous page by clicking the back button.
    public void initController(LectureMenuController c) {
        // ActionListener for JComboBox - Disable & Enable Activate/Deactivate Button
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String project_selected = view.getProject_Details().getSelectedItem().toString();

                if (project_selected == " ") {
                    JOptionPane.showMessageDialog(null, "No project has been selected. \nPlease select another one.",
                            "Invalid Selection", JOptionPane.ERROR_MESSAGE);
                } else {
                    String[] items = project_selected.split("   ");
                    String project_ID_Selected = items[0];

                    String project_student;
                    try {
                        project_student = getProjectStudent(project_ID_Selected);

                        if (project_student == "null") {
                            view.getAssigned_Project_btn().setEnabled(true);
                            view.getUnassigned_Project_btn().setEnabled(false);
                        } else if (project_student.length() == 6) {
                            view.getAssigned_Project_btn().setEnabled(false);
                            view.getUnassigned_Project_btn().setEnabled(true);
                        } else {
                            view.getAssigned_Project_btn().setEnabled(true);
                            view.getUnassigned_Project_btn().setEnabled(true);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        view.getProject_Details().addActionListener(actionListener);

        view.getAssigned_Project_btn().addActionListener(e -> {
            try {
                String ps = this.view.getProject_Details().getSelectedItem().toString();
                if (ps == " ") {
                    JOptionPane.showMessageDialog(null, "No project has been selected. \nPlease select another one.",
                            "Invalid Selection", JOptionPane.ERROR_MESSAGE);
                } else {
                    this.model = this.view.getSelectedProject();
                    AssignedListener(c, c.getModel().getName());
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        view.getUnassigned_Project_btn().addActionListener(e -> {
            try {
                this.lmc = c;
                unassignedProject(model, c.getModel().getName());
            } catch (IOException e1) {
                e1.printStackTrace();

            }
        });

        view.getBackButton().addActionListener(e -> backActionListener(c));
    }

    // Teoh Ye Zhian
    // an action listener method that calls the controller of the previous page
    // and display lecturer the previous page.
    private void backActionListener(LectureMenuController c) {
        LectureMenuController lmc = new LectureMenuController(c.getModel());
        lmc.initController();
        this.view.dispose();
    }

    // Teoh Ye Zhian
    // an action listener method that calls the LectureAssignStudentController and the initial controller.
    public void AssignedListener(LectureMenuController c, String lecturer_ID) throws IOException {
        this.view.dispose();
        LectureAssignStudentController lass = new LectureAssignStudentController(this.model, this.view);
        lass.initController(lvbc, c, this, lecturer_ID);
    }

    // Teoh Ye Zhian
    // an action listener method that retrieves list of project model and return the
    // student ID of project that the student is assigned to.
    private String getProjectStudent(String p_ID) throws IOException {
        ArrayList<ProjectModel> projectlist = model.retrieveExistingProject();
        String s_ID = "null";

        for (int i = 0; i < projectlist.size(); i++) {
            if (projectlist.get(i).getID().equals(p_ID.toUpperCase())) {
                s_ID = projectlist.get(i).getStudent_ID();
            }
        }
        return s_ID;
    }

    // Teoh Ye Zhian
    // an action listener method that retrieves list of project model and set the student ID
    // of the selected project to null and resave the project ID of the stduent in student list
    // to null.
    private void unassignedProject(ProjectModel m, String lecturer_ID) throws IOException {
        boolean studentHasSave = false;
        boolean projectHasSave = false;

        String ps = this.view.getProject_Details().getSelectedItem().toString();
        String[] items = ps.split("   ");
        String sproject_ID = items[0];

        if (ps == " ") {
            JOptionPane.showMessageDialog(null, "No project has been selected. \nPlease select another one.",
                    "Invalid Selection", JOptionPane.ERROR_MESSAGE); }
        else {
            ArrayList<ProjectModel> projectlist = this.model.retrieveExistingProject();
            ArrayList<Student> studentlist = this.sModel.retrieveStudentList();

            for (int i = 0; i < projectlist.size(); i++) {
                if (projectlist.get(i).getID().equals(sproject_ID)) {
                    if (projectlist.get(i).getStudent_ID().equals("null")) {
                        JOptionPane.showMessageDialog(null, "Project has been unassigned. \nPlease select another one.",
                        "Invalid Selection", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    else {
                        projectlist.get(i).setStudent_ID("null");
                        projectlist.get(i).setStudent_Name("null");
                        projectHasSave = true;
                        break;
                    }
                } else 
                    projectHasSave = false;
            }

            this.model.saveProjectToFile(projectlist);

            for (int j = 0; j < studentlist.size(); j++) {
                if (studentlist.get(j).getProject_ID().equals(sproject_ID)) {
                    studentlist.get(j).setProject_ID("null");
                    studentHasSave = true;
                    break;
                } else {
                    studentHasSave = false;
                }
            }
            this.sModel.saveStudentToFile(studentlist);

            if (studentHasSave == true && projectHasSave == true) {
                JOptionPane.showMessageDialog(null, "The project is unassigned successful.",
                        "Invalid Selection", JOptionPane.INFORMATION_MESSAGE);
                view.dispose();
                view = new LectureAssignView(lecturer_ID);
                initController(lmc);
            }
        }
    }

    // Teoh Ye Zhian
    // this method returns the model.
    public ProjectModel getModel() {
        return this.model;
    }
}
