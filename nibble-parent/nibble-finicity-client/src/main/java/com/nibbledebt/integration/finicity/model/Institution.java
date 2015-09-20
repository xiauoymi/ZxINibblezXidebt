/**
 * 
 */
package com.nibbledebt.integration.finicity.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author alam_home
 *
 */
@XmlRootElement
@XmlType(propOrder = {"id", "urlLogonApp", "name", "urlHomeApp", "accountTypeDescription"})
public class Institution
{
	@XmlElement(name="id")
    private String id;

	@XmlElement(name="urlLogonApp")
    private String urlLogonApp;

	@XmlElement(name="name")
    private String name;

	@XmlElement(name="urlHomeApp")
    private String urlHomeApp;

	@XmlElement(name="accountTypeDescription")
    private String accountTypeDescription;

   

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



	@Override
    public String toString(){
    	return ToStringBuilder.reflectionToString(this);
    }
}