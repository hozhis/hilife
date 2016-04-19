<link href="${contextPath}/assets/css/page/index/right.css" rel="stylesheet" type="text/css" />
<script src="${contextPath}/assets/js/1.0/require.js" type="text/javascript"></script>
<script type="text/javascript">
define('global',{
	context : '${rc.contextPath}',
	base_url : '${rc.contextPath}',
	locale : 'zh_CN',
	static_url : '${contextPath}',
	token : '${token}'
})
</script>
<script src="${contextPath}/assets/js/1.0/base.js" type="text/javascript"></script>
<script type="text/javascript">
	    require(['jquery','global','module/ajax',
	     'jquery.ui'],
		function($,global){
		
			var url = window.location.href;
			var index = ${menuIndex},active;
			$("#accordion>h3").each(function(){
				var i = $(this).index();
				if($(this).attr('data-id') == index){
				   active = i;
				}
			});
			
			$("#accordion").accordion({
				collapsible: true,
				animate: 200,
				active: active == 0?active:active/2,
			});

			 /*=================
			  * 系统通知
			  ===================*/
			 /* $.ajax({
			 	url : global.context + "/web/platform/systemNotice/newNotice?token=" + global.token,
			 	type: 'GET',
			 	dataType: 'json',
			 	success: function(resp){
			 		if(resp.code=="ACK" && resp.data > 0){
			 			$('#unread-num').append(resp.data).show();
			 		}else{
			 			$('#unread-num').hide();
			 		}
			 	}
			 }); */
			/*=================
			  * 用户管理
			  ===================*/
			  $.ajax({
			 	url : global.context + "/web/userManager/userName?token=" + global.token,
			 	type: 'GET',
			 	dataType: 'json',
			 	success: function(resp){
			 		if(resp.code=="ACK"){
			 			$('#username').html(resp.data);
			 		}else{
			 			$('#username').html('');
			 		}
			 	}
			 });

			 $('#name-container').mouseover(function(){
			 	$(this).addClass('dropup');
			 	$('#name-select').css('display','block');
			 });
			 $('#name-container').mouseout(function(){
			 	$(this).removeClass('dropup');
			 	$('#name-select').css('display','none');
			 });

			/*=================
			 * 登出
			 ===================*/
			$("#logout").on('click',function(){
				$.ajax({
					url: global.context+'/web/auth/logout?token='+global.token,
					type : "POST",
					dataType : "json",
					contentType : "application/json",
					success : function(msg){
						if(msg.code=="ACK"){
							window.location.replace(global.context+'/web/auth/login');
						}
					}
				});
				
			});
		});
</script>
	<div role="navigation" id="main-menu" class="main-menu">
   		<div id="main-menu-inner" style="overflow: hidden; width: auto; height: 100%;">
        <div id="accordion" class="slide">
	        <h3 data-id="0"><i class="icon_l gm"></i>商品管理</h3>
			<div style="display:none;">
				<ul>
					<li><a href="${contextPath}/web/product/index?token=${token}">商品管理</a></li>
					<li><a href="${contextPath}/?token=${token}">消息模板管理</a></li>
					<li><a href="${contextPath}/?token=${token}">APP版本管理</a></li>
				</ul>
			</div>
	        <h3 data-id="1"><i class="icon_l app"></i>APP配置</h3>
			<div style="display:none;">
				<ul>
					<li><a href="${contextPath}/?token=${token}">参数设置</a></li>
					<li><a href="${contextPath}/?token=${token}">消息模板管理</a></li>
					<li><a href="${contextPath}/?token=${token}">APP版本管理</a></li>
				</ul>
			</div>
         
		</div><!-- acccodin end -->
	   		
   		</div>
	</div>
