<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/product/serviceOrder.css"/>
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
		<div class="title"><label>${serviceName}</label></div>
	</div>
订单详情页 serviceId:${serviceId}
	<div data-role="page">
		<div data-role="header">
	    	<h1>欢迎访问我的主页</h1>
	  	</div>
	
		<div data-role="content">
			<p>我是一名移动开发者！</p>
		</div>
	
		<div data-role="footer">
	    	<h1>页脚文本</h1>
		</div>
	</div>
	
</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/product/serviceOrder.js" type="text/javascript"></script>
</html>
