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
        Story story = (Story) extras.getSerializable("story");
        TextView messageTV = (TextView) findViewById(R.id.resultTextView);
        if (story != null) {
            String storyStr = story.toString();
            messageTV.setText(storyStr);
        } else {
            String errorStr = "Something went wrong. Please try again.";
            messageTV.setText(errorStr);
        }
    }

    // when the button is pressed, start over FillInWordsActivity for a new story
    public void newWord(View view) {
        Intent goToWord = new Intent(this, FillInWordsActivity.class);
        startActivity(goToWord);
        finish();
    }
}




