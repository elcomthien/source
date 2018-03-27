package com.elcom.eodapp.ehotel.common;


public interface Command {
	public static String GI = "GI";   //Guest Check-in
	public static String GO = "GO";   //Guest Check-out
	public static String RO = "RO";   //Guest Check-out
	public static String GC = "GC";   //Guest channge data
	public static String RG = "RG";   //Room Move Guest
	public static String RR = "RR";   //Room Move All Guset
	public static String XL = "XL";   //Mess cua khach
	public static String XM = "XM";   //yeu cau mess tra ve cho eHotel
	public static String XT = "XT";   //
	public static String XD = "XD";   //xoa mess 
	public static String XU = "XU";   // Update mess
	public static String XO = "XO";   // Guest bill detail - online
	public static String XI = "XI";   // Guest bill item
	public static String SE = "SE";   // lenh nhan cac mon an tu PMS gui qua
	public static String HE = "HE";   // lenh nhan cac cac item HK tu PMS gui qua
	public static String HR = "HR";   // lenh nhan cac cac item HK tu
    public static String SS = "SS";   // tao chu de de chua mon an.
    public static String SD = "SD";   // tao chu de de chua mon an.
	//Field Description
	//public static Hashtable fielddes = new Hashtable();�
}
