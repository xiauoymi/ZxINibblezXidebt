package com.nibbledebt.intuit.cad.interceptor;

import java.io.InputStream;

import org.apache.http.HttpResponse;

public class ResponseElements
{
  private HttpResponse httpResponse;
  private Object response;
  private InputStream responseStream;
  
  public HttpResponse getHttpResponse()
  {
    return this.httpResponse;
  }
  
  public void setHttpResponse(HttpResponse httpResponse)
  {
    this.httpResponse = httpResponse;
  }
  
  public Object getResponse()
  {
    return this.response;
  }
  
  public void setResponse(Object response)
  {
    this.response = response;
  }
  
  public InputStream getResponseStream()
  {
    return this.responseStream;
  }
  
  public void setResponseStream(InputStream responseStream)
  {
    this.responseStream = responseStream;
  }
}
