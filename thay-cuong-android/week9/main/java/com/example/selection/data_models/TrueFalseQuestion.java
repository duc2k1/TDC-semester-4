package com.example.selection.data_models;

import java.util.ArrayList;

public class TrueFalseQuestion extends AbstractQuestion {
    protected ArrayList<String> questionChoices;
    protected ArrayList<Integer> questionAnswers;
    protected ArrayList<Integer> questionCorrect;

    public TrueFalseQuestion() {
        this.questionChoices = new ArrayList<String>();
        this.questionAnswers = new ArrayList<Integer>();
        this.questionCorrect = new ArrayList<Integer>();
    }

    public ArrayList<String> getQuestionChoices() {
        return questionChoices;
    }

    public ArrayList<Integer> getQuestionAnswers() {
        return questionAnswers;
    }

    public ArrayList<Integer> getQuestionCorrect() {
        return questionCorrect;
    }
    public void setQuestionChoices(String ... questionChoices) {
        for(String item:questionChoices)
            this.questionChoices.add(item);
    }

    public void setQuestionAnswers(Integer ... questionAnswers) {
        for(Integer item:questionAnswers)
            this.questionAnswers.add(item);
    }

    public void setQuestionCorrect(Integer ... questionCorrect) {
        for(Integer item:questionCorrect)
            this.questionCorrect.add(item);
    }

    public int getPoint() {
        int point = 0;

        if(this.questionCorrect.size()==this.questionAnswers.size()){
            int i=0;
            for(i=0;i<this.questionCorrect.size();++i){
                if(questionAnswers.get(i)!=questionCorrect.get(i)){
                    break;
                }

            }

            if(i==questionCorrect.size())
                point=1;
        }
        return point;
    }
}
