/**
 * 
 */
package com.nibbledebt.nibble.integration.model;

import android.graphics.Bitmap;

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

    private String urlLogonApp;

    private String name;

    private String urlHomeApp;

    private String accountTypeDescription;

    private Bitmap logo;

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
	 * @return the urlLogonApp
	 */
	public String getUrlLogonApp() {
		return urlLogonApp;
	}



	/**
	 * @param urlLogonApp the urlLogonApp to set
	 */
	public void setUrlLogonApp(String urlLogonApp) {
		this.urlLogonApp = urlLogonApp;
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
	 * @return the urlHomeApp
	 */
	public String getUrlHomeApp() {
		return urlHomeApp;
	}



	/**
	 * @param urlHomeApp the urlHomeApp to set
	 */
	public void setUrlHomeApp(String urlHomeApp) {
		this.urlHomeApp = urlHomeApp;
	}



	/**
	 * @return the accountTypeDescription
	 */
	public String getAccountTypeDescription() {
		return accountTypeDescription;
	}



	/**
	 * @param accountTypeDescription the accountTypeDescription to set
	 */
	public void setAccountTypeDescription(String accountTypeDescription) {
		this.accountTypeDescription = accountTypeDescription;
	}

	public Bitmap getLogo() {
		return logo;
	}

	public void setLogo(Bitmap logo) {
		this.logo = logo;
	}

	@Override
    public String toString(){
    	return ToStringBuilder.reflectionToString(this);
    }
}