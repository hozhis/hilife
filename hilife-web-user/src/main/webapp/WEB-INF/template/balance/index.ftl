<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/balance/index.css"/>
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
			<div class="title"><label>我的钱包</label></div>
		</div>
		<div class="content">
			<div style="height:100px;">
				<div id="balance" class="q first-child"><div style="margin-top:20px;"><span class="fui-balance"></span>余额</div><div><label>￥5.20</label></div></div>
				<div id="bankcard" class="q"><div style="margin-top:20px;"><span class="fui-bankcard"></span>银行卡</div><div><label>共2张</label></div></div>
			</div>
			<div class="content-title">最近交易记录</div>
			<div class="deal-duration">
				<div class="border"><div id="1" class="active">最近一周</div><div id="2">最近一月</div><div id="3" style="border:0;width:33.4%;">最近半年</div></div>
				<ul style="border-bottom:1px solid #e0e0e0">
					<li>
						<div class="d-left"><label>[日常保洁]在线支付</label><label class="d-balance">余额：5.20</label></div>
						<div class="d-right"><label class="d-time">2016-03-26</label><label class="d-money consume">-260.00</label></div>
					</li>
					<li>
						<div class="d-left"><label>支付宝在线充值</label><label class="d-balance">余额：265.20</label></div>
						<div class="d-right"><label class="d-time">2016-03-26</label><label class="d-money deposit">+260.00</label></div>
					</li>
				</ul>
				<div class="shade hide"><div class="shade-bgd"><span class="fui-spin4 animate-spin"></span></div></div>
			</div>
		</div>
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/balance/index.js" type="text/javascript"></script>
</html>