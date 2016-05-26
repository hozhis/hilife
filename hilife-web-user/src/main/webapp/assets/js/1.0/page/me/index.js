require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
			var me = {
				gradient : function(){
					$(window).scroll(function(){
						if($(window).scrollTop() > 50 || $(window).scrollTop() < 0){
							$(".back-header").removeClass("header-out").addClass("header-in");
						}else if($(window).scrollTop() > 0 && $(window).scrollTop() <= 50){
							$(".back-header").removeClass("header-in").addClass("header-out");
						}else if($(window).scrollTop() == 0){
							$(".back-header").removeClass("header-in header-out");
						}
					});
				},
				bindEvent : function() {
					$("#quit").tap(function(){
						var data = {
								token : global.token,
							};
						$.ajax({
							type : "POST",
							url : global.context + "/web/auth/logout",
							data : JSON.stringify(data),
							dataType : "json",
							contentType : "application/json; charset=utf-8",
							success : function(msg) {
								if (msg.code == "ACK") {
									window.location.href = global.context + "/web/auth/login";
								}
							}
						});
					});
				},
				init : function() {
					var _self = this;
					_self.bindEvent();
					_self.gradient();
				}
			};
			me.init();
		});