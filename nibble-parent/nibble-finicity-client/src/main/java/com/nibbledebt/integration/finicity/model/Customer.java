/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.finicity.model;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author ralam
 *
 */
@JsonRootName("customer")
public class Customer {
	private String username;
	private String firstName;
	private String lastName;
	private String id;
    private String type;
	private Long createdDate;
	
	/**
	 * Defaut
	 */
	public Customer() {}
	
	/**
	 * @param id
	 * @param createdDate
	 */
	public Customer(String id, Long createdDate) {
		super();
		this.id = id;
		this.createdDate = createdDate;
	}

	/**
	 * @param username
	 * @param firstName
	 * @param lastName
	 */
	public Customer(String username, String firstName, String lastName) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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
	 * @return the createdDate
	 */
	public Long getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
