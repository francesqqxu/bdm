package com.etop.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.etop.dao.BroadbandDAO;
import com.etop.dao.FeeConfirmDAO;
import com.etop.dao.FeeConfirmReportDAO;
import com.etop.dto.FeeConfirmDto;
import com.etop.dto.FeeConfirmReportDto;
import com.etop.pojo.Broadband;
import com.etop.pojo.FeeConfirm;
import com.etop.pojo.FeeConfirmReport;
import com.etop.utils.PageUtil;

/**
 * 
 * @author frances.xu
 *
 */
@Service("FeeConfirmService")
public class FeeConfirmService {

	 private final static Logger log = Logger.getLogger(FeeConfirmService.class);
	 Map<String , Object>  map = new HashMap<String, Object>();
     private final static int MONTH_PER_YEAR = 12;
	 private final static String rptTypeStaticInfo = "rptInfo";
	 private final static String rptTypeMonthFee = "rptTotal";
	
     
	 @Autowired
	 private FeeConfirmDAO  feeConfirmDAO;
	 
	 @Autowired
	 private FeeConfirmReportDAO feeConfirmReportDAO;
	 
	 @Autowired
	 private BroadbandService broadbandService;
	 
	 @Autowired
	 private BroadbandDAO broadbandDAO;
	 
