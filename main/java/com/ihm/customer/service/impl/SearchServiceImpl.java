package com.ihm.customer.service.impl;
/**

 @Author sardarwaqasahmed
 @date   NOV 9, 2014
 1.0
 **/
import com.ihm.customer.dao.ClinicAddressDAO;
import com.ihm.customer.dao.ProductDAO;
import com.ihm.customer.dao.SearchDAO;
import com.ihm.customer.entites.SlrSeller;
import com.ihm.customer.exceptions.IHMCustomerException;
import com.ihm.customer.service.SearchService;
import com.ihm.customer.util.IHMConstants;
import com.ihm.customer.util.IHMQueries;
import com.ihm.customer.util.StringFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService,Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -1945591295195330936L;

    @Autowired
    private SearchDAO searchDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ClinicAddressDAO clinicAddressDAO;


    public SearchDAO getSearchServiceDAO() {
        return searchDAO;
    }

    public void setSearchServiceDAO(SearchDAO searchDAO) {
        this.searchDAO = searchDAO;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public ClinicAddressDAO getClinicAddressDAO() {
        return clinicAddressDAO;
    }

    public void setClinicAddressDAO(ClinicAddressDAO clinicAddressDAO) {
        this.clinicAddressDAO = clinicAddressDAO;
    }

    public List<SlrSeller> simpleSearchSeller(String testName, String city,String locality, String zip, String date,int fromIndex,int toIndex, int selectedId) {
        List<SlrSeller> sellerList = null;
        try {

            sellerList = searchDAO.simpleSearchSeller(testName, city, locality, zip, date,fromIndex,toIndex, selectedId);

        } catch (Exception e) {
            throw e;
        }
        return sellerList;
    }
    
    public List<SlrSeller> advancedSearchSeller(List<String> testName, String city,String locality, String zip, String date,int fromIndex,int toIndex, int selectedId) {
        List<SlrSeller> sellerList = null;
        try {

            sellerList = searchDAO.advancedSearchSeller(testName, city, locality, zip, date,fromIndex,toIndex, selectedId);

        } catch (Exception e) {
            throw e;
        }
        return sellerList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAllTestByClinic(String name) {
        List<String> testNameList = null;
        try {

            String query = StringFunctions.replace(IHMQueries.SELECT_ALL_TEST, IHMConstants.CODE,name);
            testNameList = (List<String>)productDAO.executeQueryTwo(query);

        } catch (Exception e) {
            throw e;
        }
        return testNameList;

    }

    @Override
    public List<String> getAllCity() {
        List<String> cityList = null;
        try {

            cityList = clinicAddressDAO.getAllCity();

        } catch (Exception e) {
            throw e;
        }
        return cityList;
    }

    @Override
    public List<String> getAllLocality() {
        List<String> localityList = null;
        try {

            localityList = clinicAddressDAO.getAllLocality();

        } catch (Exception e) {
            throw e;
        }
        return localityList;
    }

    @Override
    public List<String> getAllLocalityByCity(String city) {
        List<String> localityList = null;
        try {

            localityList = clinicAddressDAO.getAllLocalityByCity(city);

        } catch (Exception e) {
            throw e;
        }
        return localityList;
    }



    @Override
    public SlrSeller getClinicById(long id) throws IHMCustomerException {
        return searchDAO.getByClinicById(id);
    }

    @Override
    public Integer getSlrSellerCount(String testName, String city, String locality, String zip, String date) {
        return searchDAO.getSlrSellerCount(testName, city, locality, zip, date);
    }
   
    @Override
    public SlrSeller getSlrSellerById(Long id) {
        return searchDAO.getById(id);
    }
      
    @Override
    public Integer getAdvancedSlrSellerCount(List<String> testNameList, String city, String locality, String zip, String date) {
        return searchDAO.getAdvancedSlrSellerCount(testNameList, city, locality, zip, date);
    }


}
