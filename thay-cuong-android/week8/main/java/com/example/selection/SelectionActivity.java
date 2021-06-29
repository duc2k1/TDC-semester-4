package com.example.selection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.selection.data_models.MatchingQuestion;
import com.example.selection.data_models.MultiQuestionMultiChoices;
import com.example.selection.data_models.MultiQuestionOneChoice;
import com.example.selection.data_models.Questions;
import com.example.selection.data_models.TrueFalseQuestion;
import com.example.selection.fragments.AbstractFragment;
import com.example.selection.fragments.MatchingQuestionFragment;
import com.example.selection.fragments.MultiQuestionMultiChoiceFragment;
import com.example.selection.fragments.MultiQuestionOneChoiceFragment;
import com.example.selection.fragments.TrueFalseQuestionSwitchFragment;
import com.example.selection.fragments.TrueFalseQuestionToggleFragment;

public class SelectionActivity extends AppCompatActivity {
    private int questionID = 0;
    private AbstractFragment fragment;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_layout);
        Log.d("test","onCreate of 1");
        //set label

        //setTitle(getResources().getString(R.string.scrQ1));
        //Init
        if(savedInstanceState == null)
        Questions.init();
        //
        Button btnNext=(Button) findViewById(R.id.btnNext);
        Button btnPre=(Button) findViewById(R.id.btnPre);
        Button btnFi=(Button) findViewById(R.id.btnFi);

//        TextView lblQuestion=(TextView) findViewById(R.id.lblQuestion);
//        final CheckBox chkA=(CheckBox) findViewById(R.id.chkA);
//        final CheckBox chkB=(CheckBox) findViewById(R.id.chkB);
//        final CheckBox chkC=(CheckBox) findViewById(R.id.chkC);
//        final CheckBox chkD=(CheckBox) findViewById(R.id.chkD);
//        MultiQuestionMultiChoices question=(MultiQuestionMultiChoices) Questions.questions.get(0);
//        //Set data from Questions to layout
//        lblQuestion.setText(question.getQuestionDescription());
//        chkA.setText(question.getQuestionChoices().get(0));
//        chkB.setText(question.getQuestionChoices().get(1));
//        chkC.setText(question.getQuestionChoices().get(2));
//        chkD.setText(question.getQuestionChoices().get(3));
        //
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.setQuestionAnswer(questionID);
                if(questionID==Questions.questions.size()-1){
                    questionID=0;
                }else{
                    questionID++;
                }
                updateUI();
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.setQuestionAnswer(questionID);
                if(questionID==0){
                    questionID=Questions.questions.size()-1;
                }else{
                    questionID--;
                }
                updateUI();
            }
        });
        btnFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SelectionActivity.this, ResultActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                fragment.setQuestionAnswer(questionID);
                startActivity(intent);
            }
        });
        //update the UI at the first time
        updateUI();
    }
    //Update UI for each fragment
    private void updateUI(){
        //update the title of the screen
        setTitle("Cau hoi so: "+ (questionID+1));
        if(Questions.questions.get(questionID) instanceof MultiQuestionMultiChoices){
            fragment=new MultiQuestionMultiChoiceFragment();
        }else if(Questions.questions.get(questionID) instanceof MultiQuestionOneChoice){
            fragment=new MultiQuestionOneChoiceFragment();
        }else if(Questions.questions.get(questionID) instanceof MatchingQuestion){
            fragment=new MatchingQuestionFragment();
        }
        else if(Questions.questions.get(questionID) instanceof TrueFalseQuestion){
            if(questionID==3){
                fragment=new TrueFalseQuestionToggleFragment();
            }
            else{
                fragment=new TrueFalseQuestionSwitchFragment();
            }
        }
        fragment.setQuestion(Questions.questions.get(questionID));
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment);
        fragmentTransaction.commit();
    }
    private void processUserActivities(CheckBox chkA,CheckBox chkB,CheckBox chkC,CheckBox chkD){
        Questions.questions.get(0).getQuestionAnswers().clear();
        if(chkA.isChecked()){
            Questions.questions.get(0).setQuestionAnswers(0);
        }
        if(chkB.isChecked()){
            Questions.questions.get(0).setQuestionAnswers(1);
        }
        if(chkC.isChecked()){
            Questions.questions.get(0).setQuestionAnswers(2);
        }
        if(chkD.isChecked()){
            Questions.questions.get(0).setQuestionAnswers(3);
        }
        Log.e("test sl",Questions.questions.get(0).getQuestionAnswers().toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test","onStart of 1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test","onResume of 1");
    }

    @Override
    protected void onPause() {
        Log.d("test","onPause of 1");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("test","onStop of 1");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("test","onDestroy of 1");
        super.onDestroy();
    }
}