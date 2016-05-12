require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
			var orderDetail = {
				sizeInit : function(){
					var w = $(".li-d-1").width();
					$(".con").width(w - 70 + 'px');
				},
				bindEvent : function() {
					$(".fui-arrow-left2").tap(function(){
						window.history.back();
					});
				},
				init : function() {
					var _self = this;
					_self.bindEvent();
					_self.sizeInit();
				}
			};
			orderDetail.init();
		});