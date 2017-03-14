package com.etop.utils;

import java.lang.reflect.Field;
import java.util.Iterator;


import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.PrimaryKey;
import org.hibernate.mapping.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class EntityUtil {
	
	
	
	private  Configuration hibernateConf;  
	
	private SpringContextHolder springContextHolder;
	
	private LocalSessionFactoryBean sessionFactory;
	
	 private  Configuration getHibernateConf() {  
		 if(null == hibernateConf){
			SpringContextHolder springContextHolder = new SpringContextHolder();
			sessionFactory = springContextHolder.getApplicationContext().getBean(LocalSessionFactoryBean.class);
	        //LocalSessionFactoryBean configBean = (LocalSessionFactoryBean)sessionFactory;
	        hibernateConf = sessionFactory.getConfiguration();
		 }
	  
	  return hibernateConf;  
	 }  
	  
	 private  PersistentClass getPersistentClass(Class<?> clazz) {  
	  synchronized (EntityUtil.class) {  
	   PersistentClass pc = getHibernateConf().getClassMapping(  
	     clazz.getName());  
	   if (pc == null) {  
	//  hibernateConf = getHibernateConf().addClass(clazz);//*.hbm.xml方式  
	    pc = getHibernateConf().getClassMapping(clazz.getName());  
	  
	   }  
	   return pc;  
	  }  
	 }  
	  
	 /**  
	  * 功能描述：获取实体对应的表名  
	  *   
	  * @param clazz  
	  *            实体类  
	  * @return 表名  
	  */  
	 public  String getTableName(Class<?> clazz) {  
	  return getPersistentClass(clazz).getTable().getName();  
	 }  
	  
	 /** 
	  * 功能描述：获取实体对应表的主键字段名称，只适用于唯一主键的情况 
	  *  
	  * @param clazz 
	  *            实体类 
	  * @return 主键字段名称 
	  */  
	 public String getPrimaryKey(Class<?> clazz) {  
	  
	  return getPrimaryKeys(clazz).getColumn(0).getName();  
	  
	 }  
	   
	 /** 
	  * 功能描述：获取实体对应表的主键字段名称 
	  *  
	  * @param clazz 
	  *            实体类 
	  * @return 主键对象primaryKey ，可用primaryKey.getColumn(i).getName() 
	  */  
	 public  PrimaryKey getPrimaryKeys(Class<?> clazz) {  
	  
	  return getPersistentClass(clazz).getTable().getPrimaryKey();  
	  
	 }  
	  
	 /** 
	  * 功能描述：通过实体类和属性，获取实体类属性对应的表字段名称 
	  *  
	  * @param clazz 
	  *            实体类 
	  * @param propertyName 
	  *            属性名称 
	  * @return 字段名称 
	  */  
	 public  String getColumnName(Class<?> clazz, String propertyName) {  
	  PersistentClass persistentClass = getPersistentClass(clazz);  
	  Property property = persistentClass.getProperty(propertyName);  
	  Iterator<?> it = property.getColumnIterator();  
	  if (it.hasNext()) {  
	   Column column = (Column) it.next();  
	   return column.getName();  
	  }  
	  return null;  
	 }  
	 
	 public  String[]  getColumnNames(Class<?> clazz){
		 Field [] fields = clazz.getDeclaredFields();
		 String[] columnNames = new String[fields.length];
		 
		 Field field = null;
		 int j=0;
		 for(int i=0; i<fields.length; i++){
			  field = fields[i];
			  
			  if("id".equals(field.getName())){
				  continue;
			  }
			  columnNames[j++] = getColumnName(clazz, field.getName());
			 
		 }
		 return columnNames;		 
		 
	 }
	  
}
