package com.elcom.ehotel.model;


public class HousekeepingModel
{

    public HousekeepingModel()
    {
        id = "";
        service = "";
        name = "";
        folionum = "";
        amount = "";
        datesave = "";
        status = "";
        userhk = "";
        guest = "";
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFolionum()
    {
        return folionum;
    }

    public void setFolionum(String folionum)
    {
        this.folionum = folionum;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getDatesave()
    {
        return datesave;
    }

    public void setDatesave(String datesave)
    {
        this.datesave = datesave;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getUserhk()
    {
        return userhk;
    }

    public void setUserhk(String userhk)
    {
        this.userhk = userhk;
    }

    public String getGuest()
    {
        return guest;
    }

    public void setGuest(String guest)
    {
        this.guest = guest;
    }

    public String toString()
    {
        return (new StringBuilder("HousekeepingModel [id=")).append(id).append(", service=").append(service).append(", name=").append(name).append(", folionum=").append(folionum).append(", amount=").append(amount).append(", datesave=").append(datesave).append(", status=").append(status).append(", userhk=").append(userhk).append(", guest=").append(guest).append("]").toString();
    }

    private String id;
    private String service;
    private String name;
    private String folionum;
    private String amount;
    private String datesave;
    private String status;
    private String userhk;
    private String guest;
}
