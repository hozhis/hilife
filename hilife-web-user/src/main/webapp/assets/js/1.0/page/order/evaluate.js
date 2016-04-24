require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
		var order = {
			sizeInit : function(){
			},
			bindEvent : function(){
				$(".fui-arrow-left2").tap(function(){
					window.history.back();
				});
				$(".fui-arrow-left3").tap(function(){
					$(".exchangeCoupon").addClass("hide");
					$(".main-wrapper .header").show();
					$(".fui-arrow-left2").unbind('tap');
					setTimeout(function(){
						$(".fui-arrow-left2").tap(function(){
							window.history.back();
						})
					},1000);
				});
				$(".options div:first-child").tap(function(){
					$(".main-wrapper .header").hide();
					$(".exchangeCoupon").removeClass("hide");
				});
				$(".options div:last-child").tap(function(){
					window.location.href = global.context + "/web/coupon/share?token=" + global.token;
				});
				$("#couponDescribe").tap(function(){
					window.location.href = global.context + "/web/coupon/describe?token=" + global.token;
				});
			},
			getCouponList : function(){
				$.ajax({
					type : "GET",
					url : global.context + "/web/auth/checkUser?loginId=" + $("#username").val(),
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					success : function(msg) {
						
					}
				});
			},
			init : function(){
				var _self = this;
				_self.bindEvent();
				_self.sizeInit();
				_self.getCouponList();
			}
		};
		order.init();
});