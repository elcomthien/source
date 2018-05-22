package ehotel.admin.pms;

import ehotel.abs.pms.TransportationPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.*;
import ehotel.domain.pms.eMenu;
import ehotel.domain.pms.eUrlAirline;
import java.io.*;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceTranport extends ServiceParent
{

    public ServiceTranport()
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
        default:
            break;

        case -1: 
        {
            int subId = -1;
            int menuid = -1;
            if(request.getParameter("MenuId") != null)
                menuid = Integer.parseInt(request.getParameter("MenuId").toString());
            if(request.getParameter("SubId") != null)
                subId = Integer.parseInt(request.getParameter("SubId").toString());
            request.setAttribute("MenuId", Integer.valueOf(menuid));
            request.setAttribute("SubId", Integer.valueOf(subId));
            request.setAttribute("fileJSP", "../pmsMng/transport/Transport.jsp");
            showJSPpage(request, response, "/include/Mainpage.jsp");
            break;
        }

        case 1: // '\001'
        {
            System.out.println("Get subject transport");
            response.setContentType("text/xml");
            String st = getsub();
            out.print(st);
            break;
        }

        case 2: // '\002'
        {
            int index = 0;
            int page = 6;
            if(request.getParameter("pageIndex") != null)
                index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page").toString().trim());
            int menuid = -1;
            if(request.getParameter("MenuId") != null)
                menuid = Integer.parseInt(request.getParameter("MenuId").toString());
            response.setContentType("text/xml");
            String st = getContent1(menuid, index, page);
            out.print(st);
            break;
        }

        case 3: // '\003'
        {
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            TransportationPMS tran = new TransportationPMS();
            eUrlAirline item = tran.geteUrlAirlineInfo(id);
            request.setAttribute("Item", item);
            showJSPpage(request, response, "/pmsMng/transport/detailURL.jsp");
            break;
        }

        case 4: // '\004'
        {
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            TransportationPMS tran = new TransportationPMS();
            if(id != -1)
            {
                eMenu item = tran.getItemMenuInfo(id, LangID);
                request.setAttribute("Item", item);
            }
            showJSPpage(request, response, "/pmsMng/transport/detailGround.jsp");
            break;
        }

        case 5: // '\005'
        {
            int index = 0;
            int page = 6;
            if(request.getParameter("pageIndex") != null)
                index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page").toString().trim());
            int menuid = -1;
            if(request.getParameter("MenuId") != null)
                menuid = Integer.parseInt(request.getParameter("MenuId").toString());
            response.setContentType("text/xml");
            String st = getContent2(menuid, index, page);
            out.print(st);
            break;
        }

        case 6: // '\006'
        {
            System.out.println("Delete group");
            int id = -1;
            int i = 0;
            Vector list = new Vector();
            for(; request.getParameter((new StringBuilder("id")).append(i).toString()) != null; i++)
            {
                id = Integer.parseInt(request.getParameter((new StringBuilder("id")).append(i).toString()).toString().trim());
                list.add(Integer.valueOf(id));
            }

            TransportationPMS tran = new TransportationPMS();
            for(i = 0; i < list.size(); i++)
                tran.removeItemMenu(((Integer)list.get(i)).intValue());

            break;
        }

        case 7: // '\007'
        {
            System.out.println("Delete URL");
            int id = -1;
            int i = 0;
            Vector list = new Vector();
            for(; request.getParameter((new StringBuilder("id")).append(i).toString()) != null; i++)
            {
                id = Integer.parseInt(request.getParameter((new StringBuilder("id")).append(i).toString()).toString().trim());
                list.add(Integer.valueOf(id));
            }

            TransportationPMS tran = new TransportationPMS();
            for(i = 0; i < list.size(); i++)
                tran.removeUrlAirline(((Integer)list.get(i)).intValue());

            break;
        }

        case 8: // '\b'
        {
            System.out.println("Delete URL");
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            TransportationPMS tran = new TransportationPMS();
            tran.changeStaus(id);
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
            System.out.println("Insert Ground");
            String name = "";
            String image = "";
            String icon = "";
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString().trim();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
            if(request.getParameter("icon") != null)
                icon = request.getParameter("icon").toString().trim();
            TransportationPMS transport = new TransportationPMS();
            eMenu menu = new eMenu();
            menu.setMenuName(name);
            menu.setUrlBg((new StringBuilder(String.valueOf(config._transportation))).append("/").append(icon).toString());
            menu.setUrlImage((new StringBuilder(String.valueOf(config._transportation))).append("/").append(image).toString());
            int t = transport.addItemMenu(id, menu);
            if(t > 0)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._transportation).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._transportation);
                file.deletefile(path1);
                path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(icon).toString();
                path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._transportation).append("/").append(icon).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._transportation);
                file.deletefile(path1);
            }
            break;
        }

        case 2: // '\002'
        {
            System.out.println("edit Ground");
            String name = "";
            String image = "";
            String icon = "";
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString().trim();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
            if(request.getParameter("icon") != null)
                icon = request.getParameter("icon").toString().trim();
            System.out.println((new StringBuilder("icon:")).append(icon).toString());
            TransportationPMS transport = new TransportationPMS();
            eMenu menu = new eMenu();
            menu.setMenuName(name);
            menu.setUrlBg((new StringBuilder(String.valueOf(config._transportation))).append("/").append(icon).toString());
            menu.setUrlImage((new StringBuilder(String.valueOf(config._transportation))).append("/").append(image).toString());
            menu.setMenuId(id);
            boolean t = transport.editItemMenu(menu, LangID);
            if(t)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._transportation).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._transportation);
                file.deletefile(path1);
                path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(icon).toString();
                path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._transportation).append("/").append(icon).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._transportation);
                file.deletefile(path1);
            }
            break;
        }

        case 3: // '\003'
        {
            System.out.println("Insert URL");
            String name = "";
            String url = "";
            String image = "";
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString().trim();
            if(request.getParameter("url") != null)
                url = request.getParameter("url").toString().trim();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
            TransportationPMS transport = new TransportationPMS();
            eUrlAirline item = new eUrlAirline();
            item.setName(name);
            item.setUrlLink(url);
            item.setUrlImage((new StringBuilder(String.valueOf(config._transportation))).append("/").append(image).toString());
            int t = transport.addUrlAirline(item);
            if(t > 0)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._transportation).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._transportation);
                file.deletefile(path1);
            }
            break;
        }

        case 4: // '\004'
        {
            System.out.println("update URL");
            String name = "";
            String url = "";
            String image = "";
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString().trim();
            if(request.getParameter("url") != null)
                url = request.getParameter("url").toString().trim();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
            TransportationPMS transport = new TransportationPMS();
            eUrlAirline item = new eUrlAirline();
            item.setName(name);
            item.setUrlLink(url);
            item.setUrlImage((new StringBuilder(String.valueOf(config._transportation))).append("/").append(image).toString());
            item.setId(id);
            boolean t = transport.editUrlAirline(item);
            if(t)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._transportation).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._transportation);
                file.deletefile(path1);
            }
            break;
        }

        case 5: // '\005'
        {
            System.out.println("update subject");
            String name = "";
            String icon = "";
            String image = "";
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString().trim();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
            if(request.getParameter("icon") != null)
                icon = request.getParameter("icon").toString().trim();
            TransportationPMS transport = new TransportationPMS();
            eMenu menu = new eMenu();
            menu.setMenuName(name);
            menu.setUrlImage((new StringBuilder(String.valueOf(config._transportation))).append("/").append(image).toString());
            menu.setUrlBg((new StringBuilder(String.valueOf(config._transportation))).append("/").append(icon).toString());
            menu.setMenuId(id);
            transport.editItemMenu(menu, LangID);
            ManagerFile file = new ManagerFile();
            String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
            String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._transportation).append("/").append(image).toString();
            file.copy(path1, path2);
            transferImageFLC.transferImageFLC23(path1, config._transportation);
            file.deletefile(path1);
            path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
            path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._transportation).append("/").append(icon).toString();
            file.copy(path1, path2);
            transferImageFLC.transferImageFLC23(path1, config._transportation);
            file.deletefile(path1);
            break;
        }
        }
    }

    public void init()
        throws ServletException
    {
    }

    private String getsub()
    {
        TransportationPMS transport = new TransportationPMS();
        Vector subject = transport.getMenus(LangID);
        String mData = "";
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml>").toString();
        for(int i = 0; i < subject.size(); i++)
        {
            eMenu item = (eMenu)subject.get(i);
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getMenuName()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getMenuId()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<image>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getUrlImage()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<imagebg>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getUrlBg()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</imagebg>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(-1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private String getContent1(int menuId, int index, int page)
    {
        TransportationPMS ehotel = new TransportationPMS();
        int fr = index * page;
        fr++;
        int to = (index + 1) * page;
        Vector info = ehotel.getUrlAirlines(fr, to);
        String mData = "";
        int count = ehotel.countItem();
        System.out.println((new StringBuilder("count:")).append(count).toString());
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml  count=\"").append(count).append("\">").toString();
        for(int i = 0; i < info.size(); i++)
        {
            eUrlAirline item = (eUrlAirline)info.get(i);
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
            mData = (new StringBuilder(String.valueOf(mData))).append("<url>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getUrlLink()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</url>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getInvisble()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private String getContent2(int menuId, int index, int page)
    {
        TransportationPMS ehotel = new TransportationPMS();
        int fr = index * page;
        fr++;
        int to = (index + 1) * page;
        Vector info = ehotel.getItemMenu(menuId, LangID, fr, to);
        String mData = "";
        int count = ehotel.countItemMenu(menuId);
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml  count=\"").append(count).append("\">").toString();
        for(int i = 0; i < info.size(); i++)
        {
            eMenu item = (eMenu)info.get(i);
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getMenuName()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getMenuId()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getUrlImage()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<icon>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getUrlBg()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</icon>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private static final long serialVersionUID = 1L;
    private TransferImageFLC transferImageFLC;
}
