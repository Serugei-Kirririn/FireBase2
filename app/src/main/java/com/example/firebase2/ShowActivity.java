package com.example.firebase2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    private TextView tvName, tvSecName, tvEmail;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        init();
        getIntentMain();
    }
    private void init(){
        tvName = findViewById(R.id.edName);
        tvSecName = findViewById(R.id.edSec);
        tvEmail = findViewById(R.id.edEmail);
    }
    private void getIntentMain(){
        Intent i = getIntent();
        if(i != null)
        {
            tvName.setText(i.getStringExtra("user_name"));
            tvSecName.setText(i.getStringExtra("user_sec"));
            tvEmail.setText(i.getStringExtra("user_email"));
        }
    }
}
