package View;

import Model.ProjectModel;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

//Thong Kai Chin
public class LecturerProjectActivationView extends JFrame {
        String columnNames[] = { "Project ID", "Project Name", "Project Specialization", "Lecturer ID", "Lecturer Name",
                        "Student ID", "Student Name", "Status" };

        DefaultTableModel model = new DefaultTableModel();
        JTable projectTable = new JTable();
        ArrayList<ProjectModel> project_Arraylist = new ArrayList<ProjectModel>();
        JScrollPane scroll;

        //Add Activate button, Deactivate button, Back button
        JButton activate_Project_btn = new JButton("Activate");
        JButton deactivate_Project_btn = new JButton("Deactivate");
        JButton backButton = new JButton("Back");

        //Add Label 
        JLabel titleLabel = new JLabel("Activate/Deactivate Project");
        JLabel subtitle = new JLabel("Please choose the project that you wish to activate/deactivate.");
        JLabel project_select_label = new JLabel("Project Details");

        JComboBox<String> project_Details = new JComboBox<String>();
        
        // default constructor to store the view screen
        public LecturerProjectActivationView(String lecturer_ID) throws IOException {
                super("MMU Online Mini-project Management System");

                this.model = setDTM(lecturer_ID); //View LecturerID
                this.projectTable = new JTable(model);
                this.project_Details = new JComboBox<String>(projectDetails(lecturer_ID));

                scroll = new JScrollPane(projectTable);
                projectTable.setSize(400, 200);
                add(scroll);

                GroupLayout ProjectPane = new GroupLayout(getContentPane());
                getContentPane().setLayout(ProjectPane);
                ProjectPane.setAutoCreateGaps(true);
                ProjectPane.setAutoCreateContainerGaps(true);

                titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));

                ProjectPane.setHorizontalGroup(ProjectPane.createParallelGroup(Alignment.CENTER)
                                .addComponent(titleLabel)
                                .addComponent(scroll)
                                .addComponent(subtitle)
                                .addGroup(ProjectPane.createParallelGroup(Alignment.CENTER)
                                                .addGroup(ProjectPane.createSequentialGroup()
                                                                .addComponent(project_select_label)
                                                                .addComponent(project_Details))
                                                .addGroup(ProjectPane.createSequentialGroup()
                                                                .addComponent(backButton)
                                                                .addComponent(activate_Project_btn)
                                                                .addComponent(deactivate_Project_btn))));

                ProjectPane.setVerticalGroup(ProjectPane.createSequentialGroup()
                                .addComponent(titleLabel)
                                .addComponent(scroll)
                                .addComponent(subtitle)
                                .addGroup(ProjectPane.createSequentialGroup()
                                                .addGroup(ProjectPane
                                                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(project_select_label)
                                                                .addComponent(project_Details))
                                                .addGroup(ProjectPane
                                                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(backButton)
                                                                .addComponent(activate_Project_btn)
                                                                .addComponent(deactivate_Project_btn))));

                setMinimumSize(new Dimension(800, 600));
                setVisible(true);
        }

        private DefaultTableModel setDTM(String lecturer_ID) throws IOException {
                DefaultTableModel tempDTM = new DefaultTableModel() {

                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return false;
                        }
                };

                this.projectTable.setModel(tempDTM);
                Object[][] data = loadProjectDetails(lecturer_ID);
                tempDTM.setDataVector(data, new Object[] { "Project ID", "Project Name", "Specialization",
                                "Lecturer ID", "Lecturer Name", "Student ID", "Student Name", "Status" });

                return tempDTM;
        }
        //Thong Kai Chin
        // Get the column names
        public String[] getColumnNames() {
                return columnNames;
        }
        // Set the column names
        public void setColumnNames(String[] columnNames) {
                this.columnNames = columnNames;
        }
        // Get Project Table
        public JTable getProjectTable() {
                return projectTable;
        }
        // Set Project Table
        public void setProjectTable(JTable projectTable) {
                this.projectTable = projectTable;
        }
        // Get Default Table Mode
        public DefaultTableModel getModel() {
                return model;
        }
        //Set Default table model 
        public void setModel(DefaultTableModel model) {
                this.model = model;
        }
        // Get Scroll Pane
        public JScrollPane getScroll() {
                return scroll;
        }
        // Get Activate Project Button
        public JButton getActivate_Project_btn() {
                return activate_Project_btn;
        }
        // Get Deactivate Project Button
        public JButton getDeactivate_Project_btn() {
                return deactivate_Project_btn;
        }
        // Set Activate Project Button
        public void setActivate_Project_btn(JButton activate_Project_btn) {
                this.activate_Project_btn = activate_Project_btn;
        }
        // Set Deactivate Project Button
        public void setDeactivate_Project_btn(JButton deactivate_Project_btn) {
                this.deactivate_Project_btn = deactivate_Project_btn;
        }
        // Set Scroll Panel
        public void setScroll(JScrollPane scroll) {
                this.scroll = scroll;
        }
        // Get Back Button
        public JButton getBackButton() {
                return backButton;
        }
        // Set Back Button
        public void setBackButton(JButton backButton) {
                this.backButton = backButton;
        }
        // Get Title Label
        public JLabel getTitleLabel() {
                return titleLabel;
        }
        // Set Title Label
        public void setTitleLabel(JLabel titleLabel) {
                this.titleLabel = titleLabel;
        }
        // Get Subtitle
        public JLabel getSubtitle() {
                return subtitle;
        }
        // Set Subtitle
        public void setSubtitle(JLabel subtitle) {
                this.subtitle = subtitle;
        }
        // Get Project Selected Label
        public JLabel getProject_select_label() {
                return project_select_label;
        }
        // Set Project Selected Label
        public void setProject_select_label(JLabel project_select_label) {
                this.project_select_label = project_select_label;
        }
        // Get Project Details from ComboBox
        public JComboBox<String> getProject_Details() {
                return project_Details;
        }
        // Set Project Details from ComboBox
        public void setProject_Details(JComboBox<String> project_Details) {
                this.project_Details = project_Details;
        }
        // Get Project from ArrayList
        public ArrayList<ProjectModel> getProject_Arraylist() {
                return project_Arraylist;
        }
        // Set Project ArrayList
        public void setProject_Arraylist(ArrayList<ProjectModel> project_Arraylist) {
                this.project_Arraylist = project_Arraylist;
        }
        //Thong Kai Chin
        // To retreive project details from "ProjectList.txt" and load into the arraylist of project model
        private ArrayList<ProjectModel> retrieveExistingProject(String lecturer_ID) throws IOException {
                ArrayList<ProjectModel> project = new ArrayList<ProjectModel>();
                List<String> lines = Files.readAllLines(Paths.get("txt/ProjectList.txt"));
                
                for (int i = 0; i < lines.size(); i++) {
                        String[] items = lines.get(i).split("    ");

                        if (items[3].equals(lecturer_ID)) {
                                project.add(new ProjectModel(items[0], items[1], items[2], items[3], items[4], items[5],
                                                items[6], items[7]));
                        }
                }

                return project;
        }
        //Thong Kai Chin
        // To convert the arraylist of project details into 2D array
        private String[][] loadProjectDetails(String lecturer_ID) throws IOException {
                ArrayList<ProjectModel> list = retrieveExistingProject(lecturer_ID);
                String data[][] = new String[list.size()][getColumnNames().length];

                for (int i = 0; i < list.size(); i++) {
                        data[i][0] = list.get(i).getID();
                        data[i][1] = list.get(i).getName();
                        data[i][2] = list.get(i).getSpecialization();
                        data[i][3] = list.get(i).getLecturer_ID();
                        data[i][4] = list.get(i).getLecturer_Name();
                        data[i][5] = list.get(i).getStudent_ID();
                        data[i][6] = list.get(i).getStudent_Name();
                        data[i][7] = list.get(i).getStatus();
                }
                return data;
        }
        //Thong Kai Chin
        // To append the project ID with project name and display in the JComboBox
        private String[] projectDetails(String lecturer_ID) throws IOException {
                ArrayList<ProjectModel> project = retrieveExistingProject(lecturer_ID);
                String[] projectDetails = new String[project.size() + 1];
                projectDetails[0] = " ";
                for (int j = 0; j < project.size(); j++)
                        projectDetails[j + 1] = project.get(j).getID() + "   " + project.get(j).getName();
                return projectDetails;
        }
}
