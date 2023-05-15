package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Teoh Ye Zhian
public class ContentModel {
    private String projectID;
    private String LecturerID;
    private String LecturerName;
    private String updateDateTime;
    private String content;

    // Teoh Ye Zhian
    // default constructor
    public ContentModel() {}

    // Teoh Ye Zhian
    // parameterized constructor
    public ContentModel(String pID, String lecID, String lecName, String DateTime, String c) {
        this.projectID = pID;
        this.LecturerID = lecID;
        this.LecturerName = lecName;
        this.updateDateTime = DateTime;
        this.content = c;
    }

    // Teoh Ye Zhian
    // this method saves the content to the txt file
    public void saveContenttToFile(ArrayList<ContentModel> arrayList) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++)
            sb.append(arrayList.get(i).toString() + "\n");
        Files.write(Paths.get("txt/content.txt"), sb.toString().getBytes());
    }
    
    // Teoh Ye Zhian
    // this method retrieves content from txt file
    public ArrayList<ContentModel> retrieveContents() throws IOException {
        ArrayList<ContentModel> contentModels = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("txt/content.txt"));
        boolean hasNextLine = false;

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isEmpty() == false) {
                String[] items = lines.get(i).split("    ");
                contentModels.add(new ContentModel(items[0], items[1], items[2], items[3], items[4]));
                hasNextLine = checkHasNextLine(lines, i);
                
                while (hasNextLine == true) {
                    String nextLine = lines.get(i + 1);
                    contentModels.get(contentModels.size() - 1)
                            .setContent(contentModels.get(contentModels.size() - 1).getContent() + "\n" + nextLine);
                    i++;
                    hasNextLine = checkHasNextLine(lines, i);
                }
            }
        }
        return contentModels;
    }

    // Teoh Ye Zhian
    // this method returns the boolean of whether the next line is an empty line in txt file
    private boolean checkHasNextLine(List<String> lines, int i) {
        boolean hasNextLine = false;

        if (i < lines.size()-1) {
            if (lines.get(i + 1).isEmpty() == false)
                hasNextLine = true;
        }

        return hasNextLine;
    }

    // Teoh Ye Zhian
    // this method tries to return the content.
    public String displayContent(String projectID) throws IOException {
        ArrayList<ContentModel> arrayList = retrieveContents();
        ArrayList<ContentModel> arrayList2 = new ArrayList<ContentModel>();
        String content = " ";

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getProjectID().equals(projectID))
                arrayList2.add(arrayList.get(i));
        }

        if (arrayList2.size() != 0) {
            int lastIdx = arrayList2.size() - 1;
            String lastcontent = arrayList2.get(lastIdx).getContent();
            content =  "<html>" + lastcontent.replaceAll("<","&lt;")
                        .replaceAll(">", "&gt;").replaceAll("\n", "<br/>") 
                        + "</html>";
        }
        return content;
    }

    // Teoh Ye Zhian
    // this method returns project ID
    public String getProjectID() {
        return projectID;
    }

    // Teoh Ye Zhian
    // this method sets the project ID
    public void setProjectID(String pID) {
        this.projectID = pID;
    }

    // Teoh Ye Zhian
    // this method returns lecturer ID
    public String getLecturerID() {
        return LecturerID;
    }

    // Teoh Ye Zhian
    // this method sets lecturer ID
    public void setLecturerID(String LecID) {
        this.LecturerID = LecID;
    }

    // Teoh Ye Zhian
    // this method returns lecturer name
    public String getLecturerName() {
        return LecturerName;
    }

    // Teoh Ye Zhian
    // this method sets lecturer name
    public void setLecturerName(String LecturerName) {
        this.LecturerName = LecturerName;
    }

    // Teoh Ye Zhian
    // this method returns the date and time
    public String getupdateDateTime() {
        return updateDateTime;
    }

    // Teoh Ye Zhian
    // this method sets the date and time
    public void setupdateDateTime(String DateTime) {
        this.updateDateTime = DateTime;
    }

    // Teoh Ye Zhian
    // this method returns the project content
    public String getContent() {
        return content;
    }

    // Teoh Ye Zhian
    // this method sets the project content
    public void setContent(String content) {
        this.content = content;
    }

    // this method is used to print data to a list of String
    @Override
    public String toString() {
        return (this.projectID + "    " + this.LecturerID + "    " + this.LecturerName
                + "    " + this.updateDateTime + "    " + this.content + "\n" );
    }
}

