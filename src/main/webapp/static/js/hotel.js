//开始使用jQuery.js
$(document).ready(function(){
	console.log("Now is use jQuery.");
	
	$('#queryButton').click(function(){
		console.log("queryButton click.");
		var name = $('#name').val();
		console.log("name:"+name);
		$.ajax({
			url:ctx+'/hotel/query',
			dataType:'json',
			type:'post',
			data:{name:name},
			success:function(data){
				console.log("data result:\n"+JSON.stringify(data));
			},
			error:function(XMLHttpResquest, textStatus,errorThrow){
				alert('程序出错了。。。');
			}
		});
		
		
		
		
	});
	
});

//查询方法
function query(){
	
	
}
