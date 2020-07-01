package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controlador.ControladorAppChat;
import dao.AdaptadorContactoIndividualTDS;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Grupo;
import modelo.Mensaje;
import modelo.Usuario;
import tds.BubbleText;

/**
 * Este es el panel que aparece al clicar en una conversacion de la barra de la
 * izquierda
 * 
 * @author Jesus
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

	public SelectedChat(Contacto contacto) {

		setLayout(new BorderLayout());
		// setSize(750, 700);

		setMaximumSize(new Dimension(750, 750));
		setMinimumSize(new Dimension(750, 700));
		setBackground(Color.green);
		// topPanel
		c=contacto;
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(550, 70));
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.setBackground(Color.CYAN);

		// get imagen del usuario abierto y su nombre y su info
		final ImageIcon icUInf ;
		if(c instanceof ContactoIndividual) {
			ContactoIndividual ci = (ContactoIndividual) c;
			 ImageIcon icUInfCOP = new ImageIcon(ci.getImagen());
			 icUInf = icUInfCOP;
		}else {
			 ImageIcon icUInfCOP = new ImageIcon("pics/equipo.png");
			 icUInf = icUInfCOP;
		}
		
		contactInfo = new JButton(c.getNombre(), icUInf);
		contactInfo.setMaximumSize(new Dimension(200, 60));
		contactInfo.setSize(200, 60);
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
		//Menú para borra mensajes o al Contacto.
		JPopupMenu menuDots = new JPopupMenu();
		JMenuItem borrarMsgs = new JMenuItem("Vaciar Conversación");
		JMenuItem elimCtcto = new JMenuItem("Eliminar Contacto");
		menuDots.add(borrarMsgs);
		menuDots.add(elimCtcto);
		
		puntos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					menuDots.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		//Ventana para filtrar Mesajes
		lupaLb.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JDialog vBuscar = new JDialog();
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
		add(botPanel,BorderLayout.SOUTH);

		midPanel = new JPanel();
		

		midPanel.setBackground(Color.GRAY);
		//add(midPanel,BorderLayout.CENTER);

		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
		
		final JScrollPane jsCh = new JScrollPane(midPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsCh.setPreferredSize(new Dimension(600, 545));
		jsCh.setSize(new Dimension(600, 545));
		jsCh.setMaximumSize(new Dimension(600, 545));
		jsCh.setMinimumSize(new Dimension(600, 545));
		
		
		add(jsCh,BorderLayout.CENTER);

		/*
		List<Mensaje> msj = c.getListaMensajes();
		if(msj!=null){
			if(c instanceof ContactoIndividual){
				for(Mensaje m : msj){
					addMensajeCI(m.getTexto(), m.getEmoji(), m.getEmisor());
				}
			}else
				for(Mensaje m : msj){
					addMensajeG(m.getTexto(), m.getEmoji(), m.getEmisor());
				}
		}
		*/
		
		// Informacion del contacto

		contactInfo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFrame infoFrame = new JFrame();
				// Poner nombre del contacto real
				JDialog contactInfo = new JDialog(infoFrame, "Nombre Completo del Contacto", true);
				contactInfo.setBounds(300, 300, 300, 350);
				contactInfo.setResizable(false);

				JPanel conInfPanel = new JPanel();
				conInfPanel.setLayout(new BoxLayout(conInfPanel, BoxLayout.Y_AXIS));
				JLabel name = new JLabel(c.getNombre());
				JLabel tel;
				if(c instanceof ContactoIndividual) {
					tel = new JLabel("Telf: "+ ((ContactoIndividual) c).getMovil());
				}else {
					tel = new JLabel("");
				}
			
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

		// Son 25 emogis

		sendBt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (msgT.getText().length() > 0) {
					//enviarMensaje(msgT.getText());
					Usuario actual = ControladorAppChat.getUnicaInstancia().getUsuarioActual();
					ControladorAppChat.getUnicaInstancia().cMensajeTexto(msgT.getText(), c);
					BubbleText borboja = new BubbleText(midPanel, msgT.getText(), Color.cyan, actual.getNombre(),BubbleText.SENT);
					midPanel.add(borboja);
					// cambiar Last
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
				// hay 25 emogis
				JLabel emo[] = new JLabel[25];

				for (int i = 0; i < 25; i++) {
					// Copia del iterado como final para poder usarlo con el mouseListener
					final int i2 = i;
					emo[i] = new JLabel(BubbleText.getEmoji(i));
					emoPanel.add(emo[i]);

					emo[i].addMouseListener(new MouseAdapter() {

						public void mouseClicked(MouseEvent e) {
							// Introducir Nombre del Usuario que los envía
							BubbleText emoSent = new BubbleText(midPanel, i2, Color.CYAN, "JUANPABLO", BubbleText.SENT,
									15);
							midPanel.add(emoSent);
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
						// TODO Auto-generated method stub

					}
				});

			}

		});

	}
	
	public void mostrarBubbleText(){
		
		List<Mensaje> mensajes = c.getListaMensajes();
		Usuario u = ControladorAppChat.getUnicaInstancia().getUsuarioActual();
		for(Mensaje m : mensajes){
			
			if(m.getEmisor().equals(u)){
				BubbleText b;
				if(m.getEmoji()==-1){
					b = new BubbleText(midPanel, m.getTexto(), Color.CYAN, u.getNombre() + " ", BubbleText.SENT);
				}else{
					b = new BubbleText(midPanel, m.getEmoji(), Color.CYAN, u.getNombre() + " ", BubbleText.SENT, 15);
				}
				midPanel.add(b);
			}else{
				BubbleText b;
				if(m.getEmoji()==-1){
					b = new BubbleText(midPanel, m.getTexto(), Color.CYAN," " + m.getEmisor().getNombre(), BubbleText.RECEIVED);
				}else{
					b = new BubbleText(midPanel, m.getEmoji(), Color.CYAN, " " + m.getEmisor().getNombre(), BubbleText.RECEIVED, 15);
				}
				midPanel.add(b);
			}
		}
		
	}
	/*
	private void addMensajeCI(String m, int e, Usuario u){
		Usuario uc = ControladorAppChat.getUnicaInstancia().getUsuarioActual();
		BubbleText b = null;
		if(u.equals(uc)){
			if(e==-1){
				b = new BubbleText(midPanel, m, Color.CYAN, "", BubbleText.SENT, 15);
			}else
				b = new BubbleText(midPanel, e, Color.CYAN, "", BubbleText.RECEIVED, 15);
		}else{
			if(e==-1){
				b = new BubbleText(midPanel, m, Color.CYAN, "", BubbleText.SENT, 15);
			}else
				b = new BubbleText(midPanel, e, Color.CYAN, "", BubbleText.RECEIVED, 15);
		}
		midPanel.add(b);
	}
	
	private void addMensajeG(String m, int e, Usuario u){
		Usuario uc = ControladorAppChat.getUnicaInstancia().getUsuarioActual();
		BubbleText b = null;
		if(u.equals(uc)){
			if(e==-1){
				b = new BubbleText(midPanel, m, Color.CYAN, "", BubbleText.SENT, 15);
			}else
				b = new BubbleText(midPanel, e, Color.CYAN, "", BubbleText.RECEIVED, 15);
		}else{
			String nombre;
			//if(ControladorAppChat.getUnicaInstancia().)
		
		}
		midPanel.add(b);
	}
	
	public void enviarMensaje(String m){
		if (c instanceof ContactoIndividual)
			ControladorAppChat.getUnicaInstancia().cMensajeCI(m,-1,(ContactoIndividual)c);
		else
			ControladorAppChat.getUnicaInstancia().cMensajeGrupo(m, -1, (Grupo)c);
	}
	
	public void enviarMensaje(int e){
		if(c instanceof ContactoIndividual)
			ControladorAppChat.getUnicaInstancia().cMensajeCI("", e, (ContactoIndividual)c);
		else
			ControladorAppChat.getUnicaInstancia().cMensajeGrupo("", e, (Grupo)c);
	}
*/
}
