/**
 * 
 */

function  feeConfirm_edit_loadEdit(rowData) {
	  
	  $('#feeConfirm_edit').form('load',{
		  feeConfirm_edit_id : rowData.id,
		  feeConfirm_edit_broadbandId : rowData.broadbandId,
		  feeConfirm_edit_feeDate  : rowData.feeDate,
		  feeConfirm_edit_fee: rowData.fee,
		  feeConfirm_edit_valueAddTax: rowData.valueAddTax,
		  feeConfirm_edit_agent : rowData.agent,
		  feeConfirm_edit_director : rowData.director,
		  feeConfirm_edit_changedFee : rowData.changedFee,
		  feeConfirm_edit_reason : rowData.reason,
		  feeConfirm_edit_useRatio : rowData.useRatio
	  }).dialog('open');
	
}



$(function() {
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
	
	$('#date_confirm').datebox({
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
				$('#date_confirm').val(date);
			}
			
	});

	var curr_time  = new Date();
	console.log(curr_time);
 
	$('#date_confirm').datebox("setValue" ,curr_time);
    
	curr_time = $('#date_confirm').datebox('getValue');
	console.log(curr_time);
	
	$('#feeConfirming_tool_select').click(function(){
		console.log($('#date_confirm').val());
	    console.log($('#date_confirm').datebox('getValue'));
	    var curr_time = $('#date_confirm').datebox('getValue');
	    var ss = curr_time.split('-');
	    console.log(ss[0]+ss[1]);
		
		$('#feeConfirming').datagrid({
		     url : 'feeConfirm/get_feeConfirms.html',
		     fit : false,
		     fitColumns : true,
		     striped : true,
		     rownumbers :  true,
		     border : false,
		     pagination : true,
		     pageSize : 20,
		     pageList : [10,20,30,40,50],
		     pageNumber : 1,
		     sortName : 'feeDate',
		     sortOrder : 'desc',
		     toolbar : "#feeConfirming_tool",
		    
		     queryParams: {
		    	 feeDate :  ss[0] + ss[1]
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
			    	 title : '确认标识',
			    	 width : 20
			     }
			     ]],
			     onDblClickRow : function (rowIndex, rowData){
			    	 feeConfirm_edit_loadEdit(rowData);
			    	 
			     }, 
			     onLoadError : function() {
			    	 
			    	 $.messager.show({
			    		 title : '出错信息',
			    		 msg   : '加载宽带费用确认信息出错， 请联系管理人员！'
			    	 })
			    	 
			     }
				
		});
	
	});
	
	
	$('input[name="feeConfirm_edit_changedFee"]').numberbox({
		min : 0, 
		precision : 2 
	})
	
	$('input[name="feeConfirm_edit_useRatio"]').numberbox({
		min : 0,
		precision : 2
	})
	
	$('#feeConfirm_edit').dialog({
		width: 450,
		title: '修改宽带费用确认信息',
		iconCls : 'icon-edit-new',
		model: 'true',
		closed : 'true',
		buttons: [{
			text: '提交',
			iconCls : 'icon-edit-new',
			handler : function() {
				    console.log("id " + $('input[name="feeConfirm_edit_id"]').val());
					$.ajax({
						url : 'feeConfirm/edit.html',
						type : 'POST',
						datatype : 'json',
						data : {
							id : $('input[name="feeConfirm_edit_id"]').val(),
							broadbandId : $('input[name="feeConfirm_edit_broadbandId"]').val(),
							feeDate : $('input[name="feeConfirm_edit_feeDate"]').val(),
							fee : $('input[name="feeConfirm_edit_fee"]').val(),
							valueAddTax : $('input[name="feeConfirm_edit_valueAddTax"]').val(),
							agent : $('input[name="feeConfirm_edit_agent"]').val(),
							director : $('input[name="feeConfirm_edit_director"]').val(),
							changedFee : $('input[name="feeConfirm_edit_changedFee"]').val(),
							reason : $('input[name="feeConfirm_edit_reason"]').val(),
							useRatio :  $('input[name="feeConfirm_edit_useRatio"]').val()
						},
						beforeSend : function() {
							$.messager.progress({
								text : '正在尝试提交....'
							});
						},
						success:  function(data , response, status){
							$.messager.progress('close');
							var strData = eval('('+data + ')');
							if(strData.success==true){
								$.messager.show({
									title : '提示',
									msg : '修改宽带费用确认信息成功！'
									
								});
								$('#feeConfirm_edit').dialog('close');
								$('#feeConfirming').datagrid('reload');
							}else if (strData.success==false){
						        $.messager.show({
						        	title : '错误信息',
						        	msg   : '修改宽带信息发生错误，请联系管理人员！ 具体错误信息 ： ' + strData.msg
						        });
							}
						}
						
					});
				
			}
		},
		{
			text : '取消',
            iconCls : 'icon-redo',
			handler : function() {
				$('#feeConfirm_edit').dialog('close').form('reset');
			}
		}]
	});
    
	
	$('#feeConfirming_tool_edit').click(function(){
		var rows = $('#feeConfirming').datagrid('getSelections');
		if(rows.length > 1 ){
			$.messager.alert('警告操作','修改记录仅能选择一条记录！','warning');
		}else if (rows.length == 1) {
			feeConfirm_edit_loadEdit(rows[0]);
		}else if (rows.length == 0 ){
			$.messager.alert('警告提交','至少选择一条记录！','Warning！');
		}

	});
	
	$('#feeConfirming_tool_confirm').click(function(){
		var rows = $('#feeConfirming').datagrid('getSelections');
		var curr_time = $('#date_confirm').datebox('getValue');
	    var ss = curr_time.split('-');
		if(rows.length > 0 ){
			
			$.messager.confirm('确定', '确认要确认<strong>'+rows.length + '</strong>条记录吗？'  , function(flag) {
				if(flag){
					var ids = [];
					for(var i=0; i<rows.length; i++){
						ids.push(rows[i].id);
					}
//			        alert(ids);
//			        alert(ids.join(','));
					$.ajax({
					    type : 'POST',
					    url : 'feeConfirm/confirm.html',
					    datatype : 'json',
					    data : {
					    	ids :ids.join(','),
					    	feeDate: ss[0] + ss[1]
					    },
					    beforeSend : function() {
					    	$('#feeConfirming').datagrid('loading');
					    },
					    success : function(data){
					    	var strData = eval('('+data+')');
					    	if(strData.success==true){
					    		$('#feeConfirming').datagrid('loaded');
					    		$('#feeConfirming').datagrid('reload');
					    		$('#feeConfirming').datagrid('unselectAll');
					    		$.messager.show({
					    			title : '提交',
					    			msg : '宽带费用信息被确认！'
					    		});
					    	}else if (strData.success==false){
					    		$.messager.progress('close');
					    		$('#feeConfirming').datagrid('loaded');
					    		$.messager.show({
					    			title : '错误信息',
					    			msg   : '宽带费用确认发生错误，请联系管理人员， 错误信息： ' + strData.msg
					    		});
					    	}
					    },
					    error : function(data){
							//alert(data.status);
							$.messager.progress('close');
							$.messager.alert('错误,请检查数据');
							$('#feeConfirming').datagrid('loaded');
							$('#feeConfirming').datagrid('reload');
						}
					});
				}
		
			});
		}else {
			
			$.messager.show({
				title : '提示',
				msg   : '请选择要确认的条目！'
			})
		}

	});
});


/*
feeConfirming_tool =  {
	
	edit : function () {
		var rows = $('#feeConfirming').datagrid('getSelections');
		if(rows.length > 1 ){
			
			$.messager.alert('警告操作','修改记录仅能选择一条记录！','warning');
		}else if (rows.length == 1) {
			
			$.ajax({
				type : 'POST',
				url : 'feeConfirm/get_feeConfirm.html',
				data: {
					id : rows[0].id,
				},
				beforeSend : function() {
					$.messager.progress({
						text : '正在尝试获取数据...',
					});
				},
				success: function(data) {
				  $.messager.progress('close');
				  if(data){
					 
					 var obj = $.parseJSON(data);
					  
					  $('#feeConfirm_edit').form('load',{
						  feeConfirm_edit_id : obj.id,
						  feeConfirm_edit_broadbandId : obj.broadbandId,
						  feeConfirm_edit_feeDate  : obj.feeDate,
						  feeConfirm_edit_fee: obj.fee,
						  feeConfirm_edit_agent : obj.agent,
						  feeConfirm_edit_director : obj.director,
						  feeConfirm_edit_changedFee : obj.changedFee,
						  feeConfirm_edit_reason : obj.reason,
						  feeConfirm_edit_useRatio : obj.useRatio,
						  
					  }).dialog('open');
					  
					  
				  }
				},
				error : function(data) {
					alert(data);
					$.messager.progress('close');
					$.messager.alert('错误,请检查数据');
					
				},
			}); 
		}else if (rows.length == 0 ){
			$.messager.alert('警告提交','至少选择一条记录！','Warning！');
		}
	},
	
	confirm : function() {
		
		var rows = $('#feeConfirming').datagrid('getSelections');
		
		if(rows.length > 0 ){
			
			$.messager.confirm('确定', '确认要确认<strong>'+rows.length + '</strong>条记录吗？'  , function(flag) {
				if(flag){
					var ids = [];
					for(var i=0; i<rows.length; i++){
						ids.push(rows[i].id);
					}
			        alert(ids);
			        alert(ids.join(','));
					$.ajax({
					    type : 'POST',
					    url : 'feeConfirm/confirm.html',
					    data : {
					    	ids :ids.join(','),
					    },
					    beforeSend : function() {
					    	$('#feeConfirming').datagrid('loading');
					    },
					    success : function(data){
					    	var strData = eval('('+data+')');
					    	if(strData.success){
					    		$('#feeConfirming').datagrid('loaded');
					    		$('#feeConfirming').datagrid('reload');
					    		$('#feeConfirming').datagrid('unselectAll');
					    		$.messager.show({
					    			title : '提交',
					    			msg : '宽带费用信息被确认！',
					    			
					    		});
					    	}
					    },
					    error : function(data){
					    				    
							alert(data.status);
							$.messager.progress('close');
							$.messager.alert('错误,请检查数据');
							$('#feeConfirming').datagrid('loaded');
							$('#feeConfirming').datagrid('reload');
						},
					});
				}
		
			});
		}
	},
}
*/
