require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
			var me = {
				bindEvent : function() {
					var width = $(".banner").width();
					$(".banner").height(parseInt(width)/2.8);
					var w = $(".circle").width();
					$(".circle").height(parseInt(w));
					$(".banner-gap").height(parseInt(w)/2.464);
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
				}
			};
			me.init();
		});