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
<link rel="stylesheet" href="css/usercss/AllUser.css" />
<script type="text/javascript" src="js/user/userop.js"></script>
</head>
<body>
	<form method="post">
		<input type="hidden" name="op" />
		<table id="tb1" cellspacing="0px">
			<caption>用户信息</caption>
			<thead>
				<tr>
					<th>id</th>
					<th>用户名</th>
					<th>用户类型</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${pm.list}" var="user">
					<tr>
						<td>${user.userId}</td>
						<td>${user.username}</td>

						<td><c:choose>
								<c:when test="${user.userType == 0}">
	        			管理员
	        		</c:when>
								<c:otherwise>
	        			用户
	        		</c:otherwise>
							</c:choose></td>
						<td><a href="javascript:deleteUser(${user.userId})"><button
									type="button" class="bt1">删除</button></a> <a
							href="javascript:toModifyUser(${user.userId})"><button
									type="button" class="bt1">修改</button></a></td>
					</tr>
				</c:forEach>
			</tbody>

			<tfoot>
				<tr>
					<td colspan="4"><c:if test="${pm.pageNo == 1 }">
    				首页
    			</c:if> 
    			<c:if test="${pm.pageNo != 1 }">
					<a href="javascript:toPage(${pm.pageFirst})">首页</a>
				</c:if> 
					<a href="javascript:toPage(${pm.pagePre})">上一页</a> 
					<a href="javascript:toPage(${pm.pageNext})">下一页</a>
					<a href="javascript:toPage(${pm.pageLast})">尾页 </a>

					${pm.pageNo}/${pm.pageLast} 共${pm.count}条数据 
					跳转到
					<input type="text" name="pageNo" value="${pm.pageNo}" id="pageNo" onkeyup="changePageNo(event)" />页 
					每页
					<input type="text" name="pageSize" value="${pm.pageSize}" id="pageSize" />条数据</td>
				<tr>
			</tfoot>
		</table>
	</form>
</body>
</html>