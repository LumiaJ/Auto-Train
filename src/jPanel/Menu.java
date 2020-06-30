package jPanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import constants.*;

public class Menu extends JPanel{
	/**
	 * V1.01
	 */
	private static final long serialVersionUID = 1L;
	JButton[] jb = new JButton[3];
	
	public void change(JButton j) {
		for(int i=0;i<jb.length;i++) {
			jb[i].setForeground(Color.BLACK);
		}
		for(int i=0;i<jb.length;i++) {
			if(jb[i].equals(j)) {
				jb[i].setForeground(Color.RED);
			}
		}
	}
	
	public Menu() {
		setBounds(0, 0, Usual.FRAME_WIDTH, Usual.MENU_F_HEIGHT);
		setVisible(true);
		for(int i=0;i<jb.length;i++) {
			jb[i]= new JButton();
		}
		this.jb[0].setText("四则运算");
		this.jb[0].setFont(Usual.FONT);
		this.jb[0].setBounds(0, 0, Usual.MENU_BUTTON_WIDTH, Usual.MENU_BUTTON_HEIGHT);
		this.jb[0].setForeground(Color.RED);
		add(jb[0]);
		
		this.jb[1].setText("数据变化");
		this.jb[1].setFont(Usual.FONT);
		this.jb[1].setBounds(0, 0, Usual.MENU_BUTTON_WIDTH, Usual.MENU_BUTTON_HEIGHT);
		add(jb[1]);
		
		this.jb[2].setText("有待增加");
		this.jb[2].setFont(Usual.FONT);
		this.jb[2].setBounds(0, 0, Usual.MENU_BUTTON_WIDTH, Usual.MENU_BUTTON_HEIGHT);
		add(jb[2]);
	}
}
