package Controller;

import Controller.LectureMenuController;
import Model.ContentModel;
import Model.Lecturer;
import Model.ProjectModel;
import View.LectureViewBoardView;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//Thong Kai Chin
public class LectureViewBoardController {
    private ProjectModel model;
    private LectureViewBoardView view;
    private LectureMenuController lmc;
    private ContentModel cmodel;

    //Thong Kai Chin
    //Getting projectModel and LecturerViewBoardView
    public LectureViewBoardController(Lecturer l) throws IOException {
        this.model = new ProjectModel();
        this.view = new LectureViewBoardView(l.getName());
    }

    //Thong Kai Chin
    public LectureViewBoardController(ProjectModel m, Lecturer l) throws IOException {
        this.model = m;
        this.view = new LectureViewBoardView(l.getName());
    }

    //Thong Kai Chin
    public LectureViewBoardController(ProjectModel m, LectureViewBoardView v, Lecturer l) throws IOException {
        this.model = m;
        this.view = v;
    }

    //Thong Kai Chin
    //Call the actionListener for view when lecturer click on the "View" button
    //Call the actionListener for back when lecturer click on the "Back" button
    public void initController(LectureMenuController c) {
        this.view.getViewButton().addActionListener(e -> {
            try {
                viewActionListener(c);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        this.view.getBackButton().addActionListener(e -> backActionListener(c));
    }

    //Thong Kai Chin
    //Bring lecturer to Lecturer Project Screen
    private void viewActionListener(LectureMenuController c) throws IOException {
        this.model = setProject();
        if (this.model != null) {
            ContentModel cmodel = new ContentModel();
            LectureProjectController lvpc = new LectureProjectController(c.getModel(), this.model, cmodel);
            lvpc.initController(this, c);
            this.view.dispose();
        }
    }

    //Thong Kai Chin
    //ActionListener for back bring lecturer back to previous page
    private void backActionListener(LectureMenuController c) {
        c = new LectureMenuController(c.getModel());
        c.initController();
        this.view.dispose();
    }

    //Thong Kai Chin
    //If lecturer did not select any project from the ComboBox, an error message will pop out
    //After lecturer selected the project, it will set the ProjectModel in this class as the project that had been selected
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

    //Thong Kai Chin
    //Save the  model
    public void saveModel(LectureMenuController m) {
        this.lmc = m;
    }

    //Thong Kai Chin
    //Returns the model that provides the data displayed
    public LectureMenuController getModel() {
        return this.lmc;
    }
}

