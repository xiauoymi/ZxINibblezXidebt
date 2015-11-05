package com.nibbledebt.nibble;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.nibbledebt.nibble.common.BaseLoaderActivity;
import com.nibbledebt.nibble.common.RestTemplateCreator;
import com.nibbledebt.nibble.integration.model.Bank;
import com.nibbledebt.nibble.integration.model.CustomerData;
import com.nibbledebt.nibble.integration.model.MemberDetails;
import com.nibbledebt.nibble.registration.RegisterMessageDialog;
import com.nibbledebt.nibble.security.BanksObject;
import com.nibbledebt.nibble.security.RegisterObject;
import com.nibbledebt.nibble.security.SecurityContext;
import com.nibbledebt.nibble.security.TokenObject;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ActivationActivity extends BaseLoaderActivity {

    private ActivationTask activationTask = null;
    private View loginFormView;
    private EditText activationCodeEt;
    private String username;
    private String password;
    private EditText usernameEt;
    private EditText passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);

        if(savedInstanceState!=null){
            username = savedInstanceState.getString("username");
            password = savedInstanceState.getString("password");

        }

        activationCodeEt = (EditText) findViewById(R.id.acode);
        usernameEt = (EditText) findViewById(R.id.email);
        passwordEt = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar)findViewById(R.id.activation_progress);
        progressContainer = findViewById(R.id.activation_progress_container);

        final Button activateButton = (Button) findViewById(R.id.activation_button);
        activateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    initActivation();
                } else {
                    //textView.setText("No network connection available.");
                }

            }
        });
    }


    /**
     * Validate Login form and authenticate.
     */
    public void initActivation() {
        if (activationTask != null) {
            return;
        }

        activationCodeEt.setError(null);
        usernameEt.setError(null);
        passwordEt.setError(null);

        String acode = activationCodeEt.getText().toString();
        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();

        boolean cancelActivation = false;
        View focusView = null;

        if (TextUtils.isEmpty(acode)) {
            activationCodeEt.setError(getString(R.string.acode_required));
            focusView = activationCodeEt;
            cancelActivation = true;
        }

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordEt.setError(getString(R.string.invalid_password));
            focusView = passwordEt;
            cancelActivation = true;
        }

        if (TextUtils.isEmpty(username)) {
            usernameEt.setError(getString(R.string.field_required));
            focusView = usernameEt;
            cancelActivation = true;
        }

        if (cancelActivation) {
            // error in login
            focusView.requestFocus();
            hideProgress();
        } else {
            // show progress spinner, and start background task to login
            activationTask = new ActivationTask();
            activationTask.execute(username, password, acode);
        }
    }

    private boolean isPasswordValid(String password) {
        //add your own logic
        return password.length() > 2;
    }



    private class ActivationTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... data) {
            try {
                doActivation(data[0], data[1], data[2]);
                return SecurityContext.getCurrentContext().getCookie()==null ? false : true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Boolean activationSuccessful) {
            if(!activationSuccessful){
                activationCodeEt.setError(getString(R.string.acode_incorrect));
                activationCodeEt.requestFocus();
                activationTask = null;

                hideProgress();
                //textView.setText("No network connection available.");
            }else{
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                activationTask = null;
                hideProgress();
                finish();
            }
        }

        @Override
        protected void onCancelled() {
            activationTask = null;
            hideProgress();
        }

        private void doActivation(String username, String password, String acode) throws Exception {

            /// Create a new RestTemplate instance
            RestTemplate restTemplate = RestTemplateCreator.getTemplateCreator().getNewTemplate();

            CustomerData data = new CustomerData();
            data.setActivationCode(acode);
            data.setEmail(username);
            data.setPassword(password);

            // Make the HTTP GET request, marshaling the response to a String
            try {
                ResponseEntity<Void> response = restTemplate.postForEntity(getString(R.string.activurl), data, Void.class);
                if(response.getStatusCode().value()>=200 && response.getStatusCode().value()<205){
                    MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
                    values.add("nibbler_username", username);
                    values.add("nibbler_password", password);

                    // Make the HTTP GET request, marshaling the response to a String
                    ResponseEntity<String> result = restTemplate.postForEntity(getString(R.string.loginurl), values, String.class);

                    if(result.getStatusCode().value()==302){
                        if(result.getHeaders().getLocation().getPath().equalsIgnoreCase("/nibblehome.html")){
                            SecurityContext.getCurrentContext().setCookie((String)result.getHeaders().get("Set-Cookie").toArray()[0]);
                            ResponseEntity<MemberDetails> profileResult = restTemplate.getForEntity(getString(R.string.profileurl), MemberDetails.class);
                            if(profileResult.getStatusCode().value()==200){
                                CustomerData cdata = new CustomerData();
                                cdata.setFirstName(profileResult.getBody().getFirstName());
                                cdata.setLastName(profileResult.getBody().getLastName());
                                cdata.setUsername(profileResult.getBody().getUsername());
                                SecurityContext.getCurrentContext().getSessionMap().put("memberDetails", new RegisterObject(cdata));
                            }
                        }
                    }
                }else{
                    throw new HttpServerErrorException(HttpStatus.MULTI_STATUS);

                }
            } catch (HttpServerErrorException  e) {

                RegisterMessageDialog dialog = new RegisterMessageDialog();
                dialog.setHeader("Could not Activate account! (CS "+e.getStatusCode().value()+")");
                dialog.setMessage("We're Sorry! But looks like we are unable to activate this account at the moment. Please check back later as one of our techs try to resolve the issue." +
                        " Please double-check the activation code and ensure that it is correct. ");

            }
        }
    }
}
