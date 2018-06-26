package ehotel.admin.Monitor;

import java.net.URL;
import java.util.Vector;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import ehotel.admin.util.ConfigEod;
import ehotel.admin.util.ConfigLoader;

public class XmlRpc {
	public String getCurrentLog(String type){
		ConfigLoader loader=new ConfigLoader();
		ConfigEod config=loader.getConfigEod();
		String _config =config.serverURL;
		String result02="";
		try {
			XmlRpcClientConfigImpl configImpl = new XmlRpcClientConfigImpl();
			configImpl.setServerURL(new URL(_config));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(configImpl);
			 Vector<String> params = new Vector<String>();
			 params.add(type);
			  result02 = (String) client.execute(
				     "ehotel2Monitor.getLogCurrent", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result02;
	}
	public String getDateLog(String date){
		ConfigLoader loader=new ConfigLoader();
		ConfigEod config=loader.getConfigEod();
		String _config =config.serverURL;
		String result02="";
		try {
			XmlRpcClientConfigImpl configImpl = new XmlRpcClientConfigImpl();
			configImpl.setServerURL(new URL(_config));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(configImpl);
			 Vector<String> params = new Vector<String>();
			 params.add(date);
			  result02 = (String) client.execute(
				     "ehotel2Monitor.getLog", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result02;
	}

}
