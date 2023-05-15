package View;

import Model.ProjectModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

// Lam Ting Le
public class AdminViewCommentedProjectsView {
    String[] columnNames = {"Project ID", "Project Name", "Project Specialization",
            "Lecturer ID", "Lecturer Name", "Student ID", "Student Name", "Status"}; // Table header names

    String[][] tableList = loadCommentedProjects(); // 2D array to store table data
    private JTable table = new JTable(); // Table

    private JLabel caption; // Caption

    private JButton backButton; // Back button
    private JFrame frame; // Current window

    // Lam Ting Le
    // Constructs a default AdminViewCommentedProjectsView object
    public AdminViewCommentedProjectsView() {
        Font font1 = new Font("Serif", Font.PLAIN, 40); // Font for title
        Font font2 = new Font("Serif", Font.PLAIN, 15); // Font for labels
        Font font3 = new Font("Serif", Font.PLAIN, 12); // Font for buttons

        JLabel titleLabel = new JLabel("Commented Projects"); // Title
        titleLabel.setFont(font1);

        DefaultTableModel defaultTableModel = setDTM(); // Set table to as non editable and load table data
        table = new JTable();
        table.setModel(defaultTableModel); // Set default table model
        table.setDefaultRenderer(Object.class, new LineWrapCellRenderer()); // Set renderer to allow line wrap
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
        JScrollPane tableScrollPane = new JScrollPane(table);

        caption = new JLabel();
        caption.setFont(font2);

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
                .addComponent(titleLabel).addComponent(tableScrollPane).addComponent(caption).addComponent(backButton));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(titleLabel));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(tableScrollPane));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(caption));
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
    // Returns the label
    public JLabel getCaption() {
        return caption;
    }

    // Lam Ting Le
    // Returns the 2D array
    public String[][] getTableList() {
        return tableList;
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
    // Method to set DefaultTableModel and load table data
    // Invoke method to load data to table
    private DefaultTableModel setDTM() {
        DefaultTableModel tempDTM = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.table.setModel(tempDTM);
        tableList = loadCommentedProjects();
        tempDTM.setDataVector(tableList, columnNames);

        return tempDTM;
    }

    // Lam Ting Le
    // Method to load projects with comment
    // Read all the data in the file, "comments.txt"
    // While the line has data, add the project ID into a set
    // (Set will only store non duplicate elements since each project
    // can have multiple comments but the table just need to print
    // the project details once)
    // Read all the data in the file, "ProjectList.txt"
    // While the line has data and if the project ID equals to the project ID in the,
    // create a new ProjectModel object and add to ProjectModel ArrayList
    // Store the the table data into a 2D array
    private String[][] loadCommentedProjects() {
        String[][] tableData;

        try {
            File commentFile = new File("txt/comments.txt");
            Scanner commentReader = new Scanner(commentFile);
            String line;
            String[] lineArray;
            String projectID;

            Set<String> noDuplicate = new LinkedHashSet<>();
            while (commentReader.hasNextLine()) {
                line = commentReader.nextLine();
                lineArray = line.split("    ");
                noDuplicate.add(lineArray[0]);
            }
            commentReader.close();

            ArrayList<String> tempArrayList = new ArrayList<>(noDuplicate);

            File projectFile = new File("txt/ProjectList.txt");
            Scanner projectReader = new Scanner(projectFile);
            ArrayList<ProjectModel> projectModelArrayList = new ArrayList<>();
            while (projectReader.hasNextLine()) {
                line = projectReader.nextLine();
                lineArray = line.split("    ");
                projectID = lineArray[0];

                for (int i = 0; i < noDuplicate.size(); i++) {
                    if (tempArrayList.get(i).equals(projectID)) {
                        ProjectModel projectModel = new ProjectModel(lineArray[0],
                                lineArray[1], lineArray[2], lineArray[3], lineArray[4],
                                lineArray[5], lineArray[6], lineArray[7]);
                        projectModelArrayList.add(projectModel);
                    }
                }
            }
            projectReader.close();

            tableData = new String[projectModelArrayList.size()][getColumnNames().length];
            for (int i = 0; i < projectModelArrayList.size(); i ++) {
                tableData[i][0] = projectModelArrayList.get(i).getID();
                tableData[i][1] = projectModelArrayList.get(i).getName();
                tableData[i][2] = projectModelArrayList.get(i).getSpecialization();
                tableData[i][3] = projectModelArrayList.get(i).getLecturer_ID();
                tableData[i][4] = projectModelArrayList.get(i).getLecturer_Name();
                tableData[i][5] = projectModelArrayList.get(i).getStudent_ID();
                tableData[i][6] = projectModelArrayList.get(i).getStudent_Name();
                tableData[i][7] = projectModelArrayList.get(i).getStatus();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tableData;
    }
}
