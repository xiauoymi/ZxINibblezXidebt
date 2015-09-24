package com.nibbledebt.intuit.cad.interceptor;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import com.nibbledebt.intuit.cad.data.Status;
import com.nibbledebt.intuit.cad.exception.AggCatException;
import com.nibbledebt.intuit.cad.exception.BadRequestException;
import com.nibbledebt.intuit.cad.exception.ForbiddenException;
import com.nibbledebt.intuit.cad.exception.InvalidProxyException;
import com.nibbledebt.intuit.cad.exception.InvalidRequestException;
import com.nibbledebt.intuit.cad.exception.ServiceUnavailableException;
import com.nibbledebt.intuit.cad.util.StringUtils;

public class HandleResponseInterceptor
  implements Interceptor
{
  private static final org.slf4j.Logger LOG = com.nibbledebt.intuit.cad.util.Logger.getLogger();
  private static final int HTTP_ERROR_200 = 200;
  private static final int HTTP_ERROR_201 = 201;
  private static final int HTTP_ERROR_206 = 206;
  private static final int HTTP_ERROR_400 = 400;
  private static final int HTTP_ERROR_401 = 401;
  private static final int HTTP_ERROR_403 = 403;
  private static final int HTTP_ERROR_404 = 404;
  private static final int HTTP_ERROR_408 = 408;
  private static final int HTTP_ERROR_416 = 416;
  private static final int HTTP_ERROR_500 = 500;
  private static final int HTTP_ERROR_502 = 502;
  private static final int HTTP_ERROR_503 = 503;

  public void execute(IntuitMessage intuitMessage)
    throws AggCatException
  {
    LOG.debug("Enter HandleResponseInterceptor...");

    ResponseElements responseElements = intuitMessage.getResponseElements();

    String errorMessage = responseElements.getHttpResponse().getStatusLine().getReasonPhrase();

    int statusCode = responseElements.getHttpResponse().getStatusLine().getStatusCode();

    RequestElements requestElements = intuitMessage.getRequestElements();
    String identifier = requestElements.getResourceTypeIdentifier();

    LOG.debug("Http status code : " + statusCode + ", status msg : " + errorMessage + ", identifier : " + identifier);

    HttpResponse httpResponse = responseElements.getHttpResponse();
    Header challengeSessionIdHeader = httpResponse.getLastHeader("challengeSessionId");

    Status status = null;
    String errorResponseText = null;

    if ((statusCode != 200) && (((statusCode != 201) && ((statusCode != 401) || (challengeSessionIdHeader == null))) || ((!identifier.equals("DiscoverAndAddAccounts")) && (!identifier.equals("UpdateInstitutionLogin")))))
    {
      LOG.debug("Deserialize the error response to get the Status object.");
      try {
        DeserializeInterceptor deserializeInterceptor = new DeserializeInterceptor();
        Object deserializedObject = deserializeInterceptor.deserialize(httpResponse, "Status", responseElements.getResponseStream());
        try {
          status = (Status)deserializedObject;
        } catch (Exception e) {
          errorResponseText = (String)deserializedObject;
        }
      } catch (AggCatException e) {
        LOG.error("Exception while deserialize the status object of the error response.", e);
      }
    }
    handleErrorCodes(intuitMessage, errorMessage, statusCode, identifier, status, errorResponseText);

    LOG.debug("Exit HandleResponseInterceptor.");
  }

  public void handleErrorCodes(IntuitMessage intuitMessage, String errorMessage, int statusCode, String identifier, Status status, String errorResponseText)
    throws AggCatException
  {
    if ((identifier.equals("GetInstitutions")) || (identifier.equals("GetInstitutionDetails")))
      handleGetInstitutionException(statusCode, errorMessage, status, errorResponseText);
    else if (identifier.equals("DiscoverAndAddAccounts"))
      handleDiscoverAndAddAccountsException(statusCode, errorMessage, intuitMessage, status, errorResponseText);
    else if (identifier.equals("DeleteAccount"))
      handleDeleteAccountException(statusCode, errorMessage, status, errorResponseText);
    else if (identifier.equals("UpdateInstitutionLogin"))
      handleUpdateInstitutionException(statusCode, errorMessage, intuitMessage, status, errorResponseText);
    else if (identifier.equals("DeleteCustomer"))
      handleDeleteCustomerException(statusCode, errorMessage, status, errorResponseText);
    else if (identifier.equals("UpdateAccountType"))
      handleUpdateAccountTypeException(statusCode, errorMessage, status, errorResponseText);
    else if (identifier.equals("DeleteFile"))
      handleDeleteFileException(statusCode, errorMessage, status, errorResponseText);
    else if (identifier.equals("ListFiles"))
      handleListFileException(statusCode, errorMessage, status, errorResponseText);
    else if (identifier.equals("GetFileData"))
      handleGetFileDataException(statusCode, errorMessage, status, errorResponseText);
    else if (identifier.equals("GetInvestmentPositions"))
      handleInvestmentPositionsException(statusCode, errorMessage, status, errorResponseText);
    else if ((identifier.equals("GetAccountTransactions")) || (identifier.equals("GetAccount")) || (identifier.equals("GetLoginAccounts")) || (identifier.equals("GetCustomerAccounts")))
    {
      handleGetAccountDetailsException(statusCode, errorMessage, status, errorResponseText);
    }
  }

  private void handleUpdateAccountTypeException(int statusCode, String errorMessage, Status status, String errorResponseText)
    throws AggCatException
  {
    if (statusCode == 200)
      return;
    if (statusCode == 400) {
      throw new AggCatException(String.valueOf(statusCode), "Bad Request if the accountId specified in URL is malformed, or if the resource in the request body doesn't contain the required account subtype field.", status, errorResponseText);
    }

    if (statusCode == 401) {
      throw new AggCatException(String.valueOf(statusCode), "Unauthorized if the user issuing the request doesn't have privileges to update the account specified in the URL.", status, errorResponseText);
    }
    if (statusCode == 403) {
      throw new AggCatException(String.valueOf(statusCode), "Forbidden if the account to be changed is any account type besides \"OtherAccount\", since changing between \"concrete\" account types is not supported.", status, errorResponseText);
    }

    if (statusCode == 404)
      throw new AggCatException(String.valueOf(statusCode), "Account specified in the URL not found. " + errorMessage, status, errorResponseText);
    if (statusCode == 500)
      throw new AggCatException(String.valueOf(statusCode), "An internal server error has occurred. " + errorMessage, status, errorResponseText);
    if (statusCode == 502)
      throw new InvalidProxyException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 503)
      throw new ServiceUnavailableException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
  }

  private void handleGetAccountDetailsException(int statusCode, String errorMessage, Status status, String errorResponseText)
    throws AggCatException
  {
    if (statusCode == 200)
      return;
    if (statusCode == 400)
      throw new BadRequestException(String.valueOf(statusCode), "Bad Request.", status, errorResponseText);
    if (statusCode == 401)
      throw new AggCatException(String.valueOf(statusCode), "Caller could not be authenticated.", status, errorResponseText);
    if (statusCode == 403)
      throw new ForbiddenException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 404)
      throw new AggCatException(String.valueOf(statusCode), "Account not found for the given accountId. " + errorMessage, status, errorResponseText);
    if (statusCode == 500)
      throw new AggCatException(String.valueOf(statusCode), "An internal server error has occurred. " + errorMessage, status, errorResponseText);
    if (statusCode == 502)
      throw new InvalidProxyException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 503)
      throw new ServiceUnavailableException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
  }

  private void handleDeleteAccountException(int statusCode, String errorMessage, Status status, String errorResponseText)
    throws AggCatException
  {
    if (statusCode == 200)
      return;
    if (statusCode == 400)
      throw new BadRequestException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 401)
      throw new AggCatException(String.valueOf(statusCode), "Caller could not be authenticated.", status, errorResponseText);
    if (statusCode == 403)
      throw new ForbiddenException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 404)
      throw new AggCatException(String.valueOf(statusCode), "Not found.  " + errorMessage, status, errorResponseText);
    if (statusCode == 500)
      throw new AggCatException(String.valueOf(statusCode), "An internal server error has occurred. " + errorMessage, status, errorResponseText);
    if (statusCode == 502)
      throw new InvalidProxyException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 503)
      throw new ServiceUnavailableException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
  }

  private void handleDeleteCustomerException(int statusCode, String errorMessage, Status status, String errorResponseText)
    throws AggCatException
  {
    if (statusCode == 200)
      return;
    if (statusCode == 400)
      throw new BadRequestException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 401)
      throw new AggCatException(String.valueOf(statusCode), "Caller could not be authenticated.", status, errorResponseText);
    if (statusCode == 403)
      throw new InvalidRequestException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 500)
      throw new AggCatException(String.valueOf(statusCode), "An internal server error has occurred. " + errorMessage, status, errorResponseText);
    if (statusCode == 502)
      throw new InvalidProxyException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 503)
      throw new ServiceUnavailableException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
  }

  private void handleGetInstitutionException(int statusCode, String errorMessage, Status status, String errorResponseText)
    throws AggCatException
  {
    if (statusCode == 200)
      return;
    if (statusCode == 400)
      throw new BadRequestException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 401)
      throw new AggCatException(String.valueOf(statusCode), "Caller could not be authenticated.", status, errorResponseText);
    if (statusCode == 403)
      throw new InvalidRequestException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 404)
      throw new AggCatException(String.valueOf(statusCode), "No institution with the given ID could be located.", status, errorResponseText);
    if (statusCode == 500)
      throw new AggCatException(String.valueOf(statusCode), "An internal server error has occurred. " + errorMessage, status, errorResponseText);
    if (statusCode == 502)
      throw new InvalidProxyException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 503)
      throw new ServiceUnavailableException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
  }

  private void handleDiscoverAndAddAccountsException(int statusCode, String errorMessage, IntuitMessage intuitMessage, Status status, String errorResponseText)
    throws AggCatException
  {
    if (statusCode == 201)
      return;
    if (statusCode == 401) {
      if ((status != null) || (StringUtils.hasText(errorResponseText))) {
        throw new AggCatException(String.valueOf(statusCode), "Caller could not be authenticated.", status, errorResponseText);
      }
      RequestElements requestElements = intuitMessage.getRequestElements();
      requestElements.setResourceTypeIdentifier("DiscoverAndAddAccountsChallenge");
      return;
    }if (statusCode == 400)
      throw new BadRequestException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 403)
      throw new InvalidRequestException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 404) {
      throw new AggCatException(String.valueOf(statusCode), "No institution with the given ID could be located or, the institution does exist but is not available to the calling offering.", status, errorResponseText);
    }
    if (statusCode == 408)
      throw new AggCatException(String.valueOf(statusCode), "The challengeSessionId cannot be found or has expired.", status, errorResponseText);
    if (statusCode == 500)
      throw new AggCatException(String.valueOf(statusCode), "An internal server error has occurred. " + errorMessage, status, errorResponseText);
    if (statusCode == 502)
      throw new InvalidProxyException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 503)
      throw new ServiceUnavailableException(String.valueOf(statusCode), "Access to the external financial institution is being throttled by the aggregation system. Or, there was a problem retrieving customer data from the external institution. This includes script errors. Script error details will be included in the errorCode element of the ErrorInfo of the Status in the HTTP response body. " + errorMessage, status, errorResponseText);
  }

  private void handleUpdateInstitutionException(int statusCode, String errorMessage, IntuitMessage intuitMessage, Status status, String errorResponseText)
    throws AggCatException
  {
    if (statusCode == 200)
      return;
    if (statusCode == 401) {
      if ((status != null) || (StringUtils.hasText(errorResponseText))) {
        throw new AggCatException(String.valueOf(statusCode), "Caller could not be authenticated.", status, errorResponseText);
      }
      RequestElements requestElements = intuitMessage.getRequestElements();
      requestElements.setResourceTypeIdentifier("UpdateInstitutionLoginChallenge");
      return;
    }if (statusCode == 400)
      throw new BadRequestException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 403)
      throw new ForbiddenException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 404)
      throw new AggCatException(String.valueOf(statusCode), "Login specified in the URL Not Found", status, errorResponseText);
    if (statusCode == 408)
      throw new AggCatException(String.valueOf(statusCode), "The challengeSessionId cannot be found or has expired.", status, errorResponseText);
    if (statusCode == 500)
      throw new AggCatException(String.valueOf(statusCode), "An internal server error has occurred. " + errorMessage, status, errorResponseText);
    if (statusCode == 502)
      throw new InvalidProxyException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
    if (statusCode == 503)
      throw new ServiceUnavailableException(String.valueOf(statusCode), "CustomerCentral's throttler denied access to the external FI for the refresh. Or, There was some problem aggregating data from the external FI. The details of the error can be found in the errorCode field of the Status entity's errorInfo structure " + errorMessage, status, errorResponseText);
  }

  private void handleInvestmentPositionsException(int statusCode, String errorMessage, Status status, String errorResponseText)
    throws AggCatException
  {
    if (statusCode == 200)
      return;
    if (statusCode == 401)
      throw new AggCatException(String.valueOf(statusCode), "Caller could not be authenticated. " + errorMessage, status, errorResponseText);
    if (statusCode == 404)
      throw new AggCatException(String.valueOf(statusCode), "NOT FOUND - accountId doesn't not refer to an existing account. " + errorMessage, status, errorResponseText);
    if (statusCode == 500)
      throw new AggCatException(String.valueOf(statusCode), "An internal server error has occurred. " + errorMessage, status, errorResponseText);
    if (statusCode == 503)
      throw new ServiceUnavailableException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
  }

  private void handleDeleteFileException(int statusCode, String errorMessage, Status status, String errorResponseText)
    throws AggCatException
  {
    if (statusCode == 200)
      return;
    if (statusCode == 401)
      throw new AggCatException(String.valueOf(statusCode), "Invalid Credentials.", status, errorResponseText);
    if (statusCode == 404) {
      throw new AggCatException(String.valueOf(statusCode), "Specified file not found or does not belong to offering making the request" + errorMessage, status, errorResponseText);
    }
    if (statusCode == 500)
      throw new AggCatException(String.valueOf(statusCode), "An internal server error has occurred. " + errorMessage, status, errorResponseText);
  }

  private void handleGetFileDataException(int statusCode, String errorMessage, Status status, String errorResponseText)
    throws AggCatException
  {
    if ((statusCode == 200) || (statusCode == 206))
      return;
    if (statusCode == 401)
      throw new AggCatException(String.valueOf(statusCode), "Invalid credentials or insufficient privilages. " + errorMessage, status, errorResponseText);
    if (statusCode == 404) {
      throw new AggCatException(String.valueOf(statusCode), "Specified file not found or does not belong to offering making the request. " + errorMessage, status, errorResponseText);
    }
    if (statusCode == 416)
      throw new AggCatException(String.valueOf(statusCode), "Specified range is invalid. " + errorMessage, status, errorResponseText);
    if (statusCode == 500)
      throw new AggCatException(String.valueOf(statusCode), "An internal server error has occurred. " + errorMessage, status, errorResponseText);
    if (statusCode == 503)
      throw new ServiceUnavailableException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
  }

  private void handleListFileException(int statusCode, String errorMessage, Status status, String errorResponseText)
    throws AggCatException
  {
    if (statusCode == 200)
      return;
    if (statusCode == 401)
      throw new AggCatException(String.valueOf(statusCode), "Caller could not be authenticated. " + errorMessage, status, errorResponseText);
    if (statusCode == 500)
      throw new AggCatException(String.valueOf(statusCode), "An internal server error has occurred. " + errorMessage, status, errorResponseText);
    if (statusCode == 503)
      throw new ServiceUnavailableException(String.valueOf(statusCode), errorMessage, status, errorResponseText);
  }
}