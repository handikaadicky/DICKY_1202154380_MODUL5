package com.example.handikaadicky.DICKY_1202154380_MODUL5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.holder> { //membuat class untuk menyimpan adapter
    //inisiasi awal
    private Context con;
    private List<itemtodo> itemnya;
    int id;

    //constructor
    public adapter(Context con, List<itemtodo> itemnya, int id) {
        this.con = con;
        this.itemnya = itemnya;
        this.id = id;
    }
    @Override
    //membuat method untuk menetukan viewholder untuk recyclerview
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(con).inflate(R.layout.activity_review, parent, false);
        //mengatur layout inflater
        holder hold = new holder(v);
        return hold;
    }
    @Override
    //Method untuk mengikat nilai dengan objek pada viewholder
    public void onBindViewHolder(holder holder, int position) {
        itemtodo satuan = itemnya.get(position);
        //menentukan posisi dari list item dalam variabel satuan
        holder.name.setText(satuan.getName());
        //menyimpan nama kegiatan
        holder.description.setText(satuan.getDescription());
        //menyimpan deskripsi kegiatan
        holder.priority.setText(satuan.getPriority());
        //menyimpan angka keprioritasan kegiatan
        holder.cd.setCardBackgroundColor(con.getResources().getColor(this.id));
        //mengatur warna cardview yang digunakan berdasarkan warna yang pilih nantinya
    }

    public itemtodo getItem(int position){
        //Method untuk mendapatkan itemtodo dari adapter
        return itemnya.get(position);
        //mengembalikan hasil berupa letak/posisi list item
    }
    @Override
    public int getItemCount() {
        //method yang mendaptkan jumlah item todo
        return itemnya.size();
        //ukuran itemnya
    }

    //Method untuk menghapus itemtodo
    public void removeitem(int i){
        //menghapus itemnya
        itemnya.remove(i);
        //perintah yang memanfaatkan fugsi snackbar sehingga ketika item di swipe, item akan terhapus
        notifyItemRemoved(i);
        //menghapus data dari list yang tersimpan dalam database
        notifyItemRangeChanged(i, itemnya.size());
    }

    //Subclass sebagai viewholder
    class holder extends RecyclerView.ViewHolder{
        //mendeklarasikan semua variable yang digunakan
        public TextView name, description, priority;
        public CardView cd;
        public holder(View itemView) {
            super(itemView);

            //Menginisialisasikan semua variabel yang akan dimuat kedalam objek yang akan ditampilkan
            name = itemView.findViewById(R.id.todoname);
            description = itemView.findViewById(R.id.tododescription);
            priority = itemView.findViewById(R.id.todopriority);
            cd = itemView.findViewById(R.id.rootcardnya);
        }
    }
}
