package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

public class RegistroUsuario2 extends JPanel {
	
	private JFrame ventana;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JPanel jpanelAnterior;
	private JTextField txtNombre;
	private JTextField txtMovil;
	private JDateChooser txtFecha;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JPasswordField txtClave2;
	private JTextField txtEmail;

	
	public RegistroUsuario2(JFrame frame) {
		ventana=frame;
		ventana.setResizable(false);
		jpanelAnterior = (JPanel) ventana.getContentPane();
		ventana.setTitle("Registro de usuario");
		this.setBackground(new Color(50,50,50));
		setLayout(new BorderLayout());
		ventana.setResizable(false);
		this.add(Box.createRigidArea(new Dimension(20,20)),BorderLayout.NORTH);
		
		JPanel datos=new JPanel();
		datos.setOpaque(false);
		this.add(datos,BorderLayout.CENTER);
		JLabel l=new JLabel("Introduzca sus datos: ");
		l.setForeground(Color.WHITE);
		datos.add(l);

		this.txtNombre = new JTextField("Nombre");
		txtNombre.setBorder(new LineBorder(Color.white, 1));
		txtNombre.setFocusable(false);
		txtNombre.setMaximumSize(new Dimension(350, 20));
		txtNombre.setMaximumSize(new Dimension(350, 20));
		txtNombre.setPreferredSize(new Dimension(350, 20));
		datos.add(txtNombre);
		
		txtNombre.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				txtNombre.setFocusable(true);
				txtNombre.setText("");				
			}
		});
		
		datos.add(Box.createRigidArea(new Dimension(10, 20)));

		this.txtUsuario = new JTextField("Usuario");
		txtUsuario.setBorder(new LineBorder(Color.white, 1));
		txtUsuario.setFocusable(false);
		txtUsuario.setMaximumSize(new Dimension(350, 20));
		txtUsuario.setMaximumSize(new Dimension(350, 20));
		txtUsuario.setPreferredSize(new Dimension(350, 20));
		datos.add(txtUsuario);
		
		txtUsuario.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				txtUsuario.setFocusable(true);
				txtUsuario.setText("");
				
			}
		});
		
		datos.add(Box.createRigidArea(new Dimension(10, 20)));

		
		
		this.txtMovil = new JTextField("Movil");
		txtMovil.setBorder(new LineBorder(Color.white, 1));
		txtMovil.setFocusable(false);
		txtMovil.setMaximumSize(new Dimension(450, 20));
		txtMovil.setMaximumSize(new Dimension(450, 20));
		txtMovil.setPreferredSize(new Dimension(242, 20));
		datos.add(txtMovil);
		
		txtMovil.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				txtMovil.setFocusable(true);
				txtMovil.setText("");
			}
		});
		
		datos.add(Box.createRigidArea(new Dimension(117, 20)));

		this.txtEmail = new JTextField("Email");
		txtEmail.setBorder(new LineBorder(Color.white, 1));
		txtEmail.setFocusable(false);
		txtEmail.setMaximumSize(new Dimension(450, 20));
		txtEmail.setMaximumSize(new Dimension(450, 20));
		txtEmail.setPreferredSize(new Dimension(242, 20));
		datos.add(txtEmail);
		
		txtEmail.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				txtEmail.setFocusable(true);
				txtEmail.setText("");
			}
		});
		
		datos.add(Box.createRigidArea(new Dimension(117
				, 20)));

		
		this.txtFecha = new JDateChooser();
		txtFecha.setBorder(new LineBorder(Color.white, 1));
		txtFecha.setFocusable(false);
		txtFecha.setMaximumSize(new Dimension(450, 20));
		txtFecha.setMaximumSize(new Dimension(450, 20));
		txtFecha.setPreferredSize(new Dimension(200, 20));
		datos.add(txtFecha);
		datos.add(Box.createRigidArea(new Dimension(158, 20)));

		
		this.txtClave = new JPasswordField("Clave");
		txtClave.setBorder(new LineBorder(Color.white, 1));
		txtClave.setEchoChar((char)0);
		txtClave.setFocusable(false);
		txtClave.setMaximumSize(new Dimension(350, 20));
		txtClave.setMaximumSize(new Dimension(350, 20));
		txtClave.setPreferredSize(new Dimension(350, 20));
		datos.add(txtClave);
		datos.add(Box.createRigidArea(new Dimension(8, 20)));
		
		txtClave.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				txtClave.setText("");
				txtClave.setFocusable(true);
				//txtClave.setFont(f_2);
				txtClave.setEchoChar('*');
			}
		});

		this.txtClave2 = new JPasswordField("Repetir clave");
		txtClave2.setEchoChar((char)0);
		txtClave2.setBorder(new LineBorder(Color.white, 1));
		txtClave2.setFocusable(false);
		txtClave2.setMaximumSize(new Dimension(350, 20));
		txtClave2.setMaximumSize(new Dimension(350, 20));
		txtClave2.setPreferredSize(new Dimension(350, 20));
		datos.add(txtClave2);
		datos.add(Box.createRigidArea(new Dimension(8, 20)));
		
		txtClave2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				txtClave2.setText("");
				txtClave2.setFocusable(true);
				//txtClave.setFont(f_2);
				txtClave2.setEchoChar('*');
			}
		});
		
		btnRegistrar= new JButton("Registrar");
		btnCancelar= new JButton("Cancelar");

		btnRegistrar.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnCancelar.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnRegistrar.setBackground(new Color(13,115,119));
		btnCancelar.setBackground(new Color(13,115,119));
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setBorderPainted(false);
		btnCancelar.setFocusPainted(false);
		btnCancelar.setBorderPainted(false);
		
		datos.add(btnRegistrar, BorderLayout.SOUTH);
		datos.add(btnCancelar, BorderLayout.SOUTH);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setContentPane(jpanelAnterior);
				ventana.revalidate();
				ventana.repaint();
			}
		});
		
		ventana.setContentPane(this);
		ventana.revalidate();
		ventana.repaint();
		this.setVisible(true);
		
	}
	
	/*public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(200, 200, 458, 301);
		RegistroUsuario2 r = new RegistroUsuario2(frame);
		frame.setVisible(true);
	}*/
}
