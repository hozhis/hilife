require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
			var me = {
				banner : function() {
				},
				bindEvent : function() {
				},
				length : function(){
					var w = $(".service-clsfy").width();
					$(".service-clsfy").height(w);
				},
				init : function() {
					var _self = this;
					_self.bindEvent();
					_self.banner();
					_self.length();
				}
			};
			me.init();
		});