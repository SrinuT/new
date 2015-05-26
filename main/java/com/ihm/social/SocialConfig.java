package com.ihm.social;


import org.hibernate.SessionFactory;
import org.socialsignin.springsocial.security.connect.SpringSocialSecurityConnectionFactory;
import org.socialsignin.springsocial.security.signup.SpringSocialSecurityConnectionSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ReconnectFilter;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSocial
@EnableWebSecurity
public class SocialConfig implements SocialConfigurer {

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(new FacebookConnectionFactory("714639581991517", "6178d35ee7fd1fabddd26294008ca6da"));
        cfConfig.addConnectionFactory(new SpringSocialSecurityConnectionFactory());
    }


    /**
     * This is only needed because the official spring-social-security from SpringSocial is on the classpath
     * @return
     */
    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }


    @Override
    public UsersConnectionRepository getUsersConnectionRepository(
            ConnectionFactoryLocator connectionFactoryLocator) {
        return usersConnectionRepository;
    }

    @Bean
    public ProviderSignInController providerSignInController(
            ConnectionFactoryLocator connectionFactoryLocator,
            UsersConnectionRepository usersConnectionRepository) {
        ProviderSignInController controller = new ProviderSignInController(
                connectionFactoryLocator,
                usersConnectionRepository(),
                new SignInAdapter() {
                    @Override
                    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
                        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));
                        return null;
                    }
                });
        controller.setSignUpUrl("/register");
        return controller;
    }

    @Bean
    public UsersConnectionRepository usersConnectionRepository() {
        return new HibernateUsersConnectionRepository();
    }

    @Bean(initMethod = "workaroundInit")
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository) {
            // Workaround for issue: https://github.com/spring-projects/spring-social/issues/129
            public void workaroundInit() {
                setApplicationUrl("109.75.38.74:8080");
            }
        };
        return connectController;
    }

}
