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
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/login/login.css"/>
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
		<span id="arrow-left" class="fui-arrow-left2 hide"></span>
	</div>
	<div class="content">
		<div class="login-title">
			<label class="label">登录</label>
		</div>
		<div class="progressbar hide"></div>
		<div class="login-body">
			<div id="pageone">
				<div class="login-phone">
					<label>输入您的手机号码</label>
					<input data-role="none" class="phone" type="tel" maxlength="11"  placeholder="输入您的手机号码" id="username" name="username" required />
				</div>
				<div class="login-warn hide">请输入手机号码</div>
				<div class="login-remark">关于账号方面的帮助？</div>
				<div class="login-btn"><button class="nextstep">下一步</button></div>
				<div class="clear"></div>
			</div>
			<div class="hide" id="pagetwo">
				<div class="login-validcode">
					<label>输入您的验证码</label>
					<input class="validcode" type="text" maxlength="6"  placeholder="输入您的验证码" id="validcode" name="validcode"/>
				</div>
				<div class="login-remark">未收到验证码？</div>
				<div class="login-btn"><button class="submit">登录</button></div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div class="clear"></div>
</body>
<script src="${contextPath}/assets/js/1.0/page/login/login.js" type="text/javascript"></script>
</html>
