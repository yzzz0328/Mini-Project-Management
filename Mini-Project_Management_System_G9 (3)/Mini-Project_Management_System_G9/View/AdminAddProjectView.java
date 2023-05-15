package View;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

// Chang Siu Hong
public class AdminAddProjectView extends JFrame {
        private JButton create_Project_btn = new JButton("Create Project");
        private JButton backButton = new JButton("Back");

        private JLabel titleLabel = new JLabel("Add Project");
        private JLabel project_name_label = new JLabel("Project Name");
        private JLabel project_ID_label = new JLabel("Project ID");
        private JLabel project_Specialization_label = new JLabel("Project Specialization");
        private JLabel project_lecturer_ID_label = new JLabel("Lecturer ID");

        private JTextField project_name = new JTextField();
        private JTextField project_ID = new JTextField();
        private String[] project_Specialization_choice = { " ", "Software Engineering", "Cybersecurity", "Data Science",
                        "Game Development" };
        private JComboBox<String> project_Specialization = new JComboBox<String>(project_Specialization_choice);
        private JTextField project_lecturer_ID = new JTextField();

        // Chang Siu Hong
        // default constructor to store the view screen
        public AdminAddProjectView() {
                super("MMU Online Mini-project Management System");
                GroupLayout addProjectPane = new GroupLayout(getContentPane());
                getContentPane().setLayout(addProjectPane);
                addProjectPane.setAutoCreateGaps(true);
                addProjectPane.setAutoCreateContainerGaps(true);

                titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));

                addProjectPane.setHorizontalGroup(addProjectPane.createParallelGroup(Alignment.CENTER)
                                .addComponent(titleLabel)
                                .addGroup(addProjectPane.createSequentialGroup()
                                        .addGroup(addProjectPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(project_name_label)
                                                        .addComponent(project_ID_label)
                                                        .addComponent(project_Specialization_label)
                                                        .addComponent(project_lecturer_ID_label))
                                        .addGroup(addProjectPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(project_name)
                                                        .addComponent(project_ID)
                                                        .addComponent(project_Specialization)
                                                        .addComponent(project_lecturer_ID)))
                                .addGroup(addProjectPane.createSequentialGroup()
                                                .addComponent(backButton)
                                                .addComponent(create_Project_btn)));

                addProjectPane.setVerticalGroup(addProjectPane.createSequentialGroup()
                                .addComponent(titleLabel)
                                .addGroup(addProjectPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(project_name_label)
                                                .addComponent(project_name))
                                .addGroup(addProjectPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(project_ID_label)
                                                .addComponent(project_ID))
                                .addGroup(addProjectPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(project_Specialization_label)
                                                .addComponent(project_Specialization))
                                .addGroup(addProjectPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(project_lecturer_ID_label)
                                                .addComponent(project_lecturer_ID))
                                .addGroup(addProjectPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(backButton)
                                                .addComponent(create_Project_btn)));
                setMinimumSize(new Dimension(800, 600));
                setVisible(true);
                setResizable(true);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        // Chang Siu Hong
        // to get create project button 
        public JButton getCreate_Project_btn() {
                return create_Project_btn;
        }

        // Chang Siu Hong
        // to get back button 
        public JButton getBackButton() {
                return backButton;
        }

        // Chang Siu Hong
        // to get project name entered 
        public JTextField getProject_name() {
                return project_name;
        }

        // Chang Siu Hong
        // to get project ID entered
        public JTextField getProject_ID() {
                return project_ID;
        }

        // Chang Siu Hong
        // to get the specialization chosen from the JComboBox
        public JComboBox<String> getProject_Specialization() {
                return project_Specialization;
        }

        // Chang Siu Hong
        // to get lecturer ID entered
        public JTextField getProject_lecturer_ID() {
                return project_lecturer_ID;
        }

}
