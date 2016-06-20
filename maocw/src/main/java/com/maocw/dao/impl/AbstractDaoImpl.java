package com.maocw.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.maocw.dao.BaseDao;


abstract class AbstractDaoImpl<T> implements BaseDao<T> {
	private final Class<T> clazz;
	
	public AbstractDaoImpl(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Autowired
	protected org.springframework.orm.hibernate3.HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public void save(T t){
		hibernateTemplate.save(t);
	}
	
	public void saveOrUpdate(T t){
		hibernateTemplate.saveOrUpdate(t);
	}
	
	public void update(T t){
		hibernateTemplate.update(t);
	}

	public void delete(T t){
		hibernateTemplate.delete(t);
	}
	
	public void deleteAll(List<T> list){
		hibernateTemplate.deleteAll(list);
	}
	
	public void deleteByIds(String[] ids) {
		if (null != ids) {
			List<T> list = listByIds(ids);
			hibernateTemplate.deleteAll(list);
		}
	}

	public void deleteByIds(List<String> ids){
		if (null != ids) {
			List<T> list = listByIds(ids);
			hibernateTemplate.deleteAll(list);
		}
	}
	
	public T load(Serializable id) {
		T t = (T) hibernateTemplate.get(clazz, id);
		return t;
	}
	
	public T loadByAttribute(String property,Object value) {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		if (null != property && !"".equals(property.trim())) {
			dc.add(Property.forName(property).eq(value));
		}
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) hibernateTemplate.findByCriteria(dc);
		if (null != list && list.size() == 1)
			return list.get(0);
		else
			return null;
	}
	
	public T loadByAttributes(Map<String, Object> properties) {
		if(properties==null||properties.isEmpty()){
			return null;
		}
		
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		for(Iterator<Map.Entry<String, Object>> iter = 
			properties.entrySet().iterator();iter.hasNext();){
			Map.Entry<String, Object> me = iter.next();
			String property = me.getKey();
			if (null != property && !"".equals(property.trim())) {
				dc.add(Property.forName(property).eq(me.getValue()));
			}
		}
		
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) hibernateTemplate.findByCriteria(dc);
		if (null != list && list.size() == 1)
			return list.get(0);
		else
			return null;
	}

	public List<T> listAll() {
		return hibernateTemplate.loadAll(clazz);
	}
	
	public List<T> listAll(DetachedCriteria detachedCriteria) {
		return (List<T>) hibernateTemplate.findByCriteria(detachedCriteria);
	}
	
	public int count(DetachedCriteria detachedCriteria) {
		return ((Long) hibernateTemplate.findByCriteria(
				detachedCriteria.setProjection(Projections.rowCount())).get(0))
				.intValue();
	}

	public List<T> listByIds(String[] ids) {
		if (null == ids)
			return null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
		List<Long> longs = new ArrayList<Long>();
		for (int i = 0; i < ids.length; i++) {
			longs.add(Long.parseLong(ids[i]));
		}
		detachedCriteria.add(Property.forName("id").in(longs));
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) hibernateTemplate.findByCriteria(detachedCriteria);
		return list;
	}

	public List<T> listByIds(List<String> ids) {
		if (null == ids)
			return null;
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(clazz);
		List<Long> longs = new ArrayList<Long>();
		for (String string : ids) {
			longs.add(Long.parseLong(string));
		}
		detachedCriteria.add(Property.forName("id").in(longs));
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) hibernateTemplate.findByCriteria(detachedCriteria);
		return list;
	}
	
	public void updateBySql(final String sql){
		hibernateTemplate.execute(
			new HibernateCallback(){
				public Object doInHibernate(Session session)
					throws HibernateException{
						SQLQuery query = session.createSQLQuery(sql);
						query.executeUpdate();
						return null;
					}
			});
	}
	
	public void updateByHql(final String hql){
		hibernateTemplate.execute(
			new HibernateCallback(){
				public Object doInHibernate(Session session)
					throws HibernateException{
						session.createQuery(hql).executeUpdate();;
						return null;
					}
			});
	}
	
	public Object loadBySql(final String sql){
		Object list = hibernateTemplate.execute(
				new HibernateCallback(){
					public Object doInHibernate(Session session){
						SQLQuery query = session.createSQLQuery(sql);
						return query.list();
					}
				});
		return list;
	}

	
	
	protected DetachedCriteria makeDetachedCriteria() {
		return	DetachedCriteria.forClass(clazz);
	}
	
	
	protected List findBySQL(String sql) {
		List list = null;
		Session session = null;
		try {
			session = hibernateTemplate.getSessionFactory().openSession();
			list = session.createSQLQuery(sql).list();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			session.close();
		}
		return list;
	}
}
