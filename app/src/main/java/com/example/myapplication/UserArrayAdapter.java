package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserArrayAdapter extends ArrayAdapter<User> {
    private Context context;
    private int resource;


    public UserArrayAdapter(@NonNull Context context, int resource, ArrayList<User> objects) {
        super(context, resource, objects);
        this.context = (Context) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(this.resource, parent, false);
        }
        // Code goes here
        User user = getItem(position);
        if (user!= null){
            TextView name = convertView.findViewById(R.id.namerow);
            name.setText(user.getName());
            TextView email = convertView.findViewById(R.id.emailrow);
            email.setText(user.getEmail());
        }
        return convertView;

    }
}



