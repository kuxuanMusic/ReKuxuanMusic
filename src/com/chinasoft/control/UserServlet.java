package com.chinasoft.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasoft.service.UserService;
import com.chinasoft.util.JSONUtils;
import com.chinasoft.util.PageModel;
import com.chinasoft.entity.User;

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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
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
		} else if ("adminLogin".equals(operation)) {
			adminLogin(request, response);
		} else if ("userLogin".equals(operation)) {
			userLogin(request, response);
		} else if ("logout".equals(operation)) {
			logout(request, response);
		} else if ("register".equals(operation)) {
			register(request, response);
		}else if("login".equals(operation)){
			login(request, response);
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserService service = new UserService();
		User user = new User();
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));

		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));

		System.out.println(service.selectUserByUserNameAndPassword(user));
		if (service.selectUserByUserNameAndPassword(user) != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			request.getRequestDispatcher("MusicServlet").forward(request, response);
		} else {
			String error = "用户名或密码错误！请重新登录";
			request.setAttribute("error", error);
			request.getRequestDispatcher("MusicServlet?op=toplist").forward(request, response);
		}
	}

	/**
	 * 用户注销
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("MusicServlet?op=toplist").forward(request, response);
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "用户名或密码错误！请重新登录";
		request.setAttribute("error", error);
		request.getRequestDispatcher("MusicServlet").forward(request, response);
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

		if (user != null) {
			request.setAttribute("msg", "用户已存在");
			request.getRequestDispatcher("../user/register.jsp").forward(request, response);
		} else if ("".equals(username)) {
			request.setAttribute("msg", "用户名不能为空");
			request.getRequestDispatcher("../user/register.jsp").forward(request, response);
		} else if ("".equals(psw)) {
			request.setAttribute("msg1", "密码不能为空");
			request.getRequestDispatcher("../user/register.jsp").forward(request, response);
		} else if ("".equals(psw1)) {
			request.setAttribute("msg2", "密码不能为空");
			request.getRequestDispatcher("../user/register.jsp").forward(request, response);
		} else if (!(psw1.equals(psw))) {
			request.setAttribute("msg2", "密码不一致");
			request.getRequestDispatcher("../user/register.jsp").forward(request, response);
		} else if (user == null && psw1.equals(psw)) {
			us.addUser(new User(username, psw1, 1));
			request.getRequestDispatcher("../user/userLogin.jsp").forward(request, response);
		}
	}

	protected void userLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String psw = request.getParameter("password");
		User user = us.selectUserInfoByName(username);

		if ("".equals(username)) {
			request.setAttribute("msg", "用户名不能为空");
			request.getRequestDispatcher("../user/userLogin.jsp").forward(request, response);
		} else if ("".equals(psw)) {
			request.setAttribute("msg1", "密码不能为空");
			request.getRequestDispatcher("../user/userLogin.jsp").forward(request, response);
		} else if (user == null) {
			request.setAttribute("msg", "用户不存在,请注册");
			request.getRequestDispatcher("../user/userLogin.jsp").forward(request, response);
		} else if (!psw.equals(user.getPassword())) {
			request.setAttribute("msg1", "密码错误");
			request.getRequestDispatcher("../user/userLogin.jsp").forward(request, response);
		} else if (user != null && psw.equals(user.getPassword()) && user.getUserType() == 1) {
			request.getRequestDispatcher("../home.jsp").forward(request, response);
		} else if (user.getUserType() == 0) {
			request.setAttribute("msg", "请联系管理员");
			request.getRequestDispatcher("../user/userLogin.jsp").forward(request, response);
		}
	}

	protected void adminLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		//String password = request.getParameter("password");
		System.out.println(username);
		User user = us.selectUserInfoByName(username);
		

		PrintWriter pw = response.getWriter();

		pw.print(JSONUtils.toJSONObject(user));
		pw.flush();
		System.out.println("登录");
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
