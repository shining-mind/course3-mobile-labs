package com.example.secondapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Phaser;

public class MainActivity extends AppCompatActivity {

    private String text;
    private TextView tvText;
    private TextView tvResult;
    private Button btGenerate;
    private static final String separator = " - это ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = findViewById(R.id.text);
        btGenerate = findViewById(R.id.generate);
        tvResult = findViewById(R.id.result);
        btGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = "Всё, что вам нужно - это " + Phraser.generate();
                tvText.setText(text);
                int index = text.indexOf(separator);
                String result = text.substring(index + separator.length(), index + separator.length() + 1).toUpperCase()
                        + text.substring(index + separator.length() + 1)
                        + separator
                        + text.substring(0, 1).toLowerCase()
                        + text.substring(1, index)
                        + ".";
                tvResult.setText(result);
            }
        });
    }
}