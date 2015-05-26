/**
 * 
 */
package com.ihm.security;

/**
 * <p>
 * this exception throws when user not exists
 * in login session
 * 
 * @author Artur Yolchyan
 */
public class UserCredentialsException extends RuntimeException {

	/**
	 * the serialVersionUID generated automatically by JVM
	 */
	private static final long serialVersionUID = 6654678119587833665L;
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Spring Security issue";
	}
}
