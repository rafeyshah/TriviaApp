package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.triviaapp.data.Repository;
import com.example.triviaapp.databinding.ActivityMainBinding;
import com.example.triviaapp.model.Question;
import com.google.android.material.snackbar.Snackbar;

import java.text.MessageFormat;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    List<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        questionList = new Repository().getQuestions(questionArrayList -> {
            binding.questionTextview.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
            binding.textViewOutOf.setText(MessageFormat.format("{0}{1}/{2}", getString(R.string.question_out_of_text), currentQuestionIndex+1, questionArrayList.size()));
        });

        binding.buttonNext.setOnClickListener(v -> {
            updateQuestions();
        });

        binding.buttonTrue.setOnClickListener(v -> {
            checkAnswer(true);
//            updateQuestions();
        });

        binding.buttonFalse.setOnClickListener(v -> {
            checkAnswer(false);
//            updateQuestions();
        });

    }

    private void checkAnswer(boolean userChoseCorrect) {
        boolean answer = questionList.get(currentQuestionIndex).isAnswerTrue();
        int snackMessageId;
        if(userChoseCorrect == answer){
            snackMessageId = R.string.correct_answer;
        }else{
            snackMessageId = R.string.incorrect_answer;
        }
//        Snackbar.make(binding.cardView, snackMessageId, Snackbar.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),snackMessageId, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestions(){
        currentQuestionIndex +=1 % questionList.size();
        String question = questionList.get(currentQuestionIndex).getAnswer();
        binding.questionTextview.setText(question);
        binding.textViewOutOf.setText(MessageFormat.format("Question: {0}/{1}", currentQuestionIndex+1, questionList.size()));


    }
}