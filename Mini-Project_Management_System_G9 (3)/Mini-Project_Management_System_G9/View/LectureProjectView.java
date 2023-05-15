package View;

import Model.CommentModel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// Teoh Ye Zhian
public class LectureProjectView extends JFrame {
    private Font font4;

    JLabel title = new JLabel("Project");
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
    JLabel content = new JLabel("Project Content");
    JLabel datetime = new JLabel("");
    JLabel projectContent = new JLabel();
    JLabel commentLabel = new JLabel("Comment");

    JButton upload = new JButton("Upload");
    JButton modify = new JButton("Modify");
    JButton back = new JButton("Back");

    JPanel titlePanel = new JPanel();
    JPanel projectDetails = new JPanel(new GridLayout(3, 4));
    JPanel pdPanel = new JPanel();
    JPanel contentPanel = new JPanel();
    JPanel datetimePanel = new JPanel();
    JScrollPane commentTAScrollPane = new JScrollPane();
    JScrollPane projectContentSPanel = new JScrollPane(projectContent);
    JPanel projectContentJPanel = new JPanel(new FlowLayout());
    JPanel uploadcontentPanel = new JPanel();
    JPanel backPanel = new JPanel();
    JPanel uploadPanel = new JPanel();
    JPanel modifyPanel = new JPanel();
    JPanel commentsPanel = new JPanel();

    String[] columnNames = { "ID", "Name", "Date Time", "Comment" };
    String[][] comment_tableList;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private JTable commentsTable = new JTable();
    private JScrollPane commentsTScrollPane;
    private ArrayList<CommentModel> comments = new ArrayList<>();
    
