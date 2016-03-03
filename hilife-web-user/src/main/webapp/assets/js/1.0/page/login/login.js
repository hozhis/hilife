require([ 'jquery', 'global', 'jquery.mobile', 'pnotify' ],
		function($, global) {
			var login = {
				validateInit : function() {

				},
				bindEvent : function() {
					var _self = this;
					$("#username").keyup(function() {
						$(".login-warn").addClass("hide");
						if (!$(this).val() == "") {
							$(".login-phone label").css("color", "#7b7b7b");
						} else {
							$(".login-phone label").css("color", "#ffffff");
						}
					});
					/*$(".login-body input").focus(function(){
						$("#username").css("border-color","#ea0000");
					});*/
					$("#validcode").keyup(function() {
						if (!$(this).val() == "") {
							$(".login-validcode label").css("color", "#7b7b7b");
						} else {
							$(".login-validcode label").css("color", "#ffffff");
						}
					});
					$(".nextstep").tap(function() {
						var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
						var flag = reg.test($("#username").val());
						if (!flag) {
							$(".login-warn").removeClass("hide");
							$("#username").css("border-color","#0072e3");
							return;
						}
						_self.sendLoginSms();
					});
					$("#arrow-left").tap(function() {
						$("#pagetwo").addClass("hide");
						$("#pageone").removeClass("hide");
						$("#arrow-left").addClass("hide");
					});
					$(".submit").tap(function(){
						_self.login();
					});
					var h = $("html").height();
					$(".main-wrapper").height(h);
					$(".main-wrapper").css("background","#ffffff");
				},
				sendLoginSms : function() {
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
								$("#pageone").addClass("hide");
								$("#pagetwo").removeClass("hide");
								$("#arrow-left").removeClass("hide");
							}
						}
					});
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
								/*layer.open({
								    content: '登录成功',
								    btn: ['确认', '取消'],
								    shadeClose: false,
								    yes: function(){
								        layer.open({content: '你点了确认', time: 1});
								    }, no: function(){
								        layer.open({content: '你选择了取消', time: 1});
								    }
								});*/
								window.location.href = global.context + "/web/product/index?token=" + msg.data.token;
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