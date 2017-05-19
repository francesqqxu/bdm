<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<script type="text/javascript" src='${demoPath}static/js/app/role.js'></script>
    <table id="roleList_role"> </table>
    

    
    <div id="roleList_tool">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options = "iconCls: 'icon-add'"  id="roleList_tool_add">添加角色</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options = "iconCls: 'icon-edit'"  id="roleList_tool_edit">编辑角色</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options = "iconCls: 'icon-remove'" id="roleList_tool_del">删除角色</a>
        <!--  
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options = "iconCls: 'icon-auth'"  id="roleList_tool_auth">授权</a>
        -->
    </div>
    
    <div id="roleList_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            data-options="closed: true, buttons: '#roleList_dlg-buttons'">
        <div class="ftitle">角色信息</div>
        <form id="roleList_fm" method="post" novalidate>
            <div class="fitem">
                <label>角色名称:</label>
                <input name="rolename" class="easyui-textbox" >
            </div>
            <div class="fitem">
                <label>角色描述:</label>
                <input name="description" class="easyui-textbox" >
            </div>
            
            <div class="fitem">
                <lable>拥有的资源列表：</lable>
                <input name="authList" id="authList" style="width:150px">
            </div>
            
        </form>
    </div>
    
     
    
    <div id="roleList_dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton"  id="roleList_tool_save" style="width:90px">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"  id="roleList_tool_cancel" style="width:90px">取消</a>
    </div>
    
    
   
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
