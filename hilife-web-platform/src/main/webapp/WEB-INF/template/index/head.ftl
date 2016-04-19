<!DOCTYPE html />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="icon" type="image/x-icon" href="${contextPath}/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/jqgrid/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/index/head.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/pnotify/jquery.pnotify.default.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/bootstrap-datatimepicker/css/bootstrap-datetimepicker.min.css" />
</head>
<body class="theme-frost">
	<div id="main-wrapper" class="main-wrapper">
		<div id="main-navbar" class="navbar navbar-inverse" role="navigation">
			<div class="navbar-inner">
				<!-- Main navbar header -->
				<div class="navbar-header">
					<!-- Logo -->
					<div id="logo" class="logo"></div>
					<span class="sys-name">智慧生活服务平台管理系统</span>
					<div class="right">
						<img src="${contextPath}/assets/img/avatar.png" width="30" height="30">
						<div class="usename" id="name-container">
							<span id="username"></span> <span class="caret"></span>
							<ul class="name-select" id="name-select" style="display: none;">
								<li style="position: relative"><i class="tri"></i></li>
								<li style="border-bottom:1px solid #E6E6E6;"><a href="${contextPath}/web/userManager/modifyAccount?token=${token}">账户修改</a></li>
								<li><a href="#" id="logout">退出</a></li>
							</ul>
						</div>
						<span class="ling-msg">
							<a href="${contextPath}/web/platform/systemNotice/index?token=${token}">
								<i id="ding" class="fa fa-bell icon-animated-bell"></i>
								<b id="unread-num" class="head-unread"></b>
							</a>
						</span>
					</div>
				</div>
				<!-- / .navbar-header -->
			</div>
			<!-- navbar inner -->
		</div>
	</div>
</body>
</html>