package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controlador.ControladorAppChat;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase para la representación del "Login". El usuario tiene un campo para su
 * nick y para su contraseña así como la opción de registrarse en la aplicación.
 * 
 * @author Jesus
 *
 */
public class LoginView {

	private JFrame loginfrm;
	private JTextField textLogin;
	private JPasswordField textPassword;
	private JButton inic;
	private JButton regis;

	public LoginView() {
		initialize();
	}

	private void initialize() {
		loginfrm = new JFrame();
		loginfrm.setResizable(false);
		loginfrm.setTitle("AppChat");
		ImageIcon logo = new ImageIcon("pics/chat.png");
		loginfrm.setIconImage(logo.getImage());
		loginfrm.setBounds(700, 300, 458, 301);
		loginfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginfrm.getContentPane().setLayout(new BorderLayout(0, 0));
		loginfrm.getContentPane().setBackground(new Color(50, 50, 50));

		final JPanel panel_north = new JPanel();
		panel_north.setOpaque(false);
		loginfrm.getContentPane().add(panel_north, BorderLayout.NORTH);

		JLabel lblTitu = new JLabel("ChatApp");
		lblTitu.setForeground(new Color(13, 115, 119));
		lblTitu.setFont(new Font("Monospaced", Font.PLAIN, 50));
		panel_north.add(lblTitu);

		final JPanel panel_center = new JPanel();
		panel_center.setOpaque(false);
		loginfrm.getContentPane().add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BoxLayout(panel_center, BoxLayout.Y_AXIS));

		Font f = new Font("Monospaced", Font.ITALIC, 20);

		final Font f_2 = new Font("Monospaced", Font.PLAIN, 20);

		textLogin = new JTextField("Usuario", 5);
		textLogin.setBorder(new LineBorder(Color.white, 1));
		textLogin.setFont(f);
		textLogin.setFocusable(false);
		textLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textLogin.setText("");
				textLogin.setFocusable(true);
				textLogin.setFont(f_2);

			}
		});

		textLogin.setMaximumSize(new Dimension(400, 40));
		panel_center.add(textLogin);

		panel_center.add(Box.createRigidArea(new Dimension(20, 15)));
		textPassword = new JPasswordField("Contraseña");
		textPassword.setBorder(new LineBorder(Color.white, 1));
		textPassword.setFont(f);
		textPassword.setEchoChar((char) 0);
		textPassword.setFocusable(false);

		textPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textPassword.setFocusable(true);
				textPassword.setFont(f_2);
				textPassword.setText("");
				textPassword.setEchoChar('♥');

			}
		});

		final JPanel panel_south = new JPanel();
		panel_south.setOpaque(false);
		loginfrm.getContentPane().add(panel_south, BorderLayout.SOUTH);

		inic = new JButton("Iniciar Sesión");
		regis = new JButton("Registrarse");

		panel_south.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		inic.setFont(new Font("Monospaced", Font.PLAIN, 15));
		regis.setFont(new Font("Monospaced", Font.PLAIN, 15));
		inic.setBackground(new Color(13, 115, 119));
		regis.setBackground(new Color(13, 115, 119));
		inic.setFocusPainted(false);
		inic.setBorderPainted(false);
		regis.setFocusPainted(false);
		regis.setBorderPainted(false);

		panel_south.add(inic);
		panel_south.add(regis);

		textPassword.setMaximumSize(new Dimension(400, 40));
		panel_center.add(textPassword);

		regis.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				@SuppressWarnings("unused")
				RegistroUsuario reg = new RegistroUsuario(loginfrm);

				loginfrm.getContentPane().remove(panel_north);
				loginfrm.getContentPane().remove(panel_center);
				loginfrm.getContentPane().remove(panel_south);

				loginfrm.revalidate();
				loginfrm.repaint();

			}
		});

		inic.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				boolean isLogged = ControladorAppChat.getUnicaInstancia().loginUsuario(textLogin.getText(),
						new String(textPassword.getPassword()));

				if (isLogged) {
					loginfrm.dispose();
					loginfrm = new MainWindowView();
					loginfrm.setVisible(true);
				} else {
					// credenciales malas
					JOptionPane.showMessageDialog(loginfrm, "Nombre o Contraseña erroneos", "Error Login",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
	}

	public void mostrarVentana() {
		loginfrm.setVisible(true);

	}

}
