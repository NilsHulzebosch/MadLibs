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

    public InputStream ins;
    public Story story;
    public String placeholder;
    public int remainingPlaceholderCount;

    public static String suggestion = "Please enter a";
    public static String remainingWordCount = " words remaining...";
    public static String OneWordRemaning = " word remaining...";

    public TextView wordTypeTV;
    public TextView wordCountTV;
    public EditText stringET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_words);

        if (savedInstanceState != null) {
            // first check whether there is a saved instance state, if so restore it
            story = (Story) savedInstanceState.getSerializable("story");
            initializeTextObjects();
            showNextWordAndCount();
        } else {
            // else initialize the story by choosing a random text file, passing it
            // as InputStream to create a new Story object
            ins = getResources().openRawResource(
                    getResources().getIdentifier(chooseRandomStory(), "raw", getPackageName()));
            story = new Story(ins);
            initializeTextObjects();
            showNextWordAndCount();
        }
    }

    @Override
    // this method saves the state of the program
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("test", true);
        outState.putSerializable("story", story); // pass the object so it can be re-used
        super.onSaveInstanceState(outState);
    }

    // checks if a char is a vowel
    public static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    // initializes the text objects (called in onCreate)
    public void initializeTextObjects() {
        wordTypeTV = (TextView) findViewById(R.id.wordtype);
        wordCountTV = (TextView) findViewById(R.id.wordcount);
        stringET = (EditText) findViewById(R.id.editText);
    }

    /* The visual feedback of the program:
     * Shows hint in EditText, type of word in TextView and remaining word count in TextView.
     * Also clears user input after each round.
     */
    public void showNextWordAndCount() {
        stringET.getText().clear(); // clear the user input

        placeholder = story.getNextPlaceholder();
        String n = " ";
        if ( isVowel(placeholder.charAt(0)) ) {
            n = "n ";
        }
        String suggestedText = suggestion + n + placeholder;
        wordTypeTV.setText(suggestedText);

        stringET.setHint(placeholder); // set hint for the user

        remainingPlaceholderCount = story.getPlaceholderRemainingCount();
        String countStr = Integer.toString(remainingPlaceholderCount);
        String suggestedCount;
        if (remainingPlaceholderCount > 1) {
            suggestedCount = countStr + remainingWordCount;
        } else {
            suggestedCount = countStr + OneWordRemaning;
        }
        wordCountTV.setText(suggestedCount);
    }

    /* The action of the program: when the button is pressed, check if something is filled in.
     * If there is, save the word and show next placeholder, else ask again using a Toast.
     * Continue until all words are asked. Then go to ShowStoryActivity using an Intent.
     */
    public void saveWord(View view) {
        String filledInWord = stringET.getText().toString(); // turn user input into a string

        if (!(filledInWord.length() == 0) && remainingPlaceholderCount > 1 ) {
            story.fillInPlaceholder(filledInWord); // fill in the word in the story object
            showNextWordAndCount();
        } else if ( !(filledInWord.length() == 0) ) {
            story.fillInPlaceholder(filledInWord); // fill in the word in the story object
            Intent goToStory = new Intent(this, ShowStoryActivity.class);
            goToStory.putExtra("story", story); // pas story object to next activity
            startActivity(goToStory);
            finish();
        } else {
            Toast toast = Toast.makeText(this, "Please enter a word", Toast.LENGTH_SHORT);
            toast.show();
        }

        motivateUser();
    }

    // motivate the user using a few Toasts
    public void motivateUser() {
        Toast toast;
        if (remainingPlaceholderCount == 10) {
            toast = Toast.makeText(this, "You're doing great!", Toast.LENGTH_SHORT);
            toast.show();
        } else if (remainingPlaceholderCount == 6) {
            toast = Toast.makeText(this, "A few more to go!", Toast.LENGTH_SHORT);
            toast.show();
        } else if (remainingPlaceholderCount == 3) {
            toast = Toast.makeText(this, "Only three words left!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    // this method randomly chooses one of the five possible stories
    public String chooseRandomStory() {
        String filename;
        int random = (int) (Math.random() * 5);
        if(random == 4) {
            filename = "madlib4_dance";
        } else if(random == 3) {
            filename = "madlib3_clothes";
        } else if(random == 2) {
            filename = "madlib2_university";
        } else {
            filename = "madlib1_tarzan";
        }
        return filename;
    }
}