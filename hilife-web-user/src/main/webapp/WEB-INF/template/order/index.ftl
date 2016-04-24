<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/order/index.css"/>
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
		<div class="title"><label>订单</label></div>
	</div>
	<div class="content">
		<div class="content-clsy">
			<ul>
				<li><div id="orderAll" class="active" data-id="">全部</div></li>
				<li><div id="ordering" data-id="">进行中</div></li>
				<li><div id="paying" data-id="">待付款</div></li>
				<li><div id="praising" data-id="">待评价</div></li>
			</ul>
		</div>
		<div class="content-body">
			<ul class="order-ul"></ul>
			<div class="d-t">没有更多订单了</div>
		</div>
	</div>
	<div class="gap2"></div>
	<div class="footer">
		<ul>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/product/index?token=${token}"><span class="home3"></span>首页</a></div></li>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/life/index?token=${token}"><span class="local_cafe"></span>生活</a></div></li>
			<li><div class="f-child"><a target="_top" class="active" href="${contextPath}/web/order/index?token=${token}"><span class="dns"></span>订单</a></div></li>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/me/index?token=${token}"><span class="person"></span>我</a></div></li>
		</ul>
		<div class="clear"></div>
	</div>
</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/order/index.js" type="text/javascript"></script>
</html>