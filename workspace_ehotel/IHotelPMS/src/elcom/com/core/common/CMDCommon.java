package elcom.com.core.common;

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
public class CMDCommon {
    private String folioNum;
    private String clientId;
    private String cmdId;// id cua doi tuong cu the
    private String date;
    private String time;
    private String status;
    public CMDCommon() {
    }

    public String getClientId() {
        return clientId;
    }

    public String getDate() {
        return date;
    }

    public String getFolioNum() {
        return folioNum;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getCmdId() {
        return cmdId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFolioNum(String folioNum) {
        this.folioNum = folioNum;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }
}
