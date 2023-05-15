package View;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

// Teoh Ye Zhian
public class LectureAccountView extends JFrame {
    JLabel title = new JLabel("Lecturer Account Information");
    JLabel id = new JLabel("Lecturer ID:");
    JLabel lecturerID = new JLabel();
    JLabel name = new JLabel("Full Name:");
    JLabel lecturerName = new JLabel();
    JLabel type = new JLabel("User Type:");
    JLabel userType = new JLabel("Lecturer");
    JButton back = new JButton("Back");

    JPanel titlePanel = new JPanel();
    JPanel idPanel = new JPanel();
    JPanel detailPanel = new JPanel(new GridLayout(3, 2));
    JPanel detailPPanel = new JPanel();
    JPanel backBPanel = new JPanel();

    // Teoh Ye Zhian
    // default constructor to store the view screen
    public LectureAccountView() {
        super("MMU Online Mini-project Management System");

        this.title.setFont(new Font("Serif", Font.PLAIN, 50));
        this.titlePanel.setPreferredSize(new Dimension(800, 80));
        this.titlePanel.add(this.title);

        this.detailPanel.setPreferredSize(new Dimension(300, 200));

        this.id.setFont(new Font("Serif", Font.PLAIN, 20));
        this.idPanel.add(id);
        this.detailPanel.add(this.id);

        this.lecturerID.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.lecturerID);

        this.name.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.name);

        this.lecturerName.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.lecturerName);

        this.type.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.type);

        this.userType.setFont(new Font("Serif", Font.PLAIN, 20));
        this.detailPanel.add(this.userType);

        this.detailPPanel.add(detailPanel);

        this.back.setFont(new Font("Serif", Font.PLAIN, 30));
        this.backBPanel.setPreferredSize(new Dimension(800, 60));
        this.backBPanel.add(this.back);

        GroupLayout viewBoardPane = new GroupLayout(getContentPane());
        getContentPane().setLayout(viewBoardPane);
        viewBoardPane.setAutoCreateGaps(true);
        viewBoardPane.setAutoCreateContainerGaps(true);

        viewBoardPane.setHorizontalGroup(viewBoardPane.createParallelGroup(Alignment.CENTER)
                .addComponent(titlePanel)
                .addComponent(detailPPanel)
                .addComponent(backBPanel));

        viewBoardPane.setVerticalGroup(viewBoardPane.createSequentialGroup()
                .addComponent(titlePanel)
                .addComponent(detailPPanel)
                .addComponent(backBPanel));

        this.setMinimumSize(new Dimension(800, 600));
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Teoh Ye Zhian
    // this method sets the lecturer ID
    public void setID(String id) {
        this.lecturerID.setText(id);
    }

    // Teoh Ye Zhian
    // this method sets the lecturer name
    public void setFullName(String name) {
        this.lecturerName.setText(name);
    }

    // Teoh Ye Zhian
    // this method gets the back button
    public JButton getBackButton() {
        return this.back;
    }
}
