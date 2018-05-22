package ehotel.admin.pms;

import ehotel.abs.pms.HotelInfoPMS;
import ehotel.abs.pms.LocalAttractionPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.PMSServiceDBI;
import ehotel.admin.model.PMSMainMenu;
import ehotel.admin.util.*;
import ehotel.domain.pms.eAttraction;
import ehotel.domain.pms.eMenu;
import java.io.*;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceAttaction extends ServiceParent
{

    public ServiceAttaction()
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
            request.setAttribute("fileJSP", "../pmsMng/hotel/pmsActiviti.jsp");
            showJSPpage(request, response, "/include/Mainpage.jsp");
            break;
        }

        case 1: // '\001'
        {
            System.out.println("Get subject Activiti");
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
            String st = getContent(id, index, page);
            out.print(st);
            break;
        }

        case 3: // '\003'
        {
            System.out.println("DELETE SUBJECT PMS");
            LocalAttractionPMS hotel = new LocalAttractionPMS();
            int subid = -1;
            if(request.getParameter("SubId") != null)
                subid = Integer.parseInt(request.getParameter("SubId").toString());
            boolean b = hotel.removeAttractionMenu(subid);
            System.out.println((new StringBuilder("Delete :")).append(b).toString());
            break;
        }

        case 4: // '\004'
        {
            System.out.println("Show form detial Activiti");
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString());
            LocalAttractionPMS hotel = new LocalAttractionPMS();
            eAttraction item = null;
            item = hotel.getItemInfo(id, LangID);
            request.setAttribute("eImage", item);
            showJSPpage(request, response, "/pmsMng/hotel/detailAttaction.jsp");
            break;
        }

        case 5: // '\005'
        {
            System.out.println("show form change subject");
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString());
            LocalAttractionPMS hotel = new LocalAttractionPMS();
            eAttraction item = null;
            if(id != -1)
                item = hotel.getItemInfo(id, LangID);
            request.setAttribute("Item", item);
            showJSPpage(request, response, "/pmsMng/hotel/ActiChangeSub.jsp");
            break;
        }

        case 6: // '\006'
        {
            System.out.println("Change upsubject UPA");
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString());
            int subid = -1;
            if(request.getParameter("SubId") != null)
                subid = Integer.parseInt(request.getParameter("SubId").toString());
            LocalAttractionPMS hotel = new LocalAttractionPMS();
            hotel.changeSubjectOfItem(id, String.valueOf(subid));
            break;
        }

        case 7: // '\007'
        {
            System.out.println("change subject pms");
            int Id = -1;
            if(request.getParameter("id") != null)
                Id = Integer.parseInt(request.getParameter("id").toString().trim());
            Vector list = new Vector();
            for(int i = 0; request.getParameter((new StringBuilder("SubId")).append(i).toString()) != null; i++)
            {
                int subid = Integer.parseInt(request.getParameter((new StringBuilder("SubId")).append(i).toString()).toString().trim());
                list.add(Integer.valueOf(subid));
            }

            String param = "(";
            for(int i = 0; i < list.size(); i++)
                param = (new StringBuilder(String.valueOf(param))).append(list.get(i)).append(",").toString();

            param = (new StringBuilder(String.valueOf(param.substring(0, param.length() - 1)))).append(")").toString();
            HotelInfoPMS hotel = new HotelInfoPMS();
            boolean b = hotel.changeSubjectOfItem(Id, param);
            System.out.println((new StringBuilder("chenge subject:")).append(b).toString());
            break;
        }

        case 8: // '\b'
        {
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
            LocalAttractionPMS hotel = new LocalAttractionPMS();
            String param = "(";
            for(i = 0; i < list.size(); i++)
                param = (new StringBuilder(String.valueOf(param))).append(list.get(i)).append(",").toString();

            param = (new StringBuilder(String.valueOf(param.substring(0, param.length() - 1)))).append(")").toString();
            hotel.removeItem(param);
            break;
        }

        case 9: // '\t'
        {
            System.out.println("change status ");
            int id = -1;
            LocalAttractionPMS hotel = new LocalAttractionPMS();
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
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
            System.out.println("insert subject PMS Activiti!");
            response.setContentType("text/xml");
            String subjectName = "";
            String image = "";
            String urlBg = "";
            int parent = -1;
            if(request.getParameter("image1") != null)
                image = request.getParameter("image1").toString();
            if(request.getParameter("image2") != null)
                urlBg = request.getParameter("image2").toString();
            if(request.getParameter("name") != null)
            {
                subjectName = request.getParameter("name").toString();
                LocalAttractionPMS hotel = new LocalAttractionPMS();
                eMenu menu = new eMenu();
                menu.setMenuName(subjectName);
                menu.setUrlImage((new StringBuilder(String.valueOf(config._attraction))).append("/").append(image).toString());
                menu.setUrlBg((new StringBuilder(String.valueOf(config._attraction))).append("/").append(urlBg).toString());
                int id = hotel.addAttractionMenu(menu);
                if(id > 0)
                {
                    ManagerFile file = new ManagerFile();
                    String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                    String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._attraction).append("/").append(image).toString();
                    file.copy(path1, path2);
                    file.deletefile(path1);
                    path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(urlBg).toString();
                    path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._attraction).append("/").append(urlBg).toString();
                    file.copy(path1, path2);
                    transferImageFLC.transferImageFLC23(path1, config._attraction);
                    file.deletefile(path1);
                }
                out.write(id);
            }
            break;
        }

        case 2: // '\002'
        {
            System.out.println("UPDATE SUBJECT PMS");
            String subjectName = "";
            String image = "";
            String bgimage = "";
            if(request.getParameter("image1") != null)
                image = request.getParameter("image1").toString();
            if(request.getParameter("image2") != null)
                bgimage = request.getParameter("image2").toString();
            int subjId = -1;
            if(request.getParameter("name") == null || request.getParameter("SubId") == null)
                break;
            subjId = Integer.parseInt(request.getParameter("SubId").toString());
            subjectName = request.getParameter("name").toString();
            LocalAttractionPMS hotel = new LocalAttractionPMS();
            eMenu menu = new eMenu();
            menu.setMenuName(subjectName);
            menu.setMenuId(subjId);
            menu.setUrlImage((new StringBuilder(String.valueOf(config._attraction))).append("/").append(image).toString());
            menu.setUrlBg((new StringBuilder(String.valueOf(config._attraction))).append("/").append(bgimage).toString());
            boolean t = hotel.editAttractionMenu(menu, LangID);
            System.out.println((new StringBuilder("UPDATE SUBJECT PMS ACTIVITI:")).append(t).toString());
            if(t)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._attraction).append("/").append(image).toString();
                file.copy(path1, path2);
                file.deletefile(path1);
                path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(bgimage).toString();
                path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._attraction).append("/").append(bgimage).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._attraction);
                file.deletefile(path1);
            }
            break;
        }

        case 3: // '\003'
        {
            System.out.println("Update eImage");
            int id = -1;
            String name = "";
            String def = "";
            int status = 1;
            String address = "";
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString().trim();
            if(request.getParameter("des") != null)
            {
                def = request.getParameter("des").toString();
                def = def.replaceAll("<strong>", "<b>");
                def = def.replaceAll("</strong>", "</b>");
                def = def.replaceAll("<em>", "<i>");
                def = def.replaceAll("</em>", "</i>");
                def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
                def = def.replaceAll("</span>", "</u>");
            }
            if(request.getParameter("status") != null)
                status = Integer.parseInt(request.getParameter("status").toString().trim());
            if(request.getParameter("addres") != null)
                address = request.getParameter("addres").toString().trim();
            LocalAttractionPMS hotel = new LocalAttractionPMS();
            eAttraction item = new eAttraction();
            item.setId(id);
            item.setInvisible(status);
            item.setDef(def);
            item.setName(name);
            item.setAddress(address);
            boolean b = hotel.editItem(item, LangID);
            break;
        }

        case 4: // '\004'
        {
            System.out.println("inesert eAttation");
            int id = -1;
            String name = "";
            String def = "";
            int status = 1;
            String address = "";
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString().trim();
            if(request.getParameter("des") != null)
            {
                def = request.getParameter("des").toString();
                def = def.replaceAll("<strong>", "<b>");
                def = def.replaceAll("</strong>", "</b>");
                def = def.replaceAll("<em>", "<i>");
                def = def.replaceAll("</em>", "</i>");
                def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
                def = def.replaceAll("</span>", "</u>");
            }
            if(request.getParameter("status") != null)
                status = Integer.parseInt(request.getParameter("status").toString().trim());
            if(request.getParameter("addres") != null)
                address = request.getParameter("addres").toString().trim();
            LocalAttractionPMS hotel = new LocalAttractionPMS();
            eAttraction item = new eAttraction();
            item.setInvisible(status);
            item.setDef(def);
            item.setName(name);
            item.setAddress(address);
            int b = hotel.addItem(id, item);
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
        LocalAttractionPMS hotel = new LocalAttractionPMS();
        Vector subject = hotel.getMenus(LangID);
        String mData = "";
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml>").toString();
        for(int i = 0; i < subject.size(); i++)
        {
            eMenu item = (eMenu)subject.get(i);
            if(checkFlag(item.getMenuId()))
            {
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
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private String getMainMenuHotel()
    {
        List list = new ArrayList();
        list = pmsServiceDBI.getMainMenu(8, LangID);
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

    public boolean checkFlag(int id)
    {
        boolean flag = true;
        Properties prop = new Properties();
        try
        {
            prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        String list = prop.getProperty("ehotel.subject.attraction.remove");
        String listStr[] = list.split(",");
        int listInt[] = new int[listStr.length];
        for(int i = 0; i < listStr.length; i++)
            listInt[i] = Integer.parseInt(listStr[i]);

        if(listInt.length == 0)
            return true;
        for(int j = 0; j < listInt.length; j++)
            if(id != listInt[j])
                flag = true;
            else
                return false;

        return flag;
    }

    private String getContent(int subjId, int index, int page)
    {
        LocalAttractionPMS hotel = new LocalAttractionPMS();
        int fr = index * page;
        fr++;
        int to = (index + 1) * page;
        Vector info = hotel.getItems(subjId, LangID, fr, to);
        int count = hotel.countItem(subjId);
        String mData = "";
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml count=\"").append(count).append("\">").toString();
        for(int i = 0; i < info.size(); i++)
        {
            eAttraction item = (eAttraction)info.get(i);
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getName()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getId()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<Des>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(UtilString.converString(item.getDef())).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Des>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getInvisible()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private static final long serialVersionUID = 1L;
    private PMSServiceDBI pmsServiceDBI;
    private TransferImageFLC transferImageFLC;
}
