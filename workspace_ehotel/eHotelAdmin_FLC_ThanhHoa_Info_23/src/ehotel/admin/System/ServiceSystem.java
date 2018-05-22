package ehotel.admin.System;

import ehotel.abs.pms.ServiceSystemPMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.*;
import ehotel.domain.pms.eService;
import java.io.*;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceSystem extends ServiceParent
{

    public ServiceSystem()
    {
        os = System.getProperty("os.name").toLowerCase();
        main = new ServiceSystemPMS();
        transferImageFLC = new TransferImageFLC();
    }

    public void destroy()
    {
        super.destroy();
    }

    public void init()
        throws ServletException
    {
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
            request.setAttribute("fileJSP", "../system/ServiceSystemMain.jsp");
            showJSPpage(request, response, "/include/Mainpage.jsp");
            break;
        }

        case 1: // '\001'
        {
            System.out.println("Khoi tao: Lay tat ca service trong ServiceSystem");
            int index = 0;
            int page = 6;
            int langId = 1;
            if(request.getParameter("lang") != null)
                langId = Integer.parseInt(request.getParameter("lang").toString().trim());
            response.setContentType("text/xml");
            String st = getServices(index, page, langId);
            out.print(st);
            break;
        }

        case 2: // '\002'
        {
            System.out.println("Get APK list");
            int id = -1;
            String name = "";
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString();
            request.setAttribute("apk", main.getApkPath(id));
            request.setAttribute("name", name);
            showJSPpage(request, response, "/system/detailApk.jsp");
            break;
        }

        case 3: // '\003'
        {
            System.out.println("Get Service Information");
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            int langId = 1;
            if(request.getParameter("lang") != null)
                langId = Integer.parseInt(request.getParameter("lang").toString().trim());
            eService eItem = main.getServiceInfo(id, langId);
            request.setAttribute("eItem", eItem);
            showJSPpage(request, response, "/system/detailServiceSystem.jsp");
            break;
        }

        case 4: // '\004'
        {
            System.out.println("Update service status");
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            changeVisbleService(id);
            break;
        }

        case 5: // '\005'
        {
            System.out.println("Lay tat ca service trong ServiceSystem cho ORDER");
            int index = 0;
            int page = 6;
            int langId = 1;
            if(request.getParameter("lang") != null)
                langId = Integer.parseInt(request.getParameter("lang").toString().trim());
            response.setContentType("text/xml");
            Vector st = getServicesForOrder(index, page, langId);
            request.setAttribute("eItem", st);
            showJSPpage(request, response, "/system/detailOrder.jsp");
            break;
        }

        case 6: // '\006'
        {
            System.out.println("Get service and check status of service!");
            response.setContentType("text/xml");
            String st = getServicesNameXml();
            out.print(st);
            break;
        }

        case 7: // '\007'
        {
            String name = "";
            String type = "";
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString();
            if(request.getParameter("type") != null)
                type = request.getParameter("type").toString();
            System.out.println((new StringBuilder("Thay doi trang thai status cua module: ")).append(name).append(" chuyen sang ").append(type).toString());
            response.setContentType("text/xml");
            changeStatusModule(name, type);
            break;
        }

        case 8: // '\b'
        {
            String name = "";
            String port = "";
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString();
            if(request.getParameter("port") != null)
                port = request.getParameter("port").toString();
            System.out.println((new StringBuilder("Xem log cua ")).append(name).toString());
            response.setContentType("text/xml");
            String url = (new StringBuilder(String.valueOf(request.getScheme()))).append("://").append(request.getServerName()).append(":").append(request.getServerPort()).append("/eHotelAdmin").toString();
            System.out.println((new StringBuilder("url=")).append(url).toString());
            System.out.println((new StringBuilder("port=")).append(port).toString());
            String result = trackLog(port, url);
            out.print(result);
            break;
        }

        case 9: // '\t'
        {
            System.out.println("Get info memory usage");
            String memory = getInfoMemory();
            String ram = getInfoRam();
            String st = (new StringBuilder(String.valueOf(memory))).append(ram).toString();
            out.print(st);
            break;
        }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        super.doPost(request, response);
        response.setContentType("text/html");
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

        case 2: // '\002'
        {
            System.out.println("Update service apk");
            int serviceId = -1;
            String apkvalue = "";
            if(request.getParameter("id") != null)
                serviceId = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("apkvalue") != null)
                apkvalue = request.getParameter("apkvalue").toString();
            updateApk(serviceId, apkvalue);
            break;
        }

        case 3: // '\003'
        {
            System.out.println("Update service system");
            String name = "";
            String image = "";
            int lang = 1;
            int id = -1;
            if(request.getParameter("id") != null)
                id = Integer.parseInt(request.getParameter("id").toString().trim());
            if(request.getParameter("name") != null)
                name = request.getParameter("name").toString();
            if(request.getParameter("image") != null)
                image = request.getParameter("image").toString().trim();
            if(request.getParameter("lang") != null)
                lang = Integer.parseInt(request.getParameter("lang").toString().trim());
            boolean b = main.updateServiceMain(id, name, (new StringBuilder(String.valueOf(config._main))).append("/").append(image).toString(), lang);
            if(b)
            {
                ManagerFile file = new ManagerFile();
                String path1 = (new StringBuilder(String.valueOf(config._temp))).append("/").append(image).toString();
                String path2 = (new StringBuilder(String.valueOf(config._pathImage))).append(config._main).append("/").append(image).toString();
                file.copy(path1, path2);
                transferImageFLC.transferImageFLC23(path1, config._main);
                file.deletefile(path1);
            }
            break;
        }

        case 4: // '\004'
        {
            System.out.println("Dang set order cho service");
            String id = "";
            String order = "";
            if(request.getParameter("id") != null)
                id = request.getParameter("id").toString();
            if(request.getParameter("order") != null)
                order = request.getParameter("order").toString();
            System.out.println((new StringBuilder("Thu tu order la id=")).append(id).append(" order=").append(order).toString());
            orderByService(id, order);
            break;
        }
        }
    }

    private boolean updateApk(int serviceId, String apkvalue)
    {
        return main.setValueApkcode(serviceId, apkvalue);
    }

    private boolean changeVisbleService(int serviceId)
    {
        return main.changeVisbleService(serviceId);
    }

    private boolean orderByService(String str_service_id, String str_order)
    {
        return main.orderByService(str_service_id, str_order);
    }

    private String getServices(int index, int page, int langId)
    {
        Vector info = main.getAllServices(langId);
        String mData = "";
        int count = info.size();
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml  count=\"").append(count).append("\">").toString();
        for(int i = 0; i < info.size(); i++)
        {
            eService item = (eService)info.get(i);
            if(checkFlag(item.getServiceId()))
            {
                mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getServiceName()).append("]]>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append(item.getServiceId()).toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</id>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<action>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getAction()).append("]]>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</action>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<status>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getInvisble()).append("]]>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</status>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<image>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append(item.getUrlImage()).append("]]>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</image>").toString();
                mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
            }
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
        String list = prop.getProperty("ehotel.servicesytem.remove");
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

    private Vector getServicesForOrder(int index, int page, int langId)
    {
        Vector info = main.getAllServices(langId);
        return info;
    }

    private ArrayList getDaemon()
    {
        ArrayList result = new ArrayList();
        isWindows();
        if(isUnix())
        {
            String lsString = null;
            String listCmd[] = {
                "/bin/bash", "-c", "ls /etc/init.d/eod* /etc/init.d/httpd /etc/init.d/vsftpd -l"
            };
            Runtime run = Runtime.getRuntime();
            Process runtimeProcess = null;
            try
            {
                Properties prop = new Properties();
                try
                {
                    prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
                runtimeProcess = run.exec(listCmd);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
                while((lsString = bufferedReader.readLine()) != null) 
                {
                    System.out.println(lsString);
                    String name = lsString.substring(lsString.lastIndexOf(" ") + 1);
                    name = name.substring(name.lastIndexOf("/") + 1);
                    if(checkServiceValid(name) == 1)
                        if(!name.equalsIgnoreCase("eod_wer") && !name.equalsIgnoreCase("eod_syndatabase") && !name.equalsIgnoreCase("eod_synlogic"))
                        {
                            HashMap mp = new HashMap();
                            mp.put("name", name);
                            mp.put("view", prop.getProperty((new StringBuilder(String.valueOf(name))).append("_name").toString()));
                            mp.put("portlog", prop.getProperty((new StringBuilder(String.valueOf(name))).append(".log").toString()));
                            mp.put("port", prop.getProperty(name));
                            String executeCmd[] = {
                                "/bin/bash", "-c", (new StringBuilder("lsof -i -n -P | grep ")).append(prop.getProperty(name)).toString()
                            };
                            Process runtimeProcess1 = Runtime.getRuntime().exec(executeCmd);
                            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(runtimeProcess1.getInputStream()));
                            if(bufferedReader1.readLine() != null)
                                mp.put("status", "0");
                            else
                                mp.put("status", "1");
                            if(name.equalsIgnoreCase("httpd"))
                            {
                                String executeCmd1[] = {
                                    "/bin/bash", "-c", (new StringBuilder("lsof -i -n -P | grep ")).append(prop.getProperty(name)).toString()
                                };
                                Process runtimeProcess11 = Runtime.getRuntime().exec(executeCmd1);
                                BufferedReader bufferedReader11 = new BufferedReader(new InputStreamReader(runtimeProcess11.getInputStream()));
                                String temp;
                                for(temp = ""; bufferedReader11.readLine() != null; temp = (new StringBuilder(String.valueOf(temp))).append(bufferedReader11.readLine()).toString());
                                boolean flag = temp.contains("httpd");
                                if(flag)
                                    mp.put("status", "0");
                                else
                                    mp.put("status", "1");
                            }
                            result.add(mp);
                        } else
                        if(name.equalsIgnoreCase("eod_wer"))
                        {
                            HashMap mp = new HashMap();
                            mp.put("name", name);
                            mp.put("view", prop.getProperty((new StringBuilder(String.valueOf(name))).append("_name").toString()));
                            mp.put("portlog", prop.getProperty((new StringBuilder(String.valueOf(name))).append(".log").toString()));
                            String executeCmd[] = {
                                "/bin/bash", "-c", "ps -aux | grep WeatherSynApp.jar"
                            };
                            Process runtimeProcess1 = Runtime.getRuntime().exec(executeCmd);
                            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(runtimeProcess1.getInputStream()));
                            String temp = bufferedReader1.readLine();
                            boolean flag = temp.contains("app");
                            if(flag)
                                mp.put("status", "0");
                            else
                                mp.put("status", "1");
                            result.add(mp);
                        } else
                        if(name.equalsIgnoreCase("eod_syndatabase"))
                        {
                            HashMap mp = new HashMap();
                            mp.put("name", name);
                            mp.put("view", prop.getProperty((new StringBuilder(String.valueOf(name))).append("_name").toString()));
                            mp.put("portlog", prop.getProperty((new StringBuilder(String.valueOf(name))).append(".log").toString()));
                            String executeCmd[] = {
                                "/bin/bash", "-c", "ps -aux | grep syndatabase.jar"
                            };
                            Process runtimeProcess1 = Runtime.getRuntime().exec(executeCmd);
                            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(runtimeProcess1.getInputStream()));
                            String temp = bufferedReader1.readLine();
                            temp = (new StringBuilder(String.valueOf(temp))).append(bufferedReader1.readLine()).toString();
                            System.out.println(temp);
                            boolean flag = temp.contains("app");
                            if(flag)
                                mp.put("status", "0");
                            else
                                mp.put("status", "1");
                            result.add(mp);
                        } else
                        if(name.equalsIgnoreCase("eod_synlogic"))
                        {
                            HashMap mp = new HashMap();
                            mp.put("name", name);
                            mp.put("view", prop.getProperty((new StringBuilder(String.valueOf(name))).append("_name").toString()));
                            mp.put("portlog", prop.getProperty((new StringBuilder(String.valueOf(name))).append(".log").toString()));
                            String executeCmd[] = {
                                "/bin/bash", "-c", "ps -aux | grep SyncData.jar"
                            };
                            Process runtimeProcess1 = Runtime.getRuntime().exec(executeCmd);
                            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(runtimeProcess1.getInputStream()));
                            String temp = bufferedReader1.readLine();
                            temp = (new StringBuilder(String.valueOf(temp))).append(bufferedReader1.readLine()).toString();
                            boolean flag = temp.contains("app");
                            if(flag)
                                mp.put("status", "0");
                            else
                                mp.put("status", "1");
                            result.add(mp);
                        }
                }
                run.freeMemory();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    private String changeStatusModule(String name, String type)
    {
        isWindows();
        if(isUnix())
        {
            System.out.println((new StringBuilder("service ")).append(name).append(" ").append(type).toString());
            String listCmd[] = {
                "/bin/bash", "-c", (new StringBuilder("service ")).append(name).append(" ").append(type).toString()
            };
            Runtime run = Runtime.getRuntime();
            try
            {
                run.exec(listCmd);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            if(name.equalsIgnoreCase("eod_dbi_02") && type.equalsIgnoreCase("start"))
            {
                String Cmd[] = {
                    "/bin/bash", "-c", "service eod_ehotelmanservice start"
                };
                try
                {
                    run.exec(Cmd);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(name.equalsIgnoreCase("eod_dbi_01") && type.equalsIgnoreCase("start"))
            {
                String Cmd[] = {
                    "/bin/bash", "-c", "service eod_ehotelservice start"
                };
                try
                {
                    run.exec(Cmd);
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("update status service success!");
        return "ok";
    }

    private String trackLog(String port, String url)
    {
        String result = "";
        isWindows();
        if(isUnix())
        {
            boolean flag = writeToFileJnlp(port);
            if(flag)
                result = (new StringBuilder(String.valueOf(url))).append("/LogAppletjnlp.jnlp").toString();
        }
        return result;
    }

    public boolean writeToFileJnlp(String port)
    {
        Properties prop = new Properties();
        try
        {
            prop.load(getServletContext().getResourceAsStream("/WEB-INF/philao.properties"));
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        String realPath = getServletContext().getRealPath("/");
        String filePath = (new StringBuilder(String.valueOf(realPath))).append(File.separator).append("LogAppletjnlp.jnlp").toString();
        File file = new File(filePath);
        if(file.exists())
        {
            file.delete();
            file = new File(filePath);
        }
        try
        {
            FileWriter fstream = new FileWriter(filePath);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            out.write((new StringBuilder("<jnlp spec=\"1.0+\" codebase=\"http://")).append(prop.getProperty("ehotel.module.host")).append(":8888/eHotelAdmin\" href=\"LogAppletjnlp.jnlp\">\n").toString());
            out.write("<information>\n");
            out.write("<title>Log Information</title>\n");
            out.write("<vendor>Log Information</vendor>\n");
            out.write("</information>\n");
            out.write("<resources>\n");
            out.write("<j2se version=\"1.6+\" href=\"http://java.sun.com/products/autodl/j2se\"/>\n");
            out.write("<jar href=\"LogApplication.jar\" main=\"true\"/>\n");
            out.write("</resources>\n");
            out.write("<application-desc name=\"Log Information\" main-class=\"LogApplication\" width=\"300\" height=\"300\">\n");
            out.write((new StringBuilder("<argument>")).append(prop.getProperty("ehotel.module.host")).append("</argument>\n").toString());
            out.write((new StringBuilder("<argument>")).append(port).append("</argument>\n").toString());
            out.write("</application-desc>\n");
            out.write("<update check=\"background\"/>\n");
            out.write("</jnlp>");
            out.close();
        }
        catch(Exception e)
        {
            System.err.println((new StringBuilder("Error: ")).append(e.getMessage()).toString());
        }
        return true;
    }

    private String getServicesNameXml()
    {
        System.out.println("Get service name to xml!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        ArrayList info = getDaemon();
        String mData = "";
        int count = info.size();
        mData = (new StringBuilder(String.valueOf(mData))).append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>\n").toString();
        mData = (new StringBuilder(String.valueOf(mData))).append("<xml count=\"").append(count).append("\">").toString();
        for(int i = 0; i < info.size(); i++)
        {
            HashMap item = (HashMap)info.get(i);
            mData = (new StringBuilder(String.valueOf(mData))).append("<Item>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append((String)item.get("name")).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</name>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<view>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append((String)item.get("view")).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</view>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<id>\n").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append(i).toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</id>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<port>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append((String)item.get("port")).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</port>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<portlog>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append((String)item.get("portlog")).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</portlog>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("<![CDATA[").append((String)item.get("status")).append("]]>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</status>").toString();
            mData = (new StringBuilder(String.valueOf(mData))).append("</Item>").toString();
        }

        mData = (new StringBuilder(String.valueOf(mData))).append("</xml>").toString();
        return mData;
    }

    public boolean isWindows()
    {
        return os.indexOf("win") >= 0;
    }

    public boolean isMac()
    {
        return os.indexOf("mac") >= 0;
    }

    public boolean isUnix()
    {
        return os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0;
    }

    public boolean isSolaris()
    {
        return os.indexOf("sunos") >= 0;
    }

    public int checkServiceValid(String text)
    {
        if(text == "eod_lsnrora")
            return 0;
        if(text == "eod_ehotelmanservice")
            return 0;
        return text != "eod_dbora" ? 1 : 0;
    }

    public String getMemoryUsage()
    {
        String line = null;
        String result = "";
        String listCmd[] = {
            "/bin/bash", "-c", "df -m /data"
        };
        Runtime run = Runtime.getRuntime();
        Process runtimeProcess = null;
        try
        {
            runtimeProcess = run.exec(listCmd);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
            while((line = bufferedReader.readLine()) != null) 
                result = line;
            run.freeMemory();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public String getInfoMemory()
    {
        String info = getMemoryUsage();
        info = replaceSpace(info);
        String arr[] = info.split(" ");
        long used = Long.parseLong(arr[1]);
        long free = Long.parseLong(arr[2]);
        long total = used + free;
        int usedper = Integer.parseInt(arr[3]);
        int freeper = 100 - usedper;
        String data = "";
        data = (new StringBuilder(String.valueOf(data))).append("<totalhdd>").append(total).append("</totalhdd>").toString();
        data = (new StringBuilder(String.valueOf(data))).append("<usedhdd>").append(used).append("</usedhdd>").toString();
        data = (new StringBuilder(String.valueOf(data))).append("<freehdd>").append(free).append("</freehdd>").toString();
        data = (new StringBuilder(String.valueOf(data))).append("<usedperhdd>").append(usedper).append("</usedperhdd>").toString();
        data = (new StringBuilder(String.valueOf(data))).append("<freeperhdd>").append(freeper).append("</freeperhdd>").toString();
        return data;
    }

    public String getRamUsage()
    {
        String result = "";
        String line = null;
        String listCmd[] = {
            "/bin/bash", "-c", "free -m"
        };
        Runtime run = Runtime.getRuntime();
        Process runtimeProcess = null;
        try
        {
            runtimeProcess = run.exec(listCmd);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
            for(int i = 1; (line = bufferedReader.readLine()) != null; i++)
                if(i == 2)
                    result = line;

            run.freeMemory();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public String getInfoRam()
    {
        String info = getRamUsage();
        info = replaceSpace(info);
        String arr[] = info.split(" ");
        long total = Long.parseLong(arr[1]);
        long used = Long.parseLong(arr[2]);
        long free = Long.parseLong(arr[3]);
        int usedper = Math.round((used * 100L) / total);
        int freeper = 100 - usedper;
        String data = "";
        data = (new StringBuilder(String.valueOf(data))).append("<totalram>").append(total).append("</totalram>").toString();
        data = (new StringBuilder(String.valueOf(data))).append("<usedram>").append(used).append("</usedram>").toString();
        data = (new StringBuilder(String.valueOf(data))).append("<freeram>").append(free).append("</freeram>").toString();
        data = (new StringBuilder(String.valueOf(data))).append("<usedperram>").append(usedper).append("</usedperram>").toString();
        data = (new StringBuilder(String.valueOf(data))).append("<freeperram>").append(freeper).append("</freeperram>").toString();
        return data;
    }

    public String replaceSpace(String info)
    {
        for(; info.indexOf("  ") >= 0; info = info.replaceAll("  ", " "));
        info = info.substring(1);
        info = info.replace("%", "");
        return info;
    }

    private static final long serialVersionUID = 1L;
    public String os;
    ServiceSystemPMS main;
    private TransferImageFLC transferImageFLC;
}
