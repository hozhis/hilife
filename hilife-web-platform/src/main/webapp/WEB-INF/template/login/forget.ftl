<!DOCTYPE html />
<html>
<head>
<title>智慧生活服务平台&nbsp;-&nbsp;管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="icon" type="image/x-icon" href="${contextPath}/favicon.ico"/>
<script src="${contextPath}/assets/js/1.0/require.js" type="text/javascript"></script>
<script type="text/javascript">
	define('global', {
		context: '${rc.contextPath}',
		base_url :'${rc.contextPath}',
		locale:"zh_CN",
		static_url :'${contextPath}'
	})
</script>
<script src="${contextPath}/assets/js/1.0/base.js" type="text/javascript"></script>
</head>
<body class="theme-frost">
	<div id="main-wrapper" class="main-wrapper">
	<#include "/index/head.ftl">
		<div id="content-wrapper" class="content-wrapper">
			<div class="panel" style="margin-top:80px;">
				<div class="panel-heading">
					<!--<span class="glyphicon glyphicon-th-list"></span>-->
					<span class="panel-qams panel-title">账号管理&nbsp;/&nbsp;忘记密码</span>
				</div>
			</div>
			<div class="panel" style="height:500px;">
				<div class="panel-body">
					<div id="btns" style="text-align:center;margin:50 40 100px;">
						<i class="findpassword getAccount" id="findpassword"></i>
					</div>
				</div>
				<div class="" style="text-align:center;margin:30px;" id="part-one">
					<div class="basicInfoDiv">
						<div class="form-search">
							<form id="" class="form-inline basicInfo" action="">
								<div class="block">
									<span>账户名：</span>
									<input id="phone" type="text" class="form-control" name="phone" maxlength="11" placeholder="手机号"/>
								</div>
								<div class="block" style="margin-top:15px">
									<span>验证码：</span>
									<input id="testCodeInput" type="text" class="form-control" name="testCodeInput"  style="width:100px;"/>
									<button type="button" class="btn btn-primary" id="testGetCode" total-value="1" style="width: 127px;">免费获取验证码</button>
								</div>
								<div class="form-group" style="margin-top:10px;height:20px;">
									<span id="tip" style="color:#FF0000"></span>
								</div>
							</form>
						</div>
					</div>
					<div class="group-btn">
						<div class="form-group block">
							<button type="button" class="btn btn-primary" id="next_step">下一步</button>
						</div>
					</div>
				</div>
				<div class="hidden" style="text-align:center;margin:30px;" id="part-two">
					<div class="basicInfoDiv">
						<div class="form-search">
							<form id="" class="form-inline basicInfo" action="">
								<div class="block">
									<span>&nbsp;&nbsp;&nbsp;新密码：</span>
									<input id="password" type="password" class="form-control" name="password" maxlength="16"/>
								</div>
								<div class="block" style="margin-top:15px">
									<span>再次输入：</span>
									<input id="password1" type="password" class="form-control" maxlength="16" name="password1"/>
								</div>
								<div class="form-group" style="margin-top:10px;height:20px;">
									<span id="p-tip" style="color:#FF0000"></span>
								</div>
							</form>
						</div>
					</div>
					<div class="group-btn">
						<div class="form-group block">
							<button type="button" class="btn btn-primary" id="save">提交</button>
						</div>
					</div>	
				</div>
			</div>
		</div>
	</div>
	<script src="${contextPath}/assets/js/1.0/page/login/forget.js" type="text/javascript"></script>
</body>
</html>