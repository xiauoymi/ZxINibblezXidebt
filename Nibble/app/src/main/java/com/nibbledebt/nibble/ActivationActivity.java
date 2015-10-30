package com.nibbledebt.nibble;

import android.content.Context;
import android.content.Intent;
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
import com.nibbledebt.nibble.security.SecurityContext;
import com.nibbledebt.nibble.security.TokenObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class ActivationActivity extends BaseLoaderActivity {

    private ActivationTask activationTask = null;
    private LoginTask loginTask = null;
    private View loginFormView;
    private EditText activationCodeEt;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);

        username = savedInstanceState.getString("username");
        password = savedInstanceState.getString("password");

        if(SecurityContext.getCurrentContext().isLoggedIn()){
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }

        loginFormView = findViewById(R.id.login_form);
        progressBar = (ProgressBar)findViewById(R.id.login_progress);
        progressContainer = findViewById(R.id.login_progress_container);

        final Button activateButton = (Button) findViewById(R.id.sign_in_button);
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

        String acode = activationCodeEt.getText().toString();

        boolean cancelActivation = false;
        View focusView = null;

        if (TextUtils.isEmpty(acode)) {
            activationCodeEt.setError(getString(R.string.acode_required));
            focusView = activationCodeEt;
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
            // The connection URL
            String url = "http://nibble-web.herokuapp.com/sslogin";

            MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
            values.add("nibbler_username", username);
            values.add("nibbler_password", password);

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();

            // Add the String message converter
            restTemplate.getMessageConverters().clear();
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            // Make the HTTP GET request, marshaling the response to a String
            ResponseEntity<String> result = restTemplate.postForEntity(url, values, String.class);

            if(result.getStatusCode().value()==302){
                if(result.getHeaders().getLocation().getPath().equalsIgnoreCase("/nibblehome.html")){
                    SecurityContext.getCurrentContext().setCookie((String)result.getHeaders().get("Set-Cookie").toArray()[0]);
                }
            }
        }
    }

    private class LoginTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... data) {
            try {
                doLogin(data[0], data[1]);
                return SecurityContext.getCurrentContext().getCookie()==null ? false : true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Boolean loginSuccessful) {
            if(!loginSuccessful){
                hideProgress();
            }else{
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                loginTask = null;
                hideProgress();
                finish();
            }
        }

        @Override
        protected void onCancelled() {
            loginTask = null;
            hideProgress();
        }

        private void doLogin(String username, String password) throws Exception {
            // The connection URL
            String url = "http://nibble-web.herokuapp.com/sslogin";

            MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
            values.add("nibbler_username", username);
            values.add("nibbler_password", password);

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();

            // Add the String message converter
            restTemplate.getMessageConverters().clear();
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            // Make the HTTP GET request, marshaling the response to a String
            ResponseEntity<String> result = restTemplate.postForEntity(url, values, String.class);

            if(result.getStatusCode().value()==302){
                if(result.getHeaders().getLocation().getPath().equalsIgnoreCase("/nibblehome.html")){
                    SecurityContext.getCurrentContext().setCookie((String)result.getHeaders().get("Set-Cookie").toArray()[0]);
                }
            }
        }
    }
}
