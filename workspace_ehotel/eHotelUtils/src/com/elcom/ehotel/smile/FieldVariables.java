package com.elcom.ehotel.smile;

public class FieldVariables {

	public interface IField {
	}

	public enum FID implements IField {
		DATE("DA"), TIME("TI"), GUEST_NUMBER("G#"), GUEST_ARV_DATE("GA"), GUEST_DEPT_DATE("GD"), GUEST_FIRST_NAME("GF"), GUEST_GROUP_NUMBER(
				"GG"), GUEST_LANGUAGE("GL"), GUEST_NAME("GN"), GUEST_PIN("GP"), SHARE_FLAG("GS"), GUEST_TITLE("GT"), ROOM_NUMBER("RN"), RESERVATION_NUMBER(
				"R#"), GUEST_SEX("GE"), // 0:Male, 1:Female
		OLD_ROOM_NUMBER("RO"), ITEM_DESCRIPTION("BD"), ITEM_AMOUNT("BI"), BALANCE_AMOUNT("BA"), TRANSACTION_ID("T#"), TRANSACTION_CODE("TC"), POST_TEXT(
				"PT"), TOTAL_POST_AMOUNT("TA"), CURRENCY("CU"), POST_ID("P#"), MESSAGE_ID("MI"), MESSAGE_TEXT("MT");
		public String value;

		private FID(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	public static class FieldUtils {
		public static boolean compare(IField fd, String s) {
			return fd.toString().equals(s);
		}
	}
}
