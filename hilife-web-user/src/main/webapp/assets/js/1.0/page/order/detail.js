require([ 'jquery', 'global', 'jquery.mobile','pnotify'],
		function($, global) {
			var orderDetail = {
				sizeInit : function(){
					var w = $(".li-d-1").width();
					$(".con").width(w - 70 + 'px');
				},
				bindEvent : function() {
					var _self = this;
					$(".fui-arrow-left2").tap(function(){
						window.location.replace(global.context + "/web/order/index?token=" + global.token);
					});
					$(".btn-cancel").tap(function(){
						var orderId = $("#orderId").text();
						layer.open({
						    content: '请您再确认一遍，是否取消此订单？',
						    btn: ['取消', '我再想想'],
						    shadeClose: false,
						    yes: function(){
						    	layer.closeAll();
						    	_self.cancel(orderId);
						    }, no: function(){
						    }
						});
					});
					$(".btn-delete").tap(function(){
						var orderId = $("#orderId").text();
						layer.open({
						    content: '请您再确认一遍，是否删除此订单？',
						    btn: ['确认', '取消'],
						    shadeClose: false,
						    yes: function(){
						    	layer.closeAll();
						    	_self.deleteOrder(orderId);
						    }, no: function(){
						    }
						});
					});
					$(".btn-evaluate").tap(function(){
						var orderId = $("#orderId").text();
						window.location.href = global.context + "/web/order/evaluate?orderId=" + orderId + "&token=" + global.token;
					});
				},
				cancel : function(orderId){
					$.ajax({
						type : "POST",
						url : global.context + "/web/order/cancel?orderId=" + orderId + "&token=" + global.token,
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						success : function(msg) {
							console.log(msg.code);
							if (msg.code == "ACK") {
								layer.open({content: '取消成功', time: 1});
								setTimeout(function(){window.location.reload();},1000);
							}else{
								layer.open({content: msg.message, time: 1});
							}
						}
					});
				},
				deleteOrder : function(orderId){
					$.ajax({
						type : "POST",
						url : global.context + "/web/order/delete?orderId=" + orderId + "&token=" + global.token,
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						success : function(msg) {
							console.log(msg.code);
							if (msg.code == "ACK") {
								layer.open({content: '删除订单成功', time: 1});
								setTimeout(function(){
									window.location.replace(global.context + "/web/order/index?token=" + global.token);
								},1000);
							}else{
								layer.open({content: msg.message, time: 1});
							}
						}
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