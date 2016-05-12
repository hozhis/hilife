require([ 'jquery', 'global', 'jquery.mobile', 'bootstrap','pnotify'],
		function($, global) {
			var me = {
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
					$(".fui-shopping_cart").tap(function(){
						window.location.href = global.context + "/web/goods/shopcart?token=" + global.token;
					});
					$("#searchbox").blur(function(){
						$(window).scrollTop(0);
					});
					$(".search-btn").tap(function(){
						if($("#searchbox").val() != "" ){
							window.location.href = global.context + "/web/goods/index?searchStr=" +
										encodeURI(encodeURI($("#searchbox").val())) + "&token=" + global.token;
						}else{
							layer.open({
							    content: '搜索条件不能为空！',
							    btn: ['重新输入','取消'],
							    shadeClose: false,
							    yes: function(){
							    	layer.closeAll();
							    	$("#searchbox").focus();
							    }, no: function(){
							    }
							});
						}
					});
				},
				init : function() {
					var _self = this;
					_self.bindEvent();
					_self.slider();
					//$("#carousel-hilife").carousel();
				}
			};
			me.init();
		});