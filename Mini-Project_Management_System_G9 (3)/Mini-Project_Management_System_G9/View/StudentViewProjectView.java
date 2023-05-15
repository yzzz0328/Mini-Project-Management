package View;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;

// Teo Yih Shing
public class StudentViewProjectView extends JFrame{
    private JLabel title = new JLabel();
    private JLabel projectID = new JLabel("Project ID:");
    private JLabel studentProjectID = new JLabel();
    private JLabel specialization = new JLabel("Project Specialization:");
    private JLabel projectSpecialization = new JLabel();
    private JLabel lecturerID = new JLabel("Lecturer ID:");
    private JLabel projectLecturerID = new JLabel();
    private JLabel lecturerName = new JLabel("Lecturer Name:");
    private JLabel projectLecturerName = new JLabel();
    private JLabel studentID = new JLabel("Student ID:");
    private JLabel projectStudentID = new JLabel();
    private JLabel studentName = new JLabel("Student Name:");
    private JLabel projectStudentName = new JLabel();
    private JLabel content = new JLabel("Project Content");
    private JLabel projectContent = new JLabel();
    private JButton back = new JButton("Back");
    private JPanel titlePanel = new JPanel();
    private JPanel projectDetails = new JPanel(new GridLayout(3,4));
    private JPanel pdPanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JScrollPane projectContentSPanel = new JScrollPane(projectContent);
    private JPanel backPanel = new JPanel();

    // Teo Yih Shing
    // default constructor to store the view screen
    public StudentViewProjectView() {
        super("MMU Online Mini-project Management System");
        this.title.setFont(new Font("Serif", Font.PLAIN, 40));
        this.titlePanel.setPreferredSize(new Dimension(800, 60));
        this.titlePanel.add(title);

        this.projectID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.studentProjectID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.specialization.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectSpecialization.setFont(new Font("Serif", Font.PLAIN, 15));
        this.lecturerID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectLecturerID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.lecturerName.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectLecturerName.setFont(new Font("Serif", Font.PLAIN, 15));
        this.studentID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectStudentID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.studentName.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectStudentName.setFont(new Font("Serif", Font.PLAIN, 15));

        this.projectDetails.setPreferredSize(new Dimension(700, 100));
        this.projectDetails.add(projectID);
        this.projectDetails.add(studentProjectID);
        this.projectDetails.add(lecturerName);
        this.projectDetails.add(projectLecturerName);
        this.projectDetails.add(specialization);
        this.projectDetails.add(projectSpecialization);
        this.projectDetails.add(studentID);
        this.projectDetails.add(projectStudentID);
        this.projectDetails.add(lecturerID);
        this.projectDetails.add(projectLecturerID);
        this.projectDetails.add(studentName);
        this.projectDetails.add(projectStudentName);
        this.pdPanel.add(projectDetails);

        this.content.setFont(new Font("Serif", Font.BOLD, 30));
        this.contentPanel.setPreferredSize(new Dimension(800, 45));
        this.contentPanel.add(content);

        this.projectContent.setFont(new Font("Serif", Font.PLAIN, 12));
        this.projectContentSPanel.setPreferredSize(new Dimension(700, 200));

        this.back.setFont(new Font("Serif", Font.PLAIN, 30));
        this.backPanel.setPreferredSize(new Dimension(800, 100));
        this.backPanel.add(back);

        GroupLayout viewBoardPane = new GroupLayout(getContentPane());
        getContentPane().setLayout(viewBoardPane);
        viewBoardPane.setAutoCreateGaps(true);
        viewBoardPane.setAutoCreateContainerGaps(true);

        viewBoardPane.setHorizontalGroup(viewBoardPane.createParallelGroup(Alignment.CENTER)
                .addComponent(titlePanel)
                .addComponent(pdPanel)
                .addComponent(contentPanel)
                .addComponent(projectContentSPanel)
                .addComponent(backPanel));

        viewBoardPane.setVerticalGroup(viewBoardPane.createSequentialGroup()
                .addComponent(titlePanel)
                .addComponent(pdPanel)
                .addComponent(contentPanel)
                .addComponent(projectContentSPanel)
                .addComponent(backPanel));


        this.setMinimumSize(new Dimension(800, 600));
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Teo Yih Shing
    // this method is used to set the String projectName into the JLabel title
    public void setProjectName(String projectName) {
        this.title.setText(projectName);
    }

    // Teo Yih Shing
    // this method is used to set the String projectID into the JLabel studentProjectID
    public void setProjectID(String projectID) {
        this.studentProjectID.setText(projectID);
    }

    // Teo Yih Shing
    // this method is used to set the String specialization into the JLabel projectSpecialization
    public void setProjectSpecialization(String specialization) {
        this.projectSpecialization.setText(specialization);
    }

    // Teo Yih Shing
    // this method is used to set the String id into the JLabel projectLecturerID
    public void setProjectLecturerID(String id) {
        this.projectLecturerID.setText(id);
    }

    // Teo Yih Shing
    // this method is used to set the String name into the JLabel projectLecturerName
    public void setProjectLecturerName(String name) {
        this.projectLecturerName.setText(name);
    }

    // Teo Yih Shing
    // this method is used to set the String id into the JLabel projectStudentID
    public void setProjectStudentID(String id) {
        this.projectStudentID.setText(id);
    }

    // Teo Yih Shing
    // this method is used to set the String name into the JLabel projectStudentName
    public void setProjectStudentName(String name) {
        this.projectStudentName.setText(name);
    }

    // Teo Yih Shing
    // this method is used to set the String content into the JLabel projectContent
    public void setProjectContent(String content) {
        this.projectContent.setText(content);
    }

    // Teo Yih Shing
    // this method is used to get JButton back
    public JButton getBackButton() {
        return this.back;
    }

    // Teo Yih Shing
    // this method is used to get JLabel projectContent
    public JLabel getProjectContent() {
        return projectContent;
    }
}