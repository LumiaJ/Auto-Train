package jPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.Usual;

public class Sjbh_F extends JPanel{
	/**
	 * 数据变化 V1.00
	 */
	private static final long serialVersionUID = 1L;
	private JLabel tm = new JLabel();
	private JLabel[] menu = new JLabel[Usual.SJBH_MENU.length];
	private JLabel result = new JLabel();
	private JTextField jtf0, jtfcount, jtfmin, jtfmax, jtfpr, jtfev;
	private int count, min, max, nowtm, percentRange;
	private double exactValue;
	private JComboBox<String> isExact;
	private List<Question> questions;
	private boolean isStart = false;
	private JButton start = new JButton();
	
	
	public void start() {
		setBounds(0, 0, Usual.FRAME_WIDTH, Usual.FRAME_HEIGHT);
		setVisible(true);
		setLayout(null);
		setJButton();
		setMenu();
		setComboBox();
		add(isExact);
		setJTextField();
		add(jtfcount);add(jtfmin);add(jtfmax);add(jtfpr);add(jtfev);
		add(start);
		for(JLabel j : menu) {
			add(j);
		}
	}
	
	/**
	 * 是否需要模糊计算的下拉框选项
	 */
	private void setComboBox() {
		isExact = new JComboBox<String>();
		isExact.setFont(Usual.FONT);
		isExact.setBounds(250, Usual.SIZE_Y[1],
				Usual.SZYS_TEXT_SUB_WID, Usual.O_H_HEIGHT);
		for(String s : Usual.ISEXACT_TEXT) {
			isExact.addItem(s);
		}
		isExact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((String)isExact.getSelectedItem()).equals(Usual.ISEXACT_TEXT[0])) {
					jtfev.setEnabled(false);
				}else {
					jtfev.setEnabled(true);
				}
			}
		});
	}
	
	/**
	 * 答题结束的弹窗
	 */
	private int endTrain() {
		int right=0,wrong=0,wt=0;
		for(Question q : questions) {
			if(q.isRight()) {
				right++;
			}else {
				wrong++;
			}
			wt+=q.getTime();
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("题数：\t\t");
		sb.append(count+"\n");
		sb.append("正确：\t\t");
		sb.append(right+"\n");
		sb.append("错误：\t\t");
		sb.append(wrong+"\n");
		sb.append("正确率：\t\t");
		sb.append(String.format("%.2f", right*100.0/count)+"%\n");
		sb.append("耗时：\t\t");
		sb.append(String.format("%.2f", wt/1000.0)+"s\n");
		sb.append("平均耗时：\t\t");
		sb.append(String.format("%.2f", wt/1000.0/count)+"s\n");
		
		String[] strs = {"再来一次!","先到这里吧"};
		return JOptionPane.showOptionDialog(null,
				sb,"答题结束",JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,null,strs,strs[0]);
	}
	
	/**
	 * 结果显示面板
	 */
	private void setResult() {
		String resultStr = "";
		if(questions.get(nowtm-1).isRight()) {
			result.setForeground(Color.BLACK);
			resultStr += Usual.DUI;
		}else {
			result.setForeground(Color.RED);
			resultStr += Usual.CUO;
			resultStr += "正确答案为："+questions.get(nowtm-1).getAnswer();
		}
		resultStr += "\n本题耗时:" + questions.get(nowtm-1).getTime()/1000.0 + "s"; 
		result.setFont(Usual.FONT);
		result.setBounds(0,Usual.SIZE_Y[8],Usual.FRAME_WIDTH,Usual.O_H_HEIGHT*2);
		result.setText("<html>"+resultStr+"</html>");
		result.setHorizontalAlignment(JLabel.CENTER);
	}
	

	/***
	 * 开始做题按钮
	 */
	private void setJButton() {
		start.setFont(Usual.FONT);
		start.setVisible(true);
		start.setText("开始做题");
		start.setBounds(Usual.FRAME_WIDTH/2-Usual.SZYS_START_BUTTON_WID/2,
				Usual.SIZE_Y[7],Usual.SZYS_START_BUTTON_WID, Usual.O_H_HEIGHT);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isStart = true;
				setQuestion();
				start.setEnabled(false);
				jtfcount.setEnabled(false);
				jtfmax.setEnabled(false);
				jtfmin.setEnabled(false);
				jtfpr.setEnabled(false);
				isExact.setEnabled(false);
				jtfev.setEnabled(false);
				setSubmit();
				setTm();
				add(jtf0);
				add(tm);
				repaint();
			}
		});
	}
	
	/***
	 * 通过输入的参数随机生成题目
	 */
	private void setQuestion() {
		//TODO
		questions = new ArrayList<Question>();
		count = Integer.parseInt(jtfcount.getText());
		min = Integer.parseInt(jtfmin.getText());
		max = Integer.parseInt(jtfmax.getText());
		percentRange = Integer.parseInt(jtfpr.getText());
		if(((String)isExact.getSelectedItem()).equals(Usual.ISEXACT_TEXT[0])) {
			exactValue = 0;
		}else {
			exactValue = Double.parseDouble(jtfev.getText());
		}
		
		for(int i = 0; i<count;i++) {
			questions.add(Sjbh_titles.title00(min, max, percentRange, exactValue));
		}
	}
	
	/***
	 * 答案输入框设置
	 */
	private void setSubmit() {
		jtf0 = new JTextField();
		jtf0.setFont(Usual.FONT);
		jtf0.setHorizontalAlignment(JTextField.CENTER);
		jtf0.setBounds(Usual.FRAME_WIDTH/2-Usual.SZYS_TEXT_SUB_WID/2, Usual.SIZE_Y[6],
				Usual.SZYS_TEXT_SUB_WID, Usual.O_H_HEIGHT);
		jtf0.setEnabled(true);
		jtf0.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					questions.get(nowtm-1).setSubmit(jtf0.getText());
					questions.get(nowtm-1).seteTime(new Time(System.currentTimeMillis()));
					setResult();
					add(result);
					if(nowtm == count) {
						isStart = false;
					}
					if(isStart) {
						setTm();
						jtf0.setText("");
						repaint();
					}else {
						switch(endTrain()) {
						case 0:
							setQuestion();
							nowtm=0;
							setTm();
							isStart = true;
							jtf0.setText("");
							repaint();
							break;
						case 1:
							JOptionPane.showMessageDialog(null,"请点击菜单栏的按钮重置");
							break;
						}
					}
				}
			}
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
		});
	}
	
	/**
	 * 参数输入框设置
	 */
	private void setJTextField() {
		jtfcount = new JTextField();
		jtfcount.setFont(Usual.FONT);
		jtfcount.setHorizontalAlignment(JTextField.CENTER);
		jtfcount.setBounds(50, Usual.SIZE_Y[1], Usual.SZYS_TEXT_SUB_WID, Usual.O_H_HEIGHT);
		jtfcount.setEnabled(true);
		
		jtfev = new JTextField();
		jtfev.setFont(Usual.FONT);
		jtfev.setHorizontalAlignment(JTextField.CENTER);
		jtfev.setBounds(450, Usual.SIZE_Y[1], Usual.SZYS_TEXT_SUB_WID, Usual.O_H_HEIGHT);
		jtfev.setEnabled(false);
		
		jtfpr = new JTextField();
		jtfpr.setFont(Usual.FONT);
		jtfpr.setHorizontalAlignment(JTextField.CENTER);
		jtfpr.setBounds(50, Usual.SIZE_Y[3], Usual.SZYS_TEXT_SUB_WID, Usual.O_H_HEIGHT);
		jtfpr.setEnabled(true);
		
		jtfmin = new JTextField();
		jtfmin.setFont(Usual.FONT);
		jtfmin.setHorizontalAlignment(JTextField.CENTER);
		jtfmin.setBounds(250, Usual.SIZE_Y[3], Usual.SZYS_TEXT_SUB_WID, Usual.O_H_HEIGHT);
		jtfmin.setEnabled(true);

		jtfmax = new JTextField();
		jtfmax.setFont(Usual.FONT);
		jtfmax.setHorizontalAlignment(JTextField.CENTER);
		jtfmax.setBounds(450, Usual.SIZE_Y[3], Usual.SZYS_TEXT_SUB_WID, Usual.O_H_HEIGHT);
		jtfmax.setEnabled(true);
	}
	
	/***
	 * 面板菜单文本设置
	 */
	private void setMenu() {
		for(int i = 0; i<menu.length;i++) {
			menu[i] = new JLabel(Usual.SJBH_MENU[i]);
			menu[i].setFont(Usual.FONT);
			int x = i%3;
			int y = i/3*2;
			menu[i].setBounds(Usual.SIZE_X[x],Usual.SIZE_Y[y],Usual.SZYS_MENU_WID,Usual.O_H_HEIGHT);
			menu[i].setHorizontalAlignment(JLabel.CENTER);
		}
	}
	
	/***
	 * 题目文本设置
	 */
	private void setTm() {
		tm.setFont(Usual.FONT);
		tm.setBounds(0,Usual.SIZE_Y[4],Usual.FRAME_WIDTH,Usual.O_H_HEIGHT*2);
		tm.setText("<html>"+questions.get(nowtm).getTm()+"</html>");
		questions.get(nowtm).setsTime(new Time(System.currentTimeMillis()));
		nowtm++;
		tm.setHorizontalAlignment(JLabel.CENTER);
	}

	public Sjbh_F() {
	}

}
