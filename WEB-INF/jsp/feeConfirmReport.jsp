<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src='${demoPath}static/js/app/feeConfirmReport.js'></script>
    <table id="feeConfirmReport"> </table>
    
    <div id="feeConfirmReport_tool" style="padding:5px;">
    	
    	 
    	<div style="padding:0 0 0 7px;color:#333;">
    	
    	 <form method="post" action="<c:url value='feeConfirm/exportFeeConfirm.html'/>">
    	    <table>
    	    <tr>
    	    <!-- 
    	    <td>
    	    <input type="hidden" name="rptCheck" id="rptCheck" value="0">
    	    </td>
    	     -->
            <td>         
    	            宽带费用时间从：    <input type="text"  name="dateFrom" id = "dateFrom" >  
    	    </td>
    	    <td>         
    	             到：                    <input type="text" name="dateTo"  id="dateTo" >
    	    </td>
    	    <!-- 
    	    <td>
    	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls: 'icon-search' ,iconAlign: 'left'"  
   	       		id="feeConfirmReport_tool_check">检查费用是否已确认</a>
   	        </td>
   	         -->
   	        <!-- 
   	        <td>
   	                        报表类型选项(信息表/月费支付汇总表): <input type="checkbox" name="rptCheck" id="rptCheck" value="1" checked="checked">
   	        </td>
   	        -->
   	        <td>
   	                        报表类型选项： <input type="radio" name="rptType"  id="rptInfo" value="rptInfo">信息表
   	                  <input type="radio" name="rptType"  id="rptTotal" value="rptTotal" checked="checked">月费支付汇总表
   	        </td>
   	        
   	        <td>
    	    <a href="javascript:void(0)" class="easyui-linkbutton"  id="feeConfirmReport_tool_search">查询</a>
    	    </td>
    	    <!--  
    	    <td>
   	                        费用未确认选项： <input type="checkbox"  name="rptCheck" id = "rptCheck">
   	        </td>
   	        -->
    	    <td>
    	    <input  type="submit" value="下载报表"/>
    	    </td>
    	    </tr>
    	</table>
    	</form>
     
    	</div> 
    
    </div>
   
   
   