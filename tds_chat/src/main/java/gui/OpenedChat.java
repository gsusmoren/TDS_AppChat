/*
 * Esta Clase representa graficamente un chat abierto con cierto contacto 
 * en el lado izquierdo de la pantalla principal
 * 
 */
package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.time.LocalDate;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class OpenedChat extends JPanel{
	
	private ImageIcon icono;
	private  String name;
	private LocalDate lstDate;
	private String ultMsg;
	
	public OpenedChat(ImageIcon ic,String nm,String ult) {
		icono =ic;
		name =  nm;
		ultMsg = ult;
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		Image im = icono.getImage();
		Image scaled = im.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		icono = new ImageIcon(scaled);
		JLabel icon =new  JLabel(icono);	
		add(icon);
		JPanel pRight = new JPanel();
		pRight.setLayout(new BoxLayout(pRight, BoxLayout.Y_AXIS));
		

		add(pRight);
		
		JPanel pRightTop = new JPanel();
		pRightTop.setLayout(new BoxLayout(pRightTop,BoxLayout.X_AXIS));
		pRightTop.add(Box.createHorizontalGlue());
		pRight.add(pRightTop);
		JPanel pRightBot = new JPanel();
		pRight.add(pRightBot);
		
		JLabel nameL = new JLabel(name);
		JLabel dateL = new JLabel(LocalDate.now().toString());
		JLabel last = new JLabel(ultMsg);
		nameL.setMaximumSize(new Dimension(80,20));
	
		//dateL.setMinimumSize(new Dimension(100,10));
		pRightTop.add(nameL);
		pRightTop.add(Box.createRigidArea(new Dimension(70, 10)));
		pRightTop.add(dateL);
		last.setMaximumSize(new Dimension(210, 20));
		last.setPreferredSize(new Dimension(210, 20));
		pRightBot.add(last);
		
	

		
		//this.add(Box.createRigidArea(new Dimension(60, 20)));
		
		setBackground(Color.red);
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
		return lstDate;
	}

	public void setLstDate(LocalDate lstDate) {
		this.lstDate = lstDate;
	}

	public String getUltMsg() {
		return ultMsg;
	}

	public void setUltMsg(String ultMsg) {
		this.ultMsg = ultMsg;
	}
	
}
