/**
 * 
 */
package com.nibbledebt.core.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Rocky Alam
 *
 */
@Entity()
@Table(	name="persistent_logins")
public class PersistentLogin {
	@Id
	@Column(name="series", nullable=false, length=64)
	private String series;
	
	@Column(name="username", nullable=false, length=64)
	private String username;
		
	@Column(name="token", nullable=false, length=64)
	private String token;
	
	@Column(name="last_used", nullable=false, length=64)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUsed;

	/**
	 * @return the series
	 */
	public String getSeries() {
		return series;
	}

	/**
	 * @param series the series to set
	 */
	public void setSeries(String series) {
		this.series = series;
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
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the lastUsed
	 */
	public Date getLastUsed() {
		return lastUsed;
	}

	/**
	 * @param lastUsed the lastUsed to set
	 */
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}
	
	
}
