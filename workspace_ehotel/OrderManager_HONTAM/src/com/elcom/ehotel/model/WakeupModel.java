package com.elcom.ehotel.model;


public class WakeupModel
{

    public WakeupModel()
    {
        id = "";
        service = "";
        folionum = "";
        datewakeup = "";
        hours = "";
        minutes = "";
        datepost = "";
        status = "";
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public String getFolionum()
    {
        return folionum;
    }

    public void setFolionum(String folionum)
    {
        this.folionum = folionum;
    }

    public String getDatewakeup()
    {
        return datewakeup;
    }

    public void setDatewakeup(String datewakeup)
    {
        this.datewakeup = datewakeup;
    }

    public String getHours()
    {
        return hours;
    }

    public void setHours(String hours)
    {
        this.hours = hours;
    }

    public String getMinutes()
    {
        return minutes;
    }

    public void setMinutes(String minutes)
    {
        this.minutes = minutes;
    }

    public String getDatepost()
    {
        return datepost;
    }

    public void setDatepost(String datepost)
    {
        this.datepost = datepost;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String toString()
    {
        return (new StringBuilder("WakeupModel [id=")).append(id).append(", service=").append(service).append(", folionum=").append(folionum).append(", datewakeup=").append(datewakeup).append(", hours=").append(hours).append(", minutes=").append(minutes).append(", datepost=").append(datepost).append(", status=").append(status).append("]").toString();
    }

    private String id;
    private String service;
    private String folionum;
    private String datewakeup;
    private String hours;
    private String minutes;
    private String datepost;
    private String status;
}
