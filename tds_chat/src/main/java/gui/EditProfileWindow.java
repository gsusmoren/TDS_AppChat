package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.ControladorAppChat;
import modelo.Usuario;


@SuppressWarnings("serial")
public class EditProfileWindow extends JFrame {

	private String imagenPerfil;
	private String saludo;
	private JButton editSaludo;

	public EditProfileWindow(JFrame frame) {
	
		setBounds(Constantes.mainWindow_x * 2, Constantes.mainWindow_y, Constantes.mainWx_size / 3,
				Constantes.mainWy_size / 2 + 60);
		setResizable(false);
		setTitle("Editar Perfil");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		getContentPane().setBackground(Color.CYAN);
		
		final Usuario uA = ControladorAppChat.getUnicaInstancia().getUsuarioActual();
		this.imagenPerfil = uA.getImagen();
		this.saludo = uA.getSaludo();

		ImageIcon icon = new ImageIcon(imagenPerfil);
		Image im=icon.getImage();
		Image scaled = im.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaled);

		final JLabel imagenPLabel = new JLabel(icon);
		final JLabel saludoLabel = new JLabel(saludo);
		imagenPLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		saludoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		saludoLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));
		this.getContentPane().add(Box.createRigidArea(new Dimension(50, 20)));
		this.getContentPane().add(imagenPLabel);
		this.getContentPane().add(Box.createRigidArea(new Dimension(50, 20)));
		this.getContentPane().add(saludoLabel);
		this.getContentPane().add(Box.createRigidArea(new Dimension(50, 20)));
		editSaludo = new JButton("Agregar o Modificar Saludo");
		this.getContentPane().add(editSaludo);
		editSaludo.setAlignmentX(Component.CENTER_ALIGNMENT);
		editSaludo.setBorder(Constantes.borderB);
		editSaludo.setBackground(Color.white);
		//final JDialog auxJD = this;
		editSaludo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JDialog diagIn = new JDialog(); 
				diagIn.setBounds(Constantes.mainWindow_x * 2 + 40, Constantes.mainWindow_y * 3, 300, 50);
				final JTextField textS = new JTextField(saludo);
				diagIn.getContentPane().add(textS);
				KeyboardFocusManager kb = KeyboardFocusManager.getCurrentKeyboardFocusManager();
				kb.addKeyEventPostProcessor(new KeyEventPostProcessor() {

					public boolean postProcessKeyEvent(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER && this != null) {

							saludo = textS.getText();
							saludoLabel.setText(saludo);
							Usuario uA = ControladorAppChat.getUnicaInstancia().getUsuarioActual();
							uA.setSaludo(saludo);
							diagIn.dispose();
							revalidate();
							repaint();
							ControladorAppChat.getUnicaInstancia().actualizarUsuario(uA);


							return false;
						}
						return false;
					}

				});

				diagIn.setUndecorated(true);
				diagIn.setVisible(true);

			}
		});
		
		imagenPLabel.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
			
				JFileChooser fileC = new JFileChooser();
				fileC.setBounds(Constantes.mainWindow_x,Constantes.mainWindow_y,Constantes.mainWx_size,Constantes.mainWy_size);
		
				fileC.addChoosableFileFilter(new FileNameExtensionFilter("Image Files",ImageIO.getReaderFileSuffixes()));
				fileC.setAcceptAllFileFilterUsed(false);
				int returnVal = fileC.showOpenDialog(imagenPLabel);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					String pathString =fileC.getCurrentDirectory().toString()+"/";
					pathString = pathString.concat(fileC.getSelectedFile().getName());
				
					//cambiar en la clase padre
				
					ImageIcon nImageIcon = new ImageIcon(pathString);
					
					Usuario actual = ControladorAppChat.getUnicaInstancia().getUsuarioActual();
					
					Image im = nImageIcon.getImage();
					Image scaled = im.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
					nImageIcon = new ImageIcon(scaled);
					imagenPerfil = pathString;
					imagenPLabel.setIcon(nImageIcon);
					actual.setImagen(pathString);
					
					ControladorAppChat.getUnicaInstancia().actualizarUsuario(actual);
					

					revalidate();
					repaint();
			
				}
			
			}
			
		});
		
		
		

		setVisible(true);

	}

}
