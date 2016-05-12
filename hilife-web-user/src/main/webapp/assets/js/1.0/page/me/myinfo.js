require([ 'jquery', 'global', 'jquery.mobile','pnotify','ajaxfileupload' ],
function($, global) {
	var flag1 = false;//未作修改
	var flag2 = false;
	var flag3 = false;
	var citysJson;
	var myinfo = {
		bindEvent : function() {
			var _self = this;
			var sex = $("#hide-sex").text();
			var regionId = $("#hide-region").text();
			var username = $("#hide-username").text();
			$(".fui-arrow-left2").tap(function(){
				window.location.replace(global.context + "/web/me/index?token=" + global.token);
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
				setTimeout(function(){
					$(".sex-ul").addClass("hide");
					$("#sex").parents().siblings().css("pointer-events","auto");
				},200);
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
				setTimeout(function(){
					$(".sex-ul").addClass("hide");
					$("#sex").parents().siblings().css("pointer-events","auto");
				},200);
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
				setTimeout(function(){
					$("#city-shade").addClass("hide");
					$("#china_citys").addClass("hide");
					$(".content-body").removeClass("fixed");
				},200);
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
			$("#div-name").tap(function(){
				if($("#update-name").hasClass("hide")){
					$("#update-name").removeClass("hide");
					$("#update-name").parents().siblings().css("pointer-events","none");
				}else{
					$("#update-name").addClass("hide");
					setTimeout(function(){$("#update-name").parents().siblings().css("pointer-events","auto");},200);
				}
			});
			$("#upload-img").tap(function(){
				$("#upload").removeClass("hide");
				$(".u-shade").unbind("tap");
				setTimeout(function(){
					$(".u-shade").tap(function(){
						setTimeout(function(){$("#upload").addClass("hide");},200);
					});
				},500);
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
			$(".btn-save").tap(function(){
				_self.save();
			});
			$("#newAvatar").change(function(){
				_self.uploadImage();
			});
			$("#see-big-pic").tap(function(){
				$("#upload").addClass("hide");
				var html = "<span><img src='" + $("#upload-img").attr("src") +"'></span>";
				var pageii = layer.open({
				    type: 1,
				    content: html,
				    style: 'position:fixed;left:0;top:0;width:100%;height:100%;border:none;background-color:#000000;z-index:999;position:fixed;'
				});
				setTimeout(function(){
					$(".layermcont").tap(function(){
						layer.closeAll();
					});
				},500);
			});
		},
		getCitys : function(){
			$.ajax({
				type : "GET",
				url : global.context + "/web/me/getCitys?token=" + global.token,
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				success : function(msg) {
					citysJson = msg;
					//$("#china_citys").text(JSON.stringify(citysJson));
					//return citysJson;
				}
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
		save : function(){
			var data = {
				token : global.token,
				username : $("#username").text(),
				sex : $("#sex-span").attr("data-id"),
				regionId : $("#regionId").attr("data-id")
			}
			$.ajax({
				type : "POST",
				url : global.context + "/web/me/save",
				data : JSON.stringify(data),
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				success : function(msg) {
					if(msg.code == "ACK"){
						$(".btn-save").addClass("hide");
						layer.open({content: '保存成功', time: 1});
						/*setTimeout(function(){
							window.location.replace(global.context + "/web/address/index?token=" + global.token);
						},500);*/
					}
				}
			});
		},
		uploadImage : function(){
			$.ajaxFileUpload({
				url : global.context + "/web/me/uploadImage?token=" + global.token,
				fileElementId : ["newAvatar"],
				dataType : "json",
				success : function(msg){
					if(msg.code == "ACK"){
						layer.open({content: '上传成功', time: 1});
						$("#upload").addClass("hide");
						setTimeout(function(){window.location.reload();},500);
					}
				}
			});
		},
		init : function() {
			var _self = this;
			_self.getCitys();
			_self.bindEvent();
			$("#province-ul").scroll(function(){
				var _city = "";
				for(var city in citysJson){
					if(city == $("#tip-lft").text()){
						for(var regionId in citysJson[city]){
							_city += "<li data-id=\"" + regionId + "\">" + citysJson[city][regionId] + "</li>";
						}
					}
				}
				$("#city-ul").html(_city);
				$("#tip-rgt").html(",&nbsp;" + $("#city-ul li").eq(0).text());
			});
		}
	};
	myinfo.init();
});