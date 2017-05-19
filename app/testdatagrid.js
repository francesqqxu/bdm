/**
 * 
 */


$(function() {

	
	$('#test_datagrid').datagrid({
	     url : 'broadband/get_broadbands.html',
	     fit : true,
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
	




})

