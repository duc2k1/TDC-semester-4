package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class OperatorActivity extends AppCompatActivity {
    public final static String KEY_RESULT="result";
    public final static String KEY_OP="operator";
    Bundle data;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operator_layout);
        final EditText edtA=findViewById(R.id.edtA);
        final EditText edtB=findViewById(R.id.edtB);
        Button btnCong=findViewById(R.id.btnCong);
        Button btnTru=findViewById(R.id.btnTru);
        Button btnNhan=findViewById(R.id.btnNhan);
        Button btnChia=findViewById(R.id.btnChia);
        final EditText edtResult=findViewById(R.id.edtResult);
        //do du lieu qua activity
        final Intent intent=getIntent();
        final Bundle data=intent.getBundleExtra(CalculatorActivity.KEY_Data);

        if(data!=null){
            String a=data.getString(CalculatorActivity.KEY_A);
            String b=data.getString(CalculatorActivity.KEY_B);

            //do du lieu vao txtA,txtB
            edtA.setText(a);
            edtB.setText(b);
            Log.d("test",a);//chay trong logcat
        }
        //tao event button
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=data.getString(CalculatorActivity.KEY_A);
                String b=data.getString(CalculatorActivity.KEY_B);
                edtA.setText(a);
                edtB.setText(b);
                String result=(Double.parseDouble(edtA.getText().toString())+Double.parseDouble((edtB.getText().toString())))+"";
                data.putString(KEY_RESULT,result);
                data.putString(KEY_OP,"+");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=data.getString(CalculatorActivity.KEY_A);
                String b=data.getString(CalculatorActivity.KEY_B);
                edtA.setText(a);
                edtB.setText(b);
                String result=(Double.parseDouble(edtA.getText().toString())-Double.parseDouble((edtB.getText().toString())))+"";
                data.putString(KEY_RESULT,result);
                data.putString(KEY_OP,"-");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=data.getString(CalculatorActivity.KEY_A);
                String b=data.getString(CalculatorActivity.KEY_B);
                edtA.setText(a);
                edtB.setText(b);
                String result=(Double.parseDouble(edtA.getText().toString())*Double.parseDouble((edtB.getText().toString())))+"";
                data.putString(KEY_RESULT,result);
                data.putString(KEY_OP,"*");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=data.getString(CalculatorActivity.KEY_A);
                String b=data.getString(CalculatorActivity.KEY_B);
                edtA.setText(a);
                edtB.setText(b);
                String result=(Double.parseDouble(edtA.getText().toString())/Double.parseDouble((edtB.getText().toString())))+"";
                data.putString(KEY_RESULT,result);
                data.putString(KEY_OP,"/");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}