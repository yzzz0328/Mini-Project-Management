package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// encapsulation
public class ProjectModel{
    private String ID;
    private String name;
    private String specialization;
    private String lecturer_ID;
    private String lecturer_name;
    private String student_ID;
    private String student_name;
    private String status;
    private ArrayList<CommentModel> comments = new ArrayList<CommentModel>();

    // Chang Siu Hong
    // Default constructor
    public ProjectModel() {
        this.ID = "";
        this.name = "";
        this.specialization = "";
        this.lecturer_ID = "";
        this.lecturer_name = "";
        this.student_ID = "";
        this.student_name = "";
        this.status = "";
    }

    // Chang Siu Hong
    // parameterized constructor
    public ProjectModel(String p_id, String p_nm, String sp, String l_id, String l_nm, String s_ID, String s_nm, String s) {
        this.ID = p_id;
        this.name = p_nm;
        this.specialization = sp;
        this.lecturer_ID = l_id;
        this.lecturer_name = l_nm;
        this.student_ID = s_ID;
        this.student_name = s_nm;
        this.status = s;
    }
    
    // Chang Siu Hong
    // For composition use
    public ProjectModel(ArrayList<CommentModel> comments) {
        this.comments = comments;
    }

    // Chang Siu Hong
    // For composition use
    // this method is used to get ArrayList<CommentModel> comments in this class
    public ArrayList<CommentModel> getTotalComments() {
        return this.comments;
    }

    // Chang Siu Hong
    // this method is used to get String ID in this class
    public String getID() {
        return this.ID;
    }

    // Chang Siu Hong
    // this method is used to set String ID in this class
    public void setID(String id) {
        this.ID = id;
    }

    // Chang Siu Hong
    // this method is used to get String Name in this class
    public String getName() {
        return this.name;
    }

    // Chang Siu Hong
    // this method is used to set String name in this class
    public void setName(String nm) {
        this.name = nm;
    }

    // Chang Siu Hong
    // this method is used to get String specialization in this class
    public String getSpecialization() {
        return this.specialization;
    }

    // Chang Siu Hong
    // this method is used to set String specialization in this class
    public void setSpecialization(String sp) {
        this.specialization = sp;
    }

    // Chang Siu Hong
    // this method is used to get String lecturer_ID in this class
    public String getLecturer_ID() {
        return this.lecturer_ID;
    }

    // Chang Siu Hong
    // this method is used to set String lecturer_ID in this class
    public void setLecturer_ID(String lecturer_ID) {
        this.lecturer_ID = lecturer_ID;
    }

    // Chang Siu Hong
    // this method is used to get String lecturer_name in this class
    public String getLecturer_Name() {
        return this.lecturer_name;
    }

    // Chang Siu Hong
    // this method is used to set String lecturer_name in this class
    public void setLecturer_Name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    // Chang Siu Hong
    // this method is used to get String student_ID in this class
    public String getStudent_ID() {
        return this.student_ID;
    }

    // Chang Siu Hong
    // this method is used to set String student_ID in this class
    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    // Chang Siu Hong
    // this method is used to get String student_name in this class
    public String getStudent_Name() {
        return this.student_name;
    }

    // Chang Siu Hong
    // this method is used to set String student_name in this class
    public void setStudent_Name(String student_name) {
        this.student_name = student_name;
    }

    // Chang Siu Hong
    // this method is used to get String status in this class
    public String getStatus() {
        return this.status;
    }
    
    // Chang Siu Hong
    // this method is used to set String status in this class
    public void setStatus(String status) {
        this.status = status;
    }

    // Chang Siu Hong
    // this method is used to retrieve all the existing project details in the "ProjectList.txt"
    // and load the project detials into arraylist of project model
    public ArrayList<ProjectModel> retrieveExistingProject() throws IOException{
        ArrayList<ProjectModel> project = new ArrayList<ProjectModel>();
        List<String> lines = Files.readAllLines(Paths.get("txt/ProjectList.txt"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split("    ");
            project.add(new ProjectModel(items[0], items[1], items[2], items[3], items[4], items[5], items[6], items[7]));
        }
        return project;
    }
    
    // Chang Siu Hong
    // this method is used to save the ArrayList<ProjectModel> list into the "ProjectList.txt"
    public void saveProjectToFile(ArrayList<ProjectModel> list) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++)
            sb.append (list.get(i).toString() + "\n");
        Files.write(Paths.get("txt/ProjectList.txt"), sb.toString().getBytes());
    }

    // Chang Siu Hong
    // polymorphism
    // this method is used to get this class as String
    @Override
    public String toString() {
        return (this.ID + "    " + this.name + "    " + this.specialization + "    " + this.lecturer_ID + "    " + this.lecturer_name + "    " + this.student_ID + "    " 
                + this.student_name + "    " + this.status);
    }
}
