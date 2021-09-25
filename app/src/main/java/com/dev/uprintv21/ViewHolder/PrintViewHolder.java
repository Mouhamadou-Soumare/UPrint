package com.dev.uprintv21.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.uprintv21.Interface.ItemClickListener;
import com.dev.uprintv21.R;

public class PrintViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView print_name;
    public ImageView print_image;

    private ItemClickListener itemClickListener;



    public PrintViewHolder(@NonNull View itemView) {
        super(itemView);

        print_name=(TextView)itemView.findViewById(R.id.print_name);
        print_image=(ImageView)itemView.findViewById(R.id.print_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
