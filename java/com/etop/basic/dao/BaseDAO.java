package com.etop.basic.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.util.Assert;

import com.etop.utils.PageUtil;
import com.etop.utils.ThreadLocalUtils;



/**
 * @类名: BaseDAO
 * @描述: TODO(这里用一句话描述这个类的作用)
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 下午3:12:00
 * @param <T>
 */
public class BaseDAO<T> implements Serializable {

	protected transient Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> persistentClass;

	protected Class<T> getCurClass() {
		if (persistentClass == null) {
			this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return persistentClass;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected StatelessSession getStatelessSession() {
		return sessionFactory.openStatelessSession();
	}

	public void flush() {
		getSession().flush();
	}

	public void evict(T entity) {
		getSession().evict(entity);
	}

	public T get(long id) throws DataAccessException {
		log.debug("DAO:Get entity " + getCurClass().getSimpleName() + ":Id="
				+ id);
		return (T) getSession().get(getCurClass(), id);
	}

	public void save(T entity) throws DataAccessException {
		log.debug("DAO:Save entity " + entity.getClass().getSimpleName());
		getSession().save(entity);
	}

	public void update(T entity) throws DataAccessException {
		log.debug("DAO:Update entity " + entity.getClass().getSimpleName());
		//getSession().clear();
		getSession().update(entity);
	}

	public void saveOrUpdate(T entity) throws DataAccessException {
		log.debug("DAO:Sava or Update entity "
				+ entity.getClass().getSimpleName());
		getSession().clear();
		getSession().saveOrUpdate(entity);
	}

	public void delete(T entity) throws DataAccessException {
		log.debug("DAO:delete entity " + getCurClass().getSimpleName());
		getSession().delete(entity);
	}

	public void deleteById(long id) throws DataAccessException {
		log.debug("DAO:delete entity " + getCurClass().getSimpleName() + ":Id="
				+ id);
		String queryString = "delete from " + getCurClass().getSimpleName()
				+ " where id=" + id;
		this.excute(queryString);
	}

	public int excute(String queryString) throws DataAccessException {
		log.debug("DAO:Excute HQL update :" + queryString);
		try {
			Query query = getSession().createQuery(queryString);
			return query.executeUpdate();
		}catch (Exception ex){
			throw new DataRetrievalFailureException("数据库执行错误， 请联系管理人员！");
		}
	}

	public List<T> find(String queryString) throws DataAccessException {
		log.debug("DAO:Running HQL query :" + queryString);
		Query query = getSession().createQuery(queryString);
		query.setCacheable(true);
		return query.list();
	}

	private Query createQuery(String queryString, Map<String, Object> params,
			int startRow, int pageSize) {
		Query query = getSession().createQuery(queryString);
		if (params != null) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				String paramName = entry.getKey();
				Object obj = entry.getValue();
				log.info("DAO:set param:" + paramName + " with value:" + obj);
				if (obj instanceof List) {
					query.setParameterList(paramName, (Collection) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(paramName, (Object[]) obj);
				} else {
					query.setParameter(paramName, obj);
				}
			}
		}
		query.setCacheable(true);
		if (pageSize != -1) {
			query.setFirstResult(startRow).setMaxResults(pageSize);
		}
		return query;
	}

	private Query createQuery(String queryString) {
		return createQuery(queryString, null, 0, -1);
	}

	private Query createQuery(String queryString, Map<String, Object> params) {
		return createQuery(queryString, params, 0, -1);
	}

	public List<T> find(String queryString, int startRow, int pageSize)
			throws DataAccessException {
		log.debug("DAO:Running HQL query by page:" + queryString);
		Query query = createQuery(queryString, null, startRow, pageSize);
		return query.list();
	}

	public int getTotalCount(String queryString) throws DataAccessException {
		return getTotalCount(queryString, null);
	}

	public int getTotalCount(String queryString, Map<String, Object> params)
			throws DataAccessException {
		log.debug("DAO:Running HQL query for total count of records :"
				+ queryString);
		queryString = "select count(t.id) " + queryString;
		Query query;
		if (params != null) {
			query = createQuery(queryString, params);
		} else {
			query = createQuery(queryString);
		}
		return ((Long) query.uniqueResult()).intValue();
	}

	public List findWithSelect(String queryString) throws DataAccessException {
		log.debug("DAO:Running HQL query with selections :" + queryString);
		Query query = createQuery(queryString);
		return query.list();
	}

	public List findWithSelect(String queryString, Map<String, Object> params)
			throws DataAccessException {
		log.debug("DAO:Running HQL query with parameters:" + queryString);
		Query query = createQuery(queryString, params);
		return query.list();
	}

	public List findWithSelect(String queryString, Map<String, Object> params,
			int startRow, int pageSize) throws DataAccessException {
		log.debug("DAO:Running HQL query by page :" + queryString);
		Query query = createQuery(queryString, params, startRow, pageSize);
		return query.list();
	}

	public List<T> find(String queryString, Map<String, Object> params)
			throws DataAccessException {
		log.debug("DAO:Running HQL query with parameters: " + queryString);
		Query query = createQuery(queryString, params);
		return query.list();
	}

	public List<T> find(String queryString, Map<String, Object> params,
			int startRow, int pageSize) throws DataAccessException {
		log.debug("DAO:Running HQL query with params by page :" + queryString);
		Query query = createQuery(queryString, params, startRow, pageSize);
		return query.list();
	}

	public T findUniqueResult(String queryString, Map<String, Object> params)
			throws DataAccessException {
		log.debug("DAO:Running HQL query with parameters:" + queryString);
		Query query = createQuery(queryString, params);
		return (T) query.uniqueResult();
		
	}

	public int excute(String queryString, Map<String, Object> params)
			throws DataAccessException {
		log.debug("DAO:Excute HQL update :" + queryString);
		Query query = createQuery(queryString, params);
		return query.executeUpdate();
	}
	
	
	
	/**
	 * pagefind
	 */
	private String initSort(String hql) {
		log.debug("DAO:Excute HQL update :" + hql);
		String order = ThreadLocalUtils.getOrder();
		String sort = ThreadLocalUtils.getSort();
		if (sort != null && !"".equals(sort.trim())) {
			hql += " order by " + sort;
			if (!"desc".equals(order))
				hql += " asc";
			else
				hql += " desc";
		}
		return hql;
	}

	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query, Map<String, Object> alias) {
		if (alias != null) {
			Set<String> keys = alias.keySet();
			for (String key : keys) {
				Object val = alias.get(key);
				if (val instanceof Collection) {
					// 查询条件是列表
					query.setParameterList(key, (Collection) val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}

	private void setParameter(Query query, Object[] args) {
		if (args != null && args.length > 0) {
			int index = 0;
			for (Object arg : args) {
				query.setParameter(index++, arg);
			}
		}
	}

	private String getCountHql(String hql, boolean isHql) {
		log.debug("DAO:Excute HQL update :" + hql);
		String e = hql.substring(hql.indexOf("from"));
		String c = "select count(*) " + e;
		if (isHql)
			c = c.replaceAll("fetch", "");
		return c;
	}

	/**
	 * 去除hql的orderby 子句
	 * 
	 * @see 
	 */
	private static String removeOrders(String hql) {
		
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	public PageUtil<T> pagefind(String hql, Object[] args,
			Map<String, Object> alias) {
		log.debug("DAO:Excute HQL update :" + hql);
		hql = initSort(hql);
		String cq = getCountHql(hql, true);
		cq = removeOrders(cq);
		Query cquery = getSession().createQuery(cq);
		Query query = getSession().createQuery(hql);
		// 设置别名参数
		setAliasParameter(query, alias);
		setAliasParameter(cquery, alias);
		// 设置参数
		setParameter(query, args);
		setParameter(cquery, args);
		PageUtil<T> pages = new PageUtil<T>();
		setPagers(query, pages);
		List<T> datas = query.list();
		pages.setRows(datas);
		long total = (Long) cquery.uniqueResult();
		pages.setTotal(total);
		return pages;
	}

	public PageUtil<T> pagefind(String hql, Object[] args) {
		log.debug("DAO:Excute HQL update :" + hql);
		return this.pagefind(hql, args, null);
	}

	public PageUtil<T> pagefind(String hql, Object arg) {
		log.debug("DAO:Excute HQL update :" + hql);
		return this.pagefind(hql, new Object[] { arg });
	}

	public PageUtil<T> pagefind(String hql) {
		log.debug("DAO:Excute HQL update :" + hql);
		return this.pagefind(hql, null);
	}

	@SuppressWarnings("rawtypes")
	private void setPagers(Query query, PageUtil pages) {
		Integer pageSize = ThreadLocalUtils.getPageSize();
		Integer pageOffset = ThreadLocalUtils.getPageOffset();
		if (pageOffset == null || pageOffset < 0)
			pageOffset = 0;
		if (pageSize == null || pageSize < 0)
			pageSize = 15;
		// pages.setOffset(pageOffset);
		// pages.setSize(pageSize);
		query.setFirstResult(pageOffset).setMaxResults(pageSize);
	}

	public <N extends Object> PageUtil<N> findBySql(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
		sql = initSort(sql);
		String cq = getCountHql(sql, false);
		cq  = removeOrders(cq);
		SQLQuery sq = getSession().createSQLQuery(sql);
		SQLQuery cquery = getSession().createSQLQuery(cq);
		setAliasParameter(sq, alias);
		setAliasParameter(cquery, alias);
		setParameter(sq, args);
		setParameter(cquery, args);
		PageUtil<N> pages = new PageUtil<N>();
		// setPagers(sq, pages);
		if (hasEntity) {
			sq.addEntity(clz);
		} else {
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		List<N> datas = sq.list();
		pages.setRows(datas);
		long total = ((BigInteger) cquery.uniqueResult()).longValue();
		pages.setTotal(total);
		return pages;
	}

	public <N extends Object> PageUtil<N> findBySql_Sql(String sql, String countSql, Object[] args,
			Map<String, Object> alias, Map<String, Object> count_alias, Class<?> clz, boolean hasEntity) {
		sql = initSort(sql);
		//String cq = getCountHql(sql, false);
		//cq  = removeOrders(cq);
		SQLQuery sq = getSession().createSQLQuery(sql);
		SQLQuery cquery = getSession().createSQLQuery(countSql);
		setAliasParameter(sq, alias);
		setAliasParameter(cquery, count_alias);
		setParameter(sq, args);
		setParameter(cquery, args);
		PageUtil<N> pages = new PageUtil<N>();
		// setPagers(sq, pages);
		if (hasEntity) {
			sq.addEntity(clz);
		} else {
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		List<N> datas = sq.list();
		pages.setRows(datas);
		long total = ((BigInteger) cquery.uniqueResult()).longValue();
		pages.setTotal(total);
		return pages;
	}

	
	public <N extends Object> PageUtil<N> findBySql(String sql, Object[] args,
			Class<?> clz, boolean hasEntity) {
		return this.findBySql(sql, args, null, clz, hasEntity);
	}

	public <N extends Object> PageUtil<N> findBySql(String sql, Object arg,
			Class<?> clz, boolean hasEntity) {
		return this.findBySql(sql, new Object[] { arg }, clz, hasEntity);
	}

	public <N extends Object> PageUtil<N> findBySql(String sql, Class<?> clz,
			boolean hasEntity) {
		return this.findBySql(sql, null, clz, hasEntity);
	}
}
