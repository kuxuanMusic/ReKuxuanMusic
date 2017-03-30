package com.chinasoft.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.entity.MV;
import com.chinasoft.entity.MVInfo;
import com.chinasoft.entity.MVMusicAndSinger;
import com.chinasoft.entity.Music;
import com.chinasoft.service.MVService;
import com.chinasoft.util.PageModel;

@WebServlet("/admin/MVServlet")
public class MVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String op = request.getParameter("op");
		if ("addMV".equals(op)) {
			addMV(request, response);
		} else if ("getAllMV".equals(op)) {
			getAllMV(request, response);
		} else if ("getMVBySingerName".equals(op)) {
			getMVBySingerName(request, response);
		} else if ("changeMV".equals(op)) {
			changeMV(request, response);
		} else if ("updateMV".equals(op)) {
			updateMV(request, response);
		} else if ("mvpaging".equals(op)) {
			mvpaging(request, response);
		} else if ("showmv".equals(op)) {
			showMV(request, response);
		}
	}

	/**
	 * 前台主页显示MV列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void showMV(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MVService service = new MVService();
		ArrayList<MVInfo> list = service.selectMVPicture();
		
		request.setAttribute("mvs", list);
		request.getRequestDispatcher("../index.jsp").forward(request, response);
	}

	protected void mvpaging(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageSize = 0;
		int pageNo = 0;
		if (!("".equals(request.getParameter("pageNo")) || request.getParameter("pageNo") == null)
				&& Integer.valueOf(request.getParameter("pageNo")) != 0) {
			pageNo = Integer.valueOf(request.getParameter("pageNo"));
		} else {
			pageNo = 1;
		}
		if (!("".equals(request.getParameter("pageSize")) || request.getParameter("pageSize") == null)
				&& Integer.valueOf(request.getParameter("pageSize")) != 0) {
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		} else {
			pageSize = 5;
		}

		MVService ms = new MVService();

		PageModel pm = ms.getMvpaging(pageNo, pageSize);
		request.setAttribute("pm", pm);
		request.getRequestDispatcher("allMV.jsp").forward(request, response);
	}

	protected void updateMV(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mvId = request.getParameter("mvId");
		String singerName = request.getParameter("singerName");
		MVService ms = new MVService();
		int singerId = ms.selectSingerIdBySingerName(singerName);
		if (singerId > 0) {
			String musicName = request.getParameter("musicName");
			int musicId = ms.selectMusicIdByMusicNameAndSingerId(singerId, musicName);
			if (musicId > 0) {
				String mvAddress = request.getParameter("mvAddress");
				int result = ms.updateMV(musicId, mvAddress);
				if (result == 1) {
					request.setAttribute("msg", "mv修改成功");
					request.getRequestDispatcher("allMV.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "mv修改失败");
					request.getRequestDispatcher("reviseMV.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "mv修改失败");
				request.getRequestDispatcher("reviseMV.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("msg", "mv修改失败");
			request.getRequestDispatcher("reviseMV.jsp").forward(request, response);
		}

	}

	protected void addMV(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String singerName = request.getParameter("singerName");
		MVService ms = new MVService();
		int singerId = ms.selectSingerIdBySingerName(singerName);
		if (singerId > 0) {
			String musicName = request.getParameter("musicName");
			int musicId = ms.selectMusicIdByMusicNameAndSingerId(singerId, musicName);
			if (musicId > 0) {
				String mvAddress = request.getParameter("mvAddress");
				int result = ms.addMV(musicId, mvAddress);
				if (result == 1) {
					request.setAttribute("msg", "mv上传成功");
					request.getRequestDispatcher("allMV.jsp").forward(request, response);
				} else {
					request.setAttribute("msg", "mv上传失败");
					request.getRequestDispatcher("addMV.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "歌曲不存在");
				request.getRequestDispatcher("addMV.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("msg", "歌手不存在");
			request.getRequestDispatcher("addMV.jsp").forward(request, response);
		}

	}

	protected void getAllMV(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MVService ms = new MVService();
		ArrayList<MVMusicAndSinger> list = new ArrayList<>();
		list = ms.selectAllMV();
		request.setAttribute("list", list);
		request.getRequestDispatcher("allMV.jsp").forward(request, response);

	}

	protected void getMVBySingerName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String singerName = request.getParameter("singerName");
		System.out.println(singerName);
		MVService ms = new MVService();
		int singerId = ms.selectSingerIdBySingerName(singerName);
		if (singerId > 0) {
			Map<String, String> map = new HashMap<>();
			map = ms.getMVBySingerId(singerId);
			request.setAttribute("map", map);
			request.getRequestDispatcher("mvBySingerName.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "查询错误");
		}

	}

	protected void changeMV(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 修改用户之前通过用户id查询用户的所有信息
		String mvId = request.getParameter("mvId");
		MVService ms = new MVService();
		MVMusicAndSinger mms = ms.changeMV(mvId);
		request.setAttribute("mms", mms);
		request.getRequestDispatcher("reviseMV.jsp").forward(request, response);
	}

}
