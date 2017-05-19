<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript" src='${demoPath}static/js/app/feeConfirming.js'></script>
    <table id="feeConfirming"> </table>
    
    <div id="feeConfirming_tool" style="padding:5px; ">
        <div style="padding:5px; margin-top:15px; color:#333; float:left">
    	   <label from="month">  请选择确认月份： </label>
    	   <input type="text" id="date_confirm"  >  
    	 </div> 
    	<div style="padding:5px;float:left;">
    	    <a href="javascript:void(0)" class="easyui-linkbutton"
    	       id="feeConfirming_tool_select">选取待确认条目</a>
   	    	<a href="javascript:void(0)" class="easyui-linkbutton"  
   	       		id="feeConfirming_tool_edit">修改</a>
    	    <a href="javascript:void(0)" class="easyui-linkbutton"  
   	       		id="feeConfirming_tool_confirm">确认</a>
    	</div>
    	
    	
    	<!-- 
    	<div style="padding:0 0 0 7px;color:#333;">
    	    <table>
    	    <tr>
            <td>
    		
    	    <td>         
    	            选择月份：    <input type="text"  name="date_from" class="easyui-datebox" editable="false" style="width:110px">  
    	    </td>
    	    
    	    <td>
    	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"  onclick="feeConfirm_tool.search();">查询</a>
    	    </td>
    	    </tr>
    	</table>
    	</div> 
    	  -->
   
    </div>
   
    
    <form id="feeConfirm_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
    <table>
         <tr>
         <td></td>
         <td>
         <input type="hidden" name="feeConfirm_edit_id" class="textbox" style="width:200px;">
         </td>
         </tr>
         <tr>
         <td>标识：</td>
         <td><input type="text" name="feeConfirm_edit_broadbandId" class="textbox" readonly="readonly" style="width:200px;"></td>
       	 </tr>
       	 <tr>
         <td>费用日期：</td>
         <td><input type="text" name="feeConfirm_edit_feeDate" class="textbox" readonly="readonly" style="width:200px;"></td>
       	 </tr>
       	 
       	 <tr>
       	 <td>应缴费用： </td>
       	 <td><input type="text" name="feeConfirm_edit_fee" class="textbox" readonly="readonly" style="width:200px;"></td>
  		 </tr>
  		 
  		 <tr>
       	 <td>营改增： </td>
       	 <td><input type="text" name="feeConfirm_edit_valueAddTax" class="textbox" readonly="readonly" style="width:200px;"></td>
  		 </tr>
  		 
  		 <tr>
  		 <td>经办人：</td>
  		 <td> <input type="text" class="textbox" name="feeConfirm_edit_agent" readonly = "readonly" style="width:200px;"></td>
  		 </tr>
  		 <tr>
  		 <td>负责人：</td>
  		 <td> <input type="text" class="textbox" name="feeConfirm_edit_director" readonly ="readonly" style="width:200px;"></td>
  		 </tr>
  		 
  		 <tr>
  		 <td>修改后费用：</td>
  		 <td> <input type="text" class="textbox" name="feeConfirm_edit_changedFee" style="width:200px;"></td>
  		 </tr>
  		 <tr>
  		 <td>修改理由：</td>
  		 <td> <input type="text" class="textbox" name="feeConfirm_edit_reason" style="width:200px;"></td>
  		 </tr>
  		 <tr>
  		 <td>利用率： </td>
  		 <td> <input type="text" class="textbox" name="feeConfirm_edit_useRatio" style="width:200px;"></td>
         </tr>
    </table>
    </form>
     
    <!-- 
     <div id="feeConfirm_edit" class="easyui-window">
      	 <p><input type="hidden" name="feeConfirm_edit_id" class="textbox" style="width:200px;"></p>
         <p>标识： <input type="text" name="feeConfirm_edit_broadbandId" class="textbox" style="width:200px;"></p>
       	 <p>应缴费用： <input type="text" name="feeConfirm_edit_fee" class="textbox" style="width:200px;"></p>
  		 <p>修改后费用： <input  id="feeConfirm_edit_changedFee" class="textbox" name="feeConfirm_edit_changedFee" style="width:200px;"></p>
     </div>
     
      -->
    