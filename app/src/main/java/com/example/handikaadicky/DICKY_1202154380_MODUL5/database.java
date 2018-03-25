package com.example.handikaadicky.DICKY_1202154380_MODUL5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//membuat class database
public class database extends SQLiteOpenHelper{
    //inisiasi awal
    Context con;
    SQLiteDatabase data;
    //membuat nama databasenya
    public static final String db_name = "todoosas.db";
    //membuat nama tabelnya
    public static final String table_name = "todo";
    //membuat nama nama kolom yang menyimpan datanya
    public static final String col_1 = "nama";
    public static final String col_2 = "descr";
    public static final String col_3 = "prio";

    //membuat class database
    public database(Context context) {
        super(context, db_name, null, 1);
        this.con = context;
        //update mengolah database
        data = this.getWritableDatabase();
        //konfigurasi mengatur primary key, jumlah karakter, dan jenis input
        data.execSQL("create table if not exists "+table_name+" (nama varchar(50) primary key, descr varchar(50), prio varchar(5))");
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //konfigurasi menggunakan SQLite sesuai dengan primary key, jumlah da jenis karakter yang diinputkan
        sqLiteDatabase.execSQL("create table if not exists "+table_name+" (nama varchar(50) primary key, descr varchar(50), prio varchar(5))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //perintah ini merupakan penanda bahwa database ini hanya cache online
        sqLiteDatabase.execSQL("drop table if exists "+table_name);
        //muat ulang data kembali
        onCreate(sqLiteDatabase);
    }

    // insert data
    public boolean insertdata(itemtodo satuan) {
        ContentValues cv = new ContentValues();
        //mengisi values sesuai dengan kolom
        cv.put(col_1, satuan.getName());
        cv.put(col_2, satuan.getDescription());
        cv.put(col_3, satuan.getPriority());
        //memasukkan baris baru
        long result = data.insert(table_name, null, cv);
        //pengkodisian untuk pengecekan
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    //menghapus data
    public boolean deletedata(String name){
        //mengembalikan data berupa data dari database telah dihapus
        return data.delete(table_name, col_1+"=\""+name+"\"", null)>0;

    }
    public void getAllItems(ArrayList<itemtodo> list){//mendapatkan semua item yang diinputkan
        Cursor cr = this.getReadableDatabase().rawQuery("select nama, descr, prio from "+table_name, null);
        while(cr.moveToNext()){
            list.add(new itemtodo(cr.getString(0), cr.getString(1), cr.getString(2))); //menambahkan item yang telah diset pada array
        }
    }
    public void ClearTabel(){ //mengahpus tabel
        data.execSQL("delete from "+table_name);
    }
}
