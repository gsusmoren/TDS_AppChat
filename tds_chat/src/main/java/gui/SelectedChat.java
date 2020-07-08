package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controlador.ControladorAppChat;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Grupo;
import modelo.Mensaje;
import modelo.Usuario;
import tds.BubbleText;

/**
 * Este es el panel que aparece al clicar en una conversacion de la barra de la
 * izquierda (Conversaciones abiertas). Muestra un panel con este chat.
 * 
 */
@SuppressWarnings("serial")
public class SelectedChat extends JPanel {
	private JPanel botPanel;
	private JPanel midPanel;
	private JPanel topPanel;
	private JTextField msgT;
	private JButton emoBt;
	private JButton sendBt;
	private JButton contactInfo;
	private Contacto c;
	private OpenedChat o;

	public SelectedChat(Contacto contacto, OpenedChat panel) {

		setLayout(new BorderLayout());

		setMaximumSize(new Dimension(750, Constantes.mainWy_size));
		setMinimumSize(new Dimension(750, Constantes.mainWy_size));

		// topPanel
		c = contacto;
		topPanel = new JPanel();
		o = panel;
		topPanel.setPreferredSize(new Dimension(550, 70));
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.setBackground(Color.CYAN);

		final ImageIcon icUInf;
		if (c instanceof ContactoIndividual) {
			ContactoIndividual ci = (ContactoIndividual) c;
			ImageIcon icUInfCOP = new ImageIcon(ci.getImagen());
			Image im = icUInfCOP.getImage();
			Image scaled = im.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);

			icUInfCOP = new ImageIcon(scaled);

			icUInf = icUInfCOP;
		} else {
			ImageIcon icUInfCOP = new ImageIcon("pics/equipo.png");
			icUInf = icUInfCOP;
		}

		contactInfo = new JButton(c.getNombre(), icUInf);
		contactInfo.setMaximumSize(new Dimension(250, 60));
		// contactInfo.setSize(200, 60);
		contactInfo.setPreferredSize(new Dimension(200, 60));
		ImageIcon icSearch = new ImageIcon("pics/magnifying-glass.png");
		ImageIcon icDots = new ImageIcon("pics/menu.png");
		JLabel lupaLb = new JLabel(icSearch);
		JLabel puntos = new JLabel(icDots);

		topPanel.add(Box.createRigidArea(new Dimension(20, 60)));
		contactInfo.setAlignmentX(LEFT_ALIGNMENT);
		topPanel.add(contactInfo);
		topPanel.add(Box.createRigidArea(new Dimension(410, 60)));
		lupaLb.setAlignmentX(RIGHT_ALIGNMENT);
		topPanel.add(lupaLb);
		puntos.setAlignmentX(RIGHT_ALIGNMENT);
		topPanel.add(puntos);

		add(topPanel, BorderLayout.NORTH);
		// Menú para borra mensajes o al Contacto.
		JPopupMenu menuDots = new JPopupMenu();
		JMenuItem borrarMsgs = new JMenuItem("Vaciar Conversación");
		JMenuItem elimCtcto = new JMenuItem("Eliminar Contacto");
		JMenuItem modCont = new JMenuItem("Modificar contacto");
		menuDots.add(borrarMsgs);
		menuDots.add(elimCtcto);
		if (c instanceof ContactoIndividual)
			menuDots.add(modCont);

