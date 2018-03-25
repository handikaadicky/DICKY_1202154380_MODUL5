package com.example.handikaadicky.DICKY_1202154380_MODUL5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addtodo extends AppCompatActivity {
    //inisiasi awal variabel
    EditText nama, desc, prio;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //membuat inisiasi pada masing-masing variabel
        nama = findViewById(R.id.todoname);
        desc = findViewById(R.id.description);
        prio = findViewById(R.id.priority);
        db = new database(this);
    }

    //method ketika back, akan kembali pada activity_main do intent
    @Override
    public void onBackPressed() {
        Intent a = new Intent(addtodo.this,activity_main.class);
        startActivity(a);
        this.finish();
    }
    //method ketika tambah todo ditekan
    public void tambah (View view){ //method ketika button tambah todo ditekan
        //kondisi field edittext kosong akan menampilkan toast error
        if (nama.getText().toString().length()==0){
            nama.setError("Insert Todo!");
            Toast.makeText(this, "Adding todo failed", Toast.LENGTH_SHORT).show();
        } else if (prio.getText().toString().length()==0){
            prio.setError("Insert Number of Priority");
            Toast.makeText(this, "Adding todo failed", Toast.LENGTH_SHORT).show();
        }
        else if
            //membuat kondisi yang mengecek database pada kolom nama, deskripsi, dan prioritas diinputkan
            (db.insertdata(new itemtodo(nama.getText().toString(), desc.getText().toString(), prio.getText().toString())))
            {
                //toast berhasil dan melakukan intent
                Toast.makeText(this, "Todo added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(addtodo.this, activity_main.class));
                this.finish();

            }
    }
}
