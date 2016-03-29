require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
		var order = {
			sizeInit : function(){
			},
			bindEvent : function(){
				$(".fui-arrow-left2").tap(function(){
					window.history.back();
				});
				$("#balance").tap(function(){
					window.location.href = global.context + "/web/balance/detail?token=" + global.token;
				});
				$("#bankcard").tap(function(){
					window.location.href = global.context + "/web/balance/bank/index?token=" + global.token;
				});
				$(".border div").tap(function(){
					$(this).addClass("active");
					$(this).siblings().removeClass("active");
					$(".shade.hide").removeClass("hide");
				});
			},
			init : function(){
				var _self = this;
				_self.bindEvent();
				_self.sizeInit();
			}
		};
		order.init();
});