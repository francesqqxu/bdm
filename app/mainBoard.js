$(function() {
	
	console.log('mainBoard.js');
	
	$('#nav').tree({
	    url : 'menu/get_menu.html',
	    lines : true,
	    onLoadSuccess : function(node, data){
	    	var _this = this;
	    	if(data){
	    		$(data).each(function(index, value){
	    		    if(this.state == 'closed'){
	    		    	$(_this).tree('expandAll');
	    		    }
	    		});
	    	}
	    },
	    
	    onLoadError : function(data){
	    	$.messager.show({
	    		title : '出错信息',
	    		msg   : '加载菜单信息出错， 请联系管理人员！ 具体错误信息： ' + data.statusText
	    	});
	    },
	    
		onClick : function(node){
			//console.log(node.text + node.url);
			if(node.url){
				if($('#tabs').tabs('exists',node.text)){
					$('#tabs').tabs('select', node.text)
					var tabSelect = $('#tabs').tabs('getTab', node.text);
					console.log(node.text);
					console.log(tabSelect);
					$('#tabs').tabs('update',{
						tab : tabSelect,
						options :{
							href : node.url
						}
					})
				}else {
					$('#tabs').tabs('add', {
						title : node.text,
						closable: true,
						iconCls : node.iconCls,
						href: node.url
					});
				}
			}
		}
		
	});
	
				
	
	$('#btnOk').linkbutton({
		text : '确定',
		iconCls :'icon-ok',
		iconAlign : 'left'
	});
	
	$('#tabs').tabs({
		fit : true,
		border: false,
		script: true
	});
	
	
	
    $('#editPwd').window({
             title : '修改密码',
             width : 360,
             modal : true,
             shadow : true,
             closed : true,
             height : 200,
             top : 200,
             resizable : false
                                   
            
    });
    
    //alert($('#editPwdUserId').val());
    
    $('#editPwdOld').validatebox({
    	required: true,
    	missingMessage: '请输入旧密码'
    	
    });
    
    $('#editPwdNew').validatebox({
    	required: true,
    	missingMessage: '请输入新密码'
    	
    });
   
    $('#editPwdRepeatPwd').validatebox({
    	required : true,
    	missingMessage: '请再次输入新密码',
    	validType : 'equals[$("#editPwdNew")]'
    });
    
    
    $.extend($.fn.validatebox.defaults.rules, {
    	equals: {
    		validator : function(value, param){
    			return value == $(param[0]).val();
    		},
    		message : '二次密码输入不一致.'
    	}
    });
 
    
   
    $('#editpass').click(function() {
    	$('#editPwd').window('open');
    	$('#editPwd').find("input").each(function(){
    		if(this.id != "editPwdUserId"){
    			this.value="";
    		}
    	});
    	
    });
    	
    $('#btnOk').click(function(){
    	     console.log('editpwd');
    		 $.ajax({
     		    url : 'user/editPwd.html',
     		    type : 'POST',
                 timeout : 1000,
                 datatype : 'json',
                 data   : {
                 	id :   $('#editPwdUserId').val(),
                 	oldPwd : $.trim($('#editPwdOld').val()),
                 	newPwd : $.trim($('#editPwdNew').val())
                 },
                 
                 beforeSend : function() {
                 	$.messager.progress({
                 		text: '正在尝试提交....'
                 	});
                 	
                 },
                 
                 success : function(data,response, status) {
                 	$.messager.progress('close');
                 	var strData = eval('(' + data + ')');
                 	if(strData.success==true){
                 		$('#editPwd').window('close');	
                 		$.messager.show({
                 			title : '提示',
                 			msg : strData.msg,
                 			timeout: 5000,
                 			showType : 'slide'
                 		});
                 	}else{
                 		$('#message').html(strData.msg);
                 		$('#message').css({color:"red"});
//                 		$('#editPwd').window('close');
                 		$.messager.show({
                 			title : '出错信息',
                 			msg   : '修改密码错误，请联系管理人员， 具体错误信息： ' + strData.msg,
                 			timeout : 5000,
                 			showType :'slide'
                 		});
                 	}
                 },
                 
                 error : function(data, response, status){
                 	//alert(status);
                 	$.messager.progress('close');
                 	$.messager.show({
                 		title : '出错信息',
                 		msg   : '修改密码出错，请联系管理人员！ 具体错误信息： ' + data.statusText
                 	});
                 }
    	 });
    	
    });
    
    $('#logout').click(function() {
    	$.messager.confirm('系统提示','您确定要退出本次登录吗？',function(r){
    		if(r){
    			location.href = 'logout.html';
    		}
    	});
    });
    
    $('#btnCancel').click(function(){
    	$('#editPwd').window('close');
    });
    
});

/*
function editpass() {
	$('#mainboard_upd_pwd').window('open');
}
*/		


