package com.nibbledebt.intuit.cad.util;

import java.util.ResourceBundle;

public final class PropertyHelper
{
  private static PropertyHelper propertHelper;
  private String version;
  private String requestSource;
  private String requestSourceHeader;
  
  public static synchronized PropertyHelper getInstance()
  {
    if (propertHelper == null) {
      return init();
    }
    return propertHelper;
  }
  
  private static PropertyHelper init()
  {
    propertHelper = new PropertyHelper();
    ResourceBundle bundle = ResourceBundle.getBundle("ipp-aggcat-devkit");
    
    propertHelper.setVersion(bundle.getString("version"));
    propertHelper.setRequestSource(bundle.getString("request.source"));
    propertHelper.setRequestSourceHeader(bundle.getString("request.source.header"));
    return propertHelper;
  }
  
  public String getVersion()
  {
    return this.version;
  }
  
  public void setVersion(String version)
  {
    this.version = version;
  }
  
  public String getRequestSource()
  {
    return this.requestSource;
  }
  
  public void setRequestSource(String requestSource)
  {
    this.requestSource = requestSource;
  }
  
  public String getRequestSourceHeader()
  {
    return this.requestSourceHeader;
  }
  
  public void setRequestSourceHeader(String requestSourceHeader)
  {
    this.requestSourceHeader = requestSourceHeader;
  }
}
