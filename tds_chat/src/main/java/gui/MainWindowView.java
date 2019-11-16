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
import javax.swing.Icon;;

public class MainWindowView {
	private JFrame mainfrm;
	private JButton userInf_btn,msgSea_btn,chatOpt_btn;
	

	
	public MainWindowView() {
		initialize();
	}

	private void initialize() {
		
		mainfrm = new JFrame();
		mainfrm.setTitle("ChatApp");
		mainfrm.setBounds(300, 200, 1000, 700);
		mainfrm.getContentPane().setLayout(new BorderLayout());
		mainfrm.setResizable(false);
	//	mainfrm.getContentPane().setBackground(new Color(13,115,119));
		
		//Panel Barra Superior
		final JPanel topBar = new JPanel();
		topBar.setBackground(Color.cyan);
		mainfrm.getContentPane().add(topBar,BorderLayout.NORTH);
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
		mainfrm.getContentPane().add(center,BorderLayout.CENTER);
		
		JPanel cright = new JPanel();
		JPanel cleft = new JPanel();
		
	}
	public void mostrarVentana() {
		mainfrm.setVisible(true);

	}
//BORRAR MAIN
	public static void main(String[] args) {
		MainWindowView mWv = new MainWindowView();
		mWv.mostrarVentana();
	}
}
