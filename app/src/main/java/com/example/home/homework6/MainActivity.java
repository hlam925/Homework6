package com.example.home.homework6;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AddPinDialogFragment.AddPinListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main_framelayout, new LoginFragment());
        transaction.commit();

    }


    @Override
    public void onAddPinClicked(String title, String snippet) {
        FragmentManager manager = getSupportFragmentManager();
        MapFragment fr = (MapFragment) manager.findFragmentByTag(getString(R.string.map_fragment_tag));
        fr.onAddPin(title, snippet);

    }
}
