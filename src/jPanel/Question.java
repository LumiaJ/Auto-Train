package jPanel;

import java.sql.Time;

public class Question {
	private String tm;
	private String answer;
	private String submit;
	private Time stime;
	private Time etime;
	private boolean isRight;
	private double exactValue = 0;
	
	public Question(String q, String answer, double exactValue) {
		this.tm = q;
		this.answer = answer;
		this.exactValue = exactValue;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
		double tempS = Double.parseDouble(this.submit);
		double tempA = Double.parseDouble(answer);
		if((tempS>=tempA-exactValue) &&(tempS<=tempA+exactValue)) {
			isRight = true;
		}else {
			isRight = false;
		}
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
