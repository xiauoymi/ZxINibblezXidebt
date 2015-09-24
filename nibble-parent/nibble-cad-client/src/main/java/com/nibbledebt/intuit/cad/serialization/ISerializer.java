package com.nibbledebt.intuit.cad.serialization;

import com.nibbledebt.intuit.cad.exception.SerializationException;

public abstract interface ISerializer
{
  public abstract <T> String serialize(T paramT)
    throws SerializationException;

  public abstract Object deserialize(String paramString, Class<?> paramClass)
    throws SerializationException;
}