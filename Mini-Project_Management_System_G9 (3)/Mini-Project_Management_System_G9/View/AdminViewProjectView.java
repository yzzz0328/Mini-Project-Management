package View;

import Model.CommentModel;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

// Lam Ting Le
public class AdminViewProjectView {

    private JLabel projectName; // Project name
    private JLabel projectID; // Project ID
    private JLabel projectSpecialization; // Project Specialization
    private JLabel studentID; // Student ID
    private JLabel studentName; // Student name
    private JLabel lecturerID; // Lecturer ID
    private JLabel lecturerName; // Lecturer name

    private JLabel projectContent; // Project content

    public JTextArea commentTextArea; // Comment text area
    private JButton clearCommentButton; // Clear comment button
    private JButton addCommentButton; // Add comment button

    String[] columnNames = { "ID", "Name", "Date Time", "Comment" }; // Table header names
    String[][] comment_tableList;
    private DefaultTableModel defaultTableModel = new DefaultTableModel(); // Default table model
    private JTable commentsTable = new JTable(); // Comment table
    private ArrayList<CommentModel> comments = new ArrayList<>(); // CommentModel ArrayList

    private JButton backButton; // Back button

    private JFrame frame; // Current Window

    // Lam Ting Le
    // Constructs a default AdminViewProjectView object with parameter
    public AdminViewProjectView(String pID) throws IOException {
        Font font1 = new Font("Serif", Font.PLAIN, 40); // Font for title
        Font font2 = new Font("Serif", Font.PLAIN, 30); // Font for sub title
        Font font3 = new Font("Serif", Font.PLAIN, 15); // Font for labels
        Font font4 = new Font("Serif", Font.PLAIN, 12); // Font for buttons

        projectName = new JLabel(); // Project name
        projectName.setFont(font1);

        JLabel projectIDLabel = new JLabel("Project ID:");
        projectIDLabel.setFont(font3);
        projectID = new JLabel(); // Project ID
        projectID.setFont(font3);
        JLabel projectSpecializationLabel = new JLabel("Project Specialization:");
        projectSpecializationLabel.setFont(font3);
        projectSpecialization = new JLabel(); // Project specialization
        projectSpecialization.setFont(font3);
        JLabel studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setFont(font3);
        studentID = new JLabel(); // Student ID
        studentID.setFont(font3);
        JLabel studentNameLabel = new JLabel("Student Name:");
        studentNameLabel.setFont(font3);
        studentName = new JLabel(); // Student name
        studentName.setFont(font3);
        JLabel lecturerIDLabel = new JLabel("Lecturer ID:");
        lecturerIDLabel.setFont(font3);
        lecturerID = new JLabel(); // Lecturer ID
        lecturerID.setFont(font3);
        JLabel lecturerNameLabel = new JLabel("Lecturer Name:");
        lecturerNameLabel.setFont(font3);
        lecturerName = new JLabel(); // Lecturer name
        lecturerName.setFont(font3);

        JLabel projectContentLabel = new JLabel("Project Content");
        projectContentLabel.setFont(font2);

        projectContent = new JLabel(); // Project content
        projectContent.setFont(font4);
        JScrollPane projectContentScrollPane = new JScrollPane(projectContent);
        projectContentScrollPane.setMinimumSize(new Dimension(380, 100));

        JLabel commentLabel = new JLabel("Comment");
        commentLabel.setFont(font2);

        commentTextArea = new JTextArea(); // Comment text area
        commentTextArea.setLineWrap(true); // Set line wrap
        commentTextArea.setWrapStyleWord(true);
        commentTextArea.setFont(font4);
        JScrollPane commentTAScrollPane = new JScrollPane(commentTextArea);
        commentTAScrollPane.setMinimumSize(new Dimension(380, 50));

        clearCommentButton = new JButton("Clear Comment");
        clearCommentButton.setFont(font4);
        addCommentButton = new JButton("Add Comment");
        addCommentButton.setFont(font4);

        defaultTableModel = setDTM(pID);  // Set table to as non editable and load table data
        commentsTable = new JTable();
        commentsTable.setModel(defaultTableModel); // Set default table model
        commentsTable.setDefaultRenderer(Object.class, new LineWrapCellRenderer()); // Set renderer to allow line wrap
        commentsTable.getColumnModel().getColumn(0).setResizable(false);
        commentsTable.getColumnModel().getColumn(0).setMinWidth(50);
        commentsTable.getColumnModel().getColumn(0).setMaxWidth(50);
        commentsTable.getColumnModel().getColumn(2).setResizable(false);
        commentsTable.getColumnModel().getColumn(2).setMinWidth(120);
        commentsTable.getColumnModel().getColumn(2).setMaxWidth(120);
        JScrollPane commentsTScrollPane = new JScrollPane(commentsTable);
        commentsTScrollPane.setMinimumSize(new Dimension(380, 100));

        backButton = new JButton("Back");
        backButton.setFont(font4);

        // Layout
        JPanel projectLayout = new JPanel();
        GroupLayout layout = new GroupLayout(projectLayout);
        projectLayout.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(projectName)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(projectIDLabel).addComponent(studentIDLabel)
                                .addComponent(lecturerIDLabel))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(projectID).addComponent(studentID).addComponent(lecturerID))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(projectSpecializationLabel).addComponent(studentNameLabel)
                                .addComponent(lecturerNameLabel))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(projectSpecialization).addComponent(studentName)
                                .addComponent(lecturerName)))
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(projectContentLabel).addComponent(projectContentScrollPane))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(commentLabel).addComponent(commentTAScrollPane)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup()
                                                .addComponent(clearCommentButton))
                                        .addGroup(layout.createParallelGroup()
                                                .addComponent(addCommentButton)))
                                .addComponent(commentsTScrollPane)))
                .addComponent(backButton));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(projectName));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(projectIDLabel).addComponent(projectID)
                .addComponent(projectSpecializationLabel).addComponent(projectSpecialization));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(studentIDLabel).addComponent(studentID)
                .addComponent(studentNameLabel).addComponent(studentName));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(lecturerIDLabel).addComponent(lecturerID)
                .addComponent(lecturerNameLabel).addComponent(lecturerName));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(projectContentLabel).addComponent(commentLabel));
        vGroup.addGroup(layout.createParallelGroup()
                .addComponent(projectContentScrollPane)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(commentTAScrollPane)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(clearCommentButton).addComponent(addCommentButton))
                        .addComponent(commentsTScrollPane)));
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
    // Returns the frame
    public JFrame getFrame() {
        return frame;
    }

    // Lam Ting Le
    // Sets a new project name for this label
    public void setProjectName(String projectName) {
        this.projectName.setText(projectName);
    }

    // Lam Ting Le
    // Sets a new project ID for this label
    public void setProjectID(String projectID) {
        this.projectID.setText(projectID);
    }

    // Lam Ting Le
    // Returns the project ID of this label
    public JLabel getProjectID() {
        return projectID;
    }

    // Lam Ting Le
    // Sets a new specialization for this label
    public void setProjectSpecialization(String projectSpecialization) {
        this.projectSpecialization.setText(projectSpecialization);
    }

    // Lam Ting Le
    // Sets a new student ID for this label
    public void setStudentID(String studentID) {
        this.studentID.setText(studentID);
    }

    // Lam Ting Le
    // Sets a new student name for this label
    public void setStudentName(String studentName) {
        this.studentName.setText(studentName);
    }

    // Lam Ting Le
    // Sets a new lecturer ID for this label
    public void setLecturerID(String lecturerID) {
        this.lecturerID.setText(lecturerID);
    }

    // Lam Ting Le
    // Sets a new lecturer name for this label
    public void setLecturerName(String lecturerName) {
        this.lecturerName.setText(lecturerName);
    }

    // Lam Ting Le
    // Returns the text area
    public JTextArea getCommentTextArea() {
        return commentTextArea;
    }

    // Lam Ting Le
    // Returns the default table model
    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

    // Lam Ting Le
    // Returns the button for clear comment
    public JButton getClearCommentButton() {
        return clearCommentButton;
    }

    // Lam Ting Le
    // Returns the button for add comment
    public JButton getAddCommentButton() {
        return addCommentButton;
    }

    // Lam Ting Le
    // Returns the table header names
    public String[] getColumnNames() {
        return columnNames;
    }

    // Lam Ting Le
    // Method to set DefaultTableModel and load table data
    // Invoke method to load data to table
    private DefaultTableModel setDTM(String projectID) throws IOException {
        DefaultTableModel tempDTM = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.commentsTable.setModel(tempDTM);
        comment_tableList = loadComments(projectID);
        tempDTM.setDataVector(comment_tableList, columnNames);

        return tempDTM;
    }

    // Lam Ting Le
    // Method to retrieve comment data from text file
    // Create a CommentModel ArrayList
    // Read all the data in the file, "comments.txt"
    // Create a new CommentModel object and add the data by accessing the array elements
    // Add the object into the ArrayList
    private ArrayList<CommentModel> retrieveComments() throws IOException {
        ArrayList<CommentModel> commentModels = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("txt/comments.txt"));
        boolean hasNextLine = false;

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isEmpty() == false) {
                String[] items = lines.get(i).split("    ");
                commentModels.add(new CommentModel(items[0], items[1], items[2], items[3], items[4]));
                hasNextLine = checkHasNextLine(lines, i);

                while (hasNextLine == true) {
                    if (i < (lines.size() - 1)) {
                        String nextLine = lines.get(i + 1);
                        commentModels.get(commentModels.size() - 1)
                                .setComment(commentModels.get(commentModels.size() - 1).getComment() + "\n" + nextLine);
                        i++;
                        if (i < (lines.size() - 1))
                            hasNextLine = checkHasNextLine(lines, i);
                    } else {
                        hasNextLine = false;
                        break;
                    }
                }
            }
        }
        return commentModels;
    }

    // Lam Ting Le
    // Check if the next line is empty
    private boolean checkHasNextLine(List<String> lines, int i) {
        boolean hasNextLine = false;

        if (lines.get(i + 1).isEmpty() == false)
            hasNextLine = true;

        return hasNextLine;
    }

    // Lam Ting Le
    // Method to store the the comments data into a 2D array
    // If the project ID of the comment equals to the current project ID, add to the array
    private String[][] loadComments(String projectID) throws IOException {
        String[][] dataList = {};
        ArrayList<CommentModel> temp = retrieveComments();

        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getProjectID().equals(projectID)) {
                this.comments.add(temp.get(i));
            }
        }

        dataList = new String[this.comments.size()][getColumnNames().length];
        for (int i = 0; i < comments.size(); i++) {
            dataList[i][0] = comments.get(i).getID();
            dataList[i][1] = comments.get(i).getName();
            dataList[i][2] = comments.get(i).getCommentDateTime();
            dataList[i][3] = comments.get(i).getComment();
        }
        return dataList;
    }

    // Lam Ting Le
    // Returns the button for back
    public JButton getBackButton() {
        return backButton;
    }

    // Lam Ting Le
    // Returns the label for project content
    public JLabel getProjectContent() {
        return projectContent;
    }

}
