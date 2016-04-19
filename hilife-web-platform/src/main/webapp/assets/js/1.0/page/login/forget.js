require(['jquery',
         'global',
         'module/util',
         'module/ajax',
         'common',
         'bootstrap',
         'jquery.md5',
         'jquery.jqgrid',
         'jquery.jqgrid/i18n',
         'requirejs/domready!'],
function($, global, util){
	var testCode="";//验证码
	var wait=60;
	var forgetPwd = {
			save: function(){
				var password=$("#password").val();
				var password1 = $("#password1").val();
				if(password && password1){
					if(password==password1){
						var reg = /[^\u4e00-\u9fa5]{6,16}/;
	                	var flag = reg.test($("#password").val());
						if(flag){
							var data = util.f2j($(".basicInfoDiv"));
							delete data.password1;
							delete data.testCodeInput;
							delete data.password;
							data.password = $.md5(password);
							$.ajax({
								url: global.context+'/web/auth/submitPw',
								type : "POST",
								dataType : "json",
								data : JSON.stringify(data),
								contentType : "application/json",
								success : function(msg){
									location.href=global.context+"/web/auth/login";
								}
							});
						}else{
							$("#p-tip").html("仅支持6-16位的数字、英文、符号及特殊字符!");
						}
					}else{
						$("#p-tip").html("两次输入的密码不一致!");
					}
				}else{
					$("#p-tip").html("请输入密码!");
				}
			},
			nextStep: function(){
				var testCodeInput = $("#testCodeInput").val();
				var phone = $("#phone").val();
				var reg =/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
            	var flag= reg.test(phone);
            	if(!flag){
            		$("#tip").html("仅支持11位的手机号码!");
            		return;
            	}
				if(testCodeInput){
					$.ajax({
						url: global.context+'/web/auth/checkCode',
						type : "POST",
						dataType : "json",
						data : JSON.stringify({phone:phone,code:testCodeInput}),
						contentType : "application/json",
						success : function(msg){
							if("ACK"==msg.code){
								$("#part-one").removeClass("show").addClass("hidden");
								$("#part-two").removeClass("hidden").addClass("show");
								$("#findpassword").removeClass("getAccount").addClass("getPassword");
							}else{
								$("#tip").html("验证码错误!");
							}
						}
					});
				}else{
					$("#tip").html("请输入验证码!");
				}
			},
			getTestCode:function(){
				var _self = this;
				var phone = $("#phone").val();
				var reg =/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
            	var flag= reg.test(phone);
            	if(!flag){
            		$("#tip").html("仅支持11位的手机号码!");
            		return;
            	}
				$.ajax({
					url: global.context+'/web/auth/getCode',
					type : "POST",
					dataType : "json",
					data : JSON.stringify({phone:phone}),
					contentType : "application/json",
					success : function(msg){
						if("ACK"==msg.code){
							testCode = msg.data;
							_self.reGet();
						}else{
							return;
						}
					}
				});
				
			},
			reGet:function(){
				var _self = this;
				if (wait == 0) {
					$("#phone").attr("disabled",false);
					$("#testGetCode").attr("disabled",false);			
					$("#testGetCode").text("免费获取验证码");
					wait = 60;
				} else {
					$("#phone").attr("disabled",true);
					$("#testGetCode").attr("disabled","");
					$("#testGetCode").text("重新发送" + wait + "s");
					wait--;
					setTimeout(function() {
						_self.reGet();
					},
					1000)
				}
			},
			bindEvent: function(){
				var _self = this;
				//保存
				$("#save").on('click',function(){
					_self.save();
				});
				//下一步
				$("#next_step").on('click',function(){
					_self.nextStep();
				});
				//获取验证码
				$("#testGetCode").click(function(){
					_self.getTestCode();
				});
				 // 验证用户名
                $('#phone').focus(function(){
                }).blur(function(){
                	var reg =/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
                	var flag= reg.test($(this).val());
                    if(flag){
                        $("#tip").empty();
                    }else{
                        $("#tip").text('请输入手机号码');
                    }
                });
			},
			init:function(){
				var _self = this;
				$("#tip").html("");
				$("#testGetCode").attr("disabled",false);	
				$("div[class='right']").html("<a href='${contextPath}/web/auth/login;' style='padding-right:30px;'>登录</a>");
				_self.bindEvent();
			}
	};
	forgetPwd.init();
});