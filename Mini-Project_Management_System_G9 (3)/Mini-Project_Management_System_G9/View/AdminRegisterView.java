package View;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.awt.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Chang Siu Hong
public class AdminRegisterView extends JFrame implements ItemListener {
        JPanel cards; // a panel that uses CardLayout
        final static String EMPTYPANEL = " ";
        final static String ADMINPANEL = "Administrator";
        final static String STUDENTPANEL = "Student";
        final static String LECTURERPANEL = "Lecturer";

        Container pane;

        private JLabel titleLabel = new JLabel("Register New User");
        private JLabel userTypeLabel = new JLabel("User Type");
        private String user_Types[] = { EMPTYPANEL, ADMINPANEL, LECTURERPANEL, STUDENTPANEL };
        private JComboBox userTypeChoice = new JComboBox(user_Types);

        private JLabel detailsAdmin = new JLabel("Please fill in the details.");
        private JLabel userIDLabelAdmin = new JLabel("User ID");
        private JLabel userPWLabelAdmin = new JLabel("User Password");
        private JLabel userUserNameLabelAdmin = new JLabel("Username");

        private JLabel detailsLecturer = new JLabel("Please fill in the details.");
        private JLabel userIDLabelLecturer = new JLabel("User ID");
        private JLabel userPWLabelLecturer = new JLabel("User Password");
        private JLabel userUserNameLabelLecturer = new JLabel("Username");

        private JLabel detailsStudent = new JLabel("Please fill in the details.");
        private JLabel userIDLabelStudent = new JLabel("User ID");
        private JLabel userPWLabelStudent = new JLabel("User Password");
        private JLabel userUserNameLabelStudent = new JLabel("Username");
        private JLabel studentSpecialization = new JLabel("Specialization");
        private String[] student_Specialization_choice = { " ", "Software Engineering", "Cybersecurity", "Data Science",
                        "Game Development" };
        private JComboBox<String> student_Specialization = new JComboBox<String>(student_Specialization_choice);

        private JTextField userIDTextAdmin = new JTextField();
        private JTextField userPWTextAdmin = new JTextField();
        private JTextField userUserNameTextAdmin = new JTextField();

        private JTextField userIDTextLecturer = new JTextField();
        private JTextField userPWTextLecturer = new JTextField();
        private JTextField userUserNameTextLecturer = new JTextField();

        private JTextField userIDTextStudent = new JTextField();
        private JTextField userPWTextStudent = new JTextField();
        private JTextField userUserNameTextStudent = new JTextField();

        private JButton backButtonMain = new JButton("Back");
        private JButton backButtonAdmin = new JButton("Back");
        private JButton backButtonLecturer = new JButton("Back");
        private JButton backButtonStudent = new JButton("Back");

        private JButton submitButtonAdmin = new JButton("Submit");
        private JButton submitButtonLecturer = new JButton("Submit");
        private JButton submitButtonStudent = new JButton("Submit");

        private JPanel registerMain = new JPanel();
        private JPanel registerEmpty = new JPanel();
        private JPanel registerAdmin = new JPanel();
        private JPanel registerStudent = new JPanel();
        private JPanel registerLecturer = new JPanel();

