$(function(){
	$("#inAccount").blur(function(){
		verifyinAccount();
	})
	$("#addQR").click(function(){
		var bool=true;
		if(!verifyinAccount()){
			bool= false;
		};
		$(".test").each(function(){
			if(!$(this).val()){
				$(this).next().text("不能为空！");
				$(this).next().css("color","red");
				bool=false;
			}else{
				$(this).next().text("正确！");
				$(this).next().css("color","green");
			}
		})
		return bool;
	})
	
})

function verifyinAccount(){
	var bool=true;
	var account=$("#inAccount").val();
	if(!account){
		$("#inAccountError").text("账号不能为空！");
		$("#inAccountError").css("color","red");
		bool=false;
	}else{
		$.ajax({
			cache:false,
			async:false,   //等ajax执行完毕后再继续执行后续函数
            type: "GET",
            url: "/AO/admin/ajaxVerifyAddAccount.do",
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