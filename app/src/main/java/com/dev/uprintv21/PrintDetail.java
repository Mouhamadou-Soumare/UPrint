package com.dev.uprintv21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.dev.uprintv21.Model.Printer;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PrintDetail extends AppCompatActivity {

    TextView print_name,print_price,print_price2,print_price3,print_description;
    ImageView print_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String printId="";

    FirebaseDatabase database;
    DatabaseReference print;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_detail);

        database=FirebaseDatabase.getInstance();
        print=database.getReference("Print");

        btnCart=(FloatingActionButton)findViewById(R.id.btnCart);

        print_description=(TextView)findViewById(R.id.print_description);
        print_name=(TextView)findViewById(R.id.print_name);
        print_price=(TextView)findViewById(R.id.print_price);
        print_price2=(TextView)findViewById(R.id.print_price1);
        print_price3=(TextView)findViewById(R.id.print_price3);

        print_image=(ImageView) findViewById(R.id.img_print);

        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleColor(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setExpandedTitleColor(R.style.CollapsedAppbar);

        if(getIntent() != null)
            printId=getIntent().getStringExtra("PrintId");
        if(!printId.isEmpty())
        {
            getDetailPrint(printId);
        }
    }

    private void getDetailPrint(String printId) {
        print.child(printId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Printer printer= snapshot.getValue(Printer.class);

                Picasso.with(getBaseContext()).load(printer.getImage()).into(print_image);

                collapsingToolbarLayout.setTitle(printer.getName());
                if(printer.getName()=="QIDITECH X-MAX") {
                    TextView myTextView = (TextView) findViewById(R.id.typeimpr);
                    myTextView.setText("Impression 3d");
                    TextView view = (TextView) findViewById(R.id.impr);
                    view.setVisibility(view.INVISIBLE);
                    TextView view1 = (TextView) findViewById(R.id.impr1);
                    view1.setVisibility(View.INVISIBLE);


                    print_price.setText(printer.getPriceNB());
                    print_price2.setText(printer.getPriceC());
                    print_price3.setText(printer.getPriceNM());


                    print_description.setText(printer.getDescription());
                }

                else {


                    print_price.setText(printer.getPriceNB());
                    print_price2.setText(printer.getPriceC());
                    print_price3.setText(printer.getPriceNM());


                    print_description.setText(printer.getDescription());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}