package com.example.selection.data_models;

import java.util.ArrayList;

public class MatchingQuestion extends AbstractQuestion {
    protected ArrayList<String> questionChoiceA;
    protected ArrayList<String> questionChoiceB;
    protected ArrayList<Integer> questionAnswers;
    protected ArrayList<Integer> questionCorrect;
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

    public ArrayList<Integer> getQuestionAnswers() {
        return questionAnswers;
    }

    public ArrayList<Integer> getQuestionCorrect() {
        return questionCorrect;
    }

    public void setQuestionChoiceA(String ... questionChoiceA) {
        for(String item:questionChoiceA)
            this.questionChoiceA.add(item);
    }

    public void setQuestionChoiceB(String ... questionChoiceB) {
        for(String item:questionChoiceB)
            this.questionChoiceB.add(item);
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
