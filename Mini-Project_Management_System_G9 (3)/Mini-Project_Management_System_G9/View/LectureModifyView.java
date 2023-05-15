package View;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

// Teoh Ye Zhian
public class LectureModifyView extends JFrame {

    private Font font4;

    JLabel title = new JLabel("Project");
    JLabel lecturerID = new JLabel("Lecturer ID:");
    JLabel projectLecturerID = new JLabel();
    JLabel lecturerName = new JLabel("Lecturer Name:");
    JLabel projectLecturerName = new JLabel();
    JLabel projectID = new JLabel("Project ID:");
    JLabel studentProjectID = new JLabel();
    JLabel specialization = new JLabel("Project Specialization:");
    JLabel projectSpecialization = new JLabel();
    JLabel studentID = new JLabel("Student ID:");
    JLabel projectStudentID = new JLabel();
    JLabel studentName = new JLabel("Student Name:");
    JLabel projectStudentName = new JLabel();
    JLabel content = new JLabel("Project Content");
    JLabel datetime = new JLabel("");
    JLabel projectContent = new JLabel();
    JLabel modifytitle = new JLabel("Modify Project Content");
    JTextArea project_content = new JTextArea();

    JButton modify = new JButton("Modify");
    JButton back = new JButton("Back");

    JPanel titlePanel = new JPanel();
    JPanel projectDetails = new JPanel(new GridLayout(3, 4));
    JPanel pdPanel = new JPanel();
    JPanel contentPanel = new JPanel();
    JPanel datetimePanel = new JPanel();
    JScrollPane projectContentSPanel = new JScrollPane(projectContent);
    JPanel modifycontentPanel = new JPanel();
    JPanel backPanel = new JPanel();
    JPanel modifyPanel = new JPanel();
    
    // Teoh Ye Zhian
    // default constructor to store the view screen
    public LectureModifyView() {
        super("MMU Online Mini-project Management System");

        font4 = new Font("Serif", Font.PLAIN, 12);

        this.title.setFont(new Font("Serif", Font.PLAIN, 40));
        this.titlePanel.setPreferredSize(new Dimension(800, 60));
        this.titlePanel.add(title);

        this.lecturerID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectLecturerID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.lecturerName.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectLecturerName.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.studentProjectID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.specialization.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectSpecialization.setFont(new Font("Serif", Font.PLAIN, 15));
        this.studentID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectStudentID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.studentName.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectStudentName.setFont(new Font("Serif", Font.PLAIN, 15));

        this.projectDetails.setPreferredSize(new Dimension(700, 100));
        this.projectDetails.add(lecturerID);
        this.projectDetails.add(projectLecturerID);
        this.projectDetails.add(lecturerName);
        this.projectDetails.add(projectLecturerName);
        this.projectDetails.add(projectID);
        this.projectDetails.add(studentProjectID);
        this.projectDetails.add(specialization);
        this.projectDetails.add(projectSpecialization);
        this.projectDetails.add(studentID);
        this.projectDetails.add(projectStudentID);
        this.projectDetails.add(studentName);
        this.projectDetails.add(projectStudentName);
        this.pdPanel.add(projectDetails);

        this.content.setFont(new Font("Serif", Font.BOLD, 30));
        this.contentPanel.setPreferredSize(new Dimension(800, 40));
        this.contentPanel.add(content);

        this.projectContent.setFont(new Font("Serif", Font.PLAIN, 10));
        this.projectContentSPanel.setPreferredSize(new Dimension(700, 150));
        
        this.datetime.setFont(font4);
        this.datetimePanel.setPreferredSize(new Dimension(800, 40));
        this.datetimePanel.add(datetime);

        modifytitle.setFont(new Font("Serif", Font.PLAIN, 30));

        GroupLayout viewBoardPane = new GroupLayout(getContentPane());
        getContentPane().setLayout(viewBoardPane);
        viewBoardPane.setAutoCreateGaps(true);
        viewBoardPane.setAutoCreateContainerGaps(true);

        viewBoardPane.setHorizontalGroup(viewBoardPane.createParallelGroup(Alignment.CENTER)
                .addComponent(titlePanel)
                .addComponent(pdPanel)
                .addComponent(contentPanel)
                .addComponent(datetimePanel)
                .addComponent(projectContentSPanel)
                .addComponent(modifytitle)
                .addComponent(project_content)
                .addComponent(modifycontentPanel)
                .addGroup(viewBoardPane.createParallelGroup(Alignment.CENTER)
                        .addGroup(viewBoardPane.createSequentialGroup()
                                .addComponent(back)
                                .addComponent(modify))));

        viewBoardPane.setVerticalGroup(viewBoardPane.createSequentialGroup()
                .addComponent(titlePanel)
                .addComponent(pdPanel)
                .addComponent(contentPanel)
                .addComponent(datetimePanel)
                .addComponent(projectContentSPanel)
                .addComponent(modifytitle)
                .addComponent(project_content)
                .addComponent(modifycontentPanel)
                .addGroup(viewBoardPane.createSequentialGroup()
                        .addGroup(viewBoardPane
                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(back)
                                .addComponent(modify))));

        this.setSize(800, 600);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    // Teoh Ye Zhian
    // this method sets the project name
    public void setProjectName(String projectName) {
        this.title.setText(projectName);
    }

    // Teoh Ye Zhian
    // this method sets the project ID
    public void setProjectID(String projectID) {
        this.studentProjectID.setText(projectID);
    }

    // Teoh Ye Zhian
    // this method sets the project name
    public JLabel getProjectID() {
        return studentProjectID;
    }

    // Teoh Ye Zhian
    // this method sets the project specialization
    public void setProjectSpecialization(String specialization) {
        this.projectSpecialization.setText(specialization);
    }

    // Teoh Ye Zhian
    // this method sets the lecturer ID of the project
    public void setProjectLecturerID(String id) {
        this.projectLecturerID.setText(id);
    }

    // Teoh Ye Zhian
    // this method sets the lecturer name of the project
    public void setProjectLecturerName(String name) {
        this.projectLecturerName.setText(name);
    }

    // Teoh Ye Zhian
    // this method sets the student ID of the project
    public void setProjectStudentID(String id) {
        this.projectStudentID.setText(id);
    }

    // Teoh Ye Zhian
    // this method sets the student name of the project
    public void setProjectStudentName(String name) {
        this.projectStudentName.setText(name);
    }

    // Teoh Ye Zhian
    // this method returns the content of the project
    public JLabel getContent(){
        return projectContent;
    }

    // Teoh Ye Zhian
    // this method gets the date and time of the project
    public JLabel getdatetime(){
        return datetime;
    }

    // Teoh Ye Zhian
    // this method gets the project content
    public JTextArea getProjectcontent() {
        return project_content;
    }

    // Teoh Ye Zhian
    // this method gets the back button
    public JButton getBackButton() {
        return this.back;
    }

    // Teoh Ye Zhian
    // this method gets the modify button
    public JButton getModifyButton() {
        return this.modify;
    }

}

