$(function(){
	$(".xg").click(function(){
		$(this).css("display","none");
		$(this).next().css("display","block");
		$(this).parent().parent().children(".jt").css("display","none");
		$(this).parent().parent().children(".modefy").css("display","table-cell");
	})
	
	$("#addbut").click(function(){
		var varTemplate=$("#dateTab").children().last().children().first().text();
		var num=parseInt(varTemplate.substring(1));
		
		var rowTemplate = '<tr>'+
							'<td>D'+(num+1)+'</td>'+
	        				'<td><input class="ipt" type="text" value="0"></td>'+
					        '<td><input class="ipt" type="text" value="0"></td>'+
					        '<td><input class="ipt" type="text" value="0"></td>'+
					        '<td><input class="ipt" type="text" value="0"></td>'+
					        '<td><input class="ipt" type="text" value="0"></td>'+
					        '<td><input class="ipt" type="text" value="0"></td>'+
					        '<td><input class="ipt" type="text" value="0"></td>'+
					        '<td><input class="ipt" type="text" value="0"></td>'+
					        '<td>'+
					        	'<a class="del tablelink" href="">删除</a>&nbsp;&nbsp'+
					        	'<a class="qr" href="javaScript:modefy()" class="tablelink" >确认</a>'+
					        '</td>'+
					    '<tr>';
    	$("#dateTab").append(rowTemplate);
    	$("#addbut").remove();
	})
	
	$(".del").click(function(){
		$(this).parent().parent().remove();
	})
})

		