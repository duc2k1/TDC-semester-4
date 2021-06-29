package com.example.calculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {
    public final static String KEY_A="a";
    public final static String KEY_B="b";
    public final static String KEY_Data="data";
    public final static int REQ_CODE=1;
    EditText edtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);
        //get item
        final EditText edtA=findViewById(R.id.edtA);
        final EditText edtB=findViewById(R.id.edtB);
        edtResult=findViewById(R.id.edtResult);
        Button btnCal=findViewById(R.id.btnCal);
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle data=new Bundle();
                String a=edtA.getText().toString();
                String b=edtB.getText().toString();
                data.putString(KEY_A,a);
                data.putString(KEY_B,b);
                Intent intent=new Intent(CalculatorActivity.this,OperatorActivity.class);
                intent.putExtra(KEY_Data, data);
                startActivityForResult(intent,REQ_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE)//kiem tra dung id
        {
            if (resultCode==RESULT_OK){ //kiem tra du lieu ok
                Bundle bundle=data.getBundleExtra(KEY_Data);
                if(bundle!=null){ //kiem tra du lieu null
                    String a=bundle.getString(KEY_A);
                    String b=bundle.getString(KEY_B);
                    String op=bundle.getString(OperatorActivity.KEY_OP);
                    String result=bundle.getString(OperatorActivity.KEY_RESULT);
                    edtResult.setText(a+op+b+" = "+result);
                }
            }
        }
    }
}