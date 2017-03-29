<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String str = request.getScheme() + "://" + request.getServerName()
	+ ":" + request.getServerPort() + request.getContextPath()
	+ "/";
%>
<base href="<%=str%>">
<title>插入歌手照片</title>
<link href="css/singercss/insertSingerPhoto.css" rel="stylesheet">
</head>
<body>
	<form action="SingerPhotoServlet" method="post"
		enctype="multipart/form-data">
		<table border="1px #000000">
			<tr>
				<td>歌手名：</td>
				<td><input type="text" name="singername" required/></td>
			</tr>
			<tr>
				<td>歌手照片：</td>
				<td><input type="file" name="filename" required/></td>
			</tr>
			<tr>
				<td>
				<td colspan="2"><span style="color: red">
						<%=
							request.getAttribute("msg")==null?"":request.getAttribute("msg")
						%>
				</span></td>
				</td>

			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="submit" value="保存" />&ensp;&ensp;&ensp;<input type="reset"
					value="重置"></td>
			</tr>
		</table>
	</form>
</body>
</html>