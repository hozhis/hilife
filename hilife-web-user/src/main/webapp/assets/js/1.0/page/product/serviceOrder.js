require([ 'jquery',
          'global',
          'jquery.mobile' ], 
		function($, global) {
			var service = {
				bindEvent : function(){
					$(".fui-arrow-left2").tap(function(){
						window.history.go(-1);
					});
					$(".title label").text(function(){
						return decodeURI(window.location.href.split("?")[1].split("&")[0].split("=")[1]);
						});
				},	
				init : function(){
					var _self = this;
					_self.bindEvent();
				}
			};
			service.init();
	});