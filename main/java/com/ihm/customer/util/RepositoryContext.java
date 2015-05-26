package com.ihm.customer.util;
/**

@Author sardarwaqasahmed
@date   NOV 9, 2014
 1.0
**/

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RepositoryContext {

	/** The logger. */
	private static Logger logger = Logger.getLogger(RepositoryContext.class);


    private static BeanFactory context = init();

    /**
     * Initialize the Application Context.
     * @return {@link ApplicationContext}
     */
    private static BeanFactory init(){
        logger.debug("Initializing Application Context.... " );
        String configLocations[] = new String[] {"classpath*:spring-main.xml"};
        context = (BeanFactory) new ClassPathXmlApplicationContext(configLocations);
        logger.debug("Context Initilized Successfully...");
        return context ;
    }
    
    /**
     * Return object from the ApplicationContext with the provided name or Null would be returned if no object with this name is registered in the context
     * @param name
     * @return
     */
    public static Object getBean(String name){
        return context.getBean(name);
    }
    
    
    public static void main(String[] args) {
        try{
            
        }catch(Exception e){
            System.out.println(e.getClass());
            e.printStackTrace();
        }
    }
    
	public static <T> T getBean(Class<T> clazz) {
		logger.debug("Getting bean >>> " + clazz);
		return context.getBean(clazz);
	}

}

