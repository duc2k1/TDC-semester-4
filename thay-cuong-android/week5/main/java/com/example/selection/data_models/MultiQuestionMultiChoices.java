package com.example.selection.data_models;

import java.util.ArrayList;

public class MultiQuestionMultiChoices extends AbstractQuestion{
    protected ArrayList<String> questionChoices;
    protected ArrayList<Integer> questionAnswers;
    protected ArrayList<Integer> questionCorrect;
    public MultiQuestionMultiChoices() {
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
    public ArrayList<Integer> getQuestionAnswers() {
        return questionAnswers;
    }

    @Override
    public ArrayList<Integer> getQuestionCorrect() {
        return questionCorrect;
    }

    public void setQuestionAnswers(Integer ... questionAnswers) {
        for(Integer item:questionAnswers)
            this.questionAnswers.add(item);
    }

    public void setQuestionCorrect(Integer ... questionCorrect) {
        for(Integer item:questionCorrect)
            this.questionCorrect.add(item);
    }

    @Override
    public int getPoint() {
        return 0;
    }
}
