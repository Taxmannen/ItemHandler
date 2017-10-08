package Model;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import View.GUI;

public class Start {
	private GUI g;
    private JLabel header1 = new JLabel("Username");
    private JLabel header2 = new JLabel("Password");
	private JTextField username = new JTextField("Admin", 7);
    private JTextField password = new JPasswordField("admin", 7);
    private JPanel panel = new JPanel();
    private Chain chainCalc1 = new Admin();
    private Chain chainCalc2 = new User();
    
    public Start(GUI g) {
    	this.g = g;
    	loginTab();
    	login();
    }
    
    public void login() {
    	int option = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    	if(option == JOptionPane.OK_OPTION) {
    		Login req = new Login (username.getText(), password.getText());
    		chainCalc1.setNextChain(chainCalc2);
    		chainCalc1.checkUser(req, g, this);
    	}
    	else if(option == JOptionPane.CANCEL_OPTION) {
    		login();
    	} else {
    		System.exit(0);
    	}
    }
  
    public void loginTab() {
    	panel.setLayout(new GridLayout(2, 2, 10, 2));
		panel.add(header1);
		panel.add(header2);
		panel.add(username);
		panel.add(password);
		header1.setHorizontalAlignment(SwingConstants.CENTER);
		header2.setHorizontalAlignment(SwingConstants.CENTER);
		username.setHorizontalAlignment(SwingConstants.CENTER);
		password.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public JPanel getPanel() { return panel; }
	public JTextField getUsername() { return username; }
	public JTextField getPassword() { return password; }
}
