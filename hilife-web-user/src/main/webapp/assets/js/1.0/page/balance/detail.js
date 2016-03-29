require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
		var order = {
			sizeInit : function(){
				var w = $("body").width();
				$(".icon_balance").height(w * 0.5);
			},
			bindEvent : function(){
				$(".fui-arrow-left2").tap(function(){
					window.history.back();
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