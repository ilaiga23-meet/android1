package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signupactivity extends AppCompatActivity implements View.OnClickListener {
    private TextView titlesignup;
    private EditText password, email, name;
    private Button submit, signin;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        titlesignup = findViewById(R.id.signuptitle);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submitsignup);
        submit.setOnClickListener(this);
        signin = findViewById(R.id.signinpage);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==signin){
            create_user(email.getText().toString(), password.getText().toString());
        }
        if(view==submit && email.length()>0 && password.length()>7 && name.length()>0){
            Intent i = new Intent(this, home_activity1.class);
            i.putExtra("email", email.getText().toString());
            startActivity(i);
        }
    }

    private void create_user(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            User User = new User(name.getText().toString(), email, password);
                            String uid = mAuth.getCurrentUser().getUid().toString();
                            database.getReference("Users").child(uid).setValue(User);
                            Intent i = new Intent(signupactivity.this, home_activity1.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(signupactivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
