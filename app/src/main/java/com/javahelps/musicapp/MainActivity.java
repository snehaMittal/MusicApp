package com.javahelps.musicapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextMail;
    EditText editTextPassword;
    Button buttonSignin ;
    TextView textView ;
    ProgressDialog progressBar;
    public FirebaseAuth fireBaseAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fireBaseAuth = FirebaseAuth.getInstance();
        if (fireBaseAuth.getCurrentUser() != null){

            finish();
            startActivity(new Intent(getApplicationContext() , MainActivity2.class));
        }
        progressBar = new ProgressDialog(this);
        editTextMail = (EditText)findViewById(R.id.EmailEditText);
        editTextPassword = (EditText)findViewById(R.id.PassEditText);
        buttonSignin = (Button)findViewById(R.id.SignInButton);
        textView = (TextView)findViewById(R.id.DirecttextView);
        buttonSignin.setOnClickListener(this);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == buttonSignin) {
            registerUser();
        }
        if (v == textView) {
            //will open sign in
            startActivity(new Intent(this , LoginActivity.class));
        }
    }

    private void registerUser() {
        String email = editTextMail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, " Please Enter your Email" , Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, " Please Enter your Password" , Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setMessage("Registering user...Please wait");
        progressBar.show();

        fireBaseAuth.createUserWithEmailAndPassword(email , password)
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
                            Toast.makeText(MainActivity.this,"Could not register ..Please try again" , Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }
}

