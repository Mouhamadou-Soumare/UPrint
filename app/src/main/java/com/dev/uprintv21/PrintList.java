package com.dev.uprintv21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dev.uprintv21.Interface.ItemClickListener;
import com.dev.uprintv21.Model.Printer;
import com.dev.uprintv21.ViewHolder.PrintViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PrintList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference printList;

    String categoryId="";
    FirebaseRecyclerAdapter<Printer, PrintViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_list);

        database=FirebaseDatabase.getInstance();
        printList=database.getReference("Print");

        recyclerView=(RecyclerView)findViewById(R.id.recycler_print);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent()!=null)
            categoryId=getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId != null)        {
            loadListPrint(categoryId);
        }
    }

    private void loadListPrint(String categoryId) {
        adapter=new FirebaseRecyclerAdapter<Printer, PrintViewHolder>(Printer.class,
                R.layout.print_item,
                PrintViewHolder.class,
                printList.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(PrintViewHolder printViewHolder, Printer printer, int i) {
                printViewHolder.print_name.setText(printer.getName());
                Picasso.with(getBaseContext()).load(printer.getImage()).into(printViewHolder.print_image);

                final Printer local = printer;
                printViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent printDetail=new Intent(PrintList.this,PrintDetail.class);
                        printDetail.putExtra("PrintId",adapter.getRef(position).getKey());
                        startActivity(printDetail);
                    }
                });
            }
        };

        recyclerView.setAdapter(adapter);
    }
}