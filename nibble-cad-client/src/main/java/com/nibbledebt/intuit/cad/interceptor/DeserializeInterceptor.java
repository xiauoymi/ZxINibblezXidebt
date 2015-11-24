package com.nibbledebt.intuit.cad.interceptor;

import com.nibbledebt.intuit.cad.core.ContentType;
import com.nibbledebt.intuit.cad.exception.AggCatException;
import com.nibbledebt.intuit.cad.serialization.ISerializer;
import com.nibbledebt.intuit.cad.serialization.SerializerFactory;
import com.nibbledebt.intuit.cad.util.StringUtils;

import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.slf4j.LoggerFactory;

public class DeserializeInterceptor
  implements Interceptor
{
  private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DeserializeInterceptor.class);
  
  public void execute(IntuitMessage intuitMessage)
    throws AggCatException
  {
    LOG.debug("Enter DeserializeInterceptor...");
    
    HttpResponse httpResponse = intuitMessage.getResponseElements().getHttpResponse();
    
    Object response = deserialize(httpResponse, intuitMessage.getRequestElements().getResourceTypeIdentifier(), intuitMessage.getResponseElements().getResponseStream());
    
    intuitMessage.getResponseElements().setResponse(response);
    
    LOG.debug("Exit DeserializeInterceptor.");
  }
  
  public Object deserialize(HttpResponse httpResponse, String resourceTypeIdentifier, InputStream content)
    throws AggCatException
  {
    Object response = null;
    
    Header contentTypeHeader = httpResponse.getLastHeader("content-type");
    if (contentTypeHeader != null)
    {
      String contentType = contentTypeHeader.getValue();
      
      String[] contentTypes = contentType.split(";");
      for (String contentTypePart : contentTypes) {
        if (contentTypePart.matches("(.*)/(.*)"))
        {
          if (contentTypePart.equalsIgnoreCase(ContentType.XML.toString()))
          {
            String serializeFormat = contentTypePart.substring(contentTypePart.lastIndexOf("/") + 1, contentTypePart.length());
            LOG.info("HttpResponse content-type (deserialization format) : " + serializeFormat);
            if (StringUtils.hasText(serializeFormat))
            {
              ISerializer serializer = SerializerFactory.getSerializer(serializeFormat);
              Class<?> resourceType = ResourceTypeLocator.getResourceType(resourceTypeIdentifier);
              response = serializer.deserialize(StringUtils.getContent(content), resourceType);
            }
          }
          else if (contentTypePart.equalsIgnoreCase(ContentType.OCTETSTREAM.toString()))
          {
            response = content;
          }
          else if ((contentTypePart.equalsIgnoreCase(ContentType.HTML.toString())) || (contentTypePart.equalsIgnoreCase(ContentType.TEXT.toString())))
          {
            response = StringUtils.getContent(content);
          }
          else
          {
            LOG.debug("Deserialization is not required for this request.");
          }
        }
        else {
          LOG.debug("Response doesn't contain the content-type for deserialization.");
        }
      }
    }
    return response;
  }
}
