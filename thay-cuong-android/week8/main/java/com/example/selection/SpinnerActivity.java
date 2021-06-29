package com.example.selection;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.selection.data_models.MatchingQuestion;
import com.example.selection.data_models.Questions;
import com.example.selection.data_models.TrueFalseQuestion;

public class SpinnerActivity extends AppCompatActivity {
    private Integer itemIdSpinner1 = 0;
    private Integer itemIdSpinner2 = null;
    private Integer itemIdSpinner3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_layout);
        //
        Button btnNext = findViewById(R.id.btnNext);
        Button btnPre = findViewById(R.id.btnPre);
        Button btnFi = findViewById(R.id.btnFi);
        TextView lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        MatchingQuestion question = (MatchingQuestion) Questions.questions.get(2);
        TextView txt1 = findViewById(R.id.txt1);
        TextView txt2 = findViewById(R.id.txt2);
        TextView txt3 = findViewById(R.id.txt3);
        Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
        Spinner spinner3 = findViewById(R.id.spinner3);
        //Set data from Questions to layout
        lblQuestion.setText(question.getQuestionDescription());
        txt1.setText(question.getQuestionChoiceA().get(0));
        txt2.setText(question.getQuestionChoiceA().get(1));
        txt3.setText(question.getQuestionChoiceA().get(2));
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, question.getQuestionChoiceB());
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        //
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer item = Math.toIntExact(parent.getItemIdAtPosition(position)) ;
                itemIdSpinner1 = (Integer) item;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                itemIdSpinner1 = 0;
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer item = Math.toIntExact(parent.getItemIdAtPosition(position)) ;
                itemIdSpinner2 = (Integer) item;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                itemIdSpinner2 = 0;
            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer item = Math.toIntExact(parent.getItemIdAtPosition(position)) ;
                itemIdSpinner3 = (Integer) item;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                itemIdSpinner3 = 0;
            }
        });
        //
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpinnerActivity.this, ToggleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                //processUserActivities(itemIdSpinner1, itemIdSpinner2, itemIdSpinner3);
                startActivity(intent);
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpinnerActivity.this, ChoiceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                //processUserActivities(itemIdSpinner1, itemIdSpinner2, itemIdSpinner3);
                startActivity(intent);
            }
        });
        btnFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpinnerActivity.this, ResultActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                //processUserActivities(itemIdSpinner1, itemIdSpinner2, itemIdSpinner3);
                startActivity(intent);
            }
        });
    }
    private void processUserActivities(int sp1, int sp2, int sp3) {
        Questions.questions.get(2).getQuestionAnswers().clear();
        Questions.questions.get(2).setQuestionAnswers(sp1);
        Questions.questions.get(2).setQuestionAnswers(sp2);
        Questions.questions.get(2).setQuestionAnswers(sp3);
        Log.e("test sp",Questions.questions.get(2).getQuestionAnswers().toString());
    }
}