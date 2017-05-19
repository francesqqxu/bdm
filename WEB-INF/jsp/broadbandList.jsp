<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript" src='${demoPath}static/js/app/broadband.js'></script>
    <table id="broadband"> </table>
    
    <div id="broadband_tool" style="padding:5px; ">
    	<div style="margin-bottom:5px; padding:0 0 0 5px; float : left">
   	    	<a href="javascript:void(0)" class="easyui-linkbutton" 
   	    	  id="broadband_tool_add">添加</a>
    	    <a href="javascript:void(0)" class="easyui-linkbutton"  id="broadband_tool_edit">修改</a>
    	    <a href="javascript:void(0)" class="easyui-linkbutton"  
    	    	id="broadband_tool_remove">删除</a>
    	    <a href="javascript:void(0)" class="easyui-linkbutton" 
    	    	id="broadband_tool_unselect">取消选择</a>  
    	    <a href="javascript:void(0)" class="easyui-linkbutton" 
    	    	id="broadband_tool_reload">刷新</a>
    	  
    	</div>
    	
    	<div style="margin-bottom:5px; padding:7.5px 0 0 5px;color:#333; float : left">
    	    <table>
    	    <tr>
            <td>
    		 宽带城市信息：<input type = "text" class="textbox" name="broadband_search_city" style="width:110px">
    	    </td>
    	    <td>
    	             归集单位：        	<input type="text" class="textbox"  name="broadband_search_feeCollection" style="width:110px">
    	    </td>
    	    <!-- 
    	    <td>
    	             核算负责人:　   <input type="text"  class="textbox"  name="search_director" style="width:110px">
    	     -->
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
    	    <a href="javascript:void(0)" class="easyui-linkbutton"  id="broadband_tool_search">查询</a>
    	    </td>
    	   
    	    </tr>
    	</table>
    	</div> 
    </div>
    
    <form id="broadband_add" style="margin:0;padding:5px 0 0 25px;color:#333;">
        <table class="dv-table" style="width:100%;background:#fafafa;padding:5px;margin-top:5px;"> 
        <tbody>
        <tr>
        <td>标识： </td>  
        <td><input type="text" name="broadband_add_broadbandId" id="broadband_add_broadbandId" class="textbox" style="width:200px;"></td>
    	<td>停用年月：</td>
    	<td><input type="text" name="broadband_add_stopDate" id="broadband_add_stopDate"  style="width:200px"></td>
    	</tr>
   
        <tr>
    	<td>城市：</td> 
        <td><input type="text" name="broadband_add_city" id= "broadband_add_city" class="textbox" style="width:200px;"></td>
        <!-- 
    	<td>状态：</td>
    	<td><input type="text" name="broadband_add_status" id="broadband_add_status" class="textbox" style="width:200px;"></td>
    	  --> 
    	  
    	<td>缴费单位：</td>
    	<td> <input  id="broadband_add_payOrganization" class="textbox" name="broadband_add_payOrganization" style="width:200px;"></td>  
    	</tr>
    	
    	<tr>
    	<td>场地地址：</td>
    	<td><input type="text" name="broadband_add_address" id="broadband_add_address" class="textbox" style="width:200px;"></td>
    	<td>线路类型：</td>
        <td><input type="text" name="broadband_add_lineType" id= "broadband_add_lineType" class="textbox" style="width:200px;"></td>
    	</tr>
    	
    	<tr>
        <td>链接地址：</td>
        <td><input type="text" name="broadband_add_linkAddress" id="broadband_add_linkAddress"  class="textbox" style="width:200px;"></td>
        <td>运营商:</td> 
        <td> <input type="text" name="broadband_add_operator"   id="broadband_add_operator" class="textbox" style="width:200px;"> </td>
        </tr>
       
        <tr>
        	<td>接入方式: </td>
        	<td> <input type="text" name="broadband_add_accessWay" id="broadband_add_accessWay" class="textbox"  style="width:200px;"></td>
        	<td>接入带宽:</td>
        	<td><input type="text" name="broadband_add_bandwidth"  id="broadband_add_bandwidth" class="textbox"  style="width:200px;"></td>
        </tr>
        
        <tr>
        <td>应缴费用：</td>
        <td> <input  type="text" name="broadband_add_fee"  id="broadband_add_fee"    style="width:200px;"></td>
        <td>营改增：</td>
        <td> <input type="text" name="broadband_add_valueAddTax" id="broadband_add_valueAddTax" style="width:200px;"> </td>
        </tr>
         
        <tr>
        <!-- 
        <td>经办:</td>
        <td><input type="text" name="broadband_add_agent" class="textbox" style="width:200px;"></td>
         -->
        <td>结算负责：</td>
        <td> <input type="text" name="broadband_add_director"  id="broadband_add_director" class="textbox"  style="width:200px;"
              value = ${sessionScope.user.chineseName}></td>
        <td>付费方式:</td>
        <td><input type="text"  name="broadband_add_paymentMethod" id="broadband_add_paymentMethod" class="textbox" style="width:200px;"></td>
        </tr>
       
        <tr>
        <td>结算周期:</td>
        <td> <input type="text"  name="broadband_add_settlementCycle"  id="broadband_add_settlementCycle" class="textbox" style="width:200px;"></td>
        <td>支付月: </td>
        <td><input type="text" name="broadband_add_paymentMonth" id="broadband_add_paymentMonth" class="textbox"  style="width:200px;"></td>
        </tr>
       
        <tr>
        <td>费用归集:</td>
        <td><input type="text" id="broadband_add_feeCollection" name="broadband_add_feeCollection" class="textbox"  style="width:200px;"></td>
        
        <!-- 
        <tr>
        <td>利用率: </td>
        <td><input type="text"   name="broadband_add_useRatio"  id="broadband_add_useRatio"   value="0" style="width:200px;"></td>
        <td>网络使用:</td>
        <td> <input type="text"  name="broadband_add_networkUsage"  class="textbox"  style="width:200px;"></td>
        </tr>
         --> 
        <td>到期日期</td>
        <td><input type="text" name="broadband_add_dueDate" id="broadband_add_dueDate" style="width:200px;"></td>
        </tr>
       
        <tr>
        <td>线路用途</td>
        <td><input type="text" name="broadband_add_lineUsage" id="broadband_add_lineUsage" style="width:200px;"></td>
        <td>IP地址数量</td>
        <td><input type="text" name="broadband_add_ipAddressNum" id="broadband_add_ipAddressNum" style="width:200px"></td>
        </tr>
        
        <tr>
        <td>IP地址</td>
        <td><input type="text" name="broadband_add_ipAddress" id="broadband_add_ipAddress" style="width:200px"></td>
        <td>使用部门</td>
        <td><input type="text" name="broadband_add_userDept" id="broadband_add_userDept" style="width:200px"></td>
        </tr>
         
        </tbody>
        </table>
        
    </form>
    
    
    <form id="broadband_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
         <p><input type="hidden" name="broadband_edit_id" id="broadband_edit_id" class="textbox" style="width:200px;"></p>
         <table class="dv-table" style="width:100%;background:#fafafa;padding:5px;margin-top:5px;"> 
        <tbody>
        <tr>
        <td>标识： </td>  
        <td><input type="text" name="broadband_edit_broadbandId" id="broadband_edit_broadbandId" class="textbox" style="width:200px;"></td>
    	<td>停用年月：</td>
    	<td><input type="text" name="broadband_edit_stopDate" id="broadband_edit_stopDate"  style="width:200px"></td>
    	</tr>
   
        <tr>
    	<td>城市：</td> 
        <td><input type="text" name="broadband_edit_city"  id="broadband_edit_city"  class="textbox" style="width:200px;"></td>
        <!--  
    	<td>状态：</td>
    	<td><input type="text" name="broadband_edit_status" class="textbox" style="width:200px;"></td>
    	 -->
    	<td>缴费单位：</td>
    	<td> <input  id="broadband_edit_payOrganization"  id="broadband_edit_payOrganization"  class="textbox" name="broadband_edit_payOrganization" style="width:200px;"></td> 
    	</tr>
    	
    	<tr>
    	<td>场地地址：</td>
    	<td><input type="text" name="broadband_edit_address"  id="broadband_edit_address" class="textbox" style="width:200px;"></td>
    	<td>线路类型：</td>
        <td><input type="text" name="broadband_edit_lineType" id="broadband_edit_lineType" class="textbox" style="width:200px;"></td>
    	</tr>
    	
    	<tr>
        <td>链接地址：</td>
        <td><input type="text" name="broadband_edit_linkAddress" id="broadband_edit_linkAddress" class="textbox" style="width:200px;"></td>
        <td>运营商:</td> 
        <td> <input type="text" name="broadband_edit_operator" id="broadband_edit_operator" class="textbox" style="width:200px;"> </td>
        </tr>
       
        <tr>
        	<td>接入方式: </td>
        	<td> <input type="text" name="broadband_edit_accessWay" id="broadband_edit_accessWay" class="textbox"  style="width:200px;"></td>
        	<td>接入带宽:</td>
        	<td><input type="text" name="broadband_edit_bandwidth" id="broadband_edit_bandwidth" class="textbox"  style="width:200px;"></td>
        </tr>
        
        <tr>
        <td>应缴费用：</td>
        <td> <input  type="text" name="broadband_edit_fee"  id="broadband_edit_fee"  style="width:200px;"></td>
        <td>营改增：</td>
        <td> <input type="text" name="broadband_edit_valueAddTax" id="broadband_edit_valueAddTax" style="width:200px"> </td>
        </tr>
         
        <tr>
        <!-- 
        <td>经办:</td>
        <td><input type="text" name="broadband_edit_agent" id="broadband_edit_agent" class="textbox" style="width:200px;"></td>
         -->
        <td>结算负责：</td>
        <td> <input type="text" name="broadband_edit_director" id="broadband_edit_director" class="textbox"  style="width:200px;"></td>
        <td>付费方式:</td>
        <td><input type="text"  name="broadband_edit_paymentMethod" id="broadband_edit_paymentMethod" class="textbox" style="width:200px;"></td>
        </tr>
        
        <tr>
        <td>结算周期:</td>
        <td> <input type="text"  name="broadband_edit_settlementCycle" id="broadband_edit_settlementCycle" class="textbox" style="width:200px;"></td>
        <td>支付月: </td>
        <td><input type="text" name="broadband_edit_paymentMonth" id="broadband_edit_paymentMonth" class="textbox"  style="width:200px;"></td>
        </tr>
        
        <tr>
        <td>费用归集:</td>
        <td><input type="text" id="broadband_edit_feeCollection" id="broadband_edit_feeCollection" name="broadband_edit_feeCollection" class="textbox"  style="width:200px;"></td>
        
        <!-- 
        <tr>
        <td>利用率: </td>
        <td><input type="text"   name="broadband_edit_useRatio" id="broadband_edit_useRatio" class="easyui-numberbox" value = 0  style="width:200px;"></td>
        <td>网络使用:</td>
        <td> <input type="text"  name="broadband_edit_networkUsage" id="broadband_edit_networkUsage" class="textbox"  style="width:200px;"></td>
        </tr>
          -->
        <td>到期日期</td>
        <td><input type="text" name="broadband_edit_dueDate" id="broadband_edit_dueDate" style="width:200px;"></td>
        </tr>
        
        <tr>
        <td>线路用途</td>
        <td><input type="text" name="broadband_edit_lineUsage" id="broadband_edit_lineUsage" style="width:200px;"></td>
        <td>IP地址数量</td>
        <td><input type="text" name="broadband_edit_ipAddressNum" id="broadband_edit_ipAddressNum" style="width:200px"></td>
        </tr>
        
        <tr>
        <td>IP地址</td>
        <td><input type="text" name="broadband_edit_ipAddress" id="broadband_edit_ipAddress" style="width:200px"></td>
        <td>使用部门</td>
        <td><input type="text" name="broadband_edit_userDept" id="broadband_edit_userDept" style="width:200px"></td>
        </tr>  
          
          
        </tbody>
        </table>
   
    </form>
    
    <!-- 
    <from id="braodband_fileupload" style="margin:0;padding:5px 0 0 25px;color:#333">
         <p> 文件： <input type="file"  name="fileToUpload" class="input" style="width:200px"></p>
         
    </from>
    
     -->
     <!--  
     <div id="broadband_fileupload" class="easyui-window">
      	<p> 文件： <input type="file"  id="fileToUpload" name="fileToUpload" class="input" style="width:200px"></p>
     </div>
     -->
     
     <style type="text/css">
     
    
     
     </style>