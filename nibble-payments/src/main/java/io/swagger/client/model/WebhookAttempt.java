package io.swagger.client.model;

import io.swagger.client.model.WebhookHttpResponse;
import io.swagger.client.model.WebhookHttpRequest;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;



@ApiModel(description = "")
public class WebhookAttempt  {
  
  private String id = null;
  private WebhookHttpRequest request = null;
  private WebhookHttpResponse response = null;
  private String locationHeader;


  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("request")
  public WebhookHttpRequest getRequest() {
    return request;
  }
  public void setRequest(WebhookHttpRequest request) {
    this.request = request;
  }

  
  /**
   **/
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("response")
  public WebhookHttpResponse getResponse() {
    return response;
  }
  public void setResponse(WebhookHttpResponse response) {
    this.response = response;
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
    sb.append("class WebhookAttempt {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  request: ").append(request).append("\n");
    sb.append("  response: ").append(response).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
