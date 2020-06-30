package constants;

import java.awt.Font;

public class Usual {
	public static final String DUI = "恭喜你！回答正确！";	//答对的提示语
	public static final String CUO = "很遗憾！回答错误！";	//答错的提示语
	public static final int FRAME_WIDTH = 700;		//窗口的宽度
	public static final int FRAME_HEIGHT = 600;		//窗口的高度
	public static final int MENU_F_HEIGHT = 50;		//菜单栏高度
	public static final int MENU_BUTTON_WIDTH = 100;	//菜单按钮的宽度
	public static final int MENU_BUTTON_HEIGHT = 32;	//菜单按钮的高度
	public static final int BOARD_SIZE_Y = 60;		//面板Y坐标
	public static final Font FONT = new Font("微软雅黑", 0, 20);	//窗口文字字体
	public static final String[] SZYS_MENU = {"训练的题数:"
			,"最小数字:","最大数字:"};		//四则运算面板参数菜单
	public static final int O_H_HEIGHT = 30;	//一行的高度
	public static final int SZYS_TEXT_SUB_WID = 160; 	//四则运算参数输入栏宽度
	public static final int SZYS_MENU_WID = 160; 	//四则运算菜单文字宽度
	public static final int SZYS_START_BUTTON_WID = 160; 	//四则运算开始按钮宽度
	public static final int[] SIZE_Y = {0,40,80,120,160,200,240,280,320,360};	//每一行的Y坐标
	public static final int[] SIZE_X = {50,250,450};		//每一行的X坐标
	
	public static final String[] SJBH_MENU = {"训练的题数","是否模糊答案","答案误差","百分比范围(%)","已知量的最小值","已知量的最大值"};
	public static final String[] ISEXACT_TEXT = {"否","是"};
	public static final int[] YEARS = {2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020};
	
}
