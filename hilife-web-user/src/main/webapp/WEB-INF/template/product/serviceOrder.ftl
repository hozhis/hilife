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
		<div class="content">
			<div class="notice">订单详情页 serviceId:${serviceId}<span class="fui-arrow-right"></span></div>
			<div class="gap"></div>
			<div class="form-service">
				<form id="" class="form-inline" action="">
					<div id="form-address" class="form-group">
						<span class="fui-location"></span>
						<#if serviceAddress??><label class="form-control" style="color:#000000">${serviceAddress}<#else><label class="form-control">请选择您的服务地址</#if></label>
						<span class="fui-keyboard_arrow_right"></span>
					</div>
					<div id="form-time" class="form-group">
						<span class="fui-calendar"></span>
						<label id="service-time" class="form-control">请选择服务时间</label>
						<span class="fui-keyboard_arrow_right"></span>
					</div>
					<div id="form-person" class="form-group">
						<span class="fui-user"></span>
						<label class="form-control">选择服务人员（默认系统自动分配）</label>
						<span class="fui-keyboard_arrow_right"></span>
					</div>
					<div class="form-group">
						<span class="fui-mail"></span>
						<input type="tel" class="form-control" maxlength="11" placeholder="联系方式" data-role="none">
						<br><hr>
						<span class="fui-new"></span>
						<input type="text" class="form-control" placeholder="备注" data-role="none">
					</div>
					<div class="gap2"></div>
					<button id="submit" type="button" class="btn btn-primary" data-role="none">提交</button>
				</form>
			</div>
		</div>
	</div>
	<!-- Tips 通知控件 -->
	<div class="notice-detail hide">
		<div class="header">
			<div class="left"></div>
			<div class="right"><span class="fui-cross"></span></div>
			<div class="title-"><label>服务说明</label></div>
		</div>
		<div class="content">
			<div class="service-item">
				<img id="svs-i" class="item-icon" src="${contextPath}/assets/img/logo/logo_${logo}.png">
				<label id="svs-t">${serviceName}</label>
				<label id="svs-p" class="lb-title">${minHours}小时起，${price}元/小时</label>
			</div>
			<div class="service-detail">
				<label class="lb-title">服务说明：</label>
				<label id="svs-c">${detail}</label>
				<div class="gap3"></div>
				<label class="lb-title">服务时间选择参考</label>
				<label id="svs-r">${remark}</label>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<!-- 日期弹出控件 -->
	<div class="daterpicker hide">
		<div class="shape"></div>
		<div class="datetime">
			<div class="ops">
				<button type="button" class="close" data-role="none">取消</button>
				<button type="button" class="confirm" data-role="none">确定</button>
			</div>
			<div class="time-t">
				<div class="time-title"><span>预约时间</span></div>
				<div class="date">
					<span class="fui-left"></span>
					<ul id="date-ul">
						<li id="d0" class="active"></li>
						<li id="d1"></li>
						<li id="d2"></li>
						<li id="d3"></li>
					</ul>
					<span class="fui-right"></span>
				</div>
				<div class="time">
					<ul>
						<li><div class="time-item active">7:00<div class="check"><span class="fui-check"></span></div></div></li>
						<li><div class="time-item">7:30<div class="check hide"><span class="fui-check"></span></div></div></li>
						<li><div class="time-item">8:00<div class="check hide"><span class="fui-check"></span></div></div></li>
						<li><div class="time-item">8:30<div class="check hide"><span class="fui-check"></span></div></div></li>
						<li><div class="time-item">9:00<div class="check hide"><span class="fui-check"></span></div></div></li>
						<li><div class="time-item">9:30<div class="check hide"><span class="fui-check"></span></div></div></li>
						<li><div class="time-item">13:00<div class="check hide"><span class="fui-check"></span></div></div></li>
						<li><div class="time-item">13:30<div class="check hide"><span class="fui-check"></span></div></div></li>
						<li><div class="time-item">14:00<div class="check hide"><span class="fui-check"></span></div></div></li>
						<li><div class="time-item">14:30<div class="check hide"><span class="fui-check"></span></div></div></li>
					</ul>
					<div class="clear"></div>
				</div>
			</div>
			<div class="per-c">
				<div class="t-t"><span>服务人数</span></div>
				<div class="e-o">
					<button type="button" class="minus" data-role="none"><span class="fui-minus"></span></button>
					<div class="num"><label id="svs-p">1</label></div>
					<button type="button" class="plus" data-role="none"><span class="fui-plus2"></span></button>
				</div>
				<div class="clear"></div>
			</div>
			<div class="per-c">
				<div class="t-t"><span>服务时长</span></div>
				<div class="e-o">
					<button type="button" class="minus" data-role="none"><span class="fui-minus"></span></button>
					<div class="num"><label id="svs-d" class="flag">3</label></div>
					<button type="button" class="plus" data-role="none"><span class="fui-plus2"></span></button>
				</div>
				<div class="clear"></div>
			</div>
			<input type="text" class="hide" value="2016">
		</div>
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/product/serviceOrder.js" type="text/javascript"></script>
</html>
