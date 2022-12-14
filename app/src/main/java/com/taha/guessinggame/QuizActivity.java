package com.taha.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "errorchecking";
    ArrayList<TextView> guessedNumbersTextView;
    ArrayList<Integer> guessedNumbers;
    TextView hintTextView;
    TextView rightsRemainingTextView;
    EditText guessEditText;
    Button guessButton;
    Random random;
    Intent openResultsActivity;
    int rightsRemaining;
    int answer;
    int minGuessRange;
    int maxGuessRange;
    public static final String GAME_STATUS = "game status";
    public static final String REMAINING_RIGHTS = "remaining rights";
    public static final String GUESS_HISTORY = "guess history";
    public static final String CORRECT_ANSWER = "correct answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        rightsRemaining = 10;

        guessedNumbersTextView = new ArrayList<>();
        guessedNumbers = new ArrayList<>();
        random = new Random();

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

        hintTextView = findViewById(R.id.quiz_activity_result_text);
        rightsRemainingTextView = findViewById(R.id.quiz_activity_remaining_rights_text);
        guessButton = findViewById(R.id.quiz_activity_guess_button);
        guessEditText = findViewById(R.id.quiz_activity_guess_edit_text);

        int digitNumber = getIntent().getIntExtra(MainActivity.DIGIT_NUMBER, 0);
        minGuessRange = (int) Math.pow(10, digitNumber - 1);
        maxGuessRange = (int) (Math.pow(10, digitNumber) - 1);

        answer = random.nextInt(maxGuessRange - minGuessRange) + minGuessRange;
        openResultsActivity = new Intent(QuizActivity.this, ResultsActivity.class);

        guessButton.setOnClickListener(view -> makeNewGuess(Integer.parseInt(guessEditText.getText().toString())));

        guessEditText.setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_ENTER &&
                    keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                makeNewGuess(Integer.parseInt(guessEditText.getText().toString()));
            }
            return false;
        });

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

            openResults(true);

        } else if (guessedNumber > answer) {
            hintTextView.setText(getString(R.string.quiz_activity_larger_answer));
            guessedNumbers.add(guessedNumber);
            Log.d(TAG, "makeNewGuess: " + guessedNumbers.size());
            guessedNumbersTextView.get(guessedNumbers.size() - 1)
                    .setText(String.valueOf(guessedNumbers.get(guessedNumbers.size() - 1)));
            guessedNumbersTextView.get(guessedNumbers.size() - 1)
                    .setTextColor(getResources().getColor(R.color.green_larger));
            if (guessedNumbers.size() == 10) {
                openResults(false);
            }

        } else {
            hintTextView.setText(getString(R.string.quiz_activity_smaller_answer));
            guessedNumbers.add(guessedNumber);
            Log.d(TAG, "makeNewGuess: " + guessedNumbers.size());
            guessedNumbersTextView.get(guessedNumbers.size() - 1)
                    .setText(String.valueOf(guessedNumbers.get(guessedNumbers.size() - 1)));
            guessedNumbersTextView.get(guessedNumbers.size() - 1)
                    .setTextColor(getResources().getColor(R.color.red_smaller));
            if (guessedNumbers.size() == 10) {
                openResults(false);
            }
        }
        rightsRemaining = 10 - guessedNumbers.size();
        String rightsRemainingTemp = getString(R.string.remaining_rights_string) + rightsRemaining;
        rightsRemainingTextView.setText(rightsRemainingTemp);
    }

    public void openResults(boolean isWin){
        openResultsActivity.putExtra(GAME_STATUS, isWin);
        openResultsActivity.putExtra(REMAINING_RIGHTS,rightsRemaining);
        openResultsActivity.putExtra(GUESS_HISTORY,guessedNumbers);
        openResultsActivity.putExtra(CORRECT_ANSWER,answer);
        startActivity(openResultsActivity);
    }

}