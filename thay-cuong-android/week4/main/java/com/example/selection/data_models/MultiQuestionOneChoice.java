package com.example.selection.data_models;

import java.util.ArrayList;

public class MultiQuestionOneChoice extends AbstractQuestion{
    protected ArrayList<String> questionChoices;

    public MultiQuestionOneChoice() {
        this.questionChoices = new ArrayList<String>();
        this.questionAnswers = new ArrayList<Integer>();
        this.questionCorrect = new ArrayList<Integer>();
    }

    public ArrayList<String> getQuestionChoices() {
        return questionChoices;
    }

    public void setQuestionChoices(String ... questionChoices) {
        for(String item:questionChoices)
            this.questionChoices.add(item);
    }

    @Override
    public int getPoint() {
        return 0;
    }
}
