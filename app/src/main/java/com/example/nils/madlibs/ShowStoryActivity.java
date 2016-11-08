package com.example.nils.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_story);

        Bundle extras = getIntent().getExtras();
        String savedWord = extras.getString("word", "perpendicularity");

        TextView messageTV = (TextView) findViewById(R.id.resultTextView);
        String text = "The word is: " + savedWord;
        messageTV.setText(text);
    }

    public void newWord(View view) {
        Intent goToWord = new Intent(this, FillInWordsActivity.class);
        startActivity(goToWord);
    }
}




