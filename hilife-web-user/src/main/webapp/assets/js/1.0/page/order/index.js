require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
		var currentPage = 1;
		var order = {
			sizeInit : function(){
				var w = $(".li-d-1").width();
				$(".con").width(w - 70 + 'px');
			},
			bindEvent : function(){
				var _self = this;
				_self.search(currentPage, null);
				$(".content-clsy ul>li>div").tap(function(){
					$(this).addClass("active");
					$(this).parent("li").siblings().children().removeClass("active");
				});
				$(window).scroll(function(){
					if($(window).scrollTop() > 93){
						$(".header").addClass("header-in");
						$(".content-clsy").addClass("clsy-in");
					}else if($(window).scrollTop() >= 0 && $(window).scrollTop() < 50){
						$(".header").removeClass("header-in");
						$(".content-clsy").removeClass("clsy-in");
					}
				});
				$("#orderAll").tap(function(){
					_self.search(currentPage, null);
				});
				$("#ordering").tap(function(){
					_self.search(currentPage, 301);
				});
				$("#paying").tap(function(){
					_self.search(currentPage, 501);
				});
				$("#praising").tap(function(){
					_self.search(currentPage, 601);
				});
			},
			search : function(currentPage,orderStatus){
				var _self = this;
				var data = {
						token : global.token,
						orderStatus : orderStatus,
						currentPage : currentPage,
						pageSize : 10
					};
				$.ajax({
					type : "POST",
					url : global.context + "/web/order/searchOrder",
					data : JSON.stringify(data),
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					success : function(msg) {
						var orders = msg.list;
						var _opts = "";
						for(var i = 0; i < orders.length; i++){
							if(orders[i].orderType == 1){ //服务订单
								_opts += "<li><div class=\"li-title\"><label>[服务]</label>" + orders[i].list[0].productDto.productName + "</div><div class=\"li-detail\"><div class=\"li-d-1\"><img class=\"pic\" src=\"" + global.context + "/assets/img/service-default.png\"><div class=\"con\"><div class=\"con-1\">" + orders[i].createDate + "</div><div class=\"con-2\">" + orders[i].serviceAddress + "</div></div></div></div><div class=\"li-options\">";
								if(orders[i].orderStatus == 101){
									_opts += "<button class=\"highlight\" type=\"button\">取消</button>";
								}
								if(orders[i].orderStatus == 201){
									_opts += "<button class=\"highlight\" type=\"button\">已接单</button>";
								}
								if(orders[i].orderStatus == 301){
									_opts += "<button class=\"highlight\" type=\"button\">服务中</button>";
								}
								if(orders[i].orderStatus == 501){
									_opts += "<button class=\"highlight\" type=\"button\">待付款</button>";
								}
								if(orders[i].orderStatus == 601){
									_opts += "<button class=\"highlight\" type=\"button\">待评价</button>";
								}
								_opts += "<button type=\"button\">查看详情</button></div></li>";
							}
							if(orders[i].orderType == 0){ //商品订单
								_opts += "<li><div class=\"li-title\"><label style=\"color:#4f9d9d;\">[商品]</label>生活直达&nbsp;服务到家</div><div class=\"li-detail\">";
								for(var j = 0; j < orders[i].list.length; j++){
									_opts += "<div class=\"li-d-1\"><img class=\"pic\" src=\"" + orders[i].list[j].productDto.image + "\"><div class=\"con\"><div class=\"con-3\"><div>" + orders[i].list[j].productDto.productName + "</div><div class=\"ctg\">" + orders[i].list[j].productDto.remark + "</div></div><div class=\"con-4\">￥ " + orders[i].list[j].price/100 + "<br><label style=\"color:#9d9d9d;font-size:10px;\">x" + orders[i].list[j].num + "</label></div></div></div>";
								}
								_opts += "</div><div class=\"li-options\"><div class=\"li-d-2\">";
								if(orders[i].orderStatus == 101){
									_opts += "需付款￥ " + orders[i].totalAmount/100 + "元</div><button id=\"btn-cancel\" class=\"highlight\" type=\"button\">取消</button>";
								}
								if(orders[i].orderStatus == 201){
									_opts += "实付款￥ " + orders[i].totalAmount/100 + "元</div><button id=\"btn-\" class=\"highlight\" type=\"button\">待发货</button>";
								}
								if(orders[i].orderStatus == 301){
									_opts += "实付款￥ " + orders[i].totalAmount/100 + "元</div><button id=\"btn-\" class=\"highlight\" type=\"button\">确认收货</button>";
								}
								if(orders[i].orderStatus == 501){
									_opts += "需付款￥" + orders[i].totalAmount/100 + "元</div><button id=\"btn-pay\" class=\"highlight\" type=\"button\">待付款</button>";
								}
								if(orders[i].orderStatus == 601){
									_opts += "实付款￥ " + orders[i].totalAmount/100 + "元</div><button id=\"btn-evaluate\" class=\"highlight\" type=\"button\">待评价</button>";
								}
								_opts += "<button type=\"button\">查看详情</button></div></li>";
							}
						}
						$(".order-ul").html(_opts);
						_self.sizeInit();
						if (msg.totalRecord == 0) {
							$(".order-ul").html("<li></li>");
						}
					}
				});
			},
			init : function(){
				var _self = this;
				_self.bindEvent();
				//_self.sizeInit();
			}
		};
		order.init();
});