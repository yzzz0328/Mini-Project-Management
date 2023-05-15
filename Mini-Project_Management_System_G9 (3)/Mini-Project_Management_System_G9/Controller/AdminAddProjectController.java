package Controller;

import Model.Lecturer;
import Model.ProjectModel;
import View.AdminAddProjectView;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AdminAddProjectController {
    // declare a projectmodel object
    private ProjectModel model;
    // declare a lecturer model object
    private Lecturer lmodel = new Lecturer();
    // declare a AdminAddProjectView view
    private AdminAddProjectView view;

    // Chang Siu Hong
    // parameterized constructor
    public AdminAddProjectController(ProjectModel m, AdminAddProjectView v) {
        this.model = m;
        this.view = v;
        initView();
    }

    // Chang Siu Hong
    // an initial view class when user enters the view
    public void initView() {
        view.getProject_name().setText(model.getName());
        view.getProject_ID().setText(model.getID());
        view.getProject_Specialization().setToolTipText(model.getSpecialization());
        view.getProject_lecturer_ID().setText(model.getLecturer_ID());
    }

    // Chang Siu Hong
    // an initial controller with AdminMainController parameter to handle the action
    // whenever certain button is clicked
    // In this method, our programs tries to save project details whenever create project
    // button is clicked with several verification processes.
    // When back button is pressed, user will be directed to the previous view page.
    public void initController(AdminMainController c) {
        view.getCreate_Project_btn().addActionListener(e -> {
            try {
                saveProjectDetails();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        view.getBackButton().addActionListener(e -> {
            view.dispose();
            AdminMainController smc = new AdminMainController(c.getModel());
            smc.initController();
        });
    }

    // Chang Siu Hong
    // to check if project name already exists in "Project.txt" file, if the project name
    // is used, a boolean of found == true is returned, else return found = false
    private boolean checkProjectName(String nm) throws IOException {
        ArrayList<ProjectModel> projectlist = model.retrieveExistingProject();
        boolean found = false;
        for (int i = 0; i < projectlist.size(); i++) {
            if (projectlist.get(i).getName().toUpperCase().equals(nm.toUpperCase())) {
                found = true;
                break;
            }
        }
        return found;
    }

    // Chang Siu Hong
    // to check if project ID already exists in "Project.txt" file, if the project ID
    // is used, a boolean of found == true is returned, else return found = false
    private boolean checkProjectID(String p_ID) throws IOException {
        ArrayList<ProjectModel> projectlist = model.retrieveExistingProject();
        boolean found = false;
        for (int i = 0; i < projectlist.size(); i++) {
            if (projectlist.get(i).getID().equals(p_ID.toUpperCase())) {
                found = true;
                break;
            }
        }
        return found;
    }

    // Chang Siu Hong
    // to check if lecturer ID exists in "Lecturer.txt" file, if the lecturer ID
    // is found, a boolean of found == true is returned, else return found = false
    private boolean checkProjectLecturer(String l_ID) throws IOException {
        ArrayList<Lecturer> lecturerlist = lmodel.retrieveLecturerList();
        boolean found = false;
        for (int i = 0; i < lecturerlist.size(); i++) {
            if (lecturerlist.get(i).getName().equals(l_ID.toUpperCase())) {
                found = true;
                break;
            }
        }
        return found;
    }
    
    // Chang Siu Hong
    // to load the lecturer details into arraylist of lecturer and loop to get
    // the name of the selected lecturer ID. If found, return the name of the lecturer
    private String getLecturerName (String lec_ID) throws IOException{
        String lec_Name = null;
        ArrayList<Lecturer> lecturer = lmodel.retrieveLecturerList();
        for (int j = 0; j < lecturer.size(); j++ ) {
            if (lecturer.get(j).getName().equals(lec_ID)) {
                lec_Name = lecturer.get(j).getUserFullName();
                return lec_Name;
            }
        }
        return lec_Name;
    }

    // Chang Siu Hong
    // to save the details entered buy the users and to perform several validations to
    // ensure user inputs do not contain any errors such as nothing is inputted, project ID
    // is already used, project name is already used, lecturer not found, no specialization is
    // chosen. If any errors is deteced, error message will be popped out, else, the newly entered
    // project details without errors will be added to the arraylist and saved into the 
    // "ProjectList.txt" file.
    private void saveProjectDetails() throws IOException {
        model.setName(view.getProject_name().getText());
        model.setID(view.getProject_ID().getText());  
        model.setSpecialization(view.getProject_Specialization().getSelectedItem().toString());
        model.setLecturer_ID(view.getProject_lecturer_ID().getText());
        model.setLecturer_Name(getLecturerName(model.getLecturer_ID()));
        model.setStudent_ID("null");
        model.setStudent_Name("null");
        model.setStatus("Inactive");

        ArrayList<ProjectModel> list = model.retrieveExistingProject();
        boolean found_ProjectName = checkProjectName(model.getName());
        boolean found_ProjectID = checkProjectID(model.getID());
        boolean found_ProjectLecturer = checkProjectLecturer(model.getLecturer_ID());

        if (model.getName().isBlank() || model.getID().isBlank() || model.getLecturer_ID().isBlank()) {
            JOptionPane.showMessageDialog(null, "Fill in the necessary fields. \nPlease try again.",
               "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } else if (found_ProjectName == true) {
            JOptionPane.showMessageDialog(null, "Project name has been taken. \nPlease try again.",
               "Invalid Project Name", JOptionPane.ERROR_MESSAGE);
        } else if (found_ProjectID == true) {
            JOptionPane.showMessageDialog(null, "Project ID has been taken. \nPlease try again.",
               "Invalid Project ID", JOptionPane.ERROR_MESSAGE);
        } else if (model.getSpecialization() == " ") {
            JOptionPane.showMessageDialog(null, "Please choose a valid specialization!",
               "Invalid Specialization", JOptionPane.ERROR_MESSAGE);
        } else if (found_ProjectLecturer == false ) {
            JOptionPane.showMessageDialog(null, "Please enter a valid lecturer ID!",
               "Invalid Lecturer ID", JOptionPane.ERROR_MESSAGE);
        } else {
            list.add(new ProjectModel(model.getID().toUpperCase(), model.getName(), model.getSpecialization(), model.getLecturer_ID().toUpperCase(), model.getLecturer_Name(), model.getStudent_ID(), model.getStudent_Name(), model.getStatus()));
            model.saveProjectToFile(list);
            JOptionPane.showMessageDialog(null, "Project details has been recorded!",
               "Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
