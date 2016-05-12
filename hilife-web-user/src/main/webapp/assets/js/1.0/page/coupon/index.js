require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
		var coupon = {
			bindEvent : function(){
				var _self = this;
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
				$(".btn-exchange").tap(function(){
					_self.exchange($("#inviteCode").val());
				});
			},
			exchange : function(inviteCode){
				$.ajax({
					type : "GET",
					url : global.context + "/web/coupon/exchange?inviteCode=" + inviteCode
							+ "&token=" + global.token,
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					success : function(msg) {
						alert(msg.message);
						window.location.reload();
					}
				});
			},
			getCouponList : function(){
				$.ajax({
					type : "GET",
					url : global.context + "/web/coupon/getCouponList?token=" + global.token,
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					success : function(msg) {
						var _opts = "";
						if(msg.code == "ACK"){
							for(var i = 0; i < msg.data.length; i++){
								_opts += "<li><div class=\"div-coupon\"><div class=\"c-src\">￥" +
										"<label class=\"price-1\">" + (msg.data[i].money/100).toString().substring(0,1) + "</label>" +
										"<label class=\"price-2\">" + (msg.data[i].money/100).toString().substring(1) +
										"</label></div><div class=\"c-detail\"><div>" + msg.data[i].couponTitle +
										"</div><ul><li>满" + msg.data[i].useMoneyLimit/100 + "可用</li>" +
										"<li>" + msg.data[i].useCondLimit + "</li><li>" + msg.data[i].effectiveDate +
										"至" + msg.data[i].expireDate + "</li></ul></div></div></li>";
							}
							$(".coupon-ul").html(_opts);
						}
					}
				});
			},
			init : function(){
				var _self = this;
				_self.bindEvent();
				_self.getCouponList();
			}
		};
		coupon.init();
});