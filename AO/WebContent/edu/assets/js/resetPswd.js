$(function(){
	$(".reset").click(function(){
		var sId=$(this).attr("id");
		if(sId){
			var r=confirm('确认重置？');
			if(r){
			  resetPswd(sId);
			}else{
			 return false;
			}
			
		}else{
			alert("错误!");
		}
	})
	return false;
})

function resetPswd(sId){
	$.ajax({
		cache:false,
		async:false,   //等ajax执行完毕后再继续执行后续函数
        type: "GET",
        url: "/AO/edu/ajaxResetpswdForUser.do", 
        data: {sId:sId},
        dataType: "json",
        success: function(data){
        	if(data){
        		alert("重置成功");
        	}else{
        		alert("重置失败");
        	}
        }
    });
}