<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智慧生活服务平台</title>
<meta charset="utf-8" >
<meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport" >
<meta content="telephone=no,email=no,adress=no" name="format-detection" >
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/vendor/flatui/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/common.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/page/coupon/share.css"/>
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
			<div class="title"><label>我的邀请码</label></div>
		</div>
		<div class="content">
			<div class="main-r"></div>
			<div class="l">
				<div style="width:100%;height: 19px;position: relative;"><div><hr></div><div style="width:20%;"><span>邀请码</span></div><div><hr></div></div>
				<div class="inviteCode" style="width: 100%;"><div>7AD85S</div></div>
				<label>有福同享，邀请您的朋友下载APP并注册成功后在 [我-优惠券-兑换优惠券] 输入您的邀请码。一旦您的朋友兑换成功，您的朋友会获得总价值￥60元的优惠券（限新注册用户），您也将获得一张价值￥20元的优惠券。优惠券会自动存入您的账号。</label>
			</div>
			<div class="invite">邀请好友</div>
			<div class="share-item hide">
				<div class="shade"></div>
				<ul>
					<li><span class="fui-qq"></span><label style="color:#56b6e7;">QQ</label></li>
					<li><span class="fui-wechat"></span><label style="color:#7bc549;">微信</label></li>
					<li><span class="fui-moments"></span><label style="margin-top:-5px;color:#fdbe3d;">朋友圈</label></li>
					<li><span class="fui-weibo"></span><label style="margin-top:-5px;color:#ff763b;">微博</label></li>
				</ul>
			</div>
		</div>
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/coupon/share.js" type="text/javascript"></script>
</html>