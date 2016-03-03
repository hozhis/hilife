require([ 'jquery', 'global', 'jquery.mobile', 'pnotify' ],
		function($, global) {
			var me = {
				bindEvent : function() {
					var width = $(".banner").width();
					$(".banner").height(parseInt(width)/2.8);
					var w = $(".circle").width();
					$(".circle").height(parseInt(w));
					$(".banner-gap").height(parseInt(w)/2.464);
				},
				init : function() {
					var _self = this;
					_self.bindEvent();
				}
			};
			me.init();
		});