<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<script type="text/javascript" src='${demoPath}static/js/app/user.js'></script>

<table id="userList_user"> </table>
    
    <div id="userList_tool">
        <a href="javascript:void(0)" class="easyui-linkbutton" name="userList_tool_add" data-options="iconCls: 'icon-add'" 
                                          >添加用户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" name="userList_tool_edit" data-options="iconCls: 'icon-edit'" 
          									>编辑用户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" name="userList_tool_del" data-options="iconCls: 'icon-remove'" 
         									>删除用户</a>
         <!--  
         <a href="javascript:void(0)" class="easyui-linkbutton" name="userList_tool_auth" data-options="iconCls: 'icon-remove'" 
    									>授权</a>
        -->    									
    </div>
    
    <div id="userList_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
          data-options="buttons :'#userList_dlg_buttons', closed: true ">
        <div class="ftitle">用户信息</div>
        <form id="userList_fm" method="post" novalidate>
        
            <div class="fitem">
               <label></label>
               <input name="id"  class="textbox" type="hidden">
            </div>
            <div class="fitem">
                <label>用户名:</label>
                <input name="username" class="textbox" >
            </div>
            <div class="fitem">
                <label>密&nbsp;码:</label>
                <input name="password"  type="password" >
            </div>
            <div class="fitem">
                <label>中文名字:</label>
                <input name="chineseName" class="easyui-textbox" >
            </div>
            <div class="fitem">
                <label>用户拥有的角色:</label>
                <input name="roleList" id="roleList" >
            </div>
            
        </form>
    </div>
    <div id="userList_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton"  id="userList_tool_save" style="width:90px">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"  id="userList_tool_cancel" style="width:90px">取消</a>
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
