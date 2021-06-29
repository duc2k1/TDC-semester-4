package com.example.selection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.selection.data_models.MultiQuestionMultiChoices;
import com.example.selection.data_models.Questions;
import com.example.selection.data_models.TrueFalseQuestion;

public class SwitchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.switch_layout);
        //
        Button btnNext=findViewById(R.id.btnNext);
        Button btnPre=findViewById(R.id.btnPre);
        Button btnFi=findViewById(R.id.btnFi);
        TextView lblQuestion=(TextView) findViewById(R.id.lblQuestion);
        TextView txt1=findViewById(R.id.txt1);
        TextView txt2=findViewById(R.id.txt2);
        TextView txt3=findViewById(R.id.txt3);
        Switch sw1=findViewById(R.id.sw1);
        Switch sw2=findViewById(R.id.sw2);
        Switch sw3=findViewById(R.id.sw3);
        TrueFalseQuestion question=(TrueFalseQuestion) Questions.questions.get(3);
        //Set data from Questions to layout
        lblQuestion.setText(question.getQuestionDescription());
        txt1.setText(question.getQuestionChoices().get(0));
        txt2.setText(question.getQuestionChoices().get(1));
        txt3.setText(question.getQuestionChoices().get(2));
        //
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SwitchActivity.this, SelectionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(sw1,sw2,sw3);
                startActivity(intent);
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SwitchActivity.this,ToggleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(sw1,sw2,sw3);
                startActivity(intent);
            }
        });
        btnFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SwitchActivity.this,ResultActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                processUserActivities(sw1,sw2,sw3);
                startActivity(intent);
            }
        });
    }
    private void processUserActivities(Switch sw1,Switch sw2,Switch sw3){
        Questions.questions.get(4).getQuestionAnswers().clear();
        if(sw1.isChecked()){
            Questions.questions.get(4).setQuestionAnswers(1);
        }else {
            Questions.questions.get(4).setQuestionAnswers(0);
        }
        if(sw2.isChecked()) {
            Questions.questions.get(4).setQuestionAnswers(1);
        }else {
            Questions.questions.get(4).setQuestionAnswers(0);
        }
        if(sw3.isChecked()){
            Questions.questions.get(4).setQuestionAnswers(1);
        }else {
            Questions.questions.get(4).setQuestionAnswers(0);
        }
        Log.e("test  sw",Questions.questions.get(4).getQuestionAnswers().toString());
    }
}