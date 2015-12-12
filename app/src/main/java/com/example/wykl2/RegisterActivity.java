package com.example.wykl2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wykl2.View.MainActivity;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button saveButton;
    private View.OnClickListener saveButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            registerUser();
        }
    };
    private SignUpCallback signUpCallback = new SignUpCallback() {
        @Override
        public void done(ParseException e) {
            if(e == null){
                Toast.makeText(RegisterActivity.this, "zarejestrowano usera", Toast.LENGTH_SHORT)
                        .show();

                openMainActicity();

            }else{
                Toast.makeText(RegisterActivity.this, "Błąd: "+e.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };

    private void openMainActicity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void registerUser() {
        String username = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        ParseUser newUser = new ParseUser();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(username);

        newUser.signUpInBackground(signUpCallback);

       ParseUser user = ParseUser.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginEditText = (EditText) findViewById(R.id.login_et);
        passwordEditText = (EditText) findViewById(R.id.password_et);
        saveButton = (Button) findViewById(R.id.save_btn);
        saveButton.setOnClickListener(saveButtonClick);
    }
}
