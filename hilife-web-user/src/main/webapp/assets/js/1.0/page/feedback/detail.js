require([ 'jquery', 'global', 'jquery.mobile','pnotify'],
		function($, global) {
		var detail = {
			sizeInit : function(){
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
		detail.init();
});