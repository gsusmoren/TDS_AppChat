package gui;

import java.awt.*;

import javax.swing.*;

public class LoginView {
	private JFrame loginFrame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textLogin;
	private JPasswordField textPassword;
	
	public LoginView() {
		initialize();
	}
	
	private void initialize() {
		//Panel Para el contenido 
		loginFrame = new JFrame();
		loginFrame.setTitle("Login");
		loginFrame.setBounds(100, 100, 458, 301);
		loginFrame.getContentPane().setBackground(Color.cyan);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(new BorderLayout(0, 0));

		
		
		JPanel p_north = new JPanel();
		p_north.setOpaque(false);
		loginFrame.getContentPane().add(p_north,BorderLayout.NORTH);
		
		JLabel appName = new JLabel("Chat-App Gay");
		p_north.add(appName);
		
		
		
		JPanel p_center = new JPanel();
		p_center.setOpaque(false);
		loginFrame.getContentPane().add(p_center, BorderLayout.CENTER);
		p_center.setLayout(new Border);
		
		
		
	}
	

	
	public static void main(String[] args) {
		LoginView ej= new LoginView(); 
		ej.loginFrame.setVisible(true);
	}
}


