package jmgror.tds_chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
//import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

//import umu.tds.controlador.ControladorAsistentes;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class RegistroUsuario extends JPanel {
	

		private JFrame ventana;
		private JButton btnRegistrar;
		private JButton btnCancelar;
		private JPanel jpanelAnterior;
		private JLabel lblNombre;
		private JLabel lblApellidos;
		private JLabel lblDni;
		private JLabel lblEdad;
		private JLabel lblEmail;
		private JLabel lblUsuario;
		private JLabel lblClave;
		private JTextField txtNombre;
		private JTextField txtApellidos;
		private JTextField txtDNI;
		private JTextField txtEdad;
		private JTextField txtEmail;
		private JTextField txtUsuario;
		private JLabel lblRepite;
		private JPasswordField txtClave;
		private JPasswordField txtClave2;
		private JLabel warningAll;
		private JLabel warningEdadNumerica;
		private JLabel warningEmailMovil;
		private JLabel warningClave2;
		private JLabel warningExiste;
		private JLabel warningNombre;
		private JLabel warningApellidos;
		private JLabel warningDNIEdad;
		private JLabel warningUsuario;
		private JLabel warningClave;
		private JLabel lblMovil;
		private JTextField txtMovil;
		
		
		public RegistroUsuario(JFrame frame){
			ventana=frame;
			jpanelAnterior = (JPanel) ventana.getContentPane();
			ventana.setTitle("Registro de usuario");
			this.setBackground(new Color(50,50,50));
			setLayout(new BorderLayout());
			
			this.add(Box.createRigidArea(new Dimension(20,20)),BorderLayout.NORTH);
			
			/*JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			add(tabbedPane, BorderLayout.CENTER);
			*/
			JPanel datosPersonales = new JPanel ();
			datosPersonales.setOpaque(false);
			//tabbedPane.addTab("Datos Asistente", null, datosPersonales, null);
			GridBagLayout gbl_datosPersonales = new GridBagLayout();
			gbl_datosPersonales.columnWidths = new int[]{70, 0, 0, 0, 0, 0, 0};
			gbl_datosPersonales.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0};
			gbl_datosPersonales.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_datosPersonales.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			datosPersonales.setLayout(gbl_datosPersonales);
			this.add(datosPersonales,BorderLayout.CENTER);
			
			JLabel introd=new JLabel("Introduzca sus datos: ");
			introd.setForeground(new Color(13,115,119));
			introd.setFont(new Font("Monospaced", Font.PLAIN,16));
			GridBagConstraints gbc_lblIntro = new GridBagConstraints();
			gbc_lblIntro.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblIntro.insets = new Insets(0, 0, 5, 5);
			gbc_lblIntro.gridx = 2;
			gbc_lblIntro.gridy = 0;
			datosPersonales.add(introd, gbc_lblIntro);
			
			Font f = new Font("Monospaced", Font.ITALIC, 13);
			final Font f_2 = new Font("Monospaced", Font.PLAIN, 20);

			txtNombre = new JTextField("Nombre");
			txtNombre.setBorder(new LineBorder(Color.white, 1));
			txtNombre.setFont(f);
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNombre.gridwidth = 3;
			gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
			gbc_txtNombre.gridx = 1;
			gbc_txtNombre.gridy = 1;
			datosPersonales.add(txtNombre, gbc_txtNombre);
			txtNombre.setColumns(20);
			
			txtNombre.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					txtNombre.setText("");
					txtNombre.setFocusable(true);
				}
			});
			
			warningNombre = new JLabel("*");
			warningNombre.setForeground(Color.RED);
			GridBagConstraints gbc_warningNombre = new GridBagConstraints();
			gbc_warningNombre.anchor = GridBagConstraints.WEST;
			gbc_warningNombre.insets = new Insets(0, 0, 5, 0);
			gbc_warningNombre.gridx = 5;
			gbc_warningNombre.gridy = 1;
			datosPersonales.add(warningNombre, gbc_warningNombre);
			
			/*lblApellidos = new JLabel("Apellidos : ");
			GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
			gbc_lblApellidos.anchor = GridBagConstraints.EAST;
			gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
			gbc_lblApellidos.gridx = 1;
			gbc_lblApellidos.gridy = 2;
			datosPersonales.add(lblApellidos, gbc_lblApellidos);
			*/
			txtApellidos = new JTextField("Apellidos");
			txtApellidos.setBorder(new LineBorder(Color.white, 1));
			txtApellidos.setFont(f);
			GridBagConstraints gbc_txtApellidos = new GridBagConstraints();
			gbc_txtApellidos.gridwidth = 3;
			gbc_txtApellidos.insets = new Insets(0, 0, 5, 5);
			gbc_txtApellidos.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtApellidos.gridx = 1;
			gbc_txtApellidos.gridy = 2;
			datosPersonales.add(txtApellidos, gbc_txtApellidos);
			txtApellidos.setColumns(20);
			
			txtApellidos.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					txtApellidos.setText("");
					txtApellidos.setFocusable(true);
				}
			});
			
			warningApellidos = new JLabel("*");
			warningApellidos.setForeground(Color.RED);
			GridBagConstraints gbc_warningApellidos = new GridBagConstraints();
			gbc_warningApellidos.anchor = GridBagConstraints.WEST;
			gbc_warningApellidos.insets = new Insets(0, 0, 5, 0);
			gbc_warningApellidos.gridx = 5;
			gbc_warningApellidos.gridy = 2;
			datosPersonales.add(warningApellidos, gbc_warningApellidos);
			/*
			lblDni = new JLabel("DNI : ");
			GridBagConstraints gbc_lblDni = new GridBagConstraints();
			gbc_lblDni.anchor = GridBagConstraints.EAST;
			gbc_lblDni.insets = new Insets(0, 0, 5, 5);
			gbc_lblDni.gridx = 1;
			gbc_lblDni.gridy = 3;
			datosPersonales.add(lblDni, gbc_lblDni);
			*/
			txtDNI = new JTextField("DNI");
			txtDNI.setBorder(new LineBorder(Color.white, 1));
			txtDNI.setFont(f);
			GridBagConstraints gbc_txtDNI = new GridBagConstraints();
			gbc_txtDNI.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDNI.gridwidth=2;
			gbc_txtDNI.insets = new Insets(0, 0, 5, 5);
			gbc_txtDNI.gridx = 1;
			gbc_txtDNI.gridy = 3;
			datosPersonales.add(txtDNI, gbc_txtDNI);
			txtDNI.setColumns(15);
			
			txtDNI.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					txtDNI.setText("");
					txtDNI.setFocusable(true);
				}
			});
			/*
			lblEdad = new JLabel("Edad :");
			GridBagConstraints gbc_lblEdad = new GridBagConstraints();
			gbc_lblEdad.anchor = GridBagConstraints.EAST;
			gbc_lblEdad.insets = new Insets(0, 0, 5, 5);
			gbc_lblEdad.gridx = 3;
			gbc_lblEdad.gridy = 3;
			datosPersonales.add(lblEdad, gbc_lblEdad);
			*/
			txtEdad = new JTextField("Edad");
			txtEdad.setBorder(new LineBorder(Color.white, 1));
			txtEdad.setFont(f);
			GridBagConstraints gbc_txtEdad = new GridBagConstraints();
			gbc_txtEdad.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEdad.insets = new Insets(0, 0, 5, 5);
			gbc_txtEdad.gridx = 3;
			gbc_txtEdad.gridy = 3;
			datosPersonales.add(txtEdad, gbc_txtEdad);
			txtEdad.setColumns(10);
			
			txtEdad.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					txtEdad.setText("");
					txtEdad.setFocusable(true);
				}
			});
			
			warningDNIEdad = new JLabel("*");
			warningDNIEdad.setForeground(Color.RED);
			GridBagConstraints gbc_warningDNIEmail = new GridBagConstraints();
			gbc_warningDNIEmail.anchor = GridBagConstraints.WEST;
			gbc_warningDNIEmail.insets = new Insets(0, 0, 5, 0);
			gbc_warningDNIEmail.gridx = 5;
			gbc_warningDNIEmail.gridy = 3;
			datosPersonales.add(warningDNIEdad, gbc_warningDNIEmail);
			/*
			lblEmail = new JLabel("Email :");
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.anchor = GridBagConstraints.EAST;
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.gridx = 1;
			gbc_lblEmail.gridy = 4;
			datosPersonales.add(lblEmail, gbc_lblEmail);
			*/
			txtEmail = new JTextField("Email");
			txtEmail.setBorder(new LineBorder(Color.white, 1));
			txtEmail.setFont(f);
			GridBagConstraints gbc_txtEmail = new GridBagConstraints();
			gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEmail.gridwidth=2;
			gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
			gbc_txtEmail.gridx = 1;
			gbc_txtEmail.gridy = 4;
			datosPersonales.add(txtEmail, gbc_txtEmail);
			txtEmail.setColumns(10);
			
			txtEmail.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					txtEmail.setText("");
					txtEmail.setFocusable(true);
				}
			});
			/*
			lblMovil = new JLabel("Movil : ");
			GridBagConstraints gbc_lblMovil = new GridBagConstraints();
			gbc_lblMovil.anchor = GridBagConstraints.EAST;
			gbc_lblMovil.insets = new Insets(0, 0, 5, 5);
			gbc_lblMovil.gridx = 3;
			gbc_lblMovil.gridy = 4;
			datosPersonales.add(lblMovil, gbc_lblMovil);
			*/
			txtMovil = new JTextField("Movil");
			txtMovil.setBorder(new LineBorder(Color.white, 1));
			txtMovil.setFont(f);
			GridBagConstraints gbc_txtMovil = new GridBagConstraints();
			gbc_txtMovil.insets = new Insets(0, 0, 5, 5);
			gbc_txtMovil.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtMovil.gridx = 3;
			gbc_txtMovil.gridy = 4;
			datosPersonales.add(txtMovil, gbc_txtMovil);
			txtMovil.setColumns(10);
			
			txtMovil.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					txtMovil.setText("");
					txtMovil.setFocusable(true);
				}
			});
			
			warningEmailMovil = new JLabel("*");
			warningEmailMovil.setForeground(Color.RED);
			GridBagConstraints gbc_warningMovil = new GridBagConstraints();
			gbc_warningMovil.anchor = GridBagConstraints.WEST;
			gbc_warningMovil.insets = new Insets(0, 0, 5, 0);
			gbc_warningMovil.gridx = 5;
			gbc_warningMovil.gridy = 4;
			datosPersonales.add(warningEmailMovil, gbc_warningMovil);
			/*
			lblUsuario = new JLabel("Usuario : ");
			GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
			gbc_lblUsuario.anchor = GridBagConstraints.EAST;
			gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsuario.gridx = 1;
			gbc_lblUsuario.gridy = 5;
			datosPersonales.add(lblUsuario, gbc_lblUsuario);
			*/
			txtUsuario = new JTextField("Usuario");
			txtUsuario.setBorder(new LineBorder(Color.white, 1));
			txtUsuario.setFont(f);
			GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
			gbc_txtUsuario.insets = new Insets(0, 0, 5, 5);
			gbc_txtUsuario.gridwidth=2;
			gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtUsuario.gridx = 1;
			gbc_txtUsuario.gridy = 5;
			datosPersonales.add(txtUsuario, gbc_txtUsuario);
			txtUsuario.setColumns(10);
			
			txtUsuario.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					txtUsuario.setText("");
					txtUsuario.setFocusable(true);
				}
			});
			
			warningUsuario = new JLabel("*");
			warningUsuario.setForeground(Color.RED);
			GridBagConstraints gbc_warningUsurio = new GridBagConstraints();
			gbc_warningUsurio.anchor = GridBagConstraints.WEST;
			gbc_warningUsurio.insets = new Insets(0, 0, 5, 0);
			gbc_warningUsurio.gridx = 5;
			gbc_warningUsurio.gridy = 5;
			datosPersonales.add(warningUsuario, gbc_warningUsurio);
			/*
			lblClave = new JLabel("Clave : ");
			GridBagConstraints gbc_lblClave = new GridBagConstraints();
			gbc_lblClave.anchor = GridBagConstraints.EAST;
			gbc_lblClave.insets = new Insets(0, 0, 5, 5);
			gbc_lblClave.gridx = 1;
			gbc_lblClave.gridy = 6;
			datosPersonales.add(lblClave, gbc_lblClave);
			*/
			txtClave = new JPasswordField("Clave");
			txtClave.setBorder(new LineBorder(Color.white, 1));
			txtClave.setFont(f);
			txtClave.setEchoChar((char)0);
			txtClave.setColumns(10);
			GridBagConstraints gbc_txtClave = new GridBagConstraints();
			gbc_txtClave.insets = new Insets(0, 0, 5, 5);
			gbc_txtClave.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtClave.gridx = 2;
			gbc_txtClave.gridy = 6;
			datosPersonales.add(txtClave, gbc_txtClave);
			
			txtClave.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					txtClave.setText("");
					txtClave.setFocusable(true);
					txtClave.setEchoChar('*');
				}
			});
			/*
			lblRepite = new JLabel("Repite : ");
			GridBagConstraints gbc_lblRepite = new GridBagConstraints();
			gbc_lblRepite.anchor = GridBagConstraints.EAST;
			gbc_lblRepite.insets = new Insets(0, 0, 5, 5);
			gbc_lblRepite.gridx = 3;
			gbc_lblRepite.gridy = 6;
			datosPersonales.add(lblRepite, gbc_lblRepite);
			*/
			txtClave2 = new JPasswordField("Repite clave");
			txtClave2.setBorder(new LineBorder(Color.white, 1));
			txtClave2.setFont(f);
			txtClave2.setEchoChar((char)0);
			GridBagConstraints gbc_txtClave2 = new GridBagConstraints();
			gbc_txtClave2.insets = new Insets(0, 0, 5, 5);
			gbc_txtClave2.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtClave2.gridx = 3;
			gbc_txtClave2.gridy = 6;
			datosPersonales.add(txtClave2, gbc_txtClave2);
			
			txtClave2.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					txtClave2.setText("");
					txtClave2.setFocusable(true);
					txtClave2.setEchoChar('*');
				}
			});
			
			warningClave = new JLabel("*");
			warningClave.setForeground(Color.RED);
			GridBagConstraints gbc_warningClave = new GridBagConstraints();
			gbc_warningClave.anchor = GridBagConstraints.WEST;
			gbc_warningClave.insets = new Insets(0, 0, 5, 0);
			gbc_warningClave.gridx = 5;
			gbc_warningClave.gridy = 6;
			datosPersonales.add(warningClave, gbc_warningClave);
			
			btnRegistrar= new JButton("Registrar");
			GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
			gbc_btnRegistrar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnRegistrar.insets = new Insets(10, 0, 5, 5);
			gbc_btnRegistrar.gridx = 2;
			gbc_btnRegistrar.gridy = 7;
			datosPersonales.add(btnRegistrar, gbc_btnRegistrar);
			/*btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkFields()) {
							boolean registrado=false;
							registrado = ControladorAsistentes.getUnicaInstancia().registrarAsistente(
											txtNombre.getText(),
											txtDNI.getText(),
											txtApellidos.getText(),
											Integer.parseInt(txtEdad.getText()),
											txtMovil.getText(),
											txtEmail.getText(),
											txtUsuario.getText(),
											new String(txtClave.getPassword()));
							if (registrado) {
								JOptionPane.showMessageDialog(
											ventana,
											"Asistente registrado correctamente.",
											"Registro",
											JOptionPane.INFORMATION_MESSAGE);
								ventana.setContentPane(jpanelAnterior);
								ventana.revalidate();
							} else JOptionPane.showMessageDialog(ventana,
									"No se ha podido llevar a cabo el registro.\n",
									"Registro",
									JOptionPane.ERROR_MESSAGE);
							ventana.setTitle("Login Gestor Eventos");	
					}
				} 
			}); */
			
			btnCancelar= new JButton("Cancelar");
			GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
			gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnCancelar.insets = new Insets(10, 0, 5, 5);
			gbc_btnCancelar.gridx = 3;
			gbc_btnCancelar.gridy = 7;
			datosPersonales.add(btnCancelar, gbc_btnCancelar);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ventana.setContentPane(jpanelAnterior);
					ventana.setTitle("Login Gestor Eventos");	
					ventana.revalidate();
				}
			});
			
			btnRegistrar.setFont(f_2);
			btnCancelar.setFont(f_2);
			btnRegistrar.setBackground(new Color(13,115,119));
			btnCancelar.setBackground(new Color(13,115,119));
			btnRegistrar.setFocusPainted(false);
			btnRegistrar.setBorderPainted(false);
			btnCancelar.setFocusPainted(false);
			btnCancelar.setBorderPainted(false);
			
			warningAll = new JLabel("* Las campos indicados son obligatorios");
			warningAll.setForeground(Color.RED);
			GridBagConstraints gbc_warningAll = new GridBagConstraints();
			gbc_warningAll.gridwidth = 3;
			gbc_warningAll.anchor = GridBagConstraints.WEST;
			gbc_warningAll.insets = new Insets(5, 0, 5, 5);
			gbc_warningAll.gridx = 2;
			gbc_warningAll.gridy = 8;
			datosPersonales.add(warningAll, gbc_warningAll);
			
			warningEdadNumerica = new JLabel("* La edad debe ser un número");
			warningEdadNumerica.setForeground(Color.RED);
			GridBagConstraints gbc_warningEdadNumerica = new GridBagConstraints();
			gbc_warningEdadNumerica.gridwidth = 3;
			gbc_warningEdadNumerica.anchor = GridBagConstraints.WEST;
			gbc_warningEdadNumerica.insets = new Insets(0, 0, 5, 5);
			gbc_warningEdadNumerica.gridx = 2;
			gbc_warningEdadNumerica.gridy = 9;
			datosPersonales.add(warningEdadNumerica, gbc_warningEdadNumerica);
			
			warningClave2 = new JLabel("* Las dos claves deben coincidir");
			warningClave2.setForeground(Color.RED);
			GridBagConstraints gbc_warningClave2 = new GridBagConstraints();
			gbc_warningClave2.gridwidth = 3;
			gbc_warningClave2.anchor = GridBagConstraints.WEST;
			gbc_warningClave2.insets = new Insets(0, 0, 5, 5);
			gbc_warningClave2.gridx = 2;
			gbc_warningClave2.gridy = 10;
			datosPersonales.add(warningClave2, gbc_warningClave2);
			
			warningExiste = new JLabel("* El usuario ya existe");
			warningExiste.setForeground(Color.RED);
			GridBagConstraints gbc_warningExiste = new GridBagConstraints();
			gbc_warningExiste.gridwidth = 3;
			gbc_warningExiste.anchor = GridBagConstraints.WEST;
			gbc_warningExiste.insets = new Insets(0, 0, 5, 5);
			gbc_warningExiste.gridx = 2;
			gbc_warningExiste.gridy = 11;
			datosPersonales.add(warningExiste, gbc_warningExiste);
			
			ocultarErrores();
			ventana.setContentPane(this);

			ventana.revalidate(); /*redibujar con el nuevo JPanel*/
			ventana.repaint();
			ventana.setVisible(true);
		} /*constructor*/
		
		/**
		 * Comprueba que los campos de registro estan bien
		 */
		/*private boolean checkFields() {
			boolean ok=true;
		//borrar todos los errores en pantalla
			ocultarErrores();

			if (txtNombre.getText().trim().isEmpty()) {
				warningNombre.setVisible(true); 
				ok=false;
			}
			if (txtApellidos.getText().trim().isEmpty()) {
				warningApellidos.setVisible(true); 
				ok=false;
			}

			if (txtDNI.getText().trim().isEmpty() || txtEdad.getText().trim().isEmpty()) {
				warningDNIEdad.setVisible(true); 
				ok=false;
			}
			if (txtEmail.getText().trim().isEmpty() || txtMovil.getText().trim().isEmpty()) {
				warningEmailMovil.setVisible(true); 
				ok=false;
			}
			if (txtUsuario.getText().trim().isEmpty()) {
				warningUsuario.setVisible(true); 
				ok=false;
			}
			
			String password = new String(txtClave.getPassword());
			String password2 = new String(txtClave2.getPassword());
			
			if (password.equals("")) {
				warningClave.setVisible(true); 
				ok=false;
			} 
			if (!ok) warningAll.setVisible(true);
			
			if (ok && (!(txtEdad.getText().matches("[0-9]+")))) {
				warningEdadNumerica.setVisible(true);
				ok=false;
			}
			
			if (ok && !password.equals(password2)) {
				warningClave.setVisible(true);
				warningClave2.setVisible(true);
				ok=false;
			}
			//Comprobar que no exista otro usuario con igual login
			if (ControladorAsistentes.getUnicaInstancia().esAsistenteRegistrado(txtUsuario.getText())) {
				warningExiste.setVisible(true); 
				ok=false;		
			}
			return ok;
		}*/
		
		/**
		 * Oculta todos los errores que pueda haber en la pantalla
		 */
		private void ocultarErrores() {
			warningAll.setVisible(false);
			warningApellidos.setVisible(false);
			warningClave.setVisible(false);
			warningClave2.setVisible(false);
			warningDNIEdad.setVisible(false);
			warningEdadNumerica.setVisible(false);
			warningExiste.setVisible(false);
			warningEmailMovil.setVisible(false);
			warningNombre.setVisible(false);
			warningUsuario.setVisible(false);
		}
	
	
	/*public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Login Gestor Eventos");
		frame.setBounds(100, 100, 458, 301);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton b1 = new JButton("Comeme los huevos");
		frame.getContentPane().add(b1,BorderLayout.CENTER);
		RegistroUsuario r = new RegistroUsuario(frame);
	}*/
}
