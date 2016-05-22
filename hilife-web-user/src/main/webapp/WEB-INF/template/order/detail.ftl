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
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/order/detail.css"/>
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
		<div class="title"><label>订单详情</label></div>
	</div>
	<div class="content">
		<div class="o-r-l">
			<div ><label class="o-name">${order.custName}</label><label class="o-phone">${order.phone?substring(0,3)}****${order.phone?substring(7)}</label></div>
			<div class="o-address"><span class="fui-location"></span>${order.serviceAddress}</div>
		</div>
		<div class="gap"></div>
		<#if order.orderType==1>
		<div class="o-body">
			<div class="o-b-t"><label>[服务]</label>好生活平台</div>
			<div class="o-b-body">
				<img src="${contextPath}/assets/img/logo/logo_${order.list[0].productDto.image}.png">
				<ul>
					<li>服务类型：${order.list[0].productDto.productName}</li>
					<#if order.list[0].num??><li>服务人数：${order.list[0].num}人</li></#if>
					<#if order.serviceDto.paraValue1!="undefined"><li>${order.serviceDto.paraValue1}</li></#if>
					<#if order.serviceDto.paraValue2!="undefined"><li>${order.serviceDto.paraValue2}</li></#if>
					<li>${order.serviceDto.remark}</li>
				</ul>
			</div>
		</div>
		<div class="gap"></div>
		</#if>
		<#if order.orderType==0>
		<div class="o-body-goods">
			<div class="o-b-t"><label style="color:#4f9d9d;">[商品]</label>生活直达&nbsp;服务到家</div>
			<#list order.list as product>
			<div class="li-d-1">
				<img src="${product.productDto.image}" class="pic">
				<div class="con" style="width: 230px;">
					<div class="con-3">
						<div>${product.productDto.productName}</div>
						<div class="ctg">${product.productDto.remark}</div>
					</div>
					<div class="con-4">￥ ${(product.price/100)?string('0.00')}<br><label style="color:#9d9d9d;font-size:10px;">x${product.num}</label></div>
				</div>
			</div>
			</#list>
		</div>
		</#if>
		<div class="o-status">
			<div>订单信息</div>
			<ul>
				<li>订单号：<label id="orderId">${order.orderId}</label></li>
				<li>下单日期：<label>${order.createDate}</label></li>
				<li>下单人：<label>${username}</label></li>
			</ul>
		</div>
		<div class="gap"></div>
		<div class="o-status">
			<div>支付信息</div>
			<ul>
				<li>支付方式：<label>在线支付</label></li>
				<li>订单总额：<label id="o-s-m">${(order.totalAmount/100)?string('0.00')}元</label></li>
			</ul>
		</div>
		<div class="gap2"></div>
		<div class="o-option">
			<#if order.orderStatus==501><button class="highlight" type="button" data-role="none">支付</button></#if>
			<#if order.orderStatus==601><button class="btn-evaluate highlight" type="button" data-role="none">评价</button></#if>
			<#if order.orderStatus==101><button class="btn-cancel highlight" type="button" data-role="none">取消</button></#if>
			<#if order.orderStatus==701||order.orderStatus==801><button class="btn-delete" type="button" data-role="none">删除订单</button></#if>
			<#if order.orderStatus==801><div class="order-status">订单已取消</div></#if>
		</div>
	</div>
</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/order/detail.js" type="text/javascript"></script>
</html>