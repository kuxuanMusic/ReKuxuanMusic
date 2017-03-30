package com.chinasoft.control;

import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.entity.*;
import com.chinasoft.service.UserService;
import com.chinasoft.util.JSONUtils;
import com.chinasoft.util.PageModel;



/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/admin/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService us = new UserService();
	int i;

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("op");

		if ("selectAllUser".equals(operation)) {
			showAllUser(request, response);
		} else if ("deleteUser".equals(operation)) {
			deleteUser(request, response);
		} else if ("register".equals(operation)) {
			registerUser(request, response);
		} else if ("toChangeUser".equals(operation)) {
			toChangeUser(request, response);
		} else if ("changeUser".equals(operation)) {
			changeUser(request, response);
		} else if("adminLogin".equals(operation)){
			adminLogin(request, response);
		} else if("userLogin".equals(operation)){
			userLogin(request, response);
		}
	}

	protected void showAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 每页多少条数据
		int pageSize = 0;
		// 当前是第几页
		int pageNo = 0;

		// 当前页
		pageNo = Integer.valueOf(request.getParameter("pageNo"));
		pageSize = Integer.valueOf(request.getParameter("pageSize"));

		PageModel pm = us.getUserPage(pageNo, pageSize);
		request.setAttribute("pm", pm);

		request.getRequestDispatcher("/admin/allUser.jsp").forward(request, response);
	}

	protected void registerUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String psw = request.getParameter("password");
		String psw1 = request.getParameter("psw1");
		
		User user = us.selectUserInfoByName(username);
		
		if(user != null){
			request.setAttribute("msg", "用户已存在");
			request.getRequestDispatcher("../user/register.jsp").forward(request, response);
		}else if("".equals(username)){
			request.setAttribute("msg", "用户名不能为空");
			request.getRequestDispatcher("../user/register.jsp").forward(request, response);
		}else if("".equals(psw)){
			request.setAttribute("msg1", "密码不能为空");
			request.getRequestDispatcher("../user/register.jsp").forward(request, response);
		}else if("".equals(psw1)){
			request.setAttribute("msg2", "密码不能为空");
			request.getRequestDispatcher("../user/register.jsp").forward(request, response);
		}else if(!(psw1.equals(psw))){
			request.setAttribute("msg2", "密码不一致");
			request.getRequestDispatcher("../user/register.jsp").forward(request, response);
		}else if(user == null && psw1.equals(psw)){
			us.addUser(new User(username, psw1, 1));
			request.getRequestDispatcher("../user/userLogin.jsp").forward(request, response);
		}
	}

	protected void userLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String psw = request.getParameter("password");
		User user = us.selectUserInfoByName(username);
		
		if("".equals(username)){
			request.setAttribute("msg", "用户名不能为空");
			request.getRequestDispatcher("../user/userLogin.jsp").forward(request, response);
		}else if("".equals(psw)){
			request.setAttribute("msg1", "密码不能为空");
			request.getRequestDispatcher("../user/userLogin.jsp").forward(request, response);
		}else if(user == null){
			request.setAttribute("msg", "用户不存在,请注册");
			request.getRequestDispatcher("../user/userLogin.jsp").forward(request, response);
		}else if(!psw.equals(user.getPassword())){
			request.setAttribute("msg1", "密码错误");
			request.getRequestDispatcher("../user/userLogin.jsp").forward(request, response);
		}else if(user != null && psw.equals(user.getPassword()) && user.getUserType() == 1){
			request.getRequestDispatcher("../home.jsp").forward(request, response);
		}else if(user.getUserType() == 0){
			request.setAttribute("msg", "请联系管理员");
			request.getRequestDispatcher("../user/userLogin.jsp").forward(request, response);
		}
	}
	
	protected void adminLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = us.selectUserInfoByName(username);
		
		PrintWriter pw = response.getWriter();
		
		pw.print(JSONUtils.toJSONObject(user));
		pw.flush();

	}
	
	protected void toChangeUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("userId");
		ArrayList<User> list = new ArrayList<>();

		int pageNo = Integer.valueOf(request.getParameter("pageNo"));
		int pageSize = Integer.valueOf(request.getParameter("pageSize"));

		if (id != null || !("".equals(id))) {
			list = us.selectUserByUserId(Integer.valueOf(id));

			for (User u : list) {
				request.setAttribute("userId", u.getUserId());
				request.setAttribute("username", u.getUsername());
				request.setAttribute("psw", u.getPassword());
			}

			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);
			request.getRequestDispatcher("/admin/changeUser.jsp").forward(request, response);
		}
	}

	protected void changeUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("userId");
		String typename = request.getParameter("typename");

		if ((id != null || !("".equals(id)))) {
			us.change(Integer.valueOf(id), typename);
			showAllUser(request, response);
		}

	}

	protected void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService us = new UserService();

		String id = request.getParameter("userId");

		if (id != null || !"".equals(id)) {
			us.deleteUserById(Integer.valueOf(id));
			showAllUser(request, response);
		}
	}
}
