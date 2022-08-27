package com.taha.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton radio2Digit;
    RadioButton radio3Digit;
    RadioButton radio4Digit;
    Bundle bundle;
    Button startBtn;

    public static String DIGIT_NNUMBER = "digitNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radio2Digit = findViewById(R.id.main_activity_2_digit_radiobtn);
        radio3Digit = findViewById(R.id.main_activity_3_digit_radiobtn);
        radio4Digit = findViewById(R.id.main_activity_4_digit_radiobtn);
        startBtn = findViewById(R.id.main_activity_start_button);

        startBtn.setOnClickListener(view -> {
            Intent openQuizActivity = new Intent(MainActivity.this, QuizActivity.class);
            bundle = new Bundle();

            if (radio2Digit.isChecked()) {
                openQuizActivity.putExtra(DIGIT_NNUMBER, 2);
                startActivity(openQuizActivity);
            } else if (radio3Digit.isChecked()) {
                openQuizActivity.putExtra(DIGIT_NNUMBER, 3);
                startActivity(openQuizActivity, bundle);
            } else if (radio4Digit.isChecked()) {
                openQuizActivity.putExtra(DIGIT_NNUMBER, 4);
                startActivity(openQuizActivity, bundle);
            } else {
                Toast.makeText(MainActivity.this,
                        R.string.main_activity_no_numbers_selected_message,Toast.LENGTH_SHORT)
                        .show();
            }
            finish();
        });
    }
}