package com.example.nils.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.InputStream;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("raw/" + ACCESS_RANDOM);
            Story story = new Story(inputStream);
            String placeholder = story.getNextPlaceholder();

            TextView messageTV = (TextView) findViewById(R.id.explanation);
            String text = "The text is: " + placeholder;
            messageTV.setText(text);
        } catch (IOException e) {
            TextView messageTV = (TextView) findViewById(R.id.explanation);
            String text = "Fail";
            messageTV.setText(text);
        }
        */
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
        finish();
    }
}