package com.nibbledebt.intuit.cad.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import org.opensaml.xml.security.x509.BasicX509Credential;

import com.nibbledebt.intuit.cad.exception.AggCatException;

public class SAMLCredentials
{
  private static final org.slf4j.Logger LOG = Logger.getLogger();
  private BasicX509Credential credential;

  public SAMLCredentials(String keystoreFile, String keyStorePassword, String alias, String keyPassword)
    throws AggCatException
  {
    InputStream is = null;
    try {
      if ((keystoreFile.indexOf('/') != -1) || (keystoreFile.indexOf('\\') != -1))
        is = new FileInputStream(new File(keystoreFile));
      else {
        is = Thread.currentThread().getContextClassLoader().getResourceAsStream(keystoreFile);
      }
      if (is == null) {
        throw new AggCatException("Could not get resource: " + keystoreFile);
      }
      this.credential = loadCredential(is, keyStorePassword, alias, keyPassword);
    } catch (Exception e) {
      throw new AggCatException("Exception while reading the certificate file : " + keystoreFile, e);
    } finally {
      if (is != null)
        try {
          is.close();
        } catch (IOException e) {
          LOG.error("Unable to close InputStream.", e);
        }
    }
  }

  private BasicX509Credential loadCredential(InputStream inputstream, String keystorePassword, String certAlias, String keyPassword)
    throws AggCatException
  {
    BasicX509Credential basicx509credential = null;
    try {
      LOG.debug("start loading cred.");
      KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
      keystore.load(inputstream, keystorePassword.toCharArray());
      inputstream.close();
      LOG.debug("load cred.");
      KeyStore.PasswordProtection passwordprotection = new KeyStore.PasswordProtection(keyPassword.toCharArray());
      LOG.debug("loading cred 1.");
      KeyStore.PrivateKeyEntry privatekeyentry = (KeyStore.PrivateKeyEntry)keystore.getEntry(certAlias, passwordprotection);
      LOG.debug("loading cred 2.");
      if (privatekeyentry != null) {
        PrivateKey privateKey = privatekeyentry.getPrivateKey();
        LOG.debug("loading cred 3.");
        X509Certificate x509certificate = (X509Certificate)privatekeyentry.getCertificate();
        LOG.debug("loading cred 4.");
        basicx509credential = new BasicX509Credential();
        basicx509credential.setEntityCertificate(x509certificate);
        basicx509credential.setPrivateKey(privateKey);
        LOG.debug("loading cred 5.");
        LOG.debug("Priv key Alg=" + privateKey.getAlgorithm());
        LOG.debug("Priv key Fmt=" + privateKey.getFormat());
        LOG.debug("Priv key Str=" + privateKey.toString());
      } else {
        LOG.debug("No key found for: " + certAlias);
      }
    } catch (Exception e) {
      LOG.error("Exception when loading the cert.", e);
      throw new AggCatException("Exception when loading the cert.", e);
    }
    return basicx509credential;
  }

  public BasicX509Credential getX509Credential()
  {
    return this.credential;
  }
}