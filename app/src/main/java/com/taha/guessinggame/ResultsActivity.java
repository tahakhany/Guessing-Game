package com.taha.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    boolean isWin;
    ArrayList<Integer> guessedHistory;
    int remainingRights;
    Button restartButton;
    TextView resultTextView;
    TextView remainingRightsTextView;
    int answer;
    ArrayList<TextView> guessedHistoryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resaults);

        guessedHistoryTextView = new ArrayList<>();

        guessedHistoryTextView.add(findViewById(R.id.result_activity_guess_1));
        guessedHistoryTextView.add(findViewById(R.id.result_activity_guess_2));
        guessedHistoryTextView.add(findViewById(R.id.result_activity_guess_3));
        guessedHistoryTextView.add(findViewById(R.id.result_activity_guess_4));
        guessedHistoryTextView.add(findViewById(R.id.result_activity_guess_5));
        guessedHistoryTextView.add(findViewById(R.id.result_activity_guess_6));
        guessedHistoryTextView.add(findViewById(R.id.result_activity_guess_7));
        guessedHistoryTextView.add(findViewById(R.id.result_activity_guess_8));
        guessedHistoryTextView.add(findViewById(R.id.result_activity_guess_9));
        guessedHistoryTextView.add(findViewById(R.id.result_activity_guess_10));

        resultTextView = findViewById(R.id.result_activity_result_text);
        remainingRightsTextView = findViewById(R.id.result_activity_remaining_rights_text);
        restartButton = findViewById(R.id.results_activity_restart_button);

        readData();
        updateUI(isWin);

        restartButton.setOnClickListener(view -> startActivity(new Intent(ResultsActivity.this,MainActivity.class)));

    }

    private void readData() {
        this.isWin = getIntent().getBooleanExtra(QuizActivity.GAME_STATUS, false);
        this.guessedHistory = getIntent().getIntegerArrayListExtra(QuizActivity.GUESS_HISTORY);
        this.remainingRights = getIntent().getIntExtra(QuizActivity.REMAINING_RIGHTS, 0);
        this.answer = getIntent().getIntExtra(QuizActivity.CORRECT_ANSWER,0);
    }

    public void updateUI(boolean isWin) {
        for (int i = 0; i < 10 - this.remainingRights; i++) {
            guessedHistoryTextView.get(i).setText(String.valueOf(guessedHistory.get(i)));
            guessedHistoryTextView.get(i).setTextColor(getResources().getColor(R.color.blue_correct));
        }

        remainingRightsTextView.setText(String.valueOf(remainingRights));
        if(isWin){
            resultTextView.setText(getString(R.string.result_win));
        } else {
            resultTextView.setText(getString(R.string.result_lose) + " " + answer);
        }

    }
}