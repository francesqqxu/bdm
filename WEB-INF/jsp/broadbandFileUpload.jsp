<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src='${demoPath}static/js/app/broadbandFileUpload.js'></script>

 
 <table id="broadband_fileUpload"> </table>  
 
<!-- 
<form  action="broadband/fileUploading.html" method="post" enctype="multipart/form-data">
	 	 文件： <input type="file"  id="fileToUpload" name="fileToUpload" class="input" style="width:200px">
	 	   <input type="submit" value="Submit"/>
</form>

 -->
 
   <div id="fileUpload_tool" style="padding:5px;">
   		
   		<div   style="margin-top: 7px;margin-left:5px;margin-bottom:5px; marging-right:5px; float:left">
    	 <form method="post" action="<c:url value='broadband/exportBroadbandTemplate.html'/>">
   	        	<input  id="downloadTemplate" type="submit" class="esayui-linkbutton" value="下载模板"/>
  			</form>
    	</div>
   		
    	<div style="margin-top: -6px;margin-bottom:5px; margin-left:5px; margin-right:5px; float:left">

    	    <a href="javascript:void(0)" class="easyui-linkbutton"  
    	        id="fileUpload_tool_import">导入</a>
    	    <a href="javascript:void(0)" class="easyui-linkbutton"  
    	        id="fileUpload_tool_remove">删除</a>
    	</div>
    	
    	<div id="fileUpload_search" style="margin-top: 1px; margin-left:5px; margin-bottom:5px; margin-right:5px;color:#333;">
    	    <table>
    	    <tr>
            <td>
    	           宽带城市信息：<input type = "text"  class="easyui-textbox" name="fileUpload_search_city" style="width:110px">
    	    </td>
    	    <td>
    	            归集单位：  <input type="text"   name="fileUpload_search_feeCollection" style="width:110px">
    	    </td>
    	    <td>
    	           核算负责人:　<input type="text"  name="fileUpload_search_director" style="width:110px">
    	    </td>
    	    <!-- 
    	    <td>         
    	             创建时间从：    <input type="text"  name="date_from" class="easyui-datebox" editable="false" style="width:110px">  
    	    </td>
    	    <td>         
    	             到：                    <input type="text" name="date_to" class="easyui-datebox" editable="false" style="width:110px">
    	    </td>
    	     -->
    	    <td>
    	    <a href="javascript:void(0)" class="easyui-linkbutton"  
    	        id="fileUpload_tool_search">查询</a>
    	    </td>
    	    <td>
    	      <a href="javascript:void(0)" class="easyui-linkbutton"  
   	        	id="fileUpload_tool_clear">清空选项</a>
    	    </td>
    	    </tr>
    	</table>
    	</div> 
    </div>

 <div id="fileUpload" class="easyui-window">
      	<p> 文件： <input type="file"  id="fileToUpload" name="fileToUpload"  style="width:200px"></p>
</div>

