package Controller;

import Model.ProjectModel;
import Model.Student;
import View.StudentViewBoardView;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

// Teo Yih Shing
public class StudentViewBoardController {
    private ProjectModel model;
    private StudentViewBoardView view;

    // Teo Yih Shing
    // parameterized constructor
    public StudentViewBoardController(Student s) throws IOException {
        this.model = new ProjectModel();
        this.view = new StudentViewBoardView(s.getSpecialization());
    }

    // Teo Yih Shing
    // this method is used to call the viewActionListener when user click on the "View" button and 
    // call the backActionListener when user click on the "Back" button
    public void initController(StudentMenuController c) {
        this.view.getViewButton().addActionListener(e -> {
            try {
                viewActionListener(c);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        this.view.getBackButton().addActionListener(e -> backActionListener(c));
    }

    // Teo Yih Shing
    // this method is used to redirect user to the "Student View Project Screen"
    private void viewActionListener(StudentMenuController c) throws IOException {
        this.model = setProject();
        if (this.model != null) {
            StudentViewProjectController svpc = new StudentViewProjectController(c.getModel(), this.model);
            svpc.initController(this, c);
            this.view.dispose();
        }
    }

    // Teo Yih Shing
    // this method is used to redirect the user go back to the previous screen
    private void backActionListener(StudentMenuController c) {
        c = new StudentMenuController(c.getModel());
        c.initController();
        this.view.dispose();
    }

    // Teo Yih Shing
    // this method is used to check the JComboBox has been selected or not, 
    // if yes, it will set the Project model in this class as the project that had been selected
    // if no, it will show a error message dialog to the user
    private ProjectModel setProject() throws IOException {
        ProjectModel p = new ProjectModel();
        String project_ID_and_Name = this.view.getSelectedProject();

        if (project_ID_and_Name == " ") {
            JOptionPane.showMessageDialog(null, "No project has been selected. \nPlease select another one.",
            "Invalid Selection", JOptionPane.ERROR_MESSAGE);
            p = null;
        }
        else {
            String[] items = project_ID_and_Name.split("   ");
            String project_ID_Selected = items[0];
            ArrayList<ProjectModel> plist = p.retrieveExistingProject();

            for (int i = 0; i < plist.size(); i++) {
                if( plist.get(i).getID().equals(project_ID_Selected)){
                    ProjectModel temp = new ProjectModel(plist.get(i).getID(), plist.get(i).getName(), 
                                                         plist.get(i).getSpecialization(), plist.get(i).getLecturer_ID(), 
                                                         plist.get(i).getLecturer_Name(), plist.get(i).getStudent_ID(), 
                                                         plist.get(i).getStudent_Name(), plist.get(i).getStatus());
                    p = temp;
                    break;
                }
            }
        }
        return p;
    }
}
