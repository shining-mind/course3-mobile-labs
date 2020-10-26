package com.example.thirdapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DummyItemDetailsActivity extends AppCompatActivity {
    final static String ARG_ITEM_ID = "com.example.EXTRA_ITEM_ID";

    private TextView tvItemDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy_item_details);
        tvItemDetails = findViewById(R.id.tvItemDetails);
        tvItemDetails.setMovementMethod(new ScrollingMovementMethod());
        Intent intent = getIntent();
        String itemId = intent.getStringExtra(ARG_ITEM_ID);
        DummyItem item = DummyContent.getInstance().getItemById(itemId);
        if (item != null) {
            tvItemDetails.setText(item.details);
        } else {
            tvItemDetails.setText(R.string.unknown_item_details);
        }
    }
}