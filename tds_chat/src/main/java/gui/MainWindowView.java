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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import tds.BubbleText;

import javax.swing.Icon;

public class MainWindowView extends JFrame {

	// private JFrame mainfrm;
	private JPanel contentPane;
	private JButton userInf_btn, msgSea_btn, chatOpt_btn;
	 

	public MainWindowView() {
		this.setTitle("ChatApp");
		this.setBounds(300, 200, 1000, 700);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// mainfrm.getContentPane().setBackground(new Color(13,115,119));

		// Panel Barra Superior
		final JPanel topBar = new JPanel();
		topBar.setBackground(Color.cyan);
		topBar.setSize(1000, 30);
		topBar.setMaximumSize(new Dimension(1000,30));
		topBar.setMinimumSize(new Dimension(1000,30));

		contentPane.add(topBar, BorderLayout.NORTH);
		topBar.setLayout(new FlowLayout());

		ImageIcon icUser = new ImageIcon("pics/icon_profile.png");
		ImageIcon icStatus = new ImageIcon("pics/rec.png");
		ImageIcon icOpt = new ImageIcon("pics/menu.png");
		ImageIcon icUInf = new ImageIcon("pics/inhigo.jpg");
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
		topBar.add(Box.createRigidArea(new Dimension(120, 30)));
		topBar.add(st);
		topBar.add(op);
		topBar.add(userInf_btn);
		topBar.add(Box.createRigidArea(new Dimension(400, 30)));
		topBar.add(mg);
		topBar.add(chOp);
		System.out.println(topBar.getHeight());
		 final JPanel center = new JPanel();
		center.setBackground(Color.white);

		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
		JPanel cleft = new JPanel();
		
		cleft.setMinimumSize(new Dimension(350, 500));
		cleft.setMaximumSize(new Dimension(350, 500));
		cleft.setLayout(new BoxLayout(cleft, BoxLayout.Y_AXIS));
		center.add(cleft);
		

		JScrollPane js = new JScrollPane(cleft, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		js.setMaximumSize(new Dimension(300, 700));
		js.setMinimumSize(new Dimension(300, 700));
		center.add(js);


		OpenedChat c1 = new OpenedChat(icUser, "pipi Estrada", "Ya he pagao eso broooooooooo",center);
		cleft.add(c1);
		c1.getChat();
		
	


		OpenedChat c2 = new OpenedChat(icUser, "Real Zaragoza", "nuestro equipo aaa",center);
		cleft.add(c2);
	


		OpenedChat c3 = new OpenedChat(icUser, "Pipi Estrada", "Ya he pagao eso bro",center);
		cleft.add(c3);
		

		OpenedChat c4 = new OpenedChat(icUser, "Real Zaragoza", "nuestro equipo aaa",center);
		cleft.add(c4);
		OpenedChat c5 = new OpenedChat(icUser, "Pipi Estrada", "Ya he pagao eso bro",center);
		cleft.add(c5);

		OpenedChat c6 = new OpenedChat(icUser, "Real Zaragoza", "nuestro equipo aaa",center);
		cleft.add(c6);

		OpenedChat c7 = new OpenedChat(icUser, "Pipi Estrada", "Ya he pagao eso bro",center);
		cleft.add(c7);

		OpenedChat c8 = new OpenedChat(icUser, "Real Zaragoza", "nuestro equipo aaa",center);
		cleft.add(c8);
		OpenedChat c9 = new OpenedChat(icUser, "Pipi Estrada", "Ya he pagao eso bro",center);
		cleft.add(c9);

		OpenedChat c10 = new OpenedChat(icUser, "Real Zaragoza", "nuestro equipo aaa",center);
		cleft.add(c10);

		OpenedChat c11 = new OpenedChat(icUser, "Pipi Estrada", "Ya he pagao eso bro",center);
		cleft.add(c11);

		// Usar Selected Chat de aqui hasta el final

		// Este Panel incluye el JScrollPAne con el chat y la barra en
		// la que se escribe y se envian emogis, hay que subdividirla en 2

		//SelectedChat chat_1 = new SelectedChat();
		//center.add(chat_1);
	
		/*
		 * JPanel cright = new JPanel();
		 * 
		 * cright.setLayout(new BorderLayout()); center.add(cright);
		 * 
		 * JPanel cright_south = new JPanel(); cright_south.setLayout(new
		 * BoxLayout(cright_south, BoxLayout.X_AXIS)); cright.add(cright_south,
		 * BorderLayout.SOUTH);
		 * 
		 * // añadir icono de emojis, fieldtext , boton de enviar
		 * 
		 * ImageIcon icEmo = new ImageIcon("pics/happy.png"); JButton emoBt = new
		 * JButton(icEmo); cright_south.add(emoBt);
		 * 
		 * JTextField msgT = new JTextField(); msgT.setMinimumSize(new Dimension(300,
		 * 30)); msgT.setFont(new Font("Monospaced", Font.PLAIN, 25));
		 * cright_south.add(msgT);
		 * 
		 * ImageIcon icSend = new ImageIcon("pics/right-arrow.png"); JButton sendBt =
		 * new JButton(icSend); cright_south.add(sendBt);
		 * 
		 * JPanel cright_center = new JPanel();
		 * 
		 * 
		 * cright.add(cright_center, BorderLayout.CENTER);
		 * 
		 * 
		 * //Mensaje inicial panel vacío /* JLabel panelVacio = new
		 * JLabel("No hay Chats abiertos"); panelVacio.setFont(new Font("Monospaced",
		 * Font.PLAIN, 25)); cright_center.add(Box.createRigidArea(new Dimension(360,
		 * 200))); cright_center.add(panelVacio);
		 * 
		 * 
		 * 
		 * cright_center.setLayout(new BoxLayout(cright_center, BoxLayout.Y_AXIS));
		 * 
		 * 
		 * 
		 * 
		 * // nuevo chat seleccionado JScrollPane jsCh = new JScrollPane(cright_center,
		 * JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		 * JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); cright.add(jsCh);
		 * 
		 * 
		 * cright_center.setSize(600,800); cright_center.setMinimumSize(new
		 * Dimension(600, 800));
		 * 
		 * cright_center.setBackground(Color.GRAY);
		 * 
		 * BubbleText b1 = new BubbleText(cright_center,
		 * "Prueba  1 klkkkkkkkkkkkkkk kkkkkkkkk kkkkkk kkkkkkkkke", Color.cyan,"Jesus",
		 * BubbleText.SENT,20); cright_center.add(b1); BubbleText b2 = new
		 * BubbleText(cright_center, "Prueba  2 klkkkkkkkkkkkkkkkkkkkkkkkkkkk",
		 * Color.cyan,"Jesus", BubbleText.RECEIVED,20); cright_center.add(b2);
		 * BubbleText b3 = new BubbleText(cright_center,
		 * "Prueba  1 klkkkkkkkkkkkkkk kkkkkkkkk kkkkkk kkkkkkkkke", Color.cyan,"Jesus",
		 * BubbleText.SENT,20); cright_center.add(b3);
		 * 
		 * 
		 * 
		 * /* JLabel panelVacio = new JLabel("No hay Chats abiertos");
		 * panelVacio.setFont(new Font("Monospaced", Font.PLAIN, 25));
		 * cright_center.add(Box.createRigidArea(new Dimension(360, 200)));
		 * cright_center.add(panelVacio);
		 */

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
