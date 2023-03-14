package com.example.myapplication;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView titlesignin;
    private EditText email;
    private EditText password;
    private Button submit;
    private Button signuppage;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        titlesignin= findViewById(R.id.signintitle);
        email = findViewById(R.id.emailsignin);
        password = findViewById(R.id.passwordsignin);
        submit = findViewById(R.id.submitsignin);
        submit.setOnClickListener(this);
        signuppage = findViewById(R.id.signupb);
        signuppage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==submit && email.length()>0 && password.length()>7){
            Intent i = new Intent(this, home_activity1.class);
            i.putExtra("email", email.getText().toString());
            startActivity(i);
        }
        if (view==signuppage){
            Intent i = new Intent(MainActivity.this, signupactivity.class);
            startActivity(i);
        }
    }


    private void signin_user(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent i = new Intent(MainActivity.this, home_activity1.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}