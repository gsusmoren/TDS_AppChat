package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	this.setSize(750, 700);
	this.setMaximumSize(new Dimension(750, 700));
	this.setMinimumSize(new Dimension(750, 700));
	ImageIcon icEmo = new ImageIcon("pics/happy.png");
	emoBt = new JButton(icEmo);
	this.setBackground(Color.gray);
	
	cright_south.add(emoBt);
	
	msgT = new JTextField();
	msgT.setMinimumSize(new Dimension(300, 30));
	msgT.setFont(new Font("Monospaced", Font.PLAIN, 25));
	cright_south.add(msgT);
	ImageIcon icSend = new ImageIcon("pics/right-arrow.png");
	sendBt = new JButton(icSend);
	
	cright_south.add(sendBt);
	final JPanel cright_center = new JPanel();
	
	add(cright_center, BorderLayout.CENTER);
	
	cright_center.setLayout(new BoxLayout(cright_center, BoxLayout.Y_AXIS));
	
	JScrollPane jsCh = new JScrollPane(cright_center, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	
	add(jsCh);
	
	cright_center.setSize(600,800);
	cright_center.setMinimumSize(new Dimension(600, 800));
	cright_center.setMaximumSize(new Dimension(600, 800));
	cright_center.setBackground(Color.GRAY);
	
	sendBt.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			if(msgT.getText().length()>0) {
				BubbleText borboja = new BubbleText(cright_center, msgT.getText(), Color.cyan, "Usuario", BubbleText.SENT);
				cright_center.add(borboja);
				//cambiar Last
			}
			msgT.setText("");
		msgT.grabFocus();
			
		}
	});




	}
	

	
	
	
	

}
