package com.example.nils.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class FillInWordsActivity extends AppCompatActivity {

    InputStream ins;
    Story story;
    String placeholder;
    int placeholderCount;
    int remainingPlaceholderCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_words);

        // first check whether there is a saved instance state, if so restore it
        if (savedInstanceState != null) {
            // continue with asking words
            TextView failTV = (TextView) findViewById(R.id.count);
            failTV.setText("fail");
        } else {

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                // else check whether the bundle is empty, if this is not the case,
                // a story was initialized before
                //String savedWord = extras.getString("word", "perpendicularity");
                String savedWord = extras.getString("story", "perpendicularity");

                TextView messageTV = (TextView) findViewById(R.id.count);
                String text = "The word is: " + savedWord;
                messageTV.setText(text);
            } else {
                // if the bundle was empty, we have to initialize the story
                // get InputStream, Story and parameters
                ins = getResources().openRawResource(
                        getResources().getIdentifier("madlib0_simple", "raw", getPackageName()));

                story = new Story(ins);
                placeholder = story.getNextPlaceholder();
                placeholderCount = story.getPlaceholderCount();
                remainingPlaceholderCount = story.getPlaceholderRemainingCount();
                TextView placeholderTV = (TextView) findViewById(R.id.editText);
                placeholderTV.setText(placeholder);
            }

        }
    }

    @Override
    // this method saves the state of the program
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("test", true);
        //outState.putSerializable("story", story);
        super.onSaveInstanceState(outState);
    }

    // when the button is pressed, check if something is filled in
    // if there is, save the word and go on, else ask again using a Toast
    public void saveWord(View view) {
        EditText stringET = (EditText) findViewById(R.id.editText);
        String word = stringET.getText().toString();

        if ( !(word.length() == 0) && remainingPlaceholderCount > 0) {
            Intent goToWords = new Intent(this, FillInWordsActivity.class);
            goToWords.putExtra("word", word);
            goToWords.putExtra("story", story);
            startActivity(goToWords);
        } else if ( !(word.length() == 0) ) {
            Intent goToStory = new Intent(this, ShowStoryActivity.class);
            goToStory.putExtra("word", word);
            goToStory.putExtra("story", story);
            startActivity(goToStory);
        } else {
            Toast toast = Toast.makeText(this, "Please enter a word", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public String chooseRandomStory() {
        String filename;
        int random = (int) (Math.random() * 5); // take random var between 0 and 5
        if(random == 0) {
            filename = "madlib0_simple";

        } else if(random == 1) {
            filename = "madlib1_tarzan";

        } else if(random == 2) {
            filename = "madlib2_university";

        } else if(random == 3) {
            filename = "madlib3_clothes";

        } else {
            filename = "madlib4_dance";
        }
        return filename;
    }
}