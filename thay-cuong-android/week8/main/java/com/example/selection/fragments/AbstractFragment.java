package com.example.selection.fragments;

import androidx.fragment.app.Fragment;

import com.example.selection.data_models.AbstractQuestion;

public abstract class AbstractFragment extends Fragment {
    public abstract void setQuestionAnswer(int questionID);
    public abstract void setQuestion(AbstractQuestion question);
}
