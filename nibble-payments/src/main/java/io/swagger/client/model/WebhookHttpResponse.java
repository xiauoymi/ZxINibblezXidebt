package io.swagger.client.model;

import io.swagger.client.model.WebhookHeader;
import java.util.*;
import java.util.Date;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;



@ApiModel(description = "")
public class WebhookHttpResponse  {
  
  private Date timestamp = null;
  private List<WebhookHeader> headers = new ArrayList<WebhookHeader>() ;
  private Integer statusCode = null;
  private String body = null;
  private String locationHeader;


  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("timestamp")
  public Date getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("headers")
  public List<WebhookHeader> getHeaders() {
    return headers;
  }
  public void setHeaders(List<WebhookHeader> headers) {
    this.headers = headers;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("statusCode")
  public Integer getStatusCode() {
    return statusCode;
  }
  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("body")
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
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
    sb.append("class WebhookHttpResponse {\n");
    
    sb.append("  timestamp: ").append(timestamp).append("\n");
    sb.append("  headers: ").append(headers).append("\n");
    sb.append("  statusCode: ").append(statusCode).append("\n");
    sb.append("  body: ").append(body).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
