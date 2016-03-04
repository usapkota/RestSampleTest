package org.sample.rest.RestSampleTest.service;



import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sample.rest.RestSampleTest.database.DatabaseConnection;
import org.sample.rest.RestSampleTest.model.UserDetails;

public class UserService {
	
	    SessionFactory sessionFactory = DatabaseConnection.getSessionFactory();
	
		    

	public  List<UserDetails> getAllUsers(){
        List<UserDetails> users = null;
        Session session = null;
        
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            users = session.createQuery("from UserDetails u").list();
            session.getTransaction().commit();
            
        }catch(Exception ex){
            if (session != null){
                session.getTransaction().rollback();
            }
        }
        finally{
            if (session != null){
                session.close();
            }     
        
    }
        
       return users; 
    }
	
	
	public UserDetails getUserByID(int id){
        UserDetails user = null;
        Session session = null;
        
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            user = (UserDetails) session.createQuery("from UserDetails u where u.id = :ID").setParameter("ID",id).uniqueResult();
            session.getTransaction().commit();
            
        }catch(Exception ex){
            if (session != null){
                session.getTransaction().rollback();
            }
        }
        finally{
            if (session != null){
                session.close();
            }     
        
    }
        
       return user; 
    }
	
	
	public boolean saveUser(UserDetails userdetails){
        boolean hasErrors = false;
        Session session = null;
        
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            //new
            userdetails.setDate(new Date());
            //new
           session.saveOrUpdate(userdetails);
            session.getTransaction().commit();
            
        }catch(Exception ex){
            if (session != null){
                session.getTransaction().rollback();
            }
        }
        finally{
            if (session != null){
                session.close();
            }     
        
    }
        
       return hasErrors; 
    }
	
	public boolean deleteUser(UserDetails userdetails){
        boolean hasErrors = false;
        Session session = null;
        
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
           session.delete(userdetails);
            session.getTransaction().commit();
            
        }catch(Exception ex){
            if (session != null){
                session.getTransaction().rollback();
            }
        }
        finally{
            if (session != null){
                session.close();
            }     
        
    }
        
       return hasErrors; 
    }

	
}
	
	

