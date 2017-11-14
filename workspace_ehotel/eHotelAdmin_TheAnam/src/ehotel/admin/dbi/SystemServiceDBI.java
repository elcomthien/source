// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 5/9/2016 2:49:35 PM
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SystemServiceDBI.java

package ehotel.admin.dbi;

import com.elcom.DBI.SubProParam;
import java.io.PrintStream;
import java.util.Vector;

// Referenced classes of package ehotel.admin.dbi:
//            IMBroker

public class SystemServiceDBI
{

    public SystemServiceDBI()
    {
    }

    public String updateBackgroundVideo(String filename)
    {
        String result = "";
        Vector outParam = new Vector();
        Vector params = new Vector();
        SubProParam in = new SubProParam(new String(filename), 0);
        params.add(in);
        SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
        params.add(subOut);
        try
        {
            params = broker.executeSubPro("BEGIN EPMS.update_background_video(?,?); END;", params);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if((params != null) & (params.size() > 0))
        {
            subOut = (SubProParam)params.get(0);
            outParam = subOut.getVector();
            System.out.println(outParam);
            result = outParam.get(0).toString();
        }
        return result;
    }

    static IMBroker broker = IMBroker.getInstance();
    public static final String UPDATE_BACKGROUND_VIDEO = "BEGIN EPMS.update_background_video(?,?); END;";

}
