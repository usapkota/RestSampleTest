package org.sample.rest.RestSampleTest.resource;


import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sample.rest.RestSampleTest.model.UserDetails;
import org.sample.rest.RestSampleTest.service.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	
	UserService userservice = new UserService();
	
	//get all users
	@GET
//	@Path("/getAllUsers")
	public List<UserDetails> getAllUsersInXML() {
        return userservice.getAllUsers();
    } 

  
  // single user by Id
  @GET
  @Path("/{id}")
  public UserDetails getUserByIdJSON(@PathParam("id")int id){
      return userservice.getUserByID(id);
  }
	
  
/*  //insert new user 
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{userId}/{userName}")
  public String saveUser(@PathParam("userId") int userId, @PathParam("userName") String userName){
	  UserDetails userdetails = new UserDetails();
	  userdetails.setUserID(userId);
	  userdetails.setUserName(userName);
	  userdetails.setDate(new Date());
	  
	  if (!userservice.saveUser(userdetails)){
		  return "{\"status\":\"ok\"}"; 
	  }
	  else {
		  return "{\"status\":\" not ok\"}";
	  }
	  
  }*/
  
  //insert new user test
  @POST
  public String saveUser(UserDetails userdetails){
	  if (!userservice.saveUser(userdetails)){
		  return "{\"status\":\"ok\"}"; 
	  }
	  else {
		  return "{\"status\":\" not ok\"}";
	  }
	  
  } 
  
/*  //update new user 
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{userId}/{userName}")
  public String updateUser(@PathParam("userId") int userId, @PathParam("userName") String userName){
	  UserDetails userdetails = new UserDetails();
	  userdetails.setUserID(userId);
	  userdetails.setUserName(userName);
	  userdetails.setDate(new Date());
	  
	  if (!userservice.saveUser(userdetails)){
		  return "{\"status\":\"ok\"}"; 
	  }
	  else {
		  return "{\"status\":\" not ok\"}";
	  }
	  
  }*/
  
  
  //update new user test
  @PUT
  @Path("/{userId}")
  public String updateUser(@PathParam("userId") int userId, UserDetails userdetails ){
	  if (!userservice.saveUser(userdetails)){
		  return "{\"status\":\"ok\"}"; 
	  }
	  else {
		  return "{\"status\":\" not ok\"}";
	  }
	  
  }
  
  
  
 
  
  //delete new user 
  @DELETE
  @Path("/{userId}")
  public String deleteUser(@PathParam("userId") int userId){
	  UserDetails userdetails = new UserDetails();
	  userdetails.setUserID(userId);
	  
	  if (!userservice.deleteUser(userdetails)){
		  return "{\"status\":\"ok\"}"; 
	  }
	  else {
		  return "{\"status\":\" not ok\"}";
	  }
	  
  }
	//test
	@GET
	@Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }




	
	
}
