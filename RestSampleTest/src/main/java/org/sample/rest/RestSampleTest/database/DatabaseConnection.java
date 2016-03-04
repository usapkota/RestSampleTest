package org.sample.rest.RestSampleTest.database;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {
	
	 

	 
	 //original
  
  		private static  SessionFactory sessionFactory;
	    public static SessionFactory getSessionFactory() {
	        if (sessionFactory == null ) {
	            sessionFactory = new Configuration().configure().buildSessionFactory();
	        }
	        return sessionFactory;
	    }
	 
//	 private static final SessionFactory sessionFactory;
//	    
//	    static {
//	        try {
//	         
//	            sessionFactory = new Configuration().configure().buildSessionFactory();
//	        } catch (Throwable ex) {
//	            
//	            System.err.println("Initial SessionFactory creation failed." + ex);
//	            throw new ExceptionInInitializerError(ex);
//	        }
//	    }
//	    
//	    public static SessionFactory getSessionFactory() {
//	        return sessionFactory;
//	    }
	 
	 
}

