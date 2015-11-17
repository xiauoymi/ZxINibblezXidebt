package com.nibbledebt.intuit.cad.serialization;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.nibbledebt.intuit.cad.exception.SerializationException;

public class XMLSerializer
  implements ISerializer
{
  private static final org.slf4j.Logger LOG = com.nibbledebt.intuit.cad.util.Logger.getLogger();

  public <T> String serialize(T object)
    throws SerializationException
  {
    if (object == null) {
      return null;
    }

    StringWriter writer = new StringWriter();
    try {
      Marshaller marshaller = JAXBContext.newInstance(object.getClass().getPackage().getName()).createMarshaller();
      marshaller.marshal(object, writer);
    } catch (JAXBException e) {
      LOG.error("unable to marshall in XML Serializer", e);
      throw new SerializationException(e);
    }
    String documentToPost = writer.toString();
    LOG.debug("XML serialized data : " + documentToPost);
    return documentToPost;
  }

  public Object deserialize(String str, Class<?> cl)
    throws SerializationException
  {
    Object unmarshalledObject;
    try
    {
      Unmarshaller unmarshaller = JAXBContext.newInstance(cl.getPackage().getName()).createUnmarshaller();
      unmarshalledObject = unmarshaller.unmarshal(new StringReader(new String(str.getBytes(), "UTF-8")));
    } catch (Exception e) {
      LOG.error("unable to unmarshall in XML deserializer", e);
      throw new SerializationException(e);
    }
    return unmarshalledObject;
  }
}