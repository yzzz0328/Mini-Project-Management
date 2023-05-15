package Controller;

import Model.ContentModel;
import Model.ProjectModel;
import Model.Student;
import View.StudentViewProjectView;

import java.io.IOException;

// Teo Yih Shing
public class StudentViewProjectController {
    private Student sModel;
    private ProjectModel pModel;
    private StudentViewProjectView view;
    private ContentModel contentModel = new ContentModel();

    // Teo Yih Shing
    // parameterized constructor
    public StudentViewProjectController(Student sModel, ProjectModel pModel) throws IOException {
        this.sModel = sModel;
        this.pModel = pModel;
        this.view = new StudentViewProjectView();
        initView();
    }
    
    // Teo Yih Shing
    // this method is used to initialize the details of project in the StudentViewProjectView view
    private void initView() throws IOException {
        this.view.setProjectName(this.pModel.getName());
        this.view.setProjectID(this.pModel.getID());
        this.view.setProjectSpecialization(this.pModel.getSpecialization());
        this.view.setProjectLecturerID(this.pModel.getLecturer_ID());
        this.view.setProjectLecturerName(this.pModel.getLecturer_Name());
        this.view.setProjectStudentID(this.pModel.getStudent_ID());
        this.view.setProjectStudentName(this.pModel.getStudent_Name());
        this.view.setProjectContent(contentModel.displayContent(pModel.getID()));
    }

    // Teo Yih Shing
    // this method is used to call the backActionListener when user click on the "Back" button
    public void initController(StudentViewBoardController svbc, StudentMenuController smc) {
        this.view.getBackButton().addActionListener(e -> {
            try {
                backActionListener(svbc, smc);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    // Teo Yih Shing
    // this method is used to redirect the user go back to the previous screen
    private void backActionListener(StudentViewBoardController svbc, StudentMenuController smc) throws IOException {
        svbc = new StudentViewBoardController(this.sModel);
        svbc.initController(smc);
        this.view.dispose();
    }
}
