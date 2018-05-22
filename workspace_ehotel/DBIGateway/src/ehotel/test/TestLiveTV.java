package ehotel.test;

import java.util.Vector;

import ehotel.domain.liveTV.LiveTV;
import ehotel.domain.vod.Subject;
import ehotel.impl.AMDSvcLiveTVImp;
import ehotel.inter.AMDLiveTV;

public class TestLiveTV {
	private AMDLiveTV livetv;

	public TestLiveTV() {
		livetv = new AMDSvcLiveTVImp();
	}

	public void addSubject() {
		Subject subject = new Subject();
		subject.setName("Demo LiveTV123");
		subject.setUrlImage("LiveTV/demo.jpg");
		livetv.addSubject(subject, -1);
	}

	public void getSubjectInfo() {
		Subject subject = livetv.getSubjectInfo(48, 2);
		if (subject != null)
			System.out.println(subject.toString());
	}

	public void getSubjects() {
		Vector<Subject> vSubject = livetv.getSubjects(2);
		for (Subject g : vSubject) {
			System.out.println(g.toString());
		}
	}

	public void editSubject() {
		Subject subject = new Subject();
		subject.setId(54);
		subject.setName("Demo LiveTV1");
		subject.setUrlImage("LiveTV/demo.jpg");
		livetv.editSubject(subject, 2);
	}

	public void removeSubject() {
		livetv.removeSubject(1);
	}

	public void addChannel() {
		// livetv.addLiveTV(1, "(462,463,464,465)");
		livetv.addLiveTV(3, "(582,587,588)");
	}

	public void editChannel() {
		LiveTV tv = new LiveTV();
		tv.setChannelid(585);
		tv.setServicename("HBO (HDt)");
		tv.setUrlImage("LiveTV/tv.jpg");
		livetv.updateChannel(tv, 2);
	}

	public void changeSubject() {
		livetv.changeSubject(583, "(1,2)");
	}

	public void getChannelInfo() {
		LiveTV tv = livetv.getChannelInfo(585, 2);
		if (tv != null)
			System.out.println(tv.toString());
	}

	public void getChannels() {
		Vector<LiveTV> vLiveTV = livetv.getChannels(2, 2, 1, 10);
		for (LiveTV g : vLiveTV) {
			System.out.println(g.toString());
		}
	}

	public void getChannelContentOutSuject() {
		Vector<LiveTV> vLiveTV = livetv.getChannelContentOutSuject(1, 2);
		for (LiveTV g : vLiveTV) {
			System.out.println(g.toString());
		}
	}

	public void countChannel() {
		System.out.println(livetv.countChannels(1));
	}

	public void changeStatus() {
		livetv.changStatus(1, 583);
	}

	public void removeChannel() {
		livetv.removeChannel(1, "(584,585)");
	}

	public void searchChannel() {
		Vector<LiveTV> vLiveTV = livetv.searchChannel(3, "d", 1, 6);
		for (LiveTV g : vLiveTV) {
			System.out.println(g.toString());
		}
	}

	public void countSearchChannel() {
		System.out.println(livetv.countSearchChannel(3, "o"));
	}

	public static void main(String[] args) {
		TestLiveTV test = new TestLiveTV();
		// test.addSubject();
		test.getSubjects();
	}

}
