package com.example.android.notepad;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteSearch extends ListActivity implements SearchView.OnQueryTextListener {

    private static final String[] PROJECTION = new String[] {
            NotePad.Notes._ID, //
            NotePad.Notes.COLUMN_NAME_TITLE, // 笔记标题
            NotePad.Notes.COLUMN_NAME_NOTE, //笔记内容
            NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, //笔记修改时间
            NotePad.Notes.COLUMN_NAME_BACK_COLOR, //笔记背景颜色
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_search_list);

        Intent intent = getIntent();

        if (intent.getData() == null) {
            intent.setData(NotePad.Notes.CONTENT_URI);
        }
        SearchView searchview = (SearchView)findViewById(R.id.search_view);
        //为查询文本框注册监听器
        searchview.setOnQueryTextListener(NoteSearch.this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;

    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String selection = NotePad.Notes.COLUMN_NAME_TITLE + " Like ? ";

        String[] selectionArgs = { "%"+newText+"%" };

        Cursor cursor = managedQuery(
                getIntent().getData(),
                PROJECTION,
                selection,
                selectionArgs,
                NotePad.Notes.DEFAULT_SORT_ORDER
        );

        String[] dataColumns = {
                NotePad.Notes.COLUMN_NAME_TITLE, //笔记标题
                NotePad.Notes.COLUMN_NAME_NOTE, //笔记内容
                NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE //笔记修改时间
        };

        int[] viewIDs = {
                android.R.id.text1,
                R.id.in_note, //显示笔记内容的组件名
                android.R.id.text2 };

        MyCursorAdapter adapter = new MyCursorAdapter(
                this,
                R.layout.noteslist_item,
                cursor,
                dataColumns,
                viewIDs

        );

        //显示时间格式
        SimpleCursorAdapter.ViewBinder viewBinder=new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int i)
            {
                if(cursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE)==i){

                    TextView textView1=(TextView)view;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.CHINA);
                    Date date=new Date(cursor.getLong(i));
                    String time=format.format(date);
                    Log.d("TIME", "onCreate1:"+time);
                    textView1.setText(time);
                    return true;
                }
                return false;
            }
        };
        adapter.setViewBinder(viewBinder);

        setListAdapter(adapter);
        return true;

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);
        String action = getIntent().getAction();

        if (Intent.ACTION_PICK.equals(action) || Intent.ACTION_GET_CONTENT.equals(action)) {
            setResult(RESULT_OK, new Intent().setData(uri));
        } else {
            startActivity(new Intent(Intent.ACTION_EDIT, uri));
        }
    }
}
