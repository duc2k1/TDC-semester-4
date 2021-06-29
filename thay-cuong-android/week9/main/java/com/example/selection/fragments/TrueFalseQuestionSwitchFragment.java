package com.example.selection.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.selection.R;
import com.example.selection.data_models.AbstractQuestion;
import com.example.selection.data_models.Questions;
import com.example.selection.data_models.TrueFalseQuestion;

public class TrueFalseQuestionSwitchFragment extends AbstractFragment{
    private TrueFalseQuestion question;
    private Switch sw1,sw2,sw3;
    @Override
    public void setQuestionAnswer(int questionID) {
        Questions.questions.get(questionID).getQuestionAnswers().clear();
        if(sw1.isChecked()){
            Questions.questions.get(questionID).setQuestionAnswers(1);
        }else {
            Questions.questions.get(questionID).setQuestionAnswers(0);
        }
        if(sw2.isChecked()) {
            Questions.questions.get(questionID).setQuestionAnswers(1);
        }else {
            Questions.questions.get(questionID).setQuestionAnswers(0);
        }
        if(sw3.isChecked()){
            Questions.questions.get(questionID).setQuestionAnswers(1);
        }else {
            Questions.questions.get(questionID).setQuestionAnswers(0);
        }
        Log.e("test  sw",Questions.questions.get(questionID).getQuestionAnswers().toString());
    }

    @Override
    public void setQuestion(AbstractQuestion question) {
        this.question=(TrueFalseQuestion) question;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=null;
        view=inflater.inflate(R.layout.truefalsequestion_switch_fragment_layout,container,false);
        TextView lblQuestion=(TextView) view.findViewById(R.id.lblQuestion);
        TextView txt1=view.findViewById(R.id.txt1);
        TextView txt2=view.findViewById(R.id.txt2);
        TextView txt3=view.findViewById(R.id.txt3);
         sw1=view.findViewById(R.id.sw1);
         sw2=view.findViewById(R.id.sw2);
         sw3=view.findViewById(R.id.sw3);
        //TrueFalseQuestion question=(TrueFalseQuestion) Questions.questions.get(3);
        //Set data from Questions to layout
        lblQuestion.setText(question.getQuestionDescription());
        txt1.setText(question.getQuestionChoices().get(0));
        txt2.setText(question.getQuestionChoices().get(1));
        txt3.setText(question.getQuestionChoices().get(2));
        return view;
    }
}
