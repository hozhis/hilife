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
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/me/index.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/me/myinfo.css"/>
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
		<div class="right"><button type="button" class="btn-save hide" data-role="none">保存</button></div>
		<div class="title"><label>个人信息</label></div>
	</div>
	<div class="content">
		<div class="content-body">
			<div class="gap"></div><div class="gap"></div>
			<ul>
				<li style="height:90px;"><div style="height:90px;line-height:90px;"><label>头像</label><#if myinfo.custImage??><img id="upload-img" src="${myinfo.custImage}"><#else><img id="upload-img" src="${contextPath}/assets/img/ico-default.jpg"></#if><span class="fui-keyboard_arrow_right"></span></div>
					<div id="upload" class="hide">
						<div class="u-shade"></div>
						<ul>
							<li id="see-big-pic">查看大图</li>
							<li><div class="file">本地上传新头像<input id="newAvatar" contenteditable="false" type="file" name="file"></div></li>
						</ul>
					</div>
				</li>
				<li><div id="div-name"><label>用户名</label><span id="hide-username" class="hide">${myinfo.username}</span><span id="username" class="svs-rght">${myinfo.username}</span><span class="fui-keyboard_arrow_right"></span></div>
					<div id="update-name" class="hide">
						<input id="ipt-name" data-role="none" type="text" value="${myinfo.username}">
						<button id="btn-name" data-role="none" type="button">确认</button>
					</div>
				</li>
				<li><div><label>手机号码</label><span class="svs-right">${myinfo.loginId}</span></div></li>
				<li><div id="sex"><label>性别</label><span id="hide-sex" class="hide">${myinfo.sex}</span><span id="sex-span" class="svs-rght" data-id="${myinfo.sex}"><#if myinfo.sex==0>男<#elseif myinfo.sex==1>女</#if></span><span class="fui-keyboard_arrow_right"></span></div>
					<ul class="sex-ul hide">
						<li value="1" data-id="0">男</li>
						<li value="2" data-id="1">女</li>
					</ul>
				</li>
			</ul>
			<div class="gap"></div>
			<ul id="city-ul-parent">
				<li><div id="city-div"><label>地区</label><span id="hide-region" class="hide">${myinfo.regionId}</span><span id="regionId" data-id="${myinfo.regionId}" class="svs-rght">${myinfo.region}</span><span class="fui-keyboard_arrow_right"></span></div>
					<div id="city-shade" class="hide"></div>
					<div id="china_citys" class="hide">
						<div id="city-tip"><span id="tip-lft"></span><span id="tip-rgt"></span></div>
						<div id="city-shade-up"></div>
						<div id="city-shade-down"></div>
						<div id="city-shade-auto"></div>
						<div id="select">
							<ul id="province-ul">
								<#list citys?keys as k>
									<li>${k}</li>
								</#list>
							</ul>
							<ul id="city-ul">
								<#list citys["福建省"]?values as v>
									<li>${v}</li>
								</#list>
							</ul>
						</div>
					</div>
				</li>
				<li><div><label>邀请码</label><span class="svs-right">${myinfo.inviteCode}</span></div></li>
			</ul>
		</div>
	</div>
</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/me/myinfo.js" type="text/javascript"></script>
</html>