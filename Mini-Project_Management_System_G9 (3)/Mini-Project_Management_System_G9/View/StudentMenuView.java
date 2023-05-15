package View;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

// Teo Yih Shing
public class StudentMenuView extends JFrame{
    private JLabel title = new JLabel("Student Menu Page");
    private JLabel welcome = new JLabel();
    private JButton studentCenter = new JButton("Account");
    private JButton viewboard = new JButton("Viewboard");
    private JButton logout = new JButton("Log Out");
    private JPanel titlePanel = new JPanel();
    private JPanel welPanel = new JPanel();
    private JPanel studentCenterBPanel = new JPanel();
    private JPanel viewboardBPanel = new JPanel();
    private JPanel logoutBPanel = new JPanel();

    // Teo Yih Shing
    // default constructor to store the view screen
    public StudentMenuView() {
        super("MMU Online Mini-project Management System");

        this.title.setFont(new Font("Serif", Font.PLAIN, 60));
        this.titlePanel.add(this.title);
        this.welcome.setFont(new Font("Serif", Font.PLAIN, 30));
        this.welPanel.add(welcome);
        this.studentCenter.setFont(new Font("Serif", Font.PLAIN, 30));
        this.studentCenterBPanel.add(this.studentCenter);
        this.viewboard.setFont(new Font("Serif", Font.PLAIN, 30));
        this.viewboardBPanel.add(this.viewboard);
        this.logout.setFont(new Font("Serif", Font.PLAIN, 30));
        this.logoutBPanel.add(this.logout);

        GroupLayout viewBoardPane = new GroupLayout(getContentPane());
        getContentPane().setLayout(viewBoardPane);
        viewBoardPane.setAutoCreateGaps(true);
        viewBoardPane.setAutoCreateContainerGaps(true);

        viewBoardPane.setHorizontalGroup(viewBoardPane.createParallelGroup(Alignment.CENTER)
                .addComponent(titlePanel)
                .addComponent(welPanel)
                .addComponent(studentCenterBPanel)
                .addComponent(viewboardBPanel)
                .addComponent(logoutBPanel));

        viewBoardPane.setVerticalGroup(viewBoardPane.createSequentialGroup()
                .addComponent(titlePanel)
                .addComponent(welPanel)
                .addComponent(studentCenterBPanel)
                .addComponent(viewboardBPanel)
                .addComponent(logoutBPanel));

        this.setMinimumSize(new Dimension(800, 600));
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Teo Yih Shing
    // this method is used to set String wel into the JLabel welcome
    public void setWelcomeMessage(String wel) {
        this.welcome.setText(wel);
    }

    // Teo Yih Shing
    // this method is used to get JButton studentCenter in this class
    public JButton getStudentCenterButton() {
        return this.studentCenter;
    }

    // Teo Yih Shing
    // this method is used to get JButton viewboard in this class
    public JButton getViewBoardButton() {
        return this.viewboard;
    }

    // Teo Yih Shing
    // this method is used to get JButton logout in this class
    public JButton getLogoutButton() {
        return this.logout;
    }
}
