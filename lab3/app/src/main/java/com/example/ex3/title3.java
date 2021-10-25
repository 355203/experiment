package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class title3 extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title3);
        textView = (TextView) findViewById(R.id.textView);
        registerForContextMenu(textView);
    }

    @Override
    //装填R.Menu.menu_main菜单，并添加到menu中
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //上下文菜单中菜单项被单击时，触发该方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //勾选菜单项
        item.setChecked(true);
        switch (item.getItemId()){
            case R.id.red_font:
                item.setChecked(true);
                textView.setTextColor(Color.RED);
                break;
            case R.id.black_font:
                item.setChecked(true);
                textView.setTextColor(Color.BLACK);
                break;
            case R.id.font_10:
                textView.setTextSize(10 * 2);
                break;
            case R.id.font_16:
                textView.setTextSize(16 * 2);
                break;
            case R.id.font_20:
                textView.setTextSize(20 * 2);
                break;
            case R.id.sim:
                Toast.makeText(title3.this,"您单击了普通菜单项",Toast.LENGTH_SHORT)
                        .show();
                break;
        }
        return true;
    }
}