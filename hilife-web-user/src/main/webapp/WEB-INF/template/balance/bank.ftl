<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/balance/bank.css"/>
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
			<div class="title"><label>我的银行卡</label></div>
		</div>
		<div class="content">
			<ul class="ul-bankcard-list">
				<li>
					<div class="bank-logo"></div>
					<div class="bank-info">
						<div class="bank-name">建行银行</div>
						<div class="card-type">储蓄卡</div>
						<div class="card-no">**** **** **** 3984</div>
					</div>
				</li>
			</ul>
			<button class="add-new-bankcard"><span class="fui-plus"></span>添加银行卡</button>
		</div>
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/balance/bank.js" type="text/javascript"></script>
</html>