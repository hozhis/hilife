require([ 'jquery', 'global', 'jquery.mobile','pnotify' ], function($, global) {
	var detail = {
		sizeInit : function() {
			var h = $("body").height();
			//$(".map-module").height(h - 50 + 'px');
			//var w = $("i.marker").width();
			//$("i.marker").height(w);
			//$("i.marker-shadow").height(w);
			$(".result-list").height(h * 0.5 - 95 + 'px');
			$(".fui-spin5").css("line-height",h * 0.5 - 95 + 'px');
		},
		bindEvent : function() {
			var _self = this;
			$(".fui-arrow-left2").tap(function() {
				window.location.replace(global.context + "/web/address/index?token=" + global.token);
			});
			$(".nav-detail.address").tap(function(){
				$(".main-wrapper").css("pointer-events","none");
				$("#result-list").html("");
				$(".map-module").removeClass("hide");
				_self.mapInit();
			});
			$("#save").tap(function(){
				if($("#addressName").text() != "" && $("#consignee").val() != "" && $("#phone").val() != ""){
					_self.save(); 
				}else{
					layer.open({content: '关键信息不能为空', time: 1});
				}
			});
		},
		mapInit : function() {
			var city = "南通市";
			var address = $(".address").text();
			if($(".address").text() != ""){
				city = address.split("省")[1].split("市")[0]+"市";
			}else{
				address = "南通市";
			}
			// 百度地图API功能
			var map = new BMap.Map("allmap"); // 创建Map实例
			// 创建地址解析器实例
			var geocoder = new BMap.Geocoder();
			// 将地址解析结果显示在地图上,并调整地图视野
			geocoder.getPoint(address, function(point){
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
				if($("#searchbox").val() == ""){
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
					return;
				}
				$(".progress").removeClass("hide");
				var local = new BMap.LocalSearch(map, {
					onSearchComplete: function(results){
						// 判断状态是否正确
						if (local.getStatus() == BMAP_STATUS_SUCCESS){
							$(".progress").addClass("hide");
							var _opts = "<ol>";
							for (var i = 0; i < results.getCurrentNumPois(); i ++){
								_opts +=  "<li><span class=\"poi-title\">" + results.getPoi(i).title 
										+ "</span><span class=\"poi-address\">" + results.getPoi(i).province 
										+ results.getPoi(i).city + results.getPoi(i).address
										+ "</span></li>";
							}
							_opts += "</ol>";
							$("#result-list").html(_opts);
							$("#result-list li").tap(function(){
								$(".nav-detail.address").text($(this).find("span.poi-address").text());
								$(".map-module").addClass("hide");
								setTimeout(function(){$(".main-wrapper").css("pointer-events","auto");},500);
							});
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
		save : function() {
			var data = {
				token : global.token,
				addressId : $(".nav ul").attr("data-id"),
				addressName : $("#addressName").text(),
				consignee : $("#consignee").val(),
				phone : $("#phone").val()
			}
			$.ajax({
				type : "POST",
				url : global.context + "/web/address/save",
				data : JSON.stringify(data),
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				success : function(msg) {
					if(msg.code == "ACK"){
						layer.open({content: '保存成功', time: 1});
						setTimeout(function(){
							window.location.replace(global.context + "/web/address/index?token=" + global.token);
						},500);
					}
				}
			});
		},
		init : function() {
			var _self = this;
			_self.bindEvent();
			_self.sizeInit();
		}
	}
	detail.init();
});