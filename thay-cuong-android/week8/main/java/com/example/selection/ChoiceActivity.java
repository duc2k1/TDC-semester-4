package com.example.selection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.selection.data_models.MultiQuestionMultiChoices;
import com.example.selection.data_models.MultiQuestionOneChoice;
import com.example.selection.data_models.Questions;
import com.example.selection.view_models.MyRadioGroup;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_layout);
        Log.d("test","onCreate of 2");
        //
        Button btnNext=findViewById(R.id.btnNext);
        Button btnPre=findViewById(R.id.btnPre);
        Button btnFi=findViewById(R.id.btnFi);
//        RadioButton radA=findViewById(R.id.rdA);
//        RadioButton radB=findViewById(R.id.rdB);
//        RadioButton radC=findViewById(R.id.rdC);
//        RadioButton radD=findViewById(R.id.rdD);
//        MyRadioGroup myRadioGroup=new MyRadioGroup(radA,radB,radC,radD);
//        TextView lblQuestion=(TextView) findViewById(R.id.lblQuestion);
//        MultiQuestionOneChoice question=(MultiQuestionOneChoice) Questions.questions.get(1);
//        //Set data from Questions to layout
//        lblQuestion.setText(question.getQuestionDescription());
//        radA.setText(question.getQuestionChoices().get(0));
//        radB.setText(question.getQuestionChoices().get(1));
//        radC.setText(question.getQuestionChoices().get(2));
//        radD.setText(question.getQuestionChoices().get(3));
//        //
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChoiceActivity.this,SpinnerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                //processUserActivities(radA,radB,radC,radD);
                startActivity(intent);
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChoiceActivity.this, SelectionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                //processUserActivities(radA,radB,radC,radD);
                startActivity(intent);
            }
        });
        btnFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChoiceActivity.this, ResultActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                //processUserActivities(radA,radB,radC,radD);
                startActivity(intent);
            }
        });
    }
    private void processUserActivities(RadioButton radA, RadioButton radB, RadioButton radC, RadioButton radD){
        Questions.questions.get(1).getQuestionAnswers().clear();
        if(radA.isChecked()){
            Questions.questions.get(1).setQuestionAnswers(0);
        }
        if(radB.isChecked()){
            Questions.questions.get(1).setQuestionAnswers(1);
        }
        if(radC.isChecked()){
            Questions.questions.get(1).setQuestionAnswers(2);
        }
        if(radD.isChecked()){
            Questions.questions.get(1).setQuestionAnswers(3);
        }
        Log.e("test choice",Questions.questions.get(1).getQuestionAnswers().toString());
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test","onStart of 2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test","onResume of 2");
    }

    @Override
    protected void onPause() {
        Log.d("test","onPause of 2");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("test","onStop of 2");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("test","onDestroy of 2");
        super.onDestroy();
    }
}
