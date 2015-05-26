package com.ihm.customer.dao;
/**

 @Author sardarwaqasahmed
 @date   NOV 9, 2014
 1.0
 **/
import com.ihm.customer.entites.SlrSeller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchDAO extends GenericDAO<SlrSeller, Integer> {

    public abstract List<SlrSeller> simpleSearchSeller(String testName, String city,String locality, String zip, String date, int fromIndex,int toIndex, int selectedId);
    public abstract List<SlrSeller> advancedSearchSeller(List<String> testNameList, String city,String locality, String zip, String date, int fromIndex,int toIndex, int selectedId);

    public abstract SlrSeller getByClinicById(long id);

    Integer getSlrSellerCount(String testName, String city,String locality, String zip, String date);
    Integer getAdvancedSlrSellerCount(List<String> testNameList, String city,String locality, String zip, String date);
}