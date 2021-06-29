package com.example.selection.view_models;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

public class MyRadioGroup {
    private ArrayList<RadioButton> radioButtons;
    private View.OnClickListener onClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for(RadioButton rad:radioButtons)
                if(rad.getId()!=((RadioButton)v).getId()){
                    rad.setChecked(false);
                }
        }
    };
    public MyRadioGroup(RadioButton ... radioButtons){
        this.radioButtons=new ArrayList<RadioButton>();
        for(RadioButton rad:radioButtons){
            this.radioButtons.add(rad);
            rad.setOnClickListener(onClick);
        }
    }
}
