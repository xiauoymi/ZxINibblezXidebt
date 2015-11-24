package com.nibbledebt.intuit.cad.interceptor;

import com.nibbledebt.intuit.cad.core.ContentType;
import com.nibbledebt.intuit.cad.exception.AggCatException;
import com.nibbledebt.intuit.cad.util.Config;
import com.nibbledebt.intuit.cad.util.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.LoggerFactory;

public class PrepareRequestInterceptor
  implements Interceptor
{
  private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PrepareRequestInterceptor.class);
  
  public void execute(IntuitMessage intuitMessage)
    throws AggCatException
  {
    LOG.debug("Enter PrepareRequestInterceptor...");
    
    RequestElements requestElements = intuitMessage.getRequestElements();
    Map<String, String> requestParameters = requestElements.getRequestParameters();
    
    requestParameters.put("resource-url", prepareUri(requestParameters, requestElements.getQueryParameters(), requestElements.getApiType()));
    
    Map<String, String> requestHeaders = requestElements.getRequestHeaders();
    if (!requestHeaders.containsKey("content-type")) {
      requestHeaders.put("content-type", ContentType.XML.toString());
    }
    LOG.debug("Exit PrepareRequestInterceptor.");
  }
  
  private String prepareUri(Map<String, String> requestParameters, Map<String, String> queryParameters, String apiType)
    throws AggCatException
  {
    StringBuilder uri = new StringBuilder();
    if ((apiType != null) && (apiType.equals("Batch"))) {
      uri.append(Config.getProperty("baseURL.aggcatBatch"));
    } else {
      uri.append(Config.getProperty("baseURL.aggcat"));
    }
    if (requestParameters.containsKey("entity-name")) {
      uri.append("/").append((String)requestParameters.get("entity-name"));
    }
    if (requestParameters.containsKey("id")) {
      uri.append("/").append((String)requestParameters.get("id"));
    }
    if (requestParameters.containsKey("action")) {
      uri.append("/").append((String)requestParameters.get("action"));
    }
    String queryString = buildQueryParams(queryParameters);
    if (StringUtils.hasText(queryString)) {
      uri.append("?").append(queryString);
    }
    return uri.toString();
  }
  
  private String buildQueryParams(Map<String, String> queryParameters)
  {
    StringBuilder queryString = new StringBuilder();
    Set<String> keySet = queryParameters.keySet();
    Iterator<String> keySetIterator = keySet.iterator();
    while (keySetIterator.hasNext())
    {
      String key = (String)keySetIterator.next();
      String value = (String)queryParameters.get(key);
      queryString.append(key).append("=").append(value).append("&");
    }
    return queryString.toString();
  }
}
