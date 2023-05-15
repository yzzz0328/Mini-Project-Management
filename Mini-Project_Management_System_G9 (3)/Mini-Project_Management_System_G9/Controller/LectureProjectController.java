package Controller;

import Model.ContentModel;
import Model.Lecturer;
import Model.ProjectModel;
import View.LectureProjectView;

import java.io.IOException;
import java.util.ArrayList;

// Teoh Ye Zhian
public class LectureProjectController {
    private Lecturer lModel;
    private ProjectModel pModel;
    private ContentModel cmodel;
    private LectureProjectView view;
    private LectureViewBoardController lvbc;
    private LectureMenuController lmc;

    // Teoh Ye Zhian
    // parameterized constructor
    public LectureProjectController(Lecturer lModel, ProjectModel pModel, ContentModel cmodel) throws IOException {
        this.lModel = lModel;
        this.pModel = pModel;
        this.cmodel = cmodel;
        this.view = new LectureProjectView(pModel.getID());
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
    // direct lecturer to the modification page when "Modify" button is pressed, directs 
    // lecturer to upload content page when "Upload" button is pressed and returns lecturer to
    // previous page by clicking the back button.
    public void initController(LectureViewBoardController lvbc, LectureMenuController lmc) {
        try {
            displayContent(view.getProjectID().getText());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        this.view.getUploadButton().addActionListener(e -> {
            try {
                this.lmc=lmc;
                UploadActionListener(lvbc);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        this.view.getModifyButton().addActionListener(e -> {
            try {
                this.lmc=lmc;
                ModifyActionListener(lvbc);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        this.view.getBackButton().addActionListener(e -> {
            try {
                backActionListener(lvbc, lmc);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    // Teoh Ye Zhian
    // an action listener method that calls LectureUploadProjectController controller.
    private void UploadActionListener(LectureViewBoardController c) throws IOException {
        LectureUploadProjectController lupc = new LectureUploadProjectController(lModel, pModel, cmodel);
        lupc.initController(c,lmc);
        this.view.dispose();
    }

    // Teoh Ye Zhian
    // an action listener method that calls LectureModifyController controller.
    private void ModifyActionListener(LectureViewBoardController c) throws IOException {
        LectureModifyController lupc = new LectureModifyController(lModel, pModel, cmodel);
        lupc.initController(c,lmc);
        this.view.dispose();
    }

    // Teoh Ye Zhian
    // an action listener method that calls LectureViewBoardController controller.
    private void backActionListener(LectureViewBoardController lvbc, LectureMenuController lmc) throws IOException {
        lvbc = new LectureViewBoardController(this.lModel);
        lvbc.initController(lmc);
        this.view.dispose();
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
