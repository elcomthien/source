package ehotel.test;

public class TestJava {
	public static void main(String[] args) {
//		String a = "Mem:          3878       3831         46          0        251       1729";
//		while (a.indexOf("  ") >= 0){
//			a = a.replaceAll("  ", " ");
//		}
//		a = a.substring(1);
//		a = a.replace("%", "");
//		System.out.println(a);
//		String[] arr = a.split(" ");
//		for(int i = 0; i< arr.length; i++){
//			System.out.println(arr[i]);
//		}
//		
//		int c = Math.round((3831*100)/3878);
//		System.out.println(c);
		
//		String info = "Mem:          3878       3831         46          0        251       1729";
//		while (info.indexOf("  ") >= 0){
//			info = info.replaceAll("  ", " ");
//		}
//		String[] arr = info.split(" ");
//		long total = Long.parseLong(arr[1]);
//		long used = Long.parseLong(arr[2]);
//		long free = Long.parseLong(arr[3]);
//		int usedper = Math.round((used*100)/total);
//		int freeper = 100 - usedper;
//		String data = "";
//		data += "<totalram>" + total + "</totalram>";
//		data += "<usedram>" + used + "</usedram>";
//		data += "<freeram>" + free + "</freeram>";
//		data += "<usedperram>" + usedper + "</usedperram>";
//		data += "<freeperram>" + freeper + "</freeperram>";
//		System.out.println(data);
		
		String a = "asdhaskhd.mkv";
		String b = a.substring(a.lastIndexOf("."));
		System.out.println(b);
	}
}
