<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
    
    <!-- 强制移动设备以app模式打开页面(即在移动设备下全屏，仅支持部分浏览器) -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="full-screen" content="yes"><!--UC强制全屏-->
    <meta name="browsermode" content="application"><!--UC应用模式-->
    <meta name="x5-fullscreen" content="true"><!--QQ强制全屏-->
    <meta name="x5-page-mode" content="app"><!--QQ应用模式-->
    <meta name="generator" content="68喜科技"/>
    <meta name="author" content="罗万财设计"/>
    <meta name="copyright" content="68喜论坛"/>
    <title>酷炫音乐网在线音乐</title>
    <meta name="description" content="68喜在线音乐-可以免除任何VIP限制，可以直接在线听收费的音乐和下载收费音乐，让音乐无处不在！- 68喜科技 "/>
    <meta name="keywords" content="68喜科技,在线音乐,音乐下载,音乐破解,QQ音乐破解下载,网易音乐破解下载"/>
    
    <!-- 不支持IE8及以下版本浏览器 -->
    <!--[if lte IE 8]>
        <script>window.location.href="plugns/killie/"</script>
    <![endif]--> 
    
    <!-- jQuery文件 -->
    <script src="js/jquery.min.js"></script>
    
    <!-- 播放器样式表文件 -->
    <link rel="stylesheet" type="text/css" href="css/player.css">
    
    <!-- 小屏幕样式修复 -->
    <link rel="stylesheet" type="text/css" href="css/small.css">
    
    <!-- 滚动条美化样式文件 -->
    <link rel="stylesheet" type="text/css" href="css/jquery.mCustomScrollbar.min.css">
    
</head>
<body>

<!-- 播放器本体 -->
<audio id="mkplayer" src=""></audio>

<!-- 模糊化背景 -->
<canvas id="blur-canvas"></canvas>
<div id="blur"></div>

<!-- 头部logo -->
<div class="header">
    <div class="logo" title="酷炫音乐网在线音乐">
        <a href="admin/MusicServlet?op=toplist">♫ 酷炫音乐网在线音乐</a>
    </div>
</div>  <!--class="header"-->

<!-- 中间主体区域 -->
<div class="center">
    <div class="container">
        <div class="btn-bar">
            <!-- tab按钮区 -->
            <div class="btn-box" id="btn-area">
                <span class="btn" data-action="player" hidden>播放器</span>
                <span class="btn" data-action="playing" title="正在播放列表">正在播放</span>
                <span class="btn" data-action="sheet" title="音乐播放列表">播放列表</span>
                <span class="btn" data-action="search" title="点击搜索音乐">歌曲搜索</span>
				<!--<a href="http://music.68xi.com/old_music/" target="blank"><span class="btn" title="旧版在线音乐">旧版音乐</span></a><!-->
            </div>
            <!-- 搜索框 -->
            <div class="btn-box" id="search-area" hidden>
                <span class="input">
                    <input type="text" name="wd" id="search-wd" placeholder="搜索歌手、歌名、专辑">
                </span>
                <span class="search-btn search-close" title="点击关闭搜索框">╳</span>
                <span class="search-btn search-submit">搜索</span>
            </div>
        </div>  <!--class="btn-bar"-->
        
        <div class="data-area">
            <!--歌曲歌单-->
            <div id="sheet" class="data-box" hidden></div>
            
            <!--音乐播放列表-->
            <div id="main-list" class="music-list data-box"></div>
        </div>  <!--class="data-area"-->
        
        <!-- 右侧封面及歌词展示 -->
        <div class="player" id="player">
            <!--歌曲封面-->
            <div class="cover">
                <img src="images/player_cover.png" class="music-cover" id="music-cover">
            </div>
            <!--滚动歌词-->
            <div class="lyric">
                <ul id="lyric"></ul>
            </div>
            <div id="music-info" title="点击查看歌曲信息">i</div>
        </div>
    </div>  <!--class="container"-->
</div>  <!--class="center"-->

<!-- 播放器底部区域 -->
<div class="footer">
    <div class="container">
        <div class="con-btn">
            <a href="javascript:;" class="player-btn btn-prev" title="上一首"></a>
            <a href="javascript:;" class="player-btn btn-play" title="暂停/继续"></a>
            <a href="javascript:;" class="player-btn btn-next" title="下一首"></a>
        </div>  <!--class="con-btn"-->
        
        <div class="vol">
            <div class="quiet">
                <a href="javascript:;" class="player-btn btn-quiet" title="静音"></a>
            </div>
            <div class="volume">
                <div class="volume-box">  
                    <div id="volume-progress" class="mkpgb-area"></div>
                </div>
            </div>
        </div>  <!--class="footer"-->
        
        <div class="progress">
            <div class="progress-box">  
                <div id="music-progress" class="mkpgb-area"></div>
            </div>
        </div>  <!--class="progress"-->
    </div>  <!--class="container"-->
</div>  <!--class="footer"-->

<!-- layer弹窗插件 -->
<script src="plugns/layer/layer.js"></script>

<!-- 播放器数据加载模块 -->
<script src="js/ajax.js"></script>

<!-- 播放器歌词解析模块 -->
<script src="js/lyric.js"></script>

<!-- 音乐列表配置 -->
<script src="js/musicList.js"></script>

<!-- 封装函数及ui交互模块 -->
<script src="js/functions.js"></script>

<!-- 播放器主体功能模块 -->
<script src="js/player.js"></script>

<!-- 滚动条美化插件 -->
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>

<!-- 站长统计代码 -->
<div style="display: none;">
                                                                      站点统计：<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1253005508'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s19.cnzz.com/z_stat.php%3Fid%3D1253005508%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script></div>

</body>
</html>