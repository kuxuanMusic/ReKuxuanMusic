<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String str = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=str%>">
<title>用户管理</title>
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.5.1/themes/black/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.5.1/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	//关键字查询
	function searchSaleChance() {
		$("#dg").datagrid('load', {
			"musicId" : $("#s_noticeid").val(),
			"noticetype" : $("#s_noticetype").combobox("getValue"),
			"noticetitle" : $("#s_noticetitle").val(),
			"noticeauthor" : $("#s_noticeauthor").val(),
		});
	}

	/* //添加
	function openSaleChanceAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加歌曲");
		url = "admin/MusicServlet?op=addMusic";
	} */

	//修改
	function openSaleChanceModifyDialog() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑歌曲");
		$("#fm").form("load", row);
		url = "admin/MusicServlet?op=addMusic,id=" + row.musicid;
	}

	//保存
	function saveSaleChance() {
		$("#fm").form("submit", {
			url : "admin/MusicServlet?op=updateMusic",
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$.messager.alert("系统提示", "歌曲修改成功！");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				} else {
					$.messager.alert("系统提示", "歌曲修改失败！");
					return;
				}
			}
		});
	}

	function resetValue() {
		$("#musicId").val("");
		$("#musicName").val("");
		$("#singerName").val("");
		$("#albumName").val("");
		$("#releaseTime").val("");
		$("#languageName").val("");
		$("#typeName").val("");
		$("#address").val("");
	}

	//关闭
	function closeSaleChanceDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
</script>

<title>所有歌曲</title>

<script type="text/javascript">
	
</script>
</head>
<body style="margin: 1px">
	<table id="dg" title="歌曲管理" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true" style="font-size:40px;color:red;" striped = "true"
		url="admin/MusicServlet?op=musicFenye" fit="true" toolbar="#tb"
		pageSize="10" pageList="[ 10, 20, 30,50 ]">
		<thead>
			<tr>
				<!-- <th field="cb" checkbox="true" align="center"></th> -->
				<th field="musicId" width="4%" align="center">歌曲编号</th>
				<th field="musicName" width="15%" align="center">歌曲名</th>
				<th field="singerName" width="15%" align="center">歌手名</th>
				<th field="albumName" width="15%" align="center">专辑名</th>
				<th field="releaseTime" width="10%" align="center">发布时间</th>
				<th field="languageName" width="10%" align="center">语种</th>
				<th field="typeName" width="10%" align="center">类型</th>
				<th field="address" width="20%" align="center">歌曲存放地址</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<!-- <a href="javascript:openSaleChanceAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">创建</a>  -->
			<a href="javascript:openSaleChanceModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>
		<div>
			&nbsp;公告编号：&nbsp;<input type="text" id="s_noticeid" size="20"
				onkeydown="if(event.keyCode==13) searchSaleChance()" />
			&nbsp;公告类型：&nbsp; <input type="text" id="s_noticetype" size="20"
				onkeydown="if(event.keyCode==13) searchSaleChance()" /> <select
				class="easyui-combobox" id="s_noticetype" editable="false"
				panelHeight="auto">
				<option value="">请选择...</option>
				<option value="新闻">新闻</option>
				<option value="通知">通知</option>
			</select> &nbsp;公告标题：&nbsp;<input type="text" id="s_noticetitle" size="20"
				onkeydown="if(event.keyCode==13) searchSaleChance()" />
			&nbsp;发布人：&nbsp;<input type="text" id="s_noticeauthor" size="20"
				onkeydown="if(event.keyCode==13) searchSaleChance()" /> <a
				href="javascript:searchSaleChance()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: 450px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">

		<form id="fm" method="post" action="MusicServlet?op=updateMusic">
			<table cellspacing="8px">
				<tr>
					<td>带<font color="red">*</font>为必填项
					</td>
				</tr>
				<tr>
					<td><font color="red">*</font>歌曲ID</td>
					<td><input type="text" id="musicId" name="musicId"
						class="easyui-validatebox" readonly /></td>
				</tr>
				<tr>
					<td><font color="red">*</font>歌曲名</td>
					<td><input type="text" id="musicName" name="musicName"
						class="easyui-validatebox" required /></td>

				</tr>
				<tr>
					<td><font color="red">*</font>歌手名</td>
					<td><input type="text" id="singerName" name="singerName"
						class="easyui-validatebox" required /></td>
				</tr>
				<tr>
					<td><font color="red">*</font>专辑名</td>
					<td><input type="text" id="albumName" name="albumName"
						class="easyui-validatebox" required /></td>
				</tr>
				<tr>
					<td><font color="red">*</font>发布时间</td>
					<td><input id="dd" type="text" class="easyui-datebox"
						id="releaseTime" name="releaseTime" required></td>
				</tr>
				<tr>
					<th>语种：</th>
					<td><select style="width: 100px;" id="languageId"
						name="languageId">
							<option value="1" selected>汉语</option>
							<option value="2">日语</option>
							<option value="3">英语</option>
							<option value="4">韩语</option>
							<option value="5">德语</option>
							<option value="6">法语</option>
							<option value="7">西班牙语</option>
							<option value="8">葡萄牙语</option>
							<option value="9">印第安语</option>
					</select></td>
				</tr>
				<tr>
					<th>歌曲类型：</th>
					<td><select style="width: 100px;" id="typeId"
						name="typeId">
							<option value="1" selected>流行</option>
							<option value="2">古典</option>
							<option value="3">爵士</option>
							<option value="4">乡村</option>
							<option value="5">嘻哈</option>
							<option value="6">摇滚</option>
							<option value="7">轻音乐</option>
					</select></td>
				</tr>
				<tr>
					<th>存放地址：</th>
					<td><input type="text" id="address" name="address"
						class="easyui-validatebox" required></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:saveSaleChance()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeSaleChanceDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>