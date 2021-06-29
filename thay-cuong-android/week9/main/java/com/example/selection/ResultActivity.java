package com.example.selection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.selection.data_models.AbstractQuestion;
import com.example.selection.data_models.Questions;
import com.example.selection.data_models.TrueFalseQuestion;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    //private ArrayList<String> listResults=new ArrayList<String>();
    private ArrayAdapter<AbstractQuestion> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        //
        Button btnNext=findViewById(R.id.btnNext);
        Button btnPre=findViewById(R.id.btnPre);
        Button btnExit=findViewById(R.id.btnExit);
        ListView listResults=findViewById(R.id.listResults);
        //set data listview
        //this.listResults.add("Cau 1: 0");
        //this.listResults.add("Cau 2: 0");
        //this.listResults.add("Cau 3: 0");
        this.listAdapter=new ArrayAdapter<AbstractQuestion>(this, android.R.layout.simple_list_item_1,Questions.questions);
        listResults.setAdapter(listAdapter);
        //Set data from Questions to layout
        //
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ResultActivity.this,SelectionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ResultActivity.this,SelectionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listAdapter.notifyDataSetChanged();
    }
}