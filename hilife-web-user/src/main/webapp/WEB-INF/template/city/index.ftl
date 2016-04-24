<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/city/index.css"/>
<script src="${contextPath}/assets/js/1.0/require.js" type="text/javascript"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5d69be9174089404ae8f61e384470cfc"></script>
<script type="text/javascript">
	define('global',{
		context : '${rc.contextPath}',
		base_url : '${rc.contextPath}',
		locale : 'zh_CN',
		static_url : '${contextPath}',
		token : '${token}'
	})
</script>
<script src="${contextPath}/assets/js/1.0/base.js" type="text/javascript"></script>
</head>
<body>
	<div class="main-wrapper">
		<div class="header">
			<div class="left"><span class="fui-arrow-left2"></span></div>
			<div class="right"></div>
			<div class="title"><label>当前城市</label></div>
		</div>
		<div class="content">
			<div class="location">
				<div class="loc-title">当前城市</div>
				<div class="loc-city"><label></label></div>
			</div>
			<div class="hot-city">
				<div>热门城市</div>
				<ul>
					<li><label>北京市</label></li>
					<li><label>上海市</label></li>
					<li><label>南京市</label></li>
					<li><label>广州市</label></li>
					<li><label>深圳市</label></li>
					<li><label>杭州市</label></li>
					<li><label>成都市</label></li>
				</ul>
			</div>
			<div class="city-list">
				<!-- <div class="city-clsy">A</div>
				<ul>
					<li><label>安吉市</label></li>
					<li><label>安吉市</label></li>
					<li><label>安吉市</label></li>
					<li><label>安吉市</label></li>
				</ul> -->
			</div>
			<div class="">
				<ul class="alphabet">
					<!-- <li>#</li><li>$</li><li>A</li><li>B</li>
					<li>C</li><li>D</li><li>E</li><li>F</li>
					<li>G</li><li>H</li><li>J</li><li>K</li>
					<li>L</li><li>M</li><li>N</li><li>P</li>
					<li>Q</li><li>R</li><li>S</li><li>T</li>
					<li>W</li><li>X</li><li>Y</li><li>Z</li> -->
				</ul>
			</div>
		</div>
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/city/index.js" type="text/javascript"></script>
</html>