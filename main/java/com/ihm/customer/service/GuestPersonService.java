package com.ihm.customer.service;

import com.ihm.frontend.search.dto.GuestLogin;

/**
 * @author Artur Yolchyan
 */
public interface GuestPersonService {


    /**
     * save new guest user and return id
     *
     * @param guestLogin
     * @return
     */
    Long create(GuestLogin guestLogin);

}
