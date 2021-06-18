package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LogOut extends AppCompatActivity {
    EditText ed;
    Button send , logout;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);

        firebaseFirestore = FirebaseFirestore.getInstance();

        ed = findViewById(R.id.ed_feedback);
        send = findViewById(R.id.btnSend);
        logout = findViewById(R.id.btnLogout);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = ed.getText().toString();

                Map<String, Object> user = new HashMap<>();
                user.put("otherInformaion", s);

                firebaseFirestore.collection("other")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(LogOut.this, "ddd", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });

                ed.setText("");

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent i = new Intent(getApplicationContext(),SignIn.class);
                startActivity(i);
            }
        });
    }
}