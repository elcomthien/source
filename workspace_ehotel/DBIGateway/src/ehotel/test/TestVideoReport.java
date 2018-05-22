package ehotel.test;

import java.util.Vector;

import ehotel.abs.report.VideoReport;
import ehotel.domain.report.DataReport;

public class TestVideoReport {
	VideoReport report;

	public TestVideoReport() {
		report = new VideoReport();
	}

	public void getUsedFrequency_monthly() {
		Vector<DataReport> vData = report.getUsedFrequency_monthly(2011);
		for (DataReport r : vData) {
			System.out.println(r.toString());
		}
	}

	public void getUsedFrequency_name() {
		Vector<DataReport> vData = report.getUsedFrequency_name("01/02/2011",
				"30/12/2011", 2, -1, -1);
		for (DataReport r : vData) {
			System.out.println(r.toString());
		}
	}

	public void getUsedFrequency_gener() {
		Vector<DataReport> vData = report.getUsedFrequency_gener("01/02/2011",
				"30/12/2011", 2);
		for (DataReport r : vData) {
			System.out.println(r.toString());
		}
	}

	public static void main(String[] args) {
		TestVideoReport test = new TestVideoReport();
		test.getUsedFrequency_gener();
	}

}
