package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Teoh Ye Zhian & Thong Kai Chin
// subclass of UserModel
// encapsulation
public class Lecturer extends UserModel {
    private String nm;
    private String PW;
    private String fnm;
    private String userType;

    // Teoh Ye Zhian & Thong Kai Chin
    // default constructor
    public Lecturer(){}

    // Teoh Ye Zhian & Thong Kai Chin
    // parameterized construction
    public Lecturer(String nm, String PW, String fnm) {
        this.nm = nm;
        this.PW = PW;
        this.fnm = fnm;
        this.userType = "Lecturer";
    }

    // this method is used to get String username in this class
    @Override
    public String getName() {
        return this.nm;
    }
    // this method is used to get String userpassword in this class
    @Override
    public String getPassword() {
        return this.PW;
    }

    // this method is used to get String userType in this class
    @Override
    public String getUserType(){
        this.userType = "Lecturer";
        return userType;
    }

    // this method is used to get String userfullname in this class
    @Override
    public String getUserFullName(){
        return this.fnm;
    }

    // Teoh Ye Zhian & Thong Kai Chin
    // this method retreives all lecturer list from txt file.
    public ArrayList<Lecturer> retrieveLecturerList() throws IOException{
        ArrayList<Lecturer> lecturer = new ArrayList<Lecturer>();
        List<String> lines = Files.readAllLines(Paths.get("txt/Lecturer.txt"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split("    ");
            lecturer.add(new Lecturer(items[0], items[1], items[2]));
        }
        return lecturer;
    }
    
    // Teoh Ye Zhian & Thong Kai Chin
    // this method saves the lecturer to the txt file.
    public void saveLecturerToFile(ArrayList<Lecturer> l) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l.size(); i++)
            sb.append (l.get(i).toString() + "\n");
        Files.write(Paths.get("txt/Lecturer.txt"), sb.toString().getBytes());
    }
    
    // polymorphism
    @Override
    public String toString() {
        return (getName() + "    " + getPassword() + "    " + getUserFullName() + "    " + getUserType());
    }
}