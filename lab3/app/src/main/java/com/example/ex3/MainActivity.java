package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String[] nameArr = new String[]{"Cat","Dog","Elephant","Lion","Monkey","Tiger"};
    private int[] ImageArr= new int[]{R.drawable.cat,R.drawable.dog,R.drawable.elephant,
            R.drawable.lion,R.drawable.monkey,R.drawable.tiger};
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Map<String, Object>> list = new ArrayList<>();
        for(int i=0;i<ImageArr.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("img",ImageArr[i]);
            map.put("name",nameArr[i]);
            list.add(map);
        }
        String[] from = {"img","name"};
        int [] to = {R.id.item_img,R.id.item_title};
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,list,R.layout.listview,
                from,to);
        ListView listView=findViewById(R.id.lv);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,nameArr[position],Toast.LENGTH_SHORT).show();
            }
        });
    }
}