package ehotel.test;

import java.util.Vector;

import ehotel.abs.pms.ExchangeRatePMS;
import ehotel.domain.pms.eExchangeRate;

public class TestExchangeRate {
	ExchangeRatePMS ehotel;

	public TestExchangeRate() {
		ehotel = new ExchangeRatePMS();
	}

	public void getExchangeRateInfo() {
		eExchangeRate rate = ehotel.getExchangeRateInfo(1);
		if (rate != null)
			System.out.println(rate.toString());
	}

	public void getExchangeRate() {
		Vector<eExchangeRate> rate = ehotel.getExchangeRates(1, 10);
		for (eExchangeRate aimage : rate) {
			System.out.println(aimage.toString());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestExchangeRate exchange = new TestExchangeRate();
		exchange.getExchangeRate();
	}
}
