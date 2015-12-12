package com.example.wykl2;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Maciej Markiewicz on 10.12.15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initParse();
    }

    private void initParse() {
        Parse.initialize(this,
                "YX9LM8tFHJ4gfxwKS5adQhYux7XZ0rventTHpJVX",
                "VuGcjhYzNfqdgN7GzjSp29dM0zAb95yqcIbEd4Jm");

    }
}
