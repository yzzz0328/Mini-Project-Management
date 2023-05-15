package Controller;

import Model.CommentModel;
import Model.ProjectModel;
import View.AdminProjectListView;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

// Lam Ting Le
public class AdminProjectListController {
    // Declare ProjectModel object
    private ProjectModel projectModel;

    // Declare AdminProjectListView object
    private AdminProjectListView view;

    // Lam Ting Le
    // Constructs a AdminProjectListController object with parameters
    public AdminProjectListController(ProjectModel projectModel, AdminProjectListView view) throws IOException {
        this.projectModel = projectModel;
        this.view = view;
    }

    // Lam Ting Le
    // Sets listener for button click to view project
    // If no project is selected, error message pops up
    // Sets listener for button click to return to previous window
    public void initController(AdminMainController a) {
        view.getViewProjectButton().addActionListener(e -> {
            try {
                projectModel = setProject();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            if (projectModel != null) {
                CommentModel commentModel = new CommentModel();

                AdminViewProjectController avpc;
                try {
                    avpc = new AdminViewProjectController(a.getModel(), projectModel, commentModel);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                avpc.initController(this, a);
                view.getFrame().dispose();
            }
        });
        view.getBackButton().addActionListener(e -> {
            AdminMainController amc = new AdminMainController(a.getModel());
            amc.initController();
            view.getFrame().dispose();
        });
    }

    // Lam Ting Le
    // Method to set data for the selected project
    // Invoke method to initialize project_ID
    // If project_ID is not null, read all the data in the file, "ProjectList.txt"
    // While there is next line, store the data into an array
    // If the project_ID equals to the project ID in the array,
    // create a project model object and store the project data into the object
    // Return the project data selected from the combo box
    private ProjectModel setProject() throws IOException {
        ProjectModel p = new ProjectModel();
        String project_ID = projectID();

        if (project_ID != null) {
            try {
                File projectData = new File("txt/ProjectList.txt");
                Scanner myReader = new Scanner(projectData);
                String line;

                while (myReader.hasNextLine()) {
                    line = myReader.nextLine();
                    String[] splited = line.split("    ");

                    if(project_ID.equals(splited[0])){
                        p = new ProjectModel(splited[0], splited[1], splited[2], splited[3], splited[4], splited[5], splited[6], splited[7]);
                        break;
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
            p = null;

        return p;
    }

    // Lam Ting Le
    // Return project ID of project selected from the combo box
    // If no project is selected, display error message
    private String projectID() {
        String projectID = view.getSelectedProject();

        if (Objects.equals(projectID, " ")) {
            JOptionPane.showMessageDialog(null, "No project has been selected. \nPlease select another one.",
                    "Invalid Selection", JOptionPane.ERROR_MESSAGE);
            projectID = null;
        }
        else {
            String[] items = projectID.split("   ");
            String project_ID_Selected = items[0];
            projectID = project_ID_Selected;
        }

        return projectID;
    }
}