		public PageUtil<FeeConfirmDto> getFeeConfirmSponsor(String order, int page, int rows, String sort, String feeDate){
		    Map<String , Object> params = new HashMap<String, Object>();
		    Object[] args = new Object[] {feeDate};
		    log.info(feeDate);

		    String sql = "select count(*) from t_broadbandFee where feeDate = ?";
		    int  feeConfirmNum = feeConfirmDAO.uniqueResult(sql, args);
		    
		    int  first = rows * ( page -1 );

		    if(feeConfirmNum == 0 ){
		    	
	     	    args = new Object[] {feeDate.substring(4,6)};
	     	    log.info(args[0].toString());
		 	        int iYear = Integer.parseInt(feeDate.substring(0,4));
				    int  iMonth = Integer.parseInt(feeDate.substring(4,6));
				    params.put("iYear", iYear);
				    params.put("iMonth", iMonth);
				    params.put("MONTHPERYEAR",MONTH_PER_YEAR);
				    params.put("feeDate", feeDate);
				    log.info(params.get("iMonth"));
				    log.info(params.get("feeDate"));
//					sql = "select  id, broadbandId,"   
//							+":feeDate as feeDate , fee,  valueAddTax "
//							//+ "CAST( EXTRACT(YEAR_MONTH FROM NOW()) AS CHAR(6))   as feeDate, fee"
//							+ ",  cast(fee / (1 + case when valueAddTax  is null then 0 else valueAddTax end) as decimal(10, 2)) as afterTaxFee "
//							+ ", agent, director "
//							+ " from t_broadband  where  stopDate is null and (settlementCycle = 1 or( (paymentMonth + settlementCycle) % :MONTHPERYEAR) = :iMonth "
//							+ "  or (settlementCycle = 12 and paymentMonth = :iMonth))"
//							+ " order by " + " "  + sort + " "  + order + "  limit " + first  + ","  + rows;
					
				    
				    sql = " select id, broadbandId, feeDate, fee, valueAddTax  ,afterTaxFee   ,agent, director "
				           + " from  "
				           + " ( "
				           + "  select  tb.id, tb.broadbandId, "
				           +" :feeDate as feeDate , tb.fee,  tb.valueAddTax  "
				           +" ,  cast( tb.fee / (1 + case when tb.valueAddTax  is null then 0 else tb.valueAddTax end) as decimal(10, 2)) as afterTaxFee  "
				           +" , tb.agent, tb.director  "
				           +"  from (select * from t_broadband where broadbandId not in (select distinct broadbandId from t_broadband_special)) as tb "
				           +" where  tb.stopDate is null and (tb.settlementCycle = 1 or( (tb.paymentMonth + tb.settlementCycle) % :MONTHPERYEAR) = :iMonth "
				           + " or (tb.settlementCycle = 12 and tb.paymentMonth = :iMonth)) " 
         				   + " union ( "
				           + " select  a.id, a.broadbandId, :feeDate as  feeDate, b.begFee + b.annualFee as fee, a.valueAddTax "
				           + " , cast( (b.begFee+b.annualFee) / (1 + case when a.valueAddTax  is null then 0 else a.valueAddTax end) as decimal(10, 2)) as afterTaxFee  "
				           +"  ,a.agent, a.director  "
				           + " from t_broadband  a, t_broadband_special b "
				           + " where a.broadbandId = b.broadbandId "
				           + " and  a.stopDate is null and (b.payYear =:iYear and b.payMonth = :iMonth) "
				           + " and  (b.begFee + b.annualFee <> 0 ) "
				           + " ) "
          				   + "  ) as aa "
          				   + " order by " + " "  + sort + " "  + order + "  limit " + first  + ","  + rows;
				    
				    
//					String countSql = "select count(*)  from t_broadband  where  stopDate is null "
//							+ "and (settlementCycle = 1 or( (paymentMonth + settlementCycle) % :MONTHPERYEAR) = :iMonth "
//							+ "  or (settlementCycle = 12 and paymentMonth = :iMonth))";
				   String countSql = " select count(*) "
					+ " from  "
				    + " ( "
				    + " select tb.broadbandId "
				    + " from (select * from t_broadband where broadbandId not in "
				    +" (select DISTINCT broadbandId from t_broadband_special)) as tb  "
				    +" where  tb.stopDate is null  "
				    +" and (tb.settlementCycle = 1 or( (tb.paymentMonth + tb.settlementCycle) % :MONTHPERYEAR) = :iMonth "
				    +" or (tb.settlementCycle = 12 and tb.paymentMonth = :iMonth)) "
				    +"  union "
				    +"  select a.broadbandId "
				    +" from t_broadband a , t_broadband_special b "
				    + "where a.broadbandId = b.broadbandId "
				    +" and a.stopDate is null and (b.payYear =:iYear and b.payMonth =:iMonth) "
				    + " and  (b.begFee + b.annualFee <> 0 ) "
				    +" ) as aa ";
				    
				    
					 Map<String , Object> count_params = new HashMap<String, Object>();
					 count_params.put("iYear",iYear);
					 count_params.put("iMonth", iMonth);
					 count_params.put("MONTHPERYEAR",MONTH_PER_YEAR);
			        return feeConfirmDAO.findBySql_Sql(sql, countSql,null, params, count_params, FeeConfirmDto.class, false);
		 		
			 }
		    else {
		    	
			    String sWhere= "";
			    if(null != feeDate&& !"".equals(feeDate)){
					sWhere +=  " and feeDate like '%" +feeDate + "%'";
				}
			    sql = "select broadbandId, feeDate, fee, valueAddTax,  agent, director  from t_broadbandFee where 1=1 " + sWhere + 
			    		" order by " + " " + sort + " " + order + " limit " + first + "," + rows;
		    	return feeConfirmDAO.findBySql(sql,null,FeeConfirmDto.class,false);
			    
		    }
	 
		}
		
	 
	 
