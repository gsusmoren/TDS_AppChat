/*TODO
 * 
 * Hay que "resizear" loz iconos, pues son pngs editados a 50px
 * Guardar variables necesarias como atributos
 * Darle acción a estos
 * Colores y Fuentes
 * Enter para enviar
 * Preguntar al profesor los iconos
 * 
 * 
 */

package gui;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindowView extends JFrame {

	private JPanel contentPane;
	private JButton userInf_btn; // Este se debe de mostrar luego

	public MainWindowView() {
		this.setTitle("ChatApp");
		this.setBounds(Constantes.mainWindow_x, Constantes.mainWindow_y, Constantes.mainWx_size,
				Constantes.mainWy_size);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Panel Barra Superior
		final JPanel topBar = new JPanel();
		topBar.setBackground(Color.cyan);
		topBar.setSize(1000, 30);
		topBar.setMaximumSize(new Dimension(1000, 30));
		topBar.setMinimumSize(new Dimension(1000, 30));

		contentPane.add(topBar, BorderLayout.NORTH);
		topBar.setLayout(new FlowLayout());

		final ImageIcon icUser = new ImageIcon("pics/icon_profile.png");
		ImageIcon icStatus = new ImageIcon("pics/rec.png");
		ImageIcon icOpt = new ImageIcon("pics/menu.png");
		final ImageIcon icUInf = new ImageIcon("pics/inhigo.jpg");
		userInf_btn = new JButton("Iñigo Errejón", icUInf);
		ImageIcon icSea = new ImageIcon("pics/magnifying-glass.png");

		JLabel us = new JLabel(icUser);
		JLabel st = new JLabel(icStatus);
		JLabel op = new JLabel(icOpt);
		JLabel mg = new JLabel(icSea);
		JLabel chOp = new JLabel(icOpt);
		us.setMaximumSize(new Dimension(30, 30));
		st.setMaximumSize(new Dimension(30, 30));
		op.setMaximumSize(new Dimension(30, 30));
		mg.setMaximumSize(new Dimension(30, 30));
		chOp.setMaximumSize(new Dimension(30, 30));

		topBar.add(us);
		final JFrame copiaFrame = this;
		us.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// pasar saludo del usuario
				EditProfileWindow eProf = new EditProfileWindow(copiaFrame, icUser, "+\"k pasa poyeta\"+");

			}
		});

		topBar.add(Box.createRigidArea(new Dimension(120, 30)));
		topBar.add(st);
		topBar.add(op);
		topBar.add(userInf_btn);

		// userInf_btn.setVisible(false);
		topBar.add(Box.createRigidArea(new Dimension(400, 30)));
		topBar.add(mg);
		topBar.add(chOp);
		final JPanel center = new JPanel();
		center.setBackground(Color.white);

		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
		final JPanel cleft = new JPanel();
		cleft.setSize(new Dimension(300, 500));
		cleft.setMinimumSize(new Dimension(300, 500));
		cleft.setMaximumSize(new Dimension(300, 500));
		cleft.setLayout(new BoxLayout(cleft, BoxLayout.Y_AXIS));
		center.add(cleft);

		JScrollPane js = new JScrollPane(cleft, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		js.setMaximumSize(new Dimension(300, 700));
		js.setMinimumSize(new Dimension(300, 700));
		center.add(js);

		// Se le da acción al boton del usuario de la barra superior
		userInf_btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JDialog contactInfo = new JDialog(copiaFrame, "Nombre Completo del Contacto", true);

				contactInfo.setBounds(cleft.getLocationOnScreen().x + 300, cleft.getLocationOnScreen().y, 300, 500);

				JPanel conInfPanel = new JPanel();
				conInfPanel.setLayout(new BoxLayout(conInfPanel, BoxLayout.Y_AXIS));

				JLabel name = new JLabel("Nombre Contacto");
				JLabel tel = new JLabel("Telf :           ");
				JLabel pic = new JLabel(icUInf);
				conInfPanel.add(Box.createRigidArea(new Dimension(100, 50)));
				conInfPanel.add(pic);
				conInfPanel.add(name);
				conInfPanel.add(tel);
				contactInfo.add(conInfPanel);
				pic.setAlignmentX(CENTER_ALIGNMENT);
				name.setAlignmentX(CENTER_ALIGNMENT);
				tel.setAlignmentX(CENTER_ALIGNMENT);
				contactInfo.setVisible(true);
			}
		});

		final JPopupMenu m = new JPopupMenu("Crear Chat");
		JMenuItem m1 = new JMenuItem("Nuevo Chat");
		JMenuItem m2 = new JMenuItem("Nuevo Grupo");
		JMenuItem m3 = new JMenuItem("Crear Contacto");
		JMenuItem mPremium = new JMenuItem("Hazte Premium");
		JMenuItem m5 = new JMenuItem("Modificar Grupo");
		JMenuItem m6 = new JMenuItem("Mostrar Contactos");
		JMenuItem m8 = new JMenuItem("Mostrar Estadísticas");
		JMenuItem m7 = new JMenuItem("Cerrar Sesion");

		m.add(m1);
		m.add(m2);
		m.add(m3);
		m.add(mPremium);
		m.add(m5);
		m.add(m6);
		m.add(m7);
		m.add(m8);

		// final JPanel center = new JPanel();
		center.setBackground(Color.white);

		// m1.addActionListener(new A);
		// final JFrame frameP = this;

		op.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					m.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		m1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JDialog j = new JDialog(copiaFrame, "Elegir contacto", true);
				j.setBounds(cleft.getLocationOnScreen().x + 300, cleft.getLocationOnScreen().y, 300, 300);
				DefaultListModel<String> lista = new DefaultListModel<String>();
				lista.addElement("Pepe");
				lista.addElement("Pepe");
				lista.addElement("Pepe");
				lista.addElement("Pepe");
				lista.addElement("Pepe");

				final JList<String> l = new JList<String>(lista);
				j.add(l);
				l.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				JPanel bot = new JPanel();
				bot.setLayout(new FlowLayout());
				j.add(bot, BorderLayout.SOUTH);

				JButton bCrear = new JButton("Crear");
				JButton bCancelar = new JButton("Cancelar");
				bot.add(bCrear, BorderLayout.SOUTH);
				bot.add(bCancelar, BorderLayout.SOUTH);
				JScrollPane p = new JScrollPane(l, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				j.getContentPane().add(p);

				bCrear.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (l.getSelectedIndex() != -1) {
							OpenedChat o1 = new OpenedChat(icUser, (String) l.getSelectedValue(), "", center);

							cleft.add(o1);
							cleft.revalidate();
							cleft.repaint();
							j.dispose();
						}

					}
				});

				bCancelar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						j.dispose();

					}
				});
				j.setUndecorated(true);
				j.setVisible(true);

			}
		});

		mPremium.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JDialog prmDialog = new JDialog(copiaFrame, true);
				prmDialog.setResizable(false);
				prmDialog.setBounds(cleft.getLocationOnScreen().x + 300, cleft.getLocationOnScreen().y, 500, 500);
				 JPanel prmPanel = new JPanel();
				// Ver si el usuario puede aplicar algún descuento.
				prmDialog.add(prmPanel);
				prmPanel.setLayout(new BoxLayout(prmPanel, BoxLayout.Y_AXIS));
				prmPanel.setBackground(new Color(246, 219, 142));
				prmPanel.add(Box.createRigidArea(new Dimension(200, 20)));
				prmPanel.add(Box.createRigidArea(new Dimension(30,30)));
				JLabel titulo = new JLabel("ChatApp Premium");
				titulo.setFont(new Font("Monospaced", Font.BOLD, 35));
				prmPanel.add(titulo);
				titulo.setAlignmentX(CENTER_ALIGNMENT);
				
				JTextArea desc = new JTextArea(
						"Con ChatApp Premium podrás \nobtener tus estadísticas y \nuna imagen con todos tus \nContactos registrados desde\n9'99$/año* ");

				desc.setOpaque(false);
				desc.setEditable(false);
				desc.setFont(new Font("Monospaced", Font.PLAIN, 20));
				desc.setMaximumSize(new Dimension(350, 150));
				// desc.setMinimumSize(new Dimension(200,300));
				desc.setPreferredSize(new Dimension(350, 150));
				
				
				
				JTextArea desc1 = new JTextArea(
						"\n\n*Existe la posiblidad de aplicar ciertos\ndescuentos, compruebalo por ti mismo\ncontrantando el servicio.");
				
				

				desc1.setOpaque(false);
				desc1.setEditable(false);
				desc1.setFont(new Font("Monospaced", Font.PLAIN, 14));
				desc1.setMaximumSize(new Dimension(350, 100));
				// desc.setMinimumSize(new Dimension(200,300));
				desc1.setPreferredSize(new Dimension(350, 100));
				
	
				
				prmPanel.add(desc);
				desc.setAlignmentX(CENTER_ALIGNMENT);

				prmPanel.add(desc1);
				desc1.setAlignmentX(CENTER_ALIGNMENT);
				
				prmPanel.add(Box.createRigidArea(new Dimension(200, 50)));
				
				JPanel bot = new JPanel();
				bot.setOpaque(false);
				bot.setLayout(new FlowLayout());
				prmPanel.add(bot);
				JButton aceptar = new JButton("!Vamos!");
				aceptar.setAlignmentX(BOTTOM_ALIGNMENT);
				JButton cancelar = new JButton("Mejor en otro momento");
				cancelar.setAlignmentX(BOTTOM_ALIGNMENT);
				
				
				bot.add(aceptar);
				bot.add(cancelar);
				
				cancelar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						prmDialog.dispose();
						
					}
				});
				
				prmDialog.setUndecorated(true);
				prmDialog.setVisible(true);

			}
		});
	}

	public void mostrarVentana() {
		this.setVisible(true);

	}

//BORRAR MAIN
	public static void main(String[] args) {
		MainWindowView mWv = new MainWindowView();
		mWv.mostrarVentana();
	}
}
