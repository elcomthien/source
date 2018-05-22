package ehotel.admin.pms;

import com.elcom.eod.util.UnicodeConverter;
import ehotel.abs.pms.HotelInfoPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.FolioServiceDBI;
import ehotel.admin.dbi.PMSServiceDBI;
import ehotel.admin.model.PMSMainMenu;
import ehotel.admin.util.*;
import ehotel.domain.pms.eImage;
import ehotel.domain.pms.eMenu;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceHotel extends ServiceParent
{

    public ServiceHotel()
    {
        folioServiceDBI = new FolioServiceDBI();
        pmsServiceDBI = new PMSServiceDBI();
        transferImageFLC = new TransferImageFLC();
    }

    public void destroy()
    {
        super.destroy();
    }

    @SuppressWarnings({ "rawtypes", "unused", "unchecked" })
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
            request.setAttribute("fileJSP", "../pmsMng/hotel/pmsHotel.jsp");
            showJSPpage(request, response, "/include/Mainpage.jsp");
            break;
        }

        case 1: // '\001'
        {
            System.out.println("Get subject Ctn");
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
            String st = getContentHotel(id, index, page);
            out.print(st);
            break;
        }

        case 3: // '\003'
        {
            System.out.println("DELETE SUBJECT PMS");
            HotelInfoPMS hotel = new HotelInfoPMS();
            int subid = -1;
            if(request.getParameter("SubId") != null)
                subid = Integer.parseInt(request.getParameter("SubId").toString());
            boolean b = hotel.removeHotelMenu(subid);
            System.out.println((new StringBuilder("Delete :")).append(b).toString());
            break;
        }

        case 4: // '\004'
        {
            System.out.println("Show form detail");
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString());
            HotelInfoPMS hotel = new HotelInfoPMS();
            eImage item = null;
            item = hotel.getItemInfo(id, LangID);
            request.setAttribute("eImage", item);
            showJSPpage(request, response, "/pmsMng/hotel/detailHotel.jsp");
            break;
        }

        case 5: // '\005'
        {
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            response.setContentType("text/xml");
            String str = getSuboutHotel(id);
            out.print(str);
            break;
        }

        case 6: // '\006'
        {
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            response.setContentType("text/xml");
            String str = getSubinHotel(id);
            out.print(str);
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
            HotelInfoPMS hotel = new HotelInfoPMS();
            String param = "(";
            for(i = 0; i < list.size(); i++)
                param = (new StringBuilder(String.valueOf(param))).append(list.get(i)).append(",").toString();

            param = (new StringBuilder(String.valueOf(param.substring(0, param.length() - 1)))).append(")").toString();
            System.out.println(param);
            hotel.removeItem(param);
            break;
        }

        case 9: // '\t'
        {
            System.out.println("change status hotel ");
            int id = -1;
            HotelInfoPMS hotel = new HotelInfoPMS();
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            hotel.changeStatus(id);
            break;
        }

        case 10: // '\n'
        {
            System.out.println("change status invisible");
            int subId = -1;
            int invisible = -1;
            if(request.getParameter("subid") != null)
                subId = Integer.parseInt(request.getParameter("subid").toString());
            if(request.getParameter("invisible") != null)
                invisible = Integer.parseInt(request.getParameter("invisible").toString());
            if(invisible == -1)
            {
                out.write("f");
                break;
            }
            boolean flag = true;
            if(invisible == 0)
                flag = pmsServiceDBI.updateInvisibleMainMenu(subId, 1);
            else
            if(invisible == 1)
                flag = pmsServiceDBI.updateInvisibleMainMenu(subId, 0);
            if(flag)
                out.write("t");
            else
                out.write("f");
            break;
        }
        }
    }

    @SuppressWarnings("unused")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        super.doPost(request, response);
        response.setContentType("text/xml");
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
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
            System.out.println("inser subject PMS!");
            response.setContentType("text/xml");
            String subjectName = "";
            String image = "";
            String urlBg = "";
            if(request.getParameter("image1") != null)
                image = request.getParameter("image1").toString();
            if(request.getParameter("image2") != null)
                urlBg = request.getParameter("image2").toString();
            if(request.getParameter("name") != null)
            {
                subjectName = request.getParameter("name").toString();
                HotelInfoPMS hotel = new HotelInfoPMS();
                eMenu menu = new eMenu();
                menu.setMenuName(subjectName);
                menu.setUrlImage((new StringBuilder(String.valueOf(config._hotel))).append("/").append(image).toString());
                menu.setUrlBg((new StringBuilder(String.valueOf(config._hotel))).append("/").append(urlBg).toString());
                int id = hotel.addHotelMenu(menu);
                if(id > 0)
                {
                    ManagerFile file = new ManagerFile();
                    String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                    String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._hotel).append("/").append(image).toString();
                    file.copy(path1, path2);
                    transferImageFLC.transferImageFLC23(path1, config._hotel);
                    file.deletefile(path1);
                    path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(urlBg).toString();
                    path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._hotel).append("/").append(urlBg).toString();
                    file.copy(path1, path2);
                    transferImageFLC.transferImageFLC23(path1, config._hotel);
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
            HotelInfoPMS hotel = new HotelInfoPMS();
            eMenu menu = new eMenu();
            menu.setMenuName(subjectName);
            menu.setMenuId(subjId);
            menu.setUrlImage((new StringBuilder(String.valueOf(config._hotel))).append("/").append(image).toString());
            menu.setUrlBg((new StringBuilder(String.valueOf(config._hotel))).append("/").append(bgimage).toString());
            boolean t = hotel.editHotelMenu(menu, LangID);
            System.out.println((new StringBuilder("UPDATE SUBJECT PMS:")).append(t).toString());
            if(t)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._hotel).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._hotel);
                file.deletefile(path1);
                path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(bgimage).toString();
                path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._hotel).append("/").append(bgimage).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._hotel);
                file.deletefile(path1);
            }
            break;
        }

        case 3: // '\003'
        {
            System.out.println("Update eImage");
            int id = -1;
            String name = "";
            String image = "";
            String def = "";
            int status = 1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString().trim();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
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
            HotelInfoPMS hotel = new HotelInfoPMS();
            eImage item = new eImage();
            item.setId(id);
            item.setInvisible(status);
            item.setUrlImage((new StringBuilder(String.valueOf(config._hotel))).append("/").append(image).toString());
            item.setDef(def);
            item.setName(name);
            boolean b = hotel.editItem(item, LangID);
            if(b)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._hotel).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._hotel);
                file.deletefile(path1);
            }
            break;
        }

        case 4: // '\004'
        {
            System.out.println("insert eImage");
            int id = -1;
            String name = "";
            String image = "";
            String def = "";
            int status = 1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString().trim();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
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
            HotelInfoPMS hotel = new HotelInfoPMS();
            int b = hotel.addItemHotel(id, name, def, (new StringBuilder(String.valueOf(config._hotel))).append("/").append(image).toString(), "");
            if(b > 0)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._hotel).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._hotel);
                file.deletefile(path1);
            }
            break;
        }

        case 5: // '\005'
        {
            System.out.println("Save message");
            int folionum = -1;
            String sender = "";
            String subject = "";
            String content = "";
            if(request.getParameter("folionum") != null)
                folionum = Integer.parseInt(request.getParameter("folionum").toString().trim());
            sender = request.getParameter("sender").toString().trim();
            sender = UnicodeConverter.encodeUnicode(sender);
            subject = request.getParameter("subject");
            subject = UnicodeConverter.encodeUnicode(subject);
            content = request.getParameter("content").toString().trim();
            content = UnicodeConverter.encodeUnicode(content);
            System.out.println((new StringBuilder(String.valueOf(sender))).append(" ").append(subject).append(" ").append(content).toString());
            boolean flag = folioServiceDBI.insertMessage(folionum, subject, content, sender);
            System.out.println(flag);
            break;
        }

        case 6: // '\006'
        {
            System.out.println("Check in for room");
            int folionum = Integer.parseInt(request.getParameter("folionum").trim());
            String guest = request.getParameter("guest");
            int amount = Integer.parseInt(request.getParameter("amount"));
            String checkindate = convertDay(request.getParameter("checkindate"));
            String checkoutdate = convertDay(request.getParameter("checkoutdate"));
            String array[] = guest.split(";");
            int checkmainguest = folioServiceDBI.checkExistsMainGuest(folionum);
            if(checkmainguest == 0)
            {
                for(int i = 0; i < amount; i++)
                {
                    String arr[] = array[i].split(",");
                    String temp1 = UnicodeConverter.encodeUnicode(arr[0]);
                    String temp2 = UnicodeConverter.encodeUnicode(arr[1]);
                    if(i == 0)
                        folioServiceDBI.CheckIn(folionum, temp1, temp2, arr[2], checkindate, checkoutdate, 0);
                    else
                        folioServiceDBI.CheckIn(folionum, temp1, temp2, arr[2], checkindate, checkoutdate, 1);
                }

                break;
            }
            for(int i = 0; i < amount; i++)
            {
                String arr[] = array[i].split(",");
                String temp1 = UnicodeConverter.encodeUnicode(arr[0]);
                String temp2 = UnicodeConverter.encodeUnicode(arr[1]);
                folioServiceDBI.CheckIn(folionum, temp1, temp2, arr[2], checkindate, checkoutdate, 1);
            }

            break;
        }
        }
    }

    public void init()
        throws ServletException
    {
    }

    @SuppressWarnings({ "rawtypes", "unused" })
	private String getsub()
    {
        HotelInfoPMS hotel = new HotelInfoPMS();
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

    @SuppressWarnings("rawtypes")
	private String getMainMenuHotel()
    {
        List list = new ArrayList();
        list = pmsServiceDBI.getMainMenu(1, LangID);
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

    @SuppressWarnings("rawtypes")
	private String getContentHotel(int subjId, int index, int page)
    {
        HotelInfoPMS hotel = new HotelInfoPMS();
        int fr = index * page;
        fr++;
        int to = (index + 1) * page;
        Vector info = hotel.getItemsOfHotelInfo(subjId, LangID, fr, to);
        int count = hotel.countItem(subjId);
        String mData = "";
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml count=\"").append(count).append("\">").toString();
        for(int i = 0; i < info.size(); i++)
        {
            eImage item = (eImage)info.get(i);
            String t = "";
            if(item.getDef() != null)
                t = UtilString.converString(item.getDef());
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getName()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getId()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<Des>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(t).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Des>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getInvisible()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private String getSubinHotel(int itemId)
    {
        HotelInfoPMS hotel = new HotelInfoPMS();
        Vector menu = hotel.getSubjectsInItem(itemId, LangID);
        String mData = "";
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml>").toString();
        for(int i = 0; i < menu.size(); i++)
        {
            eMenu item = (eMenu)menu.get(i);
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
            mData = (new StringBuilder(String.valueOf(mData))).append("<parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(-1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private String getSuboutHotel(int itemId)
    {
        HotelInfoPMS hotel = new HotelInfoPMS();
        Vector menu = hotel.getSubjectsOutItem(itemId, LangID);
        String mData = "";
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml>").toString();
        for(int i = 0; i < menu.size(); i++)
        {
            eMenu item = (eMenu)menu.get(i);
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
            mData = (new StringBuilder(String.valueOf(mData))).append("<parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(-1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    public String convertDay(String str)
    {
        if(str.equals(""))
            return "";
        String arr[] = str.split(" ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = arr[0];
        String result = "";
        try
        {
            java.util.Date date = formatter.parse(dateInString);
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
            result = format.format(date);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String args[])
    {
        ServiceHotel s = new ServiceHotel();
        System.out.println(s.getMainMenuHotel());
    }

    private static final long serialVersionUID = 1L;
    private FolioServiceDBI folioServiceDBI;
    private PMSServiceDBI pmsServiceDBI;
    private TransferImageFLC transferImageFLC;
}
