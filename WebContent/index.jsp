<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String str = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=str%>">
<meta charset="UTF-8">
<title>酷炫音乐网</title>
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/style.css" />

<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<header>
	<div id="logo">
		<img src="img/logo.png" alt="酷炫音乐网" />
		<div class="top">
			<a href="music.jsp">音乐馆</a>
		</div>
		<c:if test="${empty(param.username)}">
			<div class="top">
				<div class="main_nav">
					<div>
						<div style="“width: 150px;">
							<a href="javascript:;" class="cd-signin">我的音乐</a>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${!empty(param.username)}">
			<div class="top">
				<a href="kuxuan/homepage.jsp">我的音乐</a>
			</div>
		</c:if>
		<div class="top">
			<a href="#">下载客户端</a>
		</div>
		<c:if test="${empty(param.username)}">
			<div id="demo" style="margin-left: 150px;">
				<div class="main_nav">
					<div>
						<div>
							<a class="cd-signin" href="javascript:;">登录</a>
						</div>
						<div>
							<a class="cd-signup" href="javascript:;">注册</a>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${!empty(param.username)}">
			<div style="display: inline-block; margin-left: 360px;">
				<p style="color: red; font-size: 25px;">
					<i style="color: black;">欢迎你！</i>${param.username} <a
						href="admin/UserServlet?op=logout">注销</a>
				</p>
			</div>
		</c:if>
	</div>
	<div id="nov">
		<!-- <div class="nov1"><a href="index.html"><b>首&nbsp;页</b></a></div> -->
		<div class="nov1">
			<a href="#"><b>歌&nbsp;手</b></a>
		</div>
		<div class="nov1">
			<a href="#"><b>专&nbsp;辑</b></a>
		</div>
		<div class="nov1">
			<a href="#"><b>排行榜</b></a>
		</div>
		<div class="nov1">
			<a href="#"><b>分类歌单</b></a>
		</div>
		<div class="nov1">
			<a href="#"><b>M&nbsp;V</b></a>
		</div>
	</div>
	</header>
	<div class="box">
		<div class="list">
			<ul>
				<li class="p7 p0"><a href="#"><img src="img/1.png" alt="" /></a></li>
				<li class="p6 p0"><a href="#"><img src="img/2.png" alt="" /></a></li>
				<li class="p5 p0"><a href="#"><img src="img/3.png" alt="" /></a></li>
				<li class="p4 p0"><a href="#"><img src="img/44.jpg" alt="" /></a></li>
				<li class="p3 p0"><a href="#"><img src="img/55.jpg" alt="" /></a></li>
				<li class="p2 p0"><a href="#"><img src="img/66.jpg" alt="" /></a></li>
				<li class="p1 p0"><a href="#"><img src="img/77.jpg" alt="" /></a></li>
			</ul>
		</div>

		<a href="javascript:;" class="prev btn"><</a> <a href="javascript:;"
			class="next btn">></a>

		<div class="buttons">
			<a href="javascript:;"><span class="blue"></span></a> <a
				href="javascript:;"><span></span></a> <a href="javascript:;"><span></span></a>
			<a href="javascript:;"><span></span></a> <a href="javascript:;"><span></span></a>
			<a href="javascript:;"><span></span></a> <a href="javascript:;"><span></span></a>
		</div>
	</div>
	<script type="text/javascript" src="js/index.js"></script>
	<div id="toplist_box">
		<span id="phb">排行榜</span>
		<p style="float: right; margin: 30px 20px 0 0">
			<a href="https://y.qq.com/n/yqq/toplist/4.html#">全部></a>
		</p>
		<br>
		<div class="toplist" style="background: rebeccapurple;">
			<p>
				<span class="df">巅峰榜</span><br>流行指数
			</p>
			<table>
				<!-- 读取后台数据 -->
				<c:forEach items="${msa1}" var="music" begin="0" end="5">
					<tr>
						<td>${music.musicName}</td>
						<td>${music.singerName}</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="toplist" style="background: red;">
			<P>
				<span class="df">巅峰榜</span><br>热&nbsp;&nbsp;&nbsp;&nbsp;歌
			</P>
			<table>
				<!-- 读取后台数据 -->
				<c:forEach items="${msa2}" var="music" begin="1" end="6">
					<tr>
						<td>${music.musicName}</td>
						<td>${music.singerName}</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="toplist" style="background: orange;">
			<P>
				<span class="df">巅峰榜</span><br>新&nbsp;&nbsp;&nbsp;&nbsp;歌
			</P>
			<table>
				<!-- 读取后台数据 -->
				<c:forEach items="${msa3}" var="music" begin="3" end="8">
					<tr>
						<td>${music.musicName}</td>
						<td>${music.singerName}</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="toplist" style="background: blueviolet;">
			<P>
				<span class="df">巅峰榜</span><br>欧&nbsp;&nbsp;&nbsp;&nbsp;美
			</P>
			<table>
				<!-- 读取后台数据 -->
				<c:forEach items="${msa4}" var="music" begin="4" end="9">
					<tr>
						<td>${music.musicName}</td>
						<td>${music.singerName}</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<br>
	<div id="mv">
		<span id="sb">MV首播</span>
		<p style="float: right; margin: 30px 20px 0 0">
			<a href="https://y.qq.com/portal/mv_lib.html#">全部></a>
		</p>
		<br>
		<form action="" method="post">
			<div id="sort">
				<a href="http://www.qq.com" target="iframe1">全部</a> <a href="">内地</a>
				<a href="">港台</a> <a href="">欧美</a> <a href="">日本</a> <a href="">韩国</a>
			</div>
		</form>
		<div id="show">
			<!--<iframe id="iframe1" name="iframe1" src="http://www.baidu.com" frameborder="0" width="1150px" height="580px" scrolling="no" ></iframe>-->

			<!-- 读取后台数据 -->
			<c:forEach items="${mvs}" var="mv" begin="0" end="7">
				<div class="showmv">
					<div hidden>${mv.mvId}</div>
					<div>
						<img src="files/${mv.musicPictureAddress}"
							style="width: 300px; height: 200px" />
					</div><br />
					<span>${mv.musicName}</span>
				</div>
			</c:forEach>
			<c:forEach items="${mvs}" var="mv" begin="0" end="7">
				<div class="showmv">
					<div hidden>${mv.mvId}</div>
					<div>
						<img src="files/${mv.musicPictureAddress}"
							style="width: 300px; height: 200px" />
					</div><br />
					<span>${mv.musicName}</span>
				</div>
			</c:forEach>
			<c:forEach items="${mvs}" var="mv" begin="0" end="7">
				<div class="showmv">
					<div hidden>${mv.mvId}</div>
					<div>
						<img src="files/${mv.musicPictureAddress}"
							style="width: 300px; height: 200px" />
					</div><br />
					<span>${mv.musicName}</span>
				</div>
			</c:forEach>
			<c:forEach items="${mvs}" var="mv" begin="0" end="7">
				<div class="showmv">
					<div hidden>${mv.mvId}</div>
					<div>
						<img src="files/${mv.musicPictureAddress}"
							style="width: 300px; height: 200px" />
					</div><br />
					<span>${mv.musicName}</span>
				</div>
			</c:forEach>
			<c:forEach items="${mvs}" var="mv" begin="0" end="7">
				<div class="showmv">
					<div hidden>${mv.mvId}</div>
					<div>
						<img src="files/${mv.musicPictureAddress}"
							style="width: 300px; height: 200px" />
					</div><br />
					<span>${mv.musicName}</span>
				</div>
			</c:forEach>
			<c:forEach items="${mvs}" var="mv" begin="0" end="7">
				<div class="showmv">
					<div hidden>${mv.mvId}</div>
					<div>
						<img src="files/${mv.musicPictureAddress}"
							style="width: 300px; height: 200px" />
					</div><br />
					<span>${mv.musicName}</span>
				</div>
			</c:forEach>
			<c:forEach items="${mvs}" var="mv" begin="0" end="7">
				<div class="showmv">
					<div hidden>${mv.mvId}</div>
					<div>
						<img src="files/${mv.musicPictureAddress}"
							style="width: 300px; height: 200px" />
					</div><br />
					<span>${mv.musicName}</span>
				</div>
			</c:forEach>
			<c:forEach items="${mvs}" var="mv" begin="0" end="7">
				<div class="showmv">
					<div hidden>${mv.mvId}</div>
					<div>
						<img src="files/${mv.musicPictureAddress}"
							style="width: 300px; height: 200px" />
					</div><br />
					<span>${mv.musicName}</span>
				</div>
			</c:forEach>
		</div>
	</div>
	<footer>
	<div class="footer_link">
		<h3 class="footer_tit">合作链接</h3>
		<div class="footer_link_list__item">
			<a href="//y.qq.com/portal/company_detail.html?id=297"
				target="_blank" class="js_other_link"
				data-stat="y_new.footer.other_link">CJ E&M</a>
		</div>
		<div class="footer_link_list__item">
			<a href="http://v.qq.com/" target="_blank" class="js_other_link"
				data-stat="y_new.footer.other_link">腾讯视频</a>
		</div>
		<div class="footer_link_list__item">
			<a href="http://z.qzone.com/" target="_blank" class="js_other_link"
				data-stat="y_new.footer.other_link">手机QQ空间</a>
		</div>
		<div class="footer_link_list__item">
			<a href="http://im.qq.com/" target="_blank" class="js_other_link"
				data-stat="y_new.footer.other_link">最新版QQ</a>
		</div>
		<div class="footer_link_list__item">
			<a href="http://e.qq.com/index.shtml" target="_blank"
				class="js_other_link" data-stat="y_new.footer.other_link">腾讯社交广告</a>
		</div>
		<div class="footer_link_list__item">
			<a href="http://guanjia.qq.com/" target="_blank"
				class="js_other_link" data-stat="y_new.footer.other_link">电脑管家</a>
		</div>
		<div class="footer_link_list__item">
			<a href="http://browser.qq.com/" target="_blank"
				class="js_other_link" data-stat="y_new.footer.other_link">QQ浏览器</a>
		</div>
		<div class="footer_link_list__item">
			<a href="http://www.weiyun.com/" target="_blank"
				class="js_other_link" data-stat="y_new.footer.other_link">腾讯微云</a>
		</div>
		<div class="footer_link_list__item js_last" style="display: none;">
			<a href="http://fm.qq.com/" target="_blank" class="js_other_link"
				data-stat="y_new.footer.other_link">企鹅FM</a>
		</div>
		<div class="footer_link_list__item">
			<a href="javascript:;" class="js_footer_more">更多</a>
		</div>
	</div>
	<div class="footer_copyright">
		<p>
			<a href="#" rel="nofollow" target="_blank" title="关于腾讯">关于酷炫</a> | <a
				href="#" rel="nofollow" target="_blank" title="About Tencent">About
				Tencent</a> | <a href="#" rel="nofollow" target="_blank" title="服务条款">服务条款</a>
			| <a href="#" rel="nofollow" target="_blank" title="用户服务协议">用户服务协议</a>
			| <a href="#" rel="nofollow" target="_blank" title="隐私政策">隐私政策</a> |
			<a href="#" rel="nofollow" target="_blank" title="广告服务">广告服务</a> | <a
				href="#" rel="nofollow" target="_blank" title="客服中心">客服中心</a> | <a
				href="#" rel="nofollow" target="_blank" title="网站导航">网站导航</a>
		</p>

		<p>
			Copyright &copy; 2016 -
			<script type="text/javascript">
				document.write(new Date().getFullYear());
			</script>
			Tencent. <a target="_blank"
				href="http://www.tencent.com/en-us/le/copyrightstatement.shtml"
				rel="nofollow" title="All Rights Reserved."> All Rights
				Reserved.</a>
		</p>
		<p>
			酷炫音乐 <a target="_blank" href="#" rel="nofollow" title="版权所有">
				版权所有</a> <a target="_blank" href="#" rel="nofollow" title="酷炫音乐网文化经营许可证">酷炫音乐网文化经营许可证</a>
		</p>
	</div>
	</footer>
	<div class="cd-user-modal">
		<div class="cd-user-modal-container">
			<div class="cd-switcher">
				<div>
					<a href="#0">用户登录</a>
				</div>
				<div>
					<a href="#0">注册新用户</a>
				</div>
			</div>

			<div id="cd-login">
				<!-- 登录表单 -->
				<form class="cd-form" action="admin/UserServlet?op=login" method="post">
					<p class="fieldset">
						<label class="image-replace cd-username" for="signin-username">用户名</label>
						<input class="full-width has-padding has-border"
							id="signin-username" name="username" type="text"
							placeholder="输入用户名">
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="signin-password">密码</label>
						<input class="full-width has-padding has-border"
							id="signin-password" name="password" type="password"
							placeholder="输入密码">
					</p>

					<p class="fieldset">
						<input type="checkbox" id="remember-me" checked> <label
							for="remember-me">记住登录状态</label>
					</p>

					<p class="fieldset">
						<input class="full-width2" type="submit" value="登 录">
					</p>
				</form>
			</div>

			<div id="cd-signup">
				<!-- 注册表单 -->
				<form class="cd-form" action="admin/UserServlet?op=regester" method="post">
					<p class="fieldset">
						<label class="image-replace cd-username" for="username">用户名</label>
						<input class="full-width has-padding has-border" id="username"
							name="username" type="text" placeholder="输入用户名">
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="password">密码</label>
						<input class="full-width has-padding has-border" id="password"
							name="password" type="password" placeholder="输入密码">
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="repassword">确认密码</label>
						<input class="full-width has-padding has-border" id="repassword"
							name="repassword" type="password" placeholder="确认密码">
					</p>

					<p class="fieldset">
						<input type="checkbox" id="accept-terms"> <label
							for="accept-terms">我已阅读并同意 <a href="#0"
							style="color: blue">用户协议</a></label>
					</p>

					<p class="fieldset">
						<input class="full-width2" type="submit" value="注册新用户">
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>