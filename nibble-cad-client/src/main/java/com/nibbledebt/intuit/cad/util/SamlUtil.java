package com.nibbledebt.intuit.cad.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.nibbledebt.intuit.cad.exception.OAuthException;
import com.nibbledebt.intuit.cad.exception.SamlAssertionException;

public class SamlUtil
{
  private static final org.slf4j.Logger LOG = Logger.getLogger();
  private static final int HTTP_200 = 200;
  private String samlProviderId;
  private String consumerKey;
  private String subject;

  public SamlUtil(String consumerKey, String samlProviderId, String subject)
  {
    this.consumerKey = consumerKey;
    this.samlProviderId = samlProviderId;
    this.subject = subject;
  }

  public Map<String, String> getSamlResponse(String signedSamlStr)
    throws OAuthException
  {
    LOG.debug(new StringBuilder().append("Signed saml str=").append(signedSamlStr).toString());

    String params = null;

    HttpURLConnection connection = null;
    String line = null;
    try
    {
      URL url = new URL(Config.getProperty("saml.oAuthUrl"));
      connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

      connection.setRequestProperty("Content-Length", Integer.toString(signedSamlStr.getBytes().length));
      connection.setRequestProperty("Content-Language", "en-US");
      connection.setRequestProperty("Authorization", new StringBuilder().append("OAuth oauth_consumer_key=\"").append(this.consumerKey).append("\"").toString());

      connection.setUseCaches(false);
      connection.setDoInput(true);
      connection.setDoOutput(true);

      DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
      writer.writeBytes(new StringBuilder().append("saml_assertion=").append(signedSamlStr).toString());
      writer.flush();
      writer.close();

      int respCode = connection.getResponseCode();
      LOG.debug(new StringBuilder().append("Response code=").append(respCode).toString());
      if (respCode == 200) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
          stringBuilder.append(new StringBuilder().append(line).append('\n').toString());
        }
        params = stringBuilder.toString();
      }
    } catch (Exception ex) {
      throw new OAuthException(ex.getMessage(), ex);
    }
    finally {
      if (connection != null) {
        connection.disconnect();
        connection = null;
      }
    }

    LOG.debug(new StringBuilder().append("OAuth access tokens = ").append(params).toString());

    Map ret = new HashMap();
    String[] tmpTokens = params.replaceAll("\n", "").split("&");

    if (tmpTokens.length < 2) {
      return ret;
    }

    ret.put(tmpTokens[0].split("=")[0], tmpTokens[0].split("=")[1]);
    ret.put(tmpTokens[1].split("=")[0], tmpTokens[1].split("=")[1]);

    return ret;
  }

  public String createSignedSAMLPayload()
    throws SamlAssertionException
  {
    LOG.debug(new StringBuilder().append("Signing for subject = ").append(this.subject).append("; Saml provider id=").append(this.samlProviderId).toString());

    SAMLAssertionData samlData = new SAMLAssertionData(this.subject, this.samlProviderId, this.samlProviderId);
    SAML2AssertionGenerator gen = new SAML2AssertionGenerator();
    String samlStr = null;
    try {
      SAMLCredentials cred = new SAMLCredentials(Config.getProperty("saml.keystoreFile"), Config.getProperty("saml.keystorePassword"), Config.getProperty("saml.certAlias"), Config.getProperty("saml.keyPassword"));

      samlStr = gen.generateSignedAssertion(samlData, cred);
    } catch (Exception e) {
      throw new SamlAssertionException(e);
    }

    return encodeBase64UrlSaml(samlStr);
  }

  public String encodeBase64UrlSaml(String samlStr)
  {
    if (StringUtils.hasText(samlStr)) {
      return new String(Base64.encodeBase64URLSafeString(samlStr.getBytes()));
    }
    return null;
  }
}