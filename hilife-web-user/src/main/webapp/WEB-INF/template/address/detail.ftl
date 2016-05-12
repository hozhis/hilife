<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/layer-mobile/css/layer.mobile-1.7.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/address/detail.css"/>
<script src="${contextPath}/assets/js/1.0/require.js" type="text/javascript"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5d69be9174089404ae8f61e384470cfc"></script>
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
				<ul data-id="${custAddress.addressId}">
					<li class="nav-li"><div class="nav-content">预约人</div><div class="nav-detail"><input id="consignee" type="text" value="${custAddress.consignee}"></div></li>
					<li class="nav-li"><div class="nav-content">联系电话</div><div class="nav-detail"><input id="phone" type="tel" value="${custAddress.phone}" maxlength="11"></div></li>
					<li class="nav-li" style="height:auto;overflow:hidden;"><div class="nav-content">服务地址</div><div id="addressName" class="nav-detail address">${custAddress.addressName}</div></li>
				</ul>
			</div>
			<div id="save" class="add"><button>保存修改</button></div>
		</div>
	</div>
	<!-- 地图 -->
	<div class="map-module hide">
		<div class="content-search" data-role="none">
			<input class="search" type="text" placeholder="请输入小区名" id="searchbox" name="searchbox"  data-role="none">
        	<button class="search-btn" type="button" data-role="none"><span class="fui-search"></span></button>
		</div>
		<div class="result-list" id="result-list"></div>
		<div class="map" id="allmap"></div>
		<i class="marker"></i><i class="marker-shadow"></i>
		<div class="progress hide"><span class="fui-spin5 animate-spin"></span></div>
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/address/detail.js" type="text/javascript"></script>
</html>