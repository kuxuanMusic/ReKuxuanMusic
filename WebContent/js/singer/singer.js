/**
 * 
 * @param singerId
 */
function deleteSinger(singerId) {
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;
	var bool = confirm("确认删除");
	if (bool) {
		// 删除用户
		location.href = "admin/SingerServlet?op=deleteSinger&singerId="
				+ singerId + "&pageNo=" + pageNo + "&pageSize=" + pageSize;
	}
}

function changeSinger(singerId) {
	location.href = "admin/SingerServlet?op=showSinger&singerId=" + singerId;
}

function toPage(no) {
	var pageSize = document.getElementById("pageSize").value;
	location.href = "admin/SingerServlet?op=SingerPage&pageNo=" + no
			+ "&pageSize=" + pageSize;
}

function changePageNo(e, o) {
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;
	if (e.keyCode == 13) {
		if (pageNo < o) {
			location.href = "admin/SingerServlet?op=SingerPage&pageNo="
					+ pageNo + "&pageSize=" + pageSize;
		} else {
			location.href = "admin/SingerServlet?op=SingerPage&pageNo=" + o
					+ "&pageSize=" + pageSize;
		}
	}
}
function changePageSize(e) {
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;
	var reg = /@\d{1,5}$/;
	if (e.keyCode == 13) {
		if (reg.test(pageSize)) {
			location.href = "admin/SingerServlet?op=SingerPage&pageNo="
					+ pageNo + "&pageSize=" + pageSize;
		} else {
			location.href = "admin/SingerServlet?op=SingerPage&pageNo="
					+ pageNo + "&pageSize=5";
		}
	}
}
