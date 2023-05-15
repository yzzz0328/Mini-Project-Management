package View;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

// Teoh Ye Zhian
public class LectureMenuView extends JFrame {
    JLabel title = new JLabel("Lecturer Center");
    JLabel subtitle = new JLabel();
    JButton lecturerCenter = new JButton("Account");
    JButton project = new JButton("Project");
    JButton activation = new JButton("Activation");
    JButton assignation = new JButton("Assignation");
    JButton logout = new JButton("Log Out");
    JPanel titlePanel = new JPanel();
    JPanel subtitlePanel = new JPanel();
    JPanel lecturerCenterBPanel = new JPanel();
    JPanel viewboardBPanel = new JPanel();
    JPanel activationPanel = new JPanel();
    JPanel assignationPanel = new JPanel();
    JPanel logoutBPanel = new JPanel();

    // Teoh Ye Zhian
    // Construct a default Lecturer Menu View objeect
    public LectureMenuView() {
        super("MMU Online Mini-project Management System");

        this.title.setFont(new Font("Serif", Font.PLAIN, 55));
        this.titlePanel.add(this.title);
        this.subtitle.setFont(new Font("Serif", Font.PLAIN, 30));
        this.subtitlePanel.add(this.subtitle);
        this.lecturerCenter.setFont(new Font("Serif", Font.PLAIN, 30));
        this.lecturerCenterBPanel.add(this.lecturerCenter);
        this.project.setFont(new Font("Serif", Font.PLAIN, 30));
        this.viewboardBPanel.add(this.project);
        this.activation.setFont(new Font("Serif", Font.PLAIN, 30));
        this.activationPanel.add(this.activation);
        this.assignation.setFont(new Font("Serif", Font.PLAIN, 30));
        this.assignationPanel.add(this.assignation);
        this.logout.setFont(new Font("Serif", Font.PLAIN, 30));
        this.logoutBPanel.add(this.logout);

        // Layout
        GroupLayout viewBoardPane = new GroupLayout(getContentPane());
        getContentPane().setLayout(viewBoardPane);
        viewBoardPane.setAutoCreateGaps(true);
        viewBoardPane.setAutoCreateContainerGaps(true);

        viewBoardPane.setHorizontalGroup(viewBoardPane.createParallelGroup(Alignment.CENTER)
                .addComponent(titlePanel)
                .addComponent(subtitlePanel)
                .addComponent(lecturerCenterBPanel)
                .addComponent(viewboardBPanel)
                .addComponent(activationPanel)
                .addComponent(assignationPanel)
                .addComponent(logoutBPanel));

        viewBoardPane.setVerticalGroup(viewBoardPane.createSequentialGroup()
                .addComponent(titlePanel)
                .addComponent(subtitlePanel)
                .addComponent(lecturerCenterBPanel)
                .addComponent(viewboardBPanel)
                .addComponent(activationPanel)
                .addComponent(assignationPanel)
                .addComponent(logoutBPanel));

        this.setSize(800, 600);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Teoh Ye Zhian
    // Sets the welcome message in the view
    public void setWelcomeMessage(String wel) {
        this.subtitle.setText(wel);
    }

    // Teoh Ye Zhian
    // Returns the button to view the lecturer details
    public JButton getlecturerCenterButton() {
        return this.lecturerCenter;
    }

    // Teoh Ye Zhian
    // Returns the button to view the project list
    public JButton getprojectButton() {
        return this.project;
    }

    // Teoh Ye Zhian
    // Returns the button for the page to activate and deactivate projects
    public JButton getactivationButton() {
        return this.activation;
    }

    // Teoh Ye Zhian
    // Returns the button for the page to assign and unassign project
    public JButton getassignationbutton() {
        return this.assignation;
    }

    // Teoh Ye Zhian
    // Returns the button for log out
    public JButton getLogoutButton() {
        return this.logout;
    }
}
