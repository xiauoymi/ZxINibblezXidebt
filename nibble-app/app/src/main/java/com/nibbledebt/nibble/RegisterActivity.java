package com.nibbledebt.nibble;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import com.nibbledebt.nibble.common.BaseLoaderActivity;


public class RegisterActivity extends FragmentActivity {

    private EditText usernameTextView;
    private EditText passwordTextView;
    private EditText fnameTextView;
    private EditText lnameTextView;
    private SignupTask signupTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameTextView = (EditText) findViewById(R.id.register_email);
        passwordTextView = (EditText) findViewById(R.id.register_password);
        fnameTextView = (EditText) findViewById(R.id.register_firstname);
        lnameTextView = (EditText) findViewById(R.id.register_lastname);
//        progressBar = (ProgressBar)findViewById(R.id.signup_progress);
//        progressContainer = findViewById(R.id.signup_progress_container);

//        final Button signupButton = (Button) findViewById(R.id.sign_up_button);
//        signupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                showProgress();
//                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//                if (networkInfo != null && networkInfo.isConnected()) {
//                    initSignup();
//                } else {
//                    //textView.setText("No network connection available.");
//                }
//            }
//        });
    }



    public void initSignup() {
        if (signupTask != null) {
            return;
        }




        usernameTextView.setError(null);
        passwordTextView.setError(null);
        fnameTextView.setError(null);
        lnameTextView.setError(null);

        String username = usernameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String firstname = fnameTextView.getText().toString();
        String lastname = lnameTextView.getText().toString();

        boolean cancelSignup = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordTextView.setError(getString(R.string.invalid_password));
            focusView = passwordTextView;
            cancelSignup = true;
        }

        if (TextUtils.isEmpty(username)) {
            usernameTextView.setError(getString(R.string.field_required));
            focusView = usernameTextView;
            cancelSignup = true;
        }

        if (TextUtils.isEmpty(firstname)) {
            fnameTextView.setError(getString(R.string.field_required));
            focusView = fnameTextView;
            cancelSignup = true;
        }

        if (TextUtils.isEmpty(lastname)) {
            lnameTextView.setError(getString(R.string.field_required));
            focusView = lnameTextView;
            cancelSignup = true;
        }


        if (cancelSignup) {
            // error in login
            focusView.requestFocus();

//            hideProgress();
        } else {
            // show progress spinner, and start background task to login
            signupTask = new SignupTask();
            signupTask.execute(username, password, firstname, lastname);
        }
    }

    private boolean isPasswordValid(String password) {
        //add your own logic
        return password.length() > 2;
    }

    private class SignupTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... data) {
            return "";
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            if(result.equalsIgnoreCase("Success")){
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
                signupTask = null;

            }else if(result.equalsIgnoreCase("Credential is already taken")){
                usernameTextView.setError(getString(R.string.username_taken));
                usernameTextView.requestFocus();
                signupTask = null;
            }else{
                passwordTextView.setError(getString(R.string.incorrect_password));
                passwordTextView.requestFocus();
                signupTask = null;
                //textView.setText("No network connection available.");
            }

//            hideProgress();
        }

        @Override
        protected void onCancelled() {
            signupTask = null;
//            hideProgress();
        }

        private void doSignup(String username, String password, String firstName, String lastName) throws Exception{

        }
    }
}