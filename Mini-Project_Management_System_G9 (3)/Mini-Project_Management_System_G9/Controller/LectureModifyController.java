package Controller;

import Controller.LectureMenuController;
import Controller.LectureProjectController;
import Controller.LectureViewBoardController;
import Model.ContentModel;
import Model.Lecturer;
import Model.ProjectModel;
import View.LectureModifyView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

// Teoh Ye Zhian
public class LectureModifyController {
    private Lecturer lModel;
    private ProjectModel pModel;
    private ContentModel cmodel;
    private LectureModifyView view;
    private LectureProjectController lpc;

    // Teoh Ye Zhian
    // parameterized constructor
    public LectureModifyController(Lecturer lModel, ProjectModel pModel, ContentModel cmodel) {
        this.lModel = lModel;
        this.pModel = pModel;
        this.cmodel = cmodel;
        this.view = new LectureModifyView();
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
    // an initial controller to handle the action whenever certain button is clicked.
    // In this method, our programs tries to display the content of the project and 
    // direct lecturer to modify page when "Modify" button is pressed and returns lecturer to
    // previous page by clicking the back button.
    public void initController(LectureViewBoardController lvbc, LectureMenuController lmc) {
        try {
            displayContent(view.getProjectID().getText());
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        this.view.getModifyButton().addActionListener(e -> {
            try {
                modifyContent(view.getProjectID().getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        this.view.getBackButton().addActionListener(e -> {
            try {
                backActionListener(lpc, lvbc, lmc);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    // Teoh Ye Zhian
    // an action listener method that calls the controller of the previous page
    // and display lecturer the previous page.
    private void backActionListener(LectureProjectController lpc, LectureViewBoardController lvbc,
                                    LectureMenuController lmc) throws IOException {
        lpc = new LectureProjectController(lModel, pModel, cmodel);
        lpc.initController(lvbc, lmc);
        this.view.dispose();
    }

    // Teoh Ye Zhian
    // this method finds and returns the number of line of the selected project ID 
    // from the content list.
    private int findLine(String projectID) throws IOException {
        ArrayList<ContentModel> arrayList = cmodel.retrieveContents();
        int j = 0;

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getProjectID().equals(projectID)) {
                j = i;
            }
        }
        return j;
    }
    
    // Teoh Ye Zhian
    // this method tries to modify the last updated content existing in the content file.
    private void modifyContent(String projectID) throws IOException {
        ArrayList<ContentModel> arrayList = cmodel.retrieveContents();
        ArrayList<ContentModel> arrayList2 = new ArrayList<ContentModel>();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getProjectID().equals(projectID)) {
                arrayList2.add(arrayList.get(i));
            }
        }

        if (arrayList2.size() == 0) {
            JOptionPane.showMessageDialog(null, "No content can be modified. \nPlease try again.",
                "Invalid", JOptionPane.ERROR_MESSAGE);
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            int l = findLine(projectID);
            String newcontent = view.getProjectcontent().getText();
            String newDateTime = dtf.format(now);

            arrayList.get(l).setContent(newcontent);
            arrayList.get(l).setupdateDateTime(newDateTime);
            cmodel.saveContenttToFile(arrayList);

            view.getdatetime().setText(newDateTime);
            view.getContent().setText("<html>" + newcontent.replaceAll("<","&lt;")
                                        .replaceAll(">", "&gt;").replaceAll("\n", "<br/>") 
                                        + "</html>");
            JOptionPane.showMessageDialog(null, "Content has been modified.",
                "Successfully Modified", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Teoh Ye Zhian
    // this method tries to display the content and set the content on the screen.
    public void displayContent(String projectID) throws IOException {
        ArrayList<ContentModel> arrayList = cmodel.retrieveContents();
        ArrayList<ContentModel> arrayList2 = new ArrayList<ContentModel>();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getProjectID().equals(projectID))
                arrayList2.add(arrayList.get(i));
        }
        if (arrayList2.size() != 0) {
            int lastIdx = arrayList2.size() - 1;
            String lastcontentdt = arrayList2.get(lastIdx).getupdateDateTime();
            String lastcontent = arrayList2.get(lastIdx).getContent();

            view.getdatetime().setText(lastcontentdt);
            view.getContent().setText("<html>" + lastcontent.replaceAll("<","&lt;")
                                        .replaceAll(">", "&gt;").replaceAll("\n", "<br/>") 
                                        + "</html>");
        }
    }
}
