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
			<div class="left"><span class="fui-arrow-left2"></span></div>
			<div class="right"></div>
			<div class="title"><label>当前订单</label></div>
			<div class="header-option">
				<div><#if orders?? && orders[0].orderStatus==301><div id="time">00:00:00</div><#else></#if></div>
				<div><#if orders?? && orders[0].orderStatus==201><div id="start" class="circle-o">开始计时
					<#elseif orders?? && orders[0].orderStatus==301><div id="finish" class="circle-o">结束计时
					<#else><div class="circle-o">暂无订单</#if></div></div>
			</div>
		</div>
		<div class="content">
			<div>当前订单详情</div>
			<ul>
				<#if orders??>
					<li>订单编号：${orders[0].orderId}<input id="orderId" type="hidden" value="${orders[0].orderId}"></li>
					<li>客户姓名：${orders[0].custName}</li>
					<li>服务地址：${orders[0].serviceAddress}</li>
					<#if orders[0].serviceDto.paraValue1!='undefined'><li>${orders[0].serviceDto.paraValue1}</li></#if>
					<#if orders[0].serviceDto.paraValue2!='undefined'><li>${orders[0].serviceDto.paraValue2}</li></#if>
					<#if orders[0].serviceDto.paraValue3!='undefined'><li>${orders[0].serviceDto.paraValue3}</li></#if>
					<li>${orders[0].serviceDto.remark}</li>
				</#if>
				<li></li>
			</ul>
			<div>订单不能取消，如需取消请联系服务人员！</div>
		</div>
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/order/index.js" type="text/javascript"></script>
</html>