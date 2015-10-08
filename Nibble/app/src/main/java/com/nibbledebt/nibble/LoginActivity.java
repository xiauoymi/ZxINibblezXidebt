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
import com.google.android.gms.iid.InstanceID;
import com.nibbledebt.nibble.common.BaseLoaderActivity;
import com.nibbledebt.nibble.security.SecurityContext;
import com.nibbledebt.nibble.security.TokenObject;


import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class LoginActivity extends BaseLoaderActivity {

    private LoginTask loginTask = null;
    private View loginFormView;
    private EditText usernameTextView;
    private EditText passwordTextView;
    private TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new TokenTask().execute(this.getBaseContext());
        if(SecurityContext.getCurrentContext().isLoggedIn()){
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }

        usernameTextView = (EditText) findViewById(R.id.email);
//        usernameTextView.setText(((TokenObject)(SecurityContext.getCurrentContext().getSessionMap().get("token"))).getData(""));
        passwordTextView = (EditText) findViewById(R.id.password);
        passwordTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_NULL) {
                    initLogin();
                    return true;
                }
                return false;
            }
        });



        loginFormView = findViewById(R.id.login_form);
        progressBar = (ProgressBar)findViewById(R.id.login_progress);
        progressContainer = findViewById(R.id.login_progress_container);

        //adding underline and link to signup textview
        registerTextView = (TextView) findViewById(R.id.signUpTextView);
        registerTextView.setPaintFlags(registerTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Linkify.addLinks(registerTextView, Linkify.ALL);

        registerTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LoginActivity", "RegisterActivity activated.");
                showProgress();
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
                hideProgress();
            }
        });

        final Button loginButton = (Button) findViewById(R.id.sign_in_button);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    initLogin();
                } else {
                    //textView.setText("No network connection available.");
                }

            }
        });
    }


    /**
     * Validate Login form and authenticate.
     */
    public void initLogin() {
        if (loginTask != null) {
            return;
        }

        usernameTextView.setError(null);
        passwordTextView.setError(null);

        String username = usernameTextView.getText().toString();
        String password = passwordTextView.getText().toString();

        boolean cancelLogin = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordTextView.setError(getString(R.string.invalid_password));
            focusView = passwordTextView;
            cancelLogin = true;
        }

        if (TextUtils.isEmpty(username)) {
            usernameTextView.setError(getString(R.string.field_required));
            focusView = usernameTextView;
            cancelLogin = true;
        }

        if (cancelLogin) {
            // error in login
            focusView.requestFocus();
            hideProgress();
        } else {
            // show progress spinner, and start background task to login
            loginTask = new LoginTask();
            loginTask.execute(username, password);
        }
    }

    private boolean isPasswordValid(String password) {
        //add your own logic
        return password.length() > 2;
    }



    private class LoginTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... data) {
            try {
                doLogin(usernameTextView.getText().toString(), passwordTextView.getText().toString());
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
                passwordTextView.setError(getString(R.string.incorrect_password));
                passwordTextView.requestFocus();
                loginTask = null;

                hideProgress();
                //textView.setText("No network connection available.");
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


    private class TokenTask extends AsyncTask<Context, Void, Void> {
        @Override
        protected Void doInBackground(Context... data) {
            try {
//                InstanceID instanceID = InstanceID.getInstance(data[0]);
//                String token = instanceID.getToken(getString(R.string.gcm_default_sender_id),
//                        GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                GoogleCloudMessaging gcm = null;
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(data[0]);
                }
                String regid = gcm.register(getString(R.string.gcm_default_sender_id));
                SecurityContext.getCurrentContext().getSessionMap().put("token", new TokenObject(regid));
                Log.i("GCM",regid);
            }catch(Exception e){
                Log.e("", "", e);
            }
            return null;
        }
    }
}
