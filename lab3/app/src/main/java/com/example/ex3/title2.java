package com.example.ex3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class title2 extends AppCompatActivity {

    LinearLayout login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title2);
        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {

            AlertDialog.Builder dialog = new AlertDialog.Builder(title2.this);
            @Override
            public void onClick(View v) {
                login = (LinearLayout) getLayoutInflater().inflate(R.layout.login, null);
                dialog.setIcon(R.drawable.header_logo)
                        .setView(login)
                        .setNegativeButton("Cancel", (dialog1, which) -> {})
                        .setPositiveButton("Sign in",(dialog1, which) -> {})
                        .create().show();
            }
        });
    }
}