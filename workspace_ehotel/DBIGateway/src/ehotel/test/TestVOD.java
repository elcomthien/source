package ehotel.test;

import java.util.Vector;

import ehotel.abs.pms.ServiceSystemPMS;
import ehotel.domain.pms.eService;
import ehotel.domain.vod.SubTitle;
import ehotel.domain.vod.Subject;
import ehotel.domain.vod.Vod;
import ehotel.inter.AMDVod;
import ehotel.inter.IEVS;
import ehotel.render.DBIGateway;
import ehotel.render.VOD;

public class TestVOD {
	VOD vodDBI;
	AMDVod amdVod;
	AMDVod svcVod;
	IEVS evs;
	ServiceSystemPMS main;

	public TestVOD() {
		VOD vodDBI = DBIGateway.getAMDVod();
		amdVod = vodDBI.getAMDCntVod();
		svcVod = vodDBI.getAMDSvcVod();
		evs = vodDBI.getEVSVod();
		main = new ServiceSystemPMS();
	}

	public void getAllservices() {
		Vector<eService> vservices = main.getAllServices(2);
		for (eService s : vservices) {
			System.out.println(s.toString());
		}
	}

	public void orderByService() {
		main.orderByService("(1,2,3,4)", "1,2,3,100)");
	}

	public void updateMain() {
		main.updateServiceMain(1, "HOTEL INFO", "Home/hotelinfo1.png", 2);
	}

	public void addSubject() {
		amdVod.addSubject("Full HD 720 & 1080", "video/menu/item_hd.png", -1);
		svcVod.addSubject("ACTION03", "images/hoavk.png", -1);
	}

	public void editSubject() {
		amdVod.editSubject(51, "ACTION PPP1", "images/hoavk.png", 1);
		svcVod.editSubject(24, "ACTION 02", "images/hoavk.png", 1);
	}

	public void removeSubject_svc() {
		svcVod.removeSubject(50);
	}

	public void getSubjectInfo() {
		Subject subj = amdVod.getSubjectInfo(23, 2);
		if (subj != null)
			System.out.println(subj.toString());
		subj = svcVod.getSubjectInfo(24, 2);
		if (subj != null)
			System.out.println(subj.toString());
	}

	public void getSubjects() {
		Vector<Subject> vSubject = amdVod.getSubjects(2);
		for (Subject subji : vSubject) {
			System.out.println(subji.toString());
		}

		vSubject = svcVod.getSubjects(3);
		for (Subject subji : vSubject) {
			System.out.println(subji.toString());
		}
	}

	public void getVodInfo() {
		Vod aVod = amdVod.getVodInfo(-1, 9, 2);
		if (aVod != null)
			System.out.println(aVod.toString());
	}

	public void getVodInfo_svc() {
		Vod aVod = svcVod.getVodInfo(-1, 9, 2);
		if (aVod != null)
			System.out.println(aVod.toString());
	}

	public void changeSubjectOfVod() {
		amdVod.changeSubjectOfVod(9, 31);
	}

	public void changeSubjectOfVod_svc() {
		svcVod.changeSubjectOfSvcVod(9, "(31,32)");
	}

	public void getVods() {
		Vector<Vod> vVods = svcVod.getVods(31, 2, 1, 5);
		for (Vod avod : vVods) {
			System.out.println(avod.toString());
		}
	}

	public void getVods_svc() {
		Vector<Vod> vVods = svcVod.getVods(224, 2, 1, 10);
		for (Vod avod : vVods) {
			System.out.println(avod.toString());
		}
	}

	public void addVod_svc() {
		svcVod.addVod(224, "(222,235,225)");
	}

	public void updateVod_svc() {
		Vod vod = new Vod();
		vod.setId(221);
		vod.setTitle("The Ghost Breakers");
		vod.setActors("Bob Hope, Paulette Goddard, Richard Carlson, Paul Lukas.");
		vod.setDirector("George Marshal");
		vod.setPlot("A radio broadcaster, his quaking manservant and an heiress investigate the mystery of a haunted castle in Cuba.");
		svcVod.updateVod(vod, 2);
	}

	public void updateVod_ctn() {
		Vod vod = new Vod();
		vod.setId(221);
		vod.setTitle("The Ghost Breakers");
		vod.setActors("Bob Hope, Paulette Goddard, Richard Carlson, Paul Lukas.");
		vod.setDirector("George Marshal");
		vod.setPlot("A radio broadcaster, his quaking manservant and an heiress investigate the mystery of a haunted castle in Cuba.");
		vod.setCurrency("5.0");
		vod.setIUnit("USD");
		vod.setPoster("10203044.png");
		amdVod.updateVod(vod, 2);
	}

	public void countVodInSubject() {
		System.out.println(amdVod.countVodOfSubject(31));
	}

	public void getSubtitles() {
		Vector<SubTitle> v = amdVod.getSubtiles(9);
		for (SubTitle a : v) {
			System.out.println(a.toString());
		}
	}

	public void getvascVods() {
		Vector<Vod> vVods = svcVod.getVascVods(223, 263, 2, -1, -1);
		for (Vod avod : vVods) {
			System.out.println(avod.toString());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestVOD test = new TestVOD();
		// test.getSubjects();
		// test.countVodInSubject();
		// test.changeSubjectOfVod();
		test.getAllservices();

		// test.updateMain();
		// test.getVods_svc();
		// test.changeSubjectOfVod_svc();
	}
}
