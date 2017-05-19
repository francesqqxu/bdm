/**
 * 
 */
var url;

function role_loadEdit(rowData){
	$('#roleList_dlg').dialog('open').dialog('setTitle','编辑角色');
    $('#roleList_fm').form('load',rowData);
    
    $('#authList').combotree({
 		url : 'resource/get_resourcesById.html',
 		required : true,
 		
 		multiple : true,
 		
 		lines : true,
 		
 		checked : true,
 		
 		width : 200,
 		
 		cascadeCheck : true,
 		
 		onLoadSuccess : function(node, data){
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
	               url:"resource/get_resourcesByRole.html?roleId=" +rowData.id,
	               dataType:"json",
	               cache:false,
              
	               success:function(data){
	                   $(data).each(function(i, obj){ 
	                   	var t = $('#authList').combotree('tree');
	                       var n = t.tree('find',obj.id);
	                       if(n){
	                           t.tree('check',n.target);
	                       }
	                   });
	               },
	               error:function(data){
	            	  $.messager.show({
	            		  title : '出错信息',
	            		  msg   : '加载资源信息出错， 请联系管理人员！ 具体错误信息： ' + data.statusText
	            	  })
	            	   
               }
           });
 		    	
		 },
		 onLoadError: function(data){
			 $.messager.show({
				 title : '出错信息',
				 msg   : '加载资源信息出错， 请联系管理人员！ 具体错误信息： '  + data.statusText
			 })
			 
		 }
 	});
    url = 'role/edit.html?id='+rowData.id;

	
}



$(function(){
	
	
	$('#roleList_role').datagrid({
	     url : 'role/get_roles.html',
	     method: 'post',
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
	     toolbar : "#roleList_tool",
	    	    
	     columns :[[
   		 {
			field  : 'id',
			titile : '自动编号',
			width : 10,
			checkbox : true
		 },
	     {
	    	field : 'rolename',
	    	title : '角色名称',
	    	width : 20
	     },
	     {
		    	field : 'description',
		    	title : '描述',
		    	width : 20
		     },
	     ]],
	     onDblClickRow : function (rowIndex, rowData){
	    	 role_loadEdit(rowData);
	    	 
	     },
	     onLoadError : function() {
	    	 $.messager.show({
	    		 title : '出错信息',
	    		 msg   : '加载角色信息出错， 请联系管理人员！ 具体错误信息: ' + datat.statusText
	    	 });
	     }
	     
	     
	});
	
});	

$('#roleList_tool_add').click(function(){
	
	$('#roleList_dlg').dialog('open').dialog('setTitle','增加角色');
    $('#roleList_fm').form('clear');
    
    $('#authList').combotree({
		url : 'resource/get_resourcesById.html',
		required : true,
		
		multiple : true,
		
		lines : true,
		
		checked : true,
		
		width : 200,
		
		cascadeCheck : true,
		
		onLoadSuccess : function(node, data){
	    	var _this = this;
	    	if(data){
	    		$(data).each(function(index, value){
	    		    if(this.state == 'closed'){
	    		    	$(_this).tree('expandAll');
	    		    }
	    		});
	    	}
//	    	var da=$("#authList").combotree('tree').tree("getRoot");
//          
//	    	$("#authList").combotree('tree').tree("check",da.target);
	    },
	    onLoadError : function(data){
	    	 $.messager.show({
	    		 title : '出错信息',
	    		 msg   : '加载资源信息出错， 请联系管理人员！ 具体错误信息: ' + datat.statusText
	    	 });
	    }
	});
    
    url = 'role/add.html';
});

$('#roleList_tool_edit').click(function(){
	
	var row = $('#roleList_role').datagrid('getSelections');
    if (row.length > 1) {
   	 $.messager.alert('警告', '修改记录仅能选择一条记录', 'warning');
    }
    else if(row.length == 1)
    {
    	role_loadEdit(row[0]);
    }
    else if(row.length ==0 ){
    	$.messager.alert('警告提交','至少选择一条记录！','Warning！');
    }
});

$('#roleList_tool_del').click(function(){
	
	 var rows = $('#roleList_role').datagrid('getSelections');
     if (rows.length > 0 ){
         $.messager.confirm('提示','您确定要删除当前角色?',function(r){
             if (r){
            	 var  ids = [];
            	 for(var  i=0; i<rows.length; i++){
            		 ids.push(rows[i].id);
            	 }
                 $.post('role/del.html',{ids: ids.join(',')},function(result){
                     if (result.success){
                         $('#roleList_role').datagrid('reload');    // reload the user data
                     } else {
                         $.messager.show({    // show error message
                             title: '错误信息',
                             msg: '删除角色信息出错， 请联系管理人员！ 具体错误信息: ' + result.msg
                         });
                     }
                 },'json');
             }
         });
     }
     else if (rows.length == 0 ){
    	 $.messager.alert('警告提交','至少选择一条记录！','Warning！');
     }
});


