package com.fiveways.cook.json;

import com.fiveways.cook.models.RecipeModel;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 4/2/16.
 *
 * This class handles parsing a custom JSON object for the response coming
 * from the api (api/search)
 *
 * Which is
 * {
 *     "hits":[
 *          {
 *              "recipe": {
 *                  "label":""
 *                      ...
 *              }
 *          },
 *          {
 *              "recipe": {
 *                  "label":""
 *                      ...
 *              }
 *          }
 *     ]
 * }
 */
public class RestDeserializer implements JsonDeserializer<List<RecipeModel>> {
    @Override
    public List<RecipeModel> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement hitsElement = json.getAsJsonObject().get("hits");

        if (hitsElement != null) {
            List<RecipeModel> recipeList = new ArrayList<>();
            for (JsonElement element : hitsElement.getAsJsonArray()) {
                JsonElement recipeObject = element.getAsJsonObject().get("recipe");
                recipeList.add(new Gson().fromJson(recipeObject, RecipeModel.class));
            }
            return recipeList;
        } else {
            return null;
        }
    }
}
