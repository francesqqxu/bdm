<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="path.jsp"%>
    <title>登录页面</title>
    <meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
    <script type="text/javascript" src="${demoPath}static/js/md5.js"></script>
    <script type="text/javascript" src="${demoPath}static/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${demoPath}static/js/easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${demoPath}static/js/easyui/themes/bootstrap/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${demoPath}static/js/easyui/themes/icon.css" />
    <script type="text/javascript">
		window.onload = function() {
			//...
		}
       $(function(){
    	    	$("#verifyClick").bind('click',function(){
    	    		var url ='${demoPath}verifyCode.html?t=' + Math.random();
    	    		$('#verifyCode').attr('src',url);
    	    	});
    	    });
       function submitForm(){
			$('#ff').form('submit');
			 var password = document.getElementById("password");
	    	   //md5加密
	    	 document.getElementById("password").value = hex_md5(password.value);
	    	 document.user.submit();
		}
		function clearForm(){
			$('#ff').form('clear');
		}
    </script>
    <style type="text/css">
body {
	font-family: Verdana, Geneva, sans-serif;
	font-size: 12px;
}

input {
	width: 140px;
	height: 20px;
	background-color: #FFF;
	border: #999 1px solid;
}
#body_content {
	width: 400px;
	height: 250px;
	margin-left: 450px;
	margin-top: 200px;
}
</style>
</head>

<body>
	<div id="body_content">
	<div style="margin:10px 0;"></div>
	<div class="easyui-panel" title="登录窗口" style="width:400px" data-options="iconCls:'icon-lock'">
		<div style="padding:10px 0 10px 60px">
	    <form id="ff" action="login.html" name="user" method="post" >
	    	<table>
	    		<tr>
	    			<td>用户名:</td>
	    			<td><input class="easyui-validatebox" type="text" name="username" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td><input class="easyui-validatebox" type="password" name="password" id="password" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>验证码:</td>
	    			<td><input class="easyui-validatebox" type="text" name="verifyCode" data-options="required:true"></input></td>
	    			<td>
	    			<a style="text-align: center;" id="verifyClick" title="点击 刷新?" href="javascript:void(0);"> 
						<img style="width:65px;height:22px;" align="absmiddle" id="verifyCode" src="${demoPath}verifyCode.html" /> 
					</a>
					</td>
	    		</tr>
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	    </div>
	</div>
	</div>
</body>
</html>  