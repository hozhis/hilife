<!DOCTYPE html>
<html>
<head>
<title>智慧生活服务平台&nbsp;-&nbsp;管理系统</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<link rel="icon" type="image/x-icon" href="${contextPath}/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/login/login.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/font-awesome/css/font-awesome.min.css"/>
<script src="${contextPath}/assets/js/1.0/require.js" type="text/javascript"></script>
<script type="text/javascript">
    define('global', {
        context: '${rc.contextPath}',
        base_url :'${rc.contextPath}',
        locale:"zh_CN",
        static_url :　'${contextPath}'
    })
</script>
<script src="${contextPath}/assets/js/1.0/base.js" type="text/javascript"></script>
</head>
<body>
	<div class="login-form">
		<div class="head-info">
			<span>智慧生活服务平台</span>
			<label class="lbl-1"></label><label class="lbl-2"></label><label class="lbl-3"></label>
		</div>
		<div class="clear"></div>
		<div class="avtar">
			<img src="${contextPath}/assets/img/avatar.png" />
		</div>
		<form>
			<input type="text" class="username" value="用户名" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '用户名';}">
			<input type="password" class="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}">
			<div class="pull"><input type="text" class="captcha" value="验证码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '验证码';}">
			<img id="captchaImage" class="code_img" title="点击更换验证码" /></div>
		</form>
		<div class="clear"></div>
		<a href="${contextPath}/web/auth/forget" class="">忘记密码？</a>
		<button id="login_btn" type="submit">登&nbsp;&nbsp;录</button>
		<input type="hidden" name="key" id="key">
	</div>
	<div class="copy-rights">
		<p>Copyright &copy; 2016 dolphinsoft.cn. All rights reserved.</p>
	</div>
</body>
<script type="text/javascript" src="${contextPath}/assets/js/1.0/page/login/login.js"></script>
</html>