//开始使用jQuery.js
$(document).ready(function(){
	console.log("Now is use jQuery.");
	//initDatePicker();
	
	
	$('#queryButton').click(function(){
		console.log("queryButton click.");
		var name = $('#name').val();
		var idCard = $('#idCard').val();
		var lkNoroom = $('#lkNoroom').val();
		var lkLtime = $('#lkLtime').val();
		var lkLtimeBefore = $('#lkLtimeBefore').val();
		var lkEtime = $('#lkEtime').val();
		var lkEtimeAfter = $('#lkEtimeAfter').val();
		console.log("name:"+name+",idCard:"+idCard+",lkNoroom:"+lkNoroom+"lkLtimeBefore:"+lkLtimeBefore+",lkLtime:"+lkLtime+",lkEtime:"+lkEtime+",lkEtimeAfter:"+lkEtimeAfter);
		
		if(name=='' && idCard=='' && lkNoroom==''&& lkLtime==''&& lkEtime==''){
			alert('请输入需要检索的条件。');
			return false;
		}
		$('#tableResult').css('display','none');
		$('#tbody,#costTime').empty();
		$.ajax({
			url:ctx+'/hotel/query',
			dataType:'json',
			type:'post',
			data:{name:name,idCard:idCard,lkNoroom:lkNoroom,lkLtimeBefore:lkLtimeBefore,lkLtime:lkLtime,lkEtime:lkEtime,lkEtimeAfter:lkEtimeAfter},
			success:function(data){
				var tbody = '';
				if(data && data.hotels.length>0){
					$('#costTime').append('这次查询花费了'+data.costTime+'毫秒，共'+data.hotels.length+'条！'); 
					//console.log("data result:\n"+JSON.stringify(data));
					$.each(data.hotels,function(i,hotel){
						tbody += '<tr id=\"'+hotel.id+'\">'
									+'<td>'+(i+1)+'</td>'
									+'<td>'+hotel.lkName+'</td>'
									+'<td>'+translateToMF(hotel.lkSex)+'</td>'
									+'<td>'+hotel.lkIdCode+'</td>'
									+'<td>'+hotel.lkAddress+'</td>'
									+'<td>'+hotel.lgHName+'</td>'
									+'<td>'+hotel.lkNoroom+'</td>'
									+'<td>'+(new Date(hotel.lkLtime)).Format("yyyy-MM-dd hh:mm:ss")+'</td>'
									+'<td>'+(new Date(hotel.lkEtime)).Format("yyyy-MM-dd hh:mm:ss")+'</td>'
									+'<td><span class="glyphicon glyphicon-user" aria-hidden="true" onclick="partner(this)"></span></td>'
									+'</tr>';
					});
					$('#tableResult').css('display','block');
					$('#tbody').append(tbody);
					
				}else{
					alert('无检索结果，请修改检索文字。');
				}
				
			},
			error:function(XMLHttpResquest, textStatus,errorThrow){
				alert('程序出错了。。。');
			}
		});
	});
	
});

//====================util methods ===========================
//性别处理为男女
function translateToMF(sexFlag){
	if(sexFlag == '1'){
		return "男";
	}else{
		return "女";
	}
}

function initDatePicker(){
	
	$('#lkLtime,#lkEtime').click(function(){
		return WdatePicker({dateFmt:'yyyy-MM-dd hh:mm:ss',el:this});
	});
	
	
}



//show the sleep Partner.
function partner(td){
	var parentTr = $(td).parent().parent();
	var hotelId = parentTr.attr("id");//获取到改行隐藏的hotelId
	console.log('hotelId:'+hotelId); //{'id':'167'}
	//var idData = JSON.parse(data);
	//console.log("id:"+idData.id)
	
	$.ajax({
		url:ctx+'/hotel/queryPartner',
		dataType:'json',
		type:'post',
		data:{id:hotelId},
		success:function(data){
			if(data.dateError){
				alert(data.message);
			}else{
				var trHtml = '';
				if(data.hotels.length>0){
					$.each(data.hotels,function(i,hotel){
						trHtml+='<tr id=\"delete_'+hotel.id+'\">'
						+'<td>---</td>'
						+'<td>'+hotel.lkName+'</td>'
						+'<td>'+translateToMF(hotel.lkSex)+'</td>'
						+'<td>'+hotel.lkIdCode+'</td>'
						+'<td>'+hotel.lkAddress+'</td>'
						+'<td>'+hotel.lgHName+'</td>'
						+'<td>'+hotel.lkNoroom+'</td>'
						+'<td>'+(new Date(hotel.lkLtime)).Format("yyyy-MM-dd hh:mm:ss")+'</td>'
						+'<td>'+(new Date(hotel.lkEtime)).Format("yyyy-MM-dd hh:mm:ss")+'</td>'
						+'<td><span class="glyphicon glyphicon-remove" aria-hidden="true" onclick="removeTr()"></span></td>'
						+'</tr>';
					});
					console.log("trHtml:"+trHtml);
					parentTr.after(trHtml);
				}else{
					alert('暂无小伙伴信息');
				}
			}
		}
	});
}

function removeTr(){
	console.log('enter remove TR.xxx');
	var trs = $('#tbody tr');
	var firstMeeting = false;
	$.each(trs,function(i,tr){
		var trId = $(tr).attr("id");
		console.log('trId:'+trId);
		if(trId.indexOf('delete')!=-1){
			console.log('should be removed.');
			$("#"+trId).remove();
		}
	});
	//$('#tbody tr#delete_*').empty();
}

