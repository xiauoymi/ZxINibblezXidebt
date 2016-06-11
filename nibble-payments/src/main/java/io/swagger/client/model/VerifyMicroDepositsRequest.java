package io.swagger.client.model;

import io.swagger.client.model.Amount;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;



@ApiModel(description = "")
public class VerifyMicroDepositsRequest  {
  
  private Amount amount1 = null;
  private Amount amount2 = null;
  private String locationHeader;


  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("amount1")
  public Amount getAmount1() {
    return amount1;
  }
  public void setAmount1(Amount amount1) {
    this.amount1 = amount1;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("amount2")
  public Amount getAmount2() {
    return amount2;
  }
  public void setAmount2(Amount amount2) {
    this.amount2 = amount2;
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
    sb.append("class VerifyMicroDepositsRequest {\n");
    
    sb.append("  amount1: ").append(amount1).append("\n");
    sb.append("  amount2: ").append(amount2).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
