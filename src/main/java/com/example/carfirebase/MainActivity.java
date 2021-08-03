package com.example.carfirebase;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    FirebaseDatabase database;
    DatabaseReference ref;
    StorageReference sref;
    ArrayList<CarInfo> arrayList;
    String uri = "", name = "", model = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(); // initialize variables and preparation recycler and database references //
        getDataFromFirebase(); // to fetch data from firebase //
    }
    private void init()
    {
        recyclerView = findViewById(R.id.recycler);
        database = FirebaseDatabase.getInstance("https://carfirebase-17265-default-rtdb.firebaseio.com/");
        ref = database.getReference();
        sref = FirebaseStorage.getInstance().getReference().child("images");
        arrayList = new ArrayList<>();
        adapter = new Adapter();
    }
    private void getDataFromFirebase() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot post : snapshot.getChildren()) {
                    name = post.child("Name").getValue(String.class);
                    model = post.child("Model").getValue(String.class);
                    uri = post.child("image").getValue(String.class);
                    arrayList.add(new CarInfo(uri, model, name));
                    getRes(arrayList, adapter); // adapter array list and put in recyclerview
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getRes(ArrayList<CarInfo> arrayList , Adapter adapter) {
        adapter.setListInfo(arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,
                false));
        recyclerView.setAdapter(adapter);
    }
}