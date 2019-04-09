$(function(){
	$('#vCode').focus(function(){
		$('#submitMsg').attr("disabled", false);
	})
	/*异步验证vCode是否正确*/
	$('#vCode').blur(function(){
		verifyVCode();//失焦时验证验证码是否正确
	})
})

function verifyVCode(){
	var vCode = $('#vCode').val();
	if(!(vCode=='')){
		$.ajax({
			cache: false,
			async: false,
			type: "POST",
			dataType: "json",
			data: {vCode: vCode},
			url: "/AO/admin/verifyCode.do",//请求链接
			success: function(flag) {//返回true 或 false
					if(!flag) {
						$('#vCodeError').text(":×错误");
						$('#submitMsg').attr("disabled", true)
						return false;
					}else{
						$('#submit').prop('disabled',false);
						$('#vCodeError').css("color",'green');
						$('#vCodeError').text(":√ 正确");
					}
				}
			});
		}else{
			$('#vCodeError').css("color",'red');
			$('#vCodeError').text("不能为空");
			$('#submitMsg').attr("disabled", true)
		}
}
