package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class home_activity1 extends AppCompatActivity {
    private TextView title;
    private ListView listView;
    private ArrayList<User> users;
    private ArrayAdapter<User> arrayAdapter;
    private FirebaseDatabase database;
    private Menu menu;
    private FirebaseAuth mAuth;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        title = findViewById(R.id.hometitle);
        database = FirebaseDatabase.getInstance();
        database.getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()){
                    users.add(data.getValue(User.class));
                }

                listView= findViewById(R.id.users_Listview);
                arrayAdapter= new UserArrayAdapter(home_activity1.this, R.layout.users_row, users);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.signout) {
            mAuth.signOut();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        return true;
    }
}