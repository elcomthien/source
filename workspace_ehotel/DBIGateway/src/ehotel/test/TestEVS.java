package ehotel.test;

import java.util.Vector;

import ehotel.domain.vod.EVSStorage;
import ehotel.domain.vod.SubTitle;
import ehotel.domain.vod.Trailer;
import ehotel.domain.vod.Vod;
import ehotel.inter.IEVS;
import ehotel.render.DBIGateway;
import ehotel.render.VOD;

public class TestEVS {
	IEVS evs;

	public TestEVS() {
		VOD vodDBI = DBIGateway.getAMDVod();
		evs = vodDBI.getEVSVod();
	}

	public int addVodTest() {
		Vod aVod = new Vod();
		aVod.setTitle("Ice.Age.A.Mammoth.Christmasdemo");
		aVod.setActors("Huuln");
		aVod.setDirector("Huuln");
		aVod.setIUnit("VND");
		aVod.setCurrency("2.5");
		aVod.setPoster("Film/101001100.png");
		aVod.setPlot("Metadata film");
		aVod.setDuration("500");
		aVod.setFilePath("hoavk");
		int seq = evs.addVod(49, 1, "rpc://192.168.0.150", aVod);// url tam
		return seq;
	}

	public Integer[] addSubtitleTest() {
		Vector<SubTitle> vSubTitles = new Vector<SubTitle>();
		SubTitle title = new SubTitle("rpc//192.168.0.150/hehe.ts", 2);// url
																		// tam
		vSubTitles.add(title);
		title = new SubTitle("rpc//192.168.0.150/hehe1.ts", 1);
		vSubTitles.add(title);
		Integer[] seq = evs.addSubtitle(9, vSubTitles);
		return seq;
	}

	public int addTrailerTest() {
		Trailer trailer = new Trailer("rpc//192.168.0.150/hehe1.ts", 300);
		int seq = evs.addTrailer(7, trailer);
		return seq;
	}

	public void getEVSStoragesTest() {
		Vector<EVSStorage> vEVS = evs.getEVSStorages(83);
		for (EVSStorage aevs : vEVS) {
			System.out.println(aevs.toString());
		}
	}

	public void getUrl() {
		System.out.println(evs.getUrl(134, VOD.VOD));
	}

	public void getIDSubtitles() {
		int[] id = evs.getIDSubtitle(9);
		System.out.println(id[0] + "," + id[1]);
	}

	public static void main(String[] args) {
		TestEVS test = new TestEVS();
		test.getUrl();
		test.addVodTest();
		// Integer[] aa = test.addSubtitleTest();
		// System.out.println(aa[0] + " and " + aa[1]);

		// test.getUrlTrailer();
		// test.getIDSubtitles();
	}
}
