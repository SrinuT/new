package com.ihm.customer.util;


import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Sardar Waqas Ahmed
 * @Date 29-11-2014
 */
public class PropertiesUtil {

	/** The Constant logger. */
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PropertiesUtil.class);

	/**
	 * Gets the property.
	 * 
	 * @param name
	 *            the name
	 * @return the property
	 */
	public static String getProperty(String name) {
		logger.debug("Retrieving value for property: " + name);
		return PropertiesHolder.getInstance().getProperty(name)==null?null:PropertiesHolder.getInstance().getProperty(name).trim();
	}

	/**
	 * Gets the property.
	 * 
	 * @param name
	 *            the name
	 * @param defaultValue
	 *            the default value
	 * @return the property
	 */
	public static String getProperty(String name, String defaultValue) {
		logger.debug("Retrieving value for property: " + name);
		return PropertiesHolder.getInstance().getProperty(name, defaultValue)==null?null:PropertiesHolder.getInstance().getProperty(name, defaultValue).trim();
	}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public static Properties getProperties() {
		return PropertiesHolder.getInstance();
	}

	/**
	 * The Class PropertiesHolder.
	 */
	private static class PropertiesHolder {

		/** The properties. */
		private static Properties properties = init();

		/**
		 * Gets the single instance of PropertiesHolder.
		 * 
		 * @return single instance of PropertiesHolder
		 */
		public static Properties getInstance() {
			return properties;
		}

		/**
		 * Inits the.
		 * 
		 * @return the properties
		 */
		private static Properties init() {

			properties = new Properties();
			try {

				String propEnv = System.getenv("ihm.properties");

				if (propEnv == null || propEnv.length() == 0) {
					propEnv = System.getProperty("ihm.properties");
				}
				
				logger.debug("Application Properties File: " + propEnv);

				properties.load(new FileInputStream(propEnv));
				
				properties.list(System.out);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			return properties;
		}
	}

}
