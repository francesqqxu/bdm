<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="path.jsp"%>
    <title>用户资源访问</title>
	<link rel="stylesheet" type="text/css" href="${demoPath}static/js/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${demoPath}static/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${demoPath}static/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${demoPath}static/js/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
    <table id="dg" title="用户资源访问" class="easyui-datagrid" style="width:1180px;height:540px;margin: 10px 5px 15px 20px;"
            url="function/get_functions.html"
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="value" width="50">允许访问路径</th>
                <th field="permission_id" width="50">访问权限</th>
                <th field="role_id" width="50">角色</th>
                <th field="type" width="50">类型</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加资源</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑资源</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除资源</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">资源信息</div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>允许访问路径:</label>
                <input name="value" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>访问权限:</label>
                <input name="permission_id" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>角色:</label>
                <input name="role_id" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>类型:</label>
                <input name="type" class="easyui-textbox">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('setTitle','增加资源');
            $('#fm').form('clear');
            url = 'resource/add.html';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','编辑资源');
                $('#fm').form('load',row);
                url = 'resource/edit.html?id='+row.id;
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: '错误!',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','您确定删除该资源?',function(r){
                    if (r){
                        $.post('function/del.html',{id:row.id},function(result){
                            if (result.success){
                                $('#dg').datagrid('reload');    // reload the user data
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
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
</body>
</html>