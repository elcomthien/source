package ehotel.test;

import java.util.Vector;

import ehotel.abs.pms.PromotionPMS;
import ehotel.domain.pms.ePromotion;

public class TestPromotion {
	PromotionPMS ehotel = null;

	public TestPromotion() {
		ehotel = new PromotionPMS();

	}

	public void getPromotions() {
		Vector<ePromotion> promotions = ehotel.getPromotions(2, 1, 10);
		for (ePromotion aimage : promotions) {
			System.out.println(aimage.toString());
		}
	}

	public void getPromotionInfo() {
		ePromotion pro = ehotel.getPromotionInfo(1, 2);
		if (pro != null)
			System.out.println(pro.toString());
	}

	public void addPromotion() {
		ePromotion promotion = new ePromotion();
		promotion.setName("Demo promotion");
		promotion.setContent("Demo promtion test");
		ehotel.addPromotion(promotion);
	}

	public void editPromotion() {
		ePromotion pro = new ePromotion();
		pro.setId(282);
		pro.setName("Demo promotion");
		pro.setContent("Demo promotions");
		ehotel.editPromotion(pro, 2);
	}

	public void removePromotion() {
		ehotel.removePromotion("(321)");
	}

	public void countItem() {
		ehotel.countItem();
	}

	public static void main(String[] args) {
		TestPromotion test = new TestPromotion();

		test.removePromotion();
		test.getPromotions();

	}
}
