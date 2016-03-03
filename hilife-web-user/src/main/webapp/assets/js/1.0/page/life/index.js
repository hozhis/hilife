require([ 'jquery', 'global', 'jquery.mobile', 'bootstrap'],
		function($, global) {
			var me = {
				slider : function() {
					var w = $(".carousel").width();
					$(".carousel").height(w/2.2);
					$(".carousel-inner").height(w/2.2);
					$(".carousel-inner img").height(w/2);
					var wp = $(".product-item").width();
					$(".item-d").width((wp-40)/3);
					var wd = $(".item-d").width();
					$(".item-d div").width(wd-12);
					$(".item-d div").height(wd-12);
					$(".item-d span").width(wd-12);
					$(".item-d").height(wd+33);
					$("#carousel-hilife").swipeleft(function(){
						$(this).carousel("next");
					});
					$("#carousel-hilife").swiperight(function(){
						$(this).carousel("prev");
					});
				},
				bindEvent : function() {
				},
				init : function() {
					$("#carousel-hilife").carousel();
					var _self = this;
					_self.bindEvent();
					_self.slider();
				}
			};
			me.init();
		});