	public Object addFeeConfirmSponsor(String feeDate){
		 Map<String , Object> params = new HashMap<String, Object>();
	    Object[] args = new Object[] {feeDate};
	    log.info(feeDate);
      
		    String sql = "select count(*) from t_broadbandFee where feeDate = ?";
		    int  feeConfirmNum = feeConfirmDAO.uniqueResult(sql, args);
		    int iYear = Integer.parseInt(feeDate.substring(0,4));
		    int  iMonth = Integer.parseInt(feeDate.substring(4,6));
		    if(feeConfirmNum == 0 ){
			    args = new Object[]  {feeDate, MONTH_PER_YEAR, iMonth, iMonth, feeDate,iYear, iMonth};
//			    sql = "insert into t_broadbandFee (broadbandId, feeDate, fee, valueAddTax,afterTaxFee, agent, director )"
//		 				  + " select   broadbandId,"   
//							+" ?   , fee, valueAddTax "
//							+ ",  cast(fee / (1 + case  when valueAddTax is  null then 0 else valueAddTax end) as decimal(10, 2)) as afterTaxFee "
//							+ ", agent, director "
//							+ " from t_broadband  where  stopDate is null and (settlementCycle = 1 or( (paymentMonth + settlementCycle) % ?) = ? "
//							+ "  or (settlementCycle = 12 and paymentMonth = ?))  order by director";
		 		sql = "insert into t_broadbandFee (broadbandId, feeDate, fee, valueAddTax,afterTaxFee, agent, director )"
		 				   + " select  broadbandId, feeDate, fee, valueAddTax  ,afterTaxFee   ,agent, director "
				           + " from  "
				           + " ( "
				           + "  select  tb.id, tb.broadbandId, "
				           +"  ?  as  feeDate , tb.fee,  tb.valueAddTax  "
				           +" ,  cast( tb.fee / (1 + case when tb.valueAddTax  is null then 0 else tb.valueAddTax end) as decimal(10, 2)) as afterTaxFee  "
				           +" , tb.agent, tb.director  "
				           +"  from (select * from t_broadband where broadbandId not in (select distinct broadbandId from t_broadband_special)) as tb "
				           +" where  tb.stopDate is null and (tb.settlementCycle = 1 or( (tb.paymentMonth + tb.settlementCycle) % ?) =? "
				           + " or (tb.settlementCycle = 12 and tb.paymentMonth =?)) " 
       				   + " union ( "
				           + " select  a.id, a.broadbandId, ? as  feeDate, b.begFee + b.annualFee as fee, a.valueAddTax "
				           + " , cast( (b.begFee+b.annualFee) / (1 + case when a.valueAddTax  is null then 0 else a.valueAddTax end) as decimal(10, 2)) as afterTaxFee  "
				           +"  ,a.agent, a.director  "
				           + " from t_broadband  a, t_broadband_special b "
				           + " where a.broadbandId = b.broadbandId "
				           + " and  a.stopDate is null and (b.payYear =? and b.payMonth = ?) "
				           + " and  (b.begFee + b.annualFee <> 0 ) "
				           + " ) "
        				   + "  ) as aa ";
			    
			    
			    
		 		 feeConfirmDAO.executeSql(sql, args);
		    }	
		    else {
 	    	
	 	    	args = new Object[] {feeDate};
	 	    	
	 	    	sql = "delete from t_broadbandFee where  feeDate = ?";
	 	    	feeConfirmDAO.executeSql(sql, args);
	 	    	
	 	    	args = new Object[]  {feeDate, MONTH_PER_YEAR, iMonth, iMonth, feeDate,iYear, iMonth};
	   	    
//		 		sql = "insert into t_broadbandFee (broadbandId, feeDate,  fee, valueAddTax, afterTaxFee, agent, director)"
//		 				  + " select   broadbandId,"   
//							+" ?   , fee, valueAddTax "
//							+ ",  cast(fee / (1 + case  when valueAddTax is  null then 0 else valueAddTax end) as decimal(10, 2)) as afterTaxFee "
//							+ ", agent, director "
//							+ " from t_broadband  where  stopDate is null and (settlementCycle = 1 or( (paymentMonth + settlementCycle) % ?) = ? "
//							+ "  or (settlementCycle = 12 and paymentMonth = ?))  order by director";
	 	    	sql = "insert into t_broadbandFee (broadbandId, feeDate, fee, valueAddTax,afterTaxFee, agent, director )"
		 				   + " select  broadbandId, feeDate, fee, valueAddTax  ,afterTaxFee   ,agent, director "
				           + " from  "
				           + " ( "
				           + "  select  tb.id, tb.broadbandId, "
				           +"  ?  as  feeDate , tb.fee,  tb.valueAddTax  "
				           +" ,  cast( tb.fee / (1 + case when tb.valueAddTax  is null then 0 else tb.valueAddTax end) as decimal(10, 2)) as afterTaxFee  "
				           +" , tb.agent, tb.director  "
				           +"  from (select * from t_broadband where broadbandId not in (select distinct broadbandId from t_broadband_special)) as tb "
				           +" where  tb.stopDate is null and (tb.settlementCycle = 1 or( (tb.paymentMonth + tb.settlementCycle) % ?) =? "
				           + " or (tb.settlementCycle = 12 and tb.paymentMonth =?)) " 
    				   + " union ( "
				           + " select  a.id, a.broadbandId, ? as  feeDate, b.begFee + b.annualFee as fee, a.valueAddTax "
				           + " , cast( (b.begFee+b.annualFee) / (1 + case when a.valueAddTax  is null then 0 else a.valueAddTax end) as decimal(10, 2)) as afterTaxFee  "
				           +"  ,a.agent, a.director  "
				           + " from t_broadband  a, t_broadband_special b "
				           + " where a.broadbandId = b.broadbandId "
				           + " and  a.stopDate is null and (b.payYear =? and b.payMonth = ?)"
				           + " and (b.begFee + b.annualFee <> 0)  "
				           + " ) "
     				   + "  ) as aa "; 
	 	    	
		 		 feeConfirmDAO.executeSql(sql, args);
		    }
		    map.put("success", true);
		    return map;
	    
	}
	
	public Object addFeeConfirms(){
		
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
	    Object[] params  = new Object []{ String.valueOf(year) + String.format("%02d", month)};
	    
	    String sql = "select count(*) from t_broadbandFee where feeDate = ?";
	    if(feeConfirmDAO.uniqueResult(sql, params) > 0 ){
	    	map.put("success", true); 
	    	return map;
	    };
	    
	    params = new Object[] {String.format("%02d", month)};
	    log.info("params: " + params);
		sql = "insert into t_broadbandFee (broadbandId, feeDate, fee, agent, director)"
				+ " select   broadbandId,CAST( EXTRACT(YEAR_MONTH FROM NOW()) AS CHAR(6)) as feeDate"
				+ ", fee, agent, director "
				+ " from t_broadband  where settlementCycle = 1 or settlementCycle = ?";
		 
		 feeConfirmDAO.executeSql(sql, params);
		 //sql = "update t_broadbandFee set submitted = 1 where year=? and month =?";
		 //params = new Object[] { String.valueOf(year), String.valueOf(month)};
		 //feeConfirmDAO.executeSql(sql, params, new int[]{Types.VARCHAR, Types.VARCHAR});
		 map.put("success", true);
		 return map;
	}
	
    public Object deleteFeeConfirms(String feeDate) {
    	
    	//Object[] params = new Object[] {feeDate};
        Map<String , Object> params = new HashMap<String, Object>();
       params.put("feeDate", feeDate);
    	 
    	String sql = "delete FeeConfirm f where  f.feeDate = :feeDate";
    	feeConfirmDAO.excute(sql, params);
    	map.put("success", true); 
    	return map;
    	 
    }
    
    
    public int checkFeeConfirmExists() {
    	Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
	    Object[] params  = new Object []{ String.valueOf(year)+String.format("%02d", month) };
	    
	    String sql = "select count(*) from t_broadbandFee where feeDate = ?";
	    return feeConfirmDAO.uniqueResult(sql, params);
	    	
    }
    
    
    public PageUtil<FeeConfirmDto> findFeeConfirms(String order, int  page, int rows, String sort, String feeDate, String chineseName) {
		String sql = "";
		int  first = rows * ( page -1 );
		
		String  sWhere = "";
		if(null != feeDate&& !"".equals(feeDate)){
			sWhere +=  " and feeDate like '%" +feeDate + "%'";
		}
		if(null != chineseName && !"".equals(chineseName)){
			sWhere += " and director like '%" +chineseName + "%'";
			
		}
		sql = "select id,  broadbandId, feeDate, fee, valueAddTax,afterTaxFee, agent, director, changedFee, reason, useRatio"
				+", case when confirmed =0 then '未确认' else '已确认' end as confirmed "
				+ " from t_broadbandFee where 1=1 " 
		      + sWhere +  " order by " + " " + sort + " " + order + " limit " + first + "," + rows;
        log.info(sql);
		return feeConfirmDAO.findBySql(sql, FeeConfirmDto.class, false);
	}
    
    public Object updateFeeConfirm(FeeConfirmDto feeConfirmDto){
    	FeeConfirm feeConfirm = findFeeConfirmById(feeConfirmDto.getId());
	    if(null == feeConfirm || "".equals(feeConfirm)){
	    	map.put("success", false);
	    	return map;
	    }
	    BeanUtils.copyProperties(feeConfirmDto, feeConfirm);
	    BigDecimal  afterTaxFee = BigDecimal.ZERO;
	    BigDecimal changedFee = feeConfirmDto.getChangedFee();
	    BigDecimal  valueAddTax = feeConfirmDto.getValueAddTax();
	    if(null != changedFee && "" != changedFee.toString()){
	    	if(!changedFee.equals(BigDecimal.ZERO)) {
	    	   if(null !=valueAddTax && "" != valueAddTax.toString()){
	    		   valueAddTax = valueAddTax.add(BigDecimal.ONE);
	    		   afterTaxFee = changedFee.divide(valueAddTax, 2,BigDecimal.ROUND_HALF_UP);
	    	   }
	    	   else {
	    		   afterTaxFee = changedFee;
	    	   }
	    		feeConfirm.setAfterTaxFee(afterTaxFee);
	    	}
	    }
	    feeConfirm.setConfirmed(0);
	    Broadband broadband =  broadbandService.findBroadbandByBroadbandId(feeConfirmDto.getBroadbandId());
	    if(null == broadband || "".equals(broadband)){
	    	map.put("success", false);
	    	return map;
	    }
	   // broadband.setFee(feeConfirmDto.getChangedFee());
	   //broadband.setValueAddTax(feeConfirmDto.getValueAddTax());
	    broadband.setUseRatio(feeConfirmDto.getUseRatio());
	    broadband.setNetworkUsage(feeConfirmDto.getReason());
	    
	    if(!broadband.getSettlementCycle().equals("1") && !broadband.getSettlementCycle().equals("12")){
	    	broadband.setPaymentMonth("0");
	    }
	    feeConfirmDAO.update(feeConfirm);
	    broadbandDAO.update(broadband);
     
    	map.put("success", true);
    	return map;
    }
    
    public FeeConfirm findFeeConfirmById(int id){
    	Map<String , Object> params = new HashMap<String, Object>();
		params.put("id",id);
		String sql = " from FeeConfirm f where f.id = :id";
		return feeConfirmDAO.findUniqueResult(sql, params);
    }
    
    public Object updateConfirm(String ids, String feeDate){
    	log.info(ids);
		String[]  idArr = ids.split(",");
		Integer[] idIArr = new Integer[idArr.length];
		for(int i=0; i <idArr.length; i++){
			idIArr[i] = Integer.parseInt(idArr[i]);
		}
		Map<String, Object >  params = new HashMap<String, Object>();
		params.put("ids", idIArr);
		String sql  = "update FeeConfirm set confirmed = 1 where id in (:ids)";
		int result = feeConfirmDAO.excute(sql, params);
		/*update t_broadband paymentMonth field according to  feeDate
		 * 
		 */
		int iMonth =Integer.parseInt( feeDate.substring(4,6));
		for(int i = 0; i <idIArr.length; i++) {
			FeeConfirm feeConfirm = findFeeConfirmById(idIArr[i]);
			Broadband  broadband = broadbandService.findBroadbandByBroadbandId(feeConfirm.getBroadbandId());
			 if(!broadband.getSettlementCycle().equals("1") && !broadband.getSettlementCycle().equals("12")){
			    	broadband.setPaymentMonth( String.valueOf( Integer.parseInt( feeConfirm.getFeeDate().substring(4,  6))));
			 }
			 broadbandDAO.update(broadband);
		}
		
		map.put("success",true);
		return map;
    }
    
    public PageUtil<FeeConfirmReportDto> getFeeConfirmReport(String order, int  page, int rows, String sort, String dateFrom, String dateTo, String rptType) {
		String sql = "";
		int  first = rows * ( page -1 );
		log.info(dateFrom.substring(0,4));
		log.info(dateFrom.substring(5,7));
		String dateBegin = dateFrom.substring(0,4)+dateFrom.substring(5,7);
		String dateEnd = dateTo.substring(0,4) + dateTo.substring(5,7);
		
		String  sWhere = "";
		if(null != dateBegin && !"".equals(dateBegin)){
			sWhere =  " and  b.feeDate between '" + dateBegin + "'";
		}
		if(null != dateEnd&& !"".equals(dateEnd)){
			sWhere +=  " and   '" + dateEnd + "'";
		}
		
		 
		
		sort = sort  + " " + order + ",  broadbandId asc ";
		 
		if(rptType.equals( rptTypeMonthFee)){
		sql =    " select  (case when b.confirmed is null then '***' else case when  b.confirmed=0 then '未确认' else '已确认' end  end) as confirmed"
					+ ", a.broadbandId, date_format(a.stopDate, '%Y-%m-%d') as stopDate, a.city, a.`status`, a.payOrganization "
					+  "  ,a.address, a.lineType, a.operator,  a.bandwidth "
					+ " , a.fee, a.valueAddTax,  a.director, a.paymentMethod, a.settlementCycle "
					+ ", case when a.settlementCycle =1 then null else a.paymentMonth end as paymentMonth, a.feeCollection, case b.changedFee when 0.00 then b.fee else b.changedFee end  as changedFee, b.afterTaxFee "
					+ " from t_broadband  a "
					+ " left join   t_broadbandfee  b "
					+ " on  a.broadbandId = b.broadbandId "
				    + sWhere +  " order by " + " " + sort  + " limit " + first + "," + rows;
		}
		else if (rptType.equals(rptTypeStaticInfo )){
			sql =    " select  "
					+ "  a.broadbandId, date_format(a.stopDate, '%Y-%m-%d') as stopDate, a.city, a.`status`, a.payOrganization "
					+  "  ,a.address, a.lineType, a.linkAddress,  a.operator, a.accessWay, a.bandwidth "
					+ " , a.fee, a.valueAddTax, a.agent, a.director, a.paymentMethod, a.settlementCycle "
					+ ", case when a.settlementCycle =1 then null else a.paymentMonth end as paymentMonth, a.feeCollection "
					+ " from t_broadband  a "
//					+ " left join   t_broadbandfee  b "
//					+ " on  a.broadbandId = b.broadbandId "
				    +  " order by " + " " + sort  + " limit " + first + "," + rows;
		}
        log.info(sql);
		return feeConfirmDAO.findBySql(sql, FeeConfirmReportDto.class, false);
	}
    
