package Controller;

import Model.*;
import View.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

// Teo Yih Shing
// Observer design pattern
public class LoginController implements ActionListener {
    private String usertype;
    private LoginView view;
    private Student student = new Student();
    private Lecturer lecturer = new Lecturer();
    private Admin admin = new Admin();

    // Teo Yih Shing
    // parameterized constructor
    public LoginController(LoginView v) {
        this.view = v;
    }

    // Teo Yih Shing
    // this method is used to call the actionListener when user click on the "Login" button
    public void initController() {
        this.view.getLoginButton().addActionListener(this);
    }

    // Teo Yih Shing
    // this method is used to check the usertype of the user, and the credentials of the user is valid or not, 
    // if the credentials is valid, this method will set the model (student, lecturer, or admin) in this class according to the usertype
    private boolean checkUserCredentials() throws IOException {
        String userType = setUserType();
        boolean found = false;
        boolean valid = false;

        if (!(userType == null)) {
            if (this.usertype.equals("Student")) {
                ArrayList<Student> s = student.retrieveStudentList();
                found = checkStudentValidity(s, this.view.getUserName(), this.view.getPassword());
                if (found == true) {
                    this.student = setStudentDetails(s, this.view.getUserName(), this.view.getPassword());
                    this.student.setProject(setProject(this.view.getUserName()));
                    valid = true;
                }
            }
            else if (this.usertype.equals("Lecturer")) {
                ArrayList<Lecturer> l = lecturer.retrieveLecturerList();
                found = checkLecturerValidity(l, this.view.getUserName(), this.view.getPassword()); // delegator
                if (found == true) {
                    this.lecturer = setLecturerDetails(l, this.view.getUserName(), this.view.getPassword());
                    valid = true;
                }
            }
            else {
                ArrayList<Admin> a = admin.retrieveAdminList();
                found = checkAdminValidity(a, this.view.getUserName(), this.view.getPassword());
                if (found == true) {
                    this.admin = setAdminDetails(a, this.view.getUserName(), this.view.getPassword());
                    valid = true;
                }
            }
        }
        return valid;
    }

    // Teo Yih Shing
    // For aggregation use 
    // this method is used to set the Student student in this class when user is login as a student successfully
    private Student setStudentDetails(ArrayList<Student> u, String un, String pw) {
        Student s = new Student();
        for (int i = 0; i < u.size(); i++ ) {
            if (u.get(i).getName().equals(un) && u.get(i).getPassword().equals(pw)) {
                s = new Student(u.get(i).getName(), u.get(i).getPassword(), u.get(i).getUserFullName(), u.get(i).getUserType(), u.get(i).getSpecialization(), setProject(un));
                break;
            }
        }
        return s;
    }

    // Teo Yih Shing
    // this method is used to set the Lecturer lecturer in this class when user is login as a lecturer successfully
    private Lecturer setLecturerDetails(ArrayList<Lecturer> u, String un, String pw) {
        Lecturer l = new Lecturer();
        for (int i = 0; i < u.size(); i++ ) {
            if (u.get(i).getName().equals(un) && u.get(i).getPassword().equals(pw)) {
                l = u.get(i);
                break;
            }
        }
        return l;
    }

    // Teo Yih Shing
    // this method is used to set the Admin admin in this class when user is login as a administrator successfully
    private Admin setAdminDetails(ArrayList<Admin> u, String un, String pw) {
        Admin a = new Admin();
        for (int i = 0; i < u.size(); i++ ) {
            if (u.get(i).getName().equals(un) && u.get(i).getPassword().equals(pw)) {
                a = u.get(i);
                break;
            }
        }
        return a;
    }

    // Teo Yih Shing
    // this method is used to check the username (String un) and password (Dtring pw) of the user who usertype is "Student" is valid or not
    private boolean checkStudentValidity(ArrayList<Student> s, String un, String pw) {
        boolean found = false;
        for (int i = 0; i < s.size(); i++ ) {
            if (s.get(i).getName().equals(un) && s.get(i).getPassword().equals(pw)) {
                found = true;
                break;
            }
            else {
               found = false;
            }
        }

        if (found == false) {
            JOptionPane.showMessageDialog(null, "Your credentials are invalid!\nPlease try again.",
               "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }

        return found;
    }

    // Teo Yih Shing
    // this method is used to check the username (String un) and password (Dtring pw) of the user who usertype is "Lecturer" is valid or not
    private boolean checkLecturerValidity(ArrayList<Lecturer> s, String un, String pw) {
        boolean found = false;
        for (int i = 0; i < s.size(); i++ ) {
            if (s.get(i).getName().equals(un) && s.get(i).getPassword().equals(pw)) {
                found = true;
                break;
            }
            else {
               found = false;
            }
        }

        if (found == false) {
            JOptionPane.showMessageDialog(null, "Your credentials are invalid!\nPlease try again.",
               "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }

        return found;
    }

    // Teo Yih Shing
    // this method is used to check the username (String un) and password (Dtring pw) of the user who usertype is "Administrator" is valid or not
    private boolean checkAdminValidity(ArrayList<Admin> s, String un, String pw) {
        boolean found = false;
        for (int i = 0; i < s.size(); i++ ) {
            if (s.get(i).getName().equals(un) && s.get(i).getPassword().equals(pw)) {
                found = true;
                break;
            }
            else {
               found = false;
            }
        }

        if (found == false) {
            JOptionPane.showMessageDialog(null, "Your credentials are invalid!\nPlease try again.",
               "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        return found;
    }

    // Teo Yih Shing
    // this method is used to check and set the user's usertype
    private String setUserType() {
        String ut = null;

        char[] name = this.view.getUserName().toCharArray();
        if (name[0] == 'S') {
            ut = "Student";
            this.usertype = ut;
        }
        else if (name[0] == 'L') {
            ut = "Lecturer";
            this.usertype = ut;
        }
        else if (name[0] == 'A') {
            ut = "Administrator";
            this.usertype = ut;
        }
        else {
            JOptionPane.showMessageDialog(null, "Your username is invalid!\nPlease try again.",
               "Invalid Input", JOptionPane.ERROR_MESSAGE);
               ut = null;
        }
        return ut;
    }

    // Teo Yih Shing
    // this method is used to set project of student in this class when the user's usertype is "Student" and the credentils is valid. 
    private ProjectModel setProject(String studentName) {
        ProjectModel temp = new ProjectModel();

        try {
            File userData = new File("txt/ProjectList.txt");
            Scanner myReader = new Scanner(userData);
            String data;

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                String[] splited = data.split("    ");

                if (splited[5].equals(studentName)) {
                    temp.setID(splited[0]);
                    temp.setName(splited[1]);
                    temp.setSpecialization(splited[2]);
                    temp.setLecturer_ID(splited[3]);
                    temp.setLecturer_Name(splited[4]);
                    temp.setStudent_ID(splited[5]);
                    temp.setStudent_Name(splited[6]);
                    temp.setStatus(splited[7]);
                    break;
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return temp;
    }

    // Teo Yih Shing
    // this method is used to check the user input is valid or not 
    // if yes, it will redirect the user to the specified screen
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean valid = false;
        if (view.getName().isBlank() || view.getPassword().isBlank()){
            JOptionPane.showMessageDialog(null, "Please enter valid credentials!",
               "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        else{
            try {
                valid = checkUserCredentials();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            if (valid == true) {
                if (this.usertype.equals("Student")) {
                    this.view.dispose();
                    StudentMenuController smc = new StudentMenuController(this.student);
                    smc.initController();
                } else if (this.usertype.equals("Lecturer")) {
                    this.view.dispose();
                    LectureMenuController smc = new LectureMenuController(this.lecturer);
                    smc.initController();
                } else if (this.usertype.equals("Administrator")) {
                    this.view.dispose();
                    AdminMainController smc = new AdminMainController(this.admin);
                    smc.initController();
                }
            }
        }
    }
}