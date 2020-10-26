package com.example.thirdapplication;

import android.content.res.Resources;
import android.util.Log;

import java.util.Random;

public class DummyContent {

    public static int defaultItemCount = 25;
    private static DummyContent instance;
    public final DummyItem[] items;

    private DummyContent(int count) {
        items = generate(count);
    }

    public DummyItem getItem(int position) {
        return items.length > position ? items[position] : null;
    }

    public DummyItem getItemById(String id) {
        for (int i = 0; i < items.length; i ++) {
            if (items[i].id.equals(id)) {
                return items[i];
            }
        }
        return null;
    }

    public int getItemCount() {
        return items.length;
    }

    public static DummyContent getInstance() {
        if (instance == null) {
            instance = new DummyContent(defaultItemCount);
        }
        return  instance;
    }

    static DummyItem[] generate(int count) {
        DummyItem[] items = new DummyItem[count];
        // Добавление элементов в список.
        for (int k = 1; k <= count; k++) {
            StringBuilder builder = new StringBuilder();
            builder.append("Информация об элементе: ").append(k);
            for (int j = 0; j < k; j++) {
                builder.append("\n Детальная информация. ");
            }
            items[k - 1] = new DummyItem(String.valueOf(k), "Элемент " + k, builder.toString());
        }
        return items;
    }
}
