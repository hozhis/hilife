require([ 'jquery', 'global', 'jquery.mobile', 'pnotify' ],
		function($, global) {
			var wait = 60;
			//屏蔽双击事件，防止验证码的短时间内多次获取，避免服务器压力
			function handler(e) {
				e.preventDefault();
				var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
				var flag = reg.test($("#username").val());
				if (!flag) {
					$(".login-warn").removeClass("hide");
					$("#username").css("border-color","#0072e3");
					return;
				}
				$(".progressbar").removeClass("hide").addClass("loading");
				login.checkUser();
			}
			var login = {
				bindEvent : function() {
					var _self = this;
					$("#username").keyup(function() {
						$(".login-warn").addClass("hide");
						$("#username").attr("style","");
						if (!$(this).val() == "") {
							$(".login-phone label").css("color", "#7b7b7b");
						} else {
							$(".login-phone label").css("color", "#ffffff");
						}
					});
					$("#validcode").keyup(function() {
						$(".login-warn").addClass("hide").text("请输入验证码");
						$("#validcode").attr("style","");
						if (!$(this).val() == "") {
							$(".login-validcode label").css("color", "#7b7b7b");
						} else {
							$(".login-validcode label").css("color", "#ffffff");
						}
					});
					$(".nextstep").tap(handler);
					$("#arrow-left").tap(function() {
						/*$(".login-warn").addClass("hide");*/
						$("#validcode").val("").trigger("keyup");
						/*$(".login-validcode label").css("color", "#ffffff");*/
						$("#pagetwo").addClass("hide");
						$("#pageone").removeClass("hide");
						$("#arrow-left").addClass("hide");
					});
					$(".submit").tap(function(){
						if($("#validcode").val() != ""){
							_self.login();
						}else{
							$(".login-warn").removeClass("hide");
							$("#validcode").css("border-color","#0072e3");
							return;
						}
					});
					var h = $("html").height();
					$(".main-wrapper").height(h);
					$(".main-wrapper").css("background","#ffffff");
				},
				checkUser : function(){
					var _self = this;
					$.ajax({
						type : "GET",
						url : global.context + "/web/auth/checkUser?loginId=" + $("#username").val(),
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						success : function(msg) {
							if (msg.code == "NACK") {
								layer.open({
									content: '您的手机号尚未注册，点击<span style="color:#ea0000;">注册</span>即表示您同意我们的<span style="color:#ea0000;">服务条款</span>，系统将自动为您创建账号',
									btn: ['注册', '取消'],
									shadeClose: false,
									yes: function(){
										layer.closeAll();
										_self.sendLoginSms();
										$(".nextstep").unbind('tap');
										setTimeout(function(){$(".nextstep").tap(handler)},60000);
									}, no: function(){
										// do nothing...
									}
								});
							}else if (msg.code == "ACK"){
								_self.sendLoginSms();
								$(".nextstep").unbind('tap');
								setTimeout(function(){$(".nextstep").tap(handler)},60000);
							}
						}
					});
				},
				sendLoginSms : function() {
					var _self = this;
					var data = {
						loginId : $("#username").val()
					}
					$.ajax({
						type : "POST",
						url : global.context + "/web/auth/sendLoginSms",
						data : JSON.stringify(data),
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						success : function(msg) {
							if (msg.code == "ACK") {
								$(".progressbar").removeClass("loading").addClass("hide");
								$("#pageone").addClass("hide");
								$("#pagetwo").removeClass("hide");
								$("#arrow-left").removeClass("hide");
								_self.reGet();
							}
						}
					});
				},
				reGet : function(){
					var _self = this;
					if(wait == 0){
						$(".nextstep").attr("disabled",false);
						$(".nextstep").css("background-color","#ea0000");
						$(".nextstep").text("下一步");
						wait = 60;
					} else {
						$(".nextstep").attr("disabled","");
						$(".nextstep").css("background-color","#ff7575");
						$(".nextstep").text(wait + "s");
						wait--;
						setTimeout(function(){
							_self.reGet();
						},1000);
					}
				},
				login : function(){
					var data = {
							loginId : $("#username").val(),
							password : $("#validcode").val()
						}
					$.ajax({
						type : "POST",
						url : global.context + "/web/auth/login/authc",
						data : JSON.stringify(data),
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						success : function(msg) {
							if (msg.code == "ACK") {
								window.parent.location = global.context + "/web/product/index?token=" + msg.data.token;
							} else {
								$(".login-warn").removeClass("hide").text("验证码错误，请重新输入！");
								$("#validcode").css("border-color","#0072e3");
								return;
							}
						}
					});
				},
				init : function() {
					var _self = this;
					_self.bindEvent();
				}
			};
			login.init();
		});