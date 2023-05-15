package View;

import Model.Student;

import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Teoh Ye Zhian
public class LectureAssignStudentView extends JFrame {

    JLabel title = new JLabel("Project");
    JLabel tips = new JLabel("Please choose the student that you wish to assign to");
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

    JButton assign = new JButton("Assign");
    JButton back = new JButton("Back");

    ArrayList<Student> student_Arraylist = new ArrayList<Student>();
    private JComboBox<String> Student_Details = new JComboBox<String>();

    JPanel titlePanel = new JPanel();
    JPanel projectDetails = new JPanel(new GridLayout(3, 4));
    JPanel pdPanel = new JPanel();
    JPanel backPanel = new JPanel();
    JPanel assignPanel = new JPanel();

    // Teoh Ye Zhian
    // default constructor accepts project_specialization and stores the view screen
    public LectureAssignStudentView(String project_specialization) throws IOException {
        super("Lecture View Project");

        this.student_Arraylist = setStudentArrayList(project_specialization);

        this.title.setFont(new Font("Serif", Font.PLAIN, 50));
        this.titlePanel.setPreferredSize(new Dimension(800, 60));
        this.titlePanel.add(title);

        this.lecturerID.setFont(new Font("Serif", Font.PLAIN, 19));
        this.projectLecturerID.setFont(new Font("Serif", Font.PLAIN, 19));
        this.lecturerName.setFont(new Font("Serif", Font.PLAIN, 19));
        this.projectLecturerName.setFont(new Font("Serif", Font.PLAIN, 19));
        this.projectID.setFont(new Font("Serif", Font.PLAIN, 19));
        this.studentProjectID.setFont(new Font("Serif", Font.PLAIN, 19));
        this.specialization.setFont(new Font("Serif", Font.PLAIN, 19));
        this.projectSpecialization.setFont(new Font("Serif", Font.PLAIN, 19));
        this.studentID.setFont(new Font("Serif", Font.PLAIN, 19));
        this.projectStudentID.setFont(new Font("Serif", Font.PLAIN, 19));
        this.studentName.setFont(new Font("Serif", Font.PLAIN, 19));
        this.projectStudentName.setFont(new Font("Serif", Font.PLAIN, 19));

        this.projectDetails.setPreferredSize(new Dimension(700, 200));
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

        this.Student_Details.setPreferredSize(new Dimension(600, 20));
        this.Student_Details = new JComboBox<String>(setStudentList());

        GroupLayout viewBoardPane = new GroupLayout(getContentPane());
        getContentPane().setLayout(viewBoardPane);
        viewBoardPane.setAutoCreateGaps(true);
        viewBoardPane.setAutoCreateContainerGaps(true);

        viewBoardPane.setHorizontalGroup(viewBoardPane.createParallelGroup(Alignment.CENTER)
                .addComponent(titlePanel)
                .addComponent(pdPanel)
                .addComponent(tips)
                .addGroup(viewBoardPane.createParallelGroup(Alignment.CENTER)
                        .addGroup(viewBoardPane.createSequentialGroup()
                                .addComponent(Student_Details))
                        .addGroup(viewBoardPane.createSequentialGroup()
                                .addComponent(back)
                                .addComponent(assign))));

        viewBoardPane.setVerticalGroup(viewBoardPane.createSequentialGroup()
                .addComponent(titlePanel)
                .addComponent(pdPanel)
                .addComponent(tips)
                .addGroup(viewBoardPane.createSequentialGroup()
                        .addGroup(viewBoardPane.createSequentialGroup()
                                .addComponent(Student_Details))
                        .addGroup(viewBoardPane
                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(back)
                                .addComponent(assign))));

        this.setMinimumSize(new Dimension(800, 600));
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Teoh Ye Zhian
    // this method returns the project of the selected specialization
    private ArrayList<Student> setStudentArrayList(String project_specialization) throws IOException {
        ArrayList<Student> temp = new ArrayList<Student>();
        List<String> lines = Files.readAllLines(Paths.get("txt/Student.txt"));

        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split("    ");
            if (items[4].equals(project_specialization))
                if (items[5].equals("null"))
                    temp.add(new Student(items[0], items[1], items[2], items[3], items[4], items[5]));
        }
        return temp;
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
    // this method gets the project name
    public JLabel getProjectID() {
        return studentProjectID;
    }

    // Teoh Ye Zhian
    // this method gets the project specialization
    public void setProjectSpecialization(String specialization) {
        this.projectSpecialization.setText(specialization);
    }

    // Teoh Ye Zhian
    // this method gets the lectruer ID of the project
    public void setProjectLecturerID(String id) {
        this.projectLecturerID.setText(id);
    }

    // Teoh Ye Zhian
    // this method gets the lectruer name of the project
    public void setProjectLecturerName(String name) {
        this.projectLecturerName.setText(name);
    }

    // Teoh Ye Zhian
    // this method gets the student ID of the project
    public void setProjectStudentID(String id) {
        this.projectStudentID.setText(id);
    }

    // Teoh Ye Zhian
    // this method gets the student name of the project
    public void setProjectStudentName(String name) {
        this.projectStudentName.setText(name);
    }

    // Teoh Ye Zhian
    // this method gets the student details from the view
    public JComboBox<String> getStudent_Details() {
        return Student_Details;
    }

    // Teoh Ye Zhian
    // this method sets the student details and returns to the view
    private String[] setStudentList() {
        String[] temp = new String[this.student_Arraylist.size() + 1];

        temp[0] = " ";
        for (int j = 0; j < student_Arraylist.size(); j++)
            temp[j + 1] = student_Arraylist.get(j).getName() + "   " + student_Arraylist.get(j).getUserFullName();

        return temp;
    }

    // Teoh Ye Zhian
    // this method gets the back button
    public JButton getBackButton() {
        return this.back;
    }

    // Teoh Ye Zhian
    // this method gets the assign button
    public JButton getAssignButton() {
        return this.assign;
    }

    // Teoh Ye Zhian
    // this method gets and returns the student
    public Student getSelectedStudent() throws IOException {
        String student_details = this.getStudent_Details().getSelectedItem().toString();
        String[] sd = student_details.split("   ");

        Student student = retrieveStudent(sd[0]);

        return student;
    }

    // Teoh Ye Zhian
    // this method returns the project of selected student
    private Student retrieveStudent(String student_ID) throws IOException {
        Student student = new Student();
        List<String> lines = Files.readAllLines(Paths.get("txt/Student.txt"));

        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split("    ");

            if (items[0].equals(student_ID)) {
                student = new Student(items[0], items[1], items[2], items[3], items[4], items[5]);
            }
        }
        return student;
    }
}