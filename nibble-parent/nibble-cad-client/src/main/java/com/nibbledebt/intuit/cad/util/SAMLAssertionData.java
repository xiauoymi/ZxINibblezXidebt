package com.nibbledebt.intuit.cad.util;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.impl.SecureRandomIdentifierGenerator;

import com.nibbledebt.intuit.cad.exception.SamlAssertionException;

public class SAMLAssertionData
{
  public static final String SAML2 = SAMLVersion.VERSION_20.toString();
  private static SecureRandomIdentifierGenerator generator;
  public static final String SAML11 = SAMLVersion.VERSION_11.toString();
  public static final String CM_BEARER = "urn:oasis:names:tc:SAML:2.0:cm:bearer";
  public static final String AC_PASSWORD = "urn:oasis:names:tc:SAML:2.0:ac:classes:Password";
  public static final String AC_X509 = "urn:oasis:names:tc:SAML:2.0:ac:classes:X509";
  public static final String AC_UNSPECIFIED = "urn:oasis:names:tc:SAML:2.0:ac:classes:unspecified";
  private static final int MS_TOLERANCE = 300000;
  private static final int MS_TOKEN_LIFETIME = 600000;
  private String subject;
  private String issuer;
  private String audienceRestriction;
  private String recipient;
  private String version;
  private DateTime authTime;
  private DateTime assertionTime;
  private int toleranceMS;
  private int tokenLifetimeMS;
  private Map<String, String> attributes;
  private String confirmationMethod;
  private String authenticationContext;
  private String samlId;

  public SAMLAssertionData(String subject, String issuer, String restrictionInfo)
    throws SamlAssertionException
  {
    this.version = SAML2;
    this.authTime = new DateTime();
    this.assertionTime = new DateTime();
    this.toleranceMS = 300000;
    this.tokenLifetimeMS = 600000;
    this.attributes = new HashMap();
    this.confirmationMethod = "urn:oasis:names:tc:SAML:2.0:cm:bearer";
    this.authenticationContext = "urn:oasis:names:tc:SAML:2.0:ac:classes:unspecified";
    this.subject = subject;
    this.issuer = issuer;
    this.audienceRestriction = restrictionInfo;
    try {
      this.samlId = getGenerator().generateIdentifier();
    } catch (NoSuchAlgorithmException nosuchalgorithmexception) {
      throw new SamlAssertionException("Failed to generate samlId", nosuchalgorithmexception);
    }
  }

  protected SecureRandomIdentifierGenerator getGenerator()
    throws NoSuchAlgorithmException
  {
    if (null == generator) {
      generator = new SecureRandomIdentifierGenerator();
    }
    return generator;
  }

  public void setSubject(String subject)
  {
    this.subject = subject;
  }

  public String getSubject()
  {
    return this.subject;
  }

  public void setIssuer(String issuer)
  {
    this.issuer = issuer;
  }

  public String getIssuer()
  {
    return this.issuer;
  }

  public void setAudienceRestriction(String audienceRestriction)
  {
    this.audienceRestriction = audienceRestriction;
  }

  public String getAudienceRestriction()
  {
    return this.audienceRestriction;
  }

  public void setRecipient(String recipient)
  {
    this.recipient = recipient;
  }

  public String getRecipient()
  {
    return this.recipient;
  }

  public void setVersion(String version)
  {
    this.version = version;
  }

  public String getVersion()
  {
    return this.version;
  }

  public void setAuthTime(DateTime authTime)
  {
    this.authTime = authTime;
  }

  public DateTime getAuthTime()
  {
    return this.authTime;
  }

  public void setAssertionTime(DateTime assertionTime)
  {
    this.assertionTime = assertionTime;
  }

  public DateTime getAssertionTime()
  {
    return this.assertionTime;
  }

  public void setToleranceMS(int toleranceMS)
  {
    this.toleranceMS = toleranceMS;
  }

  public int getToleranceMS()
  {
    return this.toleranceMS;
  }

  public void setTokenLifetimeMS(int tokenLifetimeMS)
  {
    this.tokenLifetimeMS = tokenLifetimeMS;
  }

  public int getTokenLifetimeMS()
  {
    return this.tokenLifetimeMS;
  }

  public void setAttributes(Map<String, String> attributes)
  {
    this.attributes = attributes;
  }

  public Map<String, String> getAttributes()
  {
    return this.attributes;
  }

  public void setConfirmationMethod(String confirmationMethod)
  {
    this.confirmationMethod = confirmationMethod;
  }

  public String getConfirmationMethod()
  {
    return this.confirmationMethod;
  }

  public void setAuthenticationContext(String authenticationContext)
  {
    this.authenticationContext = authenticationContext;
  }

  public String getAuthenticationContext()
  {
    return this.authenticationContext;
  }

  public void setSamlId(String samlId)
  {
    this.samlId = samlId;
  }

  public String getSamlId()
  {
    return this.samlId;
  }
}