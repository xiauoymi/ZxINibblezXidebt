package com.nibbledebt.intuit.cad.util;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.LoggerFactory;

public final class Config
{
  private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Config.class);
  private CompositeConfiguration cc = null;
  public static final String BASE_URL_AGGCAT = "baseURL.aggcat";
  public static final String BASE_URL_AGGCATBATCH = "baseURL.aggcatBatch";
  public static final String KEY_STORE_FILE = "saml.keystoreFile";
  public static final String KEY_STORE_PASSWORD = "saml.keystorePassword";
  public static final String KEY_PASSWORD = "saml.keyPassword";
  public static final String CERT_ALIAS = "saml.certAlias";
  public static final String OAUTH_URL = "saml.oAuthUrl";
  public static final String PROXY_HOST = "proxy.host";
  public static final String PROXY_PORT = "proxy.port";
  public static final String PROXY_KEYSTORE_PATH = "proxy.keystore.path";
  public static final String PROXY_KEYSTORE_PASSWORD = "proxy.keystore.password";
  public static final String RETRY_MODE = "retry.mode";
  public static final String RETRY_FIXED_COUNT = "retry.fixed.count";
  public static final String RETRY_FIXED_INTERVAL = "retry.fixed.interval";
  public static final String RETRY_INCREMENTAL_COUNT = "retry.incremental.count";
  public static final String RETRY_INCREMENTAL_INTERVAL = "retry.incremental.interval";
  public static final String RETRY_INCREMENTAL_INCREMENT = "retry.incremental.increment";
  public static final String RETRY_EXPONENTIAL_COUNT = "retry.exponential.count";
  public static final String RETRY_EXPONENTIAL_MIN_BACKOFF = "retry.exponential.minBackoff";
  public static final String RETRY_EXPONENTIAL_MAX_BACKOFF = "retry.exponential.maxBackoff";
  public static final String RETRY_EXPONENTIAL_DELTA_BACKOFF = "retry.exponential.deltaBackoff";
  private static ThreadLocal<Config> local = new ThreadLocal()
  {
    public Config initialValue()
    {
      return new Config();
    }
    
    public Config get()
    {
      return (Config)super.get();
    }
  };
  
  private Config()
  {
    try
    {
      XMLConfiguration config = null;
      XMLConfiguration devConfig = null;
      
      this.cc = new CompositeConfiguration();
      try
      {
        devConfig = new XMLConfiguration("intuit-aggcat-config.xml");
        this.cc.addConfiguration(devConfig);
      }
      catch (ConfigurationException e)
      {
        LOG.warn("issue reading intuit-aggcat-config.xml");
      }
      config = new XMLConfiguration("intuit-aggcat-default-config.xml");
      this.cc.addConfiguration(config);
    }
    catch (ConfigurationException e)
    {
      LOG.error("ConfigurationException while loading configuration xml file.", e);
    }
  }
  
  public static String getProperty(String key)
  {
    return ((Config)local.get()).cc.getString(key);
  }
  
  public static void setProperty(String key, String value)
  {
    ((Config)local.get()).cc.setProperty(key, value);
  }
}
