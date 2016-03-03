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
		static_url : '${contextPath}'
	})
</script>
<script src="${contextPath}/assets/js/1.0/base.js" type="text/javascript"></script>
</head>
<body>
<div class="main-wrapper">
	<div class="header">
		<div class="left"><span class="fui-location"><label>南通</label></span></div>
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
					<li><div class="service-clsfy red"><span class="fui-qingjieji"></span></div><label>深度保洁</label></li>
					<li><div class="service-clsfy red"><span class="fui-dasao"></span></div><label>日常清洁</label></li>
					<li><div class="service-clsfy red"><span class="fui-chuanghu"></span></div><label>橱窗清洁</label></li>
					<li><div class="service-clsfy red"><span class="fui-chufang"></span></div><label>厨房清洁</label></li>
					<li><div class="service-clsfy red"><span class="fui-house"></span></div><label>新房开荒</label></li>
					<li><div class="service-clsfy red"><span class="fui-shower2"></span></div><label>浴室清洁</label></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="service">
				<div class="service-title blue"><label>维修服务</label></div>
				<ul>
					<li><div class="service-clsfy blue"><span class="fui-mugong"></span></div><label>木工</label></li>
					<li><div class="service-clsfy blue"><span class="fui-shuini"></span></div><label>泥瓦工</label></li>
					<li><div class="service-clsfy blue"><span class="fui-dianxian"></span></div><label>水电工</label></li>
					<li><div class="service-clsfy blue"><span class="fui-kaoxiang"></span></div><label>电器</label></li>
					<li><div class="service-clsfy blue"><span class="fui-youqi"></span></div><label>油漆工</label></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="service">
				<div class="service-title green"><label>电器清洗</label></div>
				<ul>
					<li><div class="service-clsfy green"><span class="fui-zaotai"></span></div><label>燃气灶清洗</label></li>
					<li><div class="service-clsfy green"><span class="fui-weibolu"></span></div><label>微波炉清洗</label></li>
					<li><div class="service-clsfy green"><span class="fui-bingxiang"></span></div><label>冰箱清洗</label></li>
					<li><div class="service-clsfy green"><span class="fui-youyanji"></span></div><label>油烟机清洗</label></li>
					<li><div class="service-clsfy green"><span class="fui-kongtiao2"></span></div><label>空调清洗</label></li>
					<li><div class="service-clsfy green"><span class="fui-xiyiji"></span></div><label>洗衣机清洗</label></li>
					<li><div class="service-clsfy green"><span class="fui-xiaoduji"></span></div><label>消毒柜清洗</label></li>
					<li><div class="service-clsfy green"><span class="fui-kaoxiang2"></span></div><label>烤箱清洗</label></li>
					<li><div class="service-clsfy green"><span class="fui-yingshuiji"></span></div><label>饮水机清洗</label></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="service">
				<div class="service-title pink"><label>搬家服务</label></div>
				<ul>
					<li><div class="service-clsfy pink"><span class="fui-banjia"></span></div><label>家庭搬家</label></li>
					<li><div class="service-clsfy pink"><span class="fui-banchang"></span></div><label>企业搬厂</label></li>
					<li><div class="service-clsfy pink"><span class="fui-diaoche"></span></div><label>重物吊运</label></li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="gap"></div>
			<div class="service">
				<div class="service-title yellow"><label>其他服务</label></div>
				<ul>
					<li><div class="service-clsfy yellow"><span class="fui-ganxi"></span></div><label>衣物干洗</label></li>
					<li><div class="service-clsfy yellow"><span class="fui-ayi"></span></div><label>保姆月嫂</label></li>
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
			<li><div class="f-child"><a target="_top" href="#"><span class="dns"></span>订单</a></div></li>
			<li><div class="f-child"><a target="_top" href="${contextPath}/web/me/index?token=${token}"><span class="person"></span>我</a></div></li>
		</ul>
		<div class="clear"></div>
	</div>
</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/product/index.js" type="text/javascript"></script>
</html>
