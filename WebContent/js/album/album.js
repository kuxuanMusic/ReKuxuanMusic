function changeAlbum(albumId) {
	alert(albumId);
	location.href = "admin/AlbumServlet?op=changeAlbum&albumId=" + albumId;

}

function toPage(no) {
	var pageSize = document.getElementById("pageSize").value;
	location.href = "admin/AlbumServlet?op=albuminfoFenye&pageNo=" + no
			+ "&pageSize=" + pageSize;
}

function changePageNo(e) {
	var pageNo = document.getElementById("pageNo").value;
	var pageSize = document.getElementById("pageSize").value;
	if (e.keyCode == 13) {
		location.href = "admin/AlbumServlet?op=albuminfoFenye&pageNo=" + no
				+ "&pageSize=" + pageSize;
	}
}