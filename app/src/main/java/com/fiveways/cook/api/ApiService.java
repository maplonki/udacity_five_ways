package com.fiveways.cook.api;

import com.fiveways.cook.models.RecipeModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by hugo on 4/2/16.
 *
 * This class defines all of the available paths to
 * from the Recipes API
 */
public interface ApiService {
    @GET("search")
    Call<List<RecipeModel>> recipeResponse(@QueryMap Map<String, String> params);
}
