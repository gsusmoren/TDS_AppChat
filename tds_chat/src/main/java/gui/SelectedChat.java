package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import tds.BubbleText;
/**
 * Este es el panel que aparece al clicar en una conversacion de la 
 * barra de la izquierda
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

	public SelectedChat() {
		
		setLayout(new BorderLayout());
		setSize(750, 700);
		
		setMaximumSize(new Dimension(750, 750));
		setMinimumSize(new Dimension(750, 700));
		setBackground(Color.green);
		//topPanel
		
		
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(550, 70));
		add(topPanel,BorderLayout.NORTH);
		//barra inferior
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
		add(botPanel, BorderLayout.SOUTH);
		
		
		
		midPanel = new JPanel();

		add(midPanel, BorderLayout.CENTER);

		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));

		JScrollPane jsCh = new JScrollPane(midPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		add(jsCh);

		midPanel.setSize(600, 545);
		midPanel.setMinimumSize(new Dimension(600, 545));
		midPanel.setMaximumSize(new Dimension(600, 545));
		midPanel.setPreferredSize(new Dimension(600, 545));
		midPanel.setBackground(Color.GRAY);
		
		// Son 25 emogis

		sendBt.addActionListener(new ActionListener() { 

			public void actionPerformed(ActionEvent e) {
				if (msgT.getText().length() > 0) {
					BubbleText borboja = new BubbleText(midPanel, msgT.getText(), Color.cyan, "JUANPABLO",
							BubbleText.SENT);
					midPanel.add(borboja);
					// cambiar Last
					repaint();
				}
				msgT.setText("");
				msgT.grabFocus();
				
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
				//hay 25 emogis
				JLabel emo[] = new JLabel[25];

				for (int i = 0; i < 25; i++) {
					//Copia del iterado como final para poder usarlo con el mouseListener
					final int i2 = i;
					emo[i] = new JLabel(BubbleText.getEmoji(i));
					emoPanel.add(emo[i]);

					emo[i].addMouseListener(new MouseAdapter() {

						public void mouseClicked(MouseEvent e) {
							// Introducir Nombre del Usuario que los envía
							BubbleText emoSent = new BubbleText(midPanel, i2, Color.CYAN, "JUANPABLO", BubbleText.SENT,
									15);
							midPanel.add(emoSent);
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

}
