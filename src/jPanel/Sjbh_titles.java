package jPanel;

import constants.Titles;

public class Sjbh_titles {
	public static Question title00(int min, int max, int percentRange, double exactValue) {
		StringBuffer sb = new StringBuffer();
		String year = Titles.YEARS[(int)(Math.random()*Titles.YEARS.length)];
		String month = Titles.MONTHS[(int)(Math.random()*Titles.MONTHS.length)];
		String change = Titles.CHANGE[(int)(Math.random()*Titles.CHANGE.length)];
		double fdl = Double.parseDouble(String.format("%.2f", Math.random()*(max-min)+min));
		sb.append("��ͳ�ƣ�").append(year).append(month);
		sb.append("Ƥ���𷢵���Ϊ").append(fdl).append("ǧ��ʱ��ͬ������"+month+change);
		double changeValue = Double.parseDouble(String.format("%.2f", Math.random()*percentRange));
		sb.append(changeValue+"%��������"+month+"Ƥ���𷢵���?");
		double answer = 0;
		if(change.equals(Titles.CHANGE[0])) {
			answer = fdl/(1-changeValue/100);
		}else {
			answer = fdl/(1+changeValue/100);
		}
		return new Question(sb.toString(), String.format("%.2f", answer), exactValue);
	}
	public static Question title01(int min, int max, int percentRange, double exactValue) {
		return null;
	}
	public static Question title02(int min, int max, int percentRange, double exactValue) {
		return null;
	}
	public static Question title03(int min, int max, int percentRange, double exactValue) {
		return null;
	}
	public static Question title04(int min, int max, int percentRange, double exactValue) {
		return null;
	}
}
