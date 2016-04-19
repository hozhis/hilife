require(['jquery',
         'global',
         'module/util',
         'module/ajax',
         'bootstrap',
         'jquery.md5',
         'jquery.validate',
         'requirejs/domready!'], 
function($, global, util){
	$("#loginForm").validate({
		rules: {
			username: {required:true},
			password: {required:true},
			//captcha: {required:true}
		},
		messages: {
			username: {required:'用户名不能为空'},
			password: {required:'密码不能为空'},
			//captcha: {required:'验证码不能为空'},
		},
		submitHandler:function(form){
			var data = {};
			data.loginId = $("#loginForm").find("#username").val();
			data.password = $.md5($("#loginForm").find("#password").val());
			//data.captcha =  $("#loginForm").find("#captcha");
			
			 $.ajax({
				 url : global.context + "/web/auth/login/authc",
				 type : "post",
				 dataType: "json",
				 contentType: "application/json; charset=utf-8",
				 data : JSON.stringify(data),
				 success : function(ajaxBean){
					
				 }
			 });
		}
	});
		
	$("#captchaImage").click(function(){
		$("#captchaImage").attr("src",global.context+"/captchaController/image?" +Math.random());
	});
	
});