/*
 * Esta Clase representa graficamente un chat abierto con cierto contacto 
 * en el lado izquierdo de la pantalla principal
 * 
 */
package gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.Image;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.LineBorder;

import controlador.ControladorAppChat;
import modelo.Contacto;
import modelo.ContactoIndividual;
import modelo.Grupo;
import modelo.Mensaje;
import modelo.Usuario;

@SuppressWarnings("serial")
public class OpenedChat extends JPanel {

	private SelectedChat chat;
	private ImageIcon icono;
	private String name;
	private LocalDate lstDate;
	private String ultMsg;
	private Contacto contacto;
	private JPanel lPanel;
	private JPanel rPanel;
	JLabel nameL;
	JLabel dateL;
	JLabel last;
	
	public OpenedChat(Contacto c ,String s, JPanel lPanel,final JPanel derPanel) {
		if(c instanceof ContactoIndividual) {
			ContactoIndividual ci = (ContactoIndividual) c;
			
			icono = new ImageIcon(ci.getUsuario().getImagen());
		}else {
			Grupo g = (Grupo)c;
			icono = new ImageIcon("pics/equipo.png");
		}
		this.contacto = c;
		this.lPanel = lPanel;
		this.rPanel = derPanel;
		if(c!=null) name = c.getNombre();
		
		//TODO getUltimoMensaje
		ultMsg = s;
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(new LineBorder(Color.GRAY, 1));
		this.setSize(300, 60);
		this.setMaximumSize(new Dimension(300, 60));
		this.setMinimumSize(new Dimension(300, 60));
		
		Image im = icono.getImage();
		Image scaled = im.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		icono = new ImageIcon(scaled);
		JLabel icon = new JLabel(icono);
		add(icon);
		JPanel pRight = new JPanel();
		pRight.setLayout(new BoxLayout(pRight, BoxLayout.Y_AXIS));

		add(pRight);

		JPanel pRightTop = new JPanel();
		pRightTop.setLayout(new BoxLayout(pRightTop, BoxLayout.X_AXIS));
		pRightTop.setMaximumSize(new Dimension(300, 40));

		pRight.add(pRightTop);
		JPanel pRightBot = new JPanel();
		pRight.add(pRightBot);

		nameL = new JLabel(name);
		dateL = new JLabel(LocalDate.now().toString());
		last = new JLabel(ultMsg);
		nameL.setMaximumSize(new Dimension(80, 20));

		pRightTop.add(nameL);
		pRightTop.add(Box.createRigidArea(new Dimension(40, 10)));
		pRightTop.add(dateL);
		last.setMaximumSize(new Dimension(200, 20));
		last.setPreferredSize(new Dimension(200, 20));
		pRightBot.add(last);
		//El chat desplegado que se abrirá en la derecha
		chat = new SelectedChat(contacto, this);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1 && !chat.isDisplayable()) {
					//Queremos eliminar lo que hay en ese panel con anterioridad
					//quizá lo tenga que hacer la clase que lo crea
					if(rPanel.getComponentCount() <= 1) {
					Component[] listaComp = rPanel.getComponents();
					
					for(Component c : listaComp) {
						if(c instanceof SelectedChat) {
							rPanel.remove(c);
						}	
					}
					
						
					}
					
					rPanel.add(chat);
					rPanel.validate();
					rPanel.repaint();
					//setBackground(Color.pink );	
				}
				chat.mostrarBubbleText();
				//actualizarOpenedChat();
				revalidate();
				repaint();
			}
		});

	

		// this.add(Box.createRigidArea(new Dimension(60, 20)));
		
		setBackground(Color.red);
	}
	
	
	
	
	public void actualizarOpenedChat(){
		List<Mensaje> mensajes = contacto.getListaMensajes();
		if(!mensajes.isEmpty() || contacto.getClass().equals(Grupo.class))
			for(int i=0;i<mensajes.size();i++){
				String m="";
				Mensaje ult = mensajes.get(mensajes.size()-1);
				
				if(ult.getEmoji()==-1){
					m=ult.getTexto();
				}else
					m="Emoji";
				
				this.setUltMsg(m);
				last.setText(ultMsg);
				this.revalidate();
				this.repaint();
				lPanel.revalidate();
				lPanel.repaint();	
			}
		else{
			this.setUltMsg("");
			last.setText(ultMsg);
			this.revalidate();
			this.repaint();
			lPanel.revalidate();
			lPanel.repaint();
		}
	}

	public void eliminarChat(){
		lPanel.remove(this);
		rPanel.remove(chat);
		lPanel.revalidate();
		lPanel.repaint();
		rPanel.revalidate();
		rPanel.repaint();
	}
	

	public ImageIcon getIcono() {
		return icono;
	}

	public void setIcono(ImageIcon icono) {
		this.icono = icono;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getLstDate() {
		//TODO fecha del ultiomo mensaje
		return lstDate;
	}

	public void setLstDate(LocalDate lstDate) {
		//TODO fecha ultimo msg
		this.lstDate = lstDate;
	}
	

	public Contacto getContacto() {
		return contacto;
	}



	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}



	public String getUltMsg() {
		return ultMsg;
	}

	public void setUltMsg(String ultMsg) {
		this.ultMsg = ultMsg;
	}


	public SelectedChat getChat() {
		return chat;
	}

	public void setChat(SelectedChat chat) {
		this.chat = chat;
	}

}
