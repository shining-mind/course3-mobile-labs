package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ImageView ivImage;
    private TextView tvDescription;
    private TextView tvOrigin;
    private TextView tvAlsoKnown;
    private TextView tvIngridients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivImage = findViewById(R.id.image_iv);
        tvIngridients = findViewById(R.id.ingredients_tv);
        tvOrigin = findViewById(R.id.origin_tv);
        tvAlsoKnown = findViewById(R.id.also_known_tv);
        tvDescription = findViewById(R.id.description_tv);
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
            return;
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.get()
                .load(sandwich.getImage())
                .into(ivImage);
        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        tvDescription.setText(sandwich.getDescription());
        tvOrigin.setText(sandwich.getPlaceOfOrigin());
        if (sandwich.getAlsoKnownAs().size() > 0)
            tvAlsoKnown.setText(String.join(", ", sandwich.getAlsoKnownAs()));
        else
            tvAlsoKnown.setText("-/-");
        if (sandwich.getIngredients().size() > 0)
            tvIngridients.setText(String.join(", ", sandwich.getIngredients()));
        else
            tvIngridients.setText("-/-");
    }
}
