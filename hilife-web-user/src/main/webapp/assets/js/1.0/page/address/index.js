require([ 'jquery', 'global', 'jquery.mobile'], 
		function($, global) {
		var address = {
			bindEvent : function(){
				$(".fui-arrow-left2").tap(function(){
					window.history.go(-1);
				});
			},
			swipe : function(){
				var _self = this;
				$(".front").swipeleft(function(){
					if($(this).parent(".nav-li").siblings().children("div.front").hasClass("slide")){
						$(this).parent(".nav-li").siblings().find("div.front.slide").removeClass("slide").addClass("slide2");
						return;
					}
					$(this).removeClass("slide2").addClass("slide");
				});
				$(".front").swiperight(function(){
					$("div.front.slide").removeClass("slide").addClass("slide2");
				});
				$(".front").tap(function(){
					var parseUrl = window.location.search.split("?")[1];
					var redirectUrl = parseUrl.split("&")[0].split("=")[0];
					if(redirectUrl == "redirect:url"){
						var url =parseUrl.split("&")[0].split("=")[1] + "?token=" +global.token;
						_self.setServiceAddress($(this).attr("id"),url);
					}
				});
				$(".li-default").tap(function handler(){
					$(this).unbind("tap");
					var data = {
							token : global.token,
							addressId : $(this).parents().find("div.behind").siblings(".front.slide").attr("id")
						};
					$.ajax({
						type : "POST",
						url : global.context + "/web/address/setDefaultAddress",
						data : JSON.stringify(data),
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						success : function(msg) {
							if (msg.code == "ACK") {
								alert("success");
								window.location.reload();
							}
						}
					});
					setTimeout(function(){
						$(this).tap(handler);
					},1000);
				});
				$(".li-delete").tap(function(){
					var data = {
							token : global.token,
							addressId : $(this).parents().find("div.behind").siblings(".front.slide").attr("id")
						};
					$.ajax({
						type : "POST",
						url : global.context + "/web/address/delete",
						data : JSON.stringify(data),
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						success : function(msg) {
							if (msg.code == "ACK") {
								alert("success");
								window.location.reload();
							}
						}
					});
				});
				$(".li-edit").tap(function(){
					var id = $(this).parents().find("div.behind").siblings(".front.slide").attr("id");
					$(".front").trigger("swiperight");
					window.location.href = global.context + "/web/address/detail/" + id + "?token=" + global.token;
				});
			},
			setServiceAddress : function(addressId, url){
				var data = {
						token : global.token,
						addressId : addressId
					}
					$.ajax({
						type : "POST",
						url : global.context + "/web/address/setServiceAddress",
						data : JSON.stringify(data),
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						success : function(msg) {
							if (msg.code == "ACK") {
								window.location.href = url;
							}
						}
					});
			},
			loadingAddress : function(){
				var _self = this;
				var data = {
					token : global.token
				}
				$.ajax({
					type : "GET",
					url : global.context + "/web/address/getAll",
					data : data,
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					success : function(msg) {
						if (msg.code == "ACK") {
							var _opts = "";
							for(var i = 0; i < msg.data.length; i++){
								_opts += "<li class=\"nav-li\"><div class=\"front\" id=\"" + msg.data[i].addressId + "\"><div class=\"ln-1\">" +
										"<label class=\"lb-left\">" + msg.data[i].consignee + "</label>" +
										"<label class=\"lb-right\">" + msg.data[i].phone + "</label></div><div class=\"ln-2\">";
								if(msg.data[i].def == "1"){
									_opts += "<label class=\"default\">[默认]</label>";
								}
								_opts += msg.data[i].addressName + "</div></div><div class=\"behind\">" +
										"<ul><li style=\"width: 40%;\"></li><li class=\"li-edit\" style=\"background: silver\">修改</li>" +
										"<li class=\"li-default\" style=\"background: orange\"><div style=\"padding: 13px 0px; line-height: 24px;\">";
								if(msg.data[i].def == "1"){
									_opts += "取消<br>默认";
								}else{
									_opts += "设为<br>默认";
								}
								_opts += "</div></li><li class=\"li-delete\" style=\"background: red\">删除</li></ul></div></li>";
							}
							$(".nav ul").html(_opts);
							_self.swipe();
						}
					}
				});
			},
			init : function() {
				var _self = this;
				_self.bindEvent();
				_self.loadingAddress();
			}
		};
		address.init();
});