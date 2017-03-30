<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.chinasoft.entity.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String str = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=str%>">
<title>Insert title here</title>

<link rel="stylesheet" href="css/usercss/RegisterUser.css" />

<script type="text/javascript" src="jquery-easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
</head>
<body>
	<form method="post" action="admin/UserServlet">
		<input type="hidden" name="op" value="register" />
		<table id="tb2" cellspacing="0px">
			<tr>
				<td class="td1">
					<label for="username">用&nbsp;户&nbsp;名:</label>
				</td>
				<td class="td2">
					<input type="text" name="username" id="username" />${msg}
				</td>
			</tr>

			<tr>
				<td class="td1">
					<label for="psw">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
				</td>
				<td class="td2">
					<input type="password" name="password" id="password" />${msg1}
				</td>
			</tr>

			<tr>
				<td class="td1">
					<label for="psw1">确认密码:</label>
				</td>
				<td class="td2">
					<input type="password" name="psw1" id="psw1" />${msg2}
				</td>
			</tr>

			<tr>
				<td colspan="2" id="td3"><input type="submit" value="注册"
					id="tijiao" /></td>
			</tr>
		</table>
	</form>
</body>
</html>