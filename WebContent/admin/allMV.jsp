<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <%
		String str = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
	%>
	<base href="<%=str%>"> --%>
<title>Insert title here</title>
<script type="text/javascript" src="../js/mv/mv.js"></script>
</head>
<body>

	<table with="60%">
		<thead>
			<tr>
				<th with="25%" heigth="20px">歌手</th>
				<th with="25%" heigth="20px">歌曲</th>
				<th with="25%" heigth="20px">MV</th>
				<th with="25%" heigth="20px">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pm.list}" var="list">
				<tr>
					<input type="hidden" name="id" value="${list.mvId}" />
					<td>${list.singerName}</td>
					<td>${list.musicName}</td>
					<td>${list.address }</td>
					<td><a href="javascript:changeUser(${list.mvId})">修改</a></td>

				</tr>

			</c:forEach>
		</tbody>
		<tfoot>
    	<tr>
    		<td colspan="9">
    			<c:if test="${pm.pageNo == 1 }">
    				首页
    			</c:if>
    			<c:if test="${pm.pageNo != 1 }">
    				<a href="javascript:toPage(${pm.pageFirst})">首页</a>
    			</c:if>    			
    			<a href="javascript:toPage(${pm.pagePre})">上一页</a>
    			<a href="javascript:toPage(${pm.pageNext})">下一页</a>
    			<a href="javascript:toPage(${pm.pageLast})">尾页  </a>
    			
    			${pm.pageNo}/${pm.pageLast}
    			共${pm.count}条数据 
    			跳转到<input type="text" name="pageNo" value="${pm.pageNo}" id="pageNo" onkeyup="changePageNo(event,${pm.pageLast})"/>页  
    			每页<input type="text" name="pageSize" value="${pm.pageSize}" id="pageSize" onkeyup="changePageSize(event)" />条数据
    		</td>
    	<tr>
    </tfoot>    
	</table>
</body>
</html>