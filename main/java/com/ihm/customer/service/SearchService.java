package com.ihm.customer.service;
/**

 @Author sardarwaqasahmed
 @date   NOV 9, 2014
 1.0
 **/
import com.ihm.customer.entites.SlrSeller;
import com.ihm.customer.exceptions.IHMCustomerException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {

    public abstract List<SlrSeller> simpleSearchSeller(String testName,String city,String locality,String zip, String date,int fromIndex,int toIndex, int selectedId);
    public abstract List<SlrSeller> advancedSearchSeller(List<String> testNameList,String city,String locality,String zip, String date,int fromIndex,int toIndex, int selectedId);
    public SlrSeller getClinicById(long id) throws IHMCustomerException;
    public List<String> getAllTestByClinic(String name);
    public List<String> getAllCity();
    public List<String> getAllLocality();
    public List<String> getAllLocalityByCity(String city);

    Integer getSlrSellerCount(String testName, String city, String locality, String zip, String date);
   	Integer getAdvancedSlrSellerCount(List<String> testNameList, String city,String locality, String zip, String date);

    SlrSeller getSlrSellerById(Long id);



}
