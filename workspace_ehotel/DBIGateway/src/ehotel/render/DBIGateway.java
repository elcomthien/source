package ehotel.render;

import ehotel.impl.AMDLogin;
import ehotel.impl.AMDMenuImp;
import ehotel.impl.AMDUserImpl;
import ehotel.impl.AMDVideoSTBImp;
import ehotel.impl.AnalysiTVideoImp;
import ehotel.inter.AMDMenu;
import ehotel.inter.ILOGIN;
import ehotel.inter.ITVideoAnalysis;
import ehotel.inter.IUser;
import ehotel.inter.IVideoSTB;

public class DBIGateway {

	public static MOD getAMDMod() {
		return new MOD();
	}

	public static VOD getAMDVod() {
		return new VOD();
	}

	public static AMDMenu getAMDMenu() {
		return new AMDMenuImp();
	}

	public static LiveTV getAMDLiveTV() {
		return new LiveTV();
	}

	public static Monitor getAMDMonitor() {
		return new Monitor();
	}

	public static IUser getIUser() {
		return new AMDUserImpl();
	}

	public static ITVideoAnalysis getITVideoAnalysis() {
		return new AnalysiTVideoImp();
	}

	public static ILOGIN getILogin() {
		return new AMDLogin();
	}

	public static IVideoSTB getAMDVideoSTB() {
		return new AMDVideoSTBImp();
	}

}
