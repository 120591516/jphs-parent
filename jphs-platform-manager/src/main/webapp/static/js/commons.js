function gotoPage(url, p) {
	var input_p = $("<input type='hidden' name='p' value='" + p + "'/>")
	$("#serach-form").append(input_p);
	$("#serach-form").submit();
}
function goPage(url) {
	var p = $("#goPage").val();
	var input_p = $("<input type='hidden' name='p' value='" + p + "'/>")
	$("#serach-form").append(input_p);
	$("#serach-form").submit();
}
function pageChange(){
	var p = $("#goPage").val();
	if(isNull(p)){
		p=1;	
	}
	var max=$("#goPage").attr("max");
	if(parseInt(p)>parseInt(max)){
		$("#goPage").val(max);
	}
	
}

function putcode(){
	var otype = "post";
	var osync = false;
	
	var s = httpRequest("/verification/sendMassage.json","",otype,osync);
	if(s.resultcode == 1){
		alert("短信发送成功！");
	}else{
		alert(s.msg);
	}
}

function yzCode(code){
	var otype = "post";
	var osync = false;
	var param = {
		verificattionCode : code,
	};
	var s = httpRequest("/verification/ifValidateCode.json",param,otype,osync);
	if(s.resultcode == 1){
		return true;
	}else{
		return false;
	}
}

/**
 * 获取上一个月
 *
 * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
 */
function getPreMonth(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    month = parseInt(month) - 1;
    if (month == 0) {
        year = parseInt(year) - 1;
        month = 12;
    }
    if (month < 10) {
        month = '0' + month;
    }
    return year + '-' + month;
}

/**
 * 获取下一个月
 *
 * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
 */        
function getNextMonth(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    var month = arr[1]; //获取当前日期的月份
    var month = parseInt(month) + 1;
    if (month == 13) {
        year = parseInt(year) + 1;
        month = 1;
    }
    if (month < 10) {
        month = '0' + month;
    }
    return year + '-' + month;
}
/**
 * @param date
 * @returns {String} 年-月-日
 */
function getDateTime(date) {
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hh = date.getHours();
	var mm = date.getMinutes();
	var ss = date.getSeconds();
	return year + "-" + month + "-" + day;
}
/**
 * @param date
 * @returns {String} 年
 */
function format_year(date) {
	var year = date.getFullYear();
	return year;
}
/**
 * @param date
 * @returns {String} 年-月
 */
function format_month(date) {
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	return year + "-" + month;
}
/**
 * 获取上一个月
 *
 * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
 */
function getPreYear(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    return parseInt(year) - 1;
}

/**
 * 获取下一个月
 *
 * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
 */        
function getNextYear(date) {
	var arr = date.split('-');
    var year = arr[0]; //获取当前日期的年份
    return parseInt(year) +1;
}
function isNull(str){
	if (str !== null || str !== undefined || str !== '') {
		return false;
	}
}
// 序列化提交
function httpRequest(httpUrl, httpParam, httpType, async) {
	var reqResult = {
		"result" : 0,
		"message" : "没有请求服务器或接受到返回值"
	};
	$.ajax({
		url : httpUrl,
		type : httpType,
		async : async,
		data : httpParam,
		dataType : 'json',
		success : function(msg) {
			if (msg.result == 0) {
				alert(msg.message);
				return false;
			}
			reqResult = msg;
			return msg;
		},
		error : function(data) {
			/**
			 * 此处应为弹出公共提示信息窗口 提示错误信息 并返回登录页面 return false;
			 */
			alert("系统错误，" + data.status);
		}
	});
	return reqResult;
}
jQuery.validator
		.addMethod(
				"isMobile",
				function(value, element) {
					var length = value.length;
					var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
					return this.optional(element)
							|| (length == 11 && mobile.test(value));
				}, "请正确填写您的手机号码");
jQuery.validator.addMethod("isIdCardNo", function(value, element) {
	return this.optional(element) || idCardNoUtil.checkIdCardNo(value);
}, "请正确输入您的身份证号码");
jQuery.validator.addMethod("accept", function(value, element, param) {
	var index = value.lastIndexOf("@");
	var ext = value.substr(index);
	return this.optional(element) || (param == ext);
}, "请输入带有公司后缀的邮箱");
jQuery.validator.addMethod("isTrue",
		function(value, element, userId) {
			var otype = "post";
			var osync = false;
			var param = {
				chackValue : value,
				userId : userId,
			};
			var result = httpRequest("/system/user/chackUser.json", param,
					otype, osync);
			return this.optional(element) || (result == 0);
		}, "此名称已被使用！！！");
