/**
 * 
 */
var url;

$(function(){

	$('#userList_user').datagrid({
	     url : 'user/get_users.html',
	     method : 'post',
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
	     ]]
	     
	});
	
	
	$("a[name='userList_tool_add']").click(function(){
		//alert('click');
		
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
	   	 else
	   	 {
   		 	$('#userList_dlg').dialog('open').dialog('setTitle','编辑用户');
            $('#userList_fm').form('clear');
            
            $('#userList_fm').form('load', {
            id       : row[0].id,
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
   	     
//	   	        formatter: function (row) {
//	   	             var opts = $(this).combobox('options');
//	   	             return '<input type="checkbox" class="combobox-checkbox">' + row[opts.textField];
//	   	         },
	   	         
	   	        
	   	         
	   	         onLoadSuccess: function () {
	   	             var opts = $(this).combobox('options');
	   	             var target = this;
	   	             var values = $(target).combobox('getValues');
	   	             $.map(values, function (value) {
	   	                 var el = opts.finder.getEl(target, value);
//	   	                 el.find('input.combobox-checkbox')._propAttr('checked', true);
	   	                
	   	             });
   	                 console.log(row[0].id);
	   	             $.ajax({
	                        url:"user/get_rolesByUser.html?userId=" +row[0].id,
	                        dataType:"json",
	                        cache:false,
	                       
	                        success:function(data){
	                            var dataValues =[];
	                       	 	var j = data.length;
	                            $(data).each(function(i, obj){
	                            	var el = opts.finder.getEl(target, obj.id);
//	          	                    el.find('input.combobox-checkbox')._propAttr('checked', true);
//	          	                    alert(el.find('input.combobox-checkbox').attr('checked'));
//	          	                    alert(el.find('input.combobox-checkbox').prop('checked'));
//	          	                    el.find('input.combobox-checkbox').prop('checked',true);
//	          	                    alert(el.find('input.combobox-checkbox').prop('checked'));
	          	                    $('#roleList').combobox('select', obj.id);
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
	   	             console.log(row);
	   	             var opts = $(this).combobox('options');
	   	             var el = opts.finder.getEl(this, row[opts.valueField]);
	   	             alert(el.find('input.combobox-checkbox').prop('checked'));
	   	             el.find('input.combobox-checkbox').prop('checked', true);
	   	             alert(el.find('input.combobox-checkbox').prop('checked'));
	   	             alert(el.find('input.combobox-checkbox').prop('baseURI'));
	   	            
	   	         
	   	         },
	   	         onUnselect: function (row) {
	   	        	 console.log(row);
	   	             var opts = $(this).combobox('options');
	   	             console.log(opts.valueField);
	   	             var el = opts.finder.getEl(this, row[opts.valueField]);
	   	             console.log(row[opts.valueField]);
	   	             el.find('input.combobox-checkbox')._propAttr('checked', false);
	   	             
	   	          
	   	         }
        	 
            });
   	 }    
	});

	$("a[name='userList_tool_del']").click(function(){
	 
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
	});


	$('#userList_tool_save').click(function(){
		
		$('#userList_fm').form('submit',{
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
	                    msg: '提交用户信息出错！'
	                });
	            }
	            else {
	                $('#userList_dlg').dialog('close');        // close the dialog
	                $('#userList_user').datagrid('reload');    // reload the user data
	            }
	            
	        }
	    });
	 
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
