<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/layer-mobile/css/layer.mobile-1.7.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/index/index.css"/>
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
		<div class="left">
			<img src="${contextPath}/assets/img/avatar.png">
			<div><label>您好！</label><label>马天宇</label></div>
		</div>
		<div class="right"><a target="_top" href="${contextPath}/?token=${token}"><span class="fui-notifications"></span></a></div>
		<div class="header-main">今日已服务时长</div>
		<div class="header-body">
			<div><div><span class="fui-dns"></span>今日订单数</div><label>0笔</label></div>
			<div><div><span class="fui-timer"></span>总服务时长</div><label>20h 38m 42s</label></div>
		</div>
	</div>
	<div class="content">
		<div class="order-on-service"><#if orderNum?? && orderNum gt 0><a target="_top" href="${contextPath}/web/order/index?token=${token}"><font>${orderNum}</font>个新订单待处理<font>点此查看</font></a><#else>暂无订单</#if></div>
		<div class="orders">
			<ul>
				<li>
					<img src="${contextPath}/assets/img/avatar.png">
					<div><label>日常保洁</label><label>江苏省南通市崇川区啬园路9号南通大学</label></div>
				</li>
				<li>
					<img src="${contextPath}/assets/img/avatar.png">
					<div><label>冰箱清洗</label><label>浙江省杭州市西湖区紫金花北路望月公寓桂花苑4幢2单元501室</label></div>
				</li>
				<li>
					<img src="${contextPath}/assets/img/avatar.png">
					<div><label>沙发保养</label><label>浙江省杭州市浙江省杭州市西湖区西园一路</label></div>
				</li>
				<li>
					<img src="${contextPath}/assets/img/avatar.png">
					<div><label>地板打蜡</label><label>江苏省南通市江苏省南通市崇川区中南世纪城28号</label></div>
				</li>
				<li>
					<img src="${contextPath}/assets/img/avatar.png">
					<div><label>下水道疏通</label><label>江苏省南通市崇川中南世纪城位于桃园路8号（体育会展中心正北面）</label></div>
				</li>
			</ul>
		</div>
	</div>
</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/index/index.js" type="text/javascript"></script>
</html>
