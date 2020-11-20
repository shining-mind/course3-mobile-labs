package com.example.learngson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;



public class MainActivity extends AppCompatActivity {

    private EditText etJson;

    private TextView tvCat;

    private View vCatColor;

    private Gson gson = new Gson();

    private Cat cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etJson = findViewById(R.id.etJson);
        etJson.addTextChangedListener(new JsonTextWatcher());
        tvCat = findViewById(R.id.tvCat);
        vCatColor = findViewById(R.id.vCatColor);
        cat = getCatFromJson();
        bindCatPropsToLayout();
    }

    protected Cat getCatFromJson() {
        try {
            return gson.fromJson(etJson.getText().toString(), Cat.class);
        } catch (JsonParseException e) {
            return null;
        }
    }

    protected void bindCatPropsToLayout() {
        tvCat.setText(String.format(getString(R.string.cat_as_string), cat.name, cat.age));
        vCatColor.setBackgroundColor(cat.color);
    }

    private class JsonTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Cat parsedCat = getCatFromJson();
            if (parsedCat != null) {
                cat = parsedCat;
                bindCatPropsToLayout();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}