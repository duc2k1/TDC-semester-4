package com.example.selection.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.selection.R;
import com.example.selection.data_models.AbstractQuestion;
import com.example.selection.data_models.MultiQuestionMultiChoices;
import com.example.selection.data_models.Questions;

public class MultiQuestionMultiChoiceFragment extends AbstractFragment{
    private MultiQuestionMultiChoices question;
    private CheckBox chkA,chkB,chkC,chkD;
    @Override
    public void setQuestionAnswer(int questionID) {
        Questions.questions.get(questionID).getQuestionAnswers().clear();
        if(chkA.isChecked()){
            Questions.questions.get(questionID).setQuestionAnswers(0);
        }
        if(chkB.isChecked()){
            Questions.questions.get(questionID).setQuestionAnswers(1);
        }
        if(chkC.isChecked()){
            Questions.questions.get(questionID).setQuestionAnswers(2);
        }
        if(chkD.isChecked()){
            Questions.questions.get(questionID).setQuestionAnswers(3);
        }
        //Log.e("test sl",Questions.questions.get(questionID).getQuestionAnswers().toString());
    }

    @Override
    public void setQuestion(AbstractQuestion question) {
        this.question=(MultiQuestionMultiChoices) question;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=null;
        view=inflater.inflate(R.layout.multiquestion_multichoice_fragment_layout,container,false);
        TextView lblQuestion=(TextView) view.findViewById(R.id.lblQuestion);
        chkA=(CheckBox) view.findViewById(R.id.chkA);
        chkB=(CheckBox) view.findViewById(R.id.chkB);
        chkC=(CheckBox) view.findViewById(R.id.chkC);
        chkD=(CheckBox) view.findViewById(R.id.chkD);
        //MultiQuestionMultiChoices question=(MultiQuestionMultiChoices) Questions.questions.get(0);
        //Set data from Questions to layout
        lblQuestion.setText(question.getQuestionDescription());
        chkA.setText(question.getQuestionChoices().get(0));
        chkB.setText(question.getQuestionChoices().get(1));
        chkC.setText(question.getQuestionChoices().get(2));
        chkD.setText(question.getQuestionChoices().get(3));
        return view;
    }
}
