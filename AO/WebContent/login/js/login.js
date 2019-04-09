$(function(){
	$('#account').focus(function(){
		$('#submitMsg').attr("disabled", false);
	});
	$('#pswd').focus(function(){
		$('#submitMsg').attr("disabled", false)
	});
	
	$('#submitMsg').click(function(){
		var boole=true;
		var account=$('#account').val();
		var pswd=$('#pswd').val();
		var vcode=$('#vCode').val();
		
		if(account.length==0){
			boole=false;
			$('#accountError').text("不能为空");
		}
		if(pswd.length==0){
			boole=false;
			$('#pswdError').text("不能为空");
		}
		if(vcode.length==0){
			boole=false;
			$('#vCodeError').text("不能为空");
		}
		if(boole==false){
			$('#submitMsg').attr("disabled", true)
			return false;
		}else{
			return true;
		}
	})
})
