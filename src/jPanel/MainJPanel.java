package jPanel;

import javax.swing.JPanel;
import constants.Utils;

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
		setBounds(0, Utils.BOARD_SIZE_Y, Utils.FRAME_WIDTH, Utils.FRAME_HEIGHT);
		setLayout(null);
		setVisible(true);
		szys();
	}
}
