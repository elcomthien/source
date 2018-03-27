package com.elcom.eodapp.ehotel.utils;

import java.util.LinkedHashMap;
import java.util.Set;

import com.elcom.ehotel.smile.FieldVariables.FID;
import com.elcom.ehotel.smile.FieldVariables.IField;

public class CommandProfile {
	private String command = "";
	private LinkedHashMap<String, String> content = new LinkedHashMap<String, String>();

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public LinkedHashMap<String, String> getContent() {
		return content;
	}

	public String getFieldValue(FID key) {
		if (!content.containsKey(key.value))
			return "";
		return content.get(key.toString());
	}

	public void addFieldValue(IField key, String value) {
		content.put(key.toString(), value);
	}

	public String CheckField(FID[] listField) {
		String result = "";
		for (FID field : listField) {
			if (getFieldValue(field) == "")
				result += field.value + ",";
		}
		if (result.length() > 0) {
			int endIndex = result.lastIndexOf(",");
			return result.substring(0, endIndex);
		} else
			return result;
	}

	public String toString() {
		String cmd = "";
		Set<String> listKey = content.keySet();
		for (String key : listKey) {
			cmd += key + content.get(key) + "|";
		}
		return cmd;
	}

	public String toCommandString() {
		String regex = Analysis.regex;
		String cmd = command + regex;

		Set<String> listKey = content.keySet();
		for (String key : listKey) {
			String value = content.get(key);
			if (value != "")
				cmd += key + value + regex;
		}
		return cmd;
	}
}
