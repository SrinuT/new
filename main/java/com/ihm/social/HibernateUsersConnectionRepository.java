package com.ihm.social;

import org.hibernate.SessionFactory;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.*;
import org.springframework.social.security.SocialAuthenticationFilter;

import java.util.List;
import java.util.Set;

/**
 * Created by artur on 4/21/15.
 */
public class HibernateUsersConnectionRepository implements UsersConnectionRepository {

    private SessionFactory sessionFactory;

    private ConnectionFactoryLocator connectionFactoryLocator;

    private TextEncryptor textEncryptor;

    private ConnectionSignUp connectionSignUp;


    public HibernateUsersConnectionRepository(
            ConnectionFactoryLocator connectionFactoryLocator,
            TextEncryptor textEncryptor, SessionFactory sessionFactory) {
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.textEncryptor = textEncryptor;
        this.sessionFactory = sessionFactory;
    }

    public HibernateUsersConnectionRepository() {
    }

    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        return null;
    }

    @Override
    public Set<String> findUserIdsConnectedTo(String s, Set<String> set) {
        return null;
    }

    @Override
    public ConnectionRepository createConnectionRepository(String s) {
        return null;
    }
}
