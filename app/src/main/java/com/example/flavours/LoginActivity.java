package com.example.flavours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    EditText editEmail,editPassword;
    Button btnLogin;
    ProgressBar progressBar;
    TextView txt_recover_password,textSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flavours");

        mAuth = FirebaseAuth.getInstance();

        editEmail=(EditText) findViewById(R.id.editEmail);
        editPassword=(EditText) findViewById(R.id.editPassword);
        progressBar=(ProgressBar) findViewById(R.id.progressbar);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        txt_recover_password= findViewById(R.id.txt_recover_password);
        txt_recover_password.setOnClickListener(this);
        textSignUp=findViewById(R.id.textView2);
        textSignUp.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,HomeActivity.class));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                userLogin();
                //startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                break;
            case R.id.txt_recover_password:
                startActivity(new Intent(getApplicationContext(), ForgotActivity.class));
                break;
            case R.id.textView2:
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));

        }
    }
    public void userLogin() {
        final String email= editEmail.getText().toString().trim();
        String password= editPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please enter valid email");
            editEmail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            editPassword.setError("Password is required");
            editPassword.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            editPassword.setError("Minimum length of password should be 6");
            editPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful())
                {
                    finish();
                    Intent i= new Intent(LoginActivity.this,HomeActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}