<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#tb {
	margin: auto;
	margin-top: 250px;
	border: 1px solid black;
	border-radius:5px;
	background-color: rgb(190,180,230);
	
}

#tb th,td{
	font-family:楷体;
	font-size:20px;
	border: 1px solid black;
	border-top: hidden;
	border-left: hidden;
	border-right: hidden;
	border-bottom: hidden;
	padding: 4px;
}

#tb th{
	text-align: right;
}

#tb td.td1{
	text-align: left;
}

#tb td.td1 input{
	font-family: 楷体;
	font-size: 20px;
	height:30px;
	border-radius:5px;
}

#tb td.td2{
	text-align: center;
}

#tb td.td2 input{
	font-family: 楷体;
	font-size: 20px;
}
</style>
</head>
<body>
	<form action="../MusicPictureServlet" method = "post" enctype="multipart/form-data">
	<input type="hidden" name="op" value="addPicture">
		<table id="tb">
			<tr>
				<th>歌曲名：</th>
				<td class="td1"><input type="text" id="musicname" name="musicname" required></td>
			</tr>
			<tr>
				<th>歌手名：</th>
				<td class="td1"><input type="text" id="singername" name="singername"
					required></td>
			</tr>
			<tr>
				<th>选择文件：</th>
				<td class="td1"><input type="file" id="musicpicture" name="musicpicture" required /></td>
			</tr>
			<tr>
			<td colspan="2" class="td2"><input type="submit" name="tijiao" id = "tijiao" value="提交" /></td>
			</tr>
		</table>
	</form>
</body>
</html>