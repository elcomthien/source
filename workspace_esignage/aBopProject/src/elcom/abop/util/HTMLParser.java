package elcom.abop.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLParser {
//private static Logger log = Logger.getLogger(HTMLParser.class);
	
	private static final String fontRegular = "\\&\\#([0-9]{1,6})\\;";
	private static final String tagRegular = "\\&([a-z]{1,6})\\;";
	private static final String htmlRegular = "\\<.*?\\>";
    private Pattern pattern;
    private Matcher matcher;

    private HTMLParser(String regularExpression) {
        this.pattern = Pattern.compile(regularExpression);
    }

    private String group(int i) {
        return matcher.group(i);
    }

    private String replacementFont() {
		return Character.toString((char) Integer.parseInt(group(1), 10));
	}
    
    private String replacementTag() {
    	String c = group(1);
    	if ("quot".equals(c)){
			return "\"";
    	} else if ("amp".equals(c)){ 
			return "&";
    	} else if ("lt".equals(c)){ 
			return "<";
    	} else if ("gt".equals(c)){ 
			return ">";
    	} 
    	
    	return "";
    }

    private StringBuffer rewriteByFont(CharSequence original, StringBuffer destination) {
        this.matcher = pattern.matcher(original);
        while (matcher.find()) {
            matcher.appendReplacement(destination, "");
            destination.append(replacementFont());
        }
        matcher.appendTail(destination);
        return destination;
    }
    
    private StringBuffer rewriteByTag(CharSequence original, StringBuffer destination) {
        this.matcher = pattern.matcher(original);
        while (matcher.find()) {
            matcher.appendReplacement(destination, "");
            destination.append(replacementTag());
        }
        matcher.appendTail(destination);
        return destination;
    }
    
    private StringBuffer format(CharSequence original, StringBuffer destination) {
        this.matcher = pattern.matcher(original);
        while (matcher.find()) {
            matcher.appendReplacement(destination, "");
            destination.append(formatTag(matcher.group()));
        }
        matcher.appendTail(destination);
        return destination;
    }
    
    private String formatTag(String string) {
    	StringBuffer sb = new StringBuffer(string.length());
    	
		int len = string.length();
		char c;
		for (int i = 0; i < len; i++) {
			c = string.charAt(i);
			if (c == '<') {
				sb.append("&lt;");
				//sb.append("&#60;");
			} else if(c == '>') {
				sb.append("&gt;");
				//sb.append("&#62;");
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
    }
    
    /**
     * convert into unicode font.
     * @param original
     * @return
     */
    public static String parseByFont(CharSequence original){
    	if(original == null){
    		return null;
    	}
    	HTMLParser parser = new HTMLParser(fontRegular);
    	return parser.rewriteByFont(original, new StringBuffer(original.length())).toString();
    }
    
    /**
     * parse special character in html text.
     * @param original
     * @return
     */
    public static String parseByTag(CharSequence original){
    	if(original == null){
    		return null;
    	}
    	HTMLParser parser = new HTMLParser(tagRegular);
    	return parser.rewriteByTag(original, new StringBuffer(original.length())).toString();
    }
    
    /**
     * format html string to none html string by removing tags.
     * @param htmlString
     * @return
     */
    public static String formatToString(CharSequence htmlString){
    	HTMLParser parser = new HTMLParser(htmlRegular);
    	String original = parser.formatFont(htmlString);
    	if(original == null){
    		return null;
    	}
    	return parser.format(original, new StringBuffer(original.length())).toString();
    }
    
    private String formatFont(CharSequence string) {
    	if(string == null){
    		return null;
    	}
		StringBuffer sb = new StringBuffer(string.length());

		boolean lastWasBlankChar = false;
		int len = string.length();
		char c;

		for (int i = 0; i < len; i++) {
			c = string.charAt(i);
			if (c == ' ') {
				if (lastWasBlankChar) {
					lastWasBlankChar = false;
					//sb.append("&nbsp;");
					sb.append("&#160;");
				} else {
					lastWasBlankChar = true;
					sb.append(' ');
				}
			} else {
				lastWasBlankChar = false;
				//
				// HTML Special Chars, except '<' and '>'.
				if (c == '"')
					sb.append("&quot;");
					//sb.append("&#34;");
				else if (c == '&')
					sb.append("&amp;");
					//sb.append("&#38;");
				else if (c == '\n') {
					//sb.append("&lt;br/&gt;");
					//sb.append("<br />");
//					log.info("HTMLParser.formatFont(\n --> <br />)");
				} else {
					int ci = 0xffff & c;
					if (ci < 160)
						sb.append(c);
					else {
						sb.append("&#");
						sb.append(new Integer(ci).toString());
						sb.append(';');
					}
				}
			}
		}
		return sb.toString();
	}
    
    public static String encodeUnicode(String string) {
		StringBuffer sb = new StringBuffer(string.length());

		boolean lastWasBlankChar = false;
		int len = string.length();
		char c;

		for (int i = 0; i < len; i++) {
			c = string.charAt(i);
			if (c == ' ') {
				if (lastWasBlankChar) {
					lastWasBlankChar = false;
					sb.append("&nbsp;");
				} else {
					lastWasBlankChar = true;
					sb.append(' ');
				}
			} else {
				lastWasBlankChar = false;
				//
				// HTML Special Chars
				if (c == '"')
					sb.append("&quot;");
				else if (c == '&')
					sb.append("&amp;");
				else if (c == '<')
					sb.append("&lt;");
				else if (c == '>')
					sb.append("&gt;");
				else if (c == '\n')
					sb.append("&lt;br/&gt;");
				else {
					int ci = 0xffff & c;
					if (ci < 160)
						sb.append(c);
					else {
						sb.append("&#");
						sb.append(new Integer(ci).toString());
						sb.append(';');
					}
				}
			}
		}
		return sb.toString();
	}
    
    public static String replaceNewLineForTextarea(Object text){ 
		if(text != null){
    		return ((String)text).replaceAll("&lt;br /&gt;", "\n");
    	}
    	return "";
	}
}
