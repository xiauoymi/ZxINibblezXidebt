package com.nibbledebt.intuit.cad.interceptor;

public class IntuitMessage
{
  private RequestElements requestElements = new RequestElements();

  private ResponseElements responseElements = new ResponseElements();

  public RequestElements getRequestElements()
  {
    return this.requestElements;
  }

  public ResponseElements getResponseElements()
  {
    return this.responseElements;
  }
}