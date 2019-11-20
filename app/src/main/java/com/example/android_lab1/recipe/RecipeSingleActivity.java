package com.example.android_lab1.recipe;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_lab1.R;


public class RecipeSingleActivity extends AppCompatActivity {
    public static final String FRAGMENT_TAG = "articleFragment";
    boolean saved;

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_fram);

        Bundle data = getIntent().getExtras();
        saved = data.getBoolean(Recipe.SAVED);

        RecipeSingleFragment fragment = new RecipeSingleFragment();
        fragment.setArguments(data);
        fragment.setTablet(false);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_article, fragment)
                .commit();
    }

}

