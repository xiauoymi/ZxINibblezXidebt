/**
 * 
 */
package com.nibbledebt.domain.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author alam_home
 *
 */
public class Institution {

    private String id;

    private String urlLogonApp;

    private String name;

    private String urlHomeApp;

    private String accountTypeDescription;

    private String logoCode;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlLogonApp() {
        return urlLogonApp;
    }

    public void setUrlLogonApp(String urlLogonApp) {
        this.urlLogonApp = urlLogonApp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlHomeApp() {
        return urlHomeApp;
    }

    public void setUrlHomeApp(String urlHomeApp) {
        this.urlHomeApp = urlHomeApp;
    }

    public String getAccountTypeDescription() {
        return accountTypeDescription;
    }

    public void setAccountTypeDescription(String accountTypeDescription) {
        this.accountTypeDescription = accountTypeDescription;
    }

    public String getLogoCode() {
        return logoCode;
    }

    public void setLogoCode(String logoCode) {
        this.logoCode = logoCode;
    }

    @Override
    public String toString(){
    	return ToStringBuilder.reflectionToString(this);
    }
}