package Controller;

import Model.Admin;
import Model.CommentModel;
import Model.ContentModel;
import Model.ProjectModel;
import View.AdminProjectListView;
import View.AdminViewProjectView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

// Lam Ting Le
public class AdminViewProjectController{
    // Declare Admin object
    private Admin adminModel;

    // Declare ProjectModel object
    private ProjectModel projectModel;

    // Declare CommentModel object
    private CommentModel commentModel = new CommentModel();

    // Declare ContentModel object
    private ContentModel contentModel = new ContentModel();

    // Declare AdminViewProjectView object
    private AdminViewProjectView view;

    // Lam Ting Le
    // Constructs a AdminViewProjectController object with parameters
    public AdminViewProjectController(Admin adminModel, ProjectModel projectModel, CommentModel commentModel) throws IOException {
        this.adminModel = adminModel;
        this.projectModel = projectModel;
        this.commentModel = commentModel;
        this.view = new AdminViewProjectView(projectModel.getID());
        view.setProjectName(projectModel.getName());
        view.setProjectID(projectModel.getID());
        view.setProjectSpecialization(projectModel.getSpecialization());
        view.setStudentID(projectModel.getStudent_ID());
        view.setStudentName(projectModel.getStudent_Name());
        view.setLecturerID(projectModel.getLecturer_ID());
        view.setLecturerName(projectModel.getLecturer_Name());
        view.getProjectContent().setText(contentModel.displayContent(projectModel.getID()));
    }

    // Lam Ting Le
    // Sets listener for button click to clear text area
    // Sets listener for button click to add text area's text to comment table
    // Sets listener for button click to return to previous window
    public void initController(AdminProjectListController aplc, AdminMainController amc) {
        view.getClearCommentButton().addActionListener(e -> clearComment());
        view.getAddCommentButton().addActionListener(e -> {
            try {
                addComment();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        view.getBackButton().addActionListener(e -> {
            try {
                backActionListener(aplc, amc);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    // Lam Ting Le
    // Sets text area to null
    private void clearComment() {
        view.commentTextArea.setText(null);
    }

    // Lam Ting Le
    // Method to return to previous window
    private void backActionListener(AdminProjectListController aplc, AdminMainController amc) throws IOException {
        AdminProjectListView v = new AdminProjectListView();
        aplc = new AdminProjectListController(projectModel, v);
        aplc.initController(amc);
        view.getFrame().dispose();
    }

    // Lam Ting Le
    // Method to store comment data to file and add comment to comment table
    // Invoke method to set the current project ID in view
    // Invoke method to set user ID of user commented
    // Invoke method to set name of user commented
    // Invoke method to set comment data time when the comment is added
    // Invoke method to set the comment from the text area
    // If comment text area is empty, display error message
    // Else save the comment data to file and add new row to comment table
    private void addComment() throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        commentModel.setProjectID(view.getProjectID().getText());
        commentModel.setID(adminModel.getName());
        commentModel.setName(adminModel.getUserFullName());
        commentModel.setCommentDateTime(dtf.format(now));
        commentModel.setComment(view.getCommentTextArea().getText());

        if (commentModel.getComment().isEmpty()){
            JOptionPane.showMessageDialog(null, "No comment added. \nPlease try again.",
               "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        else{
            // composition
            ProjectModel p = new ProjectModel(commentModel.retrieveComments());
            ArrayList<CommentModel> commentList = p.getTotalComments();

            commentList.add(new CommentModel(commentModel.getProjectID(), commentModel.getID(), commentModel.getName(),
                    commentModel.getCommentDateTime(), commentModel.getComment()));
            commentModel.saveCommentToFile(commentList);
            view.getDefaultTableModel().addRow(new String[]{commentModel.getID(), commentModel.getName(),
                    commentModel.getCommentDateTime(), commentModel.getComment()});}
    }

}
