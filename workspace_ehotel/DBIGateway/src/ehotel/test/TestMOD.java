package ehotel.test;

import java.util.Vector;

import ehotel.domain.mod.Mod;
import ehotel.domain.vod.Subject;
import ehotel.inter.AMDMod;
import ehotel.inter.IEAS;
import ehotel.render.DBIGateway;
import ehotel.render.MOD;

public class TestMOD {
	AMDMod cntMOD;
	AMDMod svcMOD;
	IEAS eas;

	public TestMOD() {
		MOD dbi = DBIGateway.getAMDMod();
		cntMOD = dbi.getAMDCntMod();
		svcMOD = dbi.getAMDSvcMod();
		eas = dbi.getEAS();
	}

	public void addSubjectTest() {
		Subject subj = new Subject();
		subj.setName("DEMO TEST");
		subj.setUrlImage("images/hoavk.png");
		subj.setUrlBg("images/hoavk.png");

		int seq = svcMOD.addSubject(subj, -1);
		System.out.println("addMod seq=" + seq);
	}

	public void editSubject() {
		Subject subj = new Subject();
		subj.setId(155);
		subj.setName("QUE HOA VK");
		subj.setUrlImage("images/hoavk.png");
		subj.setUrlBg("images/hoavk.png");
		svcMOD.editSubject(subj, 2);
	}

	public void removeSubject() {
		cntMOD.removeSubject(149);
	}

	public void getSubjectTest() {
		Vector<Subject> vSubject = svcMOD.getSubjects(2);
		for (Subject subj : vSubject) {
			System.out.println(subj.toString());
		}
	}

	public void removeEasMod(int modId) {
		System.out.println(eas.removeMod(modId));
	}

	public void addModTest() {
		Mod mod = new Mod();
		mod.setTitle("Come On Eileen");
		mod.setDuration("400");
		mod.setLyric("Come On Eileen");
		mod.setAlbum("Awesome 80s");
		mod.setSinger("Dexy's Midnight Runners");
		mod.setFileName("Music/80s/Come On Eileen (Dexy's Midnight Runners).mp3");
		System.out.println(eas.addMod(6, mod, 2));
	}

	public void getModInfo() {
		Mod aMod = svcMOD.getModInfo(269, 2);
		if (aMod != null)
			System.out.println(aMod.toString());
	}

	public void getModInfo_svc() {
		Mod aMod = cntMOD.getModInfo(3, 2);
		if (aMod != null)
			System.out.println(aMod.toString());
	}

	public void getMods() {
		Vector<Mod> vMods = cntMOD.getMods(6, 2, 10, 12);
		for (Mod emod : vMods) {
			System.out.println(emod.toString());
		}
	}

	public void searchMods() {
		Vector<Mod> vMods = cntMOD.searchMod(6, "Q", 2, 1, 5);
		for (Mod emod : vMods) {
			System.out.println(emod.toString());
		}
	}

	public void updateMod() {
		Mod amod = new Mod();
		amod.setId(1);
		amod.setTitle("hoa moc lan");
		System.out.println(cntMOD.updateMod(amod, 2));
	}

	public void setUrl() {
		eas.setUrl(53, "53.mp3");
	}

	public void addMod() {
		svcMOD.addMod(146, "(846)");
	}

	public void removeSvcMod() {
		svcMOD.removeMods(246, "(846)");
	}

	public void getSubjectsInMod_svc() {
		Vector<Subject> subjects = svcMOD.getSubjectsInMod(276, 2);
		System.out.println(subjects.size());
		for (Subject subj : subjects) {
			System.out.println(subj.toString());
		}
	}

	public void getSubjectsOutMod_svc() {
		Vector<Subject> subjects = svcMOD.getSubjectsOutMod(253, 2);
		System.out.println(subjects.size());
		for (Subject subj : subjects) {
			System.out.println(subj.toString());
		}
	}

	public void changeSubjectOfMod() {
		svcMOD.changeSubjectOfMod(248, "(246,267)");
	}

	public void updateMod_svc() {
		Mod amod = new Mod();
		amod.setId(245);
		amod.setTitle("Fame");
		amod.setLyric("hoa moc lan");
		svcMOD.updateMod(amod, 2);
	}

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		TestMOD test = new TestMOD();
		test.getModInfo();
		// test.removeSubject();
		// test.getSubjectTest();

		// test.addSubjectTest();

		// test.updateMod();

		// test.updateMod_svc();
		// test.addModTest();

	}
}
