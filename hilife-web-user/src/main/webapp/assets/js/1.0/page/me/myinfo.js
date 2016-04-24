require([ 'jquery', 'global', 'jquery.mobile' ],
function($, global) {
	var flag1 = false;//未作修改
	var flag2 = false;
	var flag3 = false;
	var myinfo = {
		bindEvent : function() {
			var _self = this;
			var sex = $("#hide-sex").text();
			var regionId = $("#hide-region").text();
			var username = $("#hide-username").text();
			$(".fui-arrow-left2").tap(function(){
				window.history.back();
			});
			// 性别 弹出控件
			$("#sex").tap(function(){
				if($(".sex-ul").hasClass("hide")){
					$(".sex-ul").removeClass("hide");
					$("#sex").parents().siblings().css("pointer-events","none");
				}else{
					$(".sex-ul").addClass("hide");
					setTimeout(function(){$("#sex").parents().siblings().css("pointer-events","auto");},200);
				}
			});
			// 性别 男 按键事件
			$(".sex-ul>li[value=1]").tap(function(){
				if(sex != $(this).attr("data-id")){
					flag1 = true;
				}else{
					flag1 = false;
				}
				if(flag1 || flag2 || flag3){
					$(".btn-save").removeClass("hide");
				}else{
					$(".btn-save").addClass("hide");
				}
				$("#sex-span").text($(this).text()).attr("data-id","0");
				$(".sex-ul").addClass("hide");
				setTimeout(function(){$("#sex").parents().siblings().css("pointer-events","auto");},200);
			});
			// 性别 女 按键事件
			$(".sex-ul>li[value=2]").tap(function(){
				if(sex != $(this).attr("data-id")){
					flag1 = true;
				}else{
					flag1 = false;
				}
				if(flag1 || flag2 || flag3){
					$(".btn-save").removeClass("hide");
				}else{
					$(".btn-save").addClass("hide");
				}
				$("#sex-span").text($(this).text()).attr("data-id","1");
				$(".sex-ul").addClass("hide");
				setTimeout(function(){$("#sex").parents().siblings().css("pointer-events","auto");},200);
			});
			// 地区控件  关闭事件
			function handler(){
				if(regionId != $("#city-tip").attr("data-id")){
					flag2 = true;
				}else{
					flag2 = false;
				}
				if(flag1 || flag2 || flag3){
					$(".btn-save").removeClass("hide");
				}else{
					$(".btn-save").addClass("hide");
				}
				$("#city-shade").addClass("hide");
				$("#china_citys").addClass("hide");
				$(".content-body").removeClass("fixed");
				$("#regionId").text($("#city-tip").text()).attr("data-id",$("#city-tip").attr("data-id"));
				setTimeout(function(){$("#city-div").parents().siblings().css("pointer-events","auto");},200);
			}
			// 地区控件 按键事件
			$("#city-div").tap(function(){
				$("#city-div").parents().siblings().css("pointer-events","none");
				$("#city-shade").removeClass("hide");
				$("#china_citys").removeClass("hide");
				$(".content-body").addClass("fixed");
				_self.cityPicker();
				$("#city-shade").unbind("tap");
				setTimeout(function(){
					$("#city-shade").tap(handler);
				},200);
			});
			//$("#china_citys").text(JSON.stringify(china_citys));
			$("#div-name").tap(function(){
				if($("#update-name").hasClass("hide")){
					$("#update-name").removeClass("hide");
					$("#update-name").parents().siblings().css("pointer-events","none");
				}else{
					$("#update-name").addClass("hide");
					setTimeout(function(){$("#update-name").parents().siblings().css("pointer-events","auto");},200);
				}
			});
			$("#btn-name").tap(function(){
				if(username != $("#ipt-name").val()){
					flag3 = true;
				}else{
					flag3 = false;
				}
				if(flag1 || flag2 || flag3){
					$(".btn-save").removeClass("hide");
				}else{
					$(".btn-save").addClass("hide");
				}
				$("#username").text($("#ipt-name").val());
				$("#update-name").addClass("hide");
				setTimeout(function(){$("#update-name").parents().siblings().css("pointer-events","auto");},200);
			});
		},
		cityPicker : function(){
			var tipH = $("#city-tip").height();
			$("#city-tip").css("line-height",tipH + 'px');
			var csaX = $("#city-shade-auto").offset().top;
			var csaY = $("#city-shade-auto").offset().left;
			var csaH = $("#city-shade-auto").height();
			var csaM = parseInt(csaX + csaH / 2);
			var w = $("#city-shade-auto").width();
			var csaML = parseInt(w / 4);
			var csaMR = parseInt(w / 4 * 3);
			console.log(csaM + ", " + csaML);//左边中心点(csaM,csaML)
			console.log(csaM + ", " + csaMR);//右边中心点(csaM,csaMR)
			$("#province-ul").scroll(function(){
				var fstLX = $("#province-ul>li:first-child").offset().top;
				var H = $("#province-ul>li").height();
				var num = parseInt((csaM - fstLX) / H);
				//console.log($("#province-ul li").eq(num).text());
				$("#tip-lft").html($("#province-ul li").eq(num).text());
			});
			$("#city-ul").scroll(function(){
				var fstLX = $("#city-ul>li:first-child").offset().top;
				var H = $("#city-ul>li").height();
				var num = parseInt((csaM - fstLX) / H);
				//console.log($("#city-ul li").eq(num).text());
				$("#tip-rgt").html(",&nbsp;" + $("#city-ul li").eq(num).text());
				$("#city-tip").attr("data-id",$("#city-ul li").eq(num).attr("data-id"));
			});
		},
		init : function() {
			var _self = this;
			_self.bindEvent();
		}
	};
	myinfo.init();
});