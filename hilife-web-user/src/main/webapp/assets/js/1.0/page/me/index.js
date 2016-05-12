require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
			var me = {
				gradient : function(){
					$(window).scroll(function(){
						console.log("到底有没有用呢？");
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
					/*var width = $(".banner").width();
					$(".banner").height(parseInt(width)/2.5);*/
					//var w = $(".circle").width();
					//$(".circle").height(parseInt(w));
					//$(".banner-gap").height(parseInt(w)/2.464);
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
									window.location.href = global.context + "/web/product/index";
								}
							}
						});
					});
				},
				init : function() {
					var _self = this;
					_self.bindEvent();
					_self.gradient();
					$(window).load(function() {_self.gradient();});
				}
			};
			me.init();
		});