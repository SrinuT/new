/**
 * 
 */
package com.ihm.security;

import java.util.ArrayList;
import java.util.List;

import com.ihm.customer.faces.bean.SessionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.ihm.customer.dto.PersonDto;
import com.ihm.customer.service.PersonService;

/**
 * <p>
 * this class for Authentication management for ihm
 * 
 * @author Artur Yolchyan
 */
@SuppressWarnings("deprecation")
public class AuthenticationProviderImpl implements AuthenticationProvider {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationProviderImpl.class);
	
	private static final String LOGIN_ERROR_MESSAGE = "Wrong username or/and password";
	
	
	@Autowired
	private PersonService personService;
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {				
		String email = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		PersonDto loginPerson = personService.personByCredentials(email, password);
		
		if (loginPerson == null) {
			LOG.error("invalid user want to login with username: " + email);
			throw new BadCredentialsException(LOGIN_ERROR_MESSAGE);
		}
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		
		return new UsernamePasswordAuthenticationToken(email, password, grantedAuthorities);	
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	public boolean supports(Class<? extends Object> authentication) {
		/*Used this http://stackoverflow.com/questions/3205469/custom-authentication-in-spring*/
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)); 
	}

}
