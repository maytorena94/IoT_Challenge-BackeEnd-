package com.firebase.androidchat;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Random;

public class MainActivity extends ListActivity {

    // TODO: change this to your own Firebase URL
    private static final String FIREBASE_URL = "https://smartpark1.firebaseio.com";

    private String mUsername;
    private Firebase ref;
    private ValueEventListener mConnectedListener;
    private ChatListAdapter mChatListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Setup our Firebase mFirebaseRef
        ref = new Firebase(FIREBASE_URL).child("Estacionamiento1/ZonaA/id");
        Firebase id = new Firebase(FIREBASE_URL).child("Estacionamiento1/ZonaA/id");
        Firebase carLimit = new Firebase(FIREBASE_URL).child("Estacionamiento1/ZonaA/carLimit");
        Firebase currentCars = new Firebase(FIREBASE_URL).child("Estacionamiento1/ZonaA/currentCars");

        // Get a reference to our posts
        // Attach an listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("Voy a imprimir los valores de la Zona A");
                System.out.println("ID: " + snapshot.getValue());
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        carLimit.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("Voy a imprimir los valores de la Zona A");
                System.out.println("carLimit: " + snapshot.getValue());
            }
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        currentCars.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("Voy a imprimir los valores de la Zona A");
                System.out.println("currentCars: " + snapshot.getValue());
            }
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
