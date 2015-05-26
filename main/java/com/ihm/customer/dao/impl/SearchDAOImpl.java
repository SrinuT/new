package com.ihm.customer.dao.impl;
/**

 @Author sardarwaqasahmed
 @date   NOV 9, 2014
 1.0
 **/
import com.ihm.customer.dao.AbstractHibernateDAO;
import com.ihm.customer.dao.SearchDAO;
import com.ihm.customer.entites.SlrProduct;
import com.ihm.customer.entites.SlrSeller;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Repository
@SuppressWarnings("all")
public class SearchDAOImpl extends AbstractHibernateDAO<SlrSeller, Integer> implements SearchDAO , Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public List<SlrSeller> simpleSearchSeller(String testName, String city,String locality, String zip, String date,int fromIndex,int toIndex, int selectedId) {

        List<SlrSeller> clinicList = null;

        try {

            String hqlStr = "SELECT DISTINCT clinic FROM SlrSeller As clinic "
                    + "LEFT JOIN FETCH clinic.slrAwards AS awards "
                    + "LEFT JOIN FETCH clinic.slrCertifications AS certificates "
                    + "LEFT JOIN FETCH clinic.slrProducts AS product "
                    + "LEFT JOIN FETCH product.slrProductReviews AS reviews "
                    + "LEFT JOIN FETCH clinic.prdDeals AS deals "
                    + "LEFT JOIN FETCH clinic.slrAppointments AS appointment "
                    + "LEFT JOIN FETCH clinic.slrAmenitieses AS amenitieses "
                    + "LEFT JOIN FETCH clinic.slrAddress AS address "
                    + "LEFT JOIN FETCH clinic.slrSellerReviews AS slrSellerReviews "
                    + "WHERE 1=1 ";

			/*
			 * BASIC INFO SEARCH
			 */

            if (testName != null && testName.length() > 0)
                hqlStr += " AND product.name LIKE '" + testName + "%'";
            if (city != null && city.length() > 0)
                hqlStr += " AND address.city LIKE '" + city + "%'";
            if (locality != null && locality.length() > 0)
                hqlStr += " AND address.locality LIKE '" + locality + "%'";
            if (zip != null && zip.length() > 0)
                hqlStr += " AND address.zip = '" + zip + "'";
            if (date != null && !date.isEmpty())
                hqlStr += " AND appointment.startTime = '"
                        + date + "'";

            if (selectedId == 0) {
                hqlStr += sortedBySelectedItem(1);
            } else {
                hqlStr += sortedBySelectedItem(selectedId);
            }
            Query query = getSession().createQuery(hqlStr);
            query.setFirstResult(fromIndex);
            query.setMaxResults(toIndex);
            clinicList = (List<SlrSeller>) query.list();
        }catch(Exception e){
            throw e;
        }

        return clinicList;
    }
    
    /*
	 * ADVANCED INFO SEARCH
	 */
    public List<SlrSeller> advancedSearchSeller(List<String> testName, String city,String locality, String zip, String date,int fromIndex,int toIndex, int selectedId) {

        List<SlrSeller> clinicList = null;

        try {
        	String testname = "";
        	String hqlStr = "SELECT clinic FROM SlrSeller As clinic "
                    + "LEFT JOIN FETCH clinic.slrAwards AS awards "
                    + "LEFT JOIN FETCH clinic.slrCertifications AS certificates "
                    + "LEFT JOIN FETCH clinic.slrProducts AS product "
                    + "LEFT JOIN FETCH product.slrProductReviews AS reviews "
                    + "LEFT JOIN FETCH clinic.prdDeals AS deals "
                    + "LEFT JOIN FETCH clinic.slrAppointments AS appointment "
                    + "LEFT JOIN FETCH clinic.slrAmenitieses AS amenitieses "
                    + "LEFT JOIN FETCH clinic.slrAddress AS address "
                    + "LEFT JOIN FETCH clinic.slrSellerReviews AS slrSellerReviews ";
        	
        	List<List<String>> clinicCollection = new ArrayList<List<String>>();
        	
        	for(int i = 0; i<testName.size(); i++){
            	if (testName != null && testName.size() > 0){
            		String hql = "SELECT prod.seller_id from SlrProduct as prod where prod.name = '"+testName.get(i)+"' group by prod.seller_id";
            		Query query = getSession().createQuery(hql);
            		clinicCollection.add(query.list());
            	}
                    //hqlStr +=" INNER JOIN clinic.slrProducts AS product"+i+" where clinic.id = product"+i+".seller_id and product"+i+".name ='"+testName.get(i)+"'";   
            } 
        	List<String>result =new ArrayList<String>();
        	if(clinicCollection.size()>1){
        		for(int i=0;i<clinicCollection.size();i=i+2){
            		
            		if(i==0){
            			result = IntersectionOFClinic(clinicCollection.get(i), clinicCollection.get(i+1));
            		} else {
            			result = IntersectionOFClinic(clinicCollection.get(i), result);
            		}
            	}
        	}else{
        		result = clinicCollection.get(0);
        	}
        	System.out.println("$$$$$$$$$$$$$$$$$"+result.toString());
        	
        	hqlStr +="Where (";
        	for(int i=0;i<result.size();i++){
        		if(i == result.size()-1){
        			hqlStr +=" clinic.id ='"+result.get(i)+"')";
        		} else {
        			hqlStr +=" clinic.id ='"+result.get(i)+"' or";
        		}
        	} 
            
        	
        	
            if (city != null && city.length() > 0)
                hqlStr += " AND address.city LIKE '" + city + "%'";
            if (locality != null && locality.length() > 0)
                hqlStr += " AND address.locality LIKE '" + locality + "%'";
            if (zip != null && zip.length() > 0)
                hqlStr += " AND address.zip = '" + zip + "'";
            
            hqlStr +=  " AND product.status = 'ACTIVE' GROUP BY clinic.name";
            
            System.out.println("After Replacement Query String   :   "+hqlStr);
           Query query = getSession().createQuery(hqlStr);
           //query.setFirstResult(fromIndex);
           //query.setMaxResults(toIndex);
           clinicList = (List<SlrSeller>) query.list();
           
           for(int i=0; i<clinicList.size(); i++){
               List<SlrProduct> proList =new ArrayList<SlrProduct>();
               
           	String hqlStrPrice = "SELECT product FROM SlrProduct as product where product.seller_id = '"+clinicList.get(i).getId()+"' And (";
               //String hqlStrPrice = "SELECT product FROM SlrProduct as product where product.name in (SELECT distinct pro.name From SlrProduct as pro where pro.seller_id = '"+clinicList.get(i).getId()+"' And (";
           	for(int j=0 ;j< testName.size();j++){
           		if( j== testName.size()-1){
           			hqlStrPrice +=" product.name = '"+testName.get(j)+"') GROUP BY product.name";
           		} else {
           			hqlStrPrice +=" product.name = '"+testName.get(j)+"' Or ";
           		}
           	}
           	
           	Query query1 = getSession().createQuery(hqlStrPrice);
           	proList = (List<SlrProduct>) query1.list();
           	/*Iterator<SlrProduct> it = proList.iterator();
           	BigDecimal price = new BigDecimal(0);
           	while(it.hasNext()){
           		price.add(it.next().getPrice());
           	}*/
           	Set< SlrProduct> proSets = new HashSet<SlrProduct>(proList);
           	clinicList.get(i).setSlrProducts(proSets);
           	System.out.println("########### Product list query "+hqlStrPrice);
           	Iterator<SlrProduct> it1 = clinicList.get(i).getSlrProducts().iterator();
           	//BigDecimal price = new BigDecimal(0);
           	while(it1.hasNext()){
           		System.out.println("########### Product list price "+it1.next().getName());
           	}
           	
           	
           	System.out.println("########### Product list ="+proList);
           }
                   	
        }catch(Exception e){
            throw e;
        }

        return clinicList;
    }

    @Override
    public SlrSeller getByClinicById(long id) {
        try{
            String hqlStr = "SELECT DISTINCT clinic FROM SlrSeller As clinic "
                    + "WHERE 1=1 AND clinic.id="+id;
            Query query = getSession().createQuery(hqlStr);
            return (SlrSeller) query.list().get(0);
        }catch(Exception ex){
            throw ex;
        }

    }

    public Integer getSlrSellerCount(String testName, String city,String locality, String zip, String date) {
    	 String hqlStr = "SELECT DISTINCT clinic FROM SlrSeller As clinic "
                 + "LEFT JOIN FETCH clinic.slrAwards AS awards "
                 + "LEFT JOIN FETCH clinic.slrCertifications AS certificates "
                 + "LEFT JOIN FETCH clinic.slrProducts AS product "
                 + "LEFT JOIN FETCH product.slrProductReviews AS reviews "
                 + "LEFT JOIN FETCH clinic.prdDeals AS deals "
                 + "LEFT JOIN FETCH clinic.slrAppointments AS appointment "
                 + "LEFT JOIN FETCH clinic.slrAmenitieses AS amenitieses "
                 + "LEFT JOIN FETCH clinic.slrAddress AS address "
                 + "LEFT JOIN FETCH clinic.slrSellerReviews AS slrSellerReviews "
                 + "WHERE 1=1 ";

			/*
			 * BASIC INFO SEARCH
			 */

         if (testName != null && testName.length() > 0)
             hqlStr += " AND product.name LIKE '" + testName + "%'";
         if (city != null && city.length() > 0)
             hqlStr += " AND address.city LIKE '" + city + "%'";
         if (locality != null && locality.length() > 0)
             hqlStr += " AND address.locality LIKE '" + locality + "%'";
         if (zip != null && zip.length() > 0)
             hqlStr += " AND address.zip = '" + zip + "'";
        

         
        
         
        Query query = getSession().createQuery(hqlStr);
        return query.list().size();
    }
    
    /*
	 * ADVANCED INFO SEARCH
	 */
    public Integer getAdvancedSlrSellerCount(List<String> testName, String city,String locality, String zip, String date) {
    	String testname="";
    	
    	String hqlStr = "SELECT clinic FROM SlrSeller As clinic "
                + "LEFT JOIN FETCH clinic.slrAwards AS awards "
                + "LEFT JOIN FETCH clinic.slrCertifications AS certificates "
                + "LEFT JOIN FETCH clinic.slrProducts AS product "
                + "LEFT JOIN FETCH product.slrProductReviews AS reviews "
                + "LEFT JOIN FETCH clinic.prdDeals AS deals "
                + "LEFT JOIN FETCH clinic.slrAppointments AS appointment "
                + "LEFT JOIN FETCH clinic.slrAmenitieses AS amenitieses "
                + "LEFT JOIN FETCH clinic.slrAddress AS address "
                + "LEFT JOIN FETCH clinic.slrSellerReviews AS slrSellerReviews ";
                

            List<List<String>> clinicCollection = new ArrayList<List<String>>();
    	for(int i = 0; i<testName.size(); i++){
        	if (testName != null && testName.size() > 0){
        		String hql = "SELECT pro.seller_id from SlrProduct as pro where pro.name = '"+testName.get(i)+"'";
        		Query query = getSession().createQuery(hql);
        		clinicCollection.add(query.list());
        	}
                //hqlStr +=" INNER JOIN clinic.slrProducts AS product"+i+" where clinic.id = product"+i+".seller_id and product"+i+".name ='"+testName.get(i)+"'";   
        } 
    	List<String>result =new ArrayList<String>();
    	if(clinicCollection.size()>1){
    		for(int i=0;i<clinicCollection.size();i=i+2){
        		
        		if(i==0){
        			result = IntersectionOFClinic(clinicCollection.get(i), clinicCollection.get(i+1));
        		} else {
        			result = IntersectionOFClinic(clinicCollection.get(i), result);
        		}
        	}
    	}else{
    		result = clinicCollection.get(0);
    	}
    	System.out.println("$$$$$$$$$$$$$$$$$"+result.toString());
    	hqlStr +="Where (";
    	for(int i=0;i<result.size();i++){
    		if(i == result.size()-1){
    			hqlStr +=" clinic.id ='"+result.get(i)+"' )";
    		} else {
    			hqlStr +=" clinic.id ='"+result.get(i)+"' or";
    		}
    	}
    	/*testname = testname.substring(0,testname.length()-1);
   	 	hqlStr += " AND product.name in ("+testname+")"; 	 	
   	 	*/	        
        System.out.println("After Replacement Query String   :   "+hqlStr);
    	
        if (city != null && city.length() > 0)
            hqlStr += " AND address.city LIKE '" + city + "%'";
        if (locality != null && locality.length() > 0)
            hqlStr += " AND address.locality LIKE '" + locality + "%'";
        if (zip != null && zip.length() > 0)
            hqlStr += " AND address.zip = '" + zip + "'";
        
        hqlStr +=  " AND product.status = 'ACTIVE' GROUP BY clinic.name";
        
       Query query = getSession().createQuery(hqlStr);
       System.out.println("After Replacement Query String output count  :   "+query.list().size());
       return query.list().size();
   }
   
    private List<String> IntersectionOFClinic(List<String> a,List<String> b){
    	List<String> result = new ArrayList<String>();
    	for(String clinic:a){
    		if(b.contains(clinic)){
    			result.add(clinic);
    		}
    	}
    	return result;
    }
    /**
     * this method for sort by selected type
     *
     * @param selectedId - String
     * @return sortedSql - String
     */
    private String sortedBySelectedItem(int selectedId) {
        StringBuilder sortedSql = new StringBuilder();

        switch (selectedId) {
            case 1: //sortedSql.append(" ORDER BY reviews.starRating DESC");
            	//sortedSql.append(" ORDER BY product.price DESC");
                break;
            case 2: //sortedSql.append(" ORDER BY product.price DESC");
                break;
            case 3: //sortedSql.append(" ORDER BY product.price DESC");
                break;
        }

        return sortedSql.toString();
    }

}
