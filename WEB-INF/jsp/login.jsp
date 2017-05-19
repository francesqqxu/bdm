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
	
	<script type="text/javascript" src='${demoPath}static/js/app/login.js'></script>
	
 <!-- 
    <style type="text/css">
		BODY {
			TEXT-ALIGN: center; PADDING-BOTTOM: 0px; BACKGROUND-COLOR: #ddeef2; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px
		}
		A:link {
			COLOR: #000000; TEXT-DECORATION: none
		}
		A:visited {
			COLOR: #000000; TEXT-DECORATION: none
		}
		A:hover {
			COLOR: #ff0000; TEXT-DECORATION: underline
		}
		A:active {
			TEXT-DECORATION: none
		}
		.input {
			BORDER-BOTTOM: #ccc 1px solid; BORDER-LEFT: #ccc 1px solid; LINE-HEIGHT: 20px; WIDTH: 182px; HEIGHT: 20px; BORDER-TOP: #ccc 1px solid; BORDER-RIGHT: #ccc 1px solid
		}
		.input1 {
			BORDER-BOTTOM: #ccc 1px solid; BORDER-LEFT: #ccc 1px solid; LINE-HEIGHT: 20px; WIDTH: 120px; HEIGHT: 20px; BORDER-TOP: #ccc 1px solid; BORDER-RIGHT: #ccc 1px solid
		}
</style>
 -->
</head>

<body>
	<FORM id="ff" method=post name="user" action="login.html">
	<DIV></DIV>
	<TABLE style="MARGIN: auto; WIDTH: 100%; HEIGHT: 100%" border=0 cellSpacing=0 cellPadding=0>
 		 <TBODY>
  			<TR>
  			<TD height=150 align="center">&nbsp;<font size="2" color="RED">${message}</font>
  			</TD>
  			</TR>
  			<TR style="HEIGHT: 254px"> 
  			<TD>
		 		<DIV style="MARGIN: 0px auto; WIDTH: 936px"><IMG style="DISPLAY: block" src="${demoPath}static/image/body_03.jpg"> </DIV>
		      	<DIV style="BACKGROUND-COLOR: #278296">
		      	<DIV style="MARGIN: 0px auto; WIDTH: 936px">
		      	<DIV style="BACKGROUND: url(${demoPath}static/image/body_05.jpg) no-repeat; HEIGHT: 155px">
		      	<DIV style="TEXT-ALIGN: left; WIDTH: 265px; FLOAT: right; HEIGHT: 125px; _height: 95px">
			      	<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%">
			        <TBODY>
			        <TR>
			          <TD style="HEIGHT: 43px"><INPUT id=username class=input type=text name=username required="true"></TD>
			        </TR>  
			        <TR>
	          			<TD><INPUT id=password class=input type=password name=password required="true"></TD>
	          		</TR>
	        		<TR>
	          			<TD style="HEIGHT: 50px"><INPUT id=checkcode required="true" class=yzm size=8 type=text name=checkcode> 
		          			<a style="text-align: center;" id="verifyClick" title="点击 刷新?" href="javascript:void(0);"> 
		          			<img style="width:65px;height:22px;" align="absmiddle" id="verifyCode" src="${demoPath}verifyCode.html" />
		          			</a>
	            		</TD>
	             	</TR>
	             	</TBODY>
	             	</TABLE>
	        	</DIV>
	      		<DIV style="HEIGHT: 1px; CLEAR: both"></DIV>
	      		<DIV style="WIDTH: 380px; FLOAT: right; CLEAR: both">
		      		<TABLE border=0 cellSpacing=0 cellPadding=0 width=300>
		        	<TBODY>
		        	<TR>
			          <TD width=100 align=right><INPUT onclick="submitForm()"
			            style="BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px" 
			            id=btnLogin src="${demoPath}static/image/btn1.jpg" 
			            type=image name=btnLogin></TD>
			          <TD width=100 align=middle><INPUT onclick="clearForm()"
			            style="BORDER-RIGHT-WIDTH: 0px; BORDER-TOP-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px" 
			            id=btnReset src="${demoPath}static/image/btn2.jpg" 
			            type=image name=btnReset></TD>
			        </TR>
			        </TBODY>
			        </TABLE>
		        </DIV>
		        </DIV>
		        </DIV>
		        </DIV>
	      		<DIV style="MARGIN: 0px auto; WIDTH: 936px"><IMG 
	     			 src="${demoPath}static/image/body_06.jpg">
	     		</DIV>
	     		</TD>
	     		</TR>
	  			<TR style="HEIGHT: 30%">
	   		 		<TD>&nbsp;</TD>
	   		 	</TR>
	   		 	</TBODY>
   		 	</TABLE>
   		 	</FORM>
</body>
</html>  