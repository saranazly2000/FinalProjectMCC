package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class generalInfoActivity extends AppCompatActivity {
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_info);
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Jerusalem/المسجد الاقصى.jpeg");
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Jerusalem/باب العامود.jpeg");
        getImage1(mStorageRef,"المسجد الاقصى","jpeg");
        getImage2(mStorageRef,"باب العامود","jpeg");
    }

    public void getImage1(StorageReference s,String prefix,String suffix){
        try {
            final File locationFile = File.createTempFile(prefix,suffix);
            s.getFile(locationFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(generalInfoActivity.this,"success",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(locationFile.getAbsolutePath());
                            ((ImageView)findViewById(R.id.image1)).setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(generalInfoActivity.this,"failure",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getImage2(StorageReference s,String prefix,String suffix){
        try {
            final File locationFile = File.createTempFile(prefix,suffix);
            s.getFile(locationFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(generalInfoActivity.this,"success",Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(locationFile.getAbsolutePath());
                            ((ImageView)findViewById(R.id.image2)).setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(generalInfoActivity.this,"failure",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}