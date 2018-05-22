package ehotel.admin.pms;

import ehotel.abs.pms.PromotionPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.*;
import ehotel.domain.pms.ePromotion;
import java.io.*;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServicePromotion extends ServiceParent
{

    public ServicePromotion()
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
            request.setAttribute("fileJSP", "../pmsMng/promotion/promotion.jsp");
            showJSPpage(request, response, "/include/Mainpage.jsp");
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

        case 2: // '\002'
        {
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            PromotionPMS promtion = new PromotionPMS();
            ePromotion item = promtion.getPromotionInfo(id, LangID);
            request.setAttribute("eItem", item);
            showJSPpage(request, response, "/pmsMng/Other/detailPromotion.jsp");
            break;
        }

        case 3: // '\003'
        {
            int id = -1;
            int i = 0;
            Vector list = new Vector();
            for(; request.getParameter((new StringBuilder("id")).append(i).toString()) != null; i++)
            {
                int subid = Integer.parseInt(request.getParameter((new StringBuilder("id")).append(i).toString()).toString().trim());
                list.add(Integer.valueOf(subid));
            }

            String param = "(";
            for(i = 0; i < list.size(); i++)
                param = (new StringBuilder(String.valueOf(param))).append(list.get(i)).append(",").toString();

            param = (new StringBuilder(String.valueOf(param.substring(0, param.length() - 1)))).append(")").toString();
            System.out.println((new StringBuilder("Delete promotion ")).append(param).toString());
            PromotionPMS promtion = new PromotionPMS();
            promtion.removePromotion(param);
            break;
        }

        case 4: // '\004'
        {
            System.out.println("Change status");
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            PromotionPMS promtion = new PromotionPMS();
            promtion.changeStatus(id);
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
            String name = "";
            String image = "";
            String def = "";
            int status = 1;
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
            PromotionPMS promotion = new PromotionPMS();
            ePromotion item = new ePromotion();
            item.setContent(def);
            item.setName(name);
            item.setUrlImage((new StringBuilder(String.valueOf(config._promotion))).append("/").append(image).toString());
            item.setInvisible(status);
            int b = promotion.addPromotion(item);
            if(b > 0)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._promotion).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._promotion);
                file.deletefile(path1);
            }
            break;
        }

        case 2: // '\002'
        {
            System.out.println("Update promotion");
            String name = "";
            String image = "";
            String def = "";
            int status = 1;
            int id = -1;
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
            PromotionPMS promotion = new PromotionPMS();
            ePromotion item = new ePromotion();
            item.setId(id);
            item.setContent(def);
            item.setName(name);
            item.setUrlImage((new StringBuilder(String.valueOf(config._promotion))).append("/").append(image).toString());
            item.setInvisible(status);
            boolean b = promotion.editPromotion(item, LangID);
            if(b)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._promotion).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._promotion);
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
        PromotionPMS promotion = new PromotionPMS();
        int fr = index * page;
        fr++;
        int to = (index + 1) * page;
        Vector info = promotion.getPromotions(LangID, fr, to);
        String mData = "";
        int count = promotion.countItem();
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml  count=\"").append(count).append("\">").toString();
        for(int i = 0; i < info.size(); i++)
        {
            ePromotion item = (ePromotion)info.get(i);
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getName()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getId()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<Des>").toString();
            if(item.getContent() != null)
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(UtilString.converString(item.getContent())).append("]]>").toString();
            else
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Des>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getInvisible()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getUrlImage()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    public static void main(String args[])
    {
        ServicePromotion s = new ServicePromotion();
        System.out.println(s.getContent(-1, -1));
    }

    private static final long serialVersionUID = 1L;
    private TransferImageFLC transferImageFLC;
}