    // Teoh Ye Zhian
    // parameterized constructor to store the view screen
    public LectureProjectView(String pID) throws IOException {
        super("MMU Online Mini-project Management System");

        font4 = new Font("Serif", Font.PLAIN, 12);

        this.title.setFont(new Font("Serif", Font.PLAIN, 40));
        this.titlePanel.setPreferredSize(new Dimension(800, 50));
        this.titlePanel.add(title);

        this.lecturerID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectLecturerID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.lecturerName.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectLecturerName.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.studentProjectID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.specialization.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectSpecialization.setFont(new Font("Serif", Font.PLAIN, 15));
        this.studentID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectStudentID.setFont(new Font("Serif", Font.PLAIN, 15));
        this.studentName.setFont(new Font("Serif", Font.PLAIN, 15));
        this.projectStudentName.setFont(new Font("Serif", Font.PLAIN, 15));

        this.projectDetails.setPreferredSize(new Dimension(700, 71));
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

        this.content.setFont(new Font("Serif", Font.BOLD, 30));
        this.contentPanel.setPreferredSize(new Dimension(320, 40));
        this.contentPanel.add(content);

        this.projectContent.setFont(new Font("Serif", Font.PLAIN, 10));
        this.projectContentSPanel.setPreferredSize(new Dimension(350, 270));
        
        this.datetime.setFont(font4);
        this.datetimePanel.setPreferredSize(new Dimension(350, 20));
        this.datetimePanel.add(datetime);

        this.projectContentJPanel.setPreferredSize(new Dimension(450, 350));
        this.projectContentJPanel.add(datetimePanel);
        this.projectContentJPanel.add(projectContentSPanel);

        this.commentLabel.setFont(new Font("Serif", Font.BOLD, 30));
        this.commentsPanel.setPreferredSize(new Dimension(350, 140));
        this.commentsPanel.add(commentLabel);

        defaultTableModel = setDTM(pID);
        commentsTable = new JTable();
        commentsTable.setModel(defaultTableModel);

        commentsTable.setDefaultRenderer(Object.class, new LineWrapCellRenderer());
        commentTAScrollPane.setPreferredSize(new Dimension(350, 50));
        commentsTScrollPane = new JScrollPane(commentsTable);

        GroupLayout viewBoardPane = new GroupLayout(getContentPane());
        getContentPane().setLayout(viewBoardPane);
        viewBoardPane.setAutoCreateGaps(true);
        viewBoardPane.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = viewBoardPane.createSequentialGroup();
        hGroup.addGroup(viewBoardPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(titlePanel).addComponent(pdPanel)
                .addGroup(viewBoardPane.createSequentialGroup()
                        .addGroup(viewBoardPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(contentPanel).addComponent(datetimePanel).addComponent(projectContentSPanel))
                        .addGroup(viewBoardPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(commentLabel).addComponent(commentsTScrollPane)))
                .addGroup(viewBoardPane.createSequentialGroup()
                        .addGroup(viewBoardPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(viewBoardPane.createSequentialGroup()
                                        .addGroup(viewBoardPane.createParallelGroup()
                                                .addComponent(back))
                                        .addGroup(viewBoardPane.createParallelGroup()
                                                .addComponent(upload))
                                        .addGroup(viewBoardPane.createParallelGroup()
                                                .addComponent(modify))))));
        viewBoardPane.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = viewBoardPane.createSequentialGroup();
        vGroup.addGroup(viewBoardPane.createParallelGroup()
                .addComponent(titlePanel));
        vGroup.addGroup(viewBoardPane.createParallelGroup()
                .addComponent(pdPanel));
        vGroup.addGroup(viewBoardPane.createParallelGroup()
                .addGroup(viewBoardPane.createSequentialGroup()
                        .addComponent(contentPanel).addComponent(datetimePanel))
                .addComponent(commentLabel));
        vGroup.addGroup(viewBoardPane.createParallelGroup()
                .addComponent(projectContentSPanel).addComponent(commentsTScrollPane));
        vGroup.addGroup(viewBoardPane.createParallelGroup()
                .addComponent(back).addComponent(upload).addComponent(modify));
        viewBoardPane.setVerticalGroup(vGroup);

        this.setMinimumSize(new Dimension(800, 600));
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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
    // this method gets the project ID
    public JLabel getProjectID() {
        return studentProjectID;
    }

    // Teoh Ye Zhian
    // this method sets the project specialization
    public void setProjectSpecialization(String specialization) {
        this.projectSpecialization.setText(specialization);
    }

    // Teoh Ye Zhian
    // this method sets the lecturer ID of the project
    public void setProjectLecturerID(String id) {
        this.projectLecturerID.setText(id);
    }

    // Teoh Ye Zhian
    // this method sets the lecturer name of the project
    public void setProjectLecturerName(String name) {
        this.projectLecturerName.setText(name);
    }

    // Teoh Ye Zhian
    // this method sets the stduent ID of the project
    public void setProjectStudentID(String id) {
        this.projectStudentID.setText(id);
    }

    // Teoh Ye Zhian
    // this method sets the stduent name of the project
    public void setProjectStudentName(String name) {
        this.projectStudentName.setText(name);
    }

    // Teoh Ye Zhian
    // this method gets the content of the project
    public JLabel getContent(){
        return projectContent;
    }

    // Teoh Ye Zhian
    // this method gets the date and time of the project
    public JLabel getdatetime(){
        return datetime;
    }

    // Teoh Ye Zhian
    // this method gets the back button
    public JButton getBackButton() {
        return this.back;
    }

    // Teoh Ye Zhian
    // this method gets the upload button
    public JButton getUploadButton() {
        return this.upload;
    }

    // Teoh Ye Zhian
    // this method gets the modify button
    public JButton getModifyButton() {
        return this.modify;
    }
    
    // Teoh Ye Zhian
    // this method gets the column names
    public String[] getColumnNames() {
        return columnNames;
    }
    
    // Teoh Ye Zhian
    // this method sets the comment table
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

    // Teoh Ye Zhian
    // this method retreives comment from txt file
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

    // Teoh Ye Zhian
    // this method checks if next line is empty in txt file
    private boolean checkHasNextLine(List<String> lines, int i) {
        boolean hasNextLine = false;

        if (lines.get(i + 1).isEmpty() == false)
            hasNextLine = true;

        return hasNextLine;
    }

    // Teoh Ye Zhian
    // this method laods the comments into 2D string array
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
}
