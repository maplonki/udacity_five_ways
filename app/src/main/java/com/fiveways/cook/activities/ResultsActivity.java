package com.fiveways.cook.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.fiveways.cook.R;
import com.fiveways.cook.adapters.RecipePagerAdapter;
import com.fiveways.cook.models.RecipeModel;

import java.util.List;

/**
 * Created by hugo on 4/2/16.
 * <p/>
 * This class displays the list of recipes
 * in a ViewPager. The user can scroll through and click
 * on an image to open the full recipe on a Web Browser
 */
public class ResultsActivity extends AppCompatActivity implements RecipePagerAdapter.OnRecipeDetailListener {

    /**
     * The list of recipes requested on the MainActivity
     */
    private List<RecipeModel> recipeList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        //We show an instruction message only once
        if (!sharedPreferences.getBoolean("instructions_shown", false)) {
            Toast.makeText(ResultsActivity.this, getString(R.string.description_toast_detail), Toast.LENGTH_LONG).show();
            sharedPreferences.edit().putBoolean("instructions_shown", true).apply();
        }

        //Retrieve the recipes from the Bundle
        if (getIntent().getExtras() != null) {
            recipeList = getIntent().getExtras().getParcelableArrayList("recipes");
        }

        ViewPager mViewPager = (ViewPager) findViewById(R.id.activity_results_pager_recipes);

        if (recipeList != null) {
            mViewPager.setAdapter(new RecipePagerAdapter(this, recipeList, this));
        }
    }

    @Override
    public void onRecipeDetail(String detailUri) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(detailUri));
        startActivity(browserIntent);
    }
}
