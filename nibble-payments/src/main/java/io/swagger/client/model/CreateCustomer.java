package io.swagger.client.model;


import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;



@ApiModel(description = "")
public class CreateCustomer  {
  
  private String firstName = null;
  private String lastName = null;
  private String email = null;
  private String ipAddress = null;
  private String type = null;
  private String address1 = null;
  private String address2 = null;
  private String city = null;
  private String state = null;
  private String postalCode = null;
  private String dateOfBirth = null;
  private String ssn = null;
  private String phone = null;
  private String businessName = null;
  private String businessType = null;
  private String businessClassification = null;
  private String ein = null;
  private String doingBusinessAs = null;
  private String website = null;
  private String locationHeader;


  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("ipAddress")
  public String getIpAddress() {
    return ipAddress;
  }
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("type")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("address1")
  public String getAddress1() {
    return address1;
  }
  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("address2")
  public String getAddress2() {
    return address2;
  }
  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("city")
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("state")
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("postalCode")
  public String getPostalCode() {
    return postalCode;
  }
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("dateOfBirth")
  public String getDateOfBirth() {
    return dateOfBirth;
  }
  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("ssn")
  public String getSsn() {
    return ssn;
  }
  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("phone")
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("businessName")
  public String getBusinessName() {
    return businessName;
  }
  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("businessType")
  public String getBusinessType() {
    return businessType;
  }
  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("businessClassification")
  public String getBusinessClassification() {
    return businessClassification;
  }
  public void setBusinessClassification(String businessClassification) {
    this.businessClassification = businessClassification;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("ein")
  public String getEin() {
    return ein;
  }
  public void setEin(String ein) {
    this.ein = ein;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("doingBusinessAs")
  public String getDoingBusinessAs() {
    return doingBusinessAs;
  }
  public void setDoingBusinessAs(String doingBusinessAs) {
    this.doingBusinessAs = doingBusinessAs;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("website")
  public String getWebsite() {
    return website;
  }
  public void setWebsite(String website) {
    this.website = website;
  }

  


  /**
   * Used to deserialize Location field in
   * HTTP headers, primarily for HAL-styled
   * POST requests.
   **/
  @JsonProperty("Location")
  public String getLocationHeader() { return locationHeader; }
  public void setLocationHeader(ArrayList<String> locationHeader) { this.locationHeader = locationHeader.get(0); }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateCustomer {\n");
    
    sb.append("  firstName: ").append(firstName).append("\n");
    sb.append("  lastName: ").append(lastName).append("\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  ipAddress: ").append(ipAddress).append("\n");
    sb.append("  type: ").append(type).append("\n");
    sb.append("  address1: ").append(address1).append("\n");
    sb.append("  address2: ").append(address2).append("\n");
    sb.append("  city: ").append(city).append("\n");
    sb.append("  state: ").append(state).append("\n");
    sb.append("  postalCode: ").append(postalCode).append("\n");
    sb.append("  dateOfBirth: ").append(dateOfBirth).append("\n");
    sb.append("  ssn: ").append(ssn).append("\n");
    sb.append("  phone: ").append(phone).append("\n");
    sb.append("  businessName: ").append(businessName).append("\n");
    sb.append("  businessType: ").append(businessType).append("\n");
    sb.append("  businessClassification: ").append(businessClassification).append("\n");
    sb.append("  ein: ").append(ein).append("\n");
    sb.append("  doingBusinessAs: ").append(doingBusinessAs).append("\n");
    sb.append("  website: ").append(website).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
