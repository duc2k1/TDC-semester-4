package com.example.selection.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.selection.R;
import com.example.selection.data_models.AbstractQuestion;
import com.example.selection.data_models.MultiQuestionMultiChoices;
import com.example.selection.data_models.MultiQuestionOneChoice;
import com.example.selection.data_models.Questions;
import com.example.selection.view_models.MyRadioGroup;

public class MultiQuestionOneChoiceFragment extends AbstractFragment{
    private MultiQuestionOneChoice question;
    private RadioButton radA,radB,radC,radD;
    @Override
    public void setQuestionAnswer(int questionID) {
        Questions.questions.get(questionID).getQuestionAnswers().clear();
        if(radA.isChecked()){
            Questions.questions.get(questionID).setQuestionAnswers(0);
        }
        if(radB.isChecked()){
            Questions.questions.get(questionID).setQuestionAnswers(1);
        }
        if(radC.isChecked()){
            Questions.questions.get(questionID).setQuestionAnswers(2);
        }
        if(radD.isChecked()){
            Questions.questions.get(questionID).setQuestionAnswers(3);
        }
        Log.e("test choice",Questions.questions.get(questionID).getQuestionAnswers().toString());
    }

    @Override
    public void setQuestion(AbstractQuestion question) {
        this.question=(MultiQuestionOneChoice) question;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=null;
        view=inflater.inflate(R.layout.multiquestion_onechoice_fragment_layout,container,false);
         radA=view.findViewById(R.id.rdA);
         radB=view.findViewById(R.id.rdB);
         radC=view.findViewById(R.id.rdC);
         radD=view.findViewById(R.id.rdD);
        MyRadioGroup myRadioGroup=new MyRadioGroup(radA,radB,radC,radD);
        TextView lblQuestion=(TextView) view.findViewById(R.id.lblQuestion);
        //MultiQuestionOneChoice question=(MultiQuestionOneChoice) Questions.questions.get(1);
        //Set data from Questions to layout
        lblQuestion.setText(question.getQuestionDescription());
        radA.setText(question.getQuestionChoices().get(0));
        radB.setText(question.getQuestionChoices().get(1));
        radC.setText(question.getQuestionChoices().get(2));
        radD.setText(question.getQuestionChoices().get(3));
        //
        return  view;
    }
}
