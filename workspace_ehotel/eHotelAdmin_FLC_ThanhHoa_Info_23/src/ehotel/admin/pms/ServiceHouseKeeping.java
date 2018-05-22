package ehotel.admin.pms;

import ehotel.abs.pms.HousekeepingPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.PMSServiceDBI;
import ehotel.admin.model.PMSMainMenu;
import ehotel.admin.util.*;
import ehotel.domain.pms.eHousekeeping;
import ehotel.domain.pms.eMenu;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceHouseKeeping extends ServiceParent
{

    public ServiceHouseKeeping()
    {
        pmsServiceDBI = new PMSServiceDBI();
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
            request.setAttribute("fileJSP", "../pmsMng/keeping/keeping.jsp");
            showJSPpage(request, response, "/include/Mainpage.jsp");
            break;
        }

        case 1: // '\001'
        {
            System.out.println("Get subject housekkeping");
            response.setContentType("text/xml");
            String st = getMainMenuHotel();
            out.print(st);
            break;
        }

        case 2: // '\002'
        {
            System.out.println("Get content");
            int id = -1;
            int index = 0;
            int page = 6;
            if(request.getParameter("SubId") != null)
                id = Integer.parseInt(request.getParameter("SubId").toString());
            if(request.getParameter("pageIndex") != null)
                index = Integer.parseInt(request.getParameter("pageIndex").toString().trim());
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page").toString().trim());
            response.setContentType("text/xml");
            String st = getItem(id);
            out.print(st);
            break;
        }

        case 3: // '\003'
        {
            System.out.println("Show form detail");
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString());
            HousekeepingPMS hotel = new HousekeepingPMS();
            eHousekeeping item = null;
            if(id != -1)
                item = hotel.getItemInfo(id, LangID);
            request.setAttribute("Item", item);
            showJSPpage(request, response, "/pmsMng/keeping/detaiItem.jsp");
            break;
        }

        case 4: // '\004'
        {
            System.out.println("Delete item:");
            Vector list = new Vector();
            int i = 0;
            int subId = -1;
            for(; request.getParameter((new StringBuilder("id")).append(i).toString()) != null; i++)
            {
                int subid = Integer.parseInt(request.getParameter((new StringBuilder("id")).append(i).toString()).toString().trim());
                list.add(Integer.valueOf(subid));
            }

            if(request.getParameter("SubId") != null)
                subId = Integer.parseInt(request.getParameter("SubId").toString().trim());
            String param = "(";
            for(i = 0; i < list.size(); i++)
                param = (new StringBuilder(String.valueOf(param))).append(list.get(i)).append(",").toString();

            param = (new StringBuilder(String.valueOf(param.substring(0, param.length() - 1)))).append(")").toString();
            HousekeepingPMS hotel = new HousekeepingPMS();
            hotel.removeItem(param);
            break;
        }

        case 5: // '\005'
        {
            System.out.println("Show form detail");
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString());
            HousekeepingPMS hotel = new HousekeepingPMS();
            hotel.changeStatus(id);
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
            System.out.println("Insert item housekeeping");
            String name = "";
            String image = "";
            String price = "";
            int subid = -1;
            String unit = "";
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
            if(request.getParameter("price") != null)
                price = request.getParameter("price").toString().trim();
            if(request.getParameter("subid") != null)
                subid = Integer.parseInt(request.getParameter("subid").toString().trim());
            if(request.getParameter("unit") != null)
                unit = request.getParameter("unit").toString().trim();
            HousekeepingPMS dinning = new HousekeepingPMS();
            eHousekeeping item = new eHousekeeping();
            item.setPrice(price);
            item.setName(name);
            item.setUrlImage((new StringBuilder(String.valueOf(config._housekeeping))).append("/").append(image).toString());
            item.setIunit(unit);
            int t = dinning.addItem(subid, item);
            if(t > 0)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._housekeeping).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._housekeeping);
                file.deletefile(path1);
            }
            break;
        }

        case 2: // '\002'
        {
            System.out.println("update item housekeeping");
            String name = "";
            String image = "";
            String price = "";
            String unit = "";
            int id = -1;
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
            if(request.getParameter("price") != null)
                price = request.getParameter("price").toString().trim();
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("unit") != null)
                unit = request.getParameter("unit").toString().trim();
            HousekeepingPMS dinning = new HousekeepingPMS();
            eHousekeeping item = new eHousekeeping();
            item.setPrice(price);
            item.setName(name);
            item.setUrlImage((new StringBuilder(String.valueOf(config._housekeeping))).append("/").append(image).toString());
            item.setId(id);
            item.setIunit(unit);
            boolean t = dinning.editItem(item, LangID);
            if(t)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._housekeeping).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._housekeeping);
                file.deletefile(path1);
            }
            break;
        }

        case 3: // '\003'
        {
            System.out.println("update subject PMS!");
            response.setContentType("text/xml");
            String subjectName = "";
            String image = "";
            String urlBg = "";
            int parent = -1;
            int subid = -1;
            if(request.getParameter("image1") != null)
                image = request.getParameter("image1").toString();
            if(request.getParameter("image2") != null)
                urlBg = request.getParameter("image2").toString();
            if(request.getParameter("SubId") != null)
                subid = Integer.parseInt(request.getParameter("SubId").toString().trim());
            if(request.getParameter("name") == null)
                break;
            subjectName = request.getParameter("name").toString();
            HousekeepingPMS hotel = new HousekeepingPMS();
            eMenu menu = new eMenu();
            menu.setMenuName(subjectName);
            menu.setUrlImage((new StringBuilder(String.valueOf(config._housekeeping))).append("/").append(image).toString());
            menu.setUrlBg((new StringBuilder(String.valueOf(config._housekeeping))).append("/").append(urlBg).toString());
            menu.setMenuId(subid);
            boolean id = hotel.editMenuHousekeeping(menu, LangID);
            if(id)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._housekeeping).append("/").append(image).toString();
                file.copy(path1, path2);
                file.deletefile(path1);
                path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(urlBg).toString();
                path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._housekeeping).append("/").append(urlBg).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._housekeeping);
                file.deletefile(path1);
            }
            break;
        }
        }
    }

    private String getmenu()
    {
        HousekeepingPMS hotel = new HousekeepingPMS();
        Vector subject = hotel.getMenus(LangID);
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

    private String getMainMenuHotel()
    {
        List list = new ArrayList();
        list = pmsServiceDBI.getMainMenu(6, LangID);
        String mData = "";
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml>").toString();
        for(int i = 0; i < list.size(); i++)
        {
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(((PMSMainMenu)list.get(i)).getMenuname()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(((PMSMainMenu)list.get(i)).getMenuid()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<image>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(((PMSMainMenu)list.get(i)).getMenuimage()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<imagebg>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(((PMSMainMenu)list.get(i)).getMenubackground()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</imagebg>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(-1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<invisible>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(((PMSMainMenu)list.get(i)).getMenuinvisible()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</invisible>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private String getItem(int id)
    {
        HousekeepingPMS keeping = new HousekeepingPMS();
        String mData = "";
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml>").toString();
        Vector v_rs = keeping.getItems(id, LangID, -1, -1);
        for(int i = 0; i < v_rs.size(); i++)
        {
            eHousekeeping item = (eHousekeeping)v_rs.get(i);
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getName()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getId()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<Def>").toString();
            if(item.getDef() == null)
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[]]>").toString();
            else
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getDef()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Def>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getInvisible()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getUrlImage()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<price>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getPrice()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</price>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<unit>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getIunit()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</unit>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    public void init()
        throws ServletException
    {
    }

    private static final long serialVersionUID = 1L;
    private PMSServiceDBI pmsServiceDBI;
    private TransferImageFLC transferImageFLC;
}
