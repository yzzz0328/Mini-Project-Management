package View;

import java.awt.Dimension;
import java.awt.*;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;

// Chang Siu Hong
public class AdminViewProjectReportView extends JFrame {
    private JLabel titleLabel = new JLabel("View Project", JLabel.CENTER);
    private JLabel subtitle = new JLabel("Please choose one of the options", JLabel.CENTER);
    
    private JButton viewAllProjects = new JButton("View List of Project In System");
    private JButton viewProjectsWithSpecialization = new JButton("View Project According Specialization");
    private JButton viewLecturerProject = new JButton("View Project According Lecturers");
    private JButton viewActiveProject = new JButton("View Active Projects");
    private JButton viewInactiveProject = new JButton("View Inactive Projects");
    private JButton viewAssginedProject = new JButton("View Assigned Projects");
    private JButton viewUnassignedProject = new JButton("View Unassigned Projects");
    private JButton viewProjectWithComments = new JButton("View Projects With Comments");
    private JButton backButton = new JButton("Back");

    // Chang Siu Hong
    // default constructor to store the view screen
    public AdminViewProjectReportView() {
        super("MMU Online Mini-project Management System");

        titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));

        JPanel mainFunction = new JPanel();
        BoxLayout boxlayout = new BoxLayout(mainFunction, BoxLayout.Y_AXIS);
        mainFunction.setLayout(boxlayout);
        mainFunction.add(titleLabel);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        mainFunction.add(subtitle);
        subtitle.setAlignmentX(CENTER_ALIGNMENT);

        JPanel viewMain = new JPanel();
        GroupLayout viewProjectPane = new GroupLayout(viewMain);
        viewMain.setLayout(viewProjectPane);
        viewProjectPane.setAutoCreateGaps(true);
        viewProjectPane.setAutoCreateContainerGaps(true);
        viewProjectPane.setHorizontalGroup(viewProjectPane.createParallelGroup(Alignment.CENTER)
                        .addGroup(viewProjectPane.createSequentialGroup()
                                .addGroup(viewProjectPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(viewAllProjects, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(viewLecturerProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(viewInactiveProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(viewUnassignedProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(viewProjectPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(viewProjectsWithSpecialization, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(viewActiveProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(viewAssginedProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(viewProjectWithComments, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));

        viewProjectPane.setVerticalGroup(viewProjectPane.createSequentialGroup()
                        .addGroup(viewProjectPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(viewAllProjects, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewProjectsWithSpecialization, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(viewProjectPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(viewLecturerProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewActiveProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(viewProjectPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(viewInactiveProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewAssginedProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(viewProjectPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(viewUnassignedProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewProjectWithComments, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(mainFunction);
        panel.add(viewMain);
        panel.add(backButton);
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        
        add(panel);
        pack();
        setMinimumSize(new Dimension(800, 600));
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Chang Siu Hong
    // this method is to get the View All Projects button
    public JButton getViewAllProjects() {
        return viewAllProjects;
    }

    // Chang Siu Hong
    // this method is to get the View Projects With Specialization button
    public JButton getViewProjectsWithSpecialization() {
        return viewProjectsWithSpecialization;
    }

    // Chang Siu Hong
    // this method is to get the View Projects Based on Lecturers button
    public JButton getViewLecturerProject() {
        return viewLecturerProject;
    }

    // Chang Siu Hong
    // this method is to get the View Active Projects button
    public JButton getViewActiveProject() {
        return viewActiveProject;
    }

    // Chang Siu Hong
    // this method is to get the View Inactive Projects button
    public JButton getViewInactiveProject() {
        return viewInactiveProject;
    }

    // Chang Siu Hong
    // this method is to get the View Assigned Projects button
    public JButton getViewAssginedProject() {
        return viewAssginedProject;
    }

    // Chang Siu Hong
    // this method is to get the View Unassigned Projects button
    public JButton getViewUnassignedProject() {
        return viewUnassignedProject;
    }

    // Chang Siu Hong
    // this method is to get the View Projects With Comments button
    public JButton getViewProjectWithComments() {
        return viewProjectWithComments;
    }

    // Chang Siu Hong
    // this method is to get the Back button
    public JButton getBackButton() {
        return backButton;
    }
}