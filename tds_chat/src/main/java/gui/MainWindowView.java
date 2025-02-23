package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.DocumentException;

import componente.cargador.modelo.Plataforma;
import componente.pulsador.IEncendidoListener;
import componente.pulsador.Luz;

import controlador.ControladorAppChat;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Grupo;
import modelo.Mensaje;
import modelo.Usuario;

/**
 * Clase principal que agrupa los distintos paneles y dialogos que componen la
 * interfaz de la aplicación.
 * 
 * @author Jesus
 *
 */
@SuppressWarnings("serial")
public class MainWindowView extends JFrame {

	private JPanel contentPane = new JPanel();
	final JPanel rPanel;
	final JPanel botLPanel;

	public MainWindowView() {
		this.setTitle("ChatApp");
		ImageIcon logo = new ImageIcon("pics/chat.png");
		this.setIconImage(logo.getImage());
		this.setBounds(Constantes.mainWindow_x, Constantes.mainWindow_y, Constantes.mainWx_size,
				Constantes.mainWy_size);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Panel de la derecha

		rPanel = new JPanel();
		rPanel.setBackground(Color.gray);
		rPanel.setMinimumSize(new Dimension(700, Constantes.mainWy_size));
		rPanel.setMaximumSize(new Dimension(700, Constantes.mainWy_size));

		// Panel de la izquierda de la vista principal, este contiene la barra superior
		// y los chats debajo
		final JPanel lPanel = new JPanel();
		lPanel.setSize(300, Constantes.mainWy_size);
		lPanel.setPreferredSize(new Dimension(300, Constantes.mainWy_size));
		lPanel.setMinimumSize(new Dimension(300, Constantes.mainWy_size));
		lPanel.setMaximumSize(new Dimension(300, Constantes.mainWy_size));
		lPanel.setLayout(new BoxLayout(lPanel, BoxLayout.Y_AXIS));
		contentPane.add(lPanel);
		contentPane.add(rPanel);

		// Barra superior del lado izquierdo
		final JPanel topLpanel = new JPanel();
		topLpanel.setLayout(new BoxLayout(topLpanel, BoxLayout.X_AXIS));
		topLpanel.setSize(300, 70);
		topLpanel.setMinimumSize(new Dimension(300, 70));
		topLpanel.setBackground(Color.gray);

		// iconos superiores
		// Reescalamos la imagen
		ImageIcon icUseraux = new ImageIcon(ControladorAppChat.getUnicaInstancia().getUsuarioActual().getImagen());
		Image im = icUseraux.getImage();
		Image scaled = im.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		icUseraux = new ImageIcon(scaled);
		final ImageIcon icUser = icUseraux;

		Luz luz = new Luz();
		luz.setMaximumSize(new Dimension(50, 50));
		luz.addEncendidoListener(new IEncendidoListener() {

			public void enteradoCambioEncendido(EventObject arg0) {
				if (luz.isEncendido()) {
					luz.repaint();

					JFileChooser fileC = new JFileChooser();
					fileC.setBounds(Constantes.mainWindow_x, Constantes.mainWindow_y, Constantes.mainWx_size,
							Constantes.mainWy_size);

					fileC.addChoosableFileFilter(new FileNameExtensionFilter("Txt files", "txt", "text"));
					fileC.setAcceptAllFileFilterUsed(false);
					int returnVal = fileC.showOpenDialog(null);

					@SuppressWarnings("unused")
					File f;
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						f = fileC.getSelectedFile();
						String pathString = fileC.getCurrentDirectory().toString() + "/";
						pathString = pathString.concat(fileC.getSelectedFile().getName());
						Vector<String> options = new Vector<String>();
						options.add("");
						options.add("IOS");
						options.add("Android 1");
						options.add("Android 2");

						@SuppressWarnings({ "unchecked", "rawtypes" })
						JComboBox b = new JComboBox(options);
						b.setEditable(false);

						int res = JOptionPane.showConfirmDialog(null, b, "Seleccionar formato de txt",
								JOptionPane.YES_NO_OPTION);
						if (res == JOptionPane.YES_OPTION && !b.getSelectedItem().equals("")) {
							String formato = (String) b.getSelectedItem();
							String formatDate = "";
							Plataforma p = null;
							if (formato.equals("IOS")) {
								formatDate = "d/M/yy H:mm:ss";
								p = Plataforma.IOS;
							} else if (formato.equals("Android 1")) {
								formatDate = "d/M/yy H:mm";
								p = Plataforma.ANDROID;
							} else if (formato.equals("Android 2")) {
								formatDate = "d/M/yyyy H:mm";
								p = Plataforma.ANDROID;
							}
							ControladorAppChat.getUnicaInstancia().ficheroImportado(pathString, formatDate, p);

						}
					} else if (returnVal == JFileChooser.CANCEL_OPTION || returnVal == JFileChooser.ERROR_OPTION) {
						luz.setEncendido(false);
						luz.repaint();
						return;
					}

					luz.setEncendido(false);
					luz.repaint();

				}

			}

		});

