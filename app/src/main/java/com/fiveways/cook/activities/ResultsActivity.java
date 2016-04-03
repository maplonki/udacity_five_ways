package com.fiveways.cook.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.fiveways.cook.R;
import com.fiveways.cook.adapters.RecipePagerAdapter;
import com.fiveways.cook.models.RecipeModel;

import java.util.List;

/**
 * Created by hugo on 4/2/16.
 */
public class ResultsActivity extends BaseActivity implements RecipePagerAdapter.OnRecipeDetailListener {

    private ViewPager mViewPager;
    private RecipePagerAdapter mPagerAdapter;
    private List<RecipeModel> recipeList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        if (!sharedPreferences.getBoolean("instructions_shown", false)) {
            Toast.makeText(ResultsActivity.this, getString(R.string.description_toast_detail), Toast.LENGTH_LONG).show();
            sharedPreferences.edit().putBoolean("instructions_shown", true).apply();
        }

        if (getIntent().getExtras() != null) {
            recipeList = getIntent().getExtras().getParcelableArrayList("recipes");
        }

        mViewPager = (ViewPager) findViewById(R.id.activity_results_pager_recipes);

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
