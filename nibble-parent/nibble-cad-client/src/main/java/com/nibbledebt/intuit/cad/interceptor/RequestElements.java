package com.nibbledebt.intuit.cad.interceptor;

import java.util.HashMap;
import java.util.Map;

import com.nibbledebt.intuit.cad.core.Context;

public class RequestElements
{
  public static final String HEADER_PARAM_CONTENT_TYPE = "content-type";
  public static final String HEADER_PARAM_CHALLENGE_SESSION_ID = "challengeSessionId";
  public static final String HEADER_PARAM_CHALLENGE_NODE_ID = "challengeNodeId";
  public static final String HEADER_PARAM_RANGE = "Range";
  public static final String REQ_PARAM_RESOURCE_URL = "resource-url";
  public static final String REQ_PARAM_METHOD_TYPE = "method-type";
  public static final String REQ_PARAM_ENTITY_NAME = "entity-name";
  public static final String REQ_PARAM_ID = "id";
  public static final String REQ_PARAM_ACTION = "action";
  public static final String QUERY_PARAM_TXN_START_DATE = "txnStartDate";
  public static final String QUERY_PARAM_TXN_END_DATE = "txnEndDate";
  public static final String QUERY_PARAM_EXPLICIT_REFRESH = "refresh";
  public static final String HEADER_PARAM_ACCEPT_ENCODING = "Accept-Encoding";
  public static final String HEADER_PARAM_CONTENT_ENCODING = "Content-Encoding";
  private Map<String, String> requestHeaders = new HashMap();

  private Map<String, String> requestParameters = new HashMap();

  private Map<String, String> queryParameters = new HashMap();
  private Context context;
  private Object objectToSerialize;
  private String serializedData;
  private String resourceTypeIdentifier;
  private String apiType;

  public Map<String, String> getRequestHeaders()
  {
    return this.requestHeaders;
  }

  public Map<String, String> getRequestParameters()
  {
    return this.requestParameters;
  }

  public Context getContext()
  {
    return this.context;
  }

  public void setContext(Context context)
  {
    this.context = context;
  }

  public Object getObjectToSerialize()
  {
    return this.objectToSerialize;
  }

  public void setObjectToSerialize(Object objectToSerialize)
  {
    this.objectToSerialize = objectToSerialize;
  }

  public String getSerializedData()
  {
    return this.serializedData;
  }

  public void setSerializedData(String serializedData)
  {
    this.serializedData = serializedData;
  }

  public Map<String, String> getQueryParameters()
  {
    return this.queryParameters;
  }

  public String getResourceTypeIdentifier()
  {
    return this.resourceTypeIdentifier;
  }

  public void setResourceTypeIdentifier(String resourceTypeIdentifier)
  {
    this.resourceTypeIdentifier = resourceTypeIdentifier;
  }

  public String getApiType()
  {
    return this.apiType;
  }

  public void setApiType(String apiType)
  {
    this.apiType = apiType;
  }
}