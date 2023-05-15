package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Lam Ting Le
// Encapsulation (getter, setter)
public class CommentModel {
    private String projectID; // Project ID
    private String ID; // User ID
    private String name; // Username (Real name / Full name)
    private String commentDateTime; // Date and time the comment is added by the user
    private String comment; // Project comment

    // Lam Ting Le
    // Constructs a default comment object
    public CommentModel() {
    }

    // Lam Ting Le
    // Constructs a comment object with the parameters
    // The project ID to determine where the comment is in
    // The ID of the user who commented (Admin)
    // The name of the user who commented
    // The date and time the user commented
    // The comments added by the user
    public CommentModel(String projectID, String ID, String name, String commentDateTime, String comment) {
        this.projectID = projectID;
        this.ID = ID;
        this.name = name;
        this.commentDateTime = commentDateTime;
        this.comment = comment;
    }

    // Lam Ting Le
    // Returns the project ID of this comment
    public String getProjectID() {
        return projectID;
    }

    // Lam Ting Le
    // Sets a new project ID for this comment
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    // Lam Ting Le
    // Returns the user ID of this comment
    public String getID() {
        return ID;
    }

    // Lam Ting Le
    // Sets a new user ID for this comment
    public void setID(String ID) {
        this.ID = ID;
    }

    // Lam Ting Le
    // Returns the username of this comment
    public String getName() {
        return name;
    }

    // Lam Ting Le
    // Sets a new username for this comment
    public void setName(String name) {
        this.name = name;
    }

    // Lam Ting Le
    // Returns the date and time of this comment
    public String getCommentDateTime() {
        return commentDateTime;
    }

    // Lam Ting Le
    // Sets a new data and time for this comment
    public void setCommentDateTime(String commentDateTime) {
        this.commentDateTime = commentDateTime;
    }

    // Lam Ting Le
    // Returns the comment
    public String getComment() {

        return comment;
    }

    // Lam Ting Le
    // Sets a new comment
    public void setComment(String comment) {
        this.comment = comment;
    }

    // Lam Ting Le
    // Method to retrieve comments data from text file
    // Create a CommentModel ArrayList
    // Read all the data in the file, "comments.txt"
    // If the line is not empty, store the data into an array
    // Create a new CommentModel object and add the data by accessing the array elements
    // Add the object into the ArrayList
    // Invoke method to check if there is comments in a new line
    // While true, get the comment in the next line and sets the comments together
    // Repeat until there is no comment left
    public ArrayList<CommentModel> retrieveComments() throws IOException {
        ArrayList<CommentModel> commentModels = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("txt/comments.txt"));
        boolean hasNextLine = false;

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isEmpty() == false) {
                String[] items = lines.get(i).split("    ");
                commentModels.add(new CommentModel(items[0], items[1], items[2], items[3], items[4]));
                hasNextLine = checkHasNextLine(lines, i);

                while (hasNextLine == true) {
                    String nextLine = lines.get(i + 1);
                    commentModels.get(commentModels.size() - 1)
                            .setComment(commentModels.get(commentModels.size() - 1).getComment() + "\n" + nextLine);
                    i++;
                    hasNextLine = checkHasNextLine(lines, i);
                }
            }
        }
        return commentModels;
    }

    // Lam Ting Le
    // Check if the next line is empty or contain comments
    private boolean checkHasNextLine(List<String> lines, int i) {
        boolean hasNextLine = false;

        if (i < lines.size()-1) {
            if (lines.get(i + 1).isEmpty() == false)
                hasNextLine = true;
        }

        return hasNextLine;
    }

    // Lam Ting Le
    // Print the data in the CommentModel ArrayList into the file, "comments.txt"
    public void saveCommentToFile(ArrayList<CommentModel> arrayList) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++)
            sb.append (arrayList.get(i).toString() + "\n");
        Files.write(Paths.get("txt/comments.txt"), sb.toString().getBytes());
    }

    // Lam Ting Le
    // Returns a string with comment data
    public String toString() {
        return projectID + "    " + ID + "    " + name + "    " + commentDateTime + "    " + comment + "\n";
    }
}
