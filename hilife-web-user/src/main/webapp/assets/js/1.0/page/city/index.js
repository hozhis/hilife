require([ 'jquery', 'global', 'jquery.mobile','pnotify'],
		function($, global) {
		var order = {
			sizeInit : function(){
				var h1 = $("body").height();
				var h2 = $(".alphabet").height();
				$(".alphabet").css("top", (h1 - h2 + 50)/2 + "px" )
			},
			bindEvent : function(){
				$(".fui-arrow-left2").tap(function(){
					window.history.back();
				});
			},
			initCity : function(href){
				var _self = this;
				$.ajax({
					type : "GET",
					url : global.context + "/web/city/getCity?token=" + global.token,
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					success : function(msg) {
						if (msg.code == "ACK") {
							var _opts = "";
							var _py = "<li>#</li><li>$</li>";
							for(var i = 0; i < msg.data.length; i++){
								_opts += "<div class=\"city-clsy\" id=\"" + msg.data[i].py + "\">" + msg.data[i].py + "</div><ul>"
								_py += "<li>" + msg.data[i].py + "</li>"
								for(var j = 0; j < msg.data[i].city.length; j++){
									_opts += "<li><label>" + msg.data[i].city[j] + "</label></li>";
								}
								_opts += "</ul>";
							}
							$(".alphabet").html(_py);
							$(".city-list").html(_opts);
							_self.sizeInit();
						}
					}
				});
			},
			location : function(){
				// 百度地图API功能
				var myCity = new BMap.LocalCity();
				myCity.get(function(r){
					$(".loc-city label").text(r.name);
				});
				
			},
			init : function(){
				var _self = this;
				var href = window.location;
				_self.bindEvent();
				_self.initCity(href);
				_self.location();
			}
		};
		order.init();
});