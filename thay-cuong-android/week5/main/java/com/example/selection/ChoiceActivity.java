package com.example.selection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.selection.view_models.MyRadioGroup;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_layout);
        //
        Button btnNext=findViewById(R.id.btnNext);
        Button btnPre=findViewById(R.id.btnPre);
        RadioButton radA=findViewById(R.id.rdA);
        RadioButton radB=findViewById(R.id.rdB);
        RadioButton radC=findViewById(R.id.rdC);
        RadioButton radD=findViewById(R.id.rdD);
        MyRadioGroup myRadioGroup=new MyRadioGroup(radA,radB,radC,radD);
        //
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChoiceActivity.this,SpinnerActivity.class);
                startActivity(intent);
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChoiceActivity.this, SelectionActivity.class);
                startActivity(intent);
            }
        });
    }
}