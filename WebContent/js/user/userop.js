/**
 * 
 */
function deleteUser(userId) {
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;	
	var bool = confirm("确认删除");

	if (bool) {
		location.href = "admin/UserServlet?op=deleteUser&userId=" + userId
		+ "&pageNo=" + pageNo + "&pageSize=" + pageSize;
	}
}

function toModifyUser(userId) {
	var bool = confirm("确认修改");
	
	if (bool) {
		location.href = "admin/UserServlet?op=toChangeUser&userId=" + userId
		+ "&pageNo=" + pageNo.value + "&pageSize=" + pageSize.value;
	}
}

function cancelModify(){
	var bool = confirm("确认取消");
	
	if(bool){
		location.href = "admin/UserServlet?op=selectAllUser"
			+"&pageNo=" + pageNo.value + "&pageSize=" + pageSize.value;
	}
}

function modifyUser(userId) {
	var typename = document.getElementById("typename").value;
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;
	
	if(typename == ""){
		alert("用户类型不能为空");
		return false;
	}
	location.href = "admin/UserServlet?op=changeUser&userId=" + userId + "&typename=" + typename
	+ "&pageNo=" + pageNo + "&pageSize=" + pageSize;
}

function toPage(no){
	var pageSize = document.getElementById("pageSize").value;
	location.href="admin/UserServlet?op=selectAllUser&pageNo=" + no + "&pageSize=" + pageSize;
}

function changePageNo(e){
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;	
	if(e.keyCode==13){
		location.href="admin/UserServlet?op=selectAllUser&pageNo=" + pageNo + "&pageSize=" + pageSize;		
	}
}
