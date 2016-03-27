require([ 'jquery',
          'global',
          'jquery.mobile',
          'pnotify',
          'module/localStorage'], 
		function($, global) {
			var service = {
				/*localStorage : function(key){
					if(window.localStorage){
						//alert('This browser supports localStorage');
						return getCache(key);
					}
				},*/
				getDate : function(dayCount){
					var week = ['日','一','二','三','四','五','六'];
					var date = new Date();
					date.setDate(date.getDate()+dayCount);//获取dayCount天后的日期
					var y = date.getFullYear();
					var m = date.getMonth() + 1;//获取当前月份的日期
					var d = date.getDate();
					var w = week[date.getDay()];
					return "<label class='hide'>" + y + "/</label><span>" + m + "/" + d + "</span><span>星期" + w + "</span>"; 
				},
				dataInit : function(){
					var _self = this;
					for(var i = 0; i < 4; i++){
						$("#d"+i).html(_self.getDate(i));
					}
				},
				bindEvent : function(){
					var _self = this;
					$(".fui-arrow-left2").tap(function(){
						//window.history.go(-1);
						window.location.href = global.context + "/web/product/index?token=" + global.token;
					});
					$(".notice").tap(function(){
						layer.open({
						    type: 1,
						    content: $(".notice-detail").html(),
						    style: 'position:fixed; left:0; top:0; width:100%; height:100%; border:none;background: #ffffff;'
						});
						$(".fui-cross").tap(function(){
							layer.closeAll();
						});
					});
					$(".time-item").tap(function(){
						if($(this).hasClass("active")){
							$(this).removeClass("active");
							$(this).children(".check").addClass("hide");
						} else {
							$(this).addClass("active");
							$(this).parent("li").siblings().find(".time-item").removeClass("active");
							$(this).children(".check").removeClass("hide");
							$(this).parent("li").siblings().find(".check").addClass("hide");
						}
					});
					$(".date ul>li").tap(function(){
						if(!$(this).hasClass("active")){
							$(this).addClass("active");
							$(this).siblings().removeClass("active");
						}
					});
					$(".minus").tap(function(){
						var temp = $(this).siblings().find("label").text();
						if($(this).siblings().find("label").hasClass("flag") && temp == 3){
							return;
						}else if(temp > 1){
							temp--;
							$(this).siblings().find("label").text(temp);
						}else{
							
						}
					});
					$(".plus").tap(function(){
						var temp = $(this).siblings().find("label").text();
						if(!$(this).siblings().find("label").hasClass("flag") && temp == 5){
							return;
						}else if(temp < 8){
							temp++;
							$(this).siblings().find("label").text(temp);
						}else{
							
						}
					});
					$(".close").tap(function(){
						_self.closeDatetime();
					});
					/*$(".shape").tap(function(){
						_self.closeDatetime();
					});*/
					$(".confirm").tap(function(){
						var temp = $(".date ul>li.active").text().replace("/","-").replace("/","-") +
									"&nbsp;" + $(".time-item.active").text();
						$("#service-time").html(temp).css("color","#000000");
						_self.closeDatetime();
					});
					$("#form-time").tap(function(){
						$(".daterpicker").removeClass("hide");
					});
					$("#form-address").tap(function(){
						window.location.href = global.context + "/web/address/index?redirect:url=" 
												+ window.location.href + "&token=" + global.token;
					});
				},
				closeDatetime : function(){
					$(".daterpicker").addClass("hide");
				},
				init : function(){
					var _self = this;
					_self.bindEvent();
					_self.dataInit();
				}
			};
			service.init();
	});