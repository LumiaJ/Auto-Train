package jPanel;

import java.sql.Time;

public class Question {
	private int num;
	private String tm;
	private String answer;
	private String submit;
	private Time stime;
	private Time etime;
	private boolean isRight;
	
	public Question(int i, String q, String answer) {
		this.num = i;
		this.tm = q;
		this.answer = answer;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
		if(Double.parseDouble(this.submit)==Double.parseDouble(answer)) {
			isRight = true;
		}else {
			isRight = false;
		}
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTm() {
		return tm;
	}
	public String getAnswer() {
		return answer;
	}
	public void setsTime(Time stime) {
		this.stime = stime;
	}
	public void seteTime(Time etime) {
		this.etime = etime;
	}
	public Long getTime() {
		return etime.getTime()-stime.getTime();
	}
	public boolean isRight() {
		return isRight;
	}
}