		ImageIcon icOpt = new ImageIcon("pics/menu.png");

		final JLabel userLb = new JLabel(icUser);

		JLabel opLb = new JLabel(icOpt);
		userLb.setMaximumSize(new Dimension(60, 60));

		opLb.setMaximumSize(new Dimension(60, 60));
		topLpanel.add(Box.createRigidArea(new Dimension(10, 60)));
		topLpanel.add(userLb);

		topLpanel.add(Box.createRigidArea(new Dimension(10, 60)));
		topLpanel.add(luz);
		topLpanel.add(Box.createRigidArea(new Dimension(115, 60)));

		topLpanel.add(opLb);
		lPanel.add(topLpanel);
		final JFrame copiaFrame = this;
		userLb.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				EditProfileWindow eProf = new EditProfileWindow(copiaFrame);

				eProf.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						ImageIcon i = new ImageIcon(
								ControladorAppChat.getUnicaInstancia().getUsuarioActual().getImagen());
						Image im = i.getImage();
						Image scaled = im.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
						i = new ImageIcon(scaled);
						userLb.setIcon(i);

					}
				});

			}
		});

		// Panel inferior izquierdo (para openedchats)
		botLPanel = new JPanel();
		botLPanel.setLayout(new BoxLayout(botLPanel, BoxLayout.Y_AXIS));
		botLPanel.setSize(new Dimension(300, 700));
		botLPanel.setMinimumSize(new Dimension(300, 700));
		botLPanel.setMaximumSize(new Dimension(300, 700));
		botLPanel.setBackground(Color.pink);

		JScrollPane js = new JScrollPane(botLPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		lPanel.add(js);

		// Boton de Opciones de 3 puntos
		final JPopupMenu menuDots = new JPopupMenu("Crear Chat");

		JMenuItem mNuevoChat = new JMenuItem("Nuevo Chat");
		JMenuItem mNuevoGrupo = new JMenuItem("Nuevo Grupo");
		JMenuItem mCrearContacto = new JMenuItem("Agregar Contacto");
		JMenuItem mPremium = new JMenuItem("Hazte Premium");
		JMenuItem mModGrupo = new JMenuItem("Modificar Grupo");
		JMenuItem mContactos = new JMenuItem("Mostrar Contactos");
		JMenuItem mEstadisticas = new JMenuItem("Mostrar Estadísticas");
		JMenuItem mExit = new JMenuItem("Cerrar Sesion");

		menuDots.add(mNuevoChat);
		menuDots.add(mNuevoGrupo);
		menuDots.add(mCrearContacto);
		menuDots.add(mPremium);
		menuDots.add(mModGrupo);
		menuDots.add(mContactos);
		menuDots.add(mEstadisticas);
		menuDots.add(mExit);

		opLb.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					menuDots.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		// Actualizamos el panel con los chats que teníamos abiertos
		chatsRecientes();
		// Ventana para Abrir un chat con un Contacto
		mNuevoChat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JDialog j = new JDialog(copiaFrame, "Elegir contacto", true);
				j.setBounds(lPanel.getLocationOnScreen().x + 300, lPanel.getLocationOnScreen().y, 300, 300);
				DefaultListModel<String> lista = new DefaultListModel<String>();

				List<ContactoIndividual> cont = ControladorAppChat.getUnicaInstancia().getUsuarioActual()
						.getContactosIndividuales();
				for (ContactoIndividual c : cont) {

					if (c.getListaMensajes().size() == 0)
						lista.addElement(c.getNombre());
				}

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
							ContactoIndividual cont = ControladorAppChat.getUnicaInstancia()
									.getContactoIndividual(l.getSelectedValue());
							OpenedChat o1 = new OpenedChat(cont, "", botLPanel, rPanel);
							botLPanel.add(o1);
							botLPanel.revalidate();
							botLPanel.repaint();
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

		// Ventana para crear un grupo nuevo
		mNuevoGrupo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JDialog grupoD = new JDialog();
				grupoD.setBounds(lPanel.getLocationOnScreen().x + 300, lPanel.getLocationOnScreen().y, 500, 500);
				final DefaultListModel<String> l1 = new DefaultListModel<String>();
				List<ContactoIndividual> cont = ControladorAppChat.getUnicaInstancia().getUsuarioActual()
						.getContactosIndividuales();
				for (ContactoIndividual c : cont) {

					l1.addElement(c.getNombre());
				}

				final JList l = new JList(l1);
				l.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				l.setMinimumSize(new Dimension(130, 480));
				l.setMaximumSize(new Dimension(130, 480));
				l.setPreferredSize(new Dimension(130, 480));
				l.setBorder(BorderFactory.createTitledBorder("Contactos"));
				grupoD.getContentPane().add(l, BorderLayout.WEST);

				final DefaultListModel<String> l2 = new DefaultListModel<String>();
				final JList ll = new JList(l2);
				ll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				ll.setMinimumSize(new Dimension(130, 480));
				ll.setMaximumSize(new Dimension(130, 480));
				ll.setPreferredSize(new Dimension(130, 480));
				ll.setBorder(BorderFactory.createTitledBorder("Contactos añadidos"));
				grupoD.getContentPane().add(ll, BorderLayout.EAST);

				JPanel p1 = new JPanel();
				p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
				final JTextField nombre = new JTextField("Nombre del grupo");
				nombre.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent e) {
						nombre.setText("");

					}
				});
				nombre.setMaximumSize(new Dimension(350, 20));
				ImageIcon icDer = new ImageIcon("pics/flecha-derecha.png");
				ImageIcon icIzqu = new ImageIcon("pics/flecha-hacia-la-izquierda.png");

				JButton quitar = new JButton(icIzqu);
				JButton añadir = new JButton(icDer);
				nombre.setColumns(15);
				nombre.setAlignmentX(CENTER_ALIGNMENT);
				p1.add(nombre);
				p1.add(Box.createRigidArea(new Dimension(50, 150)));
				p1.add(añadir);
				p1.add(quitar);
				grupoD.getContentPane().add(p1, BorderLayout.CENTER);

				JButton bAc = new JButton("Aceptar");
				JButton bCanc = new JButton("Cancelar");
				JPanel bot = new JPanel();
				bot.setLayout(new FlowLayout());
				bot.add(bAc);
				bot.add(bCanc);
				grupoD.add(bot, BorderLayout.SOUTH);
				grupoD.setVisible(true);

				añadir.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (l.getSelectedIndex() != -1) {
							l2.addElement((String) l.getSelectedValue());
							l1.removeElement(l.getSelectedValue());
						}

					}
				});

				quitar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						if (ll.getSelectedIndex() != 1) {
							l1.addElement((String) ll.getSelectedValue());
							l2.removeElement(ll.getSelectedValue());
						}

					}
				});

				bAc.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						if (nombre.getText().equals("")) {
							JOptionPane.showMessageDialog(grupoD, "No se ha introducido nombre de grupo",
									"Error nombre grupo", JOptionPane.ERROR_MESSAGE);
							return;
						}

						LinkedList<ContactoIndividual> contacs = new LinkedList<ContactoIndividual>();
						ContactoIndividual ci;
						for (int i = 0; i < l2.getSize(); i++) {

							ci = ControladorAppChat.getUnicaInstancia().getContactoIndividual(l2.get(i));
							if (ci != null)
								contacs.add(ci);
						}

						if (contacs.size() < 1) {
							JOptionPane.showMessageDialog(grupoD, "Se debe añadir al menos 1 persona al grupo",
									"Error numero de usuarios", JOptionPane.ERROR_MESSAGE);
							return;
						}

						Grupo grupoReg = ControladorAppChat.getUnicaInstancia().crearGrupo(nombre.getText(), contacs);

						if (grupoReg != null) {

							OpenedChat chatnew = new OpenedChat(grupoReg, "", botLPanel, rPanel);
							botLPanel.add(chatnew);
							botLPanel.revalidate();
							botLPanel.repaint();
							grupoD.dispose();
						}

					}
				});

				bCanc.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						grupoD.dispose();

					}
				});
			}
		});

		// Botón para crear un nuevo Contacto
		mCrearContacto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JDialog ventReg = new JDialog(copiaFrame, "Registrar contacto", true);
				ventReg.setBounds(lPanel.getLocationOnScreen().x + 300, lPanel.getLocationOnScreen().y, 300, 150);

				final JPanel panelV = new JPanel();

				JButton aceptar = new JButton("Aceptar");
				JButton cancelar = new JButton("Cancelar");
				JLabel enunciado = new JLabel("Introduce su nombre y número de telefono");
				panelV.add(enunciado, BorderLayout.NORTH);

				final JTextField nombreCont = new JTextField(15);
				final JTextField numero = new JTextField(15);

				JPanel panelCent = new JPanel();
				panelCent.setLayout(new BoxLayout(panelCent, BoxLayout.Y_AXIS));
				JPanel centralNom = new JPanel();
				JPanel centralNum = new JPanel();
				panelCent.add(centralNom);
				panelCent.add(centralNum);

				panelV.add(panelCent, BorderLayout.CENTER);
				centralNom.add(new JLabel("Nombre :"));
				centralNom.add(nombreCont, BorderLayout.CENTER);
				centralNum.add(new JLabel("Número :"));
				centralNum.add(numero, BorderLayout.CENTER);

				panelV.add(aceptar, BorderLayout.SOUTH);
				panelV.add(cancelar, BorderLayout.SOUTH);

				aceptar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						String nombre = nombreCont.getText().trim();

						if (nombre.isEmpty() || nombre.length() > 12) {
							JOptionPane.showMessageDialog(panelV, "Nombre incompatible", "Contacto No Añadido",
									JOptionPane.ERROR_MESSAGE);
						} else {

							String num = numero.getText().trim();
							boolean isReg = ControladorAppChat.getUnicaInstancia().addContactoIndividual(nombre, num);
							if (isReg && !num
									.equals(ControladorAppChat.getUnicaInstancia().getUsuarioActual().getMovil())) {
								JOptionPane.showMessageDialog(panelV, "Contacto registrado correctamente",
										"Contacto Añadido", JOptionPane.INFORMATION_MESSAGE);
								ventReg.dispose();
							} else {
								JOptionPane.showMessageDialog(panelV, "Contacto erroneo o repetido",
										"Contacto No Añadido", JOptionPane.ERROR_MESSAGE);

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

		mContactos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog cncDialog = new JDialog(copiaFrame, true);
				cncDialog.setResizable(false);
				cncDialog.setBounds(lPanel.getLocationOnScreen().x + 300, lPanel.getLocationOnScreen().y, 800, 600);
				cncDialog.setMinimumSize(new Dimension(700, 500));

				JPanel numerosP = new JPanel();
				numerosP.setLayout(new BoxLayout(numerosP, BoxLayout.Y_AXIS));
				JPanel nombresP = new JPanel();
				nombresP.setLayout(new BoxLayout(nombresP, BoxLayout.Y_AXIS));
				JPanel fotosP = new JPanel();
				fotosP.setLayout(new BoxLayout(fotosP, BoxLayout.Y_AXIS));
				JPanel gruposP = new JPanel();
				gruposP.setLayout(new BoxLayout(gruposP, BoxLayout.Y_AXIS));

				cncDialog.getContentPane().setLayout(new BoxLayout(cncDialog.getContentPane(), BoxLayout.X_AXIS));

				cncDialog.setTitle("Sus Contactos");
				Usuario act = ControladorAppChat.getUnicaInstancia().getUsuarioActual();
				List<ContactoIndividual> contactos = act.getContactosIndividuales();
				contactos.sort(Comparator.comparing(ContactoIndividual::getNombre));

				for (ContactoIndividual ci : contactos) {
					ImageIcon icCnt = new ImageIcon(ci.getImagen());
					Image im = icCnt.getImage();
					Image scaled = im.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
					icCnt = new ImageIcon(scaled);
					JLabel fotico = new JLabel(icCnt);
					fotosP.add(fotico);
					fotosP.add(Box.createRigidArea(new Dimension(60, 30)));

					nombresP.add(new JLabel("Nom: " + ci.getNombre()));
					nombresP.add(Box.createRigidArea(new Dimension(60, 60)));

					numerosP.add(new JLabel("Telf: " + ci.getMovil()));
					numerosP.add(Box.createRigidArea(new Dimension(60, 60)));

					gruposP.add(new JLabel(
							"Grupos Comunes: " + ControladorAppChat.getUnicaInstancia().getGruposComunes(ci)));
					gruposP.add(Box.createRigidArea(new Dimension(60, 60)));

				}
				JButton exportar = new JButton("Exportar PDF");

				cncDialog.getContentPane().add(fotosP);
				cncDialog.getContentPane().add(nombresP);
				cncDialog.getContentPane().add(numerosP);
				cncDialog.getContentPane().add(gruposP);

				cncDialog.add(exportar);

				exportar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (ControladorAppChat.getUnicaInstancia().getUsuarioActual().isPremium()) {

							try {
								ControladorAppChat.getUnicaInstancia().exportarContactosPDF();
							} catch (FileNotFoundException | DocumentException e1) {

								e1.printStackTrace();
							}

							JOptionPane.showMessageDialog(cncDialog,
									"Se ha creado un PDF con la información de sus contactos", "Exportación Exitosa",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(cncDialog,
									"Necesitas ser un Usuario Premium para exportar tus contactos", "Hazte Premium",
									JOptionPane.INFORMATION_MESSAGE);

						}
					}
				});

				cncDialog.setVisible(true);

			}
		});

		// Ventana de modificar grupo
		mModGrupo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Vector<String> options = new Vector<String>();
				options.add("");
				Usuario u = ControladorAppChat.getUnicaInstancia().getUsuarioActual();
				for (Grupo g : u.getGrupos()) {
					options.add(g.getNombre());
				}
				JComboBox b = new JComboBox(options);
				b.setEditable(false);

				int res = JOptionPane.showConfirmDialog(null, b, "Modificar grupo", JOptionPane.YES_NO_OPTION);

				if (res == JOptionPane.YES_OPTION) {
					if (!b.getSelectedItem().equals("")) {
						Grupo grupo = u.getGrupo((String) b.getSelectedItem());

						if (!ControladorAppChat.getUnicaInstancia().isAdmin(grupo)) {
							JOptionPane.showMessageDialog(copiaFrame, "Debes ser Administrador para editar un grupo",
									"Error Editar Grupo", JOptionPane.ERROR_MESSAGE);
							return;
						}

						final JDialog grupoD = new JDialog();
						grupoD.setBounds(lPanel.getLocationOnScreen().x + 300, lPanel.getLocationOnScreen().y, 500,
								500);
						final DefaultListModel<String> l1 = new DefaultListModel<String>();
						List<ContactoIndividual> cont = ControladorAppChat.getUnicaInstancia().getUsuarioActual()
								.getContactosIndividuales();
						for (ContactoIndividual c : cont) {

							if (!grupo.getContactos().contains(c))
								l1.addElement(c.getNombre());
						}

						final JList l = new JList(l1);
						l.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						l.setMinimumSize(new Dimension(130, 480));
						l.setMaximumSize(new Dimension(130, 480));
						l.setPreferredSize(new Dimension(130, 480));
						l.setBorder(BorderFactory.createTitledBorder("Contactos sin añadir"));
						grupoD.getContentPane().add(l, BorderLayout.WEST);

						final DefaultListModel<String> l2 = new DefaultListModel<String>();
						for (ContactoIndividual ci : grupo.getContactos()) {
							l2.addElement(ci.getNombre());
						}
						final JList ll = new JList(l2);
						ll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						ll.setMinimumSize(new Dimension(130, 480));
						ll.setMaximumSize(new Dimension(130, 480));
						ll.setPreferredSize(new Dimension(130, 480));
						ll.setBorder(BorderFactory.createTitledBorder("Contactos de grupo"));
						grupoD.getContentPane().add(ll, BorderLayout.EAST);

						JPanel p1 = new JPanel();
						p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
						final JTextField nombre = new JTextField(grupo.getNombre());

						nombre.setMaximumSize(new Dimension(350, 20));
						ImageIcon icDer = new ImageIcon("pics/flecha-derecha.png");
						ImageIcon icIzqu = new ImageIcon("pics/flecha-hacia-la-izquierda.png");

						JButton quitar = new JButton(icIzqu);
						JButton añadir = new JButton(icDer);
						nombre.setColumns(15);
						nombre.setAlignmentX(CENTER_ALIGNMENT);
						p1.add(nombre);
						p1.add(Box.createRigidArea(new Dimension(50, 150)));
						p1.add(añadir);
						p1.add(quitar);
						grupoD.getContentPane().add(p1, BorderLayout.CENTER);

						JButton bAc = new JButton("Aceptar");
						JButton bCanc = new JButton("Cancelar");
						JPanel bot = new JPanel();
						bot.setLayout(new FlowLayout());
						bot.add(bAc);
						bot.add(bCanc);
						grupoD.add(bot, BorderLayout.SOUTH);
						grupoD.setVisible(true);

						añadir.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								if (l.getSelectedIndex() != -1) {
									l2.addElement((String) l.getSelectedValue());
									l1.removeElement(l.getSelectedValue());
								}

							}
						});

						quitar.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								if (ll.getSelectedIndex() != 1) {
									l1.addElement((String) ll.getSelectedValue());
									l2.removeElement(ll.getSelectedValue());
								}

							}
						});

						bAc.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								if (nombre.getText().equals("")) {
									JOptionPane.showMessageDialog(grupoD, "No se ha introducido nombre de grupo",
											"Error nombre grupo", JOptionPane.ERROR_MESSAGE);
									return;
								}

								LinkedList<ContactoIndividual> contacs = new LinkedList<ContactoIndividual>();
								ContactoIndividual ci;
								for (int i = 0; i < l2.getSize(); i++) {

									ci = ControladorAppChat.getUnicaInstancia().getContactoIndividual(l2.get(i));
									if (ci != null)
										contacs.add(ci);
								}

								if (contacs.size() < 1) {
									JOptionPane.showMessageDialog(grupoD, "Se debe añadir al menos 1 persona al grupo",
											"Error numero de usuarios", JOptionPane.ERROR_MESSAGE);
									return;
								}

								Grupo g = ControladorAppChat.getUnicaInstancia().modificarGrupo(grupo.getNombre(),
										nombre.getText(), contacs);
								List<Mensaje> m = g.getListaMensajes();
								OpenedChat chatnew;
								if (!m.isEmpty())
									chatnew = new OpenedChat(g, m.get(m.size() - 1).getTexto(), botLPanel, rPanel);
								else
									chatnew = new OpenedChat(g, "", botLPanel, rPanel);
								botLPanel.removeAll();
								chatsRecientes();
								botLPanel.revalidate();
								botLPanel.repaint();
								grupoD.dispose();

							}

						});
					}
				}

			}
		});
		// Opción para exportar las estadísticas del usuario
		mEstadisticas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!ControladorAppChat.getUnicaInstancia().getUsuarioActual().isPremium()) {
					JOptionPane.showMessageDialog(copiaFrame,
							"Debe ser un Usuario Premium para exportar sus estadísticas", "Hágase Premium",
							JOptionPane.ERROR_MESSAGE);
					return;

				}

				Graficas graf = new Graficas();
				graf.exportarGraficas();
				JOptionPane.showMessageDialog(copiaFrame, "Se han generado sus estadísticas", "Exportación correcta",
						JOptionPane.INFORMATION_MESSAGE);
				return;

			}
		});

		// Opción para hacerse Usuario Premium
		mPremium.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (ControladorAppChat.getUnicaInstancia().getUsuarioActual().isPremium()) {
					JOptionPane.showMessageDialog(copiaFrame, "Usted ya es un Usaurio Premium :)", "Premium Activado",
							JOptionPane.INFORMATION_MESSAGE);
					return;

				}

				final JDialog prmDialog = new JDialog(copiaFrame, true);
				prmDialog.setResizable(false);
				prmDialog.setBounds(lPanel.getLocationOnScreen().x + 300, lPanel.getLocationOnScreen().y, 500, 500);
				final JPanel prmPanel = new JPanel();
				prmDialog.add(prmPanel);
				prmPanel.setLayout(new BoxLayout(prmPanel, BoxLayout.Y_AXIS));
				prmPanel.setBackground(new Color(246, 219, 142));
				prmPanel.add(Box.createRigidArea(new Dimension(200, 20)));
				prmPanel.add(Box.createRigidArea(new Dimension(30, 30)));
				JLabel titulo = new JLabel("ChatApp Premium");
				titulo.setFont(new Font("Monospaced", Font.BOLD, 35));
				prmPanel.add(titulo);
				titulo.setAlignmentX(CENTER_ALIGNMENT);

				JTextArea desc = new JTextArea(
						"Con ChatApp Premium podrás \nobtener tus estadísticas y \nuna imagen con todos tus \nContactos registrados desde\n6'00€/año* ");

				desc.setOpaque(false);
				desc.setEditable(false);
				desc.setFont(new Font("Monospaced", Font.PLAIN, 20));
				desc.setMaximumSize(new Dimension(350, 150));
				desc.setPreferredSize(new Dimension(350, 150));

				JTextArea desc1 = new JTextArea(
						"\n\n*Existe la posiblidad de aplicar ciertos\ndescuentos, compruebalo por ti mismo\ncontrantando el servicio.");

				desc1.setOpaque(false);
				desc1.setEditable(false);
				desc1.setFont(new Font("Monospaced", Font.PLAIN, 14));
				desc1.setMaximumSize(new Dimension(350, 100));
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

				aceptar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						final JDialog prmDialog2 = new JDialog(copiaFrame, true);
						prmDialog2.setResizable(false);
						prmDialog2.setBounds(lPanel.getLocationOnScreen().x + 300, lPanel.getLocationOnScreen().y, 500,
								500);
						JPanel prmPanel2 = new JPanel();
						prmDialog2.add(prmPanel2);

						prmPanel2.add(Box.createRigidArea(new Dimension(200, 40)));
						prmPanel2.setLayout(new BoxLayout(prmPanel2, BoxLayout.Y_AXIS));
						prmPanel2.setBackground(new Color(246, 219, 142));

						//mostramos precio final calculado.
						double precioFinal = ControladorAppChat.getUnicaInstancia().getUsuarioActual().getDescuento();

						JTextArea desc2 = new JTextArea("Su precio final es de " + precioFinal
								+ "€/año.\nIntroduzca su cuenta de PayPal®\npara finalizar la transacción.");

						prmPanel2.add(desc2);

						desc2.setEditable(false);
						desc2.setFont(new Font("Monospaced", Font.PLAIN, 20));
						desc2.setMaximumSize(new Dimension(400, 200));
						desc2.setPreferredSize(new Dimension(400, 200));
						prmPanel2.add(desc2);
						desc2.setAlignmentX(CENTER_ALIGNMENT);
						desc2.setOpaque(false);
						JLabel paypalLogo = new JLabel(new ImageIcon("pics/PP.png"));
						prmPanel2.add(paypalLogo);
						paypalLogo.setAlignmentX(CENTER_ALIGNMENT);

						final JTextField ppLogin = new JTextField("Correo Paypal");
						ppLogin.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {

								ppLogin.setText("");

							}
						});
						ppLogin.setMaximumSize(new Dimension(400, 40));
						ppLogin.setMinimumSize(new Dimension(400, 40));
						ppLogin.setPreferredSize(new Dimension(400, 40));
						prmPanel2.add(ppLogin);

						final JPasswordField ppCont = new JPasswordField("Contraseña");

						ppCont.setEchoChar((char) 0);
						ppCont.setFocusable(false);

						ppCont.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {

								ppCont.setText("");
								ppCont.setEchoChar('♥');
								ppCont.setFocusable(true);

							}
						});
						ppCont.setMaximumSize(new Dimension(400, 40));
						ppCont.setMinimumSize(new Dimension(400, 40));
						ppCont.setPreferredSize(new Dimension(400, 40));
						prmPanel2.add(ppCont);

						JButton accPP = new JButton("Aceptar");
						JButton salirPP = new JButton("Salir");

						JPanel botPP = new JPanel();
						botPP.setLayout(new FlowLayout());

						botPP.add(accPP);
						botPP.add(salirPP);

						prmPanel2.add(botPP);
						botPP.setAlignmentX(CENTER_ALIGNMENT);
						botPP.setOpaque(false);

						salirPP.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								prmDialog.dispose();
								prmDialog2.dispose();
							}
						});

						accPP.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								System.out.println(ppLogin.getText() + "  " + new String(ppCont.getPassword()));
								if (ControladorAppChat.getUnicaInstancia().loginPayPal(ppLogin.getText(),
										new String(ppCont.getPassword()))) {
									// Hacer premium al usuario y mensaje ok
									ControladorAppChat.getUnicaInstancia().setPremium();
									JOptionPane.showMessageDialog(prmDialog2, "Ya es usted Premium", "Exito Premium",
											JOptionPane.INFORMATION_MESSAGE);
									prmDialog2.dispose();

								} else {
									JOptionPane.showMessageDialog(prmDialog2,
											"Credenciales erroneas, revise los datos introducidos", "Error Premium",
											JOptionPane.ERROR_MESSAGE);

								}
							}
						});
						prmDialog2.setUndecorated(true);
						prmDialog2.setVisible(true);

					}
				});

				prmDialog.setUndecorated(true);
				prmDialog.setVisible(true);

			}
		});
		//Opción para cerrar la sesión actual
		mExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginView lv = new LoginView();
				lv.mostrarVentana();

			}
		});

	}
	//Método para actualizar los Opened Chat de la izquierda de la vista
	public void chatsRecientes() {
		Usuario u = ControladorAppChat.getUnicaInstancia().getUsuarioActual();

		List<Contacto> c = u.getContactos();
		List<OpenedChat> chats = new LinkedList<OpenedChat>();

		for (int i = 0; i < c.size(); i++) {
			String mensaje = "";
			List<Mensaje> mnjs = c.get(i).getListaMensajes();
			if (!mnjs.isEmpty() || c.get(i) instanceof Grupo) {
				if (!mnjs.isEmpty()) {
					Mensaje ult = mnjs.get(mnjs.size() - 1);

					if (ult.getEmoji() == -1) {
						mensaje = ult.getTexto();
					} else
						mensaje = "Emoji";
				}
				OpenedChat o = new OpenedChat(c.get(i), mensaje, botLPanel, rPanel);
				chats.add(o);
			}
		}
		for (OpenedChat o : chats) {
			botLPanel.add(o);
		}

	}

	public void mostrarVentana1() {
		this.setVisible(true);

	}

}