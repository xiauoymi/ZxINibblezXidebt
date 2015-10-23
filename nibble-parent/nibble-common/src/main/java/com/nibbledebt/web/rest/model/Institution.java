/**
 * 
 */
package com.nibbledebt.web.rest.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author alam_home
 *
 */
@JsonRootName("institution")
public class Institution
{
    private String id;

    private String name;

    private String description;
    
    private String logoCode;

    private String username;
    
    private String password;
    
    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}



	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}



	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}



	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}



	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @return the logoCode
	 */
	public String getLogoCode() {
		return logoCode;
	}



	/**
	 * @param logoCode the logoCode to set
	 */
	public void setLogoCode(String logoCode) {
		this.logoCode = logoCode;
	}


	@Override
    public String toString(){
    	return ToStringBuilder.reflectionToString(this);
    }
}