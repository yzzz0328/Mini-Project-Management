package View;

import Model.ProjectModel;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import java.awt.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// Chang Siu Hong
public class AdminViewAssignedProjectsView extends JFrame {
        private String columnNames[] = { "Project ID", "Project Name", "Project Specialization", "Lecturer ID", "Lecturer Name",
                        "Student ID", "Student Name", "Status" };
        private String project_tablelist[][] = loadAssignedProjectDetails();
        private JTable projectTable = new JTable();
        private DefaultTableModel model = new DefaultTableModel(project_tablelist, columnNames) {
                public boolean isCellEditable(int row, int column) {
                        return false;
                }
        };
        private JScrollPane scroll;

        private JLabel titleLabel = new JLabel("View Assigned Projects Report");
        private JLabel caption = new JLabel ();

        private JButton backButton = new JButton("Back");
        
        // Chang Siu Hong
        // default constructor to store the view screen
        public AdminViewAssignedProjectsView() throws IOException{
                super("MMU Online Mini-project Management System");
                model.setColumnIdentifiers(columnNames);
                projectTable.setModel(model);
                scroll = new JScrollPane(projectTable);
                projectTable.setSize(400, 200);
                this.projectTable.setDefaultRenderer(Object.class, new LineWrapCellRenderer());
                add(scroll);

                GroupLayout viewAllProjectReportPane = new GroupLayout(getContentPane());
                getContentPane().setLayout(viewAllProjectReportPane);
                viewAllProjectReportPane.setAutoCreateGaps(true);
                viewAllProjectReportPane.setAutoCreateContainerGaps(true);

                titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));

                viewAllProjectReportPane.setHorizontalGroup(viewAllProjectReportPane.createParallelGroup(Alignment.CENTER)
                                .addComponent(titleLabel)
                                .addComponent(scroll)
                                .addGroup(viewAllProjectReportPane.createParallelGroup(Alignment.CENTER)
                                                .addGroup(viewAllProjectReportPane.createSequentialGroup()
                                                                .addComponent(caption))
                                                .addGroup(viewAllProjectReportPane.createSequentialGroup()
                                                                .addComponent(backButton))));

                viewAllProjectReportPane.setVerticalGroup(viewAllProjectReportPane.createSequentialGroup()
                                .addComponent(titleLabel)
                                .addComponent(scroll)
                                .addGroup(viewAllProjectReportPane.createSequentialGroup()
                                                .addGroup(viewAllProjectReportPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(caption))
                                                .addGroup(viewAllProjectReportPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(backButton))));

                setMinimumSize(new Dimension(800, 600));
                setVisible(true);
                setResizable(true);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        // Chang Siu Hong
        // this method is to get the column names
        public String[] getColumnNames() {
                return columnNames;
        }

        // Chang Siu Hong
        // this method is to get 2D array list of project details
        public String[][] getProject_tablelist() {
                return project_tablelist;
        }

        // Chang Siu Hong
        // this method is to get the caption
        public JLabel getCaption() {
                return caption;
        }

        // Chang Siu Hong
        // this method is to get the back button
        public JButton getBackButton() {
                return backButton;
        }

        // Chang Siu Hong
        // this method is to retrieve the list of project details
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
        // this method is to laod assigned projects into a 2D array list
        private String[][] loadAssignedProjectDetails() throws IOException {
                ArrayList<ProjectModel> full_list = retrieveExistingProject();
                ArrayList<ProjectModel> list = new ArrayList<ProjectModel>();
                for (ProjectModel l: full_list) {
                        if (!(l.getStudent_ID().equals("null"))) {
                                list.add(l);
                        }
                }
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
}
