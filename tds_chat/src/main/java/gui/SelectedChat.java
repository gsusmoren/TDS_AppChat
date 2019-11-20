package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import tds.BubbleText;

@SuppressWarnings("serial")
public class SelectedChat extends JPanel{
	private JPanel cright_south;
	private JTextField msgT;
	private JButton emoBt;
	private JButton sendBt;
	

	public SelectedChat() {
		
	setLayout(new BorderLayout());
	cright_south = new JPanel();
	cright_south.setLayout(new BoxLayout(cright_south,BoxLayout.X_AXIS));
	add(cright_south,BorderLayout.SOUTH);
	
	ImageIcon icEmo = new ImageIcon("pics/happy.png");
	emoBt = new JButton(icEmo);
	
	cright_south.add(emoBt);
	
	msgT = new JTextField();
	msgT.setMinimumSize(new Dimension(300, 30));
	msgT.setFont(new Font("Monospaced", Font.PLAIN, 25));
	cright_south.add(msgT);
	ImageIcon icSend = new ImageIcon("pics/right-arrow.png");
	sendBt = new JButton(icSend);
	cright_south.add(sendBt);

	JPanel cright_center = new JPanel();
	
	add(cright_center, BorderLayout.CENTER);
	
	cright_center.setLayout(new BoxLayout(cright_center, BoxLayout.Y_AXIS));
	
	JScrollPane jsCh = new JScrollPane(cright_center, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	
	add(jsCh);
	
	cright_center.setSize(600,800);
	cright_center.setMinimumSize(new Dimension(600, 800));
	
	cright_center.setBackground(Color.GRAY);
	
	BubbleText b1 = new BubbleText(cright_center, "Prueba  1 klkkkkkkkkkkkkkk kkkkkkkkk kkkkkk kkkkkkkkke", Color.cyan,"Jesus", BubbleText.SENT,20);
	cright_center.add(b1);
	BubbleText b2 = new BubbleText(cright_center, "Prueba  2 klkkkkkkkkkkkkkkkkkkkkkkkkkkk", Color.cyan,"Jesus", BubbleText.RECEIVED,20);
	cright_center.add(b2);
	BubbleText b3 = new BubbleText(cright_center, "Prueba  1 klkkkkkkkkkkkkkk kkkkkkkkk kkkkkk kkkkkkkkke", Color.cyan,"Jesus", BubbleText.SENT,20);
	cright_center.add(b3);

	}
	
	public void EnviarMensaje() {
		
		
		
	}
	
	
	
	
	

}
