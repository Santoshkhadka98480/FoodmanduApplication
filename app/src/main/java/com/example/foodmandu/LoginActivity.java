package com.example.foodmandu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodmandu.bll.LoginBLL;
import com.example.foodmandu.fragment.HomeFragment;
import com.example.foodmandu.strictmode.StrictModeClass;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    TextView tvSignUp;
    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    private static final Pattern PASSWORD_PATTERN= Pattern.compile("^"+"(?=.*[0-9])"+ "(?=.*[a-z])" +"(?=.*[A-Z])"+".{8,}"+"$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvSignUp = findViewById(R.id.tvSignUp);
        btnLogin = findViewById(R.id.btnLogin);


       tvSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
               startActivity(intent);

           }
       });

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (validate()){
                   login();
               }

           }
       });
    }

    private boolean validate() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(etEmail.getText())) {
            etEmail.requestFocus();
            etEmail.setError("Please Enter Email Address");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.requestFocus();
            etEmail.setError("Please enter valid email address");
            return false;
        }
        else if (TextUtils.isEmpty(etPassword.getText())) {
            etPassword.requestFocus();
            etPassword.setError("Please Enter Password");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            etPassword.requestFocus();
            etPassword.setError("Password should contain 8 character long with upper,lower case, numbers");
            return false;
        }
        return true;
    }


    private void login() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        LoginBLL loginBLL = new LoginBLL();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(email, password)){
            Toast.makeText(this, "Login Success!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Either email or password is incorrect", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
        }

    }
}
