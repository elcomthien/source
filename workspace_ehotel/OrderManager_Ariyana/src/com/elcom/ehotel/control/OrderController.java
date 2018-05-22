package com.elcom.ehotel.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elcom.ehotel.model.DiningModel;
import com.elcom.ehotel.service.OrderService;
import com.google.gson.Gson;

public class OrderController extends HttpServlet {

	public OrderController() {
		orderService = new OrderService();
	}

	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		String user = "";
		user = session.getAttribute("user").toString();
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();
		if ("all".equalsIgnoreCase(action))
			if (session.getAttribute("user") != null) {
				// ILOGIN iuser = DBIGateway.getILogin();
				String ipAdress = request.getRemoteAddr();
				if (!orderService.isAdmin(ipAdress))
					try {
						if (orderService.checkRoleUser(ipAdress, "PMS$ORDER")) {
							request.getRequestDispatcher("index.jsp").forward(request, response);
						} else {
							String error = "You do not have permission to access!";
							out.print(error);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				else
					request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				String error = "You do not have permission to access!";
				out.print(error);
			}
		if ("confirm".equalsIgnoreCase(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			int folionum = Integer.parseInt(request.getParameter("folionum"));
			String service = request.getParameter("service");
			orderService.confirmOrder(id, folionum, service, user);
			response.sendRedirect("order?action=all");
		}
		if ("deny".equalsIgnoreCase(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			int folionum = Integer.parseInt(request.getParameter("folionum"));
			String service = request.getParameter("service");
			orderService.denyOrder(id, folionum, service, user);
			response.sendRedirect("order?action=all");
		}
		if ("request".equalsIgnoreCase(action)) {
			List<DiningModel> listorder = new ArrayList<DiningModel>();
			listorder = orderService.getNewOrder(session.getAttribute("role").toString());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write((new Gson()).toJson(listorder));
		}
		if ("delete".equalsIgnoreCase(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			orderService.deleteOrder(id);
			response.sendRedirect("order?action=all");
		}
		if ("filter".equalsIgnoreCase(action)) {
			String service = request.getParameter("service");
			String folio = request.getParameter("folio");
			List list = orderService.filterOrder(service, folio, session.getAttribute("role").toString());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write((new Gson()).toJson(list));
		}
		if ("deletelist".equalsIgnoreCase(action)) {
			String listid = request.getParameter("listid");
			orderService.deleteListOrder(listid);
			List listorder = new ArrayList();
			listorder = orderService.getNewOrder(session.getAttribute("role").toString());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write((new Gson()).toJson(listorder));
		}
		if ("getcontents".equalsIgnoreCase(action)) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			List listorder = new ArrayList();
			List listhousekeeping = new ArrayList();
			List listwakeup = new ArrayList();
			List listroom = new ArrayList();
			String tabs = request.getParameter("tabs");
			if (tabs.equals("tabs-dining")) {
				listorder = orderService.getNewOrder(session.getAttribute("role").toString());
				response.getWriter().write((new Gson()).toJson(listorder));
			} else if (tabs.equals("tabs-housekeeping")) {
				listhousekeeping = orderService.getNewHousekeeping();
				response.getWriter().write((new Gson()).toJson(listhousekeeping));
			} else if (tabs.equals("tabs-wakeup")) {
				listwakeup = orderService.getNewWakup();
				response.getWriter().write((new Gson()).toJson(listwakeup));
			} else if (tabs.equals("tabs-room")) {
				listroom = orderService.getListRoomStatus();
				response.getWriter().write((new Gson()).toJson(listroom));
			}
		}
		if ("checkorder".equalsIgnoreCase(action)) {
			List list = new ArrayList();
			list = orderService.checkNewOrder();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write((new Gson()).toJson(list));
		}
		if ("confirmhk".equalsIgnoreCase(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			int folionum = Integer.parseInt(request.getParameter("folionum"));
			orderService.confirmHousekeeping(id, folionum, user);
			List listhousekeeping = new ArrayList();
			listhousekeeping = orderService.getNewHousekeeping();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write((new Gson()).toJson(listhousekeeping));
		}
		if ("deletehk".equalsIgnoreCase(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			int folionum = Integer.parseInt(request.getParameter("folionum"));
			orderService.deleteHousekeeping(id, folionum, user);
			List listhousekeeping = new ArrayList();
			listhousekeeping = orderService.getNewHousekeeping();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write((new Gson()).toJson(listhousekeeping));
		}
		if ("confirmwk".equalsIgnoreCase(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			int folionum = Integer.parseInt(request.getParameter("folionum"));
			orderService.confirmWakeup(id, folionum, user);
			List listwakeup = new ArrayList();
			listwakeup = orderService.getNewWakup();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write((new Gson()).toJson(listwakeup));
		}
		if ("deletewk".equalsIgnoreCase(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			int folionum = Integer.parseInt(request.getParameter("folionum"));
			orderService.deleteWakeup(id, folionum, user);
			List listwakeup = new ArrayList();
			listwakeup = orderService.getNewWakup();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write((new Gson()).toJson(listwakeup));
		}
		if ("confirmrs".equalsIgnoreCase(action)) {
			int folionum = Integer.parseInt(request.getParameter("folionum"));
			orderService.confirmRoomStatus(folionum, user);
			List listroom = new ArrayList();
			listroom = orderService.getListRoomStatus();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write((new Gson()).toJson(listroom));
		}
	}

	protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) throws ServletException,
			IOException {
	}

	private static final long serialVersionUID = 1L;
	private OrderService orderService;
}
