$(document).ready(function() {
	$(".form_date").datetimepicker({  
		/*format: 'yyyy-mm',  
        weekStart: 1,  
        autoclose: true,  
        startView: 3,  
        minView: 3,  
        forceParse: false,  
        language: 'zh-CN'  */
		language:  'zh-CN',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 3,
		minView: 3,
		forceParse: false
    });
	var startDay =timeList[0];
	$("#last-month").click(function() {
		var month=getPreMonth(startDay);
		$('#data').load("/access/log/showDataByMonth.jhtml?month="+ month, "");
	});
	$("#next-month").click(function() {
		var month=getNextMonth(startDay);
		$('#data').load("/access/log/showDataByMonth.jhtml?month="+ month, "");
	});
	
	$("#search").click(function() {
		var month = $("#month").val();
		if(month==""){
			alert("请选择查询日期");
		}else{
			alert(month);
			$('#data').load("/access/log/showDataByMonth.jhtml?month="+ month, "");
		}
	});
});
