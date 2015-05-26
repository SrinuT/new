package com.ihm.customer.service;

import com.ihm.customer.entites.SlrProduct;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by artur on 5/3/15.
 */
@Service
public interface ProductService extends Serializable {

    SlrProduct getSlrProduct(String testName, Long slrSellerId);

}
