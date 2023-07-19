package features.Classes.Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class LoginScreen extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginScreen() {
        setTitle("Login Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");

        panel.add(usernameLabel, gbc);
        gbc.gridy++;
        panel.add(usernameField, gbc);
        gbc.gridy++;
        panel.add(passwordLabel, gbc);
        gbc.gridy++;
        panel.add(passwordField, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        boolean success = false;
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        BufferedReader br = null;
        ArrayList<String> users = null;
        try {
            br = new BufferedReader(
                    new FileReader("C:\\Users\\terra\\features\\app\\src\\main\\java\\features\\info\\users.txt"));
            users = (ArrayList<String>) br.lines().collect(Collectors.toList());

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        if (users != null) {
            for (int i = 0; i < users.size(); i++) {
                String cuser = users.get(i).substring(users.get(i).indexOf(':') + 2, users.get(i).indexOf("|") - 1);
                String cpass = users.get(i).substring(users.get(i).indexOf('-') + 2);
                if (cuser.equals(username) && cpass.equals(cpass)) {
                    success = true;
                }
            }
            if (success) {
                JOptionPane.showMessageDialog(this, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
            }

        }

        // Clear the fields
        usernameField.setText("");
        passwordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen();
            }
        });
    }

}
