package com.example.selection.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.selection.R;
import com.example.selection.data_models.AbstractQuestion;
import com.example.selection.data_models.MatchingQuestion;
import com.example.selection.data_models.MultiQuestionOneChoice;
import com.example.selection.data_models.Questions;
import com.example.selection.view_models.MyRadioGroup;

public class MatchingQuestionFragment extends AbstractFragment{
    private MatchingQuestion question;
    private Spinner spinner1,spinner2,spinner3;
    private Integer itemIdSpinner1 = 0;
    private Integer itemIdSpinner2 = null;
    private Integer itemIdSpinner3 = null;
    @Override
    public void setQuestionAnswer(int questionID) {
        Questions.questions.get(questionID).getQuestionAnswers().clear();
        Questions.questions.get(questionID).setQuestionAnswers(itemIdSpinner1);
        Questions.questions.get(questionID).setQuestionAnswers(itemIdSpinner2);
        Questions.questions.get(questionID).setQuestionAnswers(itemIdSpinner3);
        Log.e("test sp",Questions.questions.get(questionID).getQuestionAnswers().toString());
    }

    @Override
    public void setQuestion(AbstractQuestion question) {
        this.question=(MatchingQuestion) question;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=null;
        view=inflater.inflate(R.layout.matchingquestion_fragment_layout,container,false);
        TextView lblQuestion = (TextView) view.findViewById(R.id.lblQuestion);
        MatchingQuestion question = (MatchingQuestion) Questions.questions.get(2);
        TextView txt1 =  view.findViewById(R.id.txt1);
        TextView txt2 =  view.findViewById(R.id.txt2);
        TextView txt3 =  view.findViewById(R.id.txt3);
         spinner1 =  view.findViewById(R.id.spinner1);
         spinner2 =  view.findViewById(R.id.spinner2);
         spinner3 =  view.findViewById(R.id.spinner3);
        //Set data from Questions to layout
        lblQuestion.setText(question.getQuestionDescription());
        txt1.setText(question.getQuestionChoiceA().get(0));
        txt2.setText(question.getQuestionChoiceA().get(1));
        txt3.setText(question.getQuestionChoiceA().get(2));
        ArrayAdapter<String> adapter = new ArrayAdapter(inflater.getContext(), android.R.layout.simple_spinner_item, question.getQuestionChoiceB());
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
        return  view;
    }
}
