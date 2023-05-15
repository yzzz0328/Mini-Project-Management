package View;

import java.awt.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.*;

// Teo Yih Shing
public class LoginView extends JFrame{
    private JTextField userNameTextField = new JTextField(16);
    private JTextField passwordPasswordField = new JPasswordField(16);
    private JLabel titleLabel1 = new JLabel("MMU Online Mini-project");
    private JLabel titleLabel2 = new JLabel("Management System");
    private JLabel userNameLabel = new JLabel("User Name:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JLabel errorMessageLabel = new JLabel();
    private JLabel tryAgainLabel = new JLabel();
    
    private JPanel blankPanel = new JPanel();
    private JPanel loginPanel = new JPanel(new GridLayout(2, 2));
    private JPanel logPPanel = new JPanel();
    private JPanel userNameLPanel = new JPanel();
    private JPanel passwordLPanel = new JPanel();
    private JPanel userNameTFPanel = new JPanel();
    private JPanel passwordPFPanel = new JPanel();
    private JPanel loginBPanel = new JPanel();
    private JButton loginButton = new JButton("Log In");

    // Teo Yih Shing
     // default constructor to store the view screen
    public LoginView() {
        super("MMU Online Mini-project Management System");

        this.titleLabel1.setFont(new Font("Serif", Font.PLAIN, 50));
        this.titleLabel2.setFont(new Font("Serif", Font.PLAIN, 50));

        this.blankPanel.setPreferredSize(new Dimension(800, 10));

        this.userNameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        this.userNameLPanel.add(this.userNameLabel);
        this.userNameTFPanel.setPreferredSize(new Dimension(400, 55));
        this.userNameTFPanel.add(this.userNameTextField);
        
        this.passwordLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        this.passwordLPanel.add(this.passwordLabel);
        this.passwordPFPanel.setPreferredSize(new Dimension(400, 55));
        this.passwordPFPanel.add(this.passwordPasswordField);
        
        this.loginPanel.setPreferredSize(new Dimension(550, 150));
        this.loginPanel.add(userNameLPanel);
        this.loginPanel.add(userNameTFPanel);
        this.loginPanel.add(passwordLPanel);
        this.loginPanel.add(passwordPFPanel);
        this.logPPanel.add(loginPanel);

        this.errorMessageLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        this.errorMessageLabel.setForeground(Color.RED);

        this.tryAgainLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        this.tryAgainLabel.setForeground(Color.RED);

        this.loginButton.setFont(new Font("Serif", Font.PLAIN, 20));
        this.loginBPanel.add(this.loginButton);

        GroupLayout viewBoardPane = new GroupLayout(getContentPane());
        getContentPane().setLayout(viewBoardPane);
        viewBoardPane.setAutoCreateGaps(true);
        viewBoardPane.setAutoCreateContainerGaps(true);

        viewBoardPane.setHorizontalGroup(viewBoardPane.createParallelGroup(Alignment.CENTER)
                .addComponent(titleLabel1)
                .addComponent(titleLabel2)
                .addComponent(blankPanel)
                .addComponent(logPPanel)
                .addComponent(errorMessageLabel)
                .addComponent(tryAgainLabel)
                .addComponent(loginBPanel)
                );

        viewBoardPane.setVerticalGroup(viewBoardPane.createSequentialGroup()
                .addComponent(titleLabel1)
                .addComponent(titleLabel2)
                .addComponent(blankPanel)
                .addComponent(logPPanel)
                .addComponent(errorMessageLabel)
                .addComponent(tryAgainLabel)
                .addComponent(loginBPanel)
                );

        this.setMinimumSize(new Dimension(800, 600));
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Teo Yih Shing
    // this method is used to get String username that user input in the JTextField userNameTextField
    public String getUserName() {
        return this.userNameTextField.getText();
    }

    // Teo Yih Shing
    // this method is used to get String password that user input in the JTextField passwordPasswordField
    public String getPassword() {
        return this.passwordPasswordField.getText();
    }

    // Teo Yih Shing
    // this method is used to set the String text as an error message in the JLabel errorMessageLabel
    public void setErrorMessage(String text) {
        this.errorMessageLabel.setText(text);
    }

    // Teo Yih Shing
    // this method is used to set the String text as an try again message in the JLabel tryAgainLabel
    public void setTryAgain(String text) {
        this.tryAgainLabel.setText(text);
    }

    // Teo Yih Shing
    // this method is used to get JButton loginButton
    public JButton getLoginButton() {
        return this.loginButton;
    }
}