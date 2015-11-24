package com.nibbledebt.intuit.cad.interceptor;

import com.nibbledebt.intuit.cad.core.MethodType;
import com.nibbledebt.intuit.cad.exception.AggCatException;
import com.nibbledebt.intuit.cad.serialization.ISerializer;
import com.nibbledebt.intuit.cad.serialization.SerializerFactory;

import java.util.Map;

import org.slf4j.LoggerFactory;

public class SerializeInterceptor
  implements Interceptor
{
  private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SerializeInterceptor.class);
  
  public void execute(IntuitMessage intuitMessage)
    throws AggCatException
  {
    LOG.debug("Enter SerializeInterceptor...");
    String serializedData = null;
    RequestElements requestElements = intuitMessage.getRequestElements();
    String methodType = (String)requestElements.getRequestParameters().get("method-type");
    if ((methodType.equals(MethodType.POST.toString())) || (methodType.equals(MethodType.PUT.toString())))
    {
      ISerializer serializer = SerializerFactory.getSerializer("xml");
      serializedData = serializer.serialize(requestElements.getObjectToSerialize());
    }
    else
    {
      LOG.debug("Serialization is not applicable for the GET/DELETE request");
    }
    LOG.debug("Exit SerializeInterceptor.");
    requestElements.setSerializedData(serializedData);
  }
}
