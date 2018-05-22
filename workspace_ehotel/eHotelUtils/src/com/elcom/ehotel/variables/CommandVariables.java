package com.elcom.ehotel.variables;

public class CommandVariables {

	public interface ICommand {
	}

	public enum CMD_LINKCTRL implements ICommand {
		/*
		 * LINK_START {@Override public String toString() { return "LS";}}, LINK_ALIVE {@Override public String toString() { return "LA";}},
		 * LINK_DESC {@Override public String toString() { return "LD";}}, LINK_RECORD {@Override public String toString() { return "LR";}},
		 * LINK_END {@Override public String toString() { return "LE";}},
		 */
		LINK_START("LS"), LINK_ALIVE("LA"), LINK_DESC("LD"), LINK_RECORD("LR"), LINK_END("LE");
		public String value;

		private CMD_LINKCTRL(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	};

	public enum CMD_GUESTDATA implements ICommand {
		/*
		 * GUEST_CHECK_IN {@Override public String toString() { return "GI";}}, GUEST_CHECK_OUT {@Override public String toString() { return
		 * "GO";}}, GUEST_CHANGE {@Override public String toString() { return "GC";}}, VIRTUAL_NUMBER {@Override public String toString() {
		 * return "VA";}},
		 */
		GUEST_CHECK_IN("GI"), GUEST_CHECK_OUT("GO"), GUEST_CHANGE("GC"), VIRTUAL_NUMBER("VA");
		public String value;

		private CMD_GUESTDATA(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	};

	public enum CMD_GUESTDATA_EXT implements ICommand {
		/*
		 * GUEST_MSG_TXTONL {@Override public String toString() { return "XL";}}, GUEST_MSG_REQ {@Override public String toString() { return
		 * "XM";}}, GUEST_MSG_TXTANDOTH {@Override public String toString() { return "XT";}}, GUEST_MSG_DEL {@Override public String
		 * toString() { return "XD";}}, REMOTE_CHECK_OUT {@Override public String toString() { return "XC";}},
		 */
		GUEST_MSG_TXTONL("XL"), GUEST_MSG_REQ("XM"), GUEST_MSG_TXTANDOTH("XT"), GUEST_MSG_DEL("XD"), REMOTE_CHECK_OUT("XC");
		public String value;

		private CMD_GUESTDATA_EXT(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	};

	public enum CMD_GUESTBILL implements ICommand {
		/*
		 * GUEST_BILL_REQ {@Override public String toString() { return "XR";}}, GUEST_BILL_ITEM {@Override public String toString() { return
		 * "XI";}}, GUEST_BILL_BALANCE {@Override public String toString() { return "XB";}},
		 */
		GUEST_BILL_REQ("XR"), GUEST_BILL_ITEM("XI"), GUEST_BILL_BALANCE("XB");
		public String value;

		private CMD_GUESTBILL(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

	};

	public enum CMD_ROOMDATA implements ICommand {
		/*
		 * ROOM_EQUIP_STS {@Override public String toString() { return "RE";}}, ROOM_ASSIGN_EQUIP {@Override public String toString() {
		 * return "XI";}},
		 */
		ROOM_EQUIP_STS("RE"), ROOM_ASSIGN_EQUIP("RA");
		public String value;

		private CMD_ROOMDATA(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	};

	public enum CMD_POSTING implements ICommand {
		/*
		 * POSTING_SIMPLE {@Override public String toString() { return "PS";}}, POSTING_REQ {@Override public String toString() { return
		 * "PR";}}, POSTING_LIST {@Override public String toString() { return "PL";}}, POSTING_ANSWER {@Override public String toString() {
		 * return "PA";}},
		 */
		POSTING_SIMPLE("PS"), POSTING_REQ("PR"), POSTING_LIST("PL"), POSTING_ANSWER("PA");
		public String value;

		private CMD_POSTING(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	};

	public enum CMD_DBSYNCH implements ICommand {
		/*
		 * POSTING_SIMPLE {@Override public String toString() { return "PS";}}, POSTING_REQ {@Override public String toString() { return
		 * "PR";}}, POSTING_LIST {@Override public String toString() { return "PL";}}, POSTING_ANSWER {@Override public String toString() {
		 * return "PA";}},
		 */
		DB_SYNCH_REQ("DR"), DB_SYNCH_START("DS"), DB_SYNCH_END("DE");
		public String value;

		private CMD_DBSYNCH(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	};

	public enum CMD_BOOKING implements ICommand {
		DUE_IN("RICI"), CANCEL("RICA");
		public String value;

		private CMD_BOOKING(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	};

	public static class CommandUtils {
		public static boolean compare(ICommand cmd, String s) {
			return cmd.toString().equals(s);
		}
	}
}