$('#roleList_tool_save').click(function(){
	var t = $('#authList').combotree('tree');
	console.log(t);
	console.log(t.tree('getSelected'));
	console.log($('#authList').combotree('getValues'));
	var auths = $('#authList').combotree('getValues');
	console.log(auths);
    if($('#roleList_fm').form('validate')){
		$.ajax({
	        url: url,
	        type : 'POST',
	        dataType:"json",
	        data :{
	        	rolename : $.trim($('input[name="rolename"]').val()),
	        	description : $.trim($('input[name="description"]').val()),
	        	authList    : auths.join(',')
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
	          		//$('#roleList_fm').form('clear');
	          		$('#roleList_dlg').dialog('close');
	          		$.messager.show({
	          			title : '提示',
	          			msg :   '角色提交成功',
	          			timeout: 5000,
	          			showType : 'slide'
	          		});
	          		$('#roleList_role').datagrid('reload');
	          	}else{
	          		$.messager.show({
	          			title : '出错信息',
	          			msg   : '添加或修改角色信息错误，请联系管理人员， 具体错误信息： ' + data.msg,
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
	          		msg   : '添加或修改角色信息出错，请联系管理人员！ 具体错误信息： ' + data.statusText
	          	});
	          }
	        
	    });
	
//	$('#roleList_fm').form('submit',{
//        url: url,
//        onSubmit: function(){
//            return $(this).form('validate');
//        },
//        success: function(result){
//            var result = eval('('+result+')');
//            //alert(result.success);
//            if (result.success==false){
//                $.messager.show({
//                    title: '错误信息',
//                    msg: '保存角色信息出错，请联系管理人员！'
//                });
//            } else {
//                $('#roleList_dlg').dialog('close');        // close the dialog
//                $('#roleList_role').datagrid('reload');    // reload the user data
//            }
//        }
//    });
	
    }

});
	

$('#roleList_tool_cancel').click(function(){
	
	 $('#roleList_dlg').dialog('close');        // close the dialog
     $('#roleList_role').datagrid('reload');    // reload the user data
});
/*
roleList_tool = {
	
     
     add :  function (){
         $('#roleList_dlg').dialog('open').dialog('setTitle','增加角色');
         $('#roleList_fm').form('clear');
         
         $('#authList').combotree({
     		url : 'resource/get_resourcesById.html',
     		required : true,
     		
     		multiple : true,
     		
     		lines : true,
     		
     		checked : true,
     		
     		width : 200,
     		
     		cascadeCheck : true,
     		
     		onLoadSuccess : function(node, data){
     		    	var _this = this;
     		    	if(data){
     		    		$(data).each(function(index, value){
     		    		    if(this.state == 'closed'){
     		    		    	$(_this).tree('expandAll');
     		    		    }
     		    		});
     		    	}
     		    	
     		    	var da=$("#authList").combotree('tree').tree("getRoot");
                   
     		    	$("#authList").combotree('tree').tree("check",da.target);
     		    },
     		   
     		
     	});
         
         url = 'role/add.html';
     },
     
     edit : function(){
         var row = $('#roleList_role').datagrid('getSelections');
         if (row.length > 1) {
        	 $.messager.alert('警告', '修改记录仅能选择一条记录', 'warning');
         }
         else 
         {
             $('#roleList_dlg').dialog('open').dialog('setTitle','编辑角色');
             $('#roleList_fm').form('load',row[0]);
             
             $('#authList').combotree({
          		url : 'resource/get_resourcesById.html',
          		required : true,
          		
          		multiple : true,
          		
          		lines : true,
          		
          		checked : true,
          		
          		width : 200,
          		
          		cascadeCheck : true,
          		
          		onLoadSuccess : function(node, data){
          		    	var _this = this;
          		    	if(data){
          		    		$(data).each(function(index, value){
          		    		    if(this.state == 'closed'){
          		    		    	$(_this).tree('expandAll');
          		    		    }
          		    		    
          		    		    if(this.rPermission_id  != 0 ){
          		    		    	this.checked = true;
          		    		    }
          		    		    
          		    		});
          		    	}
          		    	$.ajax({
                            url:"resource/get_resourcesByRole.html?roleId=" +row[0].id,
                            dataType:"json",
                            cache:false,
                           
                            success:function(data){
                                $(data).each(function(i, obj){
                                	var t = $('#authList').combotree('tree');
                                    var n = t.tree('find',obj.id);
                                    if(n){
                                        t.tree('check',n.target);
                                    }
                                });
                            },
                            error:function(){alert("发送请求失败");}
                        });
          		    	
          		    },
          		  
          		
          	});
          
             
             url = 'role/edit.html?id='+row[0].id;
         }
     },
     
     save : function (){
         $('#roleList_fm').form('submit',{
             url: url,
             onSubmit: function(){
                 return $(this).form('validate');
             },
             success: function(result){
                 var result = eval('('+result+')');
                 //alert(result.success);
                 if (!result.success){
                     $.messager.show({
                         title: '错误',
                         msg: '提交角色信息出错！'
                     });
                 } else {
                     $('#roleList_dlg').dialog('close');        // close the dialog
                     $('#roleList_role').datagrid('reload');    // reload the user data
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
         var row = $('#roleList_role').datagrid('getSelected');
         if (row){
             $.messager.confirm('提示','您确定要删除该角色?',function(r){
                 if (r){
                     $.post('role/del.html',{id:row.id},function(result){
                         if (result.success){
                             $('#roleList_role').datagrid('reload');    // reload the user data
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
     },
     
    
}

*/