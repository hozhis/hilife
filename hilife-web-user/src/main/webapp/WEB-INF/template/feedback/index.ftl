<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/feedback/index.css"/>
<script src="${contextPath}/assets/js/1.0/require.js" type="text/javascript"></script>
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
			<div class="title"><label>帮助与反馈</label></div>
		</div>
		<div class="content">
			<div class="div-search"><input type="text" placeholder="搜索"></div>
			<div class="fb-title" style="background:#ff2d2d;color: #ffffff;"><div style="border-left: 4px solid #ffffff;">热点问题</div></div>
			<ul class="fb-ul">
				<li><div><label>关于智慧生活服务平台</label><span class="fui-keyboard_arrow_right"></span></div></li>
				<li><div><label>阿姨服务期间屋内的财产安全如何保证?</label><span class="fui-keyboard_arrow_right"></span></div></li>
				<li><div><label>关于家政服务的服务范围?</label><span class="fui-keyboard_arrow_right"></span></div></li>
				<li><div><label>下单时的服务时长和服务人员如何确定?</label><span class="fui-keyboard_arrow_right"></span></div></li>
				<li><div><label>关于智慧生活服务平台测试超过长度后的显示问题</label><span class="fui-keyboard_arrow_right"></span></div></li>
				<div style="height:20px;"></div>
			</ul>
			<div class="gap"></div>
			<div class="fb-title"><div>问题分类</div></div>
			<div class="fb-clsy"></div>
		</div>
		<div class="gap2"></div>
	</div>
	<div class="add"><button>意见反馈</button></div>
</body>
<script src="${contextPath}/assets/js/1.0/page/feedback/index.js" type="text/javascript"></script>
</html>