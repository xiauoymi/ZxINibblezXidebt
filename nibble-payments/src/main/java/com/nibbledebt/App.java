package com.nibbledebt;

import java.awt.Desktop;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import com.dwolla.java.sdk.Consts;
import com.dwolla.java.sdk.DwollaCallback;
import com.dwolla.java.sdk.DwollaServiceAsync;
import com.dwolla.java.sdk.DwollaServiceSync;
import com.dwolla.java.sdk.DwollaTypedBytes;
import com.dwolla.java.sdk.OAuthServiceSync;
import com.dwolla.java.sdk.requests.SendRequest;
import com.dwolla.java.sdk.requests.TokenRequest;
import com.dwolla.java.sdk.responses.BasicAccountInformationResponse;
import com.dwolla.java.sdk.responses.SendResponse;
import com.dwolla.java.sdk.responses.TokenResponse;
import com.google.gson.Gson;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.CustomersApi;
import io.swagger.client.model.CreateCustomer;
import io.swagger.client.model.CustomerListResponse;
import io.swagger.client.model.Unit$;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class App {
    public final static String SCOPES = "Send|AccountInfoFull|Funding";
    public final static String REDIRECT_URI = "http://localhost:4567/callback";
    public final static String CLIENT_ID = "vWy5Jd5MG9taM6mQ5p6pOMfXSsoMJUU4IweFeoBbZIICp8rlaU";
    public final static String CLIENT_SECRET = "Fzo8i4fFtRueFZB8t3YN1Vdr74VdeRGZm6zozHVmBPOixGCesS";
    public final static String SENDER_PIN = "1234";
    public final static String TOKEN="dhltQYKhfyUm25E4TUJfdxxfEc5Y4lYHuhgZYztne4CaTlTjGV";
    // Use "812-713-9234" in production, any money sent to it will be sent right back to you
    public final static String DESTINATION_ID = "812-172-9684";

    public static void main(String[] args) throws ApiException {
        ApiClient client = new ApiClient();
        client.setBasePath(Config.BASE_URL);
        client.setAccessToken("YTpnmBMdfR9kO2NRt9sbQVRQfZBx2SRt3fU9a0Wazck0hThrcX");
        CustomersApi api = new CustomersApi(client);
        
        CreateCustomer customer=new CreateCustomer();
        
        customer.setFirstName("withe name 3");
        customer.setLastName("last name 3");
        customer.setEmail("m3@m.m");
        customer.setType("receive-only");
        
        try {
        	Unit$ r =api.create(customer);
        	System.out.println(r);
        	CustomerListResponse list=api.list(1, 10);
        	list.getLinks().forEach((k,v)->{
        		System.out.println(v);
        	});
        } catch (ApiException e) {
        	e.printStackTrace();
        }
//        openBrowserToRequestAuthorization();
//
//        get(new Route("/callback") {
//            @Override
//            public Object handle(Request req, Response res) {
//                TokenResponse tr = getToken(req.queryParams("code"));
//                callApiAsync(tr.access_token);
//                return callApi(tr.access_token);
//            }
//        });
    }

    private static void openBrowserToRequestAuthorization() {
        try {
            Desktop.getDesktop().browse(
                    new URI(String.format("%s/authenticate?response_type=code&scope=%s&client_id=%s&redirect_uri=%s",
                            Config.BASE_OAUTH_URL, encode(SCOPES), encode(CLIENT_ID), encode(REDIRECT_URI))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static TokenResponse getToken(String code) {
        OAuthServiceSync oAuth = createOAuthService();
        return oAuth.getToken(new DwollaTypedBytes(new Gson(),
                new TokenRequest(CLIENT_ID, CLIENT_SECRET, Consts.Api.AUTHORIZATION_CODE, REDIRECT_URI, code)));
    }

    private static void callApiAsync(String token) {
        DwollaServiceAsync dwolla = createDwollaAsyncService();
        dwolla.getBasicAccountInformation(DESTINATION_ID, CLIENT_ID, CLIENT_SECRET, new BasicInformationCallback());
        dwolla.send(token, new DwollaTypedBytes(new Gson(), new SendRequest(SENDER_PIN, DESTINATION_ID, 0.01)), new SendCallback());
    }

    private static String callApi(String token) {
        DwollaServiceSync dwolla = createDwollaService();
        BasicAccountInformationResponse infoRes = dwolla.getBasicAccountInformation(DESTINATION_ID, CLIENT_ID, CLIENT_SECRET);
        SendResponse sendRes = dwolla.send(token, new DwollaTypedBytes(new Gson(), new SendRequest(SENDER_PIN, DESTINATION_ID, 0.01)));

        return String.format("Synchronous account name: \"%s\" | Synchronous transaction id: \"%s\" | Check console output for asynchronous responses",
                infoRes.Success ? infoRes.Response.Name : infoRes.Message,
                sendRes.Success ? sendRes.Response : sendRes.Message);
    }

    private static OAuthServiceSync createOAuthService() {
        return new RestAdapter
                .Builder()
                .setEndpoint(Config.BASE_OAUTH_URL)
                .build()
                .create(OAuthServiceSync.class);
    }

    private static DwollaServiceSync createDwollaService() {
        return new RestAdapter
                .Builder()
                .setEndpoint(Config.BASE_URL)
                .build()
                .create(DwollaServiceSync.class);
    }

    private static DwollaServiceAsync createDwollaAsyncService() {
        return new RestAdapter
                .Builder()
                .setEndpoint(Config.BASE_URL)
                .build()
                .create(DwollaServiceAsync.class);
    }

    private static String encode(String decoded) {
        try {
            return URLEncoder.encode(decoded, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            return decoded;
        }
    }

    /**
     * Asynchronous callbacks, helpful in Android apps to keep HTTP calls off the main thread.
     */
    private static class BasicInformationCallback extends DwollaCallback<BasicAccountInformationResponse> {
        @Override
        public void success(BasicAccountInformationResponse response, retrofit.client.Response r) {
            System.out.println("Asynchronous account name: " + (response.Success ? response.Response.Name : response.Message));
        }

        public void failure(RetrofitError error) {
            super.failure(error.getMessage(), this);
        }
    }

    private static class SendCallback extends DwollaCallback<SendResponse> {
        @Override
        public void success(SendResponse response, retrofit.client.Response r) {
            System.out.println("Asynchronous transaction id: " + (response.Success ? response.Response : response.Message));
        }

        public void failure(RetrofitError error) {
            super.failure(error.getMessage(), this);
        }
    }
}