package com.elcom.adcenter.rvcadv.util;

import com.elcom.abopuser.rvcadv.user.UserDAO;
import com.elcom.adcenter.rvcadv.group.*;
import com.elcom.adcenter.rvcadv.layout.*;
import com.elcom.adcenter.rvcadv.playlist.PlaylistDao;
import com.elcom.adcenter.rvcadv.report.ReportaBop;
import com.elcom.adcenter.rvcadv.schedule.ScheduleDao;
import com.elcom.adcenter.rvcav.content.ContentDao;
import com.elcom.adcenter.rvcav.content.ServiceContentDao;

public class DAOFactory {
	private static DAOFactory instance = null;

	  private DAOFactory()
	  {
	  }

	  public static synchronized DAOFactory getInstance()
	  {
	    if (instance == null)
	      instance = new DAOFactory();

	    return instance;
	  }
	  
	  /**
	   * @return
	   */
	  public GroupDao getGroupDao()
	  {
	    return new GroupDao();
	  }
	  
	  public LayoutDao getLayoutDao()
	  {
		  return new LayoutDao();
	  }
	  
	  public PlaylistDao getPlaylistDao()
	  {
		  return new PlaylistDao();
	  }
	  
	  public ScheduleDao getScheduleDao()
	  {
		  return new ScheduleDao();
	  }
	  
	  public ContentDao getContentDao()
	  {
		  return new ContentDao();
	  }
	  
	  public ReportaBop ReportaBopDao()
	  {
		  return new ReportaBop();
	  }
	  
	  public UserDAO getUserDAO()
	  {
		  return new UserDAO();
	  }
}