package com.fiveways.cook;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;
import com.fiveways.cook.json.RestDeserializer;
import com.fiveways.cook.models.RecipeModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by hugo on 4/2/16.
 * <p/>
 * Base app Class.
 */
public class CookApp extends Application {

    /**
     * The Retrofit singletion instance
     */
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        ViewTarget.setTagId(R.id.glide_tag);

        //We register a new JSON parser to parse the custom hierarchy
        //of the JSON response
        Gson myGson = new GsonBuilder().registerTypeAdapter(new TypeToken<List<RecipeModel>>() {
        }.getType(), new RestDeserializer()).create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.edamam.com/")
                .addConverterFactory(GsonConverterFactory.create(myGson))
                .build();
    }
}
