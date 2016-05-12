require([ 'jquery', 'global', 'jquery.mobile', 'pnotify' ], 
	function($,global) {
		var goodsDetail = {
			size : function() {
				// var w = $(window).width();
				// $(".product-img").height(w);
			},
			bindEvent : function() {
				var h = $("#p-tap").offset().top;
				$(".fui-arrow-left2").tap(function(){
					window.history.back();
				});
				$(".fui-home3").tap(function(){
					window.location.href = global.context + "/web/product/index?token=" + global.token;
				});
				$(".fui-shopping_cart").tap(function(){
					window.location.href = global.context + "/web/goods/shopcart?token=" + global.token;
				});
				$("#p-tap>li").tap(function(){
					$(".header").hide();
					$(window).scrollTop(h);
					$(this).siblings().removeClass("active");
					$(this).addClass("active");
					$(window).scroll(function(){
						if($(window).scrollTop() < (h - 50)){
							$(".header").show();
						}else{
							$(".header").hide();
						}
					});
				});
				$(window).scroll(function(){
					if($(window).scrollTop() < (h - 50)){
						$(".header").show();
					}else{
						$(".header").hide();
					}
				});
				$("#p-tap>li").eq(0).tap(function(){
					$("#page1").removeClass("hide");
					$("#page2").addClass("hide");
				});
				$("#p-tap>li").eq(1).tap(function(){
					$("#page2").removeClass("hide");
					$("#page1").addClass("hide");
					
				});
				$("#evalpiclist li").tap(function(){
					var html = "<span>" + $(this).html() +"</span>";
					var pageii = layer.open({
					    type: 1,
					    content: html,
					    style: 'position:fixed;left:0;top:0;width:100%;height:100%;border:none;background-color:#000000;z-index:999;position:fixed;'
					});
					setTimeout(function(){
						$(".layermcont").tap(function(){
							layer.closeAll();
						});
					},500);
				});
				$("#minus").tap(function(){
					var n = $(this).siblings("input").val();
					if(n != 1){
						n--;
						$("#minus").siblings("input").val(n);
					}
				});
				$("#plus").tap(function(){
					var n = $(this).siblings("input").val();
					if(n < parseInt($("#rest-num").text())){
						n++;
						$("#plus").siblings("input").val(n);
					}
				});
			},
			init : function() {
				var _self = this;
				_self.bindEvent();
				_self.size();
			}
		};
		goodsDetail.init();
});