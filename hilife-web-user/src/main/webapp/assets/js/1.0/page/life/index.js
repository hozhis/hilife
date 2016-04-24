require([ 'jquery', 'global', 'jquery.mobile', 'bootstrap'],
		function($, global) {
			var me = {
				sizeInit : function(){
				},
				slider : function() {
					$.ajax({
						type : "GET",
						url : global.context + "/web/life/getCarouselPictures?token=" + global.token,
						dataType : "json",
						contentType : "application/json; charset=utf-8",
						success : function(msg) {
							if(msg.code == "ACK"){
								_ols = "<li data-target=\"#carousel-hilife\" data-slide-to=\"0\" class=\"active\"></li>";
								_ods = "<div class=\"item active\"><a target='_top' href=\"" + msg.data[0].paraValue1 + "?token=" + global.token + "\"><img src=\"" + msg.data[0].paraValue2 + "\"></a></div>";
								for(var i = 1; i < msg.data.length; i++){
									_ols += "<li data-target=\"#carousel-hilife\" data-slide-to=\"" + i + "\"></li>";
									_ods += "<div class=\"item\"><a target='_top' href=\"" + msg.data[i].paraValue1 + "?token=" + global.token + "\"><img src=\"" + msg.data[i].paraValue2 + "\"></a></div>";
								}
								$(".carousel-indicators").html(_ols);
								$(".carousel-inner").html(_ods);
								var w = $(".carousel").width();
								$(".carousel").height(w/2.2);
								$(".carousel-inner").height(w/2.2);
								$(".carousel-inner img").height(w/2);
								$("#carousel-hilife").carousel();
								$("#carousel-hilife").swipeleft(function(){
									$(this).carousel("next");
								});
								$("#carousel-hilife").swiperight(function(){
									$(this).carousel("prev");
								});
							}
						}
					});
				},
				bindEvent : function() {
				},
				init : function() {
					var _self = this;
					_self.bindEvent();
					_self.slider();
					//_self.sizeInit();
				}
			};
			me.init();
		});