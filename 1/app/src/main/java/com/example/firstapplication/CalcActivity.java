package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PatternMatcher;
import android.service.autofill.RegexValidator;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcActivity extends AppCompatActivity {

    private double amount = 0.0;

    private double percent = 0.15;

    private EditText etAmount;

    private SeekBar sbPercent;

    private TextView tvPercent;

    private TextView tvTip;

    private TextView tvTotal;

    private DecimalFormat percentFormat = new DecimalFormat("## %");

    private DecimalFormat amountFormat = new DecimalFormat("$ ##.##");

    private Pattern numericRegex = Pattern.compile("^\\d+(|\\.\\d+)$");

    private Calculator tipCalc = new Calculator();

    private final TextWatcher amountTextWatcher = new TextWatcher() {
        // Вызывается при изменении пользователем величины счета
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Matcher matcher = numericRegex.matcher(s);
            if (matcher.matches()) {
                amount = Double.parseDouble(s.toString());
            } else {
                amount = 0.0;
            }
            updateTipAndTotal();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after
        ) { }
    };

    private final SeekBar.OnSeekBarChangeListener percentSbListener = new SeekBar.OnSeekBarChangeListener() {
        // Обновление процента чаевых и итоговой суммы
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress / 100.0; // Назначение процента чаевых
            // Вычисление чаевых и общей суммы. Вывод их на экран.
            tvPercent.setText(percentFormat.format(percent));
            updateTipAndTotal();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) { }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        etAmount = findViewById(R.id.etAmount);
        sbPercent = findViewById(R.id.sbPercent);
        tvPercent = findViewById(R.id.tvPercent);
        tvTip = findViewById(R.id.tvTip);
        tvTotal = findViewById(R.id.tvTotal);
        tvTotal.setText("0.0");
        tvTotal.setText("0.0");
        etAmount.addTextChangedListener(amountTextWatcher);
        sbPercent.setOnSeekBarChangeListener(percentSbListener);
    }

    protected void updateTipAndTotal() {
        tvTip.setText(amountFormat.format(tipCalc.calculateTip(amount, percent)));
        tvTotal.setText(amountFormat.format(tipCalc.calculateTotal(amount, percent)));
    }
}