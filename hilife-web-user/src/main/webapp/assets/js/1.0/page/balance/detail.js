require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
		var order = {
			bindEvent : function(){
				$(".fui-arrow-left2").tap(function(){
					window.history.back();
				});
			},
			init : function(){
				var _self = this;
				_self.bindEvent();
			}
		};
		order.init();
});