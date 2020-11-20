package com.udacity.sandwichclub.utils;

import com.google.gson.Gson;
import com.udacity.sandwichclub.model.Sandwich;

public class JsonUtils {

    protected static Gson gson = new Gson();

    public static Sandwich parseSandwichJson(String json) {
        return gson.fromJson(json, Sandwich.class);
    }
}
