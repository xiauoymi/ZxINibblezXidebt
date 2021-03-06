package com.nibbledebt.intuit.cad.interceptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.nibbledebt.intuit.cad.exception.AggCatException;

public class InterceptorProvider
{
  private List<Interceptor> requestInterceptors = new ArrayList();
  private List<Interceptor> responseInterceptors = new ArrayList();
  
  public InterceptorProvider()
  {
    this.requestInterceptors.add(new PrepareRequestInterceptor());
    this.requestInterceptors.add(new SerializeInterceptor());
    this.requestInterceptors.add(new ConnectionInterceptor());
    
    this.responseInterceptors.add(new HandleResponseInterceptor());
    this.responseInterceptors.add(new DeserializeInterceptor());
  }
  
  public void executeInterceptors(IntuitMessage intuitMessage)
    throws AggCatException
  {
    executeRequestInterceptors(intuitMessage);
    executeResponseInterceptors(intuitMessage);
  }
  
  private void executeRequestInterceptors(IntuitMessage intuitMessage)
    throws AggCatException
  {
    Iterator<Interceptor> itr = this.requestInterceptors.iterator();
    while (itr.hasNext())
    {
      Interceptor interceptor = (Interceptor)itr.next();
      interceptor.execute(intuitMessage);
    }
  }
  
  private void executeResponseInterceptors(IntuitMessage intuitMessage)
    throws AggCatException
  {
    Iterator<Interceptor> itr = this.responseInterceptors.iterator();
    while (itr.hasNext())
    {
      Interceptor interceptor = (Interceptor)itr.next();
      interceptor.execute(intuitMessage);
    }
  }
}
