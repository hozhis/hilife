require([ 'jquery', 'global', 'jquery.mobile', 'pnotify' ],
		function($, global) {
			var wait = 60;
			var login = {
				bindEvent : function() {
					var _self = this;
					//屏蔽双击事件，防止验证码的短时间内多次获取，避免服务器压力
					function handler(e) {
						e.preventDefault();
						var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
						var flag = reg.test($("#loginId").val());
						if (!flag) {
							alert("请输入正确的手机号");
							return;
						}
						$("#captcha").unbind("tap");
						_self.sendLoginSms();
						setTimeout(function(){
							$("#captcha").tap(handler);
						},60000);
					}
					$("#captcha").tap(handler);
					$("#btn-login").tap(function(){
						_self.login();
					});
				},
				sendLoginSms : function() {
					var _self = this;
					var data = {
						loginId : $("#loginId").val()
					}
					$.ajax({
						type : "POST",
						url : global.context + "/web/auth/sendLoginSms",
						data : JSON.stringify(data),
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						success : function(msg) {
							if (msg.code == "ACK") {
								_self.reGet();
							}
						}
					});
				},
				reGet : function(){
					var _self = this;
					if(wait == 0){
						$("#captcha").attr("disabled",false);
						$("#captcha").text("获取验证码");
						wait = 60;
					} else {
						$("#captcha").attr("disabled","");
						$("#captcha").text(wait + "s");
						wait--;
						setTimeout(function(){
							_self.reGet();
						},1000);
					}
				},
				login : function(){
					var data = {
							loginId : $("#loginId").val(),
							password : $("#password").val()
						}
					$.ajax({
						type : "POST",
						url : global.context + "/web/auth/login/authc",
						data : JSON.stringify(data),
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						success : function(msg) {
							if (msg.code == "ACK") {
								window.parent.location = global.context + "/web/index/index?token=" + msg.data.token;
							} else {
								alert(msg.message);
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