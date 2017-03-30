<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript" src="js/user/userop.js"></script>
<link rel="stylesheet" href="css/usercss/ChangeUser.css" />
</head>
<body>
	<form method="post">
	    <input type="hidden" id="pageNo" value="${pageNo}">
	    <input type="hidden" id="pageSize" value="${pageSize}">
		<table id="tb">
			<tr>
				<td class="td1">用&nbsp;户&nbsp;名:</td>
				<td class="td2"><input type="text" disabled="disabled" value="${username}" /></td>
			</tr>
			
			<tr>
				<td class="td1">密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
				<td class="td2"><input type="password" disabled="disabled" value="${psw}" /></td>
			</tr>
			
			<tr>
				<td class="td1">用户类型:</td>
				<td class="td2"><input type="text" name="typename" id="typename"/></td>
			</tr>
			<tr>
				<td class="td3" colspan="2">
				    <input type="button" value="确定" id="sure" onclick="modifyUser(${userId})" />
					<input type="button" value="取消" id="cancel" onclick="cancelModify()" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>