package com.javahelps.musicapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextMailPassword;
    EditText editTextPasswordPassword;
    Button buttonSigninPassword ;
    TextView textViewPassword ;
    ProgressDialog progressBar;
    FirebaseAuth fireBaseAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fireBaseAuth = FirebaseAuth.getInstance();
        if (fireBaseAuth.getCurrentUser() != null){

            finish();
            startActivity(new Intent(getApplicationContext() , MainActivity2.class));
        }
        progressBar = new ProgressDialog(this);
        editTextMailPassword = (EditText)findViewById(R.id.EmailEditTextSignUp);
        editTextPasswordPassword = (EditText)findViewById(R.id.PassEditTextSignUp);
        buttonSigninPassword = (Button)findViewById(R.id.SignInButtonSignUp);
        textViewPassword = (TextView)findViewById(R.id.DirecttextViewSignUp);

        buttonSigninPassword.setOnClickListener(this);
        textViewPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonSigninPassword){
            userLogin();
        }
        if (v == textViewPassword){
            finish();
            startActivity(new Intent(this , MainActivity.class));
        }
    }

    private void userLogin() {
        String email = editTextMailPassword.getText().toString().trim();
        String password = editTextPasswordPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, " Please Enter your Email" , Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, " Please Enter your Password" , Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setMessage("Registering user...");
        progressBar.show();

        fireBaseAuth.signInWithEmailAndPassword(email , password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressBar.dismiss();
                            finish();
                            startActivity(new Intent(getApplicationContext() , MainActivity2.class));

                        }
                        else {
                            progressBar.dismiss();
                            Toast.makeText(LoginActivity.this,"Could not register ..Please try again" , Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
