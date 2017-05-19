<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript" src='${demoPath}static/js/app/feeConfirmSponsor.js'></script>
    <table id="feeConfirmSponsor"> </table>
        
    <div id="feeConfirmSponsor_tool" style="padding:5px; margin-bottom;5px">
    
         <div style="padding:5px; margin-top:10px; margin-bottom:5px;color:#333; float:left">
    	   <label from="month">  请选择发起确认月份： </label>
    	   <input type="text" id="date_from"  >  
    	 </div> 
    	
    	  <div style="margin-bottom:5px; float:left">
    	    <a href="javascript:void(0)" class="easyui-linkbutton"   
   	       		id="feeConfirmSponsor_tool_sponsor">发起</a>
   	    	<a href="javascript:void(0)" class="easyui-linkbutton"   
   	       		id="feeConfirmSponsor_tool_submit">提交</a>
   	  	    <a href="javascript:void(0)" class="easyui-linkbutton"  
   	       		id="feeConfirmSponsor_tool_del">删除</a>
	   	       <!-- 
	    	    <a href="javascript:void(0)" class="easyui-linkbutton"  
	   	       		id="feeConfirmSponsor_tool_refresh">刷新</a>
	    	   -->
    	  </div>
    	 
    </div>
    
   
    <!-- 
    <form id="feeConfirm_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
         <p><input type="hidden" name="id" class="textbox" style="width:200px;"></p>
         <p>标识： <input type="text" name="broadbandId_edit" class="textbox" style="width:200px;"></p>
       	 <p>城市： <input type="text" name="city_edit" class="textbox" style="width:200px;"></p>
  		 <p>部门： <input  id="payOrganization_edit" class="textbox" name="payOrganization_edit" style="width:200px;"></p>
    </form>
     -->
    