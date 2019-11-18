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

import javax.swing.Icon;;

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

		JPanel center = new JPanel();
		center.setBackground(Color.white);
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
		JPanel cleft = new JPanel();
		cleft.setMinimumSize(new Dimension(340,500));

		cleft.setLayout(new BoxLayout(cleft, BoxLayout.Y_AXIS));
		center.add(cleft);

		JScrollPane js = new JScrollPane(cleft, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
				center.add(js);

		OpenedChat c1 = new OpenedChat(icUser,"pipi Estrada", "Ya he pagao eso broooooooooooooooooooo");
		cleft.add(c1);
		

		OpenedChat c2 = new OpenedChat(icUser, "Real Zaragoza", "nuestro equipo aaa");
		cleft.add(c2);

		OpenedChat c3 = new OpenedChat(icUser, "Pipi Estrada", "Ya he pagao eso bro");
		cleft.add(c3);

		OpenedChat c4 = new OpenedChat(icUser, "Real Zaragoza", "nuestro equipo aaa");
		cleft.add(c4);
		OpenedChat c5 = new OpenedChat(icUser, "Pipi Estrada", "Ya he pagao eso bro");
		cleft.add(c5);

		OpenedChat c6 = new OpenedChat(icUser, "Real Zaragoza", "nuestro equipo aaa");
		cleft.add(c6);

		OpenedChat c7 = new OpenedChat(icUser, "Pipi Estrada", "Ya he pagao eso bro");
		cleft.add(c7);

		OpenedChat c8 = new OpenedChat(icUser, "Real Zaragoza", "nuestro equipo aaa");
		cleft.add(c8);
		OpenedChat c9 = new OpenedChat(icUser, "Pipi Estrada", "Ya he pagao eso bro");
		cleft.add(c9);

		OpenedChat c10 = new OpenedChat(icUser, "Real Zaragoza", "nuestro equipo aaa");
		cleft.add(c10);

		OpenedChat c11 = new OpenedChat(icUser, "Pipi Estrada", "Ya he pagao eso bro");
		cleft.add(c11);

		cleft.add(Box.createVerticalGlue());

		// Este Panel incluye el JScrollPAne con el chat y la barra en
		// la que se escribe y se envian emogis, hay que subdividirla en 2

		JPanel cright = new JPanel();
		// cright.add(Box.createRigidArea(new Dimension(500, 800)));
		cright.setLayout(new BorderLayout());
		center.add(cright);

		JPanel cright_south = new JPanel();
		cright_south.setLayout(new BoxLayout(cright_south, BoxLayout.X_AXIS));
		cright.add(cright_south, BorderLayout.SOUTH);

		// añadir icono de emojis, fieldtext , boton de enviar

		ImageIcon icEmo = new ImageIcon("pics/happy.png");
		JButton emoBt = new JButton(icEmo);
		cright_south.add(emoBt);

		JTextField msgT = new JTextField();
		msgT.setMinimumSize(new Dimension(300, 30));
		msgT.setFont(new Font("Monospaced", Font.PLAIN, 25));
		cright_south.add(msgT);
		
		
		ImageIcon icSend = new ImageIcon("pics/right-arrow.png");
		JButton sendBt = new JButton(icSend);
		cright_south.add(sendBt);
		
		
		JPanel cright_center = new JPanel();
		
		cright_center.setLayout(new BoxLayout(cright_center, BoxLayout.Y_AXIS));
		
		
		
		//nuevo chat seleccionado
		JScrollPane jsCh = new JScrollPane(cright_center, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		cright.add(jsCh);
		
		
		
		
		

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
