<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String str = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<base href="<%=str%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="Css/default.css"/>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.1/themes/icon.css" />

<script type="text/javascript" src="jquery-easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
<link href="css/album/albumall.css" rel="stylesheet" />

</head>
<body>
<form action="admin/AlbumServlet?op=addAlbum" id="addAlbum" method="post">	
    <table style="width: 1000px;margin: 200px auto;" id="addAlbum">    
        <tr> <td colspan="2" style="text-align:center;" id="msg">${msg}</td></tr>
        <tr>
            <th>专辑名：</th>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <th>发行时间：</th>
            <td><input id="dd" type="text" class="easyui-datebox"  id="releasetime" name="releasetime" required> </td>
        </tr>
         <tr>
            <th>发行公司：</th>
            <td><input type="text" name="company" ></td>
        </tr>
        <tr>
            <th>类型：</th>
            <td>
                <select style="width:100px;" name="type">
                    <option value="1" selected>EP</option>
                    <option value="2">录音室专辑</option>
                    <option value="3">合辑</option>
                    <option value="4">精选辑</option>
                    <option value="5">单曲</option>
                   
                </select>
            </td>
        </tr>
        <tr>
            <th>语种：</th>
            <td>
                <select style="width:100px;" name="language">
                    <option value="1" selected>汉语</option>
                    <option value="2">日语</option>
                    <option value="3">英语</option>
                    <option value="4">韩语</option>
                    <option value="5">德语</option>
                    <option value="6">法语</option>
                    <option value="7">西班牙语</option>
                    <option value="8">葡萄牙语</option>
                    <option value="9">印第安语</option>
                </select>
            </td>
        </tr>
       
        <tr>
            <th colspan="2" style="text-align:center;">
            	<br><input type="submit" value="提交" style="margin-left:-60px;"/>
            	<input type="reset" value="重置" style="margin-left:30px;"/>
            </th>
        </tr>
    </table>
</form>

</body>
</html>