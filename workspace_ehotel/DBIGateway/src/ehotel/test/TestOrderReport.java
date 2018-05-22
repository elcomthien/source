package ehotel.test;

import java.util.Vector;

import ehotel.abs.report.OrderReport;
import ehotel.domain.report.DataReport;

public class TestOrderReport {
	OrderReport order;

	public TestOrderReport() {
		order = new OrderReport();
	}

	public void getRoomserviceRpt() {
		Vector<DataReport> vOrder = order.getRoomserviceRpt("01/01/2011",
				"31/12/2011", 2);
		for (DataReport data : vOrder) {
			System.out.println(data.toString());
		}
	}

	public void getTransportationRpt() {
		Vector<DataReport> vOrder = order.getTransportationRpt("01/01/2011",
				"31/12/2012", 2);
		for (DataReport data : vOrder) {
			System.out.println(data.toString());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestOrderReport test = new TestOrderReport();
		test.getTransportationRpt();

	}

}
