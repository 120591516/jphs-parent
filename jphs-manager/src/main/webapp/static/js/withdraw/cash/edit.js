$(function() {
	var box = document.getElementsByClassName("userPayPrice");
	var totalUserPayPrice = 0;
	for(var i=0;i<box.length;i++){
		totalUserPayPrice +=parseFloat(box[i].innerHTML);
	}
    $("#totalUserPayPrice").html(totalUserPayPrice);
    var box2 = document.getElementsByClassName("profit");
    var totalProfit = 0;
    for(var i=0;i<box.length;i++){
    	totalProfit +=parseFloat(box2[i].innerHTML);
	}
    $("#totalProfit").html(totalProfit);
    var box3 = document.getElementsByClassName("nursePayPrice");
    var totalNursePayPrice = 0;
    for(var i=0;i<box.length;i++){
    	totalNursePayPrice +=parseFloat(box3[i].innerHTML);
	}
    $("#totalNursePayPrice").html(totalNursePayPrice);
    
	$('#save').click(function() {
		if ($('#status').val() == 1) {
			if (confirm('您确定要通过提现审核吗？')) {
				$("#withdrawForm").submit();
			}
		} else {
			if ($('#remark').val() == "") {
				alert('请输入不通过理由');
			} else {
				$("#withdrawForm").submit();
			}
		}
	})
})
