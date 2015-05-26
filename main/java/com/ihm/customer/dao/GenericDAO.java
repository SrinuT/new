package com.ihm.customer.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
* @author SARDAR WAQAS AHMED
* @email  architect_pakistan@hotmail.com
* @since  01 NOV, 2014
* @version 1.0
*/
@SuppressWarnings("all")
public interface GenericDAO<T, ID extends Serializable> {
	
	
    T getById(Long id, boolean lock);

    T getById(Long id);

    T loadById(ID id);

    List<T> findAll();

    T saveOrEdit(T entity);

    public Integer findMaxByColumn(String columnName);

    List<T> findByCriteria(Map criterias);

    public List<T> findByExample(T exampleInstance, String[] excludeProperty);

    T save(T entity);
    
    String saveList(List<T> entity);

    T update(T entity);

    T saveOrUpdate(T entity);

    String delete(T entity);
    
    public String deleteList(List<T> entityList);
    
    public String deleteAll();    

    void deleteById(ID id);

    public void merge(T entity);

    public void clear();

    public void evict(List<T> entityList);

    public void evict(T entity);
    
    public String saveOrUpdate(List<T> entityList) ;
    
    /**
   	 * Retrieve the objects from database according to size of page on the basis of queryString provided. 
   	 *  
   	 * @param <code>queryString</code> contain the HQL query to be executed.
   	 * @param <code>pageNumber</code> contain the pageNumber of the result set.
   	 * @param <code>pageSize</code> contain the size of the page 
   	 * @return List<T> of the type provided if the record exists in the database.
   	 * @return empty list if no record exist in database.
   	 */
    public List<T> executePaginatedQuery(String queryString, int pageNumber, int pageSize);
    
    /**
   	 * Retrieve the objects from database according to size of page on the basis of queryString provided and with parameter values. 
   	 *  
   	 * @param <code>queryString</code> contain the HQL query to be executed.
   	 * @param <code>pageNumber</code> contain the pageNumber of the result set.
   	 * @param <code>pageSize</code> contain the size of the page 
   	 * @param <code>parameters</code> contain the parameters for HQL query to be executed.
   	 * @return List<T> of the type provided if the record exists in the database.
   	 * @return empty list if no record exist in database.
   	 */
    public List<T> executePaginatedQuery(String queryString, int pageNumber, int pageSize, Object[] parameters);
    
    /**
   	 * Retrieve the objects from database according to size of page on the basis of native queryString provided and with parameter values. 
   	 *  
   	 * @param <code>queryString</code> contain the native SQL query to be executed.
   	 * @param <code>pageNumber</code> contain the pageNumber of the result set.
   	 * @param <code>pageSize</code> contain the size of the page 
   	 * @param <code>parameters</code> contain the parameters for native SQL query to be executed.
   	 * @return List of the type provided if the record exists in the database.
   	 * @return empty list if no record exist in database.
   	 */
    public List executeNativePaginatedQuery(String queryString, int pageNumber, int pageSize, Object[] parameters);
    
    
    /**
   	 * Retrieve the objects from database on the basis of queryString provided. 
   	 *  
   	 * @param <code>queryString</code> contain the HQL query to be executed.
   	 * @return List<T> of the type provided if the record exists in the database.
   	 * @return empty list if no record exist in database.
   	 */
    public List executeQueryTwo(String queryString);
}
