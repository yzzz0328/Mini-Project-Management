package View;

import Model.ProjectModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Lam Ting Le
public class AdminSpecializationSortedProjectsView {

    private String[] columnNames = {"Project ID", "Project Name", "Project Specialization",
            "Lecturer ID", "Lecturer Name", "Student ID", "Student Name", "Status"}; // Table header names

    private String[][] tableData = loadProjectDetails(); // 2D array to store table data
    private TableRowSorter<DefaultTableModel> sorter;  // Table sorter
    private JTable table; // Table
    private JTextField filterTextField; // Text field to receive input to filter table
    private JButton backButton; // Back button
    private JFrame frame; // Current window

    // Lam Ting Le
    // Constructs a default AdminSpecializationSortedProjectsView object
    public AdminSpecializationSortedProjectsView() throws IOException {
        Font font1 = new Font("Serif", Font.PLAIN, 40); // Font for title
        Font font2 = new Font("Serif", Font.PLAIN, 15); // Font for labels
        Font font3 = new Font("Serif", Font.PLAIN, 12); // Font for buttons

        JLabel titleLabel = new JLabel("Projects List"); // Title
        titleLabel.setFont(font1);

        DefaultTableModel defaultTableModel = new DefaultTableModel(tableData, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; // Set table to as non editable
        defaultTableModel.setColumnIdentifiers(columnNames); // Set table header
        sorter = new TableRowSorter<>(defaultTableModel);
        table = new JTable();
        table.setModel(defaultTableModel); // Set table model
        table.setRowSorter(sorter); // Set sorter
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Set selection mode
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setMinWidth(70);
        table.getColumnModel().getColumn(0).setMaxWidth(70);
        table.getColumnModel().getColumn(2).setMinWidth(80);
        table.getColumnModel().getColumn(2).setMaxWidth(130);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(3).setMinWidth(70);
        table.getColumnModel().getColumn(3).setMaxWidth(70);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.getColumnModel().getColumn(5).setMinWidth(70);
        table.getColumnModel().getColumn(5).setMaxWidth(70);
        table.getColumnModel().getColumn(7).setResizable(false);
        table.getColumnModel().getColumn(7).setMinWidth(60);
        table.getColumnModel().getColumn(7).setMaxWidth(60);
        table.setDefaultRenderer(Object.class, new LineWrapCellRenderer()); // Set renderer to allow line wrap
        JScrollPane tableScrollPane = new JScrollPane(table);

        JLabel filterLabel = new JLabel("Filter by Specialization (Case Sensitive):");
        filterLabel.setFont(font2);

        filterTextField = new JTextField();
        filterTextField.setFont(font2);
        filterTextField.setMaximumSize(new Dimension(130, 15));

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
                .addComponent(titleLabel).addComponent(tableScrollPane)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(filterLabel))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(filterTextField)))
                .addComponent(backButton));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(titleLabel));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(tableScrollPane));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(filterLabel).addComponent(filterTextField));
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
    // Returns the sorter of this table
    public TableRowSorter<DefaultTableModel> getSorter() {
        return sorter;
    }

    // Lam Ting le
    // Returns the table
    public JTable getTable() {
        return table;
    }

    // Lam Ting Le
    // Returns the text field
    public JTextField getFilterTextField() {
        return filterTextField;
    }

    // Lam Ting Le
    // Returns the button
    public JButton getBackButton() {
        return backButton;
    }

    // Lam Ting Le
    // Returns the frame
    public JFrame getFrame() {
        return frame;
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
    // Method to store the the project data into a 2D array
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
}
