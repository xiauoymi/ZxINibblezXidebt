package com.nibbledebt.intuit.cad.retry;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;
import javax.security.sasl.SaslException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;

import com.nibbledebt.intuit.cad.exception.AggCatException;

public class IntuitRetryPolicyHandler
  implements HttpRequestRetryHandler
{
  private static final org.slf4j.Logger LOG = com.nibbledebt.intuit.cad.util.Logger.getLogger();
  private static final double NUM_0_8 = 0.8D;
  private static final double NUM_1_2 = 1.2D;
  private int retryCount;
  private int retryInterval;
  private int initialInterval;
  private int increment;
  private int minBackoff;
  private int maxBackoff;
  private int deltaBackoff;
  private String mechanism;

  public IntuitRetryPolicyHandler(int retryCount, int retryInterval)
    throws AggCatException
  {
    RetryHelper.argumentNotNegativeValue(retryCount, "retry.fixed.count");
    RetryHelper.argumentNotNegativeValue(retryInterval, "retry.fixed.interval");

    this.retryCount = retryCount;
    this.retryInterval = retryInterval;
    this.mechanism = "fixedretry";
  }

  public IntuitRetryPolicyHandler(int retryCount, int initialInterval, int increment)
    throws AggCatException
  {
    RetryHelper.argumentNotNegativeValue(retryCount, "retry.incremental.count");
    RetryHelper.argumentNotNegativeValue(initialInterval, "retry.incremental.interval");
    RetryHelper.argumentNotNegativeValue(increment, "retry.incremental.increment");

    this.retryCount = retryCount;
    this.initialInterval = initialInterval;
    this.increment = increment;
    this.mechanism = "incrementalretry";
  }

  public IntuitRetryPolicyHandler(int retryCount, int minBackoff, int maxBackoff, int deltaBackoff)
    throws AggCatException
  {
    RetryHelper.argumentNotNegativeValue(retryCount, "retry.exponential.count");
    RetryHelper.argumentNotNegativeValue(minBackoff, "retry.exponential.minBackoff");
    RetryHelper.argumentNotNegativeValue(maxBackoff, "retry.exponential.maxBackoff");
    RetryHelper.argumentNotNegativeValue(deltaBackoff, "retry.exponential.deltaBackoff");

    this.retryCount = retryCount;
    this.minBackoff = minBackoff;
    this.maxBackoff = maxBackoff;
    this.deltaBackoff = deltaBackoff;
    this.mechanism = "exponentialretry";
  }

  public boolean retryRequest(IOException exception, int executionCount, HttpContext context)
  {
    LOG.debug("In retry request");
    if (exception == null)
      throw new IllegalArgumentException("Exception parameter may not be null");
    if (context == null) {
      throw new IllegalArgumentException("HTTP context may not be null");
    }

    if (executionCount > this.retryCount)
      return checkPolicy(executionCount);
    if ((exception instanceof NoHttpResponseException))
    {
      return checkPolicy(executionCount);
    }if ((exception instanceof InterruptedIOException))
    {
      return false;
    }if ((exception instanceof UnknownHostException))
    {
      return false;
    }if ((exception instanceof ConnectException))
    {
      return false;
    }if ((exception instanceof SSLException))
    {
      return false;
    }if ((exception instanceof ProtocolException))
    {
      return false;
    }if ((exception instanceof SaslException))
    {
      return false;
    }

    HttpRequest request = (HttpRequest)context.getAttribute("http.request");
    boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
    if (idempotent)
    {
      return checkPolicy(executionCount);
    }

    Boolean b = (Boolean)context.getAttribute("http.request_sent");
    boolean sent = (b != null) && (b.booleanValue());

    if (!sent) {
      return checkPolicy(executionCount);
    }

    return false;
  }

  private boolean checkPolicy(int executionCount)
  {
    if (this.mechanism.equalsIgnoreCase("fixedretry")) {
      if (this.retryCount == 0)
        return false;
      if (executionCount < this.retryCount) {
        try {
          Thread.sleep(this.retryInterval);
          LOG.debug("The retryInterval " + this.retryInterval);
          return true;
        } catch (Exception e) {
          LOG.error(e.getMessage());
        }
      }
      return false;
    }

    if (this.mechanism.equalsIgnoreCase("incrementalretry")) {
      if (executionCount < this.retryCount) {
        try {
          Thread.sleep(this.initialInterval + this.increment * executionCount);
          return true;
        } catch (Exception e) {
          LOG.error(e.getMessage());
        }
      }
      return false;
    }

    if (this.mechanism.equalsIgnoreCase("exponentialretry")) {
      if (executionCount < this.retryCount) {
        try {
          int delta = (int)((Math.pow(2.0D, executionCount) - 1.0D) * (this.deltaBackoff * 0.8D) + (Math.random() * (this.deltaBackoff * 1.2D) - this.deltaBackoff * 0.8D + 1.0D));

          int interval = Math.min(this.minBackoff + delta, this.maxBackoff);
          Thread.sleep(interval);
        } catch (Exception e) {
          LOG.error(e.getMessage());
        }
        return true;
      }
      return false;
    }
    return true;
  }
}