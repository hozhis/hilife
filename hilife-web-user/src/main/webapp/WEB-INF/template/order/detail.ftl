<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
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
			<div ><label class="o-name">杨洋</label><label class="o-phone">188****7819</label></div>
			<div class="o-address"><span class="fui-location"></span>江苏省南通市崇川区啬园路9号南通大学</div>
		</div>
		<div class="gap"></div>
		<div class="o-body">
			<div class="o-b-t"><label>[服务]</label>好生活平台</div>
			<div class="o-b-body">
				<img src="${contextPath}/assets/img/logo/logo_0006s_0000.png">
				<ul>
					<li>服务类型：日常保洁</li>
					<li>服务人数：3人</li>
					<li>服务时间：2016年5月10日 13：00</li>
					<li>备注：日常保洁</li>
				</ul>
			</div>
		</div>
		<div class="gap"></div>
		<div class="o-body-goods">
			<div class="o-b-t"><label style="color:#4f9d9d;">[商品]</label>生活直达&nbsp;服务到家</div>
			<div class="li-d-1">
					<img src="http://www.maigusoft.com/image/fish.jpg" class="pic">
					<div class="con" style="width: 230px;">
						<div class="con-3">
							<div>【聚百鲜】日本料理/鲷鱼片刺身鲷鱼 营养丰富老少皆宜 鱼 海鲜 </div>
							<div class="ctg">null</div>
						</div>
						<div class="con-4">￥ 9.8<br><label style="color:#9d9d9d;font-size:10px;">x1</label></div>
					</div>
				</div>
		</div>
		<div class="o-status">
			<div>订单信息</div>
			<ul>
				<li>订单号：<label>1000000014</label></li>
				<li>下单日期：<label>2016-05-10 22:42:58</label></li>
				<li>下单人：<label>洪志胜</label></li>
			</ul>
		</div>
		<div class="gap"></div>
		<div class="o-status">
			<div>支付信息</div>
			<ul>
				<li>支付方式：<label>在线支付</label></li>
				<li>订单总额：<label id="o-s-m">50元</label></li>
			</ul>
		</div>
		<div class="gap2"></div>
		<div class="o-option">
			<button class="highlight" type="button" data-role="none">支付</button>
			<button type="button" data-role="none">评价</button>
			<button type="button" data-role="none">取消</button>
		</div>
	</div>
</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/order/detail.js" type="text/javascript"></script>
</html>