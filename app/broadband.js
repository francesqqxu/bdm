/**
 * 
 */


$(function() { 
	 console.log("broadband.js");
	 $.extend($.fn.datebox.defaults.formatter = function(date) {
	    	var y=date.getFullYear();
	    	var m = date.getMonth() +1;
	    	var d = date.getDate();
	    	
	    	if( m < 10 ){
	    		m = '0'+m;
	       	}
	    	if(d < 10 ){
	    		d = '0'+d;
	    	}
	    	return  y+'-'+m+'-'+d;
	    });
	 
	 $.extend($.fn.validatebox.defaults.rules,{
		 
		 minLength : {
			 validator : function(value, param){
				 value = $.trim(value);
				 return value.length >=param[0];
			 },
			 message: '最少输入 {0} 个字符。'
		 },
		 
		 maxDecimalDigits : {
			 validator : function(value, param){
				 //console.log(value);
				 var  dot = value.indexOf(".");
				 if( dot != -1){
					 var digitCnt = value.substring(dot+1, value.length);
					 return digitCnt.length >=param[0];
				 }
			 },
			 message : '小数只保留{0}位。'
			 
		 },
		 
	     checkSettlementCycle: {
	    	 
	    	 validator : function(value, param){//参数value是当前文本框的值，
	    		 console.log($(param[0]).val());
	    		 console.log(value);
	    		 if($(param[0]).val() != 1) {
	    			 return value > 0;
	    		 }
	    		 else {return true;}
	    		 
	    	 },	
	    	 message : '结算周期不是1的情况下， 支付月需设置， 值为1-12月中任意数！' 
	    	  
	     }
		 
		 
	 });
	 
	 
	 function broadband_edit_loadEdit(rowData){

		  $('#broadband_edit').form('load',{
			  broadband_edit_id : rowData.id,
			  broadband_edit_broadbandId : rowData.broadbandId,
			  broadband_edit_stopDate : rowData.stopDate,
			  broadband_edit_city: rowData.city,
			  broadband_edit_status : rowData.status,
			  broadband_edit_address : rowData.address,
			  broadband_edit_lineType : rowData.lineType,
			  broadband_edit_linkAddress : rowData.linkAddress,
			  broadband_edit_operator : rowData.operator,
			  broadband_edit_accessWay : rowData.accessWay,
			  broadband_edit_bandwidth : rowData.bandwidth,
			  broadband_edit_fee : rowData.fee,
			  broadband_edit_valueAddTax : rowData.valueAddTax,
			  //broadband_edit_agent : rowData.agent,
			  broadband_edit_director : rowData.director,
			  broadband_edit_paymentMethod : rowData.paymentMethod,
			  broadband_edit_settlementCycle : rowData.settlementCycle,
			  broadband_edit_paymentMonth : rowData.paymentMonth,
			  //broadband_edit_useRatio : rowData.useRatio,
			  //broadband_edit_networkUsage : rowData.networkUsage
			  broadband_edit_dueDate : rowData.dueDate,
			  broadband_edit_lineUsage : rowData.lineUsage,
			  broadband_edit_ipAddressNum : rowData.ipAddressNum,
			  broadband_edit_ipAddress : rowData.ipAddress,
			  broadband_edit_userDept : rowData.userDept,
		  }).dialog('open');
		  
		  $('#broadband_edit_payOrganization').combobox({
			  url : 'org/get_org.html',
			  required : 'true',
			 
			  onLoadSuccess : function() {
				  
				  var orgData = $(this).combobox('getData');
				  //alert(orgData.length);
				  
				  var org = $.trim(rowData.payOrganization);
	      			
				  if(orgData.length > 0 ){
					 
					  for(var i =0; i<orgData.length; i++){
						 
						  if($.trim(orgData[i].name) == org){
							  $('#broadband_edit_payOrganization').combobox('setText',orgData[i].name);
						  }
						  
					  }
	                  
				  }
			  },
			  onLoadError : function() {
				  $.messager.show({
					title : '错误信息',
					msg   : '加载机构信息出错， 请联系系统管理人员！'
				  });
			  }
		  }); 
		  
		  $('#broadband_edit_feeCollection').combobox({
			  url : 'dept/get_dept.html',
			  required : 'true',
			  
			  onLoadSuccess : function() {
				  
				  var deptData = $(this).combobox('getData');
				  //alert(deptData.length);
				  
				  var dept = $.trim(rowData.feeCollection);
	      			
				  if(deptData.length > 0 ){
					 
					  for(var i =0; i<deptData.length; i++){
						 
						  if($.trim(deptData[i].dno) == dept){
							  $('#broadband_edit_feeCollection').combobox('setText',deptData[i].dno);
						  }
						  
					  }
	                  
				  }
			  },
			  onLoadError : function() {
				  $.messager.show({
					title : '错误信息',
					msg   : '加载费用归集信息出错， 请联系系统管理人员！'
				  });
			  }
		  }); 
	}
	 
	
	$('#broadband').datagrid({
	     url : 'broadband/get_broadbands_byUser.html',
	    
	     fit : false,
	     fitColumns : true,
	     striped : true,
	     scrollbarSize : 2,
	     rownumbers :  true,
	     border : false,
	     pagination : true,
	     pageSize : 20,
	     pageList : [10,20,30,40,50],
	     pageNumber : 1,
	     sortName : 'broadbandId',
	     sortOrder : 'desc',
	     toolbar : "#broadband_tool",
	     pagePosition : 'bottom',
	     queryParams: {
	    	 city : "",
	    	 feeCollection : ""
	     },
	     
	     frozenColumns :[[{
	    	field  : 'id',
	    	titile : '自动编号',
	    	width : 10,
	    	checkbox : true
	     }
	    	 
	     ]],
	     
	     columns :[[
	     {
	    	field : 'broadbandId',
	    	title : '宽带标识',
	    	width : 20
	     },
	     {
		    	field : 'stopDate',
		    	title : '停用年月',
		    	width : 20
		 },
	     {
	    	 field : 'city',
	    	 title : '城市',
	    	 width : 20
	     },
	     {
	    	 field : 'status',
	    	 title : '状态',
	    	 width : 20
	     },
	     {
	    	 field : 'payOrganization',
	    	 title : '缴费单位',
	    	 width : 20
	     },
	     {
	    	 field : 'address',
	    	 title : '场地地址',
	    	 width : 20
	     },
	     {
	    	 field : 'lineType',
	    	 title : '线路类型',
	    	 width : 20
	     },
	     {
	    	 field : 'linkAddress',
	    	 title : '链接地址',
	    	 width : 20
	     },
	     {
	    	 field : 'operator',
	    	 title : '运营商',
	    	 width : 20
	     },
	     {
	    	 field : 'accessWay',
	    	 title : '接入方式',
	    	 width : 20
	     },
	     {
	    	 field : 'bandwidth',
	    	 title : '接入带宽',
	    	 width : 20
	     },
	     {
	    	 field : 'fee',
	    	 title : '应缴费用',
	    	 width : 20
	     },
	     {
	    	 field : "valueAddTax",
	    	 title : '营改增',
	    	 width : 20
	     },
	     
//	     {
//	    	 field : 'agent',
//	    	 title : '经办',
//	    	 width : 20
//	     },
	     {
	    	 field : 'director',
	    	 title : '结算负责',
	    	 width : 20
	     },
	     {
	    	 field : 'paymentMethod',
	    	 title : '付费方式',
	    	 width : 20
	     },
	     {
	    	 field : 'settlementCycle',
	    	 title : '结算周期',
	    	 width : 20
	     },
	     {
	    	 field : 'paymentMonth',
	    	 title : '支付月',
	    	 width : 20
	     },
	     {
	    	 field : 'feeCollection',
	    	 title : '费用归集',
	    	 width : 20
	     },
//	     {
//	    	 field : 'useRatio',
//	    	 title : '利用率',
//	    	 width : 20
//	     },
//	     {
//	    	 field : 'networkUsage',
//	    	 title : '网络使用',
//	    	 width : 20
//	     }
	     {
	    	 field : 'dueDate',
	    	 title : '到期日期',
	    	 width : 20
	     },
	     {
	    	 field : 'lineUsage',
	    	 title : '线路用途',
	    	 width : 20
	     },
	     {
	    	 field : 'ipAddressNum',
	    	 title : 'IP地址数量',
	    	 width : 20
	     },
	     {
	    	 field : 'ipAddress',
	    	 title : 'IP地址',
	    	 width : 20
	     },
	     {
	    	 field : 'userDept',
	    	 title : '使用部门',
	    	 width : 20
	     }
	     ]],
	     onDblClickRow : function (rowIndex, rowData){
	    	 broadband_edit_loadEdit(rowData);
	    	 
	     }, 
	     onLoadError: function() {
	    	 $.messager.show({
	    		 title : '提示',
	    		 msg   : '加载宽带信息出错， 请联系系统管理人员！'
	    	 })
	     }
	});
	
	$('#broadband_add').dialog({
		width : 850,
		title : '新增宽带信息',
		iconCls : 'icon-user-add',
		model: true,
		closed : true,
		
		buttons : [{		           
			text : '提交',
			iconCls : 'icon-add-new',
			handler :  function() {
				if($('#broadband_add').form('validate')){
					console.log($('#broadband_add_payOrganization').combobox('getText'));
					console.log($('#broadband_add_settlementCycle').val());
					console.log($('#broadband_add_ipAddressNum').val());
					
					$.ajax({
						url : 'broadband/add.html',
						type : 'POST',
						timeout : '100000',
						dataType : 'json',
						data : {
							broadbandId : $.trim($('#broadband_add_broadbandId').val()),
							
							stopDate : $.trim($('input[name="broadband_add_stopDate"]').val()),
							
							city : $.trim($('#broadband_add_city').val()),
							
							status : $.trim($('#broadband_add_status').val()),
							
							payOrganization : $('#broadband_add_payOrganization').combobox('getText'),
							
							address : $.trim($('#broadband_add_address').val()),
							
							lineType : $.trim($('#broadband_add_lineType').val()),
							
							linkAddress : $.trim($('#broadband_add_linkAddress').val()),
							
							operator : $.trim($('#broadband_add_operator').val()),
							
							accessWay : $.trim($('#broadband_add_accessWay').val()),
							
							bandwidth : $.trim($('#broadband_add_bandwidth').val()),
							
							fee : $.trim($('#broadband_add_fee').val()),
							
							valueAddTax : $.trim($('#broadband_add_valueAddTax').val()),
							
							//agent : $.trim($('input[name="broadband_add_agent"]').val()),
							
							director : $.trim($('#broadband_add_director').val()),
							
							paymentMethod : $.trim($('#broadband_add_paymentMethod').val()),
							
							settlementCycle : $.trim($('#broadband_add_settlementCycle').val()),
							
							paymentMonth : $.trim($('#broadband_add_paymentMonth').val()),
							
						
							feeCollection :  $('#broadband_add_feeCollection').combobox('getText'),
						
							
							//useRatio : $.trim($('input[name="broadband_add_useRatio"]').val()),
							
							//networkUsage : $.trim($('input[name="broadband_add_networkUsage"]').val())
							dueDate  : $.trim($('input[name="broadband_add_dueDate"]').val()),
							
							lineUsage : $.trim( $('#broadband_add_lineUsage').val()),
							
							ipAddressNum : $.trim($('#broadband_add_ipAddressNum').val()),
							
							ipAddress    : $.trim($('#broadband_add_ipAddress').val()),
							
							userDept  : $.trim( $('#broadband_add_userDept').val()),
						    
						},
						
						beforeSend : function() {
							$.messager.progress({
								text : '正在尝试提交....'
							});
							
						},
						
						success : function(data , response, status){
							$.messager.progress('close');
						    //var strData = eval('(' + data + ')');
							if(data.success==true){
								$.messager.show({
									title : '提示',
									msg : '宽带信息添加成功！',
									timeout : 100000,
								    showType : 'slide'
								});
								//$('#broadband_add').dialog('close').form('reset');
								$('#broadband_add').dialog('close');
								$('#broadband').datagrid('reload');
								
							}else {
								$.messager.show({
									title : '出错信息',
									msg   : '宽带信息添加失败， 请联系系统管理人员！ 具体错误信息： ' + data.msg
								})
							}
						},
						
						error : function(data){
							alert(data);
							$.messager.progress('close');
							$.messager.alert('错误,请检查数据');
						}
					});
				}
			}
		},
		
		{
			   text : '取消',
			   iconCls : 'icon-redo',
			   iconAlign : 'left',
			   handler : function() {
				  $('#broadband_add').dialog('close').form('reset');  
			   }
			   
		}
		]
		
	});
	
	$('#broadband_add_stopDate').datebox({
		
	});
	
	$('#broadband_add_fee').numberbox({
		min:0,
		precision: 2,
		required:  true
	});
	
	$('#broadband_add_valueAddTax').numberbox({
		min: 0,
		precision : 2,
		validType: "maxDecimalDigits[2]"
		//message : '小数位只保留2位'
		
	})
	
    $('#broadband_add_settlementCycle').numberbox({
		min:1,
		max:12,
		required : true,
		missingMessage : '请输入宽带付款周期'
	});
	
    $('#broadband_add_paymentMonth').numberbox({
		min:1,
		max:12,
		value : 1,
		required : true,
		validType: 'checkSettlementCycle[$("#broadband_add_settlementCycle")]'
	});
    
//	$('input[name="broadband_add_useRatio"]').numberbox({
//		min: 0,
//		precision : 2
//	});
//	
    
	$('#broadband_add_broadbandId').validatebox({
		required : true
		//validType: "minLength[5]"
		 
	});
	
	$('#broadband_add_city').validatebox({
		required : true,
		missingMessage : '请输入宽带所属城市'
	});
	
	$('#broadband_add_dueDate').datebox({
		
	});
	
	$('#broadband_add_ipAddressNum').numberbox({
		min:0,
		value: 0
	 
    });
	
	$('#broadband_edit_broadbandId').validatebox({
		required : true,
		missingMessage : '请输入宽带标识'
	});
	
	$('#broadband_edit_city').validatebox({
		required : true,
		missingMessage : '请输入宽带所属城市'
	});
	
	
	$('#broadband_edit_stopDate').datebox({
		
	});
	
	
	$('#broadband_edit_fee').numberbox({
		min:0,
		precision: 2,
		required:  true,
		missingMessage : '请输入宽带费用'
	});
	
	$('#broadband_edit_valueAddTax').numberbox({
		min:0,
		precision:2,
		validType: "maxDecimalDigits[2]"
	})
	
    $('#broadband_edit_settlementCycle').numberbox({
		min:1,
		max:12,
		required : true,
		missingMessage : '请输入宽带付款周期'
	});
	
    $('#broadband_edit_paymentMonth').numberbox({
    	min:1,
    	max:12,
		required : true,
		validType: 'checkSettlementCycle[$("#broadband_edit_settlementCycle")]'
	});
    
//	$('input[name="broadband_edit_useRatio"]').numberbox({
//		min:0,
//		precision: 2
//	});
	
    $('#broadband_edit_dueDate').datebox({
		
	});
	
	$('#broadband_edit_ipAddressNum').numberbox({
		min:0,
		value: 0
	 
    });
	
	$('#broadband_edit_feeCollection').combobox({
		 url: 'dept/get_dept.html',
		
	      valueField : 'id',
	      textField : 'dno',
	      required : true,
	      editable : false,
	      onLoadSuccess : function(){
	    	  
	    	  var val = $(this).combobox("getData");
	    	  for(var item in val[0]){
	    		  if(item=="id") {
	    			  $(this).combobox('select',val[0][item]);
	    		  }
	    	  }
	    	  
	      },
	      onLoadError : function() {
	    	  $.messager.show({
	    		  title : '出错信息',
	    		  msg   : '加载费用归集信息出错， 请联系系统管理人员！'
	    			  
	    	  });
	      }
		
	});
	
	$('#broadband_edit_payOrganization').combobox({
		url: 'org/get_org.html',
	      valueField : 'id',
	      textField : 'name',
	      required : true,
	      editable : false,
	      onLoadSuccess : function(){
	    	  
	    	  var val = $(this).combobox("getData");
	    	  
	    	  for(var item in val[0]){
	    		  if(item=="id") {
	    			  $(this).combobox('select',val[0][item]);
	    		  }
	    	  }
	    	  
	      },
	      onLoadError : function() {
	    	  $.messager.show({
	    		  title : '出错信息',
	    		  msg   : '加载机构信息出错， 请联系系统管理人员！'
	    			  
	    	  });
	      }
	});
	
	
	$('#broadband_edit').dialog({
		width: 850,
		title: '修改宽带信息',
		iconCls : 'icon-edit-new',
		model: 'true',
		closed : 'true',
		buttons: [{
			text: '提交',
			iconCls : 'icon-edit-new',
			handler : function() {
				if($('#broadband_edit').form('validate')){
					//alert($('#broadband_edit_payOrganization').combobox('getText'));
					//alert($('input[name="broadband_edit_id"]').val());
					console.log($.trim( $('#broadband_edit_settlementCycle').val()));
					console.log($.trim($('#broadband_edit_paymentMonth').val()));
					$.ajax({
						url : 'broadband/edit.html',
						type : 'POST',
						timeout: '100000',
						datatype : 'json',
						data : {
							id : $.trim($('#broadband_edit_id').val()),
							broadbandId : $.trim( $('#broadband_edit_broadbandId').val()),
							stopDate :   $.trim( $('input[name="broadband_edit_stopDate"]').val()),
							city : $.trim( $('#broadband_edit_city').val()),
							payOrganization : $('#broadband_edit_payOrganization').combobox('getText'),
							address : $.trim( $('#broadband_edit_address').val()),
							lineType : $.trim($('#broadband_edit_lineType').val()),
							linkAddress : $.trim( $('#broadband_edit_linkAddress').val()),
							operator : $.trim( $('#broadband_edit_operator').val()),
							accessWay : $.trim($('#broadband_edit_accessWay').val()),
							bandwidth : $.trim( $('#broadband_edit_bandwidth').val()),
							fee : $.trim( $('#broadband_edit_fee').val()),
							valueAddTax : $.trim( $('#broadband_edit_valueAddTax').val()),
							//agent : $.trim( $('input[name="broadband_edit_agent"]').val()),
							director : $.trim($('#broadband_edit_director').val()),
							paymentMethod : $.trim( $('#broadband_edit_paymentMethod').val()),
							settlementCycle : $.trim( $('#broadband_edit_settlementCycle').val()),
							paymentMonth : $.trim($('#broadband_edit_paymentMonth').val()),
							feeCollection : $('#broadband_edit_feeCollection').combobox('getText'),
							//useRatio : $.trim( $('input[name="broadband_edit_useRatio"]').val()),
							//networkUsage : $.trim($('input[name="broadband_edit_networkUsage"]').val())
							dueDate  : $.trim($('input[name="broadband_edit_dueDate"]').val()),
							
							lineUsage : $.trim( $('#broadband_edit_lineUsage').val()),
							
							ipAddressNum : $.trim($('#broadband_edit_ipAddressNum').val()),
							
							ipAddress    : $.trim($('#broadband_edit_ipAddress').val()),
							
							userDept  : $.trim( $('#broadband_edit_userDept').val()),
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
									msg : '修改宽带信息成功！'
									
								});
								$('#broadband_edit').dialog('close');
								$('#broadband').datagrid('reload');
							}else {
								$.messager.show({
									title : '出错信息',
									msg   : '修改宽带信息出错， 请联系系统管理人员! 具体出错信息： ' + strData.msg
								});
								$.messager.alert('警告操作','未知操作或无任何修改,请重新提交!', 'warning');
							}
						},
						error : function(date){
							$.messager.progress('close');
							$.messager.alert('严重错误！');
						}
						
					});
				}
			}
		},
		{
			text : '取消',
            iconCls : 'icon-redo',
			handler : function() {
				$('#broadband_edit').dialog('close').form('reset');
		
			}
		}
		]
	});
    
	

	
	$('#broadband_tool_add').click(function(){
		$('#broadband_add').dialog('open').form('reset');
		 
		$('#broadband_add_payOrganization').combobox({
			 url: 'org/get_org.html',
		      valueField : 'id',
		      textField : 'name',
		      required : true,
		      editable : false,
		      onLoadSuccess : function(){
		    	  
		    	  var val = $(this).combobox("getData");
		    	  for(var item in val[0]){
		    		  if(item=="id") {
		    			  $(this).combobox('select',val[0][item]);
		    		  }
		    	  }
		    	  
		      },
		      onLoadError : function() {
		    	  $.messager.show({
		    		  title : '出错信息',
		    		  msg   : '加载机构信息出错， 请联系系统管理人员！'
		    			  
		    	  });
		      }
		});
		
		
		$('#broadband_add_feeCollection').combobox({
			 url: 'dept/get_dept.html',
			
		      valueField : 'id',
		      textField : 'dno',
		      required : true,
		      editable : false,
		      onLoadSuccess : function(){
		    	  
		    	  var val = $(this).combobox("getData");
		    	  for(var item in val[0]){
		    		  if(item=="id") {
		    			  $(this).combobox('select',val[0][item]);
		    		  }
		    	  }
		    	  
		      },
		      onLoadError : function() {
		    	  $.messager.show({
		    		  title : '出错信息',
		    		  msg   : '加载费用归集信息出错， 请联系系统管理人员！'
		    			  
		    	  });
		      }
			
		});
		
		$('input[name="broadbandId_add"]').focus();		
	});
	
	$('#broadband_tool_edit').click(function(){
		
		var rows = $('#broadband').datagrid('getSelections');
		if(rows.length > 1 ){
			$.messager.alert('警告操作','修改记录仅能选择一条记录！','warning');
		}else if (rows.length == 1) {
			broadband_edit_loadEdit(rows[0]);
		}else if (rows.length == 0 ){
			$.messager.alert('警告提交','至少选择一条记录！','Warning！');
		}
	});
	
	$('#broadband_tool_remove').click(function(){
		var rows = $('#broadband').datagrid('getSelections');
		if(rows.length > 0 ){
			$.messager.confirm('确定', '确认要删除<strong>'+rows.length + '</strong>条记录吗？'  , function(flag) {
				if(flag){
					var ids = [];
					for(var i=0; i<rows.length; i++){
						ids.push(rows[i].id);
					}
			        //alert(ids);
			        //alert(ids.join(','));
					$.ajax({
					    type : 'POST',
					    url : 'broadband/del.html',
					    datatype : 'json',
					    data : {
					    	ids :ids.join(',')
					    },
					    beforeSend : function() {
					    	$('#broadband').datagrid('loading');
					    },
					    success : function(data){
					    	var strData = eval('('+data+')');
					    	if(strData.success==true){
					    		$('#broadband').datagrid('loaded');
					    		$('#broadband').datagrid('reload');
					    		$('#broadband').datagrid('unselectAll');
					    		$.messager.show({
					    			title : '提交',
					    			msg : '宽带信息被删除！'
					    			
					    		});
					    		$.messager.progress('close');
					    	}
					    	else if (strData.success=="false"){
					    		$.messager.show({
					    			title : '错误信息',
					    			msg  :  '宽带信息删除发生错误，请联系管理人员! 错误信息： ' + strData.msg
					    		})
					    	}
					    },
					    error : function(data){
					    				    
							//alert(data.status);
							$.messager.progress('close');
							$.messager.alert('错误,请检查数据');
							$('#broadband').datagrid('loaded');
							$('#broadband').datagrid('reload');
						}
					});
				}
			});
		}else {
			$.messager.alert('警告提交','至少选择一条记录！','Warning！');
		}
	});
	
	$('#broadband_tool_reload').click(function(){
		$('#broadband').datagrid('reload');
	});
     	
	$('#broadband_tool_unselect').click(function(){
		$('#broadband').datagrid('unselectAll');
	});
	
	$('#broadband_tool_search').click(function(){
		//alert($.trim($('input[name="search_city"]').val()));
		$('#broadband').datagrid('load',{
			city : $.trim($('input[name="broadband_search_city"]').val()),
			feeCollection : $.trim($('input[name="broadband_search_feeCollection"]').val())
		});
	});
	
	
	
});





/*
broadband_tool  = {
	add : function() {
		$('#broadband_add').dialog('open');
		$('input[name="broadbandId_add"]').focus();		
	},	
	
	edit : function() {
	
		var rows = $('#broadband').datagrid('getSelections');
		if(rows.length > 1 ){
			
			$.messager.alert('警告操作','修改记录仅能选择一条记录！','warning');
		}else if (rows.length == 1) {
			
			$.ajax({
				type : 'POST',
				url : 'broadband/get_broadband.html',
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
					  
					  $('#broadband_edit').form('load',{
						  broadband_edit_id : obj.id,
						  broadband_edit_broadbandId : obj.broadbandId,
						  broadband_edit_stopDate : obj.stopDate,
						  broadband_edit_city: obj.city,
						  broadband_edit_status : obj.status,
						  broadband_edit_address : obj.address,
						  broadband_edit_lineType : obj.lineType,
						  broadband_edit_linkAddress : obj.linkAddress,
						  broadband_edit_operator : obj.operator,
						  broadband_edit_accessWay : obj.accessWay,
						  broadband_edit_bandwidth : obj.bandwidth,
						  broadband_edit_fee : obj.fee,
						  broadband_edit_agent : obj.agent,
						  broadband_edit_director : obj.director,
						  broadband_edit_paymentMethod : obj.paymentMethod,
						  broadband_edit_settlementCycle : obj.settlementCycle,
						  broadband_edit_paymentMonth : obj.paymentMonth,
						  broadband_edit_useRatio : obj.useRatio,
						  broadband_edit_networkUsage : obj.networkUsage,
						   
						  
					  }).dialog('open');
					  
					  
					  $('#broadband_edit_payOrganization').combobox({
						  url : 'org/get_org.html',
						  required : 'true',
						 
						  onLoadSuccess : function() {
							  
							  var orgData = $(this).combobox('getData');
							  alert(orgData.length);
							  
							  var org = obj.payOrganization;
			          			
							  if(orgData.length > 0 ){
								 
								  for(var i =0; i<orgData.length; i++){
									 
									  if(orgData[i].name == org){
										  $('#broadband_edit_payOrganization').combobox('setText',orgData[i].name);
									  }
									  
								  }
                                  
							  }
						  },
					  }); 
					  
					  $('#broadband_edit_feeCollection').combobox({
						  url : 'dept/get_dept.html',
						  required : 'true',
						  
						  onLoadSuccess : function() {
							  
							  var deptData = $(this).combobox('getData');
							  alert(deptData.length);
							  
							  var dept = obj.feeCollection;
			          			
							  if(deptData.length > 0 ){
								 
								  for(var i =0; i<deptData.length; i++){
									 
									  if(deptData[i].dno == dept){
										  $('#broadband_edit_feeCollection').combobox('setText',deptData[i].dno);
									  }
									  
								  }
                                  
							  }
						  },
					  }); 

					  
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
	
	
	remove : function() {
		 
		var rows = $('#broadband').datagrid('getSelections');
		
		if(rows.length > 0 ){
			
			$.messager.confirm('确定', '确认要删除<strong>'+rows.length + '</strong>条记录吗？'  , function(flag) {
				if(flag){
					var ids = [];
					for(var i=0; i<rows.length; i++){
						ids.push(rows[i].id);
					}
			        alert(ids);
			        alert(ids.join(','));
					$.ajax({
					    type : 'POST',
					    url : 'broadband/del.html',
					    data : {
					    	ids :ids.join(','),
					    },
					    beforeSend : function() {
					    	$('#broadband').datagrid('loading');
					    },
					    success : function(data){
					    	var strData = eval('('+data+')');
					    	if(strData.success){
					    		$('#broadband').datagrid('loaded');
					    		$('#broadband').datagrid('reload');
					    		$('#broadband').datagrid('unselectAll');
					    		$.messager.show({
					    			title : '提交',
					    			msg : '宽带信息被删除！',
					    			
					    		});
					    	}
					    },
					    error : function(data){
					    				    
							alert(data.status);
							$.messager.progress('close');
							$.messager.alert('错误,请检查数据');
							$('#broadband').datagrid('loaded');
							$('#broadband').datagrid('reload');
						},
					});
				}
			});
		}
	},
	
	
	reload :function() {
		
		$('#broadband').datagrid('reload');
		
	},
	
	redo : function() {
		
		$('#broadband').datagrid('unselectAll');
	},
	
	search : function(){
		alert($.trim($('input[name="search_city"]').val()));
		$('#broadband').datagrid('load',{
			city : $.trim($('input[name="search_city"]').val()),
			dept : $.trim($('input[name="search_dept"]').val()),
		});
	},
	
	import : function() {
		
		$('#broadband_fileupload').dialog('open');
		
	}
};
*/		






