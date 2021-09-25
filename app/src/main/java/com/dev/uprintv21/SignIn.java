package com.dev.uprintv21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


import com.dev.uprintv21.Common.Common;
import com.dev.uprintv21.Model.User;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {
    EditText phone,password;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        password=(MaterialEditText)findViewById(R.id.Password);
        phone=(MaterialEditText)findViewById(R.id.Phone);
        btnSignIn=(Button)findViewById(R.id.btSignIn);
        final FirebaseDatabase database = FirebaseDatabase.getInstance() ;
        final DatabaseReference table_user=database.getReference("User");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  ProgressDialog mDialog=new ProgressDialog(SignIn.this);
                mDialog.setMessage("Patientez svp...");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.child(phone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            User user = snapshot.child(phone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(password.getText().toString())) {
                                {
                                    Intent homeIntent=new Intent(SignIn.this,Home.class);
                                    Common.currentUser=user;
                                    startActivity(homeIntent);
                                    finish();
                                }
                            }
                            else {
                                Toast.makeText(SignIn.this, "Identifiant ou Mot de passe incorrect !", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this,"Utilisateur introuvable",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
            });
        }
        });
    }
}