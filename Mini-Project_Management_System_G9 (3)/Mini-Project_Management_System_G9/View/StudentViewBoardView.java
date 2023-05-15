package View;

import Model.ProjectModel;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.table.*;

// Teo Yih Shing
public class StudentViewBoardView extends JFrame {
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

    // Teo Yih Shing
    // parameterized constructor to store the view screen
    public StudentViewBoardView(String studentSpecialization) throws IOException {
        super("MMU Online Mini-project Management System");

        this.title.setFont(new Font("Serif", Font.PLAIN, 50));

        this.dm = setDTM(studentSpecialization);
        this.table = new JTable(dm);

        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setPreferredSize(new Dimension(400, 200));
        this.table.setPreferredScrollableViewportSize(table.getPreferredSize());
        this.table.getColumnModel().getColumn(0).setPreferredWidth(100);
        this.table.setDefaultRenderer(Object.class, new LineWrapCellRenderer());

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

    // Teo Yih Shing
    // this method is used to set DefaultTableModel dm in this class
    private DefaultTableModel setDTM(String studentSpecialization) throws IOException {
        DefaultTableModel tempDTM = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.table.setModel(tempDTM);
        Object[][] data = setData(studentSpecialization);
        tempDTM.setDataVector(data, new Object[] { "Project ID", "Project Name", "Specialization", "Lecturer ID", "Lecturer Name", "Student ID", "Student Name" });

        return tempDTM;
    }

    // Teo Yih Shing
    // this method is used to set Object[][] data in the setDTM(String) method in this class
    private Object[][] setData(String studentSpecialization) throws IOException {
        Object[][] dataList = {};
        int a = 0;

        try {
            File projectData = new File("txt/ProjectList.txt");
            Scanner myReader = new Scanner(projectData);
            String line, specialization, status;
            boolean activate = false, sameSpecialization = false;

            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                String[] splited = line.split("    ");
                specialization = splited[2];
                status = splited[7];

                sameSpecialization = checkSpecialization(studentSpecialization, specialization);
                if (sameSpecialization) {
                    activate = checkActivate(status);
                    if (activate) {
                        ProjectModel temp = new ProjectModel(splited[0], splited[1], splited[2], splited[3], splited[4], splited[5], splited[6], splited[7]);
                        this.project.add(temp);
                        a++;
                    }
                }
            }
            myReader.close();

            dataList = new Object[this.project.size()][7];
            for(int i = 0; i < this.project.size(); i++) {
                dataList[i][0] = this.project.get(i).getID();
                dataList[i][1] = this.project.get(i).getName();
                dataList[i][2] = this.project.get(i).getSpecialization();
                dataList[i][3] = this.project.get(i).getLecturer_ID();
                dataList[i][4] = this.project.get(i).getLecturer_Name();
                dataList[i][5] = this.project.get(i).getStudent_ID();
                dataList[i][6] = this.project.get(i).getStudent_Name();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return dataList;
    }

    // Teo Yih Shing
    // this method is used to set the objects of JComboBox<String> project_Details in this class
    private String[] setProjectList() {
        String[] temp = new String[this.project.size() + 1];

        temp[0] = " ";
        for (int j = 0; j < project.size(); j++)
            temp[j + 1] = project.get(j).getID() + "   " + project.get(j).getName();

        return temp;
    }

    // Teo Yih Shing
    // this method is used to check the student specialization is same as project specialization or not in the setData(String) method in this class
    private boolean checkSpecialization(String studentSpecialization, String projectSpecialization) {
        boolean same = false;

        if (studentSpecialization.equals(projectSpecialization))
            same = true;
        else
            same = false;

        return same;
    }

    // Teo Yih Shing
    // this method is used to check the project status is active or not in the setData(String) method in this class
    private boolean checkActivate(String status) {
        boolean activate = false;

        if (status.equals("Active"))
            activate = true;
        else if(status.equals("Inactive"))
            activate = false;

        return activate;
    }

    // Teo Yih Shing
    // this method is used to get the JButton view in this class
    public JButton getViewButton() {
        return this.view;
    }

    // Teo Yih Shing
    // this method is used to get the JButton back in this class
    public JButton getBackButton() {
        return this.back;
    }

    // Teo Yih Shing
    // this method is used to get the selected project of user as String in the JComboBox project_Details
    public String getSelectedProject() {
        return this.project_Details.getSelectedItem().toString();
    }
}
