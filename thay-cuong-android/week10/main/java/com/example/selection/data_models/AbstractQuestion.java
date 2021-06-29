package com.example.selection.data_models;

import android.util.Log;

import java.util.ArrayList;

public abstract class AbstractQuestion {
    protected String questionDescription;
    protected ArrayList<Integer> questionCorrect;
    protected ArrayList<Integer> questionAnswers;
    public abstract int getPoint();

    public String getQuestionDescription() {
        return questionDescription;
    }

    public ArrayList<Integer> getQuestionCorrect() {
        return questionCorrect;
    }

    public ArrayList<Integer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public void setQuestionCorrect(Integer ... questionCorrect) {
        for(Integer item:questionCorrect){
            this.questionCorrect.add(item);
        }
    }

    public void setQuestionAnswers(Integer ... questionAnswers) {
        for(Integer item:questionAnswers){
            this.questionAnswers.add(item);
        }
    }

    @Override
    public String toString() {
        Log.e("test","ok");
        return "Question "+(Questions.questions.indexOf(this)+1)+" : "+getPoint();
    }
}
