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



@SuppressWarnings("serial")
public class MainWindowView extends JFrame {

	private JPanel contentPane;
	private JButton userInf_btn;
	 

	public MainWindowView() {
		this.setTitle("ChatApp");
		this.setBounds(Constantes.mainWindow_x, Constantes.mainWindow_y,Constantes.mainWx_size , Constantes.mainWy_size);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		this.setContentPane(contentPane);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		

		// Panel Barra Superior
		final JPanel topBar = new JPanel();
		topBar.setBackground(Color.cyan);
		topBar.setSize(1000, 30);
		topBar.setMaximumSize(new Dimension(1000,30));
		topBar.setMinimumSize(new Dimension(1000,30));

		contentPane.add(topBar, BorderLayout.NORTH);
		topBar.setLayout(new FlowLayout());

		final ImageIcon icUser = new ImageIcon("pics/icon_profile.png");
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
		final JFrame copiaFrame = this;
		us.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				//pasar saludo del usuario
			EditProfileWindow eProf = new EditProfileWindow(copiaFrame,icUser,"+\"k pasa poyeta\"+");

			}
		});
		
		topBar.add(Box.createRigidArea(new Dimension(120, 30)));
		topBar.add(st);
		topBar.add(op);
		topBar.add(userInf_btn);
		topBar.add(Box.createRigidArea(new Dimension(400, 30)));
		topBar.add(mg);
		topBar.add(chOp);
		 final JPanel center = new JPanel();
		center.setBackground(Color.white);

		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
		JPanel cleft = new JPanel();
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
