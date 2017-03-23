<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.5.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.5.1/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<table id="tt" class="easyui-datagrid"
		style="width: 600px; height: 250px" url="data/datagrid_data.json"
		title="DataGrid with Toolbar" iconCls="icon-save" toolbar="#tb">
		<thead>
			<tr>
				<th field="itemid" width="80">Item ID</th>
				<th field="productid" width="80">Product ID</th>
				<th field="listprice" width="80" align="right">List Price</th>
				<th field="unitcost" width="80" align="right">Unit Cost</th>
				<th field="attr1" width="150">Attribute</th>
				<th field="status" width="60" align="center">Stauts</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="javascript:alert('Add')">Add</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-cut" plain="true"
			onclick="javascript:alert('Cut')">Cut</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-save" plain="true"
			onclick="javascript:alert('Save')">Save</a>
	</div>
</body>
</html>