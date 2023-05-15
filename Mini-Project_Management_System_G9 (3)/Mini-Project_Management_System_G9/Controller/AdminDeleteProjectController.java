package Controller;

import Model.ProjectModel;
import View.AdminDeleteProjectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AdminDeleteProjectController {
    private ProjectModel model;
    private AdminDeleteProjectView view;

    // Chang Siu Hong
    // parameterized constructor
    public AdminDeleteProjectController(ProjectModel m, AdminDeleteProjectView v) throws IOException {
        this.model = m;
        this.view = v;
        initView();
    }

    // Chang Siu Hong
    // an initial view class when user enters the view
    public void initView(){
        view.getProject_Details().setToolTipText(model.getID() + "   " + model.getName());
    }

    // Chang Siu Hong
    // an initial controller with AdminMainController parameter to handle the action
    // whenever certain button is clicked
    // In this method, our programs tries to delete project details whenever delete project
    // button is clicked with several verification processes.
    // When back button is pressed, user will be directed to the previous view page.
    public void initController(AdminMainController c){
        view.getDelete_Project_btn().addActionListener(e -> {
            try {
                deleteProjectDetails();
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
    // this method first looks for the number of line of the selected project in the list
    // and ignores the line while loading the project details into tge "ProjectList.txt"
    // and lastly returns the number of line
    private int saveProjectToFile(String s) throws IOException
    {
        ArrayList<ProjectModel> list = model.retrieveExistingProject();
        int l = findLine(list, s);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l; i++)
            sb.append (list.get(i).toString() + "\n");
        for (int j = l+1; j < list.size(); j++)
            sb.append (list.get(j).toString() + "\n");
        Files.write(Paths.get("txt/ProjectList.txt"), sb.toString().getBytes());

        return l;
    }

    // Chang Siu Hong
    // this method looks for the number of lines of the selected project in the original list
    private int findLine(ArrayList<ProjectModel> list, String s) {
        int foundLineNo = 0;
        boolean found = false;

        while (found == false) {
            if (list.get(foundLineNo).getID().equals(s)) {
                found = true;
            }
            else 
                foundLineNo++;
        }
        return foundLineNo;
    }

    // Chang Siu Hong
    // this method checks for the project ID in the existing project lists and return boolean of 
    // true if te project ID is found else return a boolean of false
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
    // this method first detects the project ID selected and if no project ID is chosen, 
    // error message will be prompted out, else, checking will be performed if the project ID
    // has already been deleted. If not deleted yet, successful message will be popped out and
    // the project will be deleted in the txt file.
    private void deleteProjectDetails() throws IOException {
        String project_delete = view.getProject_Details().getSelectedItem().toString();

        if (project_delete == " ") {
            JOptionPane.showMessageDialog(null, "No project has been selected. \nPlease select another one.",
            "Invalid Selection", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String[] items = project_delete.split("   ");
            String project_ID_Selected = items[0];
            String project_Name_Selected = items[1];

            model.setID(project_ID_Selected);
            model.setName(project_Name_Selected);

            boolean found_Project_ID = checkProjectID(project_ID_Selected);
            
            if (found_Project_ID == false) {
                JOptionPane.showMessageDialog(null, "Project has been deleted. \nPlease select another one.",
                "Invalid Selection", JOptionPane.ERROR_MESSAGE);
            }
            else {
                int l = saveProjectToFile(project_ID_Selected);
                view.getModel().removeRow(l);
                view.getProject_Details().removeItemAt(l+1);
                JOptionPane.showMessageDialog(null, "Project has been deleted.",
                        "Successful Deleted", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
