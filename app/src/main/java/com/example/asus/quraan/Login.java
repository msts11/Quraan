package com.example.asus.quraan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by rufflez on 7/8/14.
 */
public class Login extends Activity {

    private EditText usernameEditText;
    private EditText passwordEditText;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        usernameEditText = (EditText) findViewById(R.id.username_edit_text_login);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text_login);


        Button sign_up = (Button) findViewById(R.id.edittext_action_signup);
        sign_up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Sign_Up.class);
                startActivity(i);
            }
        });

        // Set up the submit button click handler
        findViewById(R.id.action_button_login).
                setOnClickListener(new View.OnClickListener() {
                                       public void onClick (View view){
                                           // Validate the log in data
                                           boolean validationError = false;
                                           StringBuilder validationErrorMessage =
                                                   new StringBuilder("getResources().getString(R.string.error_intro)");
                                           if (isEmpty(usernameEditText)) {
                                               validationError = true;
                                               validationErrorMessage.append("error_blank_username");
                                           }
                                           if (isEmpty(passwordEditText)) {
                                               if (validationError) {
                                                   validationErrorMessage.append("error_join");
                                               }
                                               validationError = true;
                                               validationErrorMessage.append("error_blank_password");
                                           }
                                           validationErrorMessage.append("wrong username or password");

                                           // If there is a validation error, display the error
                                           if (validationError) {
                                               Toast.makeText(Login.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                                                       .show();
                                               return;
                                           }

                                           // Set up a progress dialog
                                           final ProgressDialog dlg = new ProgressDialog(Login.this);
                                           dlg.setTitle("Please wait.");
                                           dlg.setMessage("Logging in.  Please wait.");
                                           dlg.show();
                                           // Call the Parse login method
                                           ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText()
                                                   .toString(), new LogInCallback() {

                                               @Override
                                               public void done(ParseUser user, ParseException e) {
                                                   dlg.dismiss();
                                                   if (e != null) {
                                                       // Show the error message
                                                       Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                                   } else {
                                                       // Start an intent for the dispatch activity
                                                       Intent intent = new Intent(Login.this, MainPage.class);
                                                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                       startActivity(intent);
                                                   }
                                               }
                                           });

                                       }
                                   }

                );
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
}