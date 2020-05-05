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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

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

	public SelectedChat() {

		setLayout(new BorderLayout());
		// setSize(750, 700);

		setMaximumSize(new Dimension(750, 750));
		setMinimumSize(new Dimension(750, 700));
		setBackground(Color.green);
		// topPanel

		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(550, 70));
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.setBackground(Color.CYAN);

		// get imagen del usuario abierto y su nombre y su info
		final ImageIcon icUInf = new ImageIcon("pics/inhigo.jpg");
		contactInfo = new JButton("Iñigo Errejón", icUInf);
		ImageIcon icSearch = new ImageIcon("pics/magnifying-glass.png");
		ImageIcon icDots = new ImageIcon("pics/menu.png");
		JLabel lupaLb = new JLabel(icSearch);
		JLabel puntos = new JLabel(icDots);

		topPanel.add(Box.createRigidArea(new Dimension(20, 60)));
		topPanel.add(contactInfo);
		topPanel.add(Box.createRigidArea(new Dimension(410, 60)));
		topPanel.add(lupaLb);
		topPanel.add(puntos);

		add(topPanel, BorderLayout.NORTH);
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
		
		JScrollPane jsCh = new JScrollPane(midPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsCh.setPreferredSize(new Dimension(600, 545));
		jsCh.setSize(new Dimension(600, 545));
		jsCh.setMaximumSize(new Dimension(600, 545));
		jsCh.setMinimumSize(new Dimension(600, 545));
		

		add(jsCh,BorderLayout.CENTER);

	

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
				JLabel name = new JLabel("Nombre Contacto");
				JLabel tel = new JLabel("Telf :           ");
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
					BubbleText borboja = new BubbleText(midPanel, msgT.getText(), Color.cyan, "JUANPABLO",
							BubbleText.SENT);
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
