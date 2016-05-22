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
			<div class="left"><span id="arrow-left" class="fui-arrow-left2"></span></div>
			<div class="right"></div>
			<div class="title"><label><#if flagId==1||flagId==12>保洁服务<#elseif flagId==2>维修服务
				<#elseif flagId==3||flagId==9||flagId==10||flagId==11>电器清洗<#elseif flagId==4||flagId==8>家居保养<#elseif flagId==5>搬家服务
				<#elseif flagId==6>管道疏通<#elseif flagId==7||flagId==13||flagId==14>其他服务</#if>&nbsp;-&nbsp;${serviceName}</label></div>
		</div>
		<div class="content">
			<div class="notice">${detail}<span class="fui-arrow-right"></span></div>
			<div class="gap"></div>
			<div class="form-service">
				<form id="" class="form-inline" action="">
					<div id="form-address" class="form-group">
						<span class="fui-location"></span>
						<#if serviceAddress??><label id="serviceAddress" class="form-control" style="color:#000000">${serviceAddress}<#else><label class="form-control">请选择您的服务地址</#if></label>
						<span class="fui-keyboard_arrow_right"></span>
					</div>
					<#if flagId!=14>
					<div id="form-time" class="form-group">
						<span class="fui-calendar"></span>
						<label id="service-time" class="form-control">请选择服务时间</label>
						<span class="fui-keyboard_arrow_right"></span>
					</div></#if>
					<#if flagId==14>
					<div id="form-item" class="form-group">
						<span class="fui-list"></span>
						<label id="service-item" class="form-control" style="line-height:25px;" data-name="服务时长：">请选择服务时长</label>
						<span class="fui-keyboard_arrow_right"></span>
						<ul class="item-ul hide">
							<li value="1">1&nbsp;-&nbsp;3&nbsp;个月</li>
							<li value="2">4&nbsp;-&nbsp;6&nbsp;个月</li>
							<li value="3">7&nbsp;-&nbsp;12&nbsp;个月</li>
							<li value="4">12&nbsp;个月以上</li>
						</ul>
					</div></#if>
					<#if flagId==12>
					<div id="form-item" class="form-group">
						<span class="fui-list"></span>
						<label id="service-item" class="form-control" style="line-height:25px;" data-name="房间面积：">请选择房间面积（单位：m<sup>2</sup>）</label>
						<span class="fui-keyboard_arrow_right"></span>
						<ul class="item-ul hide">
							<li value="1">0&nbsp;-&nbsp;60&nbsp;m<sup>2</sup></li>
							<li value="2">61&nbsp;-&nbsp;120&nbsp;m<sup>2</sup></li>
							<li value="3">121&nbsp;-&nbsp;200&nbsp;m<sup>2</sup></li>
							<li value="4">201&nbsp;m<sup>2</sup>以上</li>
						</ul>
					</div></#if>
					<#if flagId==13>
					<div id="form-item" class="form-group">
						<span class="fui-list"></span>
						<label id="service-item" class="form-control" style="line-height:25px;" data-name="房间面积：">请选择房间面积（单位：m<sup>2</sup>）</label>
						<span class="fui-keyboard_arrow_right"></span>
						<ul class="item-ul hide">
							<li value="1">0&nbsp;-&nbsp;60&nbsp;m<sup>2</sup></li>
							<li value="2">61&nbsp;-&nbsp;120&nbsp;m<sup>2</sup></li>
							<li value="3">121&nbsp;-&nbsp;200&nbsp;m<sup>2</sup></li>
							<li value="4">201&nbsp;m<sup>2</sup>以上</li>
						</ul>
					</div></#if>
					<#if flagId==8>
					<div id="form-item" class="form-group">
						<span class="fui-sofa"></span>
						<label id="service-item" class="form-control" data-name="沙发座次：">请选择沙发座次</label>
						<span class="fui-keyboard_arrow_right"></span>
						<ul class="item-ul hide">
							<li value="1">1&nbsp;座次</li>
							<li value="2">2&nbsp;-&nbsp;3&nbsp;座次</li>
							<li value="3">4&nbsp;-&nbsp;6&nbsp;座次</li>
							<li value="4">7&nbsp;座次以上</li>
						</ul>
					</div></#if>
					<#if flagId==9>
					<div id="form-item" class="form-group">
						<span class="fui-binxiang"></span>
						<label id="service-item" class="form-control" data-name="冰箱型号：">请选择冰箱型号</label>
						<span class="fui-keyboard_arrow_right"></span>
						<ul class="item-ul hide">
							<li value="1">单开门冰箱</li>
							<li value="2">双开门冰箱</li>
							<li value="3">三开门冰箱</li>
							<li value="4">对开门冰箱</li>
						</ul>
					</div></#if>
					<#if flagId==10>
					<div id="form-item" class="form-group">
						<span class="fui-kongtiao"></span>
						<label id="service-item" class="form-control" data-name="空调类型：">请选择空调类型</label>
						<span class="fui-keyboard_arrow_right"></span>
						<ul class="item-ul hide">
							<li value="1">挂式空调</li>
							<li value="2">柜式空调</li>
							<li value="3">中央空调</li>
						</ul>
					</div></#if>
					<#if flagId==11>
					<div id="form-item" class="form-group">
						<span class="fui-xyj"></span>
						<label id="service-item" class="form-control" data-name="洗衣机类型：">请选择洗衣机类型</label>
						<span class="fui-keyboard_arrow_right"></span>
						<ul class="item-ul hide">
							<li value="1">波轮洗衣机</li>
							<li value="2">滚筒洗衣机</li>
						</ul>
					</div></#if>
					<#if flagId==4>
					<div id="form-item" class="form-group">
						<span class="fui-floor"></span>
						<label id="service-item" class="form-control" style="line-height:25px;" data-name="地板面积：">请选择地板面积（单位：m<sup>2</sup>）</label>
						<span class="fui-keyboard_arrow_right"></span>
						<ul class="item-ul hide">
							<li value="1">0&nbsp;-&nbsp;60&nbsp;m<sup>2</sup></li>
							<li value="2">61&nbsp;-&nbsp;120&nbsp;m<sup>2</sup></li>
							<li value="3">121&nbsp;-&nbsp;200&nbsp;m<sup>2</sup></li>
							<li value="4">201&nbsp;m<sup>2</sup>以上</li>
						</ul>
					</div></#if>
					<#if flagId!=2&&flagId!=6&&flagId!=5&&flagId!=14>
					<div id="form-person" class="form-group">
						<span class="fui-user"></span>
						<label id="servicer" class="form-control" data-id="">选择服务人员（默认系统自动分配）</label>
						<span class="fui-keyboard_arrow_right"></span>
					</div></#if>
					<div class="form-group">
						<span class="fui-mail"></span>
						<input id="phone" type="tel" class="form-control" maxlength="11" placeholder="联系方式" data-role="none" value="${phone}">
						<br><hr>
						<span class="fui-new"></span>
						<input id="remark" type="text" class="form-control" placeholder="备注" data-role="none">
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
				<label id="svs-p" class="lb-title">${minHours}小时起，${price/100}元/小时</label>
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
					<div class="num"><label id="svs-d" class="flag"><#if minHours??>${minHours}<#else>1</#if></label></div>
					<button type="button" class="plus" data-role="none"><span class="fui-plus2"></span></button>
				</div>
				<div class="clear"></div>
			</div>
			<input type="text" class="hide" value="2016">
		</div>
	</div>
	<!-- 选择服务人员控件 -->
	<div class="servicer hide">
		<div class="hder">
			<div class="lft"><span id="arrow-left2" class="arrow-back"></span></div>
			<div class="tle"><label>选择服务人员</label></div>
			<div class="rgt"></div>
		</div>
		<div class="content" style="margin:0;"></div>
	你好啊啊啊啊啊</div>
	<div class="hide">
		<input id="productId" value="${productId}">
		<input id="price" value="${price}">
		<input id="consignee" value="${consignee}">
	</div>
</body>
<script src="${contextPath}/assets/js/1.0/page/product/serviceOrder.js" type="text/javascript"></script>
</html>
