require([ 'jquery', 'global', 'jquery.mobile' ], 
	function($, global) {
		var notice = {
			bindEvent : function() {
				$(".fui-arrow-left2").tap(function(){
					window.history.back();
				});
			},
			init : function() {
				var _self = this;
				_self.bindEvent();
			}
		};
		notice.init();
});