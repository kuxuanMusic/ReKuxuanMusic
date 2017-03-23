function changeUser(mvId ){
	location.href="MVServlet?op=changeMV&mvId="+mvId;
}

function toPage(no){
	var pageSize = document.getElementById("pageSize").value;
	location.href="MVServlet?op=mvpaging&pageNo=" + no + "&pageSize=" + pageSize;
}


function changePageSize(e) {
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;
	var reg = /@\d{1,5}$/;
	if (e.keyCode == 13) {
		if (reg.test(pageSize)) {
			location.href = "MVServlet?op=mvpaging&pageNo="
					+ pageNo + "&pageSize=" + pageSize;
		} else {
			location.href = "MVServlet?op=mvpaging&pageNo="
					+ pageNo + "&pageSize=5";
		}
	}
}
function changePageNo(e, o) {
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;
	if (e.keyCode == 13) {
		if (pageNo < o) {
			location.href = "MVServlet?op=mvpaging&pageNo="
					+ pageNo + "&pageSize=" + pageSize;
		} else {
			location.href = "MVServlet?op=mvpaging&pageNo=" + o
					+ "&pageSize=" + pageSize;
		}
	}
}
