package elcom.com.apiconnect;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import com.elcom.DBI.DBI;



public class DBIManager {
	private String url;
	DBI dbiCore;

	public DBIManager(String url){
		this.url=url;
	}


	public DBI getConn(){
		return (DBI)mkConnectDBI();

	}


	private synchronized java.rmi.Remote mkConnectDBI(){
		return lookup();
	}

	 private DBI lookup() {
		    try {
		      // Clients requests service object reference

		      dbiCore = (DBI) Naming.lookup(url);
		    }
		    catch (NotBoundException ex) {
		      System.out.println("Url is not currently bound");
		      ex.printStackTrace();
		    }
		    catch (java.net.MalformedURLException ex) {
		      System.out.println(" Name is not an appropriately formatted URL");
		      ex.printStackTrace();
		    }
		    catch (RemoteException ex) {
		      System.out.println("registry could not be contacted");
		      ex.printStackTrace();
		    }

		    return (dbiCore);
	 }
}
