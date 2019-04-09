$(function(){
	$("#inAccount").blur(function(){
		verifyinAccount();
	})
	$("#sEmail").blur(function(){
		vervifysEmail();
	})
	$("#addQR").click(function(){
		var bool=true;
		if(!verifyinAccount()){
			bool= false;
		}if(!vervifySchNAme()){
			bool=false;
		}if(!vervifysDepartment()){
			bool=false;
		}if(!vervifysUname()){
			bool=false;
		}if(!vervifysEmail()){
			bool=false;
		}if(!vervifysPhone()){
			bool=false;
		}
		return bool;
	})
	
})


//验证账号
function verifyinAccount(){
	var bool=true;
	var re=/^[0-9a-zA-Z]*$/;
	var account=$("#inAccount").val();
	if(account.length<=6 || account.length>20){
		$("#inAccountError").text("账号长度必须大于5，小于等于20！");
		$("#inAccountError").css("color","red");
		bool=false;
	}else if(!re.test(account)){
		$("#inAccountError").text("账号只能为数字或字母！");
		$("#inAccountError").css("color","red");
		bool=false;
	} 
	else{
		$.ajax({
			cache:false,
			async:false,   //等ajax执行完毕后再继续执行后续函数
            type: "GET",
            url: "/AO/edu/ajaxVerifyAddAccount.do",
            data: {account:account},
            dataType: "json",
            success: function(data){
            	if(!data){
            		bool=true;
            		$("#inAccountError").text("账号可用！");
            		$("#inAccountError").css("color","green");
            	}else{
            		bool=false;
            		$("#inAccountError").text("账号已分配！");
            		$("#inAccountError").css("color","red");
            	}
            }
        });
	}
	return bool;
}

//验证学校名
function vervifySchNAme(){
	var bool=true;
	var name=$("#sName").val();
	if(name.length<=2 || name.length>20){
		$("#sName").next().text("学校名长度必须大于2，小于等于20！");
		$("#sName").next().css("color","red");
		bool=false;
	}else{
		$("#sName").next().text("正确！");
		$("#sName").next().css("color","green");
		bool=true;
	}
	return bool;
}

//验证部门名
function vervifysDepartment(){
	var bool=true;
	var name=$("#sDepartment").val();
	if(name.length<=2 || name.length>20){
		$("#sDepartment").next().text("部门名长度必须大于2，小于等于20！");
		$("#sDepartment").next().css("color","red");
		bool=false;
	}else{
		$("#sDepartment").next().text("正确！");
		$("#sDepartment").next().css("color","green");
		bool=true;
	}
	return bool;
}

//验证用户名 sUname
function vervifysUname(){
	var bool=true;
	var name=$("#sUname").val();
	if(name.length<=1 || name.length>10){
		$("#sUname").next().text("用户名长度必须大于2，小于等于10！");
		$("#sUname").next().css("color","red");
		bool=false;
	}else{
		$("#sUname").next().text("正确！");
		$("#sUname").next().css("color","green");
		bool=true;
	}
	return bool;
}

//验证邮箱
function vervifysEmail(){
	var bool=true;
	var email=$("#sEmail").val();
	var regex = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/;
	if(email.length<=0 || email.length>20){
		$("#sEmail").next().text("邮箱长度必须大于0，小于等于20！");
		$("#sEmail").next().css("color","red");
		bool=false;
	}else if(!regex.test(email)){
		$("#sEmail").next().text("邮箱格式不正确！");
		$("#sEmail").next().css("color","red");
		bool=false;
	}
	else{
		$("#sEmail").next().text("正确！");
		$("#sEmail").next().css("color","green");
		bool=true;
	}
	return bool;
}

//验证电话号码
function vervifysPhone(){
	var bool=true;
	var sPhone=$("#sPhone").val();
	var regex = /^[0-9]*$/;
	if(sPhone.length<=6 || sPhone.length>15){
		$("#sPhone").next().text("电话号码长度必须大于6，小于等于15！");
		$("#sPhone").next().css("color","red");
		bool=false;
	}else if(!regex.test(sPhone)){
		$("#sPhone").next().text("电话号码格式不正确！");
		$("#sPhone").next().css("color","red");
		bool=false;
	}
	else{
		$("#sPhone").next().text("正确！");
		$("#sPhone").next().css("color","green");
		bool=true;
	}
	return bool;
}
