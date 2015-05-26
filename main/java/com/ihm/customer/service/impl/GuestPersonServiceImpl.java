package com.ihm.customer.service.impl;

import com.ihm.customer.dao.GuestPersonDao;
import com.ihm.customer.entites.GuestPerson;
import com.ihm.customer.service.GuestPersonService;
import com.ihm.frontend.search.dto.GuestLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by artur on 4/12/15.
 */
@Service("guestPersonService")
public class GuestPersonServiceImpl implements GuestPersonService {

    @Autowired
    private GuestPersonDao guestPersonDao;

    @Override
    public Long create(GuestLogin guestLogin) {
        GuestPerson guestPerson = guestPersonDao.saveOrEdit(initGuestPersonByLoginPerson(guestLogin));
        return guestPerson.getId();
    }

    private GuestPerson initGuestPersonByLoginPerson(GuestLogin guestLogin) {
        GuestPerson guestPerson = new GuestPerson();
        guestPerson.setBirthDate(guestLogin.getDob());
        guestPerson.setContactNumber(guestLogin.getPrimaryNumber());
        guestPerson.setEmail(guestLogin.getEmail());
        guestPerson.setFirstName(guestLogin.getFirstName());
        guestPerson.setLastName(guestLogin.getLastName());
        return guestPerson;
    }
}
