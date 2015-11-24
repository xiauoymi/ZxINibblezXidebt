package com.nibbledebt.intuit.cad.serialization;

import com.nibbledebt.intuit.cad.exception.SerializationException;
import com.nibbledebt.intuit.cad.util.StringUtils;

public final class SerializerFactory
{
  public static final String XML_SERIALIZE_FORMAT = "xml";
  
  public static SerializerFactory getInstance()
  {
    return new SerializerFactory();
  }
  
  public static ISerializer getSerializer(String serializeFormat)
    throws SerializationException
  {
    ISerializer serializer = null;
    if ((isValidSerializeFormat(serializeFormat)) && (serializeFormat.equalsIgnoreCase("xml"))) {
      serializer = new XMLSerializer();
    }
    return serializer;
  }
  
  public static boolean isValidSerializeFormat(String serializeFormat)
    throws SerializationException
  {
    if (!StringUtils.hasText(serializeFormat)) {
      throw new SerializationException("serialization format is either null or empty!");
    }
    if (serializeFormat.equalsIgnoreCase("xml")) {
      return true;
    }
    throw new SerializationException("Serializer not supported for the given serialization format : " + serializeFormat);
  }
}
