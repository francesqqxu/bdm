/**
 * 
 */

$(function(){
	
	console.log("broadbandFileUploading");

	$('#broadband_fileUpload').datagrid({
	     url : 'broadband/get_broadbands.html',
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
	     toolbar : "#fileUpload_tool",
	     pagePosition: 'bottom',
	     queryParams: {
	    	 city : "",
	    	 feeCollection : "",
	    	 director : ""
	     },
	     
	     frozenColumns :[[
	     {
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
	    	 field : 'bandwdith',
	    	 title : '接入带宽',
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
	    	 title : '经办',
	    	 width : 20
	     },
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
	     {
	    	 field : 'useRatio',
	    	 title : '利用率',
	    	 width : 20
	     },
	     {
	    	 field : 'networkUsage',
	    	 title : '网络使用',
	    	 width : 20
	     }
	    
	     
	     ]],
	     
	     onLoadSuccess: function(data){
	    	 console.log("abcd");
	    	 console.log(data);
	     },
	     onLoadError: function( ) {
	    	$.messager.show({
	    		title : '出错信息',
	    		msg  : '加载数据出错,请联系管理人员'
	    	}); 
	    	 
	     }
	});
	

	
	$('#broadband_fileUpload').datagrid('getPager').pagination({
		layout:['list','sep', 'first', 'prev','sep', 'manual','sep','next','last','sep','refresh']
	});
			
	$('#fileUpload').dialog({
		width: 350,
		title: '上传宽带信息',
		iconCls : 'icon-edit-new',
		model: 'true',
		closed : 'true',
		buttons: [{
			text: '提交',
			iconCls : 'icon-edit-new',
			handler : function() {
				
					$.ajaxFileUpload({
						url : 'broadband/fileUploading.html',
						secureuri : false,
						fileElementId : 'fileToUpload',
						datatype : 'json',
												
						success:  function(data , status){
							//alert(data);
							if(data.success == true){
								$('#fileUpload').dialog('close');
								$('#broadband_fileUpload').datagrid('reload');
								$.messager.show({
									title  : '提示',
									msg    : '宽带信息导入成功！'
								});
								
							}
							else if(data.success == false){
								$('#fileUpload').dialog('close');
								$.messager.show({
									title : '出错信息',
									msg :   data.msg
								})
							}
						},
					    error : function(data){
							//alert(data);
							$.messager.progress('close');
							$.messager.alert('错误,请检查数据');
					    }
						
					});
				}
			},
	
		{
			text : '取消',
            iconCls : 'icon-redo',
			handler : function() {
				$('#fileUpload').dialog('close');
			}
		}
		]
	});
	
	
	$('#fileUpload_tool_import').click(function(){
		$('#fileUpload').dialog('open');
	});
	
	$('#fileUpload_tool_remove').click(function(){
		$.ajax({
		    type : 'POST',
		    url : 'broadband/delAll.html',
		    scriptCharset : 'utf-8',
		    datatype : 'json',
		     
		    beforeSend : function() {
		    	$('#broadband_fileUpload').datagrid('loading');
		    },
		    success : function(data, status){
		    	console.log(data);
		    	var strData = eval('('+data+')');
		    	if(strData.success == true){
		    		$('#broadband_fileUpload').datagrid('loaded');
		    		$('#broadband_fileUpload').datagrid('reload');
		    		$('#broadband_fileUpload').datagrid('unselectAll');
		    		$.messager.show({
		    			title : '提交',
		    			msg : '宽带信息被删除！'
		    		});
		    	}
		    	else{
		    		$('#broadband_fileUpload').datagrid('loaded'); 
		    		$.messager.show({
		    			title : '出错信息',
		    			msg : strData.msg
		    		});
		    	}
		    },
		    error : function(data){
		    				    
				//alert(data.status);
				$.messager.progress('close');
				$.messager.show({
					title : '错误信息',
					msg : data.status  + " " +  data.statusText
				})
				$('#broadband_fileUpload').datagrid('loaded');
				$('#broadband_fileUpload').datagrid('reload');
			}
		});
	});
	
	$('#fileUpload_tool_clear').click(function(){
		$("input").val("");
		$('#downloadTemplate').val('下载模板');
	});


	$('#fileUpload_tool_search').click(function(){
		//alert($.trim($('input[name="fileUpload_search_city"]').val()));
		$('#broadband_fileUpload').datagrid('load',{
			city : $.trim($('input[name="fileUpload_search_city"]').val()),
			feeCollection : $.trim($('input[name="fileUpload_search_feeCollection"]').val()),
			director : $.trim($('input[name="fileUpload_search_director"]').val())
		});
	});

});




/*
fileUpload_tool = {
		
		import : function() {
			
			$('#fileUpload').dialog('open');
			
		},

		search : function(){
			alert($.trim($('input[name="fileUpload_search_city"]').val()));
			$('#broadband_fileUpload').datagrid('load',{
				city : $.trim($('input[name="fileUpload_search_city"]').val()),
				feeCollection : $.trim($('input[name="fileUpload_search_feeCollection"]').val()),
				director : $.trim($('input[name="fileUpload_search_director"]').val()),
			});
		},
		
		clear :  function() {
			
			$("input").val("");
			//$('input[name="fileUpload_search_city"]').val("");
			
		},
     
		remove : function() {
			 
			
			$.ajax({
			    type : 'POST',
			    url : 'broadband/delAll.html',
			     
			    beforeSend : function() {
			    	$('#broadband_fileUpload').datagrid('loading');
			    },
			    success : function(data){
			    	var strData = eval('('+data+')');
			    	if(strData.success){
			    		$('#broadband_fileUpload').datagrid('loaded');
			    		$('#broadband_fileUpload').datagrid('reload');
			    		$('#broadband_fileUpload').datagrid('unselectAll');
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
					$('#broadband_fileUpload').datagrid('loaded');
					$('#broadband_fileUpload').datagrid('reload');
				},
			});
				
		},
		

}
*/

