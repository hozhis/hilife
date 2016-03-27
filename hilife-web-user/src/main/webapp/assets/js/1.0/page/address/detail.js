require([ 'jquery', 'global', 'jquery.mobile' ], function($, global) {
	var detail = {
		sizeInit : function() {
			var h = $("body").height();
			$(".map-module").height(h - 50 + 'px');
			var w = $("i.marker").width();
			$("i.marker").height(w);
			$("i.marker-shadow").height(w);
			$(".result-list").height((h - 50) * 0.4 - 45 + 'px');
			$(".shade").height((h - 50) * 0.4 - 45 + 'px');
			var hs = $(".shade").height();
			$(".fui-spin4").css("margin-top",(hs - 30)/2);
		},
		bindEvent : function() {
			$(".fui-arrow-left2").tap(function() {
				window.history.back();
			});
		},
		mapInit : function() {
			var city = "南通市";
			city = $(".address").text().split("省")[1].split("市")[0]+"市";
			// 百度地图API功能
			var map = new BMap.Map("allmap"); // 创建Map实例
			// 创建地址解析器实例
			var geocoder = new BMap.Geocoder();
			// 将地址解析结果显示在地图上,并调整地图视野
			geocoder.getPoint($(".address").text(), function(point){
				if (point) {
					map.centerAndZoom(point, 14);
				}else{
					// 默认地址南通市
					//map.centerAndZoom("南通市",12);
					alert("您的地址我们没有找到!");
					// 根据浏览器自动定位，存在误差
					var geolocation = new BMap.Geolocation();
					geolocation.getCurrentPosition(function(r){
						if(this.getStatus() == BMAP_STATUS_SUCCESS){
							map.clearOverlays(); 
							var new_point = new BMap.Point(r.point.lng, r.point.lat);
							//map.panTo(new_point);
							map.centerAndZoom(new_point,16);
						}
						else {
							alert('failed'+this.getStatus());
						}        
					},{enableHighAccuracy: true})
				}
			}, city);
			// 中文检索
			$(".search-btn").tap(function(){
				$(".shade").removeClass("hide");
				var local = new BMap.LocalSearch(map, {
					onSearchComplete: function(results){
						// 判断状态是否正确
						if (local.getStatus() == BMAP_STATUS_SUCCESS){
							$(".shade").addClass("hide");
							var _opts = "<ol>";
							for (var i = 0; i < results.getCurrentNumPois(); i ++){
								_opts +=  "<li><span class=\"poi-title\">" + results.getPoi(i).title 
										+ "</span><span class=\"poi-address\">" + results.getPoi(i).address
										+ "</span></li>";
							}
							_opts += "</ol>";
							$("#result-list").html(_opts);	
						}
					}
				});
				local.search($("#searchbox").val());
			});
			// 地图的监听事件
			map.addEventListener('touchstart', function(){
				$(".marker-shadow").hide();
			});
			map.addEventListener('touchend', function(){
				$(".marker-shadow").show();
			});
			map.addEventListener("click", function(e){        
				var pt = e.point;
				geocoder.getLocation(pt, function(rs){
					var addComp = rs.addressComponents;
					alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
				});        
			});
		},
		init : function() {
			var _self = this;
			_self.bindEvent();
			_self.sizeInit();
			_self.mapInit();
		}
	}
	detail.init();
});