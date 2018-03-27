package com.elcom.eodapp.ehotel.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.elcom.ehotel.variables.Common;

public class Analysis {
	public static String regex = Common.PMSRegex;

	public Analysis() {
	}

	public String getField(String field) {
		String result = "";
		result = field.substring(0, 2);
		return result;
	}

	public String getFieldValue(String field) {
		String result;
		result = field.substring(2, field.length());
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List tokenize(String input) {
		String token = "";
		input = input.trim();
		ArrayList tokens = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(input, regex);
		while (tokenizer.hasMoreTokens()) {
			token = tokenizer.nextToken();
			tokens.add(token);
		}
		return tokens;
	}

	@SuppressWarnings("rawtypes")
	public CommandProfile pareCommandProfile(String commands) {
		CommandProfile cp = new CommandProfile();
		List com = tokenize(commands);
		String cmdType, field, valuefield;

		cmdType = (String) com.get(0);
		cp.setCommand(cmdType);
		for (int i = 1; i < com.size(); i++) {
			String element = (String) com.get(i);
			if (element.trim().length() == 0)
				continue;
			field = getField(element);
			valuefield = getFieldValue(element);
			cp.getContent().put(field, valuefield);
		}
		return cp;
	}

	public static void main(String[] arg) {
		// GC|R#1234|G#8874|GA110327|GD110329|GLVN|GTMr.|GFHuu|GNLam Nguyen Minh|GP9876|
		// String commands = "XI|RN#307|G#100006|TC400|BDRoom Charge|DA150304|BI5555555|";
		String commands = "GI|RN2781|G#12345|GNGuest, Mr.|GLEA|GV3|GGA123|GSN|";
		Analysis a = new Analysis();
		CommandProfile gp = a.pareCommandProfile(commands);
		System.out.println(gp.getCommand());
		// System.out.println(gp.getCommand().equals(CMD_GUESTDATA.GUEST_CHECK_IN));
	}
}
