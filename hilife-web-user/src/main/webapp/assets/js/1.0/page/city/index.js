require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
		var order = {
			sizeInit : function(){
				var h1 = $("body").height();
				var h2 = $(".alphabet").height();
				$(".alphabet").css("top", (h1 - h2)/2 + "px" )
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