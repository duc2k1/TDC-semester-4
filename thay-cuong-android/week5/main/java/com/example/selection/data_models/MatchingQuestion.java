package com.example.selection.data_models;

import java.util.ArrayList;

public class MatchingQuestion extends AbstractQuestion {
    protected ArrayList<String> questionChoiceA;
    protected ArrayList<String> questionChoiceB;
    public MatchingQuestion() {
        this.questionChoiceA = new ArrayList<String>();
        this.questionChoiceB = new ArrayList<String>();
        this.questionAnswers = new ArrayList<Integer>();
        this.questionCorrect = new ArrayList<Integer>();
    }

    public ArrayList<String> getQuestionChoiceA() {
        return questionChoiceA;
    }

    public ArrayList<String> getQuestionChoiceB() {
        return questionChoiceB;
    }

    public void setQuestionChoiceA(String ... questionChoiceA) {
        for(String item:questionChoiceA)
            this.questionChoiceA.add(item);
    }

    public void setQuestionChoiceB(String ... questionChoiceB) {
        for(String item:questionChoiceB)
            this.questionChoiceB.add(item);
    }

    @Override
    public int getPoint() {
        return 0;
    }
}
