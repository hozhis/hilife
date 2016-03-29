require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
		var order = {
			sizeInit : function(){
				var w = $(".li-d-1").width();
				$(".con").width(w - 70 + 'px');
			},
			bindEvent : function(){
				$(".content-clsy ul>li>div").tap(function(){
					$(this).addClass("active");
					$(this).parent("li").siblings().children().removeClass("active");
				});
				/*$(".content-body").swipeUp(function(){
					$(".header").hide();
				});*/
			},
			init : function(){
				var _self = this;
				_self.bindEvent();
				_self.sizeInit();
			}
		};
		order.init();
});