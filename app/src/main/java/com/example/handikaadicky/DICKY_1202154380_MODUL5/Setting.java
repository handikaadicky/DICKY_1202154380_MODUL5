package com.example.handikaadicky.DICKY_1202154380_MODUL5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    //mendeklarasikan semua variabel
    int warna; TextView warnanya;
    AlertDialog.Builder bld;
    SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //menginsiasi semua variabel yang telah dideklrasikan
            bld = new AlertDialog.Builder(this);
            SharedPreferences pref = getApplicationContext().getSharedPreferences("pref", 0);
            edit = pref.edit(); //perintah untuk mengubah nilai preferences
            warna = pref.getInt("background", R.color.white); //mengatur warnanya
            warnanya = findViewById(R.id.warnanya);
            warnanya.setText(getwarna(warna));
        }
        //ketika item dipilih
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){ //membuat kondisi
            this.onBackPressed(); //memanggil method tersebut
        }
        return true;
    }
    public void onBackPressed() { //membuat fungsi toolbar back
        startActivity(new Intent(Setting.this, activity_main.class)); //membuat dan melakukan intent
        finish();//mengakhiri intent
    }
    public void bukadialogwarna(View view) { //method ketika layout diklik
        bld.setTitle("Choose Color");//mengatur judul alert dialog
        View v = getLayoutInflater().inflate(R.layout.activity_colorsetting, null);//mengatur layout inflaternya
        bld.setView(v); //menampilkannya

        //Menentukan radiobutton yang dipilih
        final RadioGroup rg = v.findViewById(R.id.rg);
        rg.check(getIntCOlor(warna));//menagtur warna
        bld.setPositiveButton("OK", new DialogInterface.OnClickListener() { //mengatur button OK
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int check = rg.getCheckedRadioButtonId(); //mengecek button yang dipilih
                switch (check){
                    //jika memilih warna biru
                    case R.id.warnabiru:
                        warna = R.color.blue;
                        break;
                        //jika memilih warna hijau
                    case R.id.warnahijau:
                        warna = R.color.green;
                        break;
                        //jika memilih warna merah
                    case R.id.warnamerah:
                        warna = R.color.red;
                        break;
                        //jika memilih warna putih
                    case R.id.warnaputih:
                        warna = R.color.white;
                        break;
                        //jika memilih warna orange
                    case R.id.warnaorange:
                        warna = R.color.orange;
                        break;
                }

                //Mengatur SharedPreference dan mengubah text
                warnanya.setText(getwarna(warna));
                edit.putInt("background", warna);
                edit.commit();
            }
        });
        bld.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() { //mengatur button cancel
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss(); //menutup dialog interface
            }
        });

        //Menampilkan dialog
        bld.create().show();
    }
    public String getwarna(int i){ //membuat penkondisian
        if(i==R.color.green){
            return "Green";
            //mengembalikan nilai berupa warna hijau
        }else if(i==R.color.blue){
            return "Blue";
            //mengembalikan nilai berupa warna biru
        }else if(i==R.color.red){
            return "Red";
            //mengembalikan nilai berupa warna merah
        }else if (i==R.color.white){
            return "White";
            //mengembalikan nilai berupa warna putih
        }else{
            return "Orange";
            //mengembalikan nilai berupa warna orange
    }}
    public int getIntCOlor(int i){ //mengatur letak warna
        if(i==R.color.green){
            return R.id.warnahijau;
            //jika memilih warna hijau
        }else if(i==R.color.blue){
            return R.id.warnabiru;
            //jika memilih warna biru
        }else if(i==R.color.red){
            return R.id.warnamerah;
            //jika memilih warna merah
        }else if(i==R.color.white){
            return R.id.warnaputih;
            //jika memilih warna putih
        } else{
            return R.id.warnaorange;
            //jika memilih warna orange
        }
    }
}
