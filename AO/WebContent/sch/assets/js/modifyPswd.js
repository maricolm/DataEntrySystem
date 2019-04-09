$(function(){
	$('#subNP').click(function(){
		var pwsd=$('#newpswd').val();
		var rpswd=$('#rnpswd').val();
		if(pwsd==''){
			$('#npswdError').text('不能为空');
			$('#subNP').attr('disabled',true);
		}
		if(rpswd==''){
			$('#rnpswdError').text('不能为空');
			$('#subNP').attr('disabled',true);
		}
	})
	
	$('#newpswd').blur(function(){
		if($('#newpswd').val() == ''){
			$('#npswdError').text('不能为空');
			$('#subNP').attr('disabled',true);
		}
	})
	$('#newpswd').focus(function(){
		$('#npswdError').text('');
		$('#subNP').attr('disabled',false);
	})
	
	$('#rnpswd').blur(function(){
		$('#subNP').attr('disabled',false);
		$('#rnpswdError').text('');
		var pwsd=$('#newpswd').val();
		var rpswd=$('#rnpswd').val();
		if(rpswd==''){
			$('#rnpswdError').text('不能为空');
			$('#subNP').attr('disabled',true);
		}else{
			if(pwsd!=rpswd){
				$('#rnpswdError').text('两次输入不一致');
				$('#subNP').attr('disabled',true);
			}else{
				$('#rnpswdError').text('');
				$('#subNP').attr('disabled',false);
			}
		}
	})
	$('#rnpswd').focus(function(){
		$('#rnpswdError').text('');
		$('#subNP').attr('disabled',false);
	})
})