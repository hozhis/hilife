define(
		[ 'jquery',
		  'global',
		  'bootstrap.datatimepicker'],
		function($,global) 
 {
	
	//tab切换
	var $trigger_tab = $(".content-wrapper .trigger-tab");
	var $trigger_li = $(".content-wrapper .trigger-tab li");
	var $trigger_div = $(".content-wrapper .trigger-form-list");
	$trigger_li.click(function(){
		if(!$(this).hasClass("active")){
			$(this).siblings(".active").removeClass("active");
			$(this).addClass("active");
			var dataTrigger = $(this).attr("data-trigger");
			var $trigger_div_active = $(".content-wrapper .trigger-form-list.active");
			if($trigger_tab.siblings("."+dataTrigger).length > 0){
				$trigger_div_active.removeClass("active");
				$trigger_tab.siblings("."+dataTrigger).addClass("active");
			}
			
		}
	});
		
	/**
	 * 用于日期控件格式化
	 */
	$.fn.datetimepicker.dates['ch'] = {
		    days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六","星期日"],
		    daysShort: ["日", "一", "二", "三", "四", "五", "六","日"],
		    daysMin: ["日", "一", "二", "三", "四", "五", "六","日"],
		    months: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
		    monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		    meridiem:["上午","下午"],
		    suffix:[],
		    today:"今日"
		};
	var datetimepicker = {
			init: function(){
				$(".dateDiv .icon-remove").click(function(){
					$(this).closest("div").find("input").val("");
					$(this).closest("div").find("input").trigger("changeDate");
				});
				$(".dateDiv .icon-calendar").click(function(){
					$(this).closest("div").find("input").focus();
				});
			}
	}.init();
	
	
	$('.panel-body').on('click','.object',function(){
		if(!$(this).closest('div').hasClass('disable')){
			if($(this).hasClass('check')){
				$(this).removeClass('check').addClass('uncheck');
			}else if($(this).hasClass('uncheck')){
				$(this).removeClass('uncheck').addClass('check');
			}
		}
	});
	
	/**
	 * table回车事件触发添加行
	 */
	
	$('.jqGrid').on('keydown','input[type="text"],textarea',function(e){
		var keyCode = (e.keyCode ? e.keyCode : e.which);
        if (keyCode == 13) {
        	e.preventDefault();
        	var $input = $(this).closest('td').nextAll().find('input[type="text"],textarea');
        	if($input.length > 0){
        		$input.focus();
        	}else{
        		var $tr = $(this).closest('tr').next();
            	if($tr.length == 0){
            		if($('#addRow').length > 0){
            			$('#addRow').click();
            		}
            		$tr = $(this).closest('tr').next();
            		$tr.click();
            	}
            	else $tr.click();
        	}
        	
        }
	});

	/**
	 * 翻页填入超出现有页数调到最后一页
	 */
	var pager = $('#pager').length>0 ? 'pager': 'page';
	$('#'+pager).on('keydown','.ui-pg-input',function(e){
		var keyCode = (e.keyCode ? e.keyCode : e.which);
		if(e.keyCode == '13'){
			var topage = $(this).val();
			var total = $('#sp_1_'+pager).html();
			if(topage>total){
				$(this).val(total);
			}
		}
	});
		
});