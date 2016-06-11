package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;

import io.swagger.client.model.*;

import java.util.*;

import io.swagger.client.model.CustomerListResponse;
import io.swagger.client.model.Unit$;
import io.swagger.client.model.CreateCustomer;
import io.swagger.client.model.Customer;
import io.swagger.client.model.UpdateCustomer;
import io.swagger.client.model.DocumentListResponse;
import io.swagger.client.model.CustomerOAuthToken;
import io.swagger.client.model.IavToken;

import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

import javax.ws.rs.core.MediaType;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

import java.net.URL;
import java.net.MalformedURLException;

public class CustomersApi {
  private ApiClient apiClient;

  private String[] authNames = new String[] { "oauth2" };

  public CustomersApi() {
    this(Configuration.getDefaultApiClient());
  }

  public CustomersApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * Get a list of customers.
   * 
   * @param limit How many results to return.
   * @param offset How many results to skip.
   * @return CustomerListResponse
   */
  public CustomerListResponse list (Integer limit, Integer offset) throws ApiException {
    Object postBody = null;
    

    

    // create path and map variables
    String path = "/customers".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    if (limit != null)
      queryParams.put("limit", apiClient.parameterToString(limit));
    if (offset != null)
      queryParams.put("offset", apiClient.parameterToString(offset));
    

    

    final String[] accepts = {
      "application/vnd.dwolla.v1.hal+json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    if(contentType.startsWith("multipart/form-data")) {
      boolean hasFields = false;
      FormDataMultiPart mp = new FormDataMultiPart();
      
      if(hasFields)
        postBody = mp;
    }
    else {
      
    }

    try {
      String response = apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, this.authNames);
      if(response != null){
        return (CustomerListResponse) apiClient.deserialize(response, "", CustomerListResponse.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Create a new customer.
   * 
   * @param body Customer to create.
   * @return Unit$
   */
  public Unit$ create (CreateCustomer body) throws ApiException {
    Object postBody = body;
    

    

    // create path and map variables
    String path = "/customers".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    final String[] accepts = {
      "application/vnd.dwolla.v1.hal+json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      "application/vnd.dwolla.v1.hal+json"
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    if(contentType.startsWith("multipart/form-data")) {
      boolean hasFields = false;
      FormDataMultiPart mp = new FormDataMultiPart();
      
      if(hasFields)
        postBody = mp;
    }
    else {
      
    }

    try {
      String response = apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept, contentType, this.authNames);
      if(response != null){
    	  System.out.println("CreateCustomer ==> "+response);
        return (Unit$) apiClient.deserialize(response, "", Unit$.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Get a customer by id
   * 
   * @param id Id of customer to get.
   * @return Customer
   */
  public Customer getCustomer (String id) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'id' is set
    if (id == null) {
       throw new ApiException(400, "Missing the required parameter 'id' when calling getCustomer");
    }
    

    
    // if a URL is provided, extract the ID
    URL u;
    try {
      u = new URL(id);
      id = id.substring(id.lastIndexOf('/') + 1);
    }
    catch (MalformedURLException mue) {
      u = null;
    }
    

    // create path and map variables
    String path = "/customers/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    final String[] accepts = {
      "application/vnd.dwolla.v1.hal+json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      "application/vnd.dwolla.v1.hal+json"
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    if(contentType.startsWith("multipart/form-data")) {
      boolean hasFields = false;
      FormDataMultiPart mp = new FormDataMultiPart();
      
      if(hasFields)
        postBody = mp;
    }
    else {
      
    }

    try {
      String response = apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, this.authNames);
      if(response != null){
        return (Customer) apiClient.deserialize(response, "", Customer.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Update customer record. Personal customer records are re-verified upon update.
   * 
   * @param body Customer to update.
   * @param id Id of customer to update.
   * @return Customer
   */
  public Customer updateCustomer (UpdateCustomer body, String id) throws ApiException {
    Object postBody = body;
    
    // verify the required parameter 'id' is set
    if (id == null) {
       throw new ApiException(400, "Missing the required parameter 'id' when calling updateCustomer");
    }
    

    
    // if a URL is provided, extract the ID
    URL u;
    try {
      u = new URL(id);
      id = id.substring(id.lastIndexOf('/') + 1);
    }
    catch (MalformedURLException mue) {
      u = null;
    }
    

    // create path and map variables
    String path = "/customers/{id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    final String[] accepts = {
      "application/vnd.dwolla.v1.hal+json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      "application/vnd.dwolla.v1.hal+json"
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    if(contentType.startsWith("multipart/form-data")) {
      boolean hasFields = false;
      FormDataMultiPart mp = new FormDataMultiPart();
      
      if(hasFields)
        postBody = mp;
    }
    else {
      
    }

    try {
      String response = apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept, contentType, this.authNames);
      if(response != null){
        return (Customer) apiClient.deserialize(response, "", Customer.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Get documents uploaded for customer.
   * 
   * @param id ID of customer.
   * @return DocumentListResponse
   */
  public DocumentListResponse getCustomerDocuments (String id) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'id' is set
    if (id == null) {
       throw new ApiException(400, "Missing the required parameter 'id' when calling getCustomerDocuments");
    }
    

    
    // if a URL is provided, extract the ID
    URL u;
    try {
      u = new URL(id);
      id = id.substring(id.lastIndexOf('/') + 1);
    }
    catch (MalformedURLException mue) {
      u = null;
    }
    

    // create path and map variables
    String path = "/customers/{id}/documents".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    final String[] accepts = {
      "application/vnd.dwolla.v1.hal+json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    if(contentType.startsWith("multipart/form-data")) {
      boolean hasFields = false;
      FormDataMultiPart mp = new FormDataMultiPart();
      
      if(hasFields)
        postBody = mp;
    }
    else {
      
    }

    try {
      String response = apiClient.invokeAPI(path, "GET", queryParams, postBody, headerParams, formParams, accept, contentType, this.authNames);
      if(response != null){
        return (DocumentListResponse) apiClient.deserialize(response, "", DocumentListResponse.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Upload a verification document.
   * 
   * @return Unit$
   */
  public Unit$ uploadDocument () throws ApiException {
    Object postBody = null;
    

    

    // create path and map variables
    String path = "/customers/{id}/documents".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    final String[] accepts = {
      
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      "multipart/form-data"
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    if(contentType.startsWith("multipart/form-data")) {
      boolean hasFields = false;
      FormDataMultiPart mp = new FormDataMultiPart();
      
      if(hasFields)
        postBody = mp;
    }
    else {
      
    }

    try {
      String response = apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept, contentType, this.authNames);
      if(response != null){
        return (Unit$) apiClient.deserialize(response, "", Unit$.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Create an OAuth token that is capable of adding a financial institution for the given customer.
   * 
   * @param id ID of customer.
   * @return CustomerOAuthToken
   */
  public CustomerOAuthToken createFundingSourcesTokenForCustomer (String id) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'id' is set
    if (id == null) {
       throw new ApiException(400, "Missing the required parameter 'id' when calling createFundingSourcesTokenForCustomer");
    }
    

    
    // if a URL is provided, extract the ID
    URL u;
    try {
      u = new URL(id);
      id = id.substring(id.lastIndexOf('/') + 1);
    }
    catch (MalformedURLException mue) {
      u = null;
    }
    

    // create path and map variables
    String path = "/customers/{id}/funding-sources-token".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    final String[] accepts = {
      "application/vnd.dwolla.v1.hal+json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    if(contentType.startsWith("multipart/form-data")) {
      boolean hasFields = false;
      FormDataMultiPart mp = new FormDataMultiPart();
      
      if(hasFields)
        postBody = mp;
    }
    else {
      
    }

    try {
      String response = apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept, contentType, this.authNames);
      if(response != null){
        return (CustomerOAuthToken) apiClient.deserialize(response, "", CustomerOAuthToken.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
  /**
   * Get iav token for customer.
   * 
   * @param id ID of customer.
   * @return IavToken
   */
  public IavToken getCustomerIavToken (String id) throws ApiException {
    Object postBody = null;
    
    // verify the required parameter 'id' is set
    if (id == null) {
       throw new ApiException(400, "Missing the required parameter 'id' when calling getCustomerIavToken");
    }
    

    
    // if a URL is provided, extract the ID
    URL u;
    try {
      u = new URL(id);
      id = id.substring(id.lastIndexOf('/') + 1);
    }
    catch (MalformedURLException mue) {
      u = null;
    }
    

    // create path and map variables
    String path = "/customers/{id}/iav-token".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    

    

    final String[] accepts = {
      "application/vnd.dwolla.v1.hal+json"
    };
    final String accept = apiClient.selectHeaderAccept(accepts);

    final String[] contentTypes = {
      
    };
    final String contentType = apiClient.selectHeaderContentType(contentTypes);

    if(contentType.startsWith("multipart/form-data")) {
      boolean hasFields = false;
      FormDataMultiPart mp = new FormDataMultiPart();
      
      if(hasFields)
        postBody = mp;
    }
    else {
      
    }

    try {
      String response = apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept, contentType, this.authNames);
      if(response != null){
        return (IavToken) apiClient.deserialize(response, "", IavToken.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      throw ex;
    }
  }
  
}
