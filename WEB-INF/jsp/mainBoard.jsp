<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head >
<%@ include file="path.jsp"%>
    <title>用户列表</title>
    <script type="text/javascript" src="${demoPath}static/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${demoPath}static/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${demoPath}static/js/easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${demoPath}static/js/easyui/themes/bootstrap/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${demoPath}static/js/easyui/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="${demoPath}static/css/default.css" />
	<script type="text/javascript" src='${demoPath}static/js/publicmethod.js'> </script>
	<script type="text/javascript" src='${demoPath}static/js/app/mainBoard.js'></script>
	
</head>
<body class="easyui-layout" style="overflow-y: hidden"  >
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="${demoPath}static/image/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div>
</noscript>
    <div data-options="region: 'north', split:true, border:false" style="overflow: hidden; height: 30px;
        background: url(${demoPath}static/image/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎 ${user.username}用户登录 
        <a href="javascript:void(0)" id="editpass" >修改密码</a> 
        <a href="javascript:void(0)" id="logout" >安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="${demoPath}static/image/blocks.gif" width="20" height="20" align="absmiddle" />业务后台管理</span>
    </div>
    <div data-options="region:'south', split:true " style="height: 30px; background: #D2E0F2; ">
        <div class="footer">By 中软国际TPG质量管理与运作部</div>
    </div>
    <div data-options="region:'west', hide:true, split:true" title="导航菜单" style="width:180px;" id="west">
	
		<ul id="nav"></ul>
    </div>
    <div id="mainPanle" data-options="region:'center'" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  data-options="fit:true, border:false, script:true" >
			<div title="起始页" style="padding:20px;overflow:hidden; color:red; " >
				<p>欢迎使用后台系统</p>
				 
			</div>
		</div>
    </div>
   
   
   <div id="editPwd" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false"   style="width: 300px; height: 250px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            	<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <div  id="message"></div>
                <table cellpadding=3>
                   <tr>
                   		<td></td>
                        <td><input id="editPwdUserId" type="hidden" class="textbox" value=${user.id}></td>
                   </tr>
                   <tr>
                        <td>输入旧密码：</td>
                        <td><input id="editPwdOld" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>输入新密码：</td>
                        <td><input id="editPwdNew" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>新密码确认：</td>
                        <td><input id="editPwdRepeatPwd" type="Password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
             
            
            <div data-options="region:'south', border:false " style="text-align: right; height: 50px; line-height: 30px;">
                <a id="btnOk"  href="javascript:void(0)" >确定</a>
                <a id="btnCancel" class="easyui-linkbutton"  data-options="iconCls:'icon-cancel', iconAlign: 'left' " href="javascript:void(0)">取消</a>
            </div>
            
             
        </div>
    </div>

  
    <!--  
    <div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
	--> 
</body>
</html> 