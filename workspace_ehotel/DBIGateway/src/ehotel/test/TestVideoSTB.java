package ehotel.test;

import java.util.Vector;

import ehotel.domain.vod.Vod;
import ehotel.inter.IVideoSTB;
import ehotel.render.DBIGateway;

public class TestVideoSTB {

	IVideoSTB vdoSTB;

	public TestVideoSTB() {
		vdoSTB = DBIGateway.getAMDVideoSTB();
	}

	public void getVodsOfStb() {
		Vector<Vod> vVods = vdoSTB.getVodsOfStb("FA3610D0036840", 2, -1, -1);
		for (Vod avod : vVods) {
			System.out.println(avod.toString());
		}
	}

	public void getVodsOutStb() {
		Vector<Vod> vVods = vdoSTB.getVodsOutStb(-1, "FA3610D0036840", 2);
		for (Vod avod : vVods) {
			System.out.println(avod.toString());
		}
	}

	public void getVods_UnSynCompleted() {
		Vector<Vod> vVods = vdoSTB.getVods_UnSynCompleted("FA3610D0036840", 2);
		for (Vod avod : vVods) {
			System.out.println(avod.toString());
		}
	}

	public void searchVods() {
		Vector<Vod> vVods = vdoSTB.searchVodOnStb("2001", "e", 2);
		for (Vod avod : vVods) {
			System.out.println(avod.toString());
		}
	}

	public void setSynStatus() {
		vdoSTB.setSynStatus("2001", "62f07bfa-d7e9-46d0-877d-571a3b6395bd");
	}

	public void insertVodStb() {
		vdoSTB.insertSynVodSTB("2001", 1075, "1", "20202");
	}

	public void getIpStb() {
		System.out.println(vdoSTB.getIpStb("2003"));
	}

	public void getFilePathVod() {
		System.out.println(vdoSTB.getFilePath(959));
	}

	public static void main(String[] args) {
		TestVideoSTB stb = new TestVideoSTB();
		stb.getVodsOutStb();
		// getVodsOutStb();

		//

		stb.getVods_UnSynCompleted();
	}

}
