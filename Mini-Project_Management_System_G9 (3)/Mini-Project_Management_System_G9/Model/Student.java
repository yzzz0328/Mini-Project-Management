package Model;

import Model.ProjectModel;
import Model.UserModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Teo Yih Shing
// encapsulation
// subclass of UserModel
public class Student extends UserModel{
    private String nm;
    private String PW;
    private String fnm;
    private String userType;
    private String specialization;
    private String project_ID;
    private ProjectModel project = new ProjectModel();

    // Teo Yih Shing
    // Default constructor
    public Student() {}
    
    // Teo Yih Shing
    // parameterized constructor
    public Student(String nm, String PW, String fnm, String type, String specialization, ProjectModel project) {
        this.nm = nm;
        this.PW = PW;
        this.fnm = fnm;
        this.userType = type;
        this.specialization = specialization;
        this.project = project;
        this.project_ID = this.project.getID();
    }

    // Teo Yih Shing
    // parameterized constructor
    public Student(String nm, String PW, String fnm, String type, String specialization, String project_ID) {
        this.nm = nm;
        this.PW = PW;
        this.fnm = fnm;
        this.userType = type;
        this.specialization = specialization;
        this.project_ID = project_ID;
    }

    // Teo Yih Shing
    // this method is used to get String username in this class
    @Override
    public String getName() {
        return this.nm;
    }

    // Teo Yih Shing
    // this method is used to get String userpassword in this class
    @Override
    public String getPassword() {
        return this.PW;
    }

    // Teo Yih Shing
    // this method is used to get String userType in this class
    @Override
    public String getUserType(){
        this.userType = "Student";
        return userType;
    }

    // Teo Yih Shing
    // this method is used to get String userfullname in this class
    @Override
    public String getUserFullName(){
        return this.fnm;
    }

    // Teo Yih Shing
    // this method is used to get String specialization in this class
    public String getSpecialization() {
        return this.specialization;
    }

    // Teo Yih Shing
    // this method is used to set String specialization in this class
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Teo Yih Shing
    // this method is used to get String project_ID in this class
    public String getProject_ID() {
        return this.project_ID;
    }

    // Teo Yih Shing
    // delegator
    // this method is used to set String project_ID in this class
    public void setProject_ID(String project_ID) {
        this.project_ID = project_ID;
        this.project.setID(project_ID);
    }

    // Teo Yih Shing
    // this method is used to set ProjectModel project in this class
    public void setProject(ProjectModel project) {
        this.project = project;
    }

    // Teo Yih Shing
    // this method is used to get ProjectModel project in this class
    public ProjectModel getProject() {
        return this.project;
    }

    // Teo Yih Shing
    // this method is used to retrieve all the existing student in the "Stident.txt"
    public ArrayList<Student> retrieveStudentList() throws IOException{
        ArrayList<Student> student = new ArrayList<Student>();
        List<String> lines = Files.readAllLines(Paths.get("txt/Student.txt"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split("    ");
            student.add(new Student(items[0], items[1], items[2], items[3], items[4], items[5]));
        }
        return student;
    }
    
    // Teo Yih Shing
    // this method is used to save the ArrayList<Student> s into the "Student.txt"
    public void saveStudentToFile(ArrayList<Student> s) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.size(); i++)
            sb.append (s.get(i).toString() + "\n");
        Files.write(Paths.get("txt/Student.txt"), sb.toString().getBytes());
    }
    
    // Teo Yih Shing
    // polymorphism
    // this method is used to get this class as String
    @Override
    public String toString() {
        return (getName() + "    " + getPassword() + "    " + getUserFullName() + "    " + getUserType() + "    " + this.specialization + "    " + this.project_ID);
    }
}