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
					var bindTapForLi = function(counter1,counter2){
						//这里是一个新的scope
						return function(){
							window.location.href = global.context + "/web/product/service/000" + counter1 
													+ "s_000" +counter2 + "?token=" + global.token;
						};
					};
					for(var i = 0; i < 7; i++){
						for(var j = 0; j < 9; j++){
							$("#service_000"+i+"s_000"+j+" div").css("background-image",
									"url('"+global.context+"/assets/img/logo/logo_000"+i+"s_000"+j+".png')");
							$("#service_000"+i+"s_000"+j).tap(bindTapForLi(i,j));
						}
					}
				},
				init : function() {
					var _self = this;
					_self.bindEvent();
					_self.banner();
				}
			};
			me.init();
		});