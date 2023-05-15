package View;

import Model.ProjectModel;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.table.*;

//Thong Kai Chin
//Display ColumnName, Label, Button, Table, Combobox, project, Scrollpane
public class LectureViewBoardView extends JFrame {
    private String columnNames[] = { "Project ID", "Project Name", "Project Specialization", "Lecturer ID", "Lecturer Name",
                        "Student ID", "Student Name", "Status" };
    private JLabel title = new JLabel("Project List");
    private JLabel tips = new JLabel("Please choose the project that you wish to view");
    private JLabel project_Select_Label = new JLabel("Project Details");

    private JButton back = new JButton("Back");
    private JButton view = new JButton("View");

    private DefaultTableModel dm;
    private JTable table = new JTable();
    private ArrayList<ProjectModel> project = new ArrayList<ProjectModel>();
    private JComboBox<String> project_Details = new JComboBox<String>();
    private JScrollPane scrollPane = new JScrollPane();

    public LectureViewBoardView(String lecturername) throws IOException {
        super("MMU Online Mini-project Management System");

        this.title.setFont(new Font("Serif", Font.PLAIN, 50));

        this.dm = setDTM(lecturername);
        this.table = new JTable(dm);

        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setPreferredSize(new Dimension(400, 200));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.getColumnModel().getColumn(0).setPreferredWidth(100);

        this.project_Details = new JComboBox<String>(setProjectList());

        GroupLayout viewBoardPane = new GroupLayout(getContentPane());
        getContentPane().setLayout(viewBoardPane);
        viewBoardPane.setAutoCreateGaps(true);
        viewBoardPane.setAutoCreateContainerGaps(true);

        viewBoardPane.setHorizontalGroup(viewBoardPane.createParallelGroup(Alignment.CENTER)
                .addComponent(title)
                .addComponent(scrollPane)
                .addComponent(tips)
                .addGroup(viewBoardPane.createParallelGroup(Alignment.CENTER)
                        .addGroup(viewBoardPane.createSequentialGroup()
                                .addComponent(project_Select_Label)
                                .addComponent(project_Details))
                        .addGroup(viewBoardPane.createSequentialGroup()
                                .addComponent(back)
                                .addComponent(view))));

        viewBoardPane.setVerticalGroup(viewBoardPane.createSequentialGroup()
                .addComponent(title)
                .addComponent(scrollPane)
                .addComponent(tips)
                .addGroup(viewBoardPane.createSequentialGroup()
                        .addGroup(viewBoardPane
                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(project_Select_Label)
                                .addComponent(project_Details))
                        .addGroup(viewBoardPane
                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(back)
                                .addComponent(view))));

        this.setMinimumSize(new Dimension(800, 600));
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //Thong Kai Chin
    //Set DefaultTableModel dm in this class
    private DefaultTableModel setDTM(String lecturername) throws IOException {
        DefaultTableModel tempDTM = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.table.setModel(tempDTM);
        Object[][] data = setData(lecturername);
        tempDTM.setDataVector(data, new Object[] { "Project ID", "Project Name", "Specialization", "Lecturer ID",
                "Lecturer Name", "Student ID", "Student Name", "Status" });

        return tempDTM;
    }

    //Thong Kai Chin
    //Read the ProjectList text file
    //Set Object[][] data in the setDTM(String) method in this class  
    private Object[][] setData(String lecturername) throws IOException {
        Object[][] dataList = {};
        int a = 0;

        try {
            File projectData = new File("txt/ProjectList.txt");
            Scanner myReader = new Scanner(projectData);
            String line, name;
            boolean samename = false;

            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                String[] splited = line.split("    ");
                name = splited[3];

                samename = checkname(lecturername, name);
                if (samename) {
                    ProjectModel temp = new ProjectModel(splited[0], splited[1], splited[2], splited[3], splited[4],
                            splited[5], splited[6], splited[7]);
                    this.project.add(temp);
                    a++;
                }
            }
            myReader.close();

            dataList = new Object[this.project.size()][getColumnNames().length];
            for (int i = 0; i < this.project.size(); i++) {
                dataList[i][0] = this.project.get(i).getID();
                dataList[i][1] = this.project.get(i).getName();
                dataList[i][2] = this.project.get(i).getSpecialization();
                dataList[i][3] = this.project.get(i).getLecturer_ID();
                dataList[i][4] = this.project.get(i).getLecturer_Name();
                dataList[i][5] = this.project.get(i).getStudent_ID();
                dataList[i][6] = this.project.get(i).getStudent_Name();
                dataList[i][7] = this.project.get(i).getStatus();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return dataList;
    }

    //Thong Kai Chin
    //Set the ProjectList of the ComboBox
    private String[] setProjectList() {
        String[] temp = new String[this.project.size() + 1];

        temp[0] = " ";
        for (int j = 0; j < project.size(); j++)
            temp[j + 1] = project.get(j).getID() + "   " + project.get(j).getName();

        return temp;
    }

    //Thong Kai Chin
    //Check whether the lecturer name is same as lec name in the setData
    private boolean checkname(String lecturername, String lecname) {
        boolean same = false;

        if (lecturername.equals(lecname))
            same = true;
        else
            same = false;

        return same;
    }

    //Thong Kai Chin
    //Get ColumnName
    public String[] getColumnNames() {
        return columnNames;
    }
    //Get View Button
    public JButton getViewButton() {
        return this.view;
    }
    //Get Back Button
    public JButton getBackButton() {
        return this.back;
    }
    //Get Selected Project
    public String getSelectedProject() {
        return this.project_Details.getSelectedItem().toString();
    }
}
