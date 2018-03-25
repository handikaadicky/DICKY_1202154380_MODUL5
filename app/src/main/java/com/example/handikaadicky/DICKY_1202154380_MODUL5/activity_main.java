package com.example.handikaadicky.DICKY_1202154380_MODUL5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class activity_main extends AppCompatActivity {
    //inisiasi awal
    database db;
    RecyclerView review;
    com.example.handikaadicky.DICKY_1202154380_MODUL5.adapter adapter;
    ArrayList<itemtodo> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisiasi semua variabel
        review = findViewById(R.id.listtodo);
        list = new ArrayList<>();
        db = new database(this);
        db.getAllItems(list);

        //inisiasi sharedpreferences untuk penyimpanan data size kecil
        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("pref", 0);
        int warna = pref.getInt("background", R.color.white);

        //menentukan adapter untuk recycler view
        adapter = new adapter(this, list, warna);
        //atur ukuran
        review.setHasFixedSize(true);
        //atur layoutmanager
        review.setLayoutManager(new LinearLayoutManager(this));
        //atur adapter
        review.setAdapter(adapter);
        //memanggil iniswipe
        initswipe();
    }
    public void initswipe(){
        //menambahkan ItemTouchHelper pada RecyclerView
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            //atur perpindahan rcview
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            //ketika digeser
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                itemtodo cur = adapter.getItem(pos);

                //kondisi ketika dislide kanan dan kiri akan terhapus
                if(direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){
                    //hapusdata
                    if(db.deletedata(cur.getName())){
                        adapter.removeitem(pos);
                        Snackbar.make(findViewById(R.id.roothome), "Item deleted", 1500).show();
                    }
                }
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        //panggil callback itemtouchelper
        helper.attachToRecyclerView(review);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle action bar ketika diklik
        int id = item.getItemId();
        //get id
        if(id==R.id.menusetting){
            //intent pada Setting
            startActivity(new Intent(activity_main.this, Setting.class)); //melakukan intent
            finish();// mengakhiri intent
        }
        return true;
    }

    //ketika float action di klik intent pada addtodo.class
    public void tambahin(View view) {
        startActivity(new Intent(activity_main.this, addtodo.class));//membuat dan memulai intent
        finish();//mengakhiri intent
    }
}
