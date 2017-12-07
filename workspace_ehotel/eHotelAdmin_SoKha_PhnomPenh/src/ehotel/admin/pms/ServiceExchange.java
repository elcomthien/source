package ehotel.admin.pms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ehotel.abs.pms.ExchangeRatePMS;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.dbi.ReportServiceDBI;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.ManagerFile;
import ehotel.domain.pms.eExchangeRate;

public class ServiceExchange extends ServiceParent {
	private static final long serialVersionUID = 1L;

	public ServiceExchange() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int cmd = -1;
		if (request.getParameter("CMD") != null) {
			try {
				cmd = Integer.parseInt(request.getParameter("CMD").toString());
			} catch (Exception e) {
			}
		}
		switch (cmd) {
		case 1: {
			int index = 0;
			int page = 6;
			if (request.getParameter("pageIndex") != null) {
				index = Integer.parseInt(request.getParameter("pageIndex")
						.toString().trim());
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page").toString()
						.trim());
			}
			response.setContentType("text/xml");
			String st = getContent(index, page);
			out.print(st);

			break;
		}
		case 2: {
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			ExchangeRatePMS exchange = new ExchangeRatePMS();
			eExchangeRate item = exchange.getExchangeRateInfo(id);
			request.setAttribute("Item", item);
			this.showJSPpage(request, response,
					"/pmsMng/Other/detailExchange.jsp");
			break;
		}
		case 3:// insert exchange rate
		{
			insert(request, response);
			break;
		}
		case 4:// update
		{
			upadte(request, response);
			break;
		}
		case 5:// delete
		{
			int id = -1;
			int i = 0;
			Vector<Integer> list = new Vector<Integer>();
			while (request.getParameter("id" + i) != null) {
				int subid = Integer.parseInt(request.getParameter("id" + i)
						.toString().trim());
				list.add(subid);
				i++;
			}
			String param = "(";
			for (i = 0; i < list.size(); i++) {
				param += list.get(i) + ",";
			}
			param = param.substring(0, param.length() - 1) + ")";
			ExchangeRatePMS exchange = new ExchangeRatePMS();
			exchange.removeExchangeRate(param);
			break;
		}
		case 6:// change status
		{
			int id = -1;
			if (request.getParameter("id") != null) {
				id = Integer.parseInt(request.getParameter("id").toString()
						.trim());
			}
			ExchangeRatePMS exchange = new ExchangeRatePMS();
			exchange.changeStatus(id);
			break;
		}
		default:
			break;
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
	}

	private String getContent(int index, int page) {
		ExchangeRatePMS exchange = new ExchangeRatePMS();
		int fr = index * page;
		fr += 1;
		int to = (index + 1) * page;
		Vector<eExchangeRate> info = exchange.getExchangeRates(fr, to);
		String mData = "";
		int count = exchange.countItem();
		mData += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData += "<xml  count=\"" + count + "\">";
		for (int i = 0; i < info.size(); i++) {
			eExchangeRate item = info.get(i);
			mData += "<Item>";
			mData += "<code>";
			mData += "<![CDATA[" + item.getCode() + "]]>";
			mData += "</code>";
			mData += "<name>";
			mData += "<![CDATA[" + item.getName() + "]]>";
			mData += "</name>";
			mData += "<id>\n";
			mData += item.getId();
			mData += "</id>";
			mData += "<buy>";
			if (item.getBuyRate() != null) {
				mData += "<![CDATA[" + item.getBuyRate() + "]]>";
			} else {
				mData += "N#A";
			}
			mData += "</buy>";
			mData += "<tran>";
			mData += "<![CDATA[" + item.getTransferRate() + "]]>";
			mData += "</tran>";
			mData += "<sel>";
			mData += "<![CDATA[" + item.getSellRate() + "]]>";
			mData += "</sel>";
			mData += "<image>";
			mData += "<![CDATA[" + item.getUrlImage() + "]]>";
			mData += "</image>";
			mData += "<icon>";
			mData += "<![CDATA[" + item.getUrlIcon() + "]]>";
			mData += "</icon>";
			mData += "<status>";
			mData += "<![CDATA[" + item.getInvisibel() + "]]>";
			mData += "</status>";
			mData += "</Item>";
		}
		mData += "</xml>";
		return mData;
	}

	private int insert(HttpServletRequest request, HttpServletResponse response) {
		String name = "";
		String code = "";
		String sell = "";
		String tran = "";
		String buy = "";
		String image = "";
		String icon = "";
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		if (request.getParameter("name") != null) {
			name = request.getParameter("name").toString().trim();
		}
		if (request.getParameter("code") != null) {
			code = request.getParameter("code").toString().trim();
		}
		if (request.getParameter("sell") != null) {
			sell = request.getParameter("sell").toString().trim();
		}
		if (request.getParameter("tran") != null) {
			tran = request.getParameter("tran").toString().trim();
		}
		if (request.getParameter("buy") != null) {
			buy = request.getParameter("buy").toString().trim();
		}
		if (request.getParameter("image") != null) {
			image = request.getParameter("image").toString().trim();
		}
		if (request.getParameter("icon") != null) {
			icon = request.getParameter("icon").toString().trim();
		}
		ExchangeRatePMS exchange = new ExchangeRatePMS();
		eExchangeRate item = new eExchangeRate();
		item.setCode(code);
		item.setName(name);
		item.setSellRate(sell);
		item.setTransferRate(tran);
		item.setBuyRate(buy);
		item.setUrlImage(config._exchange + "/" + image);
		item.setUrlBg(config._exchange + "/" + icon);
		int b = exchange.addExchangeRate(item);
		if (b > 0) {
			ManagerFile file = new ManagerFile();
			String path1 = config._temp + "/" + image;
			String path2 = config._pathImage + config._exchange + "/" + image;
			file.copy(path1, path2);
			file.deletefile(path1);
			path1 = config._temp + "/" + icon;
			path2 = config._pathImage + config._exchange + "/" + icon;
			file.copy(path1, path2);
			file.deletefile(path1);
		}
		return b;
	}

	private boolean upadte(HttpServletRequest request,
			HttpServletResponse response) {
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
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id").toString());
		}
		if (request.getParameter("name") != null) {
			name = request.getParameter("name").toString().trim();
		}
		if (request.getParameter("code") != null) {
			code = request.getParameter("code").toString().trim();
		}
		if (request.getParameter("sell") != null) {
			sell = request.getParameter("sell").toString().trim();
		}
		if (request.getParameter("tran") != null) {
			tran = request.getParameter("tran").toString().trim();
		}
		if (request.getParameter("buy") != null) {
			buy = request.getParameter("buy").toString().trim();
		}
		if (request.getParameter("image") != null) {
			image = request.getParameter("image").toString().trim();
		}
		if (request.getParameter("icon") != null) {
			icon = request.getParameter("icon").toString();
		}

		ExchangeRatePMS exchange = new ExchangeRatePMS();
		eExchangeRate old = exchange.getExchangeRateInfo(id);
		eExchangeRate item = new eExchangeRate();
		item.setId(id);
		item.setCode(code);
		item.setName(name);
		item.setSellRate(sell);
		item.setTransferRate(tran);
		item.setBuyRate(buy);
		item.setUrlImage(config._exchange + "/" + image);
		item.setUrlBg(config._exchange + "/" + icon);
		boolean b = exchange.editExchangeRate(item, LangID);
		HttpSession session = request.getSession(true);
		String user = "";
		user = session.getAttribute("user").toString();
		String text = createText(old, item);
		ReportServiceDBI.addUserActivities("Update " + name + text, user);
		if (b) {
			ManagerFile file = new ManagerFile();
			String path1 = config._temp + "/" + image;
			String path2 = config._pathImage + config._exchange + "/" + image;
			file.copy(path1, path2);
			file.deletefile(path1);
			path1 = config._temp + "/" + icon;
			path2 = config._pathImage + config._exchange + "/" + icon;
			file.copy(path1, path2);
			file.deletefile(path1);
		}
		return b;
	}

	public String createText(eExchangeRate old, eExchangeRate item) {
		String text = "";
		if (!old.getSellRate().equals(item.getSellRate()))
			text += " Sale from " + old.getSellRate() + " to "
					+ item.getSellRate();
		if (!old.getTransferRate().equals(item.getTransferRate()))
			text += " Transfer from " + old.getTransferRate() + " to "
					+ item.getTransferRate();
		if (!old.getBuyRate().equals(item.getBuyRate()))
			text += " Buy from " + old.getBuyRate() + " to "
					+ item.getBuyRate();
		return text;
	}
}