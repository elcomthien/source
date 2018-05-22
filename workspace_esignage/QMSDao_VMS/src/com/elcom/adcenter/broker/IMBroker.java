package com.elcom.adcenter.broker;

import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.elcom.adcenter.cfg.Configuration;
import com.elcom.adcenter.cfg.ConfigurationLoader;


public class IMBroker {
	//The Singleton pattern
	  private static IMBroker instance = null;
	  //Get configuration object
	  private static Configuration config = null;
	  //To refers to DBIPool, contains logic connections to the DBI
	  private ConPool connPool = null;
	  private static String lsp = System.getProperty("line.separator");
	  
	  //To log application
	  private static final Logger logger = LogManager.getLogger(IMBroker.class);

	  //Read configuration params
	  static
	  {
	    ConfigurationLoader loader = ConfigurationLoader.getInstance();
	    config = loader.getConfiguration();
	  }

	  private IMBroker()
	  {
		connPool = new ConPool(config.pool_size_max);
	  }

	  public static synchronized IMBroker getInstance()
	  {
	    if (instance == null)
	    {
	      instance = new IMBroker();
	    }

	    return instance;
	  }
      
	  //======================================
	  public ResultSet executeQuery(String stmt)
	  {
		  ResultSet r = null;
		  
		  Connection conn = connPool.getConn();
		  try{			  			  
			  java.sql.Statement s = conn.createStatement();			  
			  r = s.executeQuery(stmt);
			  
		  }catch (Exception ex){
			  conn = null;
		      connPool.clearPool();

		      //Sleep in some intervals, then try againd
		      try {Thread.sleep(500);}catch (InterruptedException ex1) {}
		  }
		  finally{
			  connPool.putConn(conn);
		    }
		  return r;
	  }
	  //==========================================
	  private String getClientIP()
	  {
	    return "localhost";
	  }
	  //===========================================
	  private void setParam(CallableStatement  prepStmt, Vector params) throws SQLException
	  {
	    if (params == null || params.size() == 0 || prepStmt == null) return;

	    for (int i=0; i<params.size(); i++)
	    {
	      Object param = params.get(i);
	      if (param == null) throw new SQLException("From IMBroker.setParam(...): parameter is NULL at params[" + i + "]");
	      else if (param instanceof java.lang.String) prepStmt.setString(i + 1, (java.lang.String)param);
	      else if (param instanceof java.math.BigDecimal) prepStmt.setBigDecimal(i + 1, (java.math.BigDecimal)param);
	      else if (param instanceof java.sql.Timestamp) prepStmt.setTimestamp(i + 1, (java.sql.Timestamp)param);
	      else throw new SQLException("From IMBroker.setParam(...): Invalid parameter type at params[" + i + "]");
	    }
	  }
	  //===============================================
	  private String getParamString(Vector params)
	  {
	    String paramStr = "";
	    if (params == null) return "";
	    for (int i=0; i<params.size(); i++)
	    {
	      paramStr += params.get(i).toString() + lsp;
	    }
	    return paramStr;
	  }
	  //===============================================
	  private void registerParams(CallableStatement cs, Vector params, Connection con) throws SQLException
	  {
	    if (params == null || params.size() == 0 || cs == null) return;

	    for (int i=0; i<params.size(); i++)
	    {
	      Object paramObject =  (Object)params.get(i);
	      if (!(paramObject instanceof SubProParam)) throw new SQLException("From DBI.registerParams(...): is not SubProParam type at params[" + i + "]");
	      SubProParam param = (SubProParam)paramObject;
	      int type = param.getType();
	      Object value = param.getValue();
	      if (type == SubProParam.IN || type == SubProParam.INOUT)
	      {//Set ParamIN
	        if (value == null) throw new SQLException("From DBI.registerParams(...): parameterIN is NULL at params[" + i + "]");
	        else if (value instanceof java.lang.String) cs.setString(i + 1, (java.lang.String)value);
	        else if (value instanceof java.math.BigDecimal) cs.setBigDecimal(i + 1, (java.math.BigDecimal)value);
	        else if (value instanceof java.sql.Timestamp) cs.setTimestamp(i + 1, (java.sql.Timestamp)value);
	        else throw new SQLException("From IMBroker.registerParams(...): Unknown parameterIN[" + i + "] type");
	      }
	      if (type == SubProParam.OUT || type == SubProParam.INOUT)
	      {//Register ParamOUT
	        if (value == null) throw new SQLException("From DBI.registerParams(...): parameterOUT is NULL at params[" + i + "]");
	        else if (value instanceof java.lang.String) cs.registerOutParameter(i + 1, Types.VARCHAR);
	        else if (value instanceof java.math.BigDecimal) cs.registerOutParameter(i + 1, Types.DECIMAL);
	        else if (value instanceof java.sql.Timestamp) cs.registerOutParameter(i + 1, Types.TIMESTAMP);
	        else if (value instanceof java.util.Vector) cs.registerOutParameter(i + 1, Types.ARRAY, param.getNameOfTypeArray());
	        else throw new SQLException("From DBI.registerParams(...): Unknown parameterOUT[" + i + "] type");
	      }
	    }
	  }
	  //===============================================
	  private Vector getReturnParams(CallableStatement cs, Vector params, Connection con) throws SQLException
	  {
	    if (params == null || params.size() == 0) return null;

	    Vector returnValues = new Vector();
	    for (int i=0; i<params.size(); i++)
	    {
	      SubProParam param = (SubProParam)params.get(i);
	      int type = param.getType();

	      if (type == SubProParam.IN)
	      {
	        returnValues.add(param);
	      }
	      else //(type == SubProParam.OUT) || (type == SubProParam.INOUT)
	      {
	        Object value = param.getValue();
	        SubProParam paramOUT = null;
	        if (value instanceof java.lang.String)
	        {
	          paramOUT = new SubProParam(cs.getString(i+1), type);
	        }
	        else if (value instanceof java.math.BigDecimal)
	        {
	          paramOUT = new SubProParam(cs.getBigDecimal(i+1), type);
	        }
	        else if (value instanceof java.sql.Timestamp)
	        {
	          paramOUT = new SubProParam(cs.getTimestamp(i+1), type);
	        }
	        else
	        {
	          paramOUT = new SubProParam(new String("Unknow Type"), type);
	        }

	        returnValues.add(paramOUT);
	      }
	    }

	    return returnValues;
	  }
	  //===============================================
	  public Vector executeSubPro(String sqlSubPro, Vector params) throws SQLException
	  {
	    String client = getClientIP();
	    
	    String paramStr = getParamString(params);
	    //writeLog("[" + client + "] CALL SubPro " + sqlSubPro + "params = " + paramStr);
	    Connection con = connPool.getConn();
	    
	    CallableStatement callStmt = null;
	    Vector returnValues = null;
	    if (params != null && params.size() > 0) returnValues = new Vector(params);
	    if (sqlSubPro == null) throw new SQLException("sqlSubPro == null");
	    String strSqlBlock = sqlSubPro.trim();
	    if (strSqlBlock.length() == 0) throw new SQLException("sqlSubPro.length() == 0");
	    try
	    {	      
	      callStmt = (CallableStatement)con.prepareCall(strSqlBlock);
	      registerParams(callStmt, params, con);	      
	      callStmt.executeUpdate();	      
	      returnValues = getReturnParams(callStmt, params, con);
	      callStmt.close();
	    }
	    catch (Exception ex)
	    {
	    	con = null;
		    connPool.clearPool();
		      //Sleep in some intervals, then try againd
		      try {Thread.sleep(500);}catch (InterruptedException ex1) {}
	    	ex.printStackTrace();
	    }
	    finally
	    {	      
	    	connPool.putConn(con);
	    	try { if (callStmt != null) callStmt.close(); } catch (Exception ex) {}
	      
	    }	    
	    return returnValues;
	  }

}
