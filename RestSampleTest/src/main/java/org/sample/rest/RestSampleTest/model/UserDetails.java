package org.sample.rest.RestSampleTest.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;




@Entity
@Table (name="UserDetails")
@XmlRootElement
public class UserDetails {
	
	@Id
	private int userID;
	private String userName;
	private Date date;
	
	
	public UserDetails() {

	}

	public UserDetails(int userID, String userName, Date date) {
		this.userID = userID;
		this.userName = userName;
		this.date= new Date();
	}
	
	
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	
	

}
