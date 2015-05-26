/**
* @author SARDAR WAQAS AHMED
* @email  architect_pakistan@hotmail.com
* @since  01 NOV, 2014
* @version 1.0
*/
package com.ihm.customer.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("all")
public abstract class AbstractHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

	private static Logger logger = Logger.getLogger(AbstractHibernateDAO.class);
	
	private Class<T> persistentClass; 
	
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public AbstractHibernateDAO() {
        this.persistentClass =
                (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      
    }

   protected Session getSession() {
    	Session session = this.sessionFactory.getCurrentSession();
        if (session == null || session.isOpen() == false){
        	session = this.sessionFactory.openSession();
        }
        return session;
    }

    
	protected void closeSession() {
		getSession().close();
	}

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    

    @SuppressWarnings("unchecked")
    public T getById(Long id) {
        T value =  (T)getSession().get(getPersistentClass(), id);        
        return value;
    }

    @SuppressWarnings("unchecked")
    public T getById(Long id, boolean lock) {
        if (lock) {
            return (T)getSession().get(getPersistentClass(), id,
                                       LockMode.UPGRADE);
        } else
            return getById(id);
    }

    @SuppressWarnings("unchecked")
    public T loadById(ID id) {
        return (T)getSession().load(getPersistentClass(), id);
    }

    public T save(T entity) {
    	try{
    		 Session session = getSession();
    		 session.save(entity);
    		return entity;
        }catch(RuntimeException re)
     	{
        	re.printStackTrace();
     		return null;
     	}
    		
    }

    public String saveList(List<T> entityList){
    	try
    	{
        Session ses = getSession();
        for (T entity : entityList) {
            ses.save(entity);
        }
        return "success";
        }catch(RuntimeException re)
     	{
        	re.printStackTrace();
     		return "failure";
     	}
    }

    public T saveOrEdit(T entity) {
        Session session = getSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    public T update(T entity) {        
       try{
    	   getSession().update(entity);
    	   return entity;
       }catch(RuntimeException re)
    	{
    		return entity;
    	}
    }

    public T saveOrUpdate(T entity) {
    	try{
    	Session ses = getSession();
    	ses.saveOrUpdate(entity);
    	   return entity;
        }catch(RuntimeException re)
     	{
     		return null;
     	}
    }
    
    public String saveOrUpdate(List<T> entityList) {
    	try{
        Session ses = getSession();
        for (T entity : entityList) {            
            ses.merge(entity);
        }
        return "success";
        }catch(RuntimeException re)
     	{
     		return "failure";
     	}
    }

    public void evict(T entity) {
        Session ses = getSession();
        if (entity != null && ses != null && ses.contains(entity)) {
            ses.evict(entity);
        }        
    }

    public void evict(List<T> entityList) {
        //Session ses = getSession();
        for (T entity : entityList) {
            evict(entity);
        }        
    }

    public void merge(T entity) {
        getSession().merge(entity);
    }

    public void clear() {
        getSession().clear();
    }

    
    public String deleteList(List<T> entityList) {
    	try{
        Session ses = getSession();
        for (T entity : entityList) {
            ses.delete(entity);
        }
        return "success";
        }catch(RuntimeException re)
     	{
     		return "failure";
     	}
    }

    public String deleteAll() {
    	try{
        List<T> entityList = findAll();
        Session ses = getSession();
        for (T entity : entityList) {
            ses.delete(entity);
        }
        return "success";
        }catch(RuntimeException re)
     	{
     		return "failure";
     	}
    }

    public String delete(T entity) {
    	try{
        getSession().delete(entity);
        return "success";
        }catch(RuntimeException re)
     	{
     		return "failure";
     	}

    }

    public void deleteById(ID id) {
        getSession().delete(loadById(id));
    }

   
    public List<T> findAll() {
        return findByCriteria();
    }

    /**
     * Use this inside subclasses as a convenience method.
     */

    protected List<T> findByCriteria(Criterion... criterion) {
        List<T> list = null;
        Session ses = getSession();
        Criteria crit = ses.createCriteria(getPersistentClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        list = crit.list();
        return list;
    }

   
    public Integer findMaxByColumn(String columnName) {
        Integer result = null;
        Session ses = getSession();
        Criteria crit = ses.createCriteria(getPersistentClass());
        crit.setProjection(Projections.max(columnName));
        result = (Integer)crit.uniqueResult();
        return result;
    }
    
    /**
     * Find by criteria.
     */
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Map criterias) {

        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.allEq(criterias));
        return criteria.list();
    }

    /**
     * This method will execute an HQL query and return the number of affected entities.
     */
    protected int executeQuery(String query, String[] namedParams,
                               Object[] params) {
        Query q = getSession().createQuery(query);

        if (namedParams != null) {
            for (int i = 0; i < namedParams.length; i++) {
                q.setParameter(namedParams[i], params[i]);
            }
        }

        return q.executeUpdate();
    }

    /**
     * This method will execute a Named HQL query and return the number of affected entities.
     */
    protected int executeNamedQuery(String namedQuery, String[] namedParams,
                                    Object[] params) {
        Query q = getSession().getNamedQuery(namedQuery);

        if (namedParams != null) {
            for (int i = 0; i < namedParams.length; i++) {
                q.setParameter(namedParams[i], params[i]);
            }
        }

        return q.executeUpdate();
    }

    protected int executeNamedQuery(String namedQuery) {
        return executeNamedQuery(namedQuery, null, null);
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example =
            Example.create(exampleInstance).excludeZeroes().enableLike().ignoreCase();
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    /**
     * Implementation of {@link GenericDAO.executePaginatedQuery}.
     */

    @Override
    public List<T> executePaginatedQuery(String queryString, int pageNumber, int pageSize) {
        Query query = getSession().createQuery(queryString);
        query.setFirstResult(pageNumber);
        query.setMaxResults(pageSize);
        return query.list();
    }

    /**
     * Implementation of {@link GenericDAO.executePaginatedQuery}.
     */

    @Override
    public List<T> executePaginatedQuery(String queryString, int pageNumber, int pageSize, Object[] parameters) {

        Query query = getSession().createQuery(queryString);
        logger.debug("Setting parameters for query: " + query.getQueryString());
        if (parameters != null) {
            for (int index = 0; index < parameters.length; index++) {
                logger.debug("index: " + index + " value: " + parameters[index]);
                query.setParameter(index, parameters[index]);
            }
        }
        query.setFirstResult(pageNumber);
        query.setMaxResults(pageSize);
        return query.list();
    }

    /**
     * Implementation of {@link GenericDAO.executeNativePaginatedQuery}.
     */

    @Override
    public List executeNativePaginatedQuery(String queryString, int pageNumber, int pageSize, Object[] parameters) {

        Query query = getSession().createSQLQuery(queryString);

        logger.debug("Setting parameters for query: " + query.getQueryString());
        if (parameters != null) {
            for (int index = 0; index < parameters.length; index++) {
                logger.debug("index: " + index + " value: " + parameters[index]);
                query.setParameter(index, parameters[index]);
            }
        }
        query.setFirstResult(pageNumber);
        query.setMaxResults(pageSize);
        return query.list();
    }

	@Override
	public List<T> executeQueryTwo(String queryString) {
		Query query = getSession().createQuery(queryString);
		return query.list();
	}

}
