/**
 * 
 */
var url;


$(function(){
    
	console.log("user.js");
	
	function  loadEdit(rowData){
		
		$('#userList_dlg').dialog('open').dialog('setTitle','编辑用户');
	    $('#userList_fm').form('clear');
	    
	    $('#userList_fm').form('load', {
	        id       : rowData.id,
	       	username : rowData.username,
	       	password : rowData.password,
	       	chineseName : rowData.chineseName
	    });
	    
	    url = 'user/edit.html?id='+rowData.id;
	    
	    $('#roleList').combotree({
			url : 'role/get_roleById.html',
			required : true,
	 		
	 		multiple : true,
	 		
	 		lines : true,
	 		
	 		checked : true,
	 		
	 		width : 200,
	 		
	 		cascadeCheck : true,
	    
		    onLoadSuccess: function (node, data) {
	        	var _this = this;
		    	if(data){
		    		$(data).each(function(index, value){
		    		    if(this.state == 'closed'){
		    		    	$(_this).tree('expandAll');
		    		    }
		    		    /*
		    		    if(this.rPermission_id  != 0 ){
		    		    	this.checked = true;
		    		    }
		    		    */
		    		    
		    		});
		    	}
	             $.ajax({
	                url:"user/get_rolesByUser.html?userId=" +rowData.id,
	                dataType:"json",
	                cache:false,
	               
	                success:function(data){
	                	$(data).each(function(i, obj){
	                       	var t = $('#roleList').combotree('tree');
	                           var n = t.tree('find',obj.id);
	                           if(n){
	                               t.tree('check',n.target);
	                           }
	                       });
	                   
	                },
	                error:function(data){
	                	$.messager.show({
	                		title  : '错误信息',
	                		msg    : '加载角色信息出错，请联系管理人员！ 具体错误信息： ' + data.statusText
	                	});
	              	 }
	            });
	            
	         }
		         
	    });
	}


	 
	$('#userList_user').datagrid({
	     url : 'user/get_users.html',
	     method : 'post',
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
	     sortName : 'id',
	     sortOrder : 'desc',
	     toolbar : "#userList_tool",
	    	    
	     columns :[[
   		 {
			field  : 'id',
			titile : '自动编号',
			width : 10,
			checkbox : true
		 },
	     {
	    	field : 'username',
	    	title : '用户名',
	    	width : 20
	     },
	     {
		    	field : 'password',
		    	title : '密码',
		    	width : 20
		 },
	     {
		    	field : 'chineseName',
		    	title : '中文名',
		    	width : 20
		 }    
	     ]],
	     onDblClickRow : function (rowIndex, rowData){
	    	 loadEdit(rowData);
	    	 
	     },
	     onLoadError : function() {
	    	 $.messager.show({
	    		 title : '错误信息',
	    		 msg   : '加载用户信息出错， 请联系管理人员！'
	    	 });
	     }
	     
	});
	
	
	$('input[name="username"]').validatebox({
		required : true
	});
	
	$('input[name="password"]').validatebox({
		required : true
	})
	
	$('input[name="chineseName"]').validatebox({
		required : true
	})
	
	$("a[name='userList_tool_add']").click(function(){
		//alert('click');
		
		 $('#userList_dlg').dialog('open').dialog('setTitle','增加用户');
	     $('#userList_fm').form('clear');
	     
	     $('#roleList').combotree({
	 		url : 'role/get_roleById.html',
	 		required : true,
			
			multiple : true,
			
			lines : true,
			
			checked : true,
			
			width : 200,
			
			cascadeCheck : true,
	     
	       
	        onLoadSuccess: function (node, data) {
	        	var _this = this;
		    	if(data){
		    		$(data).each(function(index, value){
		    		    if(this.state == 'closed'){
		    		    	$(_this).tree('expandAll');
		    		    }
		    		});
		    	}
		    	
//		    	var da=$("#roleList").combotree('tree').tree("getRoot");
//		          
//		    	$("#roleList").combotree('tree').tree("check",da.target);
		    	
	         },
	        onLoadError : function(data){
	        	$.messager.show({
	        		title : '出错信息',
	        		msg    : '加载角色信息发生错误，请联系管理人员， 具体错误 ： ' +data.statusText
	        	})
	        } 
	         
	     });
	     url = 'user/add.html';
	});

	$("a[name='userList_tool_edit']").click(function(){
	
		 var row = $('#userList_user').datagrid('getSelections');
	   	 //alert(row.length);
	   	 if(row.length > 1 ){
	   		 $.messager.show({
	   			 title : '警告',
	   			 msg : '只能更新一条记录'
	   		 });
	   		 
	   	 }
	   	 else  if(row.length == 1)
	   	 {
	   		 loadEdit(row[0]);
	   	 } 
	   	 else if (row.length == 0 ){
	   		$.messager.alert('警告提交','至少选择一条记录！','Warning！');
	   	 }
	});

	$("a[name='userList_tool_del']").click(function(){
	 
	    var rows = $('#userList_user').datagrid('getSelections');
	    if (rows.length > 0 ){
	        $.messager.confirm('提示','您确定要删除当前用户?',function(r){
	            if (r){
	            	var ids = [];
	            	for(var i=0; i < rows.length; i++){
	            		ids.push(rows[i].id);
	            	}
	                $.post('user/del.html',{ids:ids.join(',')},function(result){
	                    if (result.success==true){
	                        $('#userList_user').datagrid('reload');    // reload the user data
	                    } else {
	                        $.messager.show({    // show error message
	                            title: '错误信息',
	                            msg:   '删除用户发生错误， 请联系管理人员！ 具体错误信息：  ' + result.msg
	                        });
	                    }
	                },'json');
	            }
	        });
	    }
	    else if(rows.length == 0 ){
	    	$.messager.alert('警告提交','至少选择一条记录！','Warning！');
	    }
	});


	$('#userList_tool_save').click(function(){
		var t = $('#roleList').combotree('tree');
		console.log(t);
		console.log(t.tree('getSelected'));
		console.log($('#roleList').combotree('getValues'));
		var roles = $('#roleList').combotree('getValues');
		console.log(roles);
	    if($('#userList_fm').form('validate')) {
			$.ajax({
	            url: url,
	            type : 'POST',
	            dataType:"json",
	            data :{
	            	username : $.trim($('input[name="username"]').val()),
	            	password : $.trim($('input[name="password"]').val()),
	            	chineseName : $.trim($('input[name="chineseName"]').val()),
	            	roleList    : roles.join(',')
	            },
	            
	            beforeSend : function() {
					$.messager.progress({
						text : '正在尝试提交....'
					});
					
				},
				success : function(data,response, status) {
	              	$.messager.progress('close');
	              	//var strData = eval('(' + data + ')');
	              	if(data.success==true){
	              		//$('#userList_fm').form('clear');
	              		$('#userList_dlg').dialog('close');
	              		$.messager.show({
	              			title : '提示',
	              			msg :   '用户提交成功',
	              			timeout: 5000,
	              			showType : 'slide'
	              		});
	              		$('#userList_user').datagrid('reload');
	              	}else{
	              		$.messager.show({
	              			title : '出错信息',
	              			msg   : '添加或修改用户错误，请联系管理人员， 具体错误信息： ' + data.msg,
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
	              		msg   : '添加或修改用户出错，请联系管理人员！ 具体错误信息： ' + data.statusText
	              	});
	              }
			  
			});
	   
//		$('#userList_fm').form('submit',{
//	        url: url,
//	        onSubmit: function(){
//	            return $(this).form('validate');
//	        },
//	        success: function(result){
//	            var result = eval('('+result+')');
//	            
//	            if (result.success==true){
//	            	$('#userList_dlg').dialog('close');        // close the dialog
//	                $('#userList_user').datagrid('reload');    // reload the user data
//	            }
//	            else {
//	            	$.messager.show({
//		                    title: '错误信息',
//		                    msg: '提交用户信息出错， 请联系管理人员！'
//		            });
//	            }
//	        }
//	        
//	    });
	    }	
//	 
	});	
  	
	$('#userList_tool_cancel').click(function(){
		$('#userList_dlg').dialog('close');        // close the dialog
        $('#userList_user').datagrid('reload');    // reload the user data
		
	});
	
	 
});



/*
userList_tool = {
	
     
     add :  function (){
    	 
         $('#userList_dlg').dialog('open').dialog('setTitle','增加用户');
         $('#userList_fm').form('clear');
         
         $('#roleList').combobox({
     		url : 'role/get_roles_nopage.html',
 		    valueField : 'id',
   	        textField : 'rolename',
   	       required : 'true',
   	        editable : 'false',
   	        multiple : 'true',
	   	    panelHeight:'auto',
	     
	        formatter: function (row) {
	             var opts = $(this).combobox('options');
	             return '<input type="checkbox" class="combobox-checkbox">' + row[opts.textField];
	         },
	         onLoadSuccess: function () {
	             var opts = $(this).combobox('options');
	             var target = this;
	             var values = $(target).combobox('getValues');
	             $.map(values, function (value) {
	                 var el = opts.finder.getEl(target, value);
	                 el.find('input.combobox-checkbox')._propAttr('checked', true);
	             });
	         },
	         onSelect: function (row) {
	             //console.log(row);
	             var opts = $(this).combobox('options');
	             var el = opts.finder.getEl(this, row[opts.valueField]);
	             el.find('input.combobox-checkbox')._propAttr('checked', true);
	         },
	         onUnselect: function (row) {
	             var opts = $(this).combobox('options');
	             var el = opts.finder.getEl(this, row[opts.valueField]);
	             el.find('input.combobox-checkbox')._propAttr('checked', false);
	         }
     	});
       
         url = 'user/add.html';
     }
   
     edit : function(){
    	 
    	 var row = $('#userList_user').datagrid('getSelections');
    	 alert(row.length);
    	 if(row.length > 1 ){
    		 $.messager.show({
    			 title : '警告',
    			 msg : '只能更新一条记录'
    		 });
    		 
    	 }
    	 else
    	 {
    		 $('#userList_dlg').dialog('open').dialog('setTitle','增加用户');
             $('#userList_fm').form('clear');
             $('#userList_fm').form('load', {
            	username : row[0].username,
            	password : row[0].password,
            	chineseName : row[0].chineseName
             });
             $('#roleList').combobox({
         		url : 'role/get_roles_nopage.html',
     		    valueField : 'id',
       	        textField : 'rolename',
       	        required : 'true',
       	        editable : 'false',
       	        multiple : 'true',
    	   	    panelHeight:'auto',
    	     
    	        formatter: function (row) {
    	             var opts = $(this).combobox('options');
    	             return '<input type="checkbox" class="combobox-checkbox">' + row[opts.textField];
    	         },
    	         onLoadSuccess: function () {
    	             var opts = $(this).combobox('options');
    	             var target = this;
    	             var values = $(target).combobox('getValues');
    	             $.map(values, function (value) {
    	                 var el = opts.finder.getEl(target, value);
    	                 el.find('input.combobox-checkbox')._propAttr('checked', true);
    	             });
    	             
    	             $.ajax({
                         url:"user/get_rolesByUser.html?userId=" +row[0].id,
                         dataType:"json",
                         cache:false,
                        
                         success:function(data){
                             var dataValues =[];
                        	 var j = data.length;
                             $(data).each(function(i, obj){
                             	var el = opts.finder.getEl(target, obj.id);
           	                    el.find('input.combobox-checkbox')._propAttr('checked', true);
                             	dataValues[i] = obj.rolename;
                             });
                             
                             if(data.length >  1 ){
                            	 $('#roleList').combobox('setValues', dataValues);
                             }else{
                            	 $('#roleList').combobox('setValue', dataValues);
                             }
                         },
                         error:function(){
                        	 alert("发送请求失败");
                       	 }
                     });
    	             url = 'user/edit.html?id='+row[0].id;
    	         },
    	         onSelect: function (row) {
    	             //console.log(row);
    	             var opts = $(this).combobox('options');
    	             var el = opts.finder.getEl(this, row[opts.valueField]);
    	             el.find('input.combobox-checkbox')._propAttr('checked', true);
    	         },
    	         onUnselect: function (row) {
    	             var opts = $(this).combobox('options');
    	             var el = opts.finder.getEl(this, row[opts.valueField]);
    	             el.find('input.combobox-checkbox')._propAttr('checked', false);
    	         }
         	});
         
    		 
    	 }
    	
            
     },
     
     save : function (){
         $('#userList_fm').form('submit',{
             url: url,
             onSubmit: function(){
                 return $(this).form('validate');
             },
             success: function(result){
                 var result = eval('('+result+')');
                 alert(result.success);
                 if (!result.success){
                     $.messager.show({
                         title: '错误',
                         msg: '提交用户信息出错！'
                     });
                 } else {
                     $('#userList_dlg').dialog('close');        // close the dialog
                     $('#userList_user').datagrid('reload');    // reload the user data
                     $('#userList_user').datagrid('unSelectAll');
                 }
             }
         });
     },
     
     cancel : function() {
    	 
    	 $('#userList_dlg').dialog('close');        // close the dialog
         $('#userList_user').datagrid('reload');    // reload the user data
         $('#userList_user').datagrid('unSelectAll');
      
     },
     
     del: function (){
         var row = $('#userList_user').datagrid('getSelected');
         if (row){
             $.messager.confirm('提示','您确定要删除该用户?',function(r){
                 if (r){
                     $.post('user/del.html',{id:row.id},function(result){
                         if (result.success){
                             $('#userList_user').datagrid('reload');    // reload the user data
                         } else {
                             $.messager.show({    // show error message
                                 title: '错误!',
                                 msg: result.errorMsg
                             });
                         }
                     },'json');
                 }
             });
         }
     }
     
    
     
}
*/
