require(['jquery',
         'global',
         'module/util',
         'module/ajax',
         'jquery.validate',
         'bootstrap',
         'jquery.md5',
         'jquery.cookie',
         'requirejs/domready!'], 
function($, global, util){
	var viewFlag = false;
	var login = {
			sizeInit : function(){
				var h = $(".captcha").height();
				$("#captchaImage").height(h + 36 + 'px');
			},
			validateInit:function(){
				var key = Math.random();
				$("#captchaImage").attr("src", global.context+ "/web/auth/captcha?key=" + key);
				$("#key").val(key);
			},
			bindEvent :function(){
				var _self = this;
				/*$("#isRememberUsername").click(
						function() {
							if ($("#isRememberUsername")
									.hasClass("checked")) {
								$("#isRememberUsername").removeClass(
										"checked").addClass("unchecked");
							} else {
								$("#isRememberUsername").removeClass(
										"unchecked").addClass("checked");
							}
						});*/
				$("#captchaImage").click(function() {
					_self.validateInit();
				});
				// 用户名输入框回车直接进入密码输入框
				$(".username").keydown(function(e) {
					if (e.keyCode == "13") {
						$(".username").removeClass("focus");
						$(".password").focus();
						$(".password").select();
					}
				});
				// 密码输入框回车直接进入验证码输入框
				$(".password").keydown(function(e) {
					if (e.keyCode == "13") {
						$(".password").removeClass("focus");
						$(".captcha").focus();
						$(".captcha").select();
					}
				});
				// 验证码输入框回车直接登录
				$(".captcha").keydown(function(e) {
					if (e.keyCode == "13") {
						$("#login_btn").click();
					}
				});
				$("#login_btn").click(function(){
					var data = {};
					data.loginId = $(".login-form").find(".username").val();
					data.password = $.md5($(".login-form").find(".password").val());
					data.captcha =  $(".login-form").find(".captcha").val();
					data.key = $(".login-form").find("#key").val();
					console.log(data.loginId);
					/*if(data.loginId =="" ){
						if($('.password').val() == ""){
							if(data.captcha == ""){
								$("#tip").removeClass("hidden");
								$("#antTip").html('用户名、密码和验证码不得为空！');
								return;
							}
							$("#tip").removeClass("hidden");
							$("#antTip").html('用户名和密码不得为空！');
							return;
						}else{
							if(data.captcha == ""){
								$("#tip").removeClass("hidden");
								$("#antTip").html('用户名和验证码不得为空！');
								return;
							}
						}
						$("#tip").removeClass("hidden");
						$("#antTip").html('用户名不得为空！');
						return;
					}*/
					/*if($('#password').val() == ""){
						if(data.loginId =="" ){
							if(data.captcha == ""){
								$("#tip").removeClass("hidden");
								$("#antTip").html('用户名、密码和验证码不得为空！');
								return;
							}
							$("#tip").removeClass("hidden");
							$("#antTip").html('用户名和密码不得为空！');
							return;
						}else{
							if(data.captcha == ""){
								$("#tip").removeClass("hidden");
								$("#antTip").html('密码和验证码不得为空！');
								return;
							}
						}
						$("#tip").removeClass("hidden");
						$("#antTip").html('密码不得为空！');
						return;
					}*/
					/*if(data.captcha == ""){
						if(data.loginId =="" ){
							if($('#password').val() == ""){
								$("#tip").removeClass("hidden");
								$("#antTip").html('用户名、密码和验证码不得为空！');
								return;
							}
							$("#tip").removeClass("hidden");
							$("#antTip").html('用户名和验证码不得为空！');
							return;
						}else{
							if($('#password').val() == ""){
								$("#tip").removeClass("hidden");
								$("#antTip").html('密码和验证码不得为空！');
								return;
							}
						}
						$("#tip").removeClass("hidden");
						$("#antTip").html('验证码不得为空！');
						return;
					}
					_self.saveUserInfo();*/
					 $.ajax({
						 url : global.context + "/web/auth/login/authc",
						 type : "post",
						 dataType: "json",
						 contentType: "application/json; charset=utf-8",
						 data : JSON.stringify(data),
						 success : function(msg){
							  if(msg.code == "ACK"){
								  _self.saveUserInfo();
								  location.replace(global.context +'/web/auth/index?token=' + msg.data.token);
							  }else{
								  /*$("#tip").removeClass("hidden");
								  $("#antTip").html(msg.message);*/
//							  	if(msg.message == "验证码错误"){
//							  		$("#tip").removeClass("hidden");
//									$("#antTip").html('验证码错误！');
//							  	}else if(msg.message == "密码错误"){
//							  		$("#tip").removeClass("hidden");
//									$("#antTip").html('用户名或密码错误！');
//							  	}else{
//							  		$("#tip").removeClass("hidden");
//									$("#antTip").html('用户名或密码错误！');
//							  	}
								var key = Math.random();
								$("#captchaImage").attr("src", global.context+ "/web/auth/captcha?key=" + key);
								$("#key").val(key);
							  }
						 }
					 });
				});
				/*$("#viewPassword").click(function() {
					// viewFlag==true表示显示的是明文
					$("#viewPassword").attr("title", "隐藏密码");
					if (viewFlag) {
						$("#viewPassword").attr("title", "显示密码");
						$("#password").attr("type", "password");
						$(this).removeClass('noeye').addClass('eye');
						viewFlag = false;
					} else {
						$("#viewPassword").attr("title", "隐藏密码");
						$("#password").attr("type", "text");
						$(this).removeClass('eye').addClass('noeye');
						viewFlag = true;
					}
				});*/
			},
			cookieCheck : function() {
				if ($.cookie("hlUser") == "true") {
					$("#isRememberUsername").attr("checked", true);
					$("#username").val($.cookie("username"));
				}
			},
			saveUserInfo : function() {
				if ($("#isRememberUsername").hasClass("checked")) {
					var userName = $("#username").val();
					$.cookie("hlUser", "true", {
						expires : 7
					}); // 存储一个带7天期限的 cookie
					$.cookie("username", userName, {
						expires : 7
					}); // 存储一个带7天期限的 cookie
				} else {
					$.cookie("hlUser", "false", {
						expires : -1
					}); // 删除 cookie
					$.cookie("username", '', {
						expires : -1
					});
				}
			},
			init : function(){
				var _self = this;
				_self.validateInit();
				_self.bindEvent();
				_self.cookieCheck();
				_self.sizeInit();
			}
	};
	login.init();
	
});
