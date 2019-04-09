$(function(){
	
	$("#qrtjxm").click(function(){
		$("#proNameError").text("");
		$("#aliastError").text("");
		var bool=true;
		if(!verifyProAlias()){
			 bool=false;
		 }
		if(!vervifyproName()){
			
			bool=false;
		}
		return bool;
	})
	
	$("#alias").blur(function(){
		verifyProAlias();
	})
	
})

//验证项目名长度
function vervifyproName(){
	var bool=true;
	var name=$("#proName").val();
	if(name.length<=0 || name.length>30){
		$("#proNameError").text("项目名长度应为：1-30 ！");
		$("#proNameError").css("color","red");
		bool=false;
	}else{
		$("#proNameError").text("正确！");
		$("#proNameError").css("color","green");
	}
	return bool;
}

//验证别名
function verifyProAlias(){
	var bool=true;
	var alias=$("#alias").val();
	var reg = /^[0-9a-zA-Z]+$/;
	if(alias.length<2 || alias.length>10){
		bool=false;
		$("#aliastError").text("别名长度为：1-10！");
		$("#aliastError").css("color","red");
	}else if(!reg.test(alias)){
		bool=false;
		$("#aliastError").text("");
		$("#aliastError").text("别名只能用英文或字母！！");
		$("#aliastError").css("color","red");
	}	
	else if(alias.substring(0,2)=="t_"){
		$("#aliastError").text("别名错误，请不要以 t_ 开头！");
		$("#aliastError").css("color","red");
		bool=false;
	}
	else{
		$.ajax({
			cache:false,
			async:false,   //等ajax执行完毕后再继续执行后续函数
            type: "GET",
            url: "/AO/edu/ajaxVerifyProAlias.do",
            data: {alias:alias},
            dataType: "json",
            success: function(data){
            	if(!data){
            		$("#aliastError").text("");
            		bool=true;
            		$("#aliastError").text("别名可用！");
            		$("#aliastError").css("color","green");
            	}else{
            		$("#aliastError").text("");
            		bool=false;
            		$("#aliastError").text("别名已存在！");
            		$("#aliastError").css("color","red");
            	}
            }
        });
	}
	return bool;
}