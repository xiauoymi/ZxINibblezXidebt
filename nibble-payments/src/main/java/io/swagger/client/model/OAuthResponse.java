package io.swagger.client.model;

import io.swagger.client.model.HalLink;
import java.util.*;
import java.util.Map;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;



@ApiModel(description = "")
public class OAuthResponse  {
  
  private Map<String, HalLink> links = new HashMap<String, HalLink>() ;
  private String accessToken = null;
  private Double expiresIn = null;
  private String refreshToken = null;
  private Double refreshExpiresIn = null;
  private String tokenType = null;
  private String scope = null;
  private String locationHeader;


  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("_links")
  public Map<String, HalLink> getLinks() {
    return links;
  }
  public void setLinks(Map<String, HalLink> links) {
    this.links = links;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("access_token")
  public String getAccessToken() {
    return accessToken;
  }
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("expires_in")
  public Double getExpiresIn() {
    return expiresIn;
  }
  public void setExpiresIn(Double expiresIn) {
    this.expiresIn = expiresIn;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("refresh_token")
  public String getRefreshToken() {
    return refreshToken;
  }
  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("refresh_expires_in")
  public Double getRefreshExpiresIn() {
    return refreshExpiresIn;
  }
  public void setRefreshExpiresIn(Double refreshExpiresIn) {
    this.refreshExpiresIn = refreshExpiresIn;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("token_type")
  public String getTokenType() {
    return tokenType;
  }
  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("scope")
  public String getScope() {
    return scope;
  }
  public void setScope(String scope) {
    this.scope = scope;
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
    sb.append("class OAuthResponse {\n");
    
    sb.append("  links: ").append(links).append("\n");
    sb.append("  accessToken: ").append(accessToken).append("\n");
    sb.append("  expiresIn: ").append(expiresIn).append("\n");
    sb.append("  refreshToken: ").append(refreshToken).append("\n");
    sb.append("  refreshExpiresIn: ").append(refreshExpiresIn).append("\n");
    sb.append("  tokenType: ").append(tokenType).append("\n");
    sb.append("  scope: ").append(scope).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
