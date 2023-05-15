package View;

import Model.ProjectModel;

import java.awt.Dimension;
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

// Teoh Ye Zhian
public class LectureAssignView extends JFrame {
        String columnNames[] = { "Project ID", "Project Name", "Project Specialization", "Lecturer ID", "Lecturer Name",
                        "Student ID", "Student Name", "Status" };

        DefaultTableModel model = new DefaultTableModel();
        JTable projectTable = new JTable();
        ArrayList<ProjectModel> project_Arraylist = new ArrayList<ProjectModel>();
        JScrollPane scroll;

        JButton assign_Project_btn = new JButton("Assign");
        JButton unassign_Project_btn = new JButton("Unassign");
        JButton backButton = new JButton("Back");

        JLabel titleLabel = new JLabel("Assign/Unassign Project");
        JLabel subtitle = new JLabel("Please choose the project that you wish to activate/deactivate.");
        JLabel project_select_label = new JLabel("Project Details");

        JComboBox<String> project_Details = new JComboBox<String>();

        // Teoh Ye Zhian
        // default constructor accepts project_specialization and stores the view screen
        public LectureAssignView(String lecturer_ID) throws IOException {
                super("Project Activation");

                this.model = setDTM(lecturer_ID);
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
                                                                .addComponent(assign_Project_btn)
                                                                .addComponent(unassign_Project_btn))));

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
                                                                .addComponent(assign_Project_btn)
                                                                .addComponent(unassign_Project_btn))));

                setMinimumSize(new Dimension(800, 600));
                setVisible(true);
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        // Teoh Ye Zhian
        // default table model that sets the JTable
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

        // Teoh Ye Zhian
        // this method returns the column names
        public String[] getColumnNames() {
                return columnNames;
        }

        // Teoh Ye Zhian
        // this method gets assign project button
        public JButton getAssigned_Project_btn() {
                return assign_Project_btn;
        }

        // Teoh Ye Zhian
        // this method gets unassign project button
        public JButton getUnassigned_Project_btn() {
                return unassign_Project_btn;
        }

        // Teoh Ye Zhian
        // this method gets back button
        public JButton getBackButton() {
                return backButton;
        }

        // Teoh Ye Zhian
        // this method gets project details
        public JComboBox<String> getProject_Details() {
                return project_Details;
        }

        // Teoh Ye Zhian
        // this method separates the projectdetails and return projects
        public ProjectModel getSelectedProject() throws IOException {
                String line = project_Details.getSelectedItem().toString();
                String[] pd = line.split("   ");

                ProjectModel project = retrieveProject(pd[0]);

                return project;
        }

        // Teoh Ye Zhian
        // this method retrieves selected projects from txt file
        private ProjectModel retrieveProject(String project_ID) throws IOException {
                ProjectModel project = new ProjectModel();
                List<String> lines = Files.readAllLines(Paths.get("txt/ProjectList.txt"));

                for (int i = 0; i < lines.size(); i++) {
                        String[] items = lines.get(i).split("    ");

                        if (items[0].equals(project_ID)) {
                                project = new ProjectModel(items[0], items[1], items[2], items[3], items[4], items[5],
                                                items[6], items[7]);
                        }
                }

                return project;
        }

        // Teoh Ye Zhian
        // this method retrieves all projectdetails
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

        // Teoh Ye Zhian
        // this method laods project details into 2D string array
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

        // Teoh Ye Zhian
        // this method accepts lecturer ID parameter and return the project details
        private String[] projectDetails(String lecturer_ID) throws IOException {
                ArrayList<ProjectModel> project = retrieveExistingProject(lecturer_ID);
                String[] projectDetails = new String[project.size() + 1];
                projectDetails[0] = " ";
                for (int j = 0; j < project.size(); j++)
                        projectDetails[j + 1] = project.get(j).getID() + "   " + project.get(j).getName();
                return projectDetails;
        }
}
