/**
 * 
 */

var rptInfo = "rptInfo";
var rptTotal = "rptTotal";

$(function(){
	console.log("feeConfirmReport");
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
    
    $('#dateFrom').datebox({
    	panelWidth : 300,
    	panelHeight : 300
    	
    });
    
    $('#dateTo').datebox({
    	panelWidth : 300,
        panelHeight : 300
    })
    		
    $('#dateFrom').datebox('setValue', new Date());
    $('#dateTo').datebox('setValue', new Date());
    
    
    $('#feeConfirmReport_tool_search').click(function(){
    	var rptType; 
 
		rptType = $('input[type="radio"][name="rptType"]:checked').val();
		if($('input[name="dateFrom"]').val() == "" || $('input[name="dateTo"]').val() == "") {
			$.messager.show({ 
				title: '警告',
		        msg:	'请输入报表的岂止日期！'});
			return false;
			
		}
		if(rptType===rptTotal){ 
		$('#feeConfirmReport').datagrid({
		     url : 'feeConfirm/get_feeConfirmReport.html',
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
		     sortOrder : 'desc',
		     toolbar : "#feeConfirmReport_tool",
		    
		     queryParams: {
		    	 dateFrom  :   $('input[name="dateFrom"]').val(),
		    	 dateTo    :   $('input[name="dateTo"]').val(),
		    	 rptType :   rptType
		     },
		     
		     columns :[[
		               
		     {
		    	field  : 'id',
		    	titile : '自动编号',
		    	width : 10,
		    	checkbox : true
		     },
		     
		     {
		    	 field: 'confirmed',
		    	 title: '确认标识',
		    	 width: 20
		    	 
		     },
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
//		     {
//		    	 field : 'linkAddress',
//		    	 title : '链接地址',
//		    	 width : 20
//		     },
		     {
		    	 field : 'operator',
		    	 title : '运营商',
		    	 width : 20
		     },
//		     {
//		    	 field : 'accessWay',
//		    	 title : '接入方式',
//		    	 width : 20
//		     },
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
		     
//		     {
//		    	 field : 'agent',
//		    	 title : '经办',
//		    	 width : 20
//		     },
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
		    	 field : 'changedFee',
		    	 title : '修改后费用',
		    	 width : 20
		     },
		     
		     {
		    	 field : 'afterTaxFee',
		    	 title : '扣税后费用',
		    	 width : 20
		     }
//		     {
//		    	 field : 'reason',
//		    	 title : '修改理由',
//		    	 width : 20
//		     },
//		     {
//		    	 field : 'useRatio',
//		    	 title : '利用率',
//		    	 width : 20
//		     }
		     
		     ]],
		     onLoadError : function() {
		    	$.messager.show({
		    		title : '错误信息',
		    		msg   : '报表查询有错误发生， 请联系管理人员！'
		    	});
		     }
		     
			
		});
		}
		else if (rptType === rptInfo){
			$('#feeConfirmReport').datagrid({
			     url : 'feeConfirm/get_feeConfirmReport.html',
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
			     sortOrder : 'desc',
			     toolbar : "#feeConfirmReport_tool",
			    
			     queryParams: {
			    	 dateFrom  :   $('input[name="dateFrom"]').val(),
			    	 dateTo    :   $('input[name="dateTo"]').val(),
			    	 rptType :   rptType
			     },
			     
			     columns :[[
			               
			     {
			    	field  : 'id',
			    	titile : '自动编号',
			    	width : 10,
			    	checkbox : true
			     },
			     
			     {
			    	 field: 'confirmed',
			    	 title: '确认标识',
			    	 width: 20
			    	 
			     },
			     {
			    	field : 'broadbandId',
			    	title : '宽带标识',
			    	width : 20
			     },
			     
//			     {
//				    	field : 'stopDate',
//				    	title : '停用年月',
//				    	width : 20
//				 },
//			     {
//			    	 field : 'city',
//			    	 title : '城市',
//			    	 width : 20
//			     },
//			     {
//			    	 field : 'status',
//			    	 title : '状态',
//			    	 width : 20
//			     },
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
			     
//			     {
//			    	 field : 'agent',
//			    	 title : '经办',
//			    	 width : 20
//			     },
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
			     }
			    
//			     {
//			    	 field : 'changedFee',
//			    	 title : '修改后费用',
//			    	 width : 20
//			     },
			     
//			     {
//			    	 field : 'afterTaxFee',
//			    	 title : '扣税后费用',
//			    	 width : 20
//			     }
//			     {
//			    	 field : 'reason',
//			    	 title : '修改理由',
//			    	 width : 20
//			     },
//			     {
//			    	 field : 'useRatio',
//			    	 title : '利用率',
//			    	 width : 20
//			     }
			     
			     ]],
			     onLoadError : function() {
			    	$.messager.show({
			    		title : '错误信息',
			    		msg   : '报表查询有错误发生， 请联系管理人员！'
			    	});
			     }
			     
				
			});
			
			
		}
		 
    });
    
    $('#feeConfirmReport_tool_check').click(function(){
    	$.ajax({
		    type : 'POST',
		    url : 'feeConfirm/checkFeeConfirmReport.html',
		    data : {
		    	 dateFrom  :   $('input[name="dateFrom"]').val(),
		    	 dateTo    :   $('input[name="dateTo"]').val()
		    },
		    
		    beforeSend : function() {
		    	 $.messager.progress({
		    		 text : '正在下载数据....'
		    	 });
		    },
		    success : function(data, response, status){
	    		$.messager.progress('close');
                    var strData = eval('(' + data + ')');
		    	    if(strData.success){
		    	    	$.messager.show({
		    	    		title : '确认',
		    	    		msg : '宽带费用已确认！'
		    			
		    	    	});
		    	    }else {
		    	    	$.messager.show({
		    	    		title : '确认',
		    	    		msg  : '宽带费用有未确认条目！'
		    	    	});
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
feeConfirmReport_tool = {
	
	
	search : function() {
		var confirmed; 
		if($('#rptCheck').is(':checked')){
			confirmed  = 1;
		}else {
			confirmed = 0;
		}
		
		$('#feeConfirmReport').datagrid({
		     url : 'feeConfirm/get_feeConfirmReport.html',
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
		     toolbar : "#feeConfirmReport_tool",
		    
		     queryParams: {
		    	 dateFrom  :   $('input[name="dateFrom"]').val(),
		    	 dateTo    :   $('input[name="dateTo"]').val(),
		    	 confirmed :   confirmed,
		     },
		     
		     columns :[[
		               
		     {
		    	field  : 'id',
		    	titile : '自动编号',
		    	width : 10,
		    	checkbox : true,
		     },
		     
		     {
		    	field : 'broadbandId',
		    	title : '宽带标识',
		    	width : 20,
		     },
		     
		     {
		    	 field : 'feeDate',
		    	 title : '日期',
		    	 width : 20,
		     },
		     {
		    	 field : 'fee',
		    	 title : '应缴费用',
		    	 width : 20,
		     },
		     {
		    	 field : 'agent',
		    	 title : '经办人',
		    	 width : 20,
		     },
		     {
		    	 field : 'director',
		    	 title : '负责人',
		    	 width : 20,
		     },
		     {
		    	 field : 'changedFee',
		    	 title : '修改后费用',
		    	 width : 20,
		     },
		     {
		    	 field : 'reason',
		    	 title : '修改理由',
		    	 width : 20,
		     },
		     {
		    	 field : 'useRatio',
		    	 title : '利用率',
		    	 width : 20,
		     },
		     
		     ]],
			
		});

		
		
		
	},
	
	
	check : function() {
		
		$.ajax({
		    type : 'POST',
		    url : 'feeConfirm/checkFeeConfirmReport.html',
		    data : {
		    	 dateFrom  :   $('input[name="dateFrom"]').val(),
		    	 dateTo    :   $('input[name="dateTo"]').val(),
		    },
		    
		    beforeSend : function() {
		    	 $.messager.progress({
		    		 text : '正在下载数据....',
		    	 });
		    },
		    success : function(data, response, status){
	    		$.messager.progress('close');
                    var strData = eval('(' + data + ')');
		    	    if(strData.success){
		    	    	$.messager.show({
		    	    		title : '确认',
		    	    		msg : '宽带费用已确认！',
		    			
		    	    	})
		    	    }else {
		    	    	$.messager.show({
		    	    		title : '确认',
		    	    		msg  : '宽带费用有未确认条目， 请负责人确认后才可以查询费用报表！',
		    	    		
		    	    	})
		    	    	
		    	    }
		    	
		    },
		    error : function(data){
		    				    
				alert(data.status);
				$.messager.progress('close');
				$.messager.alert('错误,请检查数据');
				
			},
			
		});
		
		
	}
	
}

*/