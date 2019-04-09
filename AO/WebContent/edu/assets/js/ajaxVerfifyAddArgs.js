$(function(){
	$("#qraddargs").click(function(){
		$("#aNameError").text("");
		$("#aAliasError").text("");
		var bool=true;
		if(!verifyArgsaAlias()){
			 bool=false;
		 }
		if(!verifyaName()){
			bool=false;
		}
		return bool;
	})
	
	$("#aAlias").blur(function(){
		verifyArgsaAlias();
	})
	
})

//验证属性名
function verifyaName(){
	var bool=true;
	var name=$("#aName").val();
	if(name.length<1 || name.length>20){
		$("#aNameError").text("属性长度应为：1-20 ！");
		$("#aNameError").css("color","red");
		bool=false;
	}else{
		$("#aNameError").text("正确 ！");
		$("#aNameError").css("color","green");
	}
	return bool;
}

//验证属性别名
function verifyArgsaAlias(){
	var bool=true;
	var aAlias=$("#aAlias").val();
	var pro=$("#pName").text();
	var reg = /^[a-zA-Z]+$/;
	if(aAlias.length<1 || aAlias.length>10){
		bool=false;
		$("#aAliasError").text("别名长度应为：1-10 ！");
		$("#aAliasError").css("color","red");
	}else if(!reg.test(aAlias)){
		bool=false;
		$("#aAliasError").text("");
		$("#aAliasError").text("别名只能用英文字母！！");
		$("#aAliasError").css("color","red");
	}	
	else if(aAlias.substring(0,1)=="a"){
		$("#aAliasError").text("别名错误，请不要以 a 开头！");
		$("#aAliasError").css("color","red");
		bool=false;
	}
	else{
		$.ajax({
			cache:false,
			async:false,   //等ajax执行完毕后再继续执行后续函数
            type: "GET",
            url: "/AO/edu/verifyArgsaAlias.do",
            data: {aAlias:aAlias,aPname:pro},
            dataType: "json",
            success: function(data){
            	if(!data){
            		$("#aAliasError").text("");
            		bool=true;
            		$("#aAliasError").text("别名可用！");
            		$("#aAliasError").css("color","green");
            	}else{
            		$("#aAliasError").text("");
            		bool=false;
            		$("#aAliasError").text("别名已存在！");
            		$("#aAliasError").css("color","red");
            	}
            }
        });
	}
	return bool;
}