package View;

import java.awt.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Chang Siu Hong
public class AdminMainView extends JFrame{
        private JLabel title = new JLabel("Administrator Menu Page");
        private JLabel welcome = new JLabel();
        private JButton registerUser = new JButton("User Registration");
        private JButton viewProjects = new JButton("View Projects");
        private JButton addProject = new JButton("Add Project");
        private JButton deleteProject = new JButton("Delete Project");
        private JButton viewProjectReport = new JButton("View Project Reports");
        private JButton logout = new JButton("Log Out");

        private JPanel titlePanel = new JPanel();
        private JPanel welPanel = new JPanel();
        private JPanel registerUserPanel = new JPanel();
        private JPanel viewProjectJPanel = new JPanel();
        private JPanel addProjectJPanel = new JPanel();
        private JPanel deleteProjectJPanel = new JPanel();
        private JPanel viewProjectReportJPanel = new JPanel();
        private JPanel logoutPanel = new JPanel();
    
        // Chang Siu Hong
        // default constructor to store the view screen
        public AdminMainView() {
            super("MMU Online Mini-project Management System");
    
            title.setFont(new Font("Serif", Font.PLAIN, 60));
            titlePanel.add(title);

            welcome.setFont(new Font("Serif", Font.BOLD, 30));
            welPanel.add(welcome);

            registerUser.setFont(new Font("Serif", Font.PLAIN, 20));
            registerUser.setPreferredSize(new Dimension(220,30));
            registerUserPanel.add(registerUser);

            viewProjects.setFont(new Font("Serif", Font.PLAIN, 20));
            viewProjects.setPreferredSize(new Dimension(220,30));
            viewProjectJPanel.add(viewProjects);

            addProject.setFont(new Font("Serif", Font.PLAIN, 20));
            addProject.setPreferredSize(new Dimension(220,30));
            addProjectJPanel.add(addProject);

            deleteProject.setFont(new Font("Serif", Font.PLAIN, 20));
            deleteProject.setPreferredSize(new Dimension(220,30));
            deleteProjectJPanel.add(deleteProject);

            viewProjectReport.setFont(new Font("Serif", Font.PLAIN, 20));
            viewProjectReport.setPreferredSize(new Dimension(220,30));
            viewProjectReportJPanel.add(viewProjectReport);

            logout.setFont(new Font("Serif", Font.PLAIN, 20));
            logout.setPreferredSize(new Dimension(220,30));
            logoutPanel.add(logout);
    
            GroupLayout mainPane = new GroupLayout(getContentPane());
            getContentPane().setLayout(mainPane);
            mainPane.setAutoCreateGaps(true);
            mainPane.setAutoCreateContainerGaps(true);
    
            mainPane.setHorizontalGroup(mainPane.createParallelGroup(Alignment.CENTER)
                    .addComponent(titlePanel)
                    .addComponent(welPanel)
                    .addComponent(registerUserPanel)
                    .addComponent(viewProjectJPanel)
                    .addComponent(addProjectJPanel)
                    .addComponent(deleteProjectJPanel)
                    .addComponent(viewProjectReportJPanel)
                    .addComponent(logoutPanel));
    
            mainPane.setVerticalGroup(mainPane.createSequentialGroup()
                    .addComponent(titlePanel)
                    .addComponent(welPanel)
                    .addComponent(registerUserPanel)
                    .addComponent(viewProjectJPanel)
                    .addComponent(addProjectJPanel)
                    .addComponent(deleteProjectJPanel)
                    .addComponent(viewProjectReportJPanel)
                    .addComponent(logoutPanel));
    
            setMinimumSize(new Dimension(800, 600));
            setResizable(true);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        // Chang Siu Hong
        // this method is used to set String wel into the JLabel welcome
        public void setWelcomeMessage(String wel) {
            this.welcome.setText(wel);
        }

        // Chang Siu Hong
        // this method is used to get Register User button
        public JButton getRegisterUser() {
            return registerUser;
        }

        // Chang Siu Hong
        // this method is used to get Register User button
        public JButton getViewProjects() {
            return viewProjects;
        }

        // Chang Siu Hong
        // this method is used to get Add Project button
        public JButton getAddProject() {
            return addProject;
        }

        // Chang Siu Hong
        // this method is used to get Delete Project button
        public JButton getDeleteProject() {
            return deleteProject;
        }

        // Chang Siu Hong
        // this method is used to get View Project Report button
        public JButton getViewProjectReport() {
            return viewProjectReport;
        }

        // Chang Siu Hong
        // this method is used to get Logout button
        public JButton getLogout() {
            return logout;
        }
        
}