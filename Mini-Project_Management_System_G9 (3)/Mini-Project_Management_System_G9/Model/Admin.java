package Model;

import Model.UserModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Lam Ting Le & Chang Siu Hong
// subclass of UserModel
// encapsulation
public class Admin extends UserModel {
    private String nm;
    private String PW;
    private String fnm;
    private String userType;

    // Lam Ting Le & Chang Siu Hong
    // default constructor
    public Admin() {}

    // Lam Ting Le & Chang Siu Hong
    // parameterized constructor
    public Admin(String nm, String PW, String fnm) {
        this.nm = nm;
        this.PW = PW;
        this.fnm = fnm;
        this.userType = "Administrator";
    }

    // Lam Ting Le & Chang Siu Hong
    // this method is used to get String username in this class
    @Override
    public String getName() {
        return this.nm;
    }

    // Lam Ting Le & Chang Siu Hong
    // this method is used to get String userpassword in this class
    @Override
    public String getPassword() {
        return this.PW;
    }

    // Lam Ting Le & Chang Siu Hong
    // this method is used to get String userType in this class
    @Override
    public String getUserType(){
        this.userType = "Administrator";
        return userType;
    }

    // Lam Ting Le & Chang Siu Hong
    // this method is used to get String userfullname in this class
    @Override
    public String getUserFullName(){
        return this.fnm;
    }

    // Lam Ting Le &  Chang Siu Hong
    // this method is to retreive admin details from "Admin.txt" file
    // and loads the admin details into the arraylist of admin
    public ArrayList<Admin> retrieveAdminList() throws IOException{
        ArrayList<Admin> admin = new ArrayList<Admin>();
        List<String> lines = Files.readAllLines(Paths.get("txt/Admin.txt"));
        for (int i = 0; i < lines.size(); i++) {
            String[] items = lines.get(i).split("    ");
            admin.add(new Admin(items[0], items[1], items[2]));
        }
        return admin;
    }

    // Lam Ting Le & Chang Siu Hong
    // this method is to save modified arraylist of admin into "Admin.txt" file
    public void saveAdminToFile(ArrayList<Admin> a) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.size(); i++)
            sb.append (a.get(i).toString() + "\n");
        Files.write(Paths.get("txt/Admin.txt"), sb.toString().getBytes());
    }

    // Lam Ting Le & Chang Siu Hong
    // polymorphism
    // this method is used to print data to a list of String
    @Override
    public String toString() {
        return (getName() + "    " + getPassword() + "    " + getUserFullName() + "    " + getUserType());
    }
}