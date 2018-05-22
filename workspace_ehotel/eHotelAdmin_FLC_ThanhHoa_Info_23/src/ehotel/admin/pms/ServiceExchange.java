package ehotel.admin.pms;

import ehotel.abs.pms.ExchangeRatePMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.*;
import ehotel.domain.pms.eExchangeRate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceExchange extends ServiceParent
{

    public ServiceExchange()
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
        default:
            break;

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
            ExchangeRatePMS exchange = new ExchangeRatePMS();
            eExchangeRate item = exchange.getExchangeRateInfo(id);
            request.setAttribute("Item", item);
            showJSPpage(request, response, "/pmsMng/Other/detailExchange.jsp");
            break;
        }

        case 3: // '\003'
        {
            insert(request, response);
            break;
        }

        case 4: // '\004'
        {
            upadte(request, response);
            break;
        }

        case 5: // '\005'
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
            ExchangeRatePMS exchange = new ExchangeRatePMS();
            exchange.removeExchangeRate(param);
            break;
        }

        case 6: // '\006'
        {
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            ExchangeRatePMS exchange = new ExchangeRatePMS();
            exchange.changeStatus(id);
            break;
        }
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
    }

    public void init()
        throws ServletException
    {
    }

    private String getContent(int index, int page)
    {
        ExchangeRatePMS exchange = new ExchangeRatePMS();
        int fr = index * page;
        fr++;
        int to = (index + 1) * page;
        Vector info = exchange.getExchangeRates(fr, to);
        String mData = "";
        int count = exchange.countItem();
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml  count=\"").append(count).append("\">").toString();
        for(int i = 0; i < info.size(); i++)
        {
            eExchangeRate item = (eExchangeRate)info.get(i);
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<code>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getCode()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</code>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getName()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(item.getId()).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<buy>").toString();
            if(item.getBuyRate() != null)
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getBuyRate()).append("]]>").toString();
            else
                mData = (new StringBuilder(String.valueOf(mData))).append("N#A").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</buy>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<tran>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getTransferRate()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</tran>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<sel>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getSellRate()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</sel>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getUrlImage()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<icon>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getUrlIcon()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</icon>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getInvisibel()).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    private int insert(HttpServletRequest request, HttpServletResponse response)
    {
        String name = "";
        String code = "";
        String sell = "";
        String tran = "";
        String buy = "";
        String image = "";
        String icon = "";
        ConfigLoader loader = new ConfigLoader();
        Config config = loader.getConfig();
        if(request.getParameter("name") != null)
            name = request.getParameter("name").toString().trim();
        if(request.getParameter("code") != null)
            code = request.getParameter("code").toString().trim();
        if(request.getParameter("sell") != null)
            sell = request.getParameter("sell").toString().trim();
        if(request.getParameter("tran") != null)
            tran = request.getParameter("tran").toString().trim();
        if(request.getParameter("buy") != null)
            buy = request.getParameter("buy").toString().trim();
        if(request.getParameter("image") != null)
            image = request.getParameter("image").toString().trim();
        if(request.getParameter("icon") != null)
            icon = request.getParameter("icon").toString().trim();
        ExchangeRatePMS exchange = new ExchangeRatePMS();
        eExchangeRate item = new eExchangeRate();
        item.setCode(code);
        item.setName(name);
        item.setSellRate(sell);
        item.setTransferRate(tran);
        item.setBuyRate(buy);
        item.setUrlImage((new StringBuilder(String.valueOf(config._exchange))).append("/").append(image).toString());
        item.setUrlBg((new StringBuilder(String.valueOf(config._exchange))).append("/").append(icon).toString());
        int b = exchange.addExchangeRate(item);
        if(b > 0)
        {
            ManagerFile file = new ManagerFile();
            String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
            String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._exchange).append("/").append(image).toString();
            file.copy(path1, path2);
            transferImageFLC.transferImageFLC23(path1, config._exchange);
            file.deletefile(path1);
            path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(icon).toString();
            path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._exchange).append("/").append(icon).toString();
            file.copy(path1, path2);
            transferImageFLC.transferImageFLC23(path1, config._exchange);
            file.deletefile(path1);
        }
        return b;
    }

    private boolean upadte(HttpServletRequest request, HttpServletResponse response)
    {
        int id = -1;
        String name = "";
        String code = "";
        String sell = "";
        String tran = "";
        String buy = "";
        String image = "";
        String icon = "";
        ConfigLoader loader = new ConfigLoader();
        Config config = loader.getConfig();
        if(request.getParameter("id") != null)
            id = Integer.parseInt(request.getParameter("id").toString());
        if(request.getParameter("name") != null)
            name = request.getParameter("name").toString().trim();
        if(request.getParameter("code") != null)
            code = request.getParameter("code").toString().trim();
        if(request.getParameter("sell") != null)
            sell = request.getParameter("sell").toString().trim();
        if(request.getParameter("tran") != null)
            tran = request.getParameter("tran").toString().trim();
        if(request.getParameter("buy") != null)
            buy = request.getParameter("buy").toString().trim();
        if(request.getParameter("image") != null)
            image = request.getParameter("image").toString().trim();
        if(request.getParameter("icon") != null)
            icon = request.getParameter("icon").toString();
        ExchangeRatePMS exchange = new ExchangeRatePMS();
        eExchangeRate item = new eExchangeRate();
        item.setId(id);
        item.setCode(code);
        item.setName(name);
        item.setSellRate(sell);
        item.setTransferRate(tran);
        item.setBuyRate(buy);
        item.setUrlImage((new StringBuilder(String.valueOf(config._exchange))).append("/").append(image).toString());
        item.setUrlBg((new StringBuilder(String.valueOf(config._exchange))).append("/").append(icon).toString());
        boolean b = exchange.editExchangeRate(item, LangID);
        if(b)
        {
            ManagerFile file = new ManagerFile();
            String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
            String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._exchange).append("/").append(image).toString();
            file.copy(path1, path2);
            transferImageFLC.transferImageFLC23(path1, config._exchange);
            file.deletefile(path1);
            path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(icon).toString();
            path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._exchange).append("/").append(icon).toString();
            file.copy(path1, path2);
            transferImageFLC.transferImageFLC23(path1, config._exchange);
            file.deletefile(path1);
        }
        return b;
    }

    private static final long serialVersionUID = 1L;
    private TransferImageFLC transferImageFLC;
}
