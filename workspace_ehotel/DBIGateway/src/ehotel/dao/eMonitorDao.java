package ehotel.dao;

import java.util.Vector;

import org.apache.log4j.Logger;

import com.elcom.DBI.SubProParam;

import ehotel.domain.vod.EServer;
import ehotel.sql.eVODSql;
import ehotel.utils.DataUtils;

public class eMonitorDao extends eBaseDao {
	private static Logger log = Logger.getLogger(eMonitorDao.class);

	public boolean addServer(EServer server) {
		boolean isAdd = false;
		if (server != null) {
			if (server.getIp() != null) {
				Vector<SubProParam> params = new Vector<SubProParam>();
				SubProParam subPro = new SubProParam(server.getIp(),
						SubProParam.IN);
				params.add(subPro);

				String port = server.getSrvmonitor_port();
				if (port == null)
					port = "";
				subPro = new SubProParam(port, SubProParam.IN);
				params.add(subPro);

				String name = server.getSrvmonitor_name();
				if (name == null)
					name = "";
				subPro = new SubProParam(name, SubProParam.IN);
				params.add(subPro);
				try {
					params = executeSubPro(eVODSql.sqlAddServer, params);
					isAdd = true;
					String outScreen = "[addServer with params: "
							+ server.toString() + " : returnValue(isAdd="
							+ isAdd + ")";
					log.info(outScreen);
				} catch (Exception ex) {
					log.error(ex.getMessage());
				}
			}
		}
		return isAdd;
	}

	@SuppressWarnings("unchecked")
	public Vector<EServer> getServers() {
		Vector<EServer> vServers = new Vector<EServer>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlGetServers, params);
			if (params != null & params.size() > 0) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		vServers = DataUtils.LoadEServer(outParam);
		String outScreen = "[getServers with params: "
				+ "] : returnValue(size=" + vServers.size() + ")";
		log.info(outScreen);
		return vServers;
	}

	@SuppressWarnings("unchecked")
	public EServer getEServerInfo(String ip) {
		if (ip == null)
			return null;
		EServer vSubject = null;
		Vector<EServer> vVod = new Vector<EServer>();
		Vector<SubProParam> params = new Vector<SubProParam>();
		SubProParam subPro = new SubProParam(ip, SubProParam.IN);
		params.add(subPro);
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR",
				SubProParam.OUT);
		params.add(subOut);

		try {
			params = executeSubPro(eVODSql.sqlGetServerInfo, params);
			if (params != null && params.size() > 0) {
				subOut = (SubProParam) params.get(1);
				outParam = subOut.getVector();
			}
			vVod = DataUtils.LoadEServer(outParam);
			String outScreen = "[getEServer with params: ip=" + ip + "] )";
			if (vVod != null && vVod.size() > 0) {
				vSubject = vVod.get(0);
			}
			log.info(outScreen);

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return vSubject;
	}
}
