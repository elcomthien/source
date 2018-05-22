package ehotel.admin.pms;

import ehotel.abs.pms.DiningPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.*;
import ehotel.domain.pms.eItem;
import ehotel.domain.pms.eMenu;
import java.io.*;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PmsShopping extends ServiceParent
{

    public PmsShopping()
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
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
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
            request.setAttribute("fileJSP", "../pmsMng/Shopping/Shopping.jsp");
            showJSPpage(request, response, "/include/Mainpage.jsp");
            break;
        }

        case 1: // '\001'
        {
            System.out.println("Get subject Shopping");
            response.setContentType("text/xml");
            String st = getmenu();
            out.print(st);
            break;
        }

        case 2: // '\002'
        {
            System.out.println("Get item");
            response.setContentType("text/xml");
            int subId = -1;
            if(request.getParameter("SubId") != null)
                subId = Integer.parseInt(request.getParameter("SubId").toString().trim());
            String st = getItemRoomService(subId);
            out.print(st);
            break;
        }

        case 3: // '\003'
        {
            System.out.println("Delete subject shopping");
            int id = -1;
            if(request.getParameter("SubId") != null)
                id = Integer.parseInt(request.getParameter("SubId").toString().trim());
            DiningPMS shopping = new DiningPMS();
            shopping.removeDiningMenu(id);
            break;
        }

        case 4: // '\004'
        {
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            DiningPMS ding = new DiningPMS();
            eItem item = new eItem();
            item = ding.getRoomSvcItemInfo(id, LangID);
            request.setAttribute("Item", item);
            showJSPpage(request, response, "/pmsMng/Shopping/detaiShopping.jsp");
            break;
        }

        case 5: // '\005'
        {
            System.out.println("Delete item shopping");
            Vector list = new Vector();
            for(int i = 0; request.getParameter((new StringBuilder("id")).append(i).toString()) != null; i++)
            {
                int subid = Integer.parseInt(request.getParameter((new StringBuilder("id")).append(i).toString()).toString().trim());
                list.add(Integer.valueOf(subid));
            }

            DiningPMS ding = new DiningPMS();
            for(int i = 0; i < list.size(); i++)
            {
                System.out.println((new StringBuilder("ID:")).append(list.get(i)).toString());
                ding.removeRoomSvcItem(((Integer)list.get(i)).intValue());
            }

            break;
        }

        case 9: // '\t'
        {
            System.out.println("Delete item shopping");
            Vector list = new Vector();
            for(int i = 0; request.getParameter((new StringBuilder("id")).append(i).toString()) != null; i++)
            {
                int subid = Integer.parseInt(request.getParameter((new StringBuilder("id")).append(i).toString()).toString().trim());
                list.add(Integer.valueOf(subid));
            }

            DiningPMS ding = new DiningPMS();
            String param = "(";
            for(int i = 0; i < list.size(); i++)
                param = (new StringBuilder(String.valueOf(param))).append(list.get(i)).append(",").toString();

            param = (new StringBuilder(String.valueOf(param.substring(0, param.length() - 1)))).append(")").toString();
            ding.removeRestaurantItem(param);
            break;
        }

        case 10: // '\n'
        {
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            System.out.println((new StringBuilder("changeItemStatus shopping itemid=")).append(id).toString());
            DiningPMS ding = new DiningPMS();
            ding.changeItemStatus(id);
            break;
        }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        super.doGet(request, response);
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
            response.setContentType("text/xml");
            String subjectName = "";
            String image = "";
            String urlBg = "";
            int parent = -1;
            if(request.getParameter("image1") != null)
                image = request.getParameter("image1").toString();
            if(request.getParameter("image2") != null)
                urlBg = request.getParameter("image2").toString();
            if(request.getParameter("id") != null)
                parent = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("name") != null)
            {
                subjectName = request.getParameter("name").toString();
                DiningPMS dinning = new DiningPMS();
                eMenu menu = new eMenu();
                menu.setMenuName(subjectName);
                menu.setUrlImage((new StringBuilder(String.valueOf(config._dining))).append("/").append(image).toString());
                menu.setUrlBg((new StringBuilder(String.valueOf(config._dining))).append("/").append(urlBg).toString());
                menu.setParentId(22);
                System.out.println((new StringBuilder("insert new subject shopping: parent=")).append(parent).toString());
                int id = dinning.addDiningMenu(parent, menu);
                if(id > 0)
                {
                    ManagerFile file = new ManagerFile();
                    String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                    String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._dining).append("/").append(image).toString();
                    file.copy(path1, path2);
                    file.deletefile(path1);
                    path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(urlBg).toString();
                    path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._dining).append("/").append(urlBg).toString();
                    file.copy(path1, path2);
                    transferImageFLC.transferImageFLC23(path1, config._dining);
                    file.deletefile(path1);
                }
                out.write(id);
            }
            break;
        }

        case 2: // '\002'
        {
            System.out.println("Update subject shopping");
            response.setContentType("text/xml");
            String subjectName = "";
            String image = "";
            String urlBg = "";
            int id = -1;
            if(request.getParameter("image1") != null)
                image = request.getParameter("image1").toString();
            if(request.getParameter("image2") != null)
                urlBg = request.getParameter("image2").toString();
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("name") == null)
                break;
            subjectName = request.getParameter("name").toString();
            DiningPMS dinning = new DiningPMS();
            eMenu menu = new eMenu();
            menu.setMenuName(subjectName);
            menu.setUrlImage((new StringBuilder(String.valueOf(config._dining))).append("/").append(image).toString());
            menu.setUrlBg((new StringBuilder(String.valueOf(config._dining))).append("/").append(urlBg).toString());
            menu.setMenuId(id);
            boolean t = dinning.editDiningMenu(menu, LangID);
            if(t)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._dining).append("/").append(image).toString();
                file.copy(path1, path2);
                file.deletefile(path1);
                path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(urlBg).toString();
                path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._dining).append("/").append(urlBg).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._dining);
                file.deletefile(path1);
            }
            break;
        }

        case 3: // '\003'
        {
            System.out.println("Insert item shopping");
            String name = "";
            String currency = "";
            String currency_small = "";
            String price_large = "";
            String unit = "";
            int subid = -1;
            String image = "";
            String def = "";
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString();
            if(request.getParameter("price") != null)
                currency = request.getParameter("price").toString().trim();
            if(request.getParameter("price_small") != null)
                currency_small = request.getParameter("price_small").toString().trim();
            if(request.getParameter("price_large") != null)
                price_large = request.getParameter("price_large").toString().trim();
            if(request.getParameter("unit") != null)
                unit = request.getParameter("unit").toString();
            if(request.getParameter("subid") != null)
                subid = Integer.parseInt(request.getParameter("subid").toString().trim());
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
            if(request.getParameter("des") != null)
            {
                def = request.getParameter("des").toString().trim();
                def = def.replaceAll("<strong>", "<b>");
                def = def.replaceAll("</strong>", "</b>");
                def = def.replaceAll("<em>", "<i>");
                def = def.replaceAll("</em>", "</i>");
                def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
                def = def.replaceAll("</span>", "</u>");
            }
            eItem item = new eItem();
            item.setName(name);
            item.setCurrency(currency);
            item.setCurrency_large(price_large);
            item.setCurrency_small(currency_small);
            item.setIUnit(unit);
            item.setUrlImage((new StringBuilder(String.valueOf(config._dining))).append("/").append(image).toString());
            item.setDef(def);
            System.out.println((new StringBuilder("insert def=")).append(def).toString());
            DiningPMS dinning = new DiningPMS();
            int t = dinning.addRoomSvcItem(item, subid);
            if(t > 0)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._dining).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._dining);
                file.deletefile(path1);
            }
            break;
        }

        case 4: // '\004'
        {
            System.out.println("update item shopping");
            String name = "";
            String currency = "";
            String currency_small = "";
            String price_large = "";
            String unit = "";
            String image = "";
            String def = "";
            int id = -1;
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString();
            if(request.getParameter("price") != null)
                currency = request.getParameter("price").toString().trim();
            if(request.getParameter("price_small") != null)
                currency_small = request.getParameter("price_small").toString().trim();
            if(request.getParameter("price_large") != null)
                price_large = request.getParameter("price_large").toString().trim();
            if(request.getParameter("unit") != null)
                unit = request.getParameter("unit").toString();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
            if(request.getParameter("des") != null)
            {
                def = request.getParameter("des").toString().trim();
                def = def.replaceAll("<strong>", "<b>");
                def = def.replaceAll("</strong>", "</b>");
                def = def.replaceAll("<em>", "<i>");
                def = def.replaceAll("</em>", "</i>");
                def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
                def = def.replaceAll("</span>", "</u>");
            }
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            System.out.println((new StringBuilder("update def=")).append(def).toString());
            eItem item = new eItem();
            item.setName(name);
            item.setCurrency(currency);
            item.setCurrency_large(price_large);
            item.setCurrency_small(currency_small);
            item.setIUnit(unit);
            item.setUrlImage((new StringBuilder(String.valueOf(config._dining))).append("/").append(image).toString());
            item.setDef(def);
            item.setICode(id);
            DiningPMS dinning = new DiningPMS();
            boolean t = dinning.editRoomSvcItem(item, LangID);
            if(t)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._dining).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._dining);
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

    private String getmenu()
    {
        String mData = "";
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml>").toString();
        if(LangID == 1)
        {
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[Mua s\u1EAFm]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(22).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<image>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("Local/Shopping.png").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<imagebg>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("Local/bg/Shopping_bg.png").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</imagebg>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(-1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(0).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(getsubmenu1(22)).toString();
        } else
        {
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[Shopping]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(22).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<image>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("Local/Shopping.png").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<imagebg>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("Local/bg/Shopping_bg.png").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</imagebg>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(-1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(0).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(getsubmenu1(22)).toString();
        }
        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private String getsubmenu1(int menuid)
    {
        DiningPMS hotel = new DiningPMS();
        String mData = "";
        Vector menu = hotel.getSubMenus(menuid, LangID);
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
            mData = (new StringBuilder(String.valueOf(mData))).append("<imagebg>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getUrlBg()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</imagebg>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(menuid).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        return mData;
    }

    private String getItemRoomService(int id)
    {
        DiningPMS dinning = new DiningPMS();
        String mData = "";
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml>").toString();
        Vector v_rs = dinning.getRoomSvcItems(id, LangID, -1, -1);
        for(int i = 0; i < v_rs.size(); i++)
        {
            eItem item = (eItem)v_rs.get(i);
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getName()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getICode()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<Def>").toString();
            if(item.getDef() == null)
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[]]>").toString();
            else
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(UtilString.converString(item.getDef())).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Def>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getInvisible()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getUrlImage()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<price>").toString();
            if(item.getCurrency() != null)
                mData = (new StringBuilder(String.valueOf(mData))).append(item.getCurrency()).toString();
            else
                mData = (new StringBuilder(String.valueOf(mData))).append(0).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</price>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private static final long serialVersionUID = 1L;
    private TransferImageFLC transferImageFLC;
}