		puntos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					menuDots.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		// Opción para el borrado de un contacto
		elimCtcto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				o.eliminarChat();
				boolean idDelt = ControladorAppChat.getUnicaInstancia().eliminarContacto(c);
				if (idDelt) {
					JOptionPane.showMessageDialog(null, "Se ha eliminado el contacto correctamente",
							"Contacto Eliminado", JOptionPane.INFORMATION_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(null, "No se ha podido borrar el contacto ",
							"Error Contacto Eliminado", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		// Opción para borrar todos los mensajes con el contacto
		// Si es un grupo, el ususario debe ser admin
		borrarMsgs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (c instanceof Grupo) {
					Grupo gp = (Grupo) c;
					if (!ControladorAppChat.getUnicaInstancia().isAdmin(gp)) {
						JOptionPane.showMessageDialog(null, "Debe ser Admin. para borrar los mensajes grupales",
								"Error Borrado Mensajes", JOptionPane.ERROR_MESSAGE);
						return;
					}

				}
				ControladorAppChat.getUnicaInstancia().eliminarMensajes(c);

				midPanel.removeAll();
				o.actualizarOpenedChat();

				midPanel.validate();
				midPanel.repaint();
				JOptionPane.showMessageDialog(null, "Mensajes eliminados con éxito", "Borrado de Mensajes",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});

		modCont.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame modificar = new JFrame();
				final JDialog ventReg = new JDialog(modificar, "Modificar contacto", true);
				ventReg.setBounds(Constantes.mainWindow_x + 300, Constantes.mainWindow_y + 150, 300, 150);

				final JPanel panelV = new JPanel();

				JButton aceptar = new JButton("Aceptar");
				JButton cancelar = new JButton("Cancelar");
				JLabel enunciado = new JLabel("Introduce el nuevo nombre de contacto:");
				panelV.add(enunciado, BorderLayout.NORTH);

				final JTextField nombreCont = new JTextField(15);

				JPanel panelCent = new JPanel();
				panelCent.setLayout(new BoxLayout(panelCent, BoxLayout.Y_AXIS));
				JPanel centralNom = new JPanel();

				panelCent.add(centralNom);

				panelV.add(panelCent, BorderLayout.CENTER);
				centralNom.add(new JLabel("Nombre :"));
				centralNom.add(nombreCont, BorderLayout.CENTER);

				panelV.add(aceptar, BorderLayout.SOUTH);
				panelV.add(cancelar, BorderLayout.SOUTH);

				aceptar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						// TODO usuario no repetido y que exista
						String nombre = nombreCont.getText().trim();

						if (nombre.length() > 10 || nombre.length() == 0) {
							JOptionPane.showMessageDialog(panelV, "Contacto No Modificado: Nombre muy largo o vacío",
									"Contacto Modificado", JOptionPane.ERROR_MESSAGE);
						} else {
							boolean isMod = ControladorAppChat.getUnicaInstancia().modificarContactoIndividual(nombre,
									((ContactoIndividual) c));
							if (isMod) {
								JOptionPane.showMessageDialog(panelV, "Contacto modificado correctamente",
										"Contacto Modificado", JOptionPane.INFORMATION_MESSAGE);
								o.actualizarOpenedChat();
								mostrarBubbleText();
								// validate();
								o.repaint();
								contactInfo.setText(nombre);
								ventReg.dispose();
							} else {
								JOptionPane.showMessageDialog(panelV, "Ya existe un contacto con este nombre",
										"Contacto No Modificado", JOptionPane.ERROR_MESSAGE);

							}
						}

					}
				});

				cancelar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						ventReg.dispose();

					}
				});

				ventReg.setUndecorated(true);
				ventReg.add(panelV);
				ventReg.setVisible(true);

			}
		});

		// Ventana para filtrar Mesajes

		lupaLb.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				final JDialog vBuscar;

				if (c instanceof ContactoIndividual) {
					vBuscar = new FiltroMensajesCI((ContactoIndividual) c);

				} else {
					vBuscar = new FiltroMensajesGP((Grupo) c);

				}
				vBuscar.setBounds(getLocationOnScreen().x, getLocationOnScreen().y, 400, 500);

				vBuscar.setVisible(true);

			}

		});

		// barra inferior
		botPanel = new JPanel();
		botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.X_AXIS));

		ImageIcon icEmo = new ImageIcon("pics/happy.png");
		emoBt = new JButton(icEmo);

		botPanel.add(emoBt);

		msgT = new JTextField();
		msgT.setMinimumSize(new Dimension(550, 50));
		msgT.setPreferredSize(new Dimension(550, 50));
		msgT.setMaximumSize(new Dimension(550, 50));
		msgT.setFont(new Font("Monospaced", Font.PLAIN, 25));
		botPanel.add(msgT);
		ImageIcon icSend = new ImageIcon("pics/right-arrow.png");
		sendBt = new JButton(icSend);
		botPanel.add(sendBt);
		add(botPanel, BorderLayout.SOUTH);

		midPanel = new JPanel();

		midPanel.setBackground(Color.GRAY);

		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));

		final JScrollPane jsCh = new JScrollPane(midPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsCh.setPreferredSize(new Dimension(600, 545));
		jsCh.setSize(new Dimension(600, 545));
		jsCh.setMaximumSize(new Dimension(600, 545));
		jsCh.setMinimumSize(new Dimension(600, 545));

		add(jsCh, BorderLayout.CENTER);

		// Informacion del contacto

		contactInfo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFrame infoFrame = new JFrame();
				JDialog contactInfo = new JDialog(infoFrame, "Nombre Completo del Contacto", true);
				contactInfo.setBounds(300, 300, 300, 350);
				contactInfo.setResizable(false);

				JPanel conInfPanel = new JPanel();
				conInfPanel.setLayout(new BoxLayout(conInfPanel, BoxLayout.Y_AXIS));
				JLabel name = new JLabel(c.getNombre());
				JLabel tel;

				if (c instanceof ContactoIndividual) {
					tel = new JLabel("Telf: " + ((ContactoIndividual) c).getMovil());
				} else {

					String s = "<html>Miembros del Grupo:<br/> ";
					if (((Grupo) c).getAdmin() != null)

						s += ((Grupo) c).getAdmin().getNombre() + "<br/>";
					for (ContactoIndividual ci : ((Grupo) c).getContactos())
						s += ci.getNombre() + "<br/>";

					s += "</html>";
					tel = new JLabel();
					tel.setText(s);

					tel.setFont(new Font("Monospaced", Font.PLAIN, 14));
					tel.setMaximumSize(new Dimension(200, 300));

				}
				tel.setOpaque(false);
				JLabel pic = new JLabel(icUInf);
				conInfPanel.add(Box.createRigidArea(new Dimension(100, 50)));
				conInfPanel.add(pic);
				conInfPanel.add(name);
				conInfPanel.add(tel);

				contactInfo.add(conInfPanel);
				pic.setAlignmentX(CENTER_ALIGNMENT);
				name.setFont(new Font("Monospaced", Font.BOLD, 22));
				name.setAlignmentX(CENTER_ALIGNMENT);
				tel.setAlignmentX(CENTER_ALIGNMENT);
				contactInfo.setVisible(true);
			}
		});

		// Son 25 emogis en una cuadrícula
		sendBt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (msgT.getText().length() > 0) {
					// enviarMensaje(msgT.getText());
					Usuario actual = ControladorAppChat.getUnicaInstancia().getUsuarioActual();
					ControladorAppChat.getUnicaInstancia().cMensajeTexto(msgT.getText(), c);
					BubbleText borboja = new BubbleText(midPanel, msgT.getText(), Color.cyan, actual.getNombre(),
							BubbleText.SENT);
					midPanel.add(borboja);
					// cambiar Last
					o.actualizarOpenedChat();

				}
				msgT.setText("");
				msgT.grabFocus();

				validate();
				repaint();
			}
		});

		emoBt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JFrame emos = new JFrame();
				emos.setBounds(emoBt.getLocationOnScreen().x, emoBt.getLocationOnScreen().y - 300, 600, 300);
				emos.setBackground(Color.black);

				emos.setUndecorated(true);
				emos.setVisible(true);

				JPanel emoPanel = new JPanel();
				emos.getContentPane().add(emoPanel);
				emoPanel.setLayout(new GridLayout(5, 5));
				JLabel emo[] = new JLabel[25];

				for (int i = 0; i < 25; i++) {
					// Copia del iterado como final para poder usarlo con el mouseListener
					final int i2 = i;
					emo[i] = new JLabel(BubbleText.getEmoji(i));
					emoPanel.add(emo[i]);

					emo[i].addMouseListener(new MouseAdapter() {

						public void mouseClicked(MouseEvent e) {
							// Introducir Nombre del Usuario que los envía
							Usuario actual = ControladorAppChat.getUnicaInstancia().getUsuarioActual();
							ControladorAppChat.getUnicaInstancia().cMensajeEmoji(i2, c);
							BubbleText borboja = new BubbleText(midPanel, i2, Color.cyan, actual.getNombre(),
									BubbleText.SENT, 15);
							midPanel.add(borboja);
							o.actualizarOpenedChat();
							midPanel.validate();
							midPanel.repaint();
							emos.dispose();
						}
					});
				}

				emos.addFocusListener(new FocusListener() {

					public void focusLost(FocusEvent e) {
						emos.dispose();

					}

					public void focusGained(FocusEvent e) {
						

					}
				});

			}

		});

	}

	public void mostrarBubbleText() {

		midPanel.removeAll();

		List<Mensaje> mensajes = c.getListaMensajes();
		Usuario u = ControladorAppChat.getUnicaInstancia().getUsuarioActual();

		boolean isGrupo = (c instanceof Grupo);
		Usuario userAct = ControladorAppChat.getUnicaInstancia().getUsuarioActual();

		for (Mensaje m : mensajes) {

			if (m.getEmisor().equals(u)) {
				BubbleText b;
				if (m.getEmoji() == -1) {
					b = new BubbleText(midPanel, m.getTexto(), Color.CYAN, u.getNombre() + " ", BubbleText.SENT);
				} else {
					b = new BubbleText(midPanel, m.getEmoji(), Color.CYAN, u.getNombre() + " ", BubbleText.SENT, 15);
				}
				midPanel.add(b);
			} else {
				String nombreAux = "";

				if (isGrupo) {
					if (userAct.comprobarContacto(m.getEmisor())) {
						nombreAux = userAct.getContactoIndividual(m.getEmisor()).getNombre();

					} else {
						nombreAux = "+34" + m.getEmisor().getMovil();

					}

				} else {
					nombreAux = c.getNombre();
				}

				BubbleText b;
				if (m.getEmoji() == -1) {
					b = new BubbleText(midPanel, m.getTexto(), Color.CYAN, " " + nombreAux, BubbleText.RECEIVED);
				} else {
					b = new BubbleText(midPanel, m.getEmoji(), Color.CYAN, " " + nombreAux, BubbleText.RECEIVED, 15);
				}
				midPanel.add(b);
			}
		}

	}

}
