package elcom.abop.util;


public class UnicodeConverter {
	public static String decodeUnicode(String original) {
		String returnValue = HTMLParser.parseByFont(original);
		return HTMLParser.parseByTag(returnValue);
	}

	public static String encodeUnicode(String original) {
		// original = original.replaceAll("<p>", "<p style='margin-bottom: 5px; margin-top: 5px'>");
		return HTMLParser.formatToString(original);
	}
	
	public static void main(String[] args) {
		System.out.println(encodeUnicode("Công Ty Cổ Phần Đầu Tư Phát Triển Công Nghệ Điện Tử Viễn Thông"));
	}
}
