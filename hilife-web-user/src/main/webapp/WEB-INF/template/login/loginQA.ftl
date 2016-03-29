<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/coupon/describe.css"/>
<script src="${contextPath}/assets/vendor/jquery/jquery-1.11.0.js" type="text/javascript"></script>
<script src="${contextPath}/assets/vendor/jquery-mobile/js/jquery.mobile-1.4.5.js" type="text/javascript"></script>
</head>
<body>
<div class="main-wrapper">
	<div class="header">
		<div class="left"><span class="fui-arrow-left2"></span></div>
		<div class="right"></div>
		<div class="title"><label>关于账号</label></div>
	</div>
	<div class="content">
		<ul>
			<li>
				<div class="li-title">1.&nbsp;没有账号怎么使用你们的平台？</div>
				<div class="li-content">智慧生活服务平台采用短信验证码的方式登录，无论您之前是否有在我们的相关平台注册过账号，只要你输入手机号和手机短信里面的验证码，即可以正常登录。</div>
			</li>
			<li>
				<div class="li-title">2.&nbsp;验证码收不到怎么办？</div>
				<div class="li-content">请检查一遍您的手机号码重新再试一次，如果手机号码正确且还收不到短信，则查看您的手机是否对我们下发的短信自动拦截。短信验证码也受短信平台等不可控因素影响。</div>
			</li>
		</ul>
	</div>
</div>
</body>
<script type="text/javascript">
	$(".fui-arrow-left2").tap(function(){
		window.history.back();
	});
	$(".content ul>li:last-child").css("border-bottom","0");
</script>
</html>