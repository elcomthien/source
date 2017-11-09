package com.elcom.ehotel.admin.util;

import java.util.Vector;

import com.elcom.DBI.SubProParam;

public class UnicodeConverter {
	public static String decodeUnicode(String original) {
		String returnValue = HTMLParser.parseByFont(original);
		return HTMLParser.parseByTag(returnValue);
	}

	public static String encodeUnicode(String original) {
		// original = original.replaceAll("<p>", "<p style='margin-bottom: 5px; margin-top: 5px'>");
		return HTMLParser.formatToString(original);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Vector changeFontForDBI(Vector params) {
		if (params != null) {
			int i = 0, count = params.size();
			while (i < count) {
				Object item = params.get(i);
				if (item instanceof SubProParam) {
					SubProParam param = (SubProParam) item;
					if (param != null && param.getType() == SubProParam.IN) {
						String value = param.getString();
						if (value != null) {
							// TODO do not remove this command
							value = value.trim();
							// System.out.println("param " + (i + 1) + " before encode:" + value);
							value = encodeUnicode(value);
							// log.info("\t param " + (i + 1) + " after encode:" + value);
							param = new SubProParam(value, SubProParam.IN);
							params.set(i, param);
						}
					}
				} else if (item instanceof String) {
					String value = (String) item;
					if (value != null) {
						// TODO do not remove this command
						value = value.trim();
						// System.out.println("param " + (i + 1) + " before encode:" + value);
						value = UnicodeConverter.encodeUnicode(value);
						// log.info("\t param " + (i + 1) + " after encode:" + value);
						params.set(i, value);
					}
				}
				i++;
			}
		}
		return params;
	}
}
