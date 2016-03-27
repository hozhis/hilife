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
				<li><div id="orderAll" class="active">全部</div></li>
				<li><div id="ordering">进行中</div></li>
				<li><div id="paying">待付款</div></li>
				<li><div id="praising">待评价</div></li>
			</ul>
		</div>
		<div class="content-body">
			<ul>
				<li><div class="li-title"><label>[服务]</label>日常保洁</div>
					<div class="li-detail">
						<div class="li-d-1">
							<div class="pic"></div>
							<div class="con">
								<div class="con-1">2016-03-26 13:00</div>
								<div class="con-2">江苏省南通市崇川区啬园路9号江苏省南通市崇川区啬园路9号</div>
							</div>
						</div>
					</div>
					<div class="li-options">
						<button class="highlight" type="button">评价</button>
						<button type="button">查看详情</button>
					</div>
				</li>
				<li><div class="li-title"><label style="color:#4f9d9d;">[商品]</label>生活直达&nbsp;服务到家</div>
					<div class="li-detail">
						<div class="li-d-1">
							<div class="pic"></div>
							<div class="con">
								<div class="con-3"><div>江苏省南通市崇川区啬园路9号江苏省南通市崇川区啬园路9号</div><div class="ctg">江苏省南通市崇川区啬园路9号</div></div>
								<div class="con-4">￥ 9.80<br><label style="color:#9d9d9d;font-size:10px;">x2</label></div>
							</div>
						</div>
					</div>
					<div class="li-options">
						<div class="li-d-2">实付款￥ 19.60元</div>
						<button class="highlight" type="button">评价</button>
						<button type="button">查看详情</button>
					</div>
				</li>
				<li><div class="li-title"><label style="color:#4f9d9d;">[商品]</label>生活直达&nbsp;服务到家</div>
					<div class="li-detail">
						<div class="li-d-1">
							<div class="pic"></div>
							<div class="con">
								<div class="con-3"><div>江苏省南通市崇川区啬园路9号江苏省南通市崇川区啬园路9号</div><div class="ctg">江苏省南通市崇川区啬园路9号</div></div>
								<div class="con-4">￥ 9.80<br><label style="color:#9d9d9d;font-size:10px;">x2</label></div>
							</div>
						</div>
					</div>
					<div class="li-detail">
						<div class="li-d-1">
							<div class="pic"></div>
							<div class="con">
								<div class="con-3"><div>江苏省南通市崇川区啬园路9号江苏省南通市崇川区啬园路9号</div><div class="ctg">江苏省南通市崇川区啬园路9号</div></div>
								<div class="con-4">￥ 9.80<br><label style="color:#9d9d9d;font-size:10px;">x2</label></div>
							</div>
						</div>
					</div>
					<div class="li-options">
						<div class="li-d-2">实付款￥ 19.60元</div>
						<button class="highlight" type="button">评价</button>
						<button type="button">查看详情</button>
					</div>
				</li>
			</ul>
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