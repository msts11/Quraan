package com.example.asus.quraan;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class Sign_Up extends Activity {
    // UI references.
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText passwordAgainEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        // Set up the sign up form.
        usernameEditText = (EditText) findViewById(R.id.username_edit_text_sign_up);
        emailEditText = (EditText) findViewById(R.id.email_edit_text_sign_up);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text_sign_up);
        passwordAgainEditText = (EditText) findViewById(R.id.password_again_edit_text_sign_up);
        passwordAgainEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.action_button_sign_up ||
                        actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    sign_up();
                    return true;
                }
                return false;
            }
        });

        // Set up the submit button click handler
        Button mActionButton = (Button) findViewById(R.id.action_button_sign_up);
        mActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sign_up();

            }
        });
    }


    private void sign_up() {
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String passwordAgain = passwordAgainEditText.getText().toString().trim();

        // Validate the sign up data
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder("Please");
        if (username.length() == 0) {
            validationError = true;
            validationErrorMessage.append("NO username");
        }
        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append("NO Password");
            }
            validationError = true;
            validationErrorMessage.append("Wrong Password");
        }
        if (!password.equals(passwordAgain)) {
            if (validationError) {
                validationErrorMessage.append("error_join");
            }
            validationError = true;
            validationErrorMessage.append("error_mismatched_passwords");
        }
        validationErrorMessage.append("error_end");

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(Sign_Up.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(Sign_Up.this);
        dialog.setMessage("progress_sign up");
        dialog.show();

        // Set up a new Parse user
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);


        // Call the Parse sign up method
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(Sign_Up.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(Sign_Up.this, MainPage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign__up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
