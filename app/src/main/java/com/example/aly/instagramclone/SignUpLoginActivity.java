package com.example.aly.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity
{
        private EditText edtSignUpName, edtSignUpPassword, edtName, edtPassword;
        private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtSignUpName = findViewById(R.id.edtSignUpName);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                final ParseUser appNewUser = new ParseUser();
                appNewUser.setUsername(edtSignUpName.getText().toString());
                appNewUser.setPassword(edtSignUpPassword.getText().toString());

                appNewUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this, appNewUser.get("username") +
                                            " has signed up successfully.", FancyToast.LENGTH_LONG,
                                    FancyToast.SUCCESS, true).show();

                            Intent intent = new Intent(SignUpLoginActivity.this, Welcome.class);
                            startActivity(intent);
                        }
                        else
                            {
                                FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG,
                                    FancyToast.ERROR, true).show();
                            }
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ParseUser.logInInBackground(edtName.getText().toString(), edtPassword.getText().toString(), new LogInCallback()
                {
                    @Override
                    public void done(ParseUser user, ParseException e)
                    {
                        if (user != null && e == null)
                        {
                            FancyToast.makeText(SignUpLoginActivity.this, user.get("username") +
                                            " has signed up successfully.", FancyToast.LENGTH_LONG,
                                    FancyToast.SUCCESS, true).show();

                            Intent intent = new Intent(SignUpLoginActivity.this, Welcome.class);
                            startActivity(intent);
                        }
                        else
                        {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG,
                                    FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });
    }
}
