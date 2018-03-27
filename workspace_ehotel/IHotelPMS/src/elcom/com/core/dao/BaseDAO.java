package elcom.com.core.dao;

import com.elcom.DBI.*;
import java.util.Vector;
import elcom.com.apiconnect.DBIManager;
import elcom.com.apiconnect.eConnection;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class BaseDAO {
    public static DBI dbiService;
    public static String urlSource;
    private static eConnection con;
    public SubProParam outData;
    public SubProParam outDataRet;
    public String dbiHost;
    public String dbiPort;
    public String dbiServiceName;
    public DBIManager dbiManager;


    public BaseDAO() {
        initSystem();
    }

    public BaseDAO(String langName) {
        initSystem();
    }

    @SuppressWarnings("rawtypes")
	private void initSystem() {
        if (dbiService == null) {
            if (con == null) {
                con = new eConnection();
            }
            dbiService = con.getDBICore();
            //System.out.println("dbiService="+dbiService);
        }

        if (outData == null) {
            outData = new SubProParam(new Vector(), "STRING_ARR",
                                      SubProParam.OUT);
        }
    }

    @SuppressWarnings("rawtypes")
	public Vector executeSubPro(String query, Vector params) throws RemoteException, SQLException{
                System.out.println(query);
                return dbiService.executeSubPro(query, params);
    }


}
