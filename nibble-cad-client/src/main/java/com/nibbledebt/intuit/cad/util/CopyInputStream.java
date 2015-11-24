package com.nibbledebt.intuit.cad.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CopyInputStream
{
  private InputStream _is;
  private ByteArrayOutputStream _copy = new ByteArrayOutputStream();
  
  public CopyInputStream(InputStream is)
    throws IOException
  {
    this._is = is;
    copy();
  }
  
  private int copy()
    throws IOException
  {
    int read = 0;
    int chunk = 0;
    byte[] data = new byte['Ā'];
    while (-1 != (chunk = this._is.read(data)))
    {
      read += data.length;
      this._copy.write(data, 0, chunk);
    }
    return read;
  }
  
  public InputStream getCopy()
  {
    return new ByteArrayInputStream(this._copy.toByteArray());
  }
}
