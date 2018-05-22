package com.elcom.abopuser.rvcadv.user;

import org.apache.log4j.Logger;

import com.elcom.adcenter.rvcadv.common.SQL;
import com.elcom.adcenter.rvcadv.sql.SqlCore;
import com.elcom.adcenter.rvcadv.util.DateHelper;

public class UserDAO {
	private static Logger log = Logger.getLogger(UserDAO.class);

	public String abopGetUser(String xmlparamter) {
		String username = DateHelper.utilStringXml(xmlparamter, SQL.username);
		String pass = DateHelper.utilStringXml(xmlparamter, SQL.password);
		String result = SqlCore.abopGetUser(username, pass);
		log.info("-> abopGetUser: username="+username+" password="+pass+" status="+result);
		return result;
	}
	public String abopGetAllBox(String xmlparamter){
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		String result = SqlCore.abopGetAllBox(creator, pcreator);
		log.info("->Call abopGetAllBox():"+result);
		return result;
	}

	public String abopGetListUser(String xmlparamter) {
		log.info("*********Method abopGetListUser************");
		String creator = DateHelper.utilStringXml(xmlparamter, SQL.creator);
		String pcreator = DateHelper.utilStringXml(xmlparamter, SQL.parentcreator);
		log.info("*********creator: "+creator+"************");
		String result = SqlCore.abopGetListUser(creator, pcreator);
		return result;
	}

	public String abopCreateUser(String xmlparamter) {
		log.info("*********Method abopCreatorUser************");
		log.info("*********Param: "+xmlparamter+"************");
		String result = SqlCore.abopCreateUser(xmlparamter);
		return result;
	}

	public String abopGetRole() {
		log.info("*********Method abopGetRole************");
		
		String result = SqlCore.abopGetRole();
		return result;
	}

	public String abopCheckUser(String username) {
		log.info("*********Method abopCheckUser************");
		String result = SqlCore.abopCheckUser(username);
		return result;
	}

	public String abopUpdateUser(String xmlparamter) {
		log.info("*********Method abopUpdateUser************");
		log.info("*********Param: "+xmlparamter+"************");
		String result = SqlCore.abopUpdateUser(xmlparamter);
		return result;
	}
	public String abopAddStbUser(String xmlparamter) {
		log.info("*********Method abopAddStbUser************");
		log.info("*********Param: "+xmlparamter+"************");
		String result = SqlCore.abopAddStbUser(xmlparamter);
		return result;
	}

	public String abopDeleteUser(String xmlparamter) {
		log.info("*********Method abopDeleteUser************");
		log.info("*********Param: "+xmlparamter+"************");
		String result = SqlCore.abopDeleteUser(xmlparamter);
		return result;
	}
}
