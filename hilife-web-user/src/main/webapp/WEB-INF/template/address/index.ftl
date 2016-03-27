<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/address/index.css"/>
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
			<div class="title"><label>地址管理</label></div>
		</div>
		<div class="content">
			<div class="nav">
				<ul>
					<!-- <li class="nav-li">
						<div class="front"><div class="ln-1"><label class="lb-left">马天宇</label><label class="lb-right">18862947819</label>
						</div><div class="ln-2"><label class="default">[默认]</label>江苏省南通市崇川区啬园路9号南通大学江苏省南通市崇川区啬园路9号</div></div>
						<div class="behind">
							<ul>
								<li style="width: 40%;"></li>
								<li style="background: silver">修改</li>
								<li style="background: orange"><div style="padding: 13px 0px; line-height: 24px;">取消<br>默认</div></li>
								<li style="background: red">删除</li>
							</ul>
						</div>
					</li>
					<li class="nav-li">
						<div class="front"><div class="ln-1"><label class="lb-left">杨洋</label><label class="lb-right">18862947820</label>
						</div><div class="ln-2">江苏省南通市崇川区啬园路9号南通大学</div></div>
						<div class="behind">
							<ul>
								<li style="width: 40%;"></li>
								<li style="background: silver">修改</li>
								<li style="background: orange"><div style="padding: 13px 0px; line-height: 24px;">设为<br>默认</div></li>
								<li style="background: red">删除</li>
							</ul>
						</div>
					</li> -->
				</ul>
				<div class="gap2"></div>
				<div class="add"><button>添加新地址</button></div>
			</div>
		</div>
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/address/index.js" type="text/javascript"></script>
</html>