package View;

import Model.ProjectModel;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// Chang Siu Hong
public class AdminDeleteProjectView extends JFrame {
        private String columnNames[] = {"Project ID", "Project Name", "Project Specialization", "Lecturer ID", "Lecturer Name", "Student ID", "Student Name", "Status"};
        private String project_tablelist[][] = loadProjectDetails();
        private JTable projectTable = new JTable();
        private DefaultTableModel model = new DefaultTableModel(project_tablelist, columnNames) {
                public boolean isCellEditable(int row, int column) {
                        return false;
                }
        };
        private JScrollPane scroll;

        private JButton delete_Project_btn = new JButton("Delete");
        private JButton backButton = new JButton("Back");

        private JLabel titleLabel = new JLabel("Delete Project");
        private JLabel subtitle = new JLabel("Please choose the project that you wish to delete");
        private JLabel project_select_label = new JLabel("Project Details");

        private String[] project_List = projectDetails();
        private JComboBox<String> project_Details = new JComboBox<String>(project_List);

        // Chang Siu Hong
        // default constructor to store the view screen
        public AdminDeleteProjectView() throws IOException {
                super("MMU Online Mini-project Management System");
                
                model.setColumnIdentifiers(columnNames);   
                projectTable.setModel(model);
                scroll = new JScrollPane(projectTable);
                projectTable.setSize(400,200);
                this.projectTable.setDefaultRenderer(Object.class, new LineWrapCellRenderer());
                add(scroll);

                GroupLayout deleteProjectPane = new GroupLayout(getContentPane());
                getContentPane().setLayout(deleteProjectPane);
                deleteProjectPane.setAutoCreateGaps(true);
                deleteProjectPane.setAutoCreateContainerGaps(true);

                titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
                
                deleteProjectPane.setHorizontalGroup(deleteProjectPane.createParallelGroup(Alignment.CENTER)
                        .addComponent(titleLabel)
                        .addComponent(scroll)
                        .addComponent(subtitle)
                        .addGroup(deleteProjectPane.createParallelGroup(Alignment.CENTER)
                                .addGroup(deleteProjectPane.createSequentialGroup()
                                        .addComponent(project_select_label)
                                        .addComponent(project_Details))
                                .addGroup(deleteProjectPane.createSequentialGroup()
                                        .addComponent(backButton)
                                        .addComponent(delete_Project_btn))));
                
                deleteProjectPane.setVerticalGroup(deleteProjectPane.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addComponent(scroll)
                        .addComponent(subtitle)
                        .addGroup(deleteProjectPane.createSequentialGroup()
                                .addGroup(deleteProjectPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(project_select_label)
                                        .addComponent(project_Details))
                                .addGroup(deleteProjectPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(backButton)
                                        .addComponent(delete_Project_btn))));
                
                setMinimumSize(new Dimension(800, 600));
                setVisible(true);
                setResizable(true);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        // Chang Siu Hong
        // to get the column names
        public String[] getColumnNames() {
                return columnNames;
        }

        // Chang Siu Hong
        // to get the default table model
        public DefaultTableModel getModel() {
                return model;
        }

        // Chang Siu Hong
        // to get delete project button
        public JButton getDelete_Project_btn() {
                return delete_Project_btn;
        }

        // Chang Siu Hong
        // to get back button
        public JButton getBackButton() {
                return backButton;
        }

        // Chang Siu Hong
        // to get project details from JComboBox
        public JComboBox<String> getProject_Details() {
                return project_Details;
        }

        // Chang Siu Hong
        // to retreive project details from "ProjectList.txt" and load into the arraylist of project model
        private ArrayList<ProjectModel> retrieveExistingProject() throws IOException{
                ArrayList<ProjectModel> project = new ArrayList<ProjectModel>();
                List<String> lines = Files.readAllLines(Paths.get("txt/ProjectList.txt"));
                for (int i = 0; i < lines.size(); i++) {
                    String[] items = lines.get(i).split("    ");
                    project.add(new ProjectModel(items[0], items[1], items[2], items[3], items[4], items[5], items[6], items[7]));
                }
                return project;
        }
        
        // Chang Siu Hong
        // to convert the arraylist of project details into 2D array
        private String[][] loadProjectDetails() throws IOException {
                ArrayList<ProjectModel> list = retrieveExistingProject();
                String data[][] = new String [list.size()][getColumnNames().length];
        
                for (int i = 0; i < list.size(); i ++) {
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

        // Chang Siu Hong
        // to append the project ID with project name and display in the JComboBox
        private String[] projectDetails() throws IOException{
                ArrayList<ProjectModel> project = retrieveExistingProject();
                String[] projectDetails = new String[project.size()+1];
                projectDetails[0] = " ";
                for (int j = 0; j < project.size(); j++) projectDetails[j+1] = project.get(j).getID() + "   " + project.get(j).getName();
                return projectDetails;
        }
}
