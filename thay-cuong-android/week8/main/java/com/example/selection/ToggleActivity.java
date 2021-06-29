package com.example.selection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.selection.data_models.Questions;
import com.example.selection.data_models.TrueFalseQuestion;

public class ToggleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toggle_layout);
        //
        Button btnNext=findViewById(R.id.btnNext);
        Button btnPre=findViewById(R.id.btnPre);
        Button btnFi=findViewById(R.id.btnFi);
        TextView lblQuestion=(TextView) findViewById(R.id.lblQuestion);
        TextView txt1=findViewById(R.id.txt1);
        TextView txt2=findViewById(R.id.txt2);
        TextView txt3=findViewById(R.id.txt3);
        ToggleButton tg1=findViewById(R.id.tg1);
        ToggleButton tg2=findViewById(R.id.tg2);
        ToggleButton tg3=findViewById(R.id.tg3);
        //Set data from Questions to layout
        TrueFalseQuestion question=(TrueFalseQuestion) Questions.questions.get(4);
        lblQuestion.setText(question.getQuestionDescription());
        txt1.setText(question.getQuestionChoices().get(0));
        txt2.setText(question.getQuestionChoices().get(1));
        txt3.setText(question.getQuestionChoices().get(2));
        //
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ToggleActivity.this,SwitchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(tg1,tg2,tg3);
                startActivity(intent);
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ToggleActivity.this,SpinnerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(tg1,tg2,tg3);
                startActivity(intent);
            }
        });
        btnFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ToggleActivity.this,ResultActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(tg1,tg2,tg3);
                startActivity(intent);
            }
        });
    }
    private void processUserActivities(ToggleButton tg1,ToggleButton tg2,ToggleButton tg3){
        Questions.questions.get(3).getQuestionAnswers().clear();
        if(tg1.isChecked()){
            Questions.questions.get(3).setQuestionAnswers(1);
        }else {
            Questions.questions.get(3).setQuestionAnswers(0);
        }
        if(tg2.isChecked()){
            Questions.questions.get(3).setQuestionAnswers(1);
        }else {
            Questions.questions.get(3).setQuestionAnswers(0);
        }
        if(tg3.isChecked()){
            Questions.questions.get(3).setQuestionAnswers(1);
        }else {
            Questions.questions.get(3).setQuestionAnswers(0);
        }
        Log.e("test tg",Questions.questions.get(3).getQuestionAnswers().toString());
    }
}