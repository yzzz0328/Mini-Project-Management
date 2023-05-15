package Controller;

import Model.ProjectModel;
import View.LecturerProjectActivationView;

import java.io.IOException;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

//Thong Kai Chin
public class LecturerProjectActivationController {
    private ProjectModel model;
    private LecturerProjectActivationView view;

    //Thong Kai Chin
    //Constructor
    public LecturerProjectActivationController(ProjectModel m, String lecturer_ID) throws IOException{
        this.model = m;
        this.view = new LecturerProjectActivationView(lecturer_ID);
        initView();
    }

    // An initial view class when user enters the view
    public void initView(){
        view.getProject_Details().setToolTipText(model.getID() + "   " + model.getName());
    }

    //Thong Kai Chin
    // An initial controller with AdminMainController parameter to handle the action
    public void initController(LectureMenuController c){
        
        // ActionListener for JComboBox - Disable & Enable Activate/Deactivate Button
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                // Get user selected project
                String project_selected = view.getProject_Details().getSelectedItem().toString();
        
                if (project_selected == " ") {
                    JOptionPane.showMessageDialog(null, "No project has been selected. \nPlease select another one.",
                    "Invalid Selection", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    String[] items = project_selected.split("   ");
                    String project_ID_Selected = items[0];

                    // Based on project id, find the project status
                    try{
                        String project_selected_status = getProjectStatus(project_ID_Selected).trim();
                        if (project_selected_status.length() == 6){                 //"Active" with 6 characters
                            view.getActivate_Project_btn().setEnabled(false);
                            view.getDeactivate_Project_btn().setEnabled(true);
                        }else if (project_selected_status.length() == 8){            //"Inactive" with 8 characters
                            view.getActivate_Project_btn().setEnabled(true);
                            view.getDeactivate_Project_btn().setEnabled(false);
                        }else{
                            view.getActivate_Project_btn().setEnabled(false);
                            view.getDeactivate_Project_btn().setEnabled(false);
                        }
                    }catch (IOException e1){
                        e1.printStackTrace();
                    }
                }
            }       
        };
        view.getProject_Details().addActionListener(actionListener);

        //Add listener for Activate Button
        // Activate project while Button is clicked
        view.getActivate_Project_btn().addActionListener(e -> {
            try {
                activateProject(model, c);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        //Add listener for Deactivate Button
        // Deactivate project while Button is clicked
        view.getDeactivate_Project_btn().addActionListener(e -> {
            try {
                deactivateProject(model, c);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        //Add listener for Back Button
        //Back to previous page while Button is clicked
        this.view.getBackButton().addActionListener(e -> backActionListener(c));
    }
    //Thong Kai Chin
    // Loop project list and find target project and return its status
    private String getProjectStatus(String p_ID) throws IOException {
        ArrayList<ProjectModel> projectlist = model.retrieveExistingProject();
        for (int i = 0; i < projectlist.size(); i++) {
            if (projectlist.get(i).getID().equals(p_ID.toUpperCase())) {
                return projectlist.get(i).getStatus();
            }
        }
        return "-";
    }
    //Thong Kai Chin
    // Pop out error message if user did not select project ID
    private void activateProject(ProjectModel m, LectureMenuController c) throws IOException{
        String project_selected = view.getProject_Details().getSelectedItem().toString();

        if (project_selected == " ") {
            JOptionPane.showMessageDialog(null, "No project has been selected. \nPlease select another one.",
            "Invalid Selection", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String[] items = project_selected.split("   ");
            String project_ID_Selected = items[0];

            ArrayList<ProjectModel> projectlist = model.retrieveExistingProject();
            for (int i = 0; i < projectlist.size(); i++) {
                if (projectlist.get(i).getID().equals(project_ID_Selected.toUpperCase())) {
                    projectlist.get(i).setStatus("Active");
                    model.saveProjectToFile(projectlist); //save project to file , update text file , automatic after click active
                    JOptionPane.showMessageDialog(null, "The project is successfully activated.", //Message pop out succesffully activated project
                "Invalid Selection", JOptionPane.INFORMATION_MESSAGE);
                    view.dispose();
                    view = new LecturerProjectActivationView(c.getModel().getName());
                    initController(c);
                    break;
                }
            }	
        }
    }

    //Thong Kai Chin
    // tries to deactivate the project when project ID is found
    private void deactivateProject(ProjectModel m, LectureMenuController c) throws IOException{
        String project_selected = view.getProject_Details().getSelectedItem().toString();

        if (project_selected == " ") {
            JOptionPane.showMessageDialog(null, "No project has been selected. \nPlease select another one.",
            "Invalid Selection", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String[] items = project_selected.split("   ");
            String project_ID_Selected = items[0];

            ArrayList<ProjectModel> projectlist = model.retrieveExistingProject();
            for (int i = 0; i < projectlist.size(); i++) {
                if (projectlist.get(i).getID().equals(project_ID_Selected.toUpperCase())) {
                    projectlist.get(i).setStatus("Inactive");
                    model.saveProjectToFile(projectlist); //save project to file , update text file , automatic after click inactive
                    JOptionPane.showMessageDialog(null, "The project is successfully deactivated.", //Message pop out succesffully deactivated project
                "Invalid Selection", JOptionPane.INFORMATION_MESSAGE);
                    view.dispose();
                    view = new LecturerProjectActivationView(c.getModel().getName());
                    initController(c);
                    break;
                }
            }	
        }
    }
    //button back to Menu Page
    private void backActionListener(LectureMenuController c) {
        this.view.dispose();
        LectureMenuController lmc = new LectureMenuController(c.getModel());
        lmc.initController();
    }
}
//Thong Kai Chin
