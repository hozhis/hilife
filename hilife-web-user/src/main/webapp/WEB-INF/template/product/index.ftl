<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/product/index.css"/>
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
		<div class="left"><a target="_top" href="${contextPath}/web/city/index?token=${token}"><span class="fui-location"><label>南通</label></span></a></div>
		<div class="right"><span class="fui-notifications"></span></div>
		<div class="title"><label>首页</label></div>
	</div>
	<div class="content">
		<div class="content-title">
			<div class="banner">
				<ul>
					<li><div class="banner-item"><a href="#"><img src="${contextPath}/assets/img/housekeeping1.png"></a></div></li>
					<li><div class="banner-item"><a href="#"><img src="${contextPath}/assets/img/housekeeping2.png"></a></div></li>
				</ul>
			</div>
		</div>
		<div class="content-body">
			<div class="service">
				<div class="service-title red"><label>保洁服务</label></div>
				<ul>
					<li id="service_0006s_0000"><div class="service-clsfy"></div><label>深度保洁</label></li>
					<li id="service_0006s_0001"><div class="service-clsfy"></div><label>日常清洁</label></li>
					<li id="service_0006s_0002"><div class="service-clsfy"></div><label>橱窗清洁</label></li>
					<li id="service_0006s_0003"><div class="service-clsfy"></div><label>厨房清洁</label></li>
					<li id="service_0006s_0004"><div class="service-clsfy"></div><label>新房开荒</label></li>
					<li id="service_0006s_0005"><div class="service-clsfy"></div><label>浴室清洁</label></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="service">
				<div class="service-title blue"><label>维修服务</label></div>
				<ul>
					<li id="service_0005s_0000"><div class="service-clsfy"></div><label>木工</label></li>
					<li id="service_0005s_0001"><div class="service-clsfy"></div><label>泥瓦工</label></li>
					<li id="service_0005s_0002"><div class="service-clsfy"></div><label>水电工</label></li>
					<li id="service_0005s_0003"><div class="service-clsfy"></div><label>电器</label></li>
					<li id="service_0005s_0004"><div class="service-clsfy"></div><label>油漆工</label></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="service">
				<div class="service-title green"><label>电器清洗</label></div>
				<ul>
					<li id="service_0004s_0000"><div class="service-clsfy"></div><label>燃气灶清洗</label></li>
					<li id="service_0004s_0001"><div class="service-clsfy"></div><label>微波炉清洗</label></li>
					<li id="service_0004s_0002"><div class="service-clsfy"></div><label>冰箱清洗</label></li>
					<li id="service_0004s_0003"><div class="service-clsfy"></div><label>油烟机清洗</label></li>
					<li id="service_0004s_0004"><div class="service-clsfy"></div><label>空调清洗</label></li>
					<li id="service_0004s_0005"><div class="service-clsfy"></div><label>洗衣机清洗</label></li>
					<li id="service_0004s_0006"><div class="service-clsfy"></div><label>消毒柜清洗</label></li>
					<li id="service_0004s_0007"><div class="service-clsfy"></div><label>烤箱清洗</label></li>
					<li id="service_0004s_0008"><div class="service-clsfy"></div><label>饮水机清洗</label></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="service">
				<div class="service-title purple"><label>家居保养</label></div>
				<ul>
					<li id="service_0003s_0000"><div class="service-clsfy"></div><label>沙发保养</label></li>
					<li id="service_0003s_0001"><div class="service-clsfy"></div><label>地板打蜡</label></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="service">
				<div class="service-title pink"><label>搬家服务</label></div>
				<ul>
					<li id="service_0002s_0000"><div class="service-clsfy"></div><label>家庭搬家</label></li>
					<li id="service_0002s_0001"><div class="service-clsfy"></div><label>企业搬厂</label></li>
					<li id="service_0002s_0002"><div class="service-clsfy"></div><label>重物吊运</label></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="service">
				<div class="service-title cyan"><label>管道疏通</label></div>
				<ul>
					<li id="service_0001s_0000"><div class="service-clsfy"></div><label>下水道疏通</label></li>
					<li id="service_0001s_0001"><div class="service-clsfy"></div><label>马桶疏通</label></li>
					<li id="service_0001s_0002"><div class="service-clsfy"></div><label>浴缸疏通</label></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="service">
				<div class="service-title yellow"><label>其他服务</label></div>
				<ul>
					<li id="service_0000s_0000"><div class="service-clsfy"></div><label>衣物干洗</label></li>
					<li id="service_0000s_0001"><div class="service-clsfy"></div><label>保姆月嫂</label></li>
					<li id="service_0000s_0002"><div class="service-clsfy"></div><label>除甲醛</label></li>
				</ul>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="gap2"></div>
	<div class="footer">
		<ul>
			<li><div class="f-child"><a target="_top" class="active" href="${contextPath}/web/product/index?token=${token}"><span class="home3"></span>首页</a></div></li>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/life/index?token=${token}"><span class="local_cafe"></span>生活</a></div></li>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/order/index?token=${token}"><span class="dns"></span>订单</a></div></li>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/me/index?token=${token}"><span class="person"></span>我</a></div></li>
		</ul>
		<div class="clear"></div>
	</div>
</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/product/index.js" type="text/javascript"></script>
</html>
