require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
		var order = {
			sizeInit : function(){
				var w = $("body").width();
				var h = $("body").height();
				$(".content").css("min-height",h - 50 + 'px');
				$(".main-r").css("height",w / 1.44 + 'px');
			},
			bindEvent : function(){
				$(".fui-arrow-left2").tap(function(){
					window.history.back();
				});
				$(".invite").tap(function(){
					$(".shade").unbind("tap");
					$(".share-item").removeClass("hide");
					setTimeout(function(){
						$(".shade").tap(function(){
							$(".share-item").addClass("hide");
						});
					},500);
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