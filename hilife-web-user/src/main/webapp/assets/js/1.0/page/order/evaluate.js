require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
			var evaluate = {
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
			evaluate.init();
		});