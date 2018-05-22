package ehotel.admin.pms;

import ehotel.abs.pms.DiningPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.PMSServiceDBI;
import ehotel.admin.model.PMSMainMenu;
import ehotel.admin.util.*;
import ehotel.domain.pms.*;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceDinning extends ServiceParent
{

    public ServiceDinning()
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
            request.setAttribute("fileJSP", "../pmsMng/Dinning/Dinning.jsp");
            showJSPpage(request, response, "/include/Mainpage.jsp");
            break;
        }

        case 1: // '\001'
        {
            System.out.println("Get subject Ctn");
            response.setContentType("text/xml");
            String st = getmenu();
            System.out.println(st);
            out.print(st);
            break;
        }

        case 2: // '\002'
        {
            System.out.println("Get ");
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
            System.out.println("Delete subject dinning");
            int id = -1;
            if(request.getParameter("SubId") != null)
                id = Integer.parseInt(request.getParameter("SubId").toString().trim());
            DiningPMS ding = new DiningPMS();
            ding.removeDiningMenu(id);
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
            showJSPpage(request, response, "/pmsMng/Dinning/detaiDinning.jsp");
            break;
        }

        case 5: // '\005'
        {
            System.out.println("Delete item dinning");
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

        case 6: // '\006'
        {
            System.out.println("Get item restaurance");
            response.setContentType("text/xml");
            int subId = -1;
            if(request.getParameter("SubId") != null)
                subId = Integer.parseInt(request.getParameter("SubId").toString().trim());
            String st = getItemRestaurant(subId);
            out.print(st);
            break;
        }

        case 7: // '\007'
        {
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            DiningPMS ding = new DiningPMS();
            eRestaurant item = new eRestaurant();
            item = ding.getResItemInfo(id, LangID);
            request.setAttribute("Item", item);
            showJSPpage(request, response, "/pmsMng/Dinning/detaiRestaurant.jsp");
            break;
        }

        case 9: // '\t'
        {
            System.out.println("Delete item dinning");
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
            System.out.println((new StringBuilder("Change status res=")).append(id).toString());
            DiningPMS ding = new DiningPMS();
            boolean t = ding.changeStatus(id);
            break;
        }

        case 11: // '\013'
        {
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            System.out.println((new StringBuilder("Change status din=")).append(id).toString());
            DiningPMS ding = new DiningPMS();
            boolean t = ding.changeItemStatus(id);
            break;
        }

        case 12: // '\f'
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
                flag = pmsServiceDBI.updateInvisibleDiningMenu(subId, 1);
            else
            if(invisible == 1)
                flag = pmsServiceDBI.updateInvisibleDiningMenu(subId, 0);
            if(flag)
                out.write("t");
            else
                out.write("f");
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
            System.out.println("insert new subject dining");
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
                int id = dinning.addDiningMenu(parent, menu);
                if(id > 0)
                {
                    ManagerFile file = new ManagerFile();
                    String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                    String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._dining).append("/").append(image).toString();
                    file.copy(path1, path2);
                    transferImageFLC.transferImageFLC23(path1, config._dining);
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
            System.out.println("Upadte subject dinning");
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
                transferImageFLC.transferImageFLC23(path1, config._dining);
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
            System.out.println("Insert item reataurant");
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
            System.out.println("Before insert subject hiden = = = = = = = = = = =");
            subid = intsertSubjectDining(name, image, image, -1);
            System.out.println((new StringBuilder("Subject hiden = = = = = = = = = ")).append(subid).toString());
            eItem item = new eItem();
            item.setName(name);
            item.setCurrency(currency);
            item.setCurrency_large(price_large);
            item.setCurrency_small(currency_small);
            item.setIUnit(unit);
            item.setUrlImage((new StringBuilder(String.valueOf(config._dining))).append("/").append(image).toString());
            item.setDef(def);
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
            System.out.println("update item dinning");
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
            System.out.println((new StringBuilder("Unit:")).append(unit).toString());
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
            ManagerFile file = new ManagerFile();
            String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
            String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._dining).append("/").append(image).toString();
            file.copy(path1, path2);
            transferImageFLC.transferImageFLC23(path1, config._dining);
            file.deletefile(path1);
            break;
        }

        case 5: // '\005'
        {
            System.out.println("Insert item restaurant");
            String name = "";
            String image = "";
            String def = "";
            int subid = -1;
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
            if(request.getParameter("des") != null)
                def = request.getParameter("des").toString().trim();
            if(request.getParameter("subid") != null)
                subid = Integer.parseInt(request.getParameter("subid").toString().trim());
            int id = intsertSubjectDining(name, image, image, subid);
            def = def.replaceAll("<strong>", "<b>");
            def = def.replaceAll("</strong>", "</b>");
            def = def.replaceAll("<em>", "<i>");
            def = def.replaceAll("</em>", "</i>");
            def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
            def = def.replaceAll("</span>", "</u>");
            DiningPMS dinning = new DiningPMS();
            eRestaurant item = new eRestaurant();
            item.setDef(def);
            item.setName(name);
            item.setUrlImage((new StringBuilder(String.valueOf(config._dining))).append("/").append(image).toString());
            int t = dinning.addRestaurantItem(item, id);
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

        case 6: // '\006'
        {
            System.out.println("update item restaurant");
            String name = "";
            String image = "";
            String def = "";
            int id = -1;
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
            if(request.getParameter("des") != null)
                def = request.getParameter("des").toString().trim();
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            def = def.replaceAll("<strong>", "<b>");
            def = def.replaceAll("</strong>", "</b>");
            def = def.replaceAll("<em>", "<i>");
            def = def.replaceAll("</em>", "</i>");
            def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
            def = def.replaceAll("</span>", "</u>");
            DiningPMS dinning = new DiningPMS();
            eRestaurant item = new eRestaurant();
            item.setDef(def);
            item.setName(name);
            item.setUrlImage((new StringBuilder(String.valueOf(config._dining))).append("/").append(image).toString());
            item.setId(id);
            boolean b = dinning.editRestaurantItem(item, LangID);
            if(b)
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

        case 7: // '\007'
        {
            System.out.println("Insert item Dining");
            int menuid = -1;
            String name = "";
            String def = "";
            String price = "";
            String price_large = "";
            String price_small = "";
            String url = "";
            String unit = "";
            menuid = Integer.parseInt(request.getParameter("subid"));
            name = request.getParameter("name");
            def = request.getParameter("des");
            def = def.replaceAll("<strong>", "<b>");
            def = def.replaceAll("</strong>", "</b>");
            def = def.replaceAll("<em>", "<i>");
            def = def.replaceAll("</em>", "</i>");
            def = def.replaceAll("<span style=\"text-decoration: underline;\">", "<u>");
            def = def.replaceAll("</span>", "</u>");
            price = request.getParameter("price");
            price_large = request.getParameter("price_large");
            price_small = request.getParameter("price_small");
            url = request.getParameter("image");
            url = (new StringBuilder(String.valueOf(config._dining))).append("/").append(url).toString();
            unit = request.getParameter("unit");
            PMSServiceDBI pmsServiceDBI = new PMSServiceDBI();
            int t = pmsServiceDBI.addItemDining(menuid, name, def, price, price_large, price_small, url, unit);
            if(t > 0)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(url).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._dining).append("/").append(url).toString();
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
        DiningPMS hotel = new DiningPMS();
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
            mData = (new StringBuilder(String.valueOf(mData))).append("<type>").toString();
            if(item.getType().equals("RESTAURANT"))
                mData = (new StringBuilder(String.valueOf(mData))).append(0).toString();
            if(item.getType().equals("ROOMSERVICE"))
                mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(0).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<invisible>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</invisible>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
            if(item.getType().equals("RESTAURANT"))
                mData = (new StringBuilder(String.valueOf(mData))).append(getRestaurantMenu(item.getMenuId())).toString();
            if(item.getType().equals("ROOMSERVICE"))
                mData = (new StringBuilder(String.valueOf(mData))).append(getInRoomMenu(item.getMenuId())).toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private String getRestaurantMenu(int menuid)
    {
        List list = new ArrayList();
        list = pmsServiceDBI.getMenuDining(menuid, LangID);
        String mData = "";
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
            mData = (new StringBuilder(String.valueOf(mData))).append(menuid).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(0).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<invisible>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(((PMSMainMenu)list.get(i)).getMenuinvisible()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</invisible>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        return mData;
    }

    private String getInRoomMenu(int menuid)
    {
        List list = new ArrayList();
        list = pmsServiceDBI.getMenuDining(menuid, LangID);
        String mData = "";
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
            mData = (new StringBuilder(String.valueOf(mData))).append(menuid).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(0).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<invisible>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(((PMSMainMenu)list.get(i)).getMenuinvisible()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</invisible>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
            List item = new ArrayList();
            int id = Integer.parseInt(((PMSMainMenu)list.get(i)).getMenuid());
            item = pmsServiceDBI.getMenuDining(id, LangID);
            for(int j = 0; j < item.size(); j++)
            {
                mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(((PMSMainMenu)item.get(j)).getMenuname()).append("]]>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(((PMSMainMenu)item.get(j)).getMenuid()).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</id>\n").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<image>\n").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(((PMSMainMenu)item.get(j)).getMenuimage()).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<imagebg>\n").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(((PMSMainMenu)item.get(j)).getMenubackground()).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</imagebg>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<parent>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(id).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</parent>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<type>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<level>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</level>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<invisible>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(((PMSMainMenu)item.get(j)).getMenuinvisible()).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</invisible>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
            }

        }

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
            mData = (new StringBuilder(String.valueOf(mData))).append(0).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        return mData;
    }

    private String getsubmenu2(int menuid)
    {
        DiningPMS hotel = new DiningPMS();
        String mData = "";
        Vector subject = hotel.getSubMenus(menuid, LangID);
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
            mData = (new StringBuilder(String.valueOf(mData))).append(menuid).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</parent>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(0).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</level>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
            Vector submenu = new Vector();
            submenu = hotel.getSubMenus(item.getMenuId(), LangID);
            for(int j = 0; j < submenu.size(); j++)
            {
                eMenu item1 = (eMenu)submenu.get(j);
                mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item1.getMenuName()).append("]]>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(item1.getMenuId()).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</id>\n").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<image>\n").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(item1.getUrlImage()).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<imagebg>\n").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(item1.getUrlBg()).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</imagebg>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<parent>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(item.getMenuId()).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</parent>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<type>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</type>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<level>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(1).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</level>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
            }

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

    private String getItemRestaurant(int id)
    {
        DiningPMS dinning = new DiningPMS();
        String mData = "";
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml>").toString();
        Vector v_rs = dinning.getRestaurantItems(id, LangID, -1, -1);
        for(int i = 0; i < v_rs.size(); i++)
        {
            eRestaurant item = (eRestaurant)v_rs.get(i);
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
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(UtilString.converString(item.getDef())).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Def>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getInvisible()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getUrlImage()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    public int intsertSubjectDining(String subjectName, String image, String urlBg, int parent)
    {
        ConfigLoader loader = new ConfigLoader();
        Config config = loader.getConfig();
        DiningPMS dinning = new DiningPMS();
        eMenu menu = new eMenu();
        menu.setMenuName(subjectName);
        menu.setUrlImage((new StringBuilder(String.valueOf(config._dining))).append("/").append(image).toString());
        menu.setUrlBg((new StringBuilder(String.valueOf(config._dining))).append("/").append(urlBg).toString());
        int id = dinning.addDiningMenu(parent, menu);
        if(id > 0)
        {
            ManagerFile file = new ManagerFile();
            String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
            String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._dining).append("/").append(image).toString();
            file.copy(path1, path2);
            transferImageFLC.transferImageFLC23(path1, config._dining);
            file.deletefile(path1);
            path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(urlBg).toString();
            path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._dining).append("/").append(urlBg).toString();
            file.copy(path1, path2);
            transferImageFLC.transferImageFLC23(path1, config._dining);
            file.deletefile(path1);
        }
        return id;
    }

    public static void main(String args[])
    {
        ServiceDinning s = new ServiceDinning();
        System.out.println(s.getmenu());
    }

    private static final long serialVersionUID = 1L;
    private PMSServiceDBI pmsServiceDBI;
    private TransferImageFLC transferImageFLC;
}
