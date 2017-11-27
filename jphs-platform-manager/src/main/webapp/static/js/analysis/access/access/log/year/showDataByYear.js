$(document).ready(function() {
	$(".form_date").datetimepicker({  
		 format: 'yyyy',  
         weekStart: 1,  
         autoclose: true,  
         startView: 4,  
         minView: 4,  
         forceParse: false,  
         language: 'zh-CN'
    });
	var startDay =timeList[0];
	$("#last-year").click(function() {
		var year=getPreYear(startDay);
		$('#data').load("/access/log/showDataByYear.jhtml?year="+ year, "");
	});
	$("#next-year").click(function() {
		var year=getNextYear(startDay);
		$('#data').load("/access/log/showDataByYear.jhtml?year="+ year, "");
	});
	
	$("#search").click(function() {
		var year = $("#year").val();
		if(year==""){
			alert("请选择查询日期");
		}else{
			$('#data').load("/access/log/showDataByYear.jhtml?year="+ year, "");
		}
	});
});
