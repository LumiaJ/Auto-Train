package jPanel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.UIManager;

import constants.Usual;

public class MyFrame extends JFrame{
	/**
	 * V2.01
	 */
	private static final long serialVersionUID = 1L;
	private MainJPanel m = new MainJPanel();
	private Menu n = new Menu();
	
	public void start() throws Exception {
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		setTitle("数资快速计算练习");
		setResizable(false);
		setBounds((int) screensize.getWidth() / 2 - Usual.FRAME_WIDTH/2,
				(int) screensize.getHeight() / 2 - Usual.FRAME_HEIGHT/2,
				Usual.FRAME_WIDTH, Usual.FRAME_HEIGHT);
		setVisible(true);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(n);
		change();
		add(m);
	}
	
	public void change() {
		n.jb[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				n.change(n.jb[0]);
				m.removeAll();
				m.szys();
				setVisible(true);
				add(m);
				repaint();
			}
		});
		
		n.jb[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				n.change(n.jb[1]);
				m.removeAll();
				m.sjbh();
				setVisible(true);
				add(m);
				repaint();
			}
		});
		
		n.jb[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				n.change(n.jb[1]);
				m.removeAll();
				m.other();
				setVisible(true);
				add(m);
				repaint();
			}
		});
	}

	public MyFrame() {
	}
	
	public static void main(String[] args) throws Exception {
		new MyFrame().start();
	}
}
