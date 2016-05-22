require([ 'jquery', 'global', 'jquery.mobile'],
		function($, global) {
		var balance = {
			sizeInit : function(){
			},
			bindEvent : function(){
				var _self = this;
				$(".fui-arrow-left2").tap(function(){
					window.location.replace(global.context + "/web/me/index?token=" + global.token);
				});
				$("#balance").tap(function(){
					window.location.href = global.context + "/web/balance/detail?token=" + global.token;
				});
				$("#bankcard").tap(function(){
					window.location.href = global.context + "/web/balance/bank/index?token=" + global.token;
				});
				$(".border div").tap(function(){
					$(this).addClass("active");
					$(this).siblings().removeClass("active");
					$(".progress.hide").removeClass("hide");
					_self.searchTransaction($(this).attr("data-id"));
				});
			},
			searchTransaction : function(interval){
				var data = {
					token : global.token,
					interval : interval
				}
				$.ajax({
					type : "POST",
					url : global.context + "/web/balance/searchTransaction",
					data : JSON.stringify(data),
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					success : function(msg) {
						$(".progress").addClass("hide");
						var _opts = "";
						if(msg.list.length == 0){
							_opts = "<div class='d-none'>暂无交易记录</div>";
							$("#transaction").css("border","0");
						}
						for(var i = 0; i < msg.list.length; i++){
							_opts += "<li><div class=\"d-left\"><label>" + msg.list[i].transName + "</label><label class=\"d-balance\">余额：" + (msg.list[i].balance/100).toFixed(2) + "</label></div><div class=\"d-right\"><label class=\"d-time\">" + msg.list[i].transTime + "</label>";
							if(msg.list[i].code == 101){
								_opts += "<label class=\"d-money consume\">-";
							}
							if(msg.list[i].code == 201){
								_opts += "<label class=\"d-money deposit\">+";
							}
							_opts += (msg.list[i].transMoney/100).toFixed(2) + "</label></div></li>";
							$("#transaction").css("border-bottom","1px solid #e0e0e0");
						}
						$("#transaction").html(_opts);
					}
				});
			},
			init : function(){
				var h = $("body").height();
				$(".fui-spin5").css("line-height", (h + 225) + 'px');
				var _self = this;
				_self.bindEvent();
				_self.sizeInit();
				_self.searchTransaction(1);
			}
		};
		balance.init();
});