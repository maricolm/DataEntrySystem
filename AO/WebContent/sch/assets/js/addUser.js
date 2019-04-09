$(function(){
	$("#inAccount").blur(function(){
		verifyinAccount();
	})
	$("#cEmail").blur(function(){
		vervifysEmail();
	})
	$("#addQR").click(function(){
		var bool=true;
		if(!verifyinAccount()){
			bool= false;
		}if(!vervifycDepartment()){
			bool=false;
		}if(!vervifycUname()){
			bool=false;
		}if(!vervifycEmail()){
			bool=false;
		}if(!vervifycPhone()){
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
		$("#inAccountError").text("账号长度必须大于6，小于等于20！");
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
            url: "/AO/sch/ajaxVerifyAddAccount.do",
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
/*
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
}*/

//验证部门名cDepartment
function vervifycDepartment(){
	var bool=true;
	var name=$("#cDepartment").val();
	if(name.length<=2 || name.length>20){
		$("#cDepartment").next().text("部门名长度必须大于2，小于等于20！");
		$("#cDepartment").next().css("color","red");
		bool=false;
	}else{
		$("#cDepartment").next().text("正确！");
		$("#cDepartment").next().css("color","green");
		bool=true;
	}
	return bool;
}

//验证用户名 sUname
function vervifycUname(){
	var bool=true;
	var name=$("#cUname").val();
	if(name.length<=1 || name.length>10){
		$("#cUname").next().text("用户名长度必须大于2，小于等于10！");
		$("#cUname").next().css("color","red");
		bool=false;
	}else{
		$("#cUname").next().text("正确！");
		$("#cUname").next().css("color","green");
		bool=true;
	}
	return bool;
}

//验证邮箱
function vervifycEmail(){
	var bool=true;
	var email=$("#cEmail").val();
	var regex = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/;
	if(email.length<=0 || email.length>20){
		$("#cEmail").next().text("邮箱长度必须大于0，小于等于20！");
		$("#cEmail").next().css("color","red");
		bool=false;
	}else if(!regex.test(email)){
		$("#cEmail").next().text("邮箱格式不正确！");
		$("#cEmail").next().css("color","red");
		bool=false;
	}
	else{
		$("#cEmail").next().text("正确！");
		$("#cEmail").next().css("color","green");
		bool=true;
	}
	return bool;
}

//验证电话号码
function vervifycPhone(){
	var bool=true;
	var sPhone=$("#cPhone").val();
	var regex = /^[0-9]*$/;
	if(sPhone.length<=6 || sPhone.length>15){
		$("#cPhone").next().text("电话号码长度必须大于6，小于等于15！");
		$("#cPhone").next().css("color","red");
		bool=false;
	}else if(!regex.test(sPhone)){
		$("#cPhone").next().text("电话号码格式不正确！");
		$("#cPhone").next().css("color","red");
		bool=false;
	}
	else{
		$("#cPhone").next().text("正确！");
		$("#cPhone").next().css("color","green");
		bool=true;
	}
	return bool;
}
