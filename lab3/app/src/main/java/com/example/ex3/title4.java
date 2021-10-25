package com.example.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class title4 extends AppCompatActivity {

    View numView;
    TextView count;
    private String[] nameArr = new String[]{"One","Two","Three","Four","Five"};
    private int[] imgArr = new int[]{R.drawable.ic_launcher_foreground};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title4);

        List<Map<String, Object>> list = new ArrayList<>();
        for(int i=0;i<nameArr.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("img",imgArr[0]);
            map.put("name",nameArr[i]);
            list.add(map);
        }
        String[] from = {"img","name"};
        int [] to = {R.id.item4_img,R.id.item4_title};
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,list,R.layout.listview_title4,
                from,to);
        ListView listView=findViewById(R.id.lv1);
        listView.setAdapter(simpleAdapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                updateCount();
                mode.invalidate();
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                int id = item.getItemId();
                return false;
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_main4,menu);
                if(numView==null){
                    numView = LayoutInflater.from(title4.this).inflate(R.layout.title4_1,null);
                    count = (TextView) numView.findViewById(R.id.txt1);
                }
                mode.setCustomView(numView);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

                return false;
            }

            public void updateCount(){
                int n = listView.getCheckedItemCount();
                count.setText(n+"");
            }
        });
    }
}