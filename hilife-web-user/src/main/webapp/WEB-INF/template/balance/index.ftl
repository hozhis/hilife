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
				<div id="balance" class="q first-child"><div style="margin-top:20px;"><span class="fui-balance"></span>余额</div><div><label>￥${(balance/100)?string('0.00')}</label></div></div>
				<div id="bankcard" class="q"><div style="margin-top:20px;"><span class="fui-bankcard"></span>银行卡</div><div><label>共2张</label></div></div>
			</div>
			<div class="content-title">最近交易记录</div>
			<div class="deal-duration">
				<div class="border"><div data-id="1" class="active">最近一周</div><div data-id="2">最近一月</div><div data-id="3" style="border:0;width:33.4%;">最近半年</div></div>
				<ul id="transaction" style="border-bottom:1px solid #e0e0e0">
				</ul>
				<!-- <div class="shade hide"><div class="shade-bgd"><span class="fui-spin4 animate-spin"></span></div></div> -->
				<div class="progress hide"><span class="fui-spin5 animate-spin"></span></div>
			</div>
		</div>
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/balance/index.js" type="text/javascript"></script>
</html>