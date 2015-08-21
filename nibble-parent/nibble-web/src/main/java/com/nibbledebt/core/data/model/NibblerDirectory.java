/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * @author Rocky Alam
 *
 */
@NamedQueries({
	@NamedQuery(name="findNibblerDirByUsername", query="from NibblerDirectory n where n.username = :username")
})
@Entity()
@Table(	name="nibbler_directory", 
		uniqueConstraints = {
			@UniqueConstraint(columnNames = {"username"}),
			@UniqueConstraint(columnNames = {"username", "activation_code"}),
			@UniqueConstraint(columnNames = {"username", "reset_code"})
		})
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="nibbler_directory_id"))
})
public class NibblerDirectory  extends AbstractModel{
	@Column(name="username", nullable=false, length=25)
	private String username;
		
	@Column(name="password", nullable=true, length=256)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "nibbler_directory_role", joinColumns = { 
			@JoinColumn(name = "nibbler_directory_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "nibbler_role_id", 
					nullable = false, updatable = false) })
	private Set<NibblerRole> roles;
	
	@Column(name="activation_code", nullable=true, length=100)
	private String activationCode;
	
	@Column(name="reset_code", nullable=true, length=100)
	private String resetCode;
	
	@Column(name="status", nullable=true)
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_ts", nullable = true)
	private Date lastLoginTs;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Nibbler nibbler;

	public NibblerDirectory(){
		super();
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
	 * @return the roles
	 */
	public Set<NibblerRole> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<NibblerRole> roles) {
		this.roles = roles;
	}

	/**
	 * @return the nibbler
	 */
	public Nibbler getNibbler() {
		return nibbler;
	}

	/**
	 * @param nibbler the nibbler to set
	 */
	public void setNibbler(Nibbler nibbler) {
		this.nibbler = nibbler;
	}

	/**
	 * @return the activationCode
	 */
	public String getActivationCode() {
		return activationCode;
	}

	/**
	 * @param activationCode the activationCode to set
	 */
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	/**
	 * @return the resetCode
	 */
	public String getResetCode() {
		return resetCode;
	}

	/**
	 * @param resetCode the resetCode to set
	 */
	public void setResetCode(String resetCode) {
		this.resetCode = resetCode;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the lastLoginTs
	 */
	public Date getLastLoginTs() {
		return lastLoginTs;
	}

	/**
	 * @param lastLoginTs the lastLoginTs to set
	 */
	public void setLastLoginTs(Date lastLoginTs) {
		this.lastLoginTs = lastLoginTs;
	}
	
	
}
