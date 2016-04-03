package com.fiveways.cook.models;

import java.util.List;

/**
 * Created by hugo on 4/2/16.
 */
public class RecipeResponse {
    private String q;
    private List<RecipeModel> hits;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public List<RecipeModel> getHits() {
        return hits;
    }

    public void setHits(List<RecipeModel> hits) {
        this.hits = hits;
    }
}
