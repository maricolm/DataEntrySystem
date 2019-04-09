$(function(){
	$(".reset").click(function(){
		var cid=$(this).attr("id");
		if(cid){
			var r=confirm('确认重置？');
			if(r){
			  resetPswd(cid);
			}else{
			 return false;
			}
			
		}else{
			alert("错误!");
		}
	})
	return false;
})

function resetPswd(cid){
	$.ajax({
		cache:false,
		async:false,   //等ajax执行完毕后再继续执行后续函数
        type: "GET",
        url: "/AO/sch/ajaxResetpswdForUser.do",
        data: {cId:cid},
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