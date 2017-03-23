/**
 * @param 专辑id
 *            decription :修改专辑
 * 
 */
function changeAlbum(albumId) {
	location.href = "admin/AlbumServlet?op=changeAlbum&albumId=" + albumId;
}
/**
 * @param no：下一页面页码
 *  decription :翻页
 * 
 */
function toPage(no) {
	var pageSize = document.getElementById("pageSize").value;
	location.href = "admin/AlbumServlet?op=albuminfoFenye&pageNo=" + no
			+ "&pageSize=" + pageSize;
}
location.href = "admin/AlbumServlet?op=albuminfoFenye&pageNo=" + no
		+ "&pageSize=" + pageSize;

function changePageNo(e, o) {
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;
	if (e.keyCode == 13) {
		if (pageNo < o) {
			location.href = "admin/AlbumServlet?op=albuminfoFenye&pageNo=" + no
					+ "&pageSize=" + pageSize;
		} else {
			location.href = "admin/AlbumServlet?op=albuminfoFenye&pageNo=" + o
					+ "&pageSize=" + pageSize;
		}
	}
}
function changePageSize(e) {
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;
	var reg = /^\+?[1-9][0-9]*$/;
	if (e.keyCode == 13) {
		if (reg.test(pageSize)) {
			location.href = "admin/AlbumServlet?op=albuminfoFenye&pageNo=" + no
					+ "&pageSize=" + pageSize;
		} else {
			location.href = "admin/AlbumServlet?op=albuminfoFenye&pageNo="
					+ pageNo + "&pageSize=5";
		}
	}
}
