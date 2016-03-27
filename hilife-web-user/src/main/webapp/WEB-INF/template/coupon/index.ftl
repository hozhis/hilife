<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/coupon/index.css"/>
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
			<div class="title"><label>优惠券</label></div>
		</div>
		<div class="content">
			<div class="options">
				<div style="border-right: 1px solid #e0e0e0;"><span class="fui-coupon"></span>兑换优惠券</div>
				<div><span class="fui-gift"></span>邀请有奖</div>
			</div>
			<div class="describe"><div>有<label style="color:#ea0000;">2</label>个优惠券即将过期</div><div id="couponDescribe" style="float:right;color:#0066cc;"><span class="fui-question-circle"></span>优惠券说明</div></div>
			<div class="coupon">
				<ul>
					<li>
						<div class="div-coupon">
							<div class="c-src">￥<label class="price-1">4</label><label class="price-2">0</label></div>
							<div class="c-detail">
								<div>新注册赠送</div>
								<ul><li>满200可用</li><li>限手机客户端在线支付使用</li><li>2016-03-26至2017-03-26</li></ul>
							</div>
						</div>
					</li>
					<li>
						<div class="div-coupon">
							<div class="c-src">￥<label class="price-1">6</label><label class="price-2">0</label></div>
							<div class="c-detail">
								<div>活动赠送</div>
								<ul><li>满240可用</li><li>限手机客户端在线支付使用</li><li>2016-03-26至2017-03-26</li></ul>
							</div>
						</div>
					</li>
				</ul>
				<div class="view-invalid">没有更多优惠券了</div>
			</div>
			<!-- 兑换优惠券组件 -->
			<div class="exchangeCoupon hide">
				<div class="header">
					<div class="left"><span class="fui-arrow-left3"></span></div>
					<div class="right"></div>
					<div class="title"><label>兑换优惠券</label></div>
				</div>
				<div class="con"><input type="text" placeholder="请输入邀请码或优惠码">
				<button class="btn-exchange">兑换</button></div>
			</div>
		</div>
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/coupon/index.js" type="text/javascript"></script>
</html>