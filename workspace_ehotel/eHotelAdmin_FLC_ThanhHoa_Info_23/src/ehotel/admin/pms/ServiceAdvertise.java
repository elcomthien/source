package ehotel.admin.pms;

import ehotel.abs.pms.AdvertisePMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.*;
import ehotel.domain.pms.eAdvertise;
import java.io.*;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceAdvertise extends ServiceParent
{

    public ServiceAdvertise()
    {
        transferImageFLC = new TransferImageFLC();
    }

    public void destroy()
    {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        super.doGet(request, response);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int cmd = -1;
        if(request.getParameter("CMD") != null)
            try
            {
                cmd = Integer.parseInt(request.getParameter("CMD").toString());
            }
            catch(Exception exception) { }
        switch(cmd)
        {
        case 0: // '\0'
        case 2: // '\002'
        default:
            break;

        case -1: 
        {
            int index = 0;
            int page = 5;
            if(request.getParameter("pageIndex") != null)
                index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page").toString().trim());
            response.setContentType("text/xml");
            String st = getContent(index, page);
            out.print(st);
            break;
        }

        case 1: // '\001'
        {
            int index = 0;
            int page = 6;
            if(request.getParameter("pageIndex") != null)
                index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page").toString().trim());
            response.setContentType("text/xml");
            String st = getContent(index, page);
            out.print(st);
            break;
        }

        case 3: // '\003'
        {
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            AdvertisePMS ehotel = new AdvertisePMS();
            eAdvertise item = ehotel.getAdvertiseInfo(id, LangID);
            request.setAttribute("Item", item);
            showJSPpage(request, response, "/pmsMng/Other/detailAdver.jsp");
            break;
        }

        case 4: // '\004'
        {
            System.out.println("DELETE ADV");
            int i = 0;
            Vector list = new Vector();
            int id;
            for(; request.getParameter((new StringBuilder("id")).append(i).toString()) != null; list.add(Integer.valueOf(id)))
            {
                id = Integer.parseInt(request.getParameter((new StringBuilder("id")).append(i).toString()).toString().trim());
                i++;
            }

            AdvertisePMS ehotel = new AdvertisePMS();
            String param = "(";
            for(i = 0; i < list.size(); i++)
                param = (new StringBuilder(String.valueOf(param))).append(list.get(i)).append(",").toString();

            param = (new StringBuilder(String.valueOf(param.substring(0, param.length() - 1)))).append(")").toString();
            ehotel.removeAdverties(param);
            break;
        }

        case 5: // '\005'
        {
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            AdvertisePMS adv = new AdvertisePMS();
            adv.changeStatus(id);
            break;
        }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        super.doPost(request, response);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int cmd = -1;
        ConfigLoader loader = new ConfigLoader();
        Config config = loader.getConfig();
        if(request.getParameter("CMD") != null)
            try
            {
                cmd = Integer.parseInt(request.getParameter("CMD").toString());
            }
            catch(Exception exception) { }
        switch(cmd)
        {
        default:
            break;

        case 1: // '\001'
        {
            System.out.println("UPDATE ADV PMS");
            String Name = "";
            String image = "";
            String bgimage = "";
            String type = "";
            if(request.getParameter("type") != null)
                type = request.getParameter("type").toString();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString();
            if(request.getParameter("icon") != null)
                bgimage = request.getParameter("icon").toString();
            int id = -1;
            if(request.getParameter("name") != null && request.getParameter("id") != null)
            {
                id = Integer.parseInt(request.getParameter("id").toString().trim());
                Name = request.getParameter("name").toString();
                AdvertisePMS adv = new AdvertisePMS();
                eAdvertise item = new eAdvertise();
                item.setId(id);
                item.setName(Name);
                item.setType(type);
                item.setUrlImage((new StringBuilder(String.valueOf(config._advertise))).append("/").append(image).toString());
                item.setUrlBg((new StringBuilder(String.valueOf(config._advertise))).append("/").append(bgimage).toString());
                boolean t = adv.editAdvertise(item, LangID);
                System.out.println((new StringBuilder("UPDATE ADV PMS:")).append(t).toString());
                if(t)
                {
                    ManagerFile file = new ManagerFile();
                    String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                    String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._advertise).append("/").append(image).toString();
                    file.copy(path1, path2);
                    file.deletefile(path1);
                    path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(bgimage).toString();
                    path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._advertise).append("/").append(bgimage).toString();
                    file.copy(path1, path2);
                    transferImageFLC.transferImageFLC23(path1, config._advertise);
                    file.deletefile(path1);
                }
            }
            break;
        }

        case 2: // '\002'
        {
            System.out.println("insert ADV PMS");
            String Name = "";
            String image = "";
            String bgimage = "";
            String type = "";
            if(request.getParameter("type") != null)
                type = request.getParameter("type").toString();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString();
            if(request.getParameter("icon") != null)
                bgimage = request.getParameter("icon").toString();
            int id = -1;
            if(request.getParameter("name") == null || request.getParameter("id") == null)
                break;
            id = Integer.parseInt(request.getParameter("id").toString().trim());
            Name = request.getParameter("name").toString();
            AdvertisePMS adv = new AdvertisePMS();
            eAdvertise item = new eAdvertise();
            item.setId(id);
            item.setName(Name);
            item.setType(type);
            item.setUrlImage((new StringBuilder(String.valueOf(config._advertise))).append("/").append(image).toString());
            item.setUrlBg((new StringBuilder(String.valueOf(config._advertise))).append("/").append(bgimage).toString());
            int t = adv.addAdvertise(item, type);
            System.out.println((new StringBuilder("insert ADV PMS:")).append(t).toString());
            if(t > 0)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._advertise).append("/").append(image).toString();
                file.copy(path1, path2);
                file.deletefile(path1);
                path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(bgimage).toString();
                path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._advertise).append("/").append(bgimage).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._advertise);
                file.deletefile(path1);
            }
            break;
        }
        }
    }

    public void init()
        throws ServletException
    {
    }

    private String getContent(int index, int page)
    {
        AdvertisePMS ehotel = new AdvertisePMS();
        int fr = index * page;
        fr++;
        int to = (index + 1) * page;
        Vector info = ehotel.getImageAdverties(LangID, fr, to);
        String mData = "";
        int count = ehotel.countItem();
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml  count=\"").append(count).append("\">").toString();
        for(int i = 0; i < info.size(); i++)
        {
            eAdvertise item = (eAdvertise)info.get(i);
            System.out.println((new StringBuilder("Name:")).append(item.getName()).toString());
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getName()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getId()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getUrlImage()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<icon>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getUrlIcon()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</icon>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getType()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getInvisibe()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private static final long serialVersionUID = 1L;
    private TransferImageFLC transferImageFLC;
}