    public  Object checkFeeConfirmReport(String dateFrom, String dateTo){
	    
	     Map<String, Object > params = new HashMap<String , Object>();
	     
	     String dateBegin = dateFrom.substring(0,4)+dateFrom.substring(5,7);
		String dateEnd = dateTo.substring(0,4) + dateTo.substring(5,7);
	     params.put("dateFrom", dateBegin);
	     params.put("dateTo", dateEnd);
	     String sql = "from  FeeConfirm  t where  t.confirmed <>  1 and  t.feeDate between  :dateFrom and :dateTo ";
	     int count =  feeConfirmDAO.getTotalCount(sql, params);
	     if ( count > 0 ){
	    	 map.put("success", false);
	     }else {
	    	 map.put("success", true);
	     }
	     return map;
}

    
    public List<FeeConfirmReport> getFeeConfirmReportList(String dateFrom, String dateTo, String rptType){
    	    
    	     Map<String, Object > params = new HashMap<String , Object>();
    	     
    	     String dateBegin = dateFrom.substring(0,4)+dateFrom.substring(5,7);
    		String dateEnd = dateTo.substring(0,4) + dateTo.substring(5,7);
    	     params.put("dateFrom", dateBegin);
    	     params.put("dateTo", dateEnd);
    	     
    	     String  sWhere = "";
    			if(null != dateBegin && !"".equals(dateBegin)){
    				sWhere =  " and  b.feeDate between '" + dateBegin + "'";
    			}
    			if(null != dateEnd&& !"".equals(dateEnd)){
    				sWhere +=  " and   '" + dateEnd + "'";
    			}
    		   String sql = "";
//    			String sql =    " select a.id,  a.broadbandId, date_format(a.stopDate, '%Y-%m-%d') as stopDate, a.city, a.status, a.payOrganization "
//    						+  "  ,a.address, a.lineType, a.operator, a.accessWay, a.bandwidth "
//    						+ " , a.fee, a.valueAddTax, a.agent, a.director, a.paymentMethod, a.settlementCycle "
//    						+ ", a.paymentMonth, a.feeCollection, case b.changedFee when 0.00 then b.fee else b.changedFee end  as changedFee, b.afterTaxFee "
//    						+ " from t_broadband  a "
//    						+ " left join   t_broadbandfee  b "
//    						+ " on  a.broadbandId = b.broadbandId "
//    						+ "  and  b.confirmed = 1 "
//    						+ sWhere +  " order by a.broadbandId ";
    			if(rptType.equals(rptTypeMonthFee)){
    				sql = " select id,   broadbandId,   city,  status,  payOrganization   "
    			         + " , address,  lineType,  operator,   bandwidth  "
    			         + " ,  fee,  valueAddTax,  director,  paymentMethod,  settlementCycle  "
    			         + " ,  paymentMonth,  feeCollection,   changedFee,  afterTaxFee " 
    			         + " from  "
    			         + " ( select a.id,  a.broadbandId,   a.city, a.status, a.payOrganization  "
    			         + " ,a.address, a.lineType, a.operator,   a.bandwidth  "
    			         + " , a.fee, a.valueAddTax,  a.director, a.paymentMethod, a.settlementCycle  "
    			         + " , a.paymentMonth, a.feeCollection, case b.changedFee when 0.00 then b.fee else b.changedFee end  as changedFee"
    			         + ", b.afterTaxFee  "
    			         + " from (select * from  t_broadband t where t.stopDate is Null)   a "  
    			         + " left join   t_broadbandfee  b "  
    			         + " on  a.broadbandId = b.broadbandId "  
    			         + " and  b.confirmed = 1 "
    			         + sWhere  
    					 + " union  "
    					 + " select   1,  'aaa', ' ' , ' ', ' ' "  
    					 + ",' ', ' ', ' ',  ' ' "  
    					 + ",0, 0,  'zzz', ' ', ' ' " 
    					 + ", ' ', ' ', 0"
    					 + ", 0  "
    					 + " from dual "
    					 + " union  "  
    					 + " select  1,  'bbb',  ' ' , ' ', ' ' "  
    					 + ",' ',  ' ', ' ', ' ' "  
    					 + ",0, 0,  'zzz', ' ', ' ' " 
    					 + ", ' ', a.feeCollection,sum( case b.changedFee when 0.00 then b.fee else b.changedFee end ) as changedFee"
    					 + ", sum(b.afterTaxFee) as afterTaxFee  "
    					 + " from t_broadband  a "
    					 + " left join   t_broadbandfee  b "  
    					 + " on  a.broadbandId = b.broadbandId "  
    					 + " and  b.confirmed = 1 "  
    					 +  sWhere  	
    					 + " group by a.feeCollection "
    					 + " ) as  feeReport " 
    					 + " order by director desc,  broadbandId asc ";   
    			}
    			else if (rptType.equals(rptTypeStaticInfo)){
    				sql =    " select  "
    						+ "  a.broadbandId, date_format(a.stopDate, '%Y-%m-%d') as stopDate, a.city, a.`status`, a.payOrganization "
    						+  "  ,a.address, a.lineType,  a.linkAddress, a.operator, a.accessWay, a.bandwidth "
    						+ " , a.fee, a.valueAddTax, a.agent, a.director, a.paymentMethod, a.settlementCycle "
    						+ ", case when a.settlementCycle =1 then null else a.paymentMonth end as paymentMonth, a.feeCollection "
    						+ " from t_broadband  a "
//    						+ " left join   t_broadbandfee  b "
//    						+ " on  a.broadbandId = b.broadbandId "
    					    +  " order by director desc,  broadbandId asc ";   
    			}
    	        log.info(sql);
    	        
    	       return feeConfirmReportDAO.findFeeConfirmReport(sql);
    }
    
    
}
