package com.elcom.ehotel.smile;

import com.elcom.ehotel.variables.CommandVariables.ICommand;

public class CommandVariables {

	public interface ICommand {
	}

	public enum CMD_GUESTDATA implements ICommand {
		GUEST_CHECK_IN("GI"), GUEST_CHECK_OUT("GO"), GUEST_CHANGE("GC");
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
		ROOM_MOVE_GUEST("RG"), ROOM_MOVE_ALL("RR");
		public String value;

		private CMD_GUESTDATA_EXT(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	};

	public enum CMD_GUESTMESSAGE implements ICommand {
		GUEST_MESSAGE_ONLINE("XL"), GUEST_MESSAGE_DELETE("XD");
		public String value;

		private CMD_GUESTMESSAGE(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	};

	public enum CMD_GUESTBILL implements ICommand {
		GUEST_BILL_REQ("XR"), GUEST_BILL_ITEM("XI"), GUEST_BILL_BALANCE("XB"), GUEST_BILL_ONLINE("XO");
		public String value;

		private CMD_GUESTBILL(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	};

	public enum CMD_POSTING implements ICommand {
		POSTING_SIMPLE("PS");
		public String value;

		private CMD_POSTING(String value) {
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
