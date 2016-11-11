package com.example.nils.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    // this method saves the state of the program
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("test", true);
        super.onSaveInstanceState(outState);
    }

    // when the button is pressed, go to the next screen to fill in the words
    public void forward(View view) {
        Intent goToNumberActivity = new Intent(this, FillInWordsActivity.class);
        startActivity(goToNumberActivity);
    }
}