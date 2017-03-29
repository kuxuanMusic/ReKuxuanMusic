package com.chinasoft.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.chinasoft.service.SingerService;

@WebServlet("/SingerPhotoServlet")
public class SingerPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();

		String singerName = "";
		String fileName = "";

		// 1 检测request中是否包含有multipart内容（isMultipartContent(request)是它的一个静态方法）
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (!isMultipart) {
			System.out.println("设置错误");
			return;
		}
		// 2 生成工厂类DiskFileItemFactory对象，进行相关的设置
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb

		File file = new File(this.getServletContext().getRealPath("/tempfile"));
		if (!file.exists()) {
			file.mkdir();
		}
		factory.setRepository(file);// 设置临时目录

		try {
			// 3 生成上传ServletFileUpload类，并将DiskFileItemFactory
			// 工厂传给它，并对ServletFileUpload进行配置
			ServletFileUpload upload = new ServletFileUpload(factory);

			upload.setFileSizeMax(4 * 1024 * 1024);// 设置单个上传文件的大小
			upload.setSizeMax(6 * 1024 * 1024);// 设置总文件大小

			// 4 从request得到上传的文件列表,并获得其迭代器
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					// 普通字段
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");

					if ("singername".equals(fieldName)) {
						singerName = fieldValue;
					}
				} else {
					// 得到MIME类型
					String mimeType = item.getContentType();
					// 只允许上传图片
					if (mimeType.startsWith("image")) {
						// 当是文件域时，将文件保存到硬盘中
						String filePath = this.getServletContext().getRealPath(
								"/files");
						long sizeInBytes = item.getSize();

						String fieldName = item.getFieldName();// 得到元素的name属
						fileName = item.getName();// 取得上传的文件名

						/*
						 * // 打散存储目录 // 根据
						 * /WEB-INF/files和文件名，创建一个新的存储路径,结果是这样的路径:
						 * /WEB-INF/files/1/12 String newStorePath =
						 * makeStorePath(filePath, fileName); String storeFile =
						 * newStorePath + "\\" + fileName; //
						 * WEB-INF/files/1/2/文件名 file = new File(storeFile);
						 * item.write(file); goods.setFilename(storeFile);
						 */

						file = new File(filePath + "\\" + fileName);
						item.write(file);
					}
				}
			}
			SingerService ss = new SingerService();
			boolean b = ss.selectSingerByName(singerName);

			if (b) {
				ss.insertSingerPhoto(singerName, fileName);
				// request.setAttribute("msg", "照片添加成功");
				request.getRequestDispatcher("admin/addSingerPhoto.jsp")
						.forward(request, response);

			} else {
				request.setAttribute("msg", "歌手不存在，请重新输入");
				request.getRequestDispatcher("admin/addSingerPhoto.jsp")
						.forward(request, response);
			}

			// 保存到数据库
		} catch (FileSizeLimitExceededException e) {
			// 单个文件超出大小时的异常
			writer.write("单个文件大小不能超出4M");
		} catch (SizeLimitExceededException e) {
			// 总文件超出大小时的异常
			writer.write("总文件大小不能超出6M");
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}