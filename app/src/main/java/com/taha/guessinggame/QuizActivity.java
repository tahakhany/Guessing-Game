package com.taha.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "errorchecking";
    ArrayList<TextView> guessedNumbersTextView;
    ArrayList<Integer> guessedNumbers;
    TextView hintTextView;
    TextView rightsRemainingTextView;
    EditText guessEditText;
    Button guessButton;
    int rightsRemaining;
    int answer;
    int minGuessRange;
    int maxGuessRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        rightsRemaining = 10;

        guessedNumbersTextView = new ArrayList<>();
        guessedNumbers = new ArrayList<>();

        guessedNumbersTextView.add(findViewById(R.id.quiz_activity_guess_1));
        guessedNumbersTextView.add(findViewById(R.id.quiz_activity_guess_2));
        guessedNumbersTextView.add(findViewById(R.id.quiz_activity_guess_3));
        guessedNumbersTextView.add(findViewById(R.id.quiz_activity_guess_4));
        guessedNumbersTextView.add(findViewById(R.id.quiz_activity_guess_5));
        guessedNumbersTextView.add(findViewById(R.id.quiz_activity_guess_6));
        guessedNumbersTextView.add(findViewById(R.id.quiz_activity_guess_7));
        guessedNumbersTextView.add(findViewById(R.id.quiz_activity_guess_8));
        guessedNumbersTextView.add(findViewById(R.id.quiz_activity_guess_9));
        guessedNumbersTextView.add(findViewById(R.id.quiz_activity_guess_10));

        hintTextView = findViewById(R.id.quiz_activity_hint_text);
        rightsRemainingTextView = findViewById(R.id.quiz_activity_remaining_rights_text);
        guessButton = findViewById(R.id.quiz_activity_guess_button);
        guessEditText = findViewById(R.id.quiz_activity_guess_edit_text);

        int digitNumber = getIntent().getIntExtra(MainActivity.DIGIT_NNUMBER, 0);
        minGuessRange = 10 ^ (digitNumber - 1);
        maxGuessRange = 10 ^ (digitNumber) - 1;

        answer = (int) (Math.random() * (maxGuessRange - minGuessRange) + minGuessRange);

        guessButton.setOnClickListener(view -> {
            makeNewGuess(Integer.parseInt(guessEditText.getText().toString()));
        });

        /*guessEditText.setOnKeyListener((view, i, keyEvent) -> {
            if(i == KeyEvent.KEYCODE_ENTER &&
                    keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                makeNewGuess(Integer.parseInt(guessEditText.getText().toString()));
            }
            return true;
        });*/

    }

    private void makeNewGuess(int guessedNumber) {
        if (guessedNumber == answer) {
            hintTextView.setText(getString(R.string.quiz_activity_correct_answer));
            guessedNumbers.add(guessedNumber);
            Log.d(TAG, "makeNewGuess: " + guessedNumbers.size());
            guessedNumbersTextView.get(guessedNumbers.size() - 1)
                    .setText(String.valueOf(guessedNumbers.get(guessedNumbers.size() - 1)));
            guessedNumbersTextView.get(guessedNumbers.size() - 1)
                    .setTextColor(getResources().getColor(R.color.blue_correct));
            //todo show endgame activity here

        } else if (guessedNumber > answer) {
            hintTextView.setText(getString(R.string.quiz_activity_larger_answer));
            guessedNumbers.add(guessedNumber);
            Log.d(TAG, "makeNewGuess: " + guessedNumbers.size());
            guessedNumbersTextView.get(guessedNumbers.size() - 1)
                    .setText(String.valueOf(guessedNumbers.get(guessedNumbers.size() - 1)));
            guessedNumbersTextView.get(guessedNumbers.size() - 1)
                    .setTextColor(getResources().getColor(R.color.green_larger));
            if (guessedNumbers.size() == 10){
                //todo show endgame activity here
            }

        } else {
            hintTextView.setText(getString(R.string.quiz_activity_smaller_answer));
            guessedNumbers.add(guessedNumber);
            Log.d(TAG, "makeNewGuess: " + guessedNumbers.size());
            guessedNumbersTextView.get(guessedNumbers.size() - 1)
                    .setText(String.valueOf(guessedNumbers.get(guessedNumbers.size() - 1)));
            guessedNumbersTextView.get(guessedNumbers.size() - 1)
                    .setTextColor(getResources().getColor(R.color.red_smaller));
            if (guessedNumbers.size() == 10){
                //todo show endgame activity here
            }
        }
    }

}