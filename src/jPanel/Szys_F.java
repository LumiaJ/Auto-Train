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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.Usual;

public class Szys_F extends JPanel{
	/**
	 * ����������� V1.00
	 */
	private static final long serialVersionUID = 1L;
	private JLabel tm = new JLabel();
	private JLabel[] menu = new JLabel[Usual.SZYS_MENU.length];
	private JLabel result = new JLabel();
	private JTextField jtf0 = new JTextField();
	private JTextField jtfcount = new JTextField();
	private JTextField jtfmin = new JTextField();
	private JTextField jtfmax = new JTextField();
	private int count, min, max, nowtm;
	private List<Question> questions;
	private boolean isStart = false;
	private JButton start = new JButton();
	
	
	public void start() {
		setBounds(0, 0, Usual.FRAME_WIDTH, Usual.FRAME_HEIGHT);
		setVisible(true);
		setLayout(null);
		setJButton();
		setMenu();
		setJTextField();
		add(jtfcount);add(jtfmin);add(jtfmax);
		add(start);
		for(JLabel j : menu) {
			add(j);
		}
	}
	
	/**
	 * ��������ĵ���
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
		sb.append("������\t\t");
		sb.append(count+"\n");
		sb.append("��ȷ��\t\t");
		sb.append(right+"\n");
		sb.append("����\t\t");
		sb.append(wrong+"\n");
		sb.append("��ȷ�ʣ�\t\t");
		sb.append(String.format("%.2f", right*100.0/count)+"%\n");
		sb.append("��ʱ��\t\t");
		sb.append(String.format("%.2f", wt/1000.0)+"s\n");
		sb.append("ƽ����ʱ��\t\t");
		sb.append(String.format("%.2f", wt/1000.0/count)+"s\n");
		
		String[] strs = {"����һ��!","�ȵ������"};
		return JOptionPane.showOptionDialog(null,
				sb,"�������",JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,null,strs,strs[0]);
	}
	
	/**
	 * �����ʾ���
	 */
	private void setResult() {
		String resultStr = "";
		if(questions.get(nowtm-1).isRight()) {
			result.setForeground(Color.BLACK);
			resultStr += Usual.DUI;
		}else {
			result.setForeground(Color.RED);
			resultStr += Usual.CUO;
			resultStr += questions.get(nowtm-1).getTm()+questions.get(nowtm-1).getAnswer();
		}
		resultStr += "�����ʱ:" + questions.get(nowtm-1).getTime()/1000.0 + "s"; 
		result.setFont(Usual.FONT);
		result.setBounds(0,Usual.SIZE_Y[5],Usual.FRAME_WIDTH,Usual.O_H_HEIGHT*2);
		result.setText(resultStr);
		result.setHorizontalAlignment(JLabel.CENTER);
	}
	

	/***
	 * ��ʼ���ⰴť
	 */
	private void setJButton() {
		start.setFont(Usual.FONT);
		start.setVisible(true);
		start.setText("��ʼ����");
		start.setBounds(Usual.FRAME_WIDTH/2-Usual.SZYS_START_BUTTON_WID/2,
				Usual.SIZE_Y[4],Usual.SZYS_START_BUTTON_WID, Usual.O_H_HEIGHT);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isStart = true;
				setQuestion();
				start.setEnabled(false);
				jtfcount.setEnabled(false);
				jtfmax.setEnabled(false);
				jtfmin.setEnabled(false);
				setSubmit();
				setTm();
				add(jtf0);
				add(tm);
				repaint();
			}
		});
	}
	
	/***
	 * ͨ������Ĳ������������Ŀ
	 */
	private void setQuestion() {
		questions = new ArrayList<Question>();
		count = Integer.parseInt(jtfcount.getText());
		min = Integer.parseInt(jtfmin.getText());
		max = Integer.parseInt(jtfmax.getText());
		for(int i = 0; i<count;i++) {
			int num1 = (int)(Math.random()*(max-min)+min);
			int num2 = (int)(Math.random()*(max-min)+min);
			String q = "",answer = "";
			int random = (int)(Math.random()*4);
			switch(random) {
			case 0:
				q = num1 + " + " + num2 + " = ";
				answer = (num1+num2)+"";
				break;
			case 1:
				q = num1 + " - " + num2 + " = ";
				answer = (num1-num2)+"";
				break;
			case 2:
				q = num1 + " * " + num2 + " = ";
				answer = (num1*num2)+"";
				break;
			case 3:
				q = num1 + " / " + num2 + " = ";
				answer = String.format("%.2f", (num1*1.0/num2));
				break;
			}
			questions.add(new Question(q, answer, 0));
		}
	}
	
	/***
	 * �����������
	 */
	private void setSubmit() {
		jtf0.setFont(Usual.FONT);
		jtf0.setHorizontalAlignment(JTextField.CENTER);
		jtf0.setBounds(Usual.FRAME_WIDTH/2-Usual.SZYS_TEXT_SUB_WID/2, Usual.SIZE_Y[3],
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
							JOptionPane.showMessageDialog(null,"�����˵����İ�ť����");
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
	 * �������������
	 */
	private void setJTextField() {
		jtfcount.setFont(Usual.FONT);
		jtfcount.setHorizontalAlignment(JTextField.CENTER);
		jtfcount.setBounds(50, Usual.SIZE_Y[1], Usual.SZYS_TEXT_SUB_WID, Usual.O_H_HEIGHT);
		jtfcount.setEnabled(true);

		jtfmin.setFont(Usual.FONT);
		jtfmin.setHorizontalAlignment(JTextField.CENTER);
		jtfmin.setBounds(250, Usual.SIZE_Y[1], Usual.SZYS_TEXT_SUB_WID, Usual.O_H_HEIGHT);
		jtfmin.setEnabled(true);

		jtfmax.setFont(Usual.FONT);
		jtfmax.setHorizontalAlignment(JTextField.CENTER);
		jtfmax.setBounds(450, Usual.SIZE_Y[1], Usual.SZYS_TEXT_SUB_WID, Usual.O_H_HEIGHT);
		jtfmax.setEnabled(true);
	}
	
	/***
	 * ���˵��ı�����
	 */
	private void setMenu() {
		for(int i = 0; i<menu.length;i++) {
			menu[i] = new JLabel(Usual.SZYS_MENU[i]);
			menu[i].setFont(Usual.FONT);
			menu[i].setBounds(i*200+50,Usual.SIZE_Y[0],Usual.SZYS_MENU_WID,Usual.O_H_HEIGHT);
			menu[i].setHorizontalAlignment(JLabel.CENTER);
		}
	}
	
	/***
	 * ��Ŀ�ı�����
	 */
	private void setTm() {
		tm.setFont(Usual.FONT);
		tm.setBounds(0,Usual.SIZE_Y[2],Usual.FRAME_WIDTH,Usual.O_H_HEIGHT);
		tm.setText(questions.get(nowtm).getTm());
		questions.get(nowtm).setsTime(new Time(System.currentTimeMillis()));
		nowtm++;
		tm.setHorizontalAlignment(JLabel.CENTER);
	}

	public Szys_F() {
	}

}