jQuery.validator.addMethod("jobtitleIsTrue", function(value, element, id) {
	var otype = "post";
	var osync = false;
	var param = {
		name : value,
		id : id,
	};
	var result = httpRequest("/jobtitle/checkName.json", param, otype, osync);
	return this.optional(element) || (result == 0);
}, "此名称已被使用！！！");
jQuery.validator.addMethod("jobtitleTypeIsTrue", function(value, element, id) {
	var otype = "post";
	var osync = false;
	var param = {
		name : value,
		id : id,
	};
	var result = httpRequest("/jobtitle/type/checkName.json", param, otype, osync);
	return this.optional(element) || (result == 0);
}, "此名称已被使用！！！");
jQuery.validator.addMethod("checkPassword", function(value, element, userId) {
	var otype = "post";
	var osync = false;
	var param = {
		password : value,
		userId : userId,
	};
	var result = httpRequest("/system/user/checkPassword.json", param, otype,
			osync);
	return this.optional(element) || (result);
}, "此名称已被使用！！！");
jQuery.validator.addMethod("roleName", function(value, element, id) {
	var otype = "post";
	var osync = false;
	var param = {
		name : value,
		id : id,
	};
	var result = httpRequest("/system/role/checkName.json", param, otype,
			osync);
	return this.optional(element) || (result==0);
}, "此名称已被使用！！！");
jQuery.validator.addMethod("isChinese", function(value, element) {
	var chinese = /^[\u4e00-\u9fa5]+$/;
	return this.optional(element) || (chinese.test(value));
}, "请输入中文");

var idCardNoUtil = {

	provinceAndCitys : {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外"
	},
	powers : [ "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9",
			"10", "5", "8", "4", "2" ],
	parityBit : [ "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" ],
	genders : {
		male : "男",
		female : "女"
	},
	checkAddressCode : function(addressCode) {
		var check = /^[1-9]\d{5}$/.test(addressCode);
		if (!check)
			return false;
		if (idCardNoUtil.provinceAndCitys[parseInt(addressCode.substring(0, 2))]) {
			return true;
		} else {
			return false;
		}
	},
	checkBirthDayCode : function(birDayCode) {
		var check = /^[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/
				.test(birDayCode);
		if (!check)
			return false;
		var yyyy = parseInt(birDayCode.substring(0, 4), 10);
		var mm = parseInt(birDayCode.substring(4, 6), 10);
		var dd = parseInt(birDayCode.substring(6), 10);
		var xdata = new Date(yyyy, mm - 1, dd);
		if (xdata > new Date()) {
			return false;// 生日不能大于当前日期
		} else if ((xdata.getFullYear() == yyyy)
				&& (xdata.getMonth() == mm - 1) && (xdata.getDate() == dd)) {
			return true;
		} else {
			return false;
		}
	},
	getParityBit : function(idCardNo) {
		var id17 = idCardNo.substring(0, 17);

		var power = 0;
		for (var i = 0; i < 17; i++) {
			power += parseInt(id17.charAt(i), 10)
					* parseInt(idCardNoUtil.powers[i]);
		}

		var mod = power % 11;
		return idCardNoUtil.parityBit[mod];
	},
	checkParityBit : function(idCardNo) {
		var parityBit = idCardNo.charAt(17).toUpperCase();
		if (idCardNoUtil.getParityBit(idCardNo) == parityBit) {
			return true;
		} else {
			return false;
		}
	},
	checkIdCardNo : function(idCardNo) {
		// 15位和18位身份证号码的基本校验
		var check = /^\d{15}|(\d{17}(\d|x|X))$/.test(idCardNo);
		if (!check)
			return false;
		// 判断长度为15位或18位
		if (idCardNo.length == 15) {
			return idCardNoUtil.check15IdCardNo(idCardNo);
		} else if (idCardNo.length == 18) {
			return idCardNoUtil.check18IdCardNo(idCardNo);
		} else {
			return false;
		}
	},

	// 校验15位的身份证号码
	check15IdCardNo : function(idCardNo) {
		// 15位身份证号码的基本校验
		var check = /^[1-9]\d{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}$/
				.test(idCardNo);
		if (!check)
			return false;
		// 校验地址码
		var addressCode = idCardNo.substring(0, 6);
		check = idCardNoUtil.checkAddressCode(addressCode);
		if (!check)
			return false;
		var birDayCode = '19' + idCardNo.substring(6, 12);
		// 校验日期码
		return idCardNoUtil.checkBirthDayCode(birDayCode);
	},

	// 校验18位的身份证号码
	check18IdCardNo : function(idCardNo) {
		// 18位身份证号码的基本格式校验
		var check = /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}(\d|x|X)$/
				.test(idCardNo);
		if (!check)
			return false;
		// 校验地址码
		var addressCode = idCardNo.substring(0, 6);
		check = idCardNoUtil.checkAddressCode(addressCode);
		if (!check)
			return false;
		// 校验日期码
		var birDayCode = idCardNo.substring(6, 14);
		check = idCardNoUtil.checkBirthDayCode(birDayCode);
		if (!check)
			return false;
		// 验证校检码
		return idCardNoUtil.checkParityBit(idCardNo);
	},

	formateDateCN : function(day) {
		var yyyy = day.substring(0, 4);
		var mm = day.substring(4, 6);
		var dd = day.substring(6);
		return yyyy + '-' + mm + '-' + dd;
	},

	// 获取信息
	getIdCardInfo : function(idCardNo) {
		var idCardInfo = {
			gender : "", // 性别
			birthday : "" // 出生日期(yyyy-mm-dd)
		};
		if (idCardNo.length == 15) {
			var aday = '19' + idCardNo.substring(6, 12);
			idCardInfo.birthday = idCardNoUtil.formateDateCN(aday);
			if (parseInt(idCardNo.charAt(14)) % 2 == 0) {
				idCardInfo.gender = idCardNoUtil.genders.female;
			} else {
				idCardInfo.gender = idCardNoUtil.genders.male;
			}
		} else if (idCardNo.length == 18) {
			var aday = idCardNo.substring(6, 14);
			idCardInfo.birthday = idCardNoUtil.formateDateCN(aday);
			if (parseInt(idCardNo.charAt(16)) % 2 == 0) {
				idCardInfo.gender = idCardNoUtil.genders.female;
			} else {
				idCardInfo.gender = idCardNoUtil.genders.male;
			}

		}
		return idCardInfo;
	},
	getId15 : function(idCardNo) {
		if (idCardNo.length == 15) {
			return idCardNo;
		} else if (idCardNo.length == 18) {
			return idCardNo.substring(0, 6) + idCardNo.substring(8, 17);
		} else {
			return null;
		}
	},
	getId18 : function(idCardNo) {
		if (idCardNo.length == 15) {
			var id17 = idCardNo.substring(0, 6) + '19' + idCardNo.substring(6);
			var parityBit = idCardNoUtil.getParityBit(id17);
			return id17 + parityBit;
		} else if (idCardNo.length == 18) {
			return idCardNo;
		} else {
			return null;
		}
	}
};
// 验证护照是否正确
function checknumber(number) {
	var str = number;
	// 在JavaScript中，正则表达式只能使用"/"开头和结束，不能使用双引号
	var Expression = /(P\d{7})|(G\d{8})/;
	var objExp = new RegExp(Expression);
	if (objExp.test(str) == true) {
		return true;
	} else {
		return false;
	}
};
/**
 * 获取url参数 调用的时候 var orderId = GetRequest()['参数名'];
 */

function  GetRequest()  {      
	var  url  =  location.search;  //获取url中"?"符后的字串   
	var  theRequest  =  new  Object();      
	if (url.indexOf("?")  !=  -1)  {         
		var  str  =  url.substr(1);         
		strs  =  str.split("&");         
		for(var  i  =  0;  i  <  strs.length;  i ++)  {            
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);         
		}      
	}      
	return  theRequest;   
}
// 退出提示
function logout() {
	if(window.confirm('你确定要退出吗？')){
		window.location.href="/loginOut.jhtml";
	}
}


/**
 * 判断value值是否为空
 * @param valData
 * @returns {Boolean}
 */
function valIsNotNull(valData){
	var valDatas=$("#"+valData).val();
	if(valDatas == null || valDatas == ''){
		return false;
	}else{
		return true;
	}
}