package gui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView {

	private JFrame loginfrm;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textLogin;
	private JPasswordField textPassword;

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginfrm = new JFrame();
		loginfrm.setTitle("Grindr");
		// que aparezaca en el centro de la pantalla
		loginfrm.setBounds(100, 100, 458, 301);
		loginfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginfrm.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_north = new JPanel();
		loginfrm.getContentPane().add(panel_north, BorderLayout.NORTH);
		JLabel lblTitu = new JLabel("ChatApp");
		lblTitu.setFont(new Font("Monospaced", Font.PLAIN, 50));
		
		panel_north.add(lblTitu);

		JPanel panel_center = new JPanel();
		loginfrm.getContentPane().add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BoxLayout(panel_center, BoxLayout.Y_AXIS));
		textLogin = new JTextField("Usuario",5);
		textLogin.setFocusable(false);
		textLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textLogin.setText("");
				textLogin.setFocusable(true);

			}
		});

		textLogin.setMaximumSize(new Dimension(400, 40));
		panel_center.add(textLogin);
		
		textPassword = new JPasswordField("Contraseña");
		textPassword.setEchoChar((char)0);
		textPassword.setFocusable(false);
		textPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textPassword.setText("");
				textPassword.setEchoChar('♥');
				textPassword.setFocusable(true);
				
			}
		});
		
		JPanel panel_south = new JPanel();
		loginfrm.getContentPane().add(panel_south,BorderLayout.SOUTH);
		panel_south.setLayout(new BoxLayout(panel_south, BoxLayout.X_AXIS));
		
		JButton inic = new JButton("Iniciar Sesión");
		JButton regis = new JButton("Registrarse");
		
		panel_south.add(inic);
		panel_center.add(regis);
		
		textPassword.setMaximumSize(new Dimension(400,40));
		 panel_center.add(textPassword);
		 
		 
		 

		/*
		 * 
		 * 
		 * textLogin = new JTextField(); GridBagConstraints gbc_textLogin = new
		 * GridBagConstraints(); gbc_textLogin.gridwidth = 2; gbc_textLogin.insets = new
		 * Insets(0, 0, 5, 5); gbc_textLogin.fill = GridBagConstraints.HORIZONTAL;
		 * gbc_textLogin.gridx = 3; gbc_textLogin.gridy = 2;
		 */

		/*
		 * JPanel panel = new JPanel(); panel.setBorder(new
		 * EtchedBorder(EtchedBorder.LOWERED, null, null)); GridBagConstraints gbc_panel
		 * = new GridBagConstraints(); gbc_panel.fill = GridBagConstraints.BOTH;
		 * gbc_panel.gridheight = 2; gbc_panel.insets = new Insets(0, 0, 5, 0);
		 * gbc_panel.gridx = 6; gbc_panel.gridy = 2; panel_center.add(panel, gbc_panel);
		 * GridBagLayout gbl_panel = new GridBagLayout(); gbl_panel.columnWidths = new
		 * int[]{0, 0}; gbl_panel.rowHeights = new int[]{0, 0, 0};
		 * gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		 * gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		 * panel.setLayout(gbl_panel); /* JRadioButton rdbtnNewRadioButton = new
		 * JRadioButton("Asistente"); rdbtnNewRadioButton.setActionCommand("Asistente");
		 * rdbtnNewRadioButton.setSelected(true); GridBagConstraints
		 * gbc_rdbtnNewRadioButton = new GridBagConstraints();
		 * gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 0);
		 * gbc_rdbtnNewRadioButton.gridx = 0; gbc_rdbtnNewRadioButton.gridy = 0;
		 * panel.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);
		 * buttonGroup.add(rdbtnNewRadioButton);
		 * 
		 * JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Empresa");
		 * rdbtnNewRadioButton_1.setActionCommand("Empresa"); GridBagConstraints
		 * gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		 * gbc_rdbtnNewRadioButton_1.gridx = 0; gbc_rdbtnNewRadioButton_1.gridy = 1;
		 * panel.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);
		 * buttonGroup.add(rdbtnNewRadioButton_1);
		 * 
		 * JLabel lblCalve = new JLabel("Clave : "); GridBagConstraints gbc_lblCalve =
		 * new GridBagConstraints(); gbc_lblCalve.anchor = GridBagConstraints.EAST;
		 * gbc_lblCalve.insets = new Insets(0, 0, 5, 5); gbc_lblCalve.gridx = 2;
		 * gbc_lblCalve.gridy = 3; panel_center.add(lblCalve, gbc_lblCalve);
		 * 
		 * textPassword = new JPasswordField(); GridBagConstraints gbc_textPassword =
		 * new GridBagConstraints(); gbc_textPassword.gridwidth = 2;
		 * gbc_textPassword.insets = new Insets(0, 0, 5, 5); gbc_textPassword.fill =
		 * GridBagConstraints.HORIZONTAL; gbc_textPassword.gridx = 3;
		 * gbc_textPassword.gridy = 3; panel_center.add(textPassword, gbc_textPassword);
		 * /* JButton btnLogin = new JButton("Login"); btnLogin.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) { boolean
		 * login; if (buttonGroup.getSelection().getActionCommand() == "Empresa") login
		 * = ControladorEmpresas.getUnicaInstancia().loginEmpresa( textLogin.getText(),
		 * new String(textPassword.getPassword())); else login =
		 * ControladorAsistentes.getUnicaInstancia().loginAsistente(
		 * textLogin.getText(), new String(textPassword.getPassword())); if (login) {
		 * GestionEventosMainView window = new GestionEventosMainView();
		 * window.setVisible(true); frmLoginGestorEventos.dispose(); } else
		 * JOptionPane.showMessageDialog(frmLoginGestorEventos,
		 * "Nombre de usuario o contrase\u00F1a no valido", "Error",
		 * JOptionPane.ERROR_MESSAGE);
		 * 
		 * } }); GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		 * gbc_btnLogin.insets = new Insets(0, 0, 0, 5); gbc_btnLogin.gridx = 3;
		 * gbc_btnLogin.gridy = 5; panel_center.add(btnLogin, gbc_btnLogin);
		 * 
		 * JButton btnRegistro = new JButton("Registro");
		 * btnRegistro.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { if
		 * (buttonGroup.getSelection().getActionCommand() == "Empresa"){
		 * frmLoginGestorEventos.setTitle("Registro Empresa"); new
		 * RegistroEmpresaView(frmLoginGestorEventos); } else {
		 * frmLoginGestorEventos.setTitle("Registro Asistente"); new
		 * RegistroAsistenteView(frmLoginGestorEventos); } } });
		 * btnRegistro.setForeground(Color.BLACK); GridBagConstraints gbc_btnRegistro =
		 * new GridBagConstraints(); gbc_btnRegistro.insets = new Insets(0, 0, 0, 5);
		 * gbc_btnRegistro.gridx = 4; gbc_btnRegistro.gridy = 5;
		 * panel_center.add(btnRegistro, gbc_btnRegistro);
		 * 
		 * JButton btnSalir = new JButton("Salir"); btnSalir.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) {
		 * frmLoginGestorEventos.dispose(); System.exit(0); /*no seria necesario en este
		 * caso } }); /* GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		 * gbc_btnSalir.fill = GridBagConstraints.HORIZONTAL; gbc_btnSalir.gridx = 6;
		 * gbc_btnSalir.gridy = 5; panel_center.add(btnSalir, gbc_btnSalir);
		 * 
		 * JPanel panel_north = new JPanel(); FlowLayout flowLayout = (FlowLayout)
		 * panel_north.getLayout(); flowLayout.setVgap(15);
		 * frmLoginGestorEventos.getContentPane().add(panel_north, BorderLayout.NORTH);
		 * 
		 * JLabel lblGestorDeEventos = new JLabel("Gestor de Eventos");
		 * lblGestorDeEventos.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		 * lblGestorDeEventos.setForeground(Color.BLUE);
		 * panel_north.add(lblGestorDeEventos);
		 * 
		 */
	}

	public void mostrarVentana() {
		loginfrm.setVisible(true);

	}

	public static void main(String[] args) {
		LoginView lv = new LoginView();
		lv.mostrarVentana();
	}

}
