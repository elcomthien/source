package com.elcom.ehotel.esmile.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;

public class FileUtil {
	public static void writeFileJson(String path, HashMap<String, Object> map) {
		ObjectMapper mapper = new ObjectMapper();
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(path), map);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
