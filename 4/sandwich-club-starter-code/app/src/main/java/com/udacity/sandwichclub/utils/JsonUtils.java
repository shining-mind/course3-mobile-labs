package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import com.google.gson.Gson;

public class JsonUtils {

    protected static Gson gson = new Gson();

    public static Sandwich parseSandwichJson(String json) {
        return gson.fromJson(json, Sandwich.class);
    }
}
