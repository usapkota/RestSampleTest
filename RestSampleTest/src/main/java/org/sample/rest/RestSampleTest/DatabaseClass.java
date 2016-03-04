package org.sample.rest.RestSampleTest;

import java.util.HashMap;
import java.util.Map;

import org.sample.rest.RestSampleTest.model.UserDetails;

public class DatabaseClass {
	
	private static Map<Integer, UserDetails> userdetails = new HashMap<>();
	
	
	public static Map<Integer, UserDetails> getUsers(){
		return userdetails;
	}
	


}
