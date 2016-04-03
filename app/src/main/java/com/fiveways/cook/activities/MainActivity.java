package com.fiveways.cook.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fiveways.cook.BuildConfig;
import com.fiveways.cook.CookApp;
import com.fiveways.cook.R;
import com.fiveways.cook.api.ApiService;
import com.fiveways.cook.models.RecipeModel;
import com.fiveways.cook.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hugo on 4/2/16.
 */
public class MainActivity extends BaseActivity {

    private EditText mSearchField;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchField = (EditText) findViewById(R.id.activity_main_et_search);
    }

    public void onSearchButton(View view) {


        if (!ValidationUtils.emptyFields(mSearchField)) {
            mProgressDialog = ProgressDialog.show(this, getString(R.string.title_dialog_loading), getString(R.string.description_dialog_loading));

            ApiService service = CookApp.retrofit.create(ApiService.class);
            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("q", mSearchField.getText().toString());
            requestParams.put("to", "5");
            requestParams.put("app_id", BuildConfig.API_APP_ID);
            requestParams.put("app_key", BuildConfig.API_APP_KEY);

            service.recipeResponse(requestParams).enqueue(new Callback<List<RecipeModel>>() {
                @Override
                public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                    mProgressDialog.dismiss();
                    if (response.body() != null && response.body().size() > 0) {
                        List<RecipeModel> recipeList = response.body();

                        Intent resultsIntent = new Intent(MainActivity.this, ResultsActivity.class);
                        resultsIntent.putExtra("recipes", (ArrayList<RecipeModel>) recipeList);
                        startActivity(resultsIntent);
                    }
                }

                @Override
                public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                    mProgressDialog.dismiss();
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.error_main_empty_field), Toast.LENGTH_SHORT).show();
        }

    }
}
