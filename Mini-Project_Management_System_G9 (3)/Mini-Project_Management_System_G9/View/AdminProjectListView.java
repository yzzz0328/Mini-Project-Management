package View;

import Model.ProjectModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Lam Ting Le
public class AdminProjectListView {

    private String[] columnNames = {"Project ID", "Project Name", "Project Specialization",
            "Lecturer ID", "Lecturer Name", "Student ID", "Student Name", "Status"}; // Table header names

    private String[][] project_tableList = loadProjectDetails(); // 2D array to store table data

    String[] projectList = projectDetails(); // Array with project details
    private JComboBox<String> projectComboBox; // Combo box to select project
    private JButton viewProjectButton; // View project button

    private JButton backButton;  // Back button
    private JFrame frame; // Current window

    // Lam Ting Le
    // Constructs a default AdminProjectListView object
    public AdminProjectListView() throws IOException {
        Font font1 = new Font("Serif", Font.PLAIN, 40); // Font for title
        Font font2 = new Font("Serif", Font.PLAIN, 15); // Font for labels
        Font font3 = new Font("Serif", Font.PLAIN, 12); // Font for buttons

        JLabel projectListLabel = new JLabel("Project List");
        projectListLabel.setFont(font1);

        DefaultTableModel defaultTableModel = new DefaultTableModel(project_tableList, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; // Set table to as non editable
        defaultTableModel.setColumnIdentifiers(columnNames); // Set table header
        JTable projectTable = new JTable();
        projectTable.setModel(defaultTableModel); // Set table model
        projectTable.getColumnModel().getColumn(0).setResizable(false);
        projectTable.getColumnModel().getColumn(0).setMinWidth(70);
        projectTable.getColumnModel().getColumn(0).setMaxWidth(70);
        projectTable.getColumnModel().getColumn(2).setMinWidth(80);
        projectTable.getColumnModel().getColumn(2).setMaxWidth(130);
        projectTable.getColumnModel().getColumn(3).setResizable(false);
        projectTable.getColumnModel().getColumn(3).setMinWidth(70);
        projectTable.getColumnModel().getColumn(3).setMaxWidth(70);
        projectTable.getColumnModel().getColumn(5).setResizable(false);
        projectTable.getColumnModel().getColumn(5).setMinWidth(70);
        projectTable.getColumnModel().getColumn(5).setMaxWidth(70);
        projectTable.getColumnModel().getColumn(7).setResizable(false);
        projectTable.getColumnModel().getColumn(7).setMinWidth(60);
        projectTable.getColumnModel().getColumn(7).setMaxWidth(60);
        projectTable.setDefaultRenderer(Object.class, new LineWrapCellRenderer()); // Set renderer to allow line wrap
        JScrollPane projectTScrollPane = new JScrollPane(projectTable);

        JLabel comboBoxLabel = new JLabel("Project Details:");
        comboBoxLabel.setFont(font2);

        projectComboBox = new JComboBox<>(projectList);
        projectComboBox.setMaximumSize(new Dimension(600, 10));

        viewProjectButton = new JButton("View Project");
        viewProjectButton.setFont(font3);

        backButton = new JButton("Back");
        backButton.setFont(font3);

        // Layout
        JPanel projectLayout = new JPanel();
        GroupLayout layout = new GroupLayout(projectLayout);
        projectLayout.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(projectListLabel).addComponent(projectTScrollPane)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(comboBoxLabel))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(projectComboBox))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(viewProjectButton)))
                .addComponent(backButton));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(projectListLabel));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(projectTScrollPane));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(comboBoxLabel).addComponent(projectComboBox).addComponent(viewProjectButton));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(backButton));
        layout.setVerticalGroup(vGroup);

        frame = new JFrame("MMU Online Mini-project Management System");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setResizable(true);
        frame.add(projectLayout);
        frame.setVisible(true);
    }

    // Lam Ting Le
    // Returns the table header names
    public String[] getColumnNames() {
        return columnNames;
    }

    // Lam Ting Le
    // Method to store the the table data into a 2D array
    private String[][] loadProjectDetails() throws IOException {
        ArrayList<ProjectModel> list = retrieveExistingProject();
        String[][] data = new String [list.size()][getColumnNames().length];

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

    // Lam Ting Le
    // Method to retrieve project data from text file
    // Create a ProjectModel ArrayList
    // Read all the data in the file, "ProjectList.txt"
    // Create a new ProjectModel object and add the data by accessing the array elements
    // Add the object into the ArrayList
    private ArrayList<ProjectModel> retrieveExistingProject() throws IOException{
        ArrayList<ProjectModel> project = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("txt/ProjectList.txt"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split("    ");
            project.add(new ProjectModel(items[0], items[1], items[2], items[3], items[4], items[5], items[6], items[7]));
        }
        return project;
    }

    // Lam Ting Le
    // Returns the project details for the combo box
    private String[] projectDetails() throws IOException{
        ArrayList<ProjectModel> project = retrieveExistingProject();
        String[] projectDetails = new String[project.size()+1];
        projectDetails[0] = " ";
        for (int j = 0; j < project.size(); j++) projectDetails[j+1] = project.get(j).getID() + "   " + project.get(j).getName();
        return projectDetails;
    }

    // Lam Ting Le
    // Returns the frame
    public JFrame getFrame() {
        return frame;
    }

    // Lam Ting Le
    // Returns the selected project details in the combo box
    public String getSelectedProject() {
        return Objects.requireNonNull(this.projectComboBox.getSelectedItem()).toString();
    }

    // Lam Ting Le
    // Returns the button
    public JButton getViewProjectButton() {
        return viewProjectButton;
    }

    // Lam Ting Le
    // Returns the button
    public JButton getBackButton() {
        return backButton;
    }
}
