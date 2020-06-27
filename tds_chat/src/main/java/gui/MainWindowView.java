/*TODO
 * 
 * Hay que "resizear" loz iconos, pues son pngs editados a 50px
 * Guardar variables necesarias como atributos
 * Darle acción a estos
 * Colores y Fuentes
 * Enter para enviar
 * Preguntar al profesor los iconos
 * 
 * Arreglar barra superior hacer que aparezca cuando se habre un chat 
 * 
 * 
 */

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

import javax.swing.*;

import controlador.ControladorAppChat;

@SuppressWarnings("serial")
public class MainWindowView extends JFrame {

	private JPanel contentPane = new JPanel();

	public MainWindowView() {
		this.setTitle("ChatApp");
		this.setBounds(Constantes.mainWindow_x, Constantes.mainWindow_y, Constantes.mainWx_size,
				Constantes.mainWy_size);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Panel de la derecha
		
		final JPanel rPanel = new JPanel();
		rPanel.setBackground(Color.gray);
		
		
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
		topLpanel.setPreferredSize(new Dimension(300, 70));
		topLpanel.setBackground(Color.gray);

		// iconos superiores
		//Reescalamos la imagen
		ImageIcon icUseraux = new ImageIcon(ControladorAppChat.getUnicaInstancia().getUsuarioActual().getImagen());
		Image im = icUseraux.getImage();
		Image scaled = im.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		icUseraux = new ImageIcon(scaled);
		final ImageIcon icUser = icUseraux;
		
		ImageIcon icOpt = new ImageIcon("pics/menu.png");

		final JLabel userLb = new JLabel(icUser);

		JLabel opLb = new JLabel(icOpt);
		userLb.setMaximumSize(new Dimension(60, 60));

		opLb.setMaximumSize(new Dimension(60, 60));
		topLpanel.add(Box.createRigidArea(new Dimension(10, 60)));
		topLpanel.add(userLb);
		topLpanel.add(Box.createRigidArea(new Dimension(170, 60)));

		topLpanel.add(opLb);
		lPanel.add(topLpanel);
		final JFrame copiaFrame = this;
		userLb.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// pasar saludo del usuario
				EditProfileWindow eProf = new EditProfileWindow(copiaFrame);
				
				eProf.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosing(WindowEvent e){
						ImageIcon i = new ImageIcon(ControladorAppChat.getUnicaInstancia().getUsuarioActual().getImagen());
						Image im = i.getImage();
						Image scaled = im.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
						i = new ImageIcon(scaled);
						userLb.setIcon(i);
						topLpanel.revalidate();
						topLpanel.repaint();
						MainWindowView.this.validate();
						
					}
				});

			}
		});

		// Panel inferior izquierdo (para openedchats)
		final JPanel botLPanel = new JPanel();
		botLPanel.setLayout(new BoxLayout(botLPanel, BoxLayout.Y_AXIS));
		botLPanel.setSize(new Dimension(300, 700));
		botLPanel.setMinimumSize(new Dimension(300, 700));
		botLPanel.setMaximumSize(new Dimension(300, 700));
		botLPanel.setBackground(Color.pink);

		JScrollPane js = new JScrollPane(botLPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		lPanel.add(js);

		// Boton de Opciones
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

		mNuevoChat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JDialog j = new JDialog(copiaFrame, "Elegir contacto", true);
				j.setBounds(lPanel.getLocationOnScreen().x + 300, lPanel.getLocationOnScreen().y, 300, 300);
				DefaultListModel<String> lista = new DefaultListModel<String>();
				lista.addElement("Pepe");
				lista.addElement("Jose");
				lista.addElement("Amigo");
				lista.addElement("Pepe");
				lista.addElement("Contacto 1");

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
							OpenedChat o1 = new OpenedChat(icUser, (String) l.getSelectedValue(), "", rPanel);

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
		
		mNuevoGrupo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				final JDialog grupo = new JDialog();
				grupo.setBounds(lPanel.getLocationOnScreen().x + 300, lPanel.getLocationOnScreen().y,500, 500);
				final DefaultListModel<String> l1=new DefaultListModel<String>();
				l1.addElement("Pepe");
				l1.addElement("Juan");
				l1.addElement("Carlos");
				l1.addElement("Zerpas");
				
				final JList l=new JList(l1);
				l.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				l.setMinimumSize(new Dimension(130, 480));
				l.setMaximumSize(new Dimension(130, 480));
				l.setPreferredSize(new Dimension(130, 480));
				l.setBorder(BorderFactory.createTitledBorder("Contactos"));
				grupo.getContentPane().add(l,BorderLayout.WEST);
				final DefaultListModel<String> l2=new DefaultListModel<String>();
				final JList ll=new JList(l2);
				ll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				ll.setMinimumSize(new Dimension(130, 480));
				ll.setMaximumSize(new Dimension(130, 480));
				ll.setPreferredSize(new Dimension(130, 480));
				ll.setBorder(BorderFactory.createTitledBorder("Contactos añadidos"));
				grupo.getContentPane().add(ll,BorderLayout.EAST);
				
				JPanel p1=new JPanel();
				p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
				final JTextField nombre=new JTextField("Nombre del grupo");
				nombre.addMouseListener(new MouseAdapter() {
					
					public void mouseClicked(MouseEvent e) {
						nombre.setText("");
						
					}
				});
				nombre.setMaximumSize(new Dimension(350, 20));
				ImageIcon icDer = new ImageIcon("pics/flecha-derecha.png");
				ImageIcon icIzqu = new ImageIcon("pics/flecha-hacia-la-izquierda.png");

				JButton quitar=new JButton(icIzqu);
				JButton añadir=new JButton(icDer);
				nombre.setColumns(15);
				nombre.setAlignmentX(CENTER_ALIGNMENT);
				p1.add(nombre);
				p1.add(Box.createRigidArea(new Dimension(50, 150)));
				p1.add(añadir);
				p1.add(quitar);
				grupo.getContentPane().add(p1,BorderLayout.CENTER);
				
				JButton bAc=new JButton("Aceptar");
				JButton bCanc=new JButton("Cancelar");
				JPanel bot=new JPanel();
				bot.setLayout(new FlowLayout());
				bot.add(bAc);
				bot.add(bCanc);
				grupo.add(bot,BorderLayout.SOUTH);
				grupo.setVisible(true);
				
				añadir.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						if(l.getSelectedIndex()!=-1) {
							l2.addElement((String)l.getSelectedValue());
							l1.removeElement(l.getSelectedValue());
						}
						
					}
				});
				
				quitar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						if(ll.getSelectedIndex()!=1) {
							l1.addElement((String)ll.getSelectedValue());
							l2.removeElement(ll.getSelectedValue());
						}
						
					}
				});
				
				bAc.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						OpenedChat chatnew=new OpenedChat(icUser, nombre.getText(), "", rPanel);
						botLPanel.add(chatnew);
						botLPanel.revalidate();
						botLPanel.repaint();
						grupo.dispose();
					}
				});
				
				bCanc.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						grupo.dispose();
						
					}
				});
			}
		});

		mPremium.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JDialog prmDialog = new JDialog(copiaFrame, true);
				prmDialog.setResizable(false);
				prmDialog.setBounds(lPanel.getLocationOnScreen().x + 300, lPanel.getLocationOnScreen().y, 500, 500);
				final JPanel prmPanel = new JPanel();
				// Ver si el usuario puede aplicar algún descuento.
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

						// TODO
						// If tiene descuento -> mostrarlo
						// mostrar precio final.

						JTextArea desc2 = new JTextArea(
								"Su precio final es de 19'99$/año.\nIntroduzca su cuenta de PayPal®\npara finalizar la transacción.");

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

						prmDialog2.setUndecorated(true);
						prmDialog2.setVisible(true);

					}
				});

				prmDialog.setUndecorated(true);
				prmDialog.setVisible(true);

			}
		});

	}

	public void mostrarVentana1() {
		this.setVisible(true);

	}

//BORRAR MAIN
	/*
	public static void main(String[] args) {
		MainWindowView mWv1 = new MainWindowView();
		mWv1.mostrarVentana1();
	}
	*/
}
