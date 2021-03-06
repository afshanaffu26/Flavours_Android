package com.example.flavours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    TextView conditions,textLogin;
    Button btnSignUp;
    ProgressBar progressBar;
    CheckBox checkBox;
    EditText editName,editEmail,editPassword,editConfirmPassword;
    private FirebaseAuth mAuth;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editName=(EditText) findViewById(R.id.editName);
        editEmail=(EditText) findViewById(R.id.editEmail);
        editPassword=(EditText) findViewById(R.id.editPassword);
        editConfirmPassword=(EditText) findViewById(R.id.editConfirmPassword);
        progressBar=(ProgressBar) findViewById(R.id.progressbar);
        checkBox = findViewById(R.id.checkBox);
        mAuth = FirebaseAuth.getInstance();
        conditions = findViewById(R.id.conditions);
        conditions.setOnClickListener(this);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
        textLogin=findViewById(R.id.textLogin);
        textLogin.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flavours");
        //display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.conditions:
                startActivity(new Intent(getApplicationContext(), TermsActivity.class));
                break;
            case R.id.btnSignUp:
                this.navigateToLogin();
                //startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                break;
            case R.id.textLogin:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

        }
    }
    public void navigateToLogin() {
        final String name = editName.getText().toString().trim();
        String email=editEmail.getText().toString().trim();
        String password=editPassword.getText().toString().trim();
        String confirmPassword=editConfirmPassword.getText().toString().trim();
        if(name.isEmpty())
        {
            editName.setError("Name is required");
            editName.requestFocus();
            return;
        }
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
        if(confirmPassword.isEmpty())
        {
            editConfirmPassword.setError("Password is required");
            editConfirmPassword.requestFocus();
            return;
        }
        if(!password.equals(confirmPassword))
        {
            editPassword.setError("Passwords do not match");
            //editConfirmPassword.setError("Passwords do not match");
            editPassword.requestFocus();
            return;
        }
        if(!checkBox.isChecked())
        {
            Toast.makeText(getApplicationContext(),"Please read and accept the Terms and Conditions",Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name).build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //Toast.makeText(getApplicationContext(), "User profile Updated", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    //if (user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp()) {
                    //FirebaseAuth.getInstance().signOut();
                    finish();
                    //startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    Toast.makeText(getApplicationContext(), "Welcome to Flavours!", Toast.LENGTH_SHORT).show();
                    //}
                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"Email is already registered",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}