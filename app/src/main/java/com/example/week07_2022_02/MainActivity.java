package com.example.week07_2022_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.ListAdapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
ListView lv;

SQLiteDatabase db;
    Dbhelper db1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv= (ListView) findViewById(R.id.myList);

        db=openOrCreateDatabase("Test",MODE_PRIVATE,null);

        db1 = new Dbhelper(db);
        populateList();
    }


    class Dbhelper {

        SQLiteDatabase db;
        public Dbhelper(SQLiteDatabase db){
            this.db=db;
            db.execSQL("Create Table If not exists " +
                         "MyTable(" +
                          "Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                          " TASK  varchar NOT NULL, " +
                          " Done boolean );" );

            db.execSQL("insert  into MyTable( Task, Done ) Values('uyan', false);");
            db.close();

        }
    public Cursor  getAllList(){


        String sql  =  "Select * from MyTable Where Done=false";
        Cursor c = db.rawQuery(sql,null);
        return c;
    }


    public String get_data(){


        return null;
    }
    }
    private void populateList() {
        Cursor c = db1.getAllList();
        String[] fields = new String[]{db1.get_data()};

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.mylistlayout, c,
                fields, new int[] {R.id.EntryText});


        lv.setAdapter(cursorAdapter);
    }

}