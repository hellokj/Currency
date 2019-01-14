package com.home.currency;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edNtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        edNtd = findViewById(R.id.ntd);
    }

    public void exchange(View view){
        String input = edNtd.getText().toString();
        if(input.equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("Problem")
                    .setMessage("Please enter your NTD amount")
                    .setPositiveButton("OK", null)
                    .show();
        } else {
            float ntd = Float.parseFloat(input);
            float usd = getUsd(ntd);
            new AlertDialog.Builder(this)
                    .setTitle("Result")
                    .setMessage("USD is " + usd)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            edNtd.setText("");
                        }
                    })
                    .show();
        }
    }

    private float getUsd(float ntd) {
        float rate_usd2ntd = 30.9f;
        return ntd/rate_usd2ntd;
    }
}
