package View;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

// Teo Yih Shing
public class StudentAccountView extends JFrame{
    private JLabel title = new JLabel("Account Information");
    private JLabel id = new JLabel("User Name:");
    private JLabel studentID = new JLabel();
    private JLabel name = new JLabel("User Full Name:");
    private JLabel studentName = new JLabel();
    private JLabel type = new JLabel("User Type:");
    private JLabel userType = new JLabel("Student");
    private JLabel specialization = new JLabel("Specialization:");
    private JLabel studentSpecialization = new JLabel();
    private JLabel project = new JLabel("Project:");
    private JLabel studentProject = new JLabel();
    private JButton back = new JButton("Back");
    private JPanel titlePanel = new JPanel();
    private JPanel idPanel = new JPanel();
    private JPanel detailPanel = new JPanel(new GridLayout(6,2));
    private JPanel detailPPanel = new JPanel();
    private JPanel backBPanel = new JPanel();

    // Teo Yih Shing
    // default constructor to store the view screen
    public StudentAccountView() {
        super("MMU Online Mini-project Management System");

        this.title.setFont(new Font("Serif", Font.PLAIN, 50));
        this.titlePanel.setPreferredSize(new Dimension(800, 80));
        this.titlePanel.add(this.title);
        
        this.detailPanel.setPreferredSize(new Dimension(600, 250));
        
        this.id.setFont(new Font("Serif", Font.PLAIN, 20));
        this.idPanel.add(id);
        this.detailPanel.add(this.id);
        
        this.studentID.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.studentID);
        
        this.name.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.name);
        
        this.studentName.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.studentName);
        
        this.type.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.type);
        
        this.userType.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.userType);
        
        this.specialization.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.specialization);

        this.studentSpecialization.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.studentSpecialization);

        this.project.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.project);

        this.studentProject.setFont(new Font("Serif", Font.PLAIN, 15));
        this.detailPanel.add(this.studentProject);

        this.detailPPanel.add(detailPanel);

        this.back.setFont(new Font("Serif", Font.PLAIN, 30));
        this.backBPanel.setPreferredSize(new Dimension(800, 60));
        this.backBPanel.add(this.back);

        GroupLayout viewBoardPane = new GroupLayout(getContentPane());
        getContentPane().setLayout(viewBoardPane);
        viewBoardPane.setAutoCreateGaps(true);
        viewBoardPane.setAutoCreateContainerGaps(true);

        viewBoardPane.setHorizontalGroup(viewBoardPane.createParallelGroup(Alignment.CENTER)
                .addComponent(titlePanel)
                .addComponent(detailPPanel)
                .addComponent(backBPanel));

        viewBoardPane.setVerticalGroup(viewBoardPane.createSequentialGroup()
                .addComponent(titlePanel)
                .addComponent(detailPPanel)
                    .addComponent(backBPanel));

        this.setMinimumSize(new Dimension(800, 600));
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Teo Yih Shing
    // this method is used to set String id into JLabel studentID in this class
    public void setID(String id) {
        this.studentID.setText(id);
    }

    // Teo Yih Shing
    // this method is used to set String name into JLabel studentName in this class
    public void setName(String name) {
        this.studentName.setText(name);
    }

    // Teo Yih Shing
    // this method is used to set String specialization into JLabel studentSpecialization in this class
    public void setSpecialization(String specialization) {
        this.studentSpecialization.setText(specialization);
    }

    // Teo Yih Shing
    // this method is used to set String project into JLabel studentProject in this class
    public void setProject(String project) {
        this.studentProject.setText(project);
    }

    // Teo Yih Shing
    // this method is used to get JButton back in this class
    public JButton getBackButton() {
        return this.back;
    }
}
