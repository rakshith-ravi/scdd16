package com.startupclubs.scdd16;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*LinearLayout layout = new LinearLayout(this);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.splash_screen);
        layout.addView(imageView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setBackgroundResource(android.R.color.white);
        setContentView(layout);*/
        setContentView(R.layout.activity_login);

        /*new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Data.load(LoginActivity.this);
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (getSupportActionBar() != null)
                    getSupportActionBar().show();
                /*
                if he has already registered, set home screen as content view
                else, set activity_login as content view
                 * /
                setContentView(R.layout.activity_login);
                setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
            }
        }.execute();*/
    }

    //fired when "login" is clicked in the activity_login screen
    public void loginClicked(View view) {
        final String username = ((EditText) findViewById(R.id.login_username)).getText().toString();
        final String password = ((EditText) findViewById(R.id.login_password)).getText().toString();
        //show some progress dialog when getting the information from the internet
        //done in an AsyncTask to avoid blocking the UI thread
        new AsyncTask<Void, Void, Void>() {
            ProgressDialog progressDialog;
            Data.NetworkRequest result = Data.NetworkRequest.Failure;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setTitle("Logging in");
                progressDialog.setMessage("Please wait while we log you in");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                result = Data.validateLogin(username, password);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressDialog.dismiss();
                if(result == Data.NetworkRequest.Failure) {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Invalid username or password")
                            .setMessage("Please check your username and password")
                            .setCancelable(false)
                            .setPositiveButton("OK", null)
                            .create()
                            .show();
                }
                else if(result == Data.NetworkRequest.UnableToConnect) {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Unable to connect")
                            .setMessage("Unable to connect to the internet. Please check your internet connection and try again")
                            .setCancelable(false)
                            .setPositiveButton("OK", null)
                            .create()
                            .show();
                }
                else if(result == Data.NetworkRequest.Success) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }.execute();
    }

    /**
     * Fired when the "lost your password?" button is clicked on the activity_login screen
     * @param view The View which caused the action (in this case, always the "lost your password?" label)
     */
    public void lostPasswordClicked(View view) {

    }

    /**
     * Fired when the "New User" button is clicked on the activity_login screen
     * @param view The View which caused the action (in this case, always the "New user" label)
     */
    public void newUserClicked(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
