require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
		var feedback = {
			sizeInit : function(){
			},
			bindEvent : function(){
				$(".fui-arrow-left2").tap(function(){
					window.location.replace(global.context + "/web/me/index?token=" + global.token);
				});
			},
			init : function(){
				var _self = this;
				_self.bindEvent();
				_self.sizeInit();
			}
		};
		feedback.init();
});