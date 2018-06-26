package ehotel.admin.util;

public class TransferImageSokhaSR {
	private final static String pathbalance01 = "/core/ehotel01/service/app/Loadbalancer/service01/webapps/ePMS2.0/elcom/resources/image";
	private final static String pathbalance02 = "/core/ehotel01/service/app/Loadbalancer/service02/webapps/ePMS2.0/elcom/resources/image";
	private final static String pathbalance03 = "/core/ehotel01/service/app/Loadbalancer/service03/webapps/ePMS2.0/elcom/resources/image";
	
	
	public void transferImageBalance01(String filepath, String folder) {
		System.out.println("server 03 - balance 01");
		String filename = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
		ManagerFile file = new ManagerFile();
		file.copy(filepath, pathbalance01 + folder + "/" + filename);
	}

	public void transferImageBalance02(String filepath, String folder) {
		System.out.println("server 03 - balance 02");
		String filename = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
		ManagerFile file = new ManagerFile();
		file.copy(filepath, pathbalance02 + folder + "/" + filename);
	}

	public void transferImageBalance03(String filepath, String folder) {
		System.out.println("server 03 - balance 03");
		String filename = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
		ManagerFile file = new ManagerFile();
		file.copy(filepath, pathbalance03 + folder + "/" + filename);
	}
	
	public void transferImageBalance(String filepath, String folder){
		System.out.println("Transfer image load balance");
		System.out.println("File path: " + filepath);
		System.out.println("Folder: " + folder);
		transferImageBalance01(filepath, folder);
		transferImageBalance02(filepath, folder);
		transferImageBalance03(filepath, folder);
	}
}
