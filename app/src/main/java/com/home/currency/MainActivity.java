package com.home.currency;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private EditText edNtd;
    private TextView tvUsd;
    private TextView tvJpy;
    private float rate_usd2ntd;
    private float rate_jpy2ntd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setRates();
    }

    private void setRates(){
        rate_usd2ntd = 30.9f;
        rate_jpy2ntd = 0.26f;
    }

    private void findViews() {
        tvUsd = findViewById(R.id.usd);
        tvJpy = findViewById(R.id.jpy);
        edNtd = findViewById(R.id.ntd);
        Button rate = findViewById(R.id.rate);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.rate)
                        .setMessage(getString(R.string.usd2ntd) + rate_usd2ntd + "\n" +
                                    getString(R.string.jpy2ntd) + rate_jpy2ntd)
                        .setPositiveButton(R.string.ok,null)
                        .show();
            }
        });
    }

    public void exchange(View view){
        String input = edNtd.getText().toString();
        if(input.equals("")){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.problem)
                    .setMessage(R.string.input_ntd)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        } else {
            float ntd = Float.parseFloat(input);
            float usd = getUsd(ntd);
            float jpy = getJpy(ntd);
//            修改為輸出至editText內容，取代彈出視窗
            tvUsd.setText(String.valueOf(usd));
//            setContentView(tvUsd);
            tvJpy.setText(String.valueOf(jpy));
//            setContentView(tvJpy);
            /*new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Result")
                    .setMessage("Calculate Finished.")
                    .setPositiveButton("OK", null)
                    .show();*/
            }
        }

    public void reset(View view){
        edNtd.setText("");
        tvUsd.setText("");
        tvJpy.setText("");
    }

    private float getJpy(float ntd){
        return ntd/rate_jpy2ntd;
    }

    private float getUsd(float ntd) {
        return ntd/rate_usd2ntd;
    }
}
