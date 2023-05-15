package Controller;

import Model.ProjectModel;
import Model.Student;
import View.LectureAssignStudentView;
import View.LectureAssignView;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

// Teoh Ye Zhian
public class LectureAssignStudentController {
    private ProjectModel pModel;
    private Student sModel = new Student();
    private LectureAssignStudentView view;
    private LectureAssignView lav;

    // Teoh Ye Zhian
    // parameterized constructor
    public LectureAssignStudentController(ProjectModel pModel, LectureAssignView lav) throws IOException {
        this.pModel = pModel;
        this.lav = lav;
        this.view = new LectureAssignStudentView(pModel.getSpecialization());
        initView();
    }

    // Teoh Ye Zhian
    // an initial view class when user enters the view
    private void initView() {
        this.view.setProjectLecturerID(this.pModel.getLecturer_ID());
        this.view.setProjectLecturerName(this.pModel.getLecturer_Name());
        this.view.setProjectName(this.pModel.getName());
        this.view.setProjectID(this.pModel.getID());
        this.view.setProjectSpecialization(this.pModel.getSpecialization());
        this.view.setProjectStudentID(this.pModel.getStudent_ID());
        this.view.setProjectStudentName(this.pModel.getStudent_Name());
    }

    // Teoh Ye Zhian
    // an initial controller with LectureViewBoardController, LectureMenuController,
    // LectureAssignController, and Lecturer_ID parameters to handle the action
    // whenever certain button is clicked.
    // In this method, our programs tries to direct lecturer back to previous page
    // by clicking the back button and assigns stduent to the selected project
    // by clicking the assign button.
    public void initController(LectureViewBoardController lvbc, LectureMenuController lmc, LectureAssignController c,
            String Lecturer_ID) {

        this.view.getAssignButton().addActionListener(e -> {
            try {
                String ps = this.view.getStudent_Details().getSelectedItem().toString();
                if (ps == " ") {
                    JOptionPane.showMessageDialog(null, "No student has been selected. \nPlease select another one.",
                            "Invalid Selection", JOptionPane.ERROR_MESSAGE);
                } else
                    assignProject(pModel);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        this.view.getBackButton().addActionListener(e -> {
            try {
                backActionListener(lmc, c, Lecturer_ID);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    // Teoh Ye Zhian
    // an action listener method that calls the controller of the previous page
    // and display lecturer the previous page.
    private void backActionListener(LectureMenuController lmc, LectureAssignController c, String Lecturer_ID)
            throws IOException {
        LectureAssignController lac = new LectureAssignController(c.getModel(), Lecturer_ID);
        lac.initController(lmc);
        this.view.dispose();
    }

    // Teoh Ye Zhian
    // an action listener method that retrieves list of project model and set the
    // student ID
    // of the selected project and resave the project ID of the stduent in student
    // list.
    private void assignProject(ProjectModel m) throws IOException {
        boolean studentHasSave = false;
        boolean projectHasSave = false;

        ProjectModel project_selected = this.lav.getSelectedProject();
        Student student_selected = this.view.getSelectedStudent();

        ArrayList<Student> studentlist = sModel.retrieveStudentList();
        ArrayList<ProjectModel> projectlist = pModel.retrieveExistingProject();

        for (int i = 0; i < studentlist.size(); i++) {
            if (studentlist.get(i).getName().equals(student_selected.getName())) {
                studentlist.get(i).setProject_ID(project_selected.getID());

                sModel.saveStudentToFile(studentlist);
                studentHasSave = true;
                break;
            } else {
                studentHasSave = false;
            }
        }

        for (int i = 0; i < projectlist.size(); i++) {
            if (projectlist.get(i).getName().equals(project_selected.getName())) {
                projectlist.get(i).setStudent_ID(student_selected.getName());
                projectlist.get(i).setStudent_Name(student_selected.getUserFullName());

                pModel.saveProjectToFile(projectlist);
                projectHasSave = true;
                break;
            } else {
                projectHasSave = false;
            }
        }

        if (studentHasSave == true && projectHasSave == true) {
            JOptionPane.showMessageDialog(null, "Project has been assigned.", "Successful Assignation",
                    JOptionPane.INFORMATION_MESSAGE);
            this.view.setProjectStudentID(student_selected.getName());
            this.view.setProjectStudentName(student_selected.getUserFullName());
        }
    }
}
