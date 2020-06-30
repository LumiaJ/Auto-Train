package jPanel;

import javax.swing.JPanel;
import constants.Usual;

public class MainJPanel extends JPanel{
	
	/**
	 * V1.01
	 */
	private static final long serialVersionUID = 1L;

	public void szys() {
		Szys_F szys = new Szys_F();
		szys.start();
		add(szys);
	}
	
	public void sjbh() {
		Sjbh_F sjbh = new Sjbh_F();
		sjbh.start();
		add(sjbh);
	}
	
	public void other() {
		Other other = new Other();
		other.start();
		add(other);
	}
	
	public MainJPanel() {
		setVisible(true);
		setBounds(0, Usual.BOARD_SIZE_Y, Usual.FRAME_WIDTH, Usual.FRAME_HEIGHT);
		setLayout(null);
		setVisible(true);
		szys();
	}
}