        // Chang Siu Hong
        // default constructor to store the view screen
        public AdminRegisterView() {
                super("MMU Online Mini-project Management System");
                addComponentToPane(this.getContentPane());

                this.pack();
                this.setVisible(true);
                this.setResizable(true);
                this.setMinimumSize(new Dimension(800, 600));
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        // Chang Siu Hong
        // default constructor to store the view screen
        public void addComponentToPane(Container pane) {
                userTypeChoice.setEditable(false);
                userTypeChoice.addItemListener(this);

                GroupLayout registrationMainPane = new GroupLayout(registerMain);
                registerMain.setLayout(registrationMainPane);
                registrationMainPane.setAutoCreateGaps(true);
                registrationMainPane.setAutoCreateContainerGaps(true);
                titleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
                registrationMainPane.setHorizontalGroup(registrationMainPane.createParallelGroup(Alignment.CENTER)
                                .addComponent(titleLabel)
                                .addGroup(registrationMainPane.createSequentialGroup()
                                                .addGroup(registrationMainPane
                                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(userTypeLabel))
                                                .addGroup(registrationMainPane
                                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(userTypeChoice))));
                registrationMainPane.setVerticalGroup(registrationMainPane.createSequentialGroup()
                                .addComponent(titleLabel)
                                .addGroup(registrationMainPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(userTypeLabel)
                                                .addComponent(userTypeChoice)));

                registerEmpty.add(backButtonMain);

                GroupLayout adminregistrationPane = new GroupLayout(registerAdmin);
                detailsAdmin.setFont(new Font("Serif", Font.ITALIC, 15));
                registerAdmin.setLayout(adminregistrationPane);
                adminregistrationPane.setAutoCreateGaps(true);
                adminregistrationPane.setAutoCreateContainerGaps(true);
                adminregistrationPane.setHorizontalGroup(adminregistrationPane.createParallelGroup(Alignment.CENTER)
                                .addComponent(detailsAdmin)
                                .addGroup(adminregistrationPane.createSequentialGroup()
                                                .addGroup(adminregistrationPane
                                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(userIDLabelAdmin)
                                                                .addComponent(userPWLabelAdmin)
                                                                .addComponent(userUserNameLabelAdmin))
                                                .addGroup(adminregistrationPane
                                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(userIDTextAdmin)
                                                                .addComponent(userPWTextAdmin)
                                                                .addComponent(userUserNameTextAdmin)))
                                .addGroup(adminregistrationPane.createSequentialGroup()
                                                .addComponent(backButtonAdmin)
                                                .addComponent(submitButtonAdmin)));
                adminregistrationPane.setVerticalGroup(adminregistrationPane.createSequentialGroup()
                                .addComponent(detailsAdmin)
                                .addGroup(adminregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(userIDLabelAdmin)
                                                .addComponent(userIDTextAdmin))
                                .addGroup(adminregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(userPWLabelAdmin)
                                                .addComponent(userPWTextAdmin))
                                .addGroup(adminregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(userUserNameLabelAdmin)
                                                .addComponent(userUserNameTextAdmin))
                                .addGroup(adminregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(backButtonAdmin)
                                                .addComponent(submitButtonAdmin)));

                GroupLayout studentregistrationPane = new GroupLayout(registerStudent);
                detailsStudent.setFont(new Font("Serif", Font.ITALIC, 15));
                registerStudent.setLayout(studentregistrationPane);
                studentregistrationPane.setAutoCreateGaps(true);
                studentregistrationPane.setAutoCreateContainerGaps(true);
                studentregistrationPane.setHorizontalGroup(studentregistrationPane.createParallelGroup(Alignment.CENTER)
                                .addComponent(detailsStudent)
                                .addGroup(studentregistrationPane.createSequentialGroup()
                                                .addGroup(studentregistrationPane
                                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(userIDLabelStudent)
                                                                .addComponent(userPWLabelStudent)
                                                                .addComponent(userUserNameLabelStudent)
                                                                .addComponent(studentSpecialization))
                                                .addGroup(studentregistrationPane
                                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(userIDTextStudent)
                                                                .addComponent(userPWTextStudent)
                                                                .addComponent(userUserNameTextStudent)
                                                                .addComponent(student_Specialization)))
                                .addGroup(studentregistrationPane.createSequentialGroup()
                                                .addComponent(backButtonStudent)
                                                .addComponent(submitButtonStudent)));
                studentregistrationPane.setVerticalGroup(studentregistrationPane.createSequentialGroup()
                                .addComponent(detailsStudent)
                                .addGroup(studentregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(userIDLabelStudent)
                                                .addComponent(userIDTextStudent))
                                .addGroup(studentregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(userPWLabelStudent)
                                                .addComponent(userPWTextStudent))
                                .addGroup(studentregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(userUserNameLabelStudent)
                                                .addComponent(userUserNameTextStudent))
                                .addGroup(studentregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(studentSpecialization)
                                                .addComponent(student_Specialization))
                                .addGroup(studentregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(backButtonStudent)
                                                .addComponent(submitButtonStudent)));

                GroupLayout lecturerregistrationPane = new GroupLayout(registerLecturer);
                detailsLecturer.setFont(new Font("Serif", Font.ITALIC, 15));
                registerLecturer.setLayout(lecturerregistrationPane);
                lecturerregistrationPane.setAutoCreateGaps(true);
                lecturerregistrationPane.setAutoCreateContainerGaps(true);
                lecturerregistrationPane.setHorizontalGroup(lecturerregistrationPane
                                .createParallelGroup(Alignment.CENTER)
                                .addComponent(detailsLecturer)
                                .addGroup(lecturerregistrationPane.createSequentialGroup()
                                                .addGroup(lecturerregistrationPane
                                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(userIDLabelLecturer)
                                                                .addComponent(userPWLabelLecturer)
                                                                .addComponent(userUserNameLabelLecturer))
                                                .addGroup(lecturerregistrationPane
                                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(userIDTextLecturer)
                                                                .addComponent(userPWTextLecturer)
                                                                .addComponent(userUserNameTextLecturer)))
                                .addGroup(lecturerregistrationPane.createSequentialGroup()
                                                .addComponent(backButtonLecturer)
                                                .addComponent(submitButtonLecturer)));
                lecturerregistrationPane.setVerticalGroup(lecturerregistrationPane.createSequentialGroup()
                                .addComponent(detailsLecturer)
                                .addGroup(lecturerregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(userIDLabelLecturer)
                                                .addComponent(userIDTextLecturer))
                                .addGroup(lecturerregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(userPWLabelLecturer)
                                                .addComponent(userPWTextLecturer))
                                .addGroup(lecturerregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(userUserNameLabelLecturer)
                                                .addComponent(userUserNameTextLecturer))
                                .addGroup(lecturerregistrationPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(backButtonLecturer)
                                                .addComponent(submitButtonLecturer)));

                cards = new JPanel(new CardLayout());
                cards.add(registerEmpty, EMPTYPANEL);
                cards.add(registerAdmin, ADMINPANEL);
                cards.add(registerLecturer, LECTURERPANEL);
                cards.add(registerStudent, STUDENTPANEL);

                pane.add(registerMain, BorderLayout.PAGE_START);
                pane.add(cards, BorderLayout.CENTER);
        }

        // Chang Siu Hong
        // this method gets the user type choice selected by user
        public JComboBox getUserTypeChoice() {
                return userTypeChoice;
        }
        
        // Chang Siu Hong
        // this method gets the user specialization selected by user
        public JComboBox<String> getStudent_Specialization() {
                return student_Specialization;
        }
        
        // Chang Siu Hong
        // this method gets the Admin ID entered by user
        public JTextField getUserIDTextAdmin() {
                return userIDTextAdmin;
        }
        
        // Chang Siu Hong
        // this method gets the Admin password entered by user
        public JTextField getUserPWTextAdmin() {
                return userPWTextAdmin;
        }
        
        // Chang Siu Hong
        // this method gets the Admin user full name entered by user
        public JTextField getUserUserNameTextAdmin() {
                return userUserNameTextAdmin;
        }
        
        // Chang Siu Hong
        // this method gets the Leturer ID entered by user
        public JTextField getUserIDTextLecturer() {
                return userIDTextLecturer;
        }
        
        // Chang Siu Hong
        // this method gets the Leturer Password entered by user
        public JTextField getUserPWTextLecturer() {
                return userPWTextLecturer;
        }
        
        // Chang Siu Hong
        // this method gets the Leturer user full name entered by user
        public JTextField getUserUserNameTextLecturer() {
                return userUserNameTextLecturer;
        }

        // Chang Siu Hong
        // this method gets the Student ID entered by user
        public JTextField getUserIDTextStudent() {
                return userIDTextStudent;
        }
        
        // Chang Siu Hong
        // this method gets the Student password entered by user
        public JTextField getUserPWTextStudent() {
                return userPWTextStudent;
        }
        
        // Chang Siu Hong
        // this method gets the Student user full name entered by user
        public JTextField getUserUserNameTextStudent() {
                return userUserNameTextStudent;
        }
        
        // Chang Siu Hong
        // this method gets the back button from main registration page
        public JButton getBackButtonMain() {
                return backButtonMain;
        }
        
        // Chang Siu Hong
        // this method gets the back button from admin registration page
        public JButton getBackButtonAdmin() {
                return backButtonAdmin;
        }
        
        // Chang Siu Hong
        // this method gets the back button from lecturer registration page
        public JButton getBackButtonLecturer() {
                return backButtonLecturer;
        }
        
        // Chang Siu Hong
        // this method gets the back button from student registration page
        public JButton getBackButtonStudent() {
                return backButtonStudent;
        }
        
        // Chang Siu Hong
        // this method gets the submit button from admin registration page
        public JButton getSubmitButtonAdmin() {
                return submitButtonAdmin;
        }

        // Chang Siu Hong
        // this method gets the submit button from lecturer registration page
        public JButton getSubmitButtonLecturer() {
                return submitButtonLecturer;
        }
        
        // Chang Siu Hong
        // this method gets the submit button from student registration page
        public JButton getSubmitButtonStudent() {
                return submitButtonStudent;
        }
        
        // Chang Siu Hong
        // this method detects the usertype chosen by the user
        public void itemStateChanged(ItemEvent evt) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, (String) evt.getItem());
        }
}
