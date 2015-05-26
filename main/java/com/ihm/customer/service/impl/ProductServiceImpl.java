package com.ihm.customer.service.impl;

import com.ihm.customer.dao.ProductDAO;
import com.ihm.customer.entites.SlrProduct;
import com.ihm.customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by artur on 5/3/15.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public SlrProduct getSlrProduct(String testName, Long slrSellerId) {
        return productDAO.getSlrProduct(testName, slrSellerId);
    }
}
