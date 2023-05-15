package Controller;

import Model.Admin;
import Model.Lecturer;
import Model.Student;
import Model.UserModel;
import View.AdminRegisterView;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

// Chang Siu Hong
public class AdminRegisterController{
    private Admin model;
    private Lecturer lmodel = new Lecturer();
    private Student smodel = new Student();
    private String usertype;
    private String userName;
    private String userPW;
    private String userFullName;

    private AdminRegisterView view;

    // Chang Siu Hong
    // parameterized constructor
    public AdminRegisterController(Admin m, AdminRegisterView v) {
        this.model = m;
        this.view = v;
    }

    // Chang Siu Hong
    // initial controller that performs certain functions whenever certain buttons
    // is pressed, i.e. Submit buttons and back buttons.
    public void initController() {
        view.getSubmitButtonStudent().addActionListener(e -> {
            try {
                saveUserDetails();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        view.getSubmitButtonLecturer().addActionListener(e -> {
            try {
                saveUserDetails();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        view.getSubmitButtonAdmin().addActionListener(e -> {
            try {
                saveUserDetails();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        view.getBackButtonAdmin().addActionListener(e -> {
            view.dispose();
            AdminMainController smc = new AdminMainController(this.model);
            smc.initController();
        });
        view.getBackButtonLecturer().addActionListener(e -> {
            view.dispose();
            AdminMainController smc = new AdminMainController(this.model);
            smc.initController();
        });
        view.getBackButtonMain().addActionListener(e -> {
            view.dispose();
            AdminMainController smc = new AdminMainController(this.model);
            smc.initController();
        });
        view.getBackButtonStudent().addActionListener(e -> {
            view.dispose();
            AdminMainController smc = new AdminMainController(this.model);
            smc.initController();
        });
    }

    // Chang Siu Hong
    // this method tries to catch any errors with the input, i.e. when nothing is inputted,
    // error message will be popped out as well as if the user ID is used. If no errors is deteced, 
    // details will be added into the arraylist and stored into the selected user type txt file.
    private void saveUserDetails() throws IOException {
        this.usertype = getUserType();
        this.userName = saveUserID();
        this.userPW = saveUserPW();
        this.userFullName = saveUserName();

        boolean valid = false;
        boolean found = false;

        if (this.userName.equals("BLANK") || checkInputUserPW().isBlank() || userFullName.isBlank()) {
            JOptionPane.showMessageDialog(null, "Fill in the necessary fields. \nPlease try again.",
               "Invalid Input", JOptionPane.ERROR_MESSAGE);
               valid = false;
        }
        else if (userName == null) {
            valid = false;
        }
        else if (userPW == "" ) {
            valid = false;
        }
        else {
            if (this.usertype == "Student") {
                String specialization = saveUserSpecialization();
                if (specialization == " ") {
                    valid = false;
                } else {
                    ArrayList<Student> student = smodel.retrieveStudentList();
                    found = checkExistingStudent(student, userName);
                    if (found == false) {
                        String noassignedproject = "null";
                        student.add(new Student(userName, userPW, userFullName, usertype, specialization, noassignedproject));
                        smodel.saveStudentToFile(student);
                        JOptionPane.showMessageDialog(null, "Student details has been created.",
                        "Successful Registration", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else if (this.usertype == "Lecturer"){
                ArrayList<Lecturer> lecturer = lmodel.retrieveLecturerList();
                found = checkExistingLecturer(lecturer, userName);
                if (found == false) {
                    lecturer.add(new Lecturer(userName, userPW, userFullName));
                    lmodel.saveLecturerToFile(lecturer);
                    JOptionPane.showMessageDialog(null, "Lecturer details has been created.",
                        "Successful Registration", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (this.usertype == "Administrator"){
                ArrayList<Admin> admin = model.retrieveAdminList();
                found = checkExistingAdmin (admin, userName);
                if (found == false) {
                    admin.add(new Admin(userName, userPW, userFullName));
                    model.saveAdminToFile(admin);
                    JOptionPane.showMessageDialog(null, "Administrator details has been created.",
                        "Successful Registration", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    // Chang Siu Hong
    // this method checks for the existence of user ID in the existing list of students and
    // return found = true if the User ID is found, else return false.
    private boolean checkExistingStudent(ArrayList<Student> students, String ID) {
        boolean found = false;
        for (Student s: students) {
            if (s.getName().equals(ID)) {
                JOptionPane.showMessageDialog(null, "Student ID has been taken. \nPlease try again.",
                        "Invalid ID", JOptionPane.ERROR_MESSAGE);
                found = true;
                break;
            }
        }
        return found;
    }

    // Chang Siu Hong
    // this method checks for the existence of user ID in the existing list of lecturers and
    // return found = true if the User ID is found, else return false.
    private boolean checkExistingLecturer(ArrayList<Lecturer> lecturers, String ID) {
        boolean found = false;
        for (Lecturer l: lecturers) {
            if (l.getName().equals(ID)) {
                JOptionPane.showMessageDialog(null, "Lecturer ID has been taken. \nPlease try again.",
                        "Invalid ID", JOptionPane.ERROR_MESSAGE);
                found = true;
                break;
            }
        }
        return found;
    }

    // Chang Siu Hong
    // this method checks for the existence of user ID in the existing list of admins and
    // return found = true if the User ID is found, else return false.
    private boolean checkExistingAdmin(ArrayList<Admin> admins, String ID) {
        boolean found = false;
        for (Admin a: admins) {
            if (a.getName().equals(ID)) {
                JOptionPane.showMessageDialog(null, "Admin ID has been taken. \nPlease try again.",
                        "Invalid ID", JOptionPane.ERROR_MESSAGE);
                found = true;
                break;
            }
        }
        return found;
    }

    // Chang Siu Hong
    // this method returns the selected user type
    private String getUserType() {
        return view.getUserTypeChoice().getSelectedItem().toString();
    }

    // Chang Siu Hong
    // this method returns the user ID if there is no error detected in several validation processes.
    // If nothing is entered, a string of "BLANK" is assigned to user ID, else if user ID does not
    // equal to the length of 6, error message will be popped out and valid is set to false, else, 
    // if the first character doesn't follow the user type format, valid is set to false and error message 
    // will be popped out and lastly, if the remaining characters are not digits, valid is set to false
    // and error messgae will be popped out. 
    // If valid == true, user ID will be returned, else null will be returned.
    private String saveUserID() {
        boolean valid = true;
        if (this.usertype.equals("Administrator") || this.usertype.equals("Student") || this.usertype.equals("Lecturer")) {
            if (this.usertype.equals("Administrator")) {
                this.userName = view.getUserIDTextAdmin().getText();
                if (this.userName.isBlank()){
                    this.userName = "BLANK";
                }
                else if (this.userName.length() != 6) {
                    valid = false;
                    JOptionPane.showMessageDialog(null, "Please follow the correct ID format!\nA#####",
                    "Incorrect Admin ID", JOptionPane.ERROR_MESSAGE);}
                else {
                    for (int i = 0; i< this.userName.length(); i++) {
                        if (i == 0) {
                            if (this.userName.charAt(i) != 'A') {
                                JOptionPane.showMessageDialog(null, "Please follow the correct ID format!\nA#####",
                                "Incorrect Admin ID", JOptionPane.ERROR_MESSAGE);
                                valid = false;
                                break; }
                        } else {
                            if (!(Character.isDigit(this.userName.charAt(i)))) {
                                JOptionPane.showMessageDialog(null, "Please follow the correct ID format!\nA#####",
                                "Incorrect Admin ID", JOptionPane.ERROR_MESSAGE);
                                valid = false;
                                break; }
                        }
                    }
                }
            }
            else if (this.usertype.equals("Student")) {
                this.userName = view.getUserIDTextStudent().getText();
                if (this.userName.isBlank()){
                    this.userName = "BLANK";
                }
                else if (this.userName.length() != 6) {
                    valid = false;
                    JOptionPane.showMessageDialog(null, "Please follow the correct ID format!\nS#####",
                    "Incorrect Student ID", JOptionPane.ERROR_MESSAGE);}
                else {
                    for (int i = 0; i<this.userName.length(); i++) {
                        if (i == 0) {
                            if (this.userName.charAt(i) != 'S') {
                                JOptionPane.showMessageDialog(null, "Please follow the correct ID format!\nS#####",
                                "Incorrect Student ID", JOptionPane.ERROR_MESSAGE);
                                valid = false;
                                break; }
                        } else {
                            if (!(Character.isDigit(this.userName.charAt(i)))) {
                                JOptionPane.showMessageDialog(null, "Please follow the correct ID format!\nS#####",
                                "Incorrect Student ID", JOptionPane.ERROR_MESSAGE);
                                valid = false;
                                break; }
                        }
                    }
                }
            }
            else if (this.usertype.equals("Lecturer")){
                this.userName = view.getUserIDTextLecturer().getText();
                if (this.userName.isBlank()){
                    this.userName = "BLANK";
                }
                else if (this.userName.length() != 6) {
                    valid = false;
                    JOptionPane.showMessageDialog(null, "Please follow the correct ID format!\nL#####",
                    "Incorrect Lecturer ID", JOptionPane.ERROR_MESSAGE);}
                else {
                    for (int i = 0; i<this.userName.length(); i++) {
                        if (i == 0) {
                            if (this.userName.charAt(i) != 'L') {
                                JOptionPane.showMessageDialog(null, "Please follow the correct ID format!\nL#####",
                                "Incorrect Lecturer ID", JOptionPane.ERROR_MESSAGE);
                                valid = false;
                                break; }
                        } else {
                            if (!(Character.isDigit(this.userName.charAt(i)))) {
                                JOptionPane.showMessageDialog(null, "Please follow the correct ID format!\nL#####",
                                "Incorrect Lecturer ID", JOptionPane.ERROR_MESSAGE);
                                valid = false;
                                break; }
                        }
                    }
                }
            }
        }

        if (valid == true)
            return this.userName;
        else
            return null;
    }

    // Chang Siu Hong
    // this method returns the password entered by the admin
    private String checkInputUserPW() {
        if (this.usertype.equals("Administrator")) this.userPW = view.getUserPWTextAdmin().getText();
        else if(this.usertype.equals("Student")) this.userPW = view.getUserPWTextStudent().getText();
        else if (this.usertype.equals("Lecturer")) this.userPW = view.getUserPWTextLecturer().getText();
        return this.userPW;
    }

    // Chang Siu Hong
    // this method tries to save the password by passing the password from checkInputUserPW()
    // into a method for verification purpose and password will be returned.
    private String saveUserPW() {
        String temppw = checkInputUserPW();
        String pw = checkPasswordValidity(temppw);
        return pw;
    }

    // Chang Siu Hong
    // this method tries to check for the password requirements, the password must consist 7 -15 characters of 
    // at least one letter and one digit, must contain no special characters or space. Error message will be 
    // prompted if the password doesn't meet the requirements, else password is valid and will be returned. 
    private String checkPasswordValidity(String userPassword) {
        final int PASSWORD_MIN_LENGTH = 7;
        final int PASSWORD_MAX_LENGTH = 15;
        int digit = 0;
        int letter = 0;
        String temppw = "";
        boolean valid = true;

        if (userPassword.length() >= PASSWORD_MIN_LENGTH && userPassword.length() <= PASSWORD_MAX_LENGTH) {
            for (int i = 0; i < userPassword.length(); i++) {
                // count the number of digit and check if there's digit inserted
                if (Character.isDigit(userPassword.charAt(i)))
                    digit++;
                // count the number of letter
                if (Character.isLetter(userPassword.charAt(i)))
                    letter++;
            }

            if (digit == 0) {
                JOptionPane.showMessageDialog(null, "Your password must have at least one digit.",
                "Incorrect Password Format", JOptionPane.ERROR_MESSAGE);
                valid = false;
            } else if (letter == 0) {
                JOptionPane.showMessageDialog(null, "Your password must have at least one letter.",
                 "Incorrect Password Format", JOptionPane.ERROR_MESSAGE);
                valid = false;
            } else {
                for (int j = 0; j<userPassword.length(); j++) {
                    if (Character.isWhitespace(userPassword.charAt(j))) {
                        JOptionPane.showMessageDialog(null, "Password must not have space.",
                                    "Incorrect Password Format", JOptionPane.ERROR_MESSAGE);
                        valid = false;
                        break;
                    } else if (!Character.isDigit(userPassword.charAt(j)) && !Character.isLetter(userPassword.charAt(j))) {
                        // if the character is neither a digit nor a letter, the password has error
                        JOptionPane.showMessageDialog(null, "Password must not have any special characters.",
                                    "Incorrect Password Format", JOptionPane.ERROR_MESSAGE);
                        valid = false;
                        break;}
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Password must be "+ PASSWORD_MIN_LENGTH + " - " + PASSWORD_MAX_LENGTH + " long.",
                            "Incorrect Password Length", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }

        if (valid == true ) temppw = userPassword;
        return temppw;
    }

    // Chang Siu Hong
    // this method returns username entered by admin
    private String saveUserName() {
        if (this.usertype.equals("Administrator")) this.userFullName = view.getUserUserNameTextAdmin().getText();
        else if(this.usertype.equals("Student")) this.userFullName = view.getUserUserNameTextStudent().getText();
        else if (this.usertype.equals("Lecturer")) this.userFullName = view.getUserUserNameTextLecturer().getText();
        return this.userFullName;
    }

    // Chang Siu Hong
    // this method returns the specialization of the student entered by admin and pop out error
    // message if no specialization is chosen.
    private String saveUserSpecialization() {
        String specialization = view.getStudent_Specialization().getSelectedItem().toString();
        if (specialization == " ") {
            JOptionPane.showMessageDialog(null, "Please choose a valid specialization!",
               "Error!", JOptionPane.ERROR_MESSAGE);
        }
        else {
            return specialization;
        }
        return specialization;
    }
}
