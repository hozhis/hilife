<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/me/index.css"/>
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
		<div class="left"></div>
		<div class="right"></div>
		<div class="title"><label>我</label></div>
	</div>
	<div style="margin-top:50px;">
		<div class="banner">
			<div class="circle"></div>
		</div>
		<div class="banner-gap"></div>
	</div>
	<div class="gap"></div>
	<div class="content">
		<div class="content-title"></div>
		<div class="content-body">
			<ul>
				<li><div><a target="_top" href=""><span class="fui-person"></span><label>个人信息</label><span class="fui-keyboard_arrow_right"></span></a></div></li>
				<li><div><a target="_top" href="${contextPath}/web/balance/index?token=${token}"><span class="fui-credit-card"></span><label>我的钱包</label><span class="fui-keyboard_arrow_right"></span></a></div></li>
				<li><div><a target="_top" href="${contextPath}/web/coupon/index?token=${token}"><span class="fui-coupon"></span><label>优惠券</label><span class="fui-keyboard_arrow_right"></span></a></div></li>
				<li style="height:44px;"><div><a target="_top" href="${contextPath}/web/address/index?token=${token}"><span class="fui-address"></span><label>地址管理</label><span class="fui-keyboard_arrow_right"></span></a></div></li>
			</ul>
			<div class="gap"></div>
			<ul>
				<li><div><a target="_top" href="tel:4000000000"><span class="fui-service"></span><label>在线客服</label><label class="svs-phone">400-000-0000</label></a></div></li>
				<li style="height:44px;"><div><a target="_top" href="${contextPath}/web/feedback/index?token=${token}"><span class="fui-feedback"></span><label>帮助与反馈</label><span class="fui-keyboard_arrow_right"></span></a></div></li>
			</ul>
			<div class="gap"></div>
			<ul>
				<li style="height:44px;"><div><a id="quit" target="_top" href=""><span class="fui-exit"></span><label>退出此账号</label></a></div></li>
			</ul>
		</div>
	</div>
	<div class="gap2"></div>
	<div class="footer">
		<ul>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/product/index?token=${token}"><span class="home3"></span>首页</a></div></li>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/life/index?token=${token}"><span class="local_cafe"></span>生活</a></div></li>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/order/index?token=${token}"><span class="dns"></span>订单</a></div></li>
			<li><div class="f-child"><a target="_top" class="active" href="${contextPath}/web/me/index?token=${token}"><span class="person"></span>我</a></div></li>
		</ul>
		<div class="clear"></div>
	</div>
</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/me/index.js" type="text/javascript"></script>
</html>