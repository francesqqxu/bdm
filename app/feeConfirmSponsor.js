
//@  sourceURL=feeConfirmSponsors.js
$(function(){
	
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	 
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o){
	    	//alert(k);
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    }
	    return fmt;
	};
	
	
//	function myparser(s){
//		if(!s) return new Date();
//		var ss =(s.split('-'));
//		var y = parseInt(ss[0],10);
//		var m = parseInt(ss[1],10);
//		var d = parseInt(ss[2],10);
//		if(!isNaN(y) && !isNaN(m) && !isNaN(d)){
//			return new Date(y, m-1, d);
//		}else {
//			return new Date();
//		}
//	}
	
	
	$('#date_from').datebox({
	   panelWidth : 300,
	    
	   currentText : '今',
	   closeText: '关',
	   disable : true,
	   formatter : function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m) + '-'+(d<10?('0'+d):d);
		},
		onSelect : function(date){
			$('#date_from').val(date);
		}
		
	});
	
	var curr_time  = new Date();
	console.log(curr_time);
 
	$('#date_from').datebox("setValue" ,curr_time);
    
	curr_time = $('#date_from').datebox('getValue');
	console.log(curr_time);
	
	$('#feeConfirmSponsor_tool_sponsor').click(function(){
	    console.log($('#date_from').val());
	    console.log($('#date_from').datebox('getValue'));
	    var curr_time = $('#date_from').datebox('getValue');
	    var ss = curr_time.split('-');
	    console.log(ss[0]+ss[1]);
		$('#feeConfirmSponsor').datagrid({
		     url : 'feeConfirm/get_feeConfirmSponsors.html',
		     fit : false,
		     fitColumns : true,
		     striped : true,
		     rownumbers :  true,
		     border : false,
		     pagination : true,
		     pageSize : 20,
		     pageList : [10,20,30,40,50],
		     pageNumber : 1,
		     sortName : 'director',
		     sortOrder : 'asc',
		     toolbar : "#feeConfirmSponsor_tool",
		    
		     queryParams: {
		    	 
		    	 feeDate : ss[0] + ss[1]
		     },
		     
		     columns :[[
		               
		     {
		    	field  : 'id',
		    	titile : '自动编号',
		    	width : 10,
		    	checkbox : true
		     },
		    
		     {
		    	field : 'broadbandId',
		    	title : '宽带标识',
		    	width : 20
		     },
		     
		     {
		    	 field : 'feeDate',
		    	 title : '年份',
		    	 width : 20
		     },
		    
		     {
		    	 field : 'fee',
		    	 title : '应缴费用',
		    	 width : 20
		     },
		     {
		    	 field : 'valueAddTax',
		    	 title : '营改增',
		    	 width : 20
		     },
		     {
		    	 field : 'agent',
		    	 title : '经办人',
		    	 width : 20
		     },
		     {
		    	 field : 'director',
		    	 title : '负责人',
		    	 width : 20
		     },
		     {
		    	 field : 'changedFee',
		    	 title : '修改后费用',
		    	 width : 20
		     },
		     {
		    	 field : 'reason',
		    	 title : '修改理由',
		    	 width : 20
		     },
		     {
		    	 field : 'useRatio',
		    	 title : '利用率',
		    	 width : 20
		     },
		     {
		    	 field : 'confirmed',
		    	 title : '是否确认',
		    	 width : 20
		     }
		     ]]
			
		});
		$('#feeConfirmSponsor_tool_submit').linkbutton('enable');
		
	})
	
	
	$('#feeConfirmSponsor_tool_submit').click(function(){
		var curr_time = $('#date_from').datebox('getValue');
	    var ss = curr_time.split('-');
		$.ajax({
		    url : 'feeConfirm/submit_feeConfirmSponsors.html',
	        data: {feeDate: ss[0]+ss[1]},
		    type : 'POST',
	        timeout : '100000',
	        datatype : 'json',
		    beforeSend : function() {
		    	$.messager.progress({
					text : '正在尝试提交....'
				});
		    },
		    success : function(data){
		    	var strData = eval('('+data+')');
		    	if(strData.success==true){
		    		$.messager.show({
		    			title : '提交',
		    			msg : '宽带费用确认数据已提交！'
		    		});
		    		$.messager.progress('close');
		    		$('#feeConfirmSponsor_tool_submit').linkbutton('disable');
		    	}
		    	else if (strData.success="false") {
		    		$.messager.show({
		    			title: '错误信息',
		    			msg :  '宽带费用确认数据提交出错，请联系管理人员， 具体错误信息： ' + strData.msg
		    		})
		    		$.messager.progress('close');
		    	}
		    	
		    },
		    error : function(data){
				//alert(data.status);
				$.messager.progress('close');
				$.messager.alert('错误,请检查数据');
				
			}
		});

	});
	
    


	
	$('#feeConfirmSponsor_tool_del').click(function(){
		var curr_time = $('#date_from').datebox('getValue');
	    var ss = curr_time.split('-');
		$.ajax({
		    url : 'feeConfirm/delFeeConfirms.html',
		    data: {feeDate: ss[0]+ss[1]},
	        type : 'POST',
	        timeout : '1000',
	        datatype : 'json',
		    beforeSend : function() {
		    	$.messager.progress({
					text : '正在尝试提交....'
				});
		    },
		    success : function(data){
		    	var strData = eval('('+data+')');
		    	if(strData.success==true){
		    		$.messager.show({
		    			title : '提交',
		    			msg : '宽带费用确认数据已删除！'
		    		});
		    		$.messager.progress('close');
		    		$('#feeConfirmSponsor_tool_submit').linkbutton('disable'); 
		    		$('#feeConfirmSponsor').datagrid('loadData',{ 
		    			total: 0,
		    			rows: []
		    		});
		    	}
		    	else if(strData.success=="false") {
		    		$.messager.show({
		    			title: '错误信息',
		    			msg :  '宽带费用确认数据提交出错，请联系管理人员， 具体错误信息： ' + strData.msg
		    		})
		    		$.messager.progress('close');
		    	}
		    },
		    error : function(data){
				//alert(data.status);
				$.messager.progress('close');
				$.messager.alert('错误,请检查数据');
				
			}
		});

	});
	
    
});

/*
feeConfirmSponsor_tool = {
		
		submit : function() {
			
			$.ajax({
			    url : 'feeConfirm/addFeeConfirms.html',
                type : 'POST',
                timeout : '1000',
			    beforeSend : function() {
			    	$.messager.progress({
						text : '正在尝试提交....',
					});
			    },
			    success : function(data){
			    	var strData = eval('('+data+')');
			    	if(strData.success){
			    		
			    		$.messager.show({
			    			title : '提交',
			    			msg : '宽带费用确认发起结束！',
			    			
			    		});
			    		
			    		
			    		
			    		$('#feeConfirm_submit').linkbutton('disable');
			    	}
			    	$.messager.progress('close');
			    },
			    error : function(data){
					alert(data.status);
					$.messager.progress('close');
					$.messager.alert('错误,请检查数据');
					
				},
			});
		},
		
		
        del : function() {
	
			$.ajax({
			    url : 'feeConfirm/del.html',
		        type : 'POST',
		        timeout : '1000',
			    beforeSend : function() {
			    	$.messager.progress({
						text : '正在尝试提交....',
					});
			    },
			    success : function(data){
			    	var strData = eval('('+data+')');
			    	if(strData.success){
			    
			    		$.messager.show({
			    			title : '提交',
			    			msg : '宽带费用确认数据已重新加载！',
			    			
			    		});
			    		
			    		$.messager.progress('close');
			    		
			    		$('#feeConfirmSponsor').datagrid('load',{
			    			 feeDate :  new Date().Format("yyyyMM"),
			    		});
			    		
			    		
			    	}
			    	
			    },
			    error : function(data){
					alert(data.status);
					$.messager.progress('close');
					$.messager.alert('错误,请检查数据');
					
				},
			});
    	}

};
*/