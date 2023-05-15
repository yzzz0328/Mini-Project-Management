package Controller;

import Model.ContentModel;
import Model.Lecturer;
import Model.ProjectModel;
import View.LectureUploadProjectView;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

// Teoh Ye Zhian
public class LectureUploadProjectController {
    private Lecturer lModel;
    private ProjectModel pModel;
    private ContentModel cmodel;
    private LectureUploadProjectView view;
    private LectureProjectController lpc;

    // Teoh Ye Zhian
    // parameterized constructor
    public LectureUploadProjectController(Lecturer lModel, ProjectModel pModel, ContentModel cmodel) {
        this.lModel = lModel;
        this.pModel = pModel;
        this.cmodel = cmodel;
        this.view = new LectureUploadProjectView();
        initView();
    }

    // Teoh Ye Zhian
    // An initial view class when user enters the view
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
    // calls addContent method when "Upload" button is pressed and returns lecturer to
    // previous page by clicking the back button.
    public void initController(LectureViewBoardController lvbc, LectureMenuController lmc) {
        try {
            displayContent(view.getProjectID().getText());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.view.getUploadButton().addActionListener(e -> {
            try {
                addContent();
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
    // an action listener method that calls LectureProjectController controller.
    private void backActionListener(LectureProjectController lpc, LectureViewBoardController lvbc,
            LectureMenuController lmc) throws IOException {
        lpc = new LectureProjectController(lModel, pModel, cmodel);
        lpc.initController(lvbc, lmc);
        this.view.dispose();
    }

    // Teoh Ye Zhian
    // this method tries to add the content of the chosen project ID and save
    // it to the content file.
    private void addContent() throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        cmodel.setProjectID(this.view.getProjectID().getText());

        this.cmodel.setLecturerID(this.lModel.getName());
        this.cmodel.setLecturerName(this.lModel.getUserFullName());
        this.cmodel.setupdateDateTime(dtf.format(now));
        this.cmodel.setContent(this.view.getProjectcontent().getText());

        ArrayList<ContentModel> arrayList = cmodel.retrieveContents();

        if (cmodel.getContent().isBlank()) {
            JOptionPane.showMessageDialog(null, "Fill in the necessary fields. \nPlease try again.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } else {
            arrayList.add(new ContentModel(cmodel.getProjectID(), cmodel.getLecturerID(), cmodel.getLecturerName(),
                    cmodel.getupdateDateTime(), cmodel.getContent()));
            cmodel.saveContenttToFile(arrayList);
            view.getdatetime().setText(cmodel.getupdateDateTime());
            view.getContent().setText("<html>" + cmodel.getContent().replaceAll("<","&lt;")
                                        .replaceAll(">", "&gt;").replaceAll("\n", "<br/>") 
                                        + "</html>");
            JOptionPane.showMessageDialog(null, "Content has been uploaded.",
                "Successfully Uploaded", JOptionPane.INFORMATION_MESSAGE);
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



