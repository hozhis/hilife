require([ 'jquery', 'global', 'jquery.mobile' ], 
	function($, global) {
		var h = 0;
		var m = 0;
		var s = 0;
		var order = {
			bindEvent : function() {
				var _self = this;
				$(".fui-arrow-left2").tap(function(){
					window.history.back();
				});
				$("#start").tap(function(){
					_self.start();
				});
				$("#finish").tap(function(){
					_self.finish();
				});
			},
			start : function(){
				var data = {
					token : global.token,
					orderId : $("#orderId").val()
				};
				$.ajax({
					type : "POST",
					url : global.context + "/web/order/startOrder",
					dataType : "json",
					data : JSON.stringify(data),
					contentType : "application/json; charset=utf-8",
					success : function(msg) {
						window.location.reload();
					}
				});
			},
			finish : function(){
				var data = {
					token : global.token,
					orderId : $("#orderId").val()
				};
				$.ajax({
					type : "POST",
					url : global.context + "/web/order/finishOrder",
					dataType : "json",
					data : JSON.stringify(data),
					contentType : "application/json; charset=utf-8",
					success : function(msg) {
						window.location.reload();
					}
				});
			},
			time : function(){
				var _self = this;
				setTimeout(function(){
					if(s<60){
						s++;
						_self.time();
					}else if(s == 60){
						m++;
						s = 0;
						if(m<60){
							m++;
							_self.time();
						}else if(m == 60){
							h++;
							m = 0;
						}
					}
					$("#time").text(h + ":"+ m +":" + s);
				},1000);
			},
			init : function() {
				var _self = this;
				_self.bindEvent();
				_self.time();
			}
		};
		order.init();
});