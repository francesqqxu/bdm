<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%@ include file="path.jsp"%>
    <title>用户列表</title>
    <script type="text/javascript" src="${demoPath}static/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="${demoPath}static/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${demoPath}static/js/easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${demoPath}static/js/easyui/themes/bootstrap/easyui.css" />
  
	<link rel="stylesheet" type="text/css" href="${demoPath}static/js/easyui/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="${demoPath}static/css/default.css" />
 
	<script type="text/javascript" src='${demoPath}static/js/publicmethod.js'> </script>
	<script type="text/javascript" src='${demoPath}static/js/app/testdatagrid.js'></script>
	
	
	
	
</head>
<body>

  <table id="test_datagrid"></table>

</body>
</html>