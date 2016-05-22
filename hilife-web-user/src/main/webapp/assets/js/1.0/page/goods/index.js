require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
			var goods = {
				searchProduct : function(onsaleType, searchStr, typeId, sortBy, order) {
					var data = {
							token : global.token,
							onsaleType : onsaleType,
							searchStr : searchStr,
							typeId : typeId,
							sortBy : sortBy,
							order : order
						};
					$.ajax({
						type : "POST",
						url : global.context + "/web/goods/searchProduct",
						dataType : "json",
						data : JSON.stringify(data),
						contentType : "application/json; charset=utf-8",
						success : function(msg) {
							$(".progress").addClass("hide");
							var _opts = "";
							for(var i = 0; i < msg.list.length; i++){
								_opts += "<li data-id=\"" + msg.list[i].productId + "\"><a href=\"" + global.context + "/web/goods/detail/" + msg.list[i].productId + "?token=" + global.token + "\" target=\"_top\"><div class=\"product-item\"><div class=\"item-image\"><img src=\"" + msg.list[i].image + "\"></div><div class=\"item-desc\"><span>￥" + (msg.list[i].price/100).toFixed(2) + "<label>已售：" + msg.list[i].saleAmount + "</label></span><span>" + msg.list[i].productName + "</span></div></div></a></li>";
							}
							$(".product-ul").html(_opts);
						}
					});
				},
				bindEvent : function() {
					var _self = this;
					$(".fui-arrow-left2").tap(function(){
						window.history.back();
					});
					$(".icon-search").tap(function(){
						$("#searchStr").val($("#search").val());
						$(".content-title ul>li").eq(0).trigger("tap");
					});
					$(".content-title ul>li").tap(function(){
						$(".progress").removeClass("hide");
						$(this).addClass("active");
						$(this).siblings().removeClass("active");
						$(this).siblings().children().removeClass("active");
						if($(this).children(".fui-triangle-up-small").hasClass("active")){
							$(this).children(".fui-triangle-up-small").removeClass("active");
							$(this).children(".fui-triangle-down-small").addClass("active");
							_self.searchProduct($("#onsaleType").val(), $("#searchStr").val(), $("#typeId").val(), $(this).attr("data-id"), "DESC");
							//降序
						}else{
							$(this).children(".fui-triangle-up-small").addClass("active");
							$(this).children(".fui-triangle-down-small").removeClass("active");
							_self.searchProduct($("#onsaleType").val(), $("#searchStr").val(), $("#typeId").val(), $(this).attr("data-id"), "ASC");
							//升序
						}
					});
				},
				init : function() {
					var h = $("body").height();
					$(".fui-spin5").css("line-height",h + 'px');
					var _self = this;
					_self.bindEvent();
				}
			};
			goods.init();
		});