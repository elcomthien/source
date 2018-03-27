package com.elcom.ehotel.model;


public class RoomStatusModel
{

    public RoomStatusModel()
    {
        folionum = "";
        statusname = "";
        statusid = "";
        date = "";
        service = "";
    }

    public String getFolionum()
    {
        return folionum;
    }

    public void setFolionum(String folionum)
    {
        this.folionum = folionum;
    }

    public String getStatusname()
    {
        return statusname;
    }

    public void setStatusname(String statusname)
    {
        this.statusname = statusname;
    }

    public String getStatusid()
    {
        return statusid;
    }

    public void setStatusid(String statusid)
    {
        this.statusid = statusid;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public String toString()
    {
        return (new StringBuilder("RoomStatusModel [folionum=")).append(folionum).append(", statusname=").append(statusname).append(", statusid=").append(statusid).append(", date=").append(date).append(", service=").append(service).append("]").toString();
    }

    private String folionum;
    private String statusname;
    private String statusid;
    private String date;
    private String service;
}
