package org.sample.rest.RestSampleTest.resource;


import java.util.Date;
import java.util.List;


import org.sample.rest.RestSampleTest.model.UserDetails;
import org.sample.rest.RestSampleTest.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@Api(
        value = "User Details",
        description = "Methods to manage Sensitive Data Codes",
        consumes = "application/json",
        produces = "application/json") 
public class UserResource {
	
	UserService userservice = new UserService();
	
    /**
     * Get all User Details
     * 
     */
    @ApiOperation(
            httpMethod = "GET",
            value = "Get all SensitiveDataCode records",
            response = UserDetails.class,
            responseContainer = "List")
    @ApiImplicitParam(
            name = "access_token",
            required = false,
            dataType = "integer",
            paramType = "header")
	 @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public List<UserDetails> getAllUsers() {
        return userservice.getAllUsers();
    } 

  
  // single user by Id
	 @RequestMapping(value = "/getAllUsers/{id}", method = RequestMethod.GET)
  public UserDetails getUserByIdJSON( @ApiParam(value = "Id of User Details", required = true) @PathVariable int id){
      return userservice.getUserByID(id);
  }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
  /*
  
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

*/


	
	
}
