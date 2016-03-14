require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
			var me = {
				banner : function() {
				},
				bindEvent : function() {
					/* ###错误的写法###
					 * 这是js闭包原理的经典案例。
					 * tap函数绑定后的变量i是最终循环完之后的值
					 * for(var i = 1; i <= 31; i++){
					 * 	$("#service"+i).tap(function(){
					 * 	window.location.href = global.context + "/web/product/service/" + i + "?token=" + global.token;
					 * });
					}*/
					var bindTapForLi = function(counter){
						//这里是一个新的scope
						return function(){
							window.location.href = global.context + "/web/product/service/" + counter 
													+ "?serviceName=" + $("#service"+counter+" label").text()
													+ "&token=" + global.token;
						};
					};
					for(var i = 1; i <= 31; i++){
						$("#service"+i).tap(bindTapForLi(i));
					}
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