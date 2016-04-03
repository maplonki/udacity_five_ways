package com.fiveways.cook.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fiveways.cook.R;
import com.fiveways.cook.models.RecipeModel;

import java.util.List;

/**
 * Created by hugo on 4/2/16.
 * <p/>
 * This is the adapter that shows each recipe
 * in a "single page"
 */
public class RecipePagerAdapter extends PagerAdapter {

    /**
     * This interface will be called whenever a user clicks on a
     * recipe image.
     * <p/>
     * It will call the activity containing this adapter and will
     * handle the URL
     */
    public interface OnRecipeDetailListener {
        void onRecipeDetail(String detailUri);
    }

    /**
     * The application context
     */
    private Context mContext;

    /**
     * The list of recipes
     */
    private List<RecipeModel> mRecipeList;

    /**
     * The callback for the ResultsActivity
     */
    private OnRecipeDetailListener recipeListener;

    /**
     * Click Listener, executed when clicking on an image
     */
    private View.OnClickListener recipeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof String) {
                if (recipeListener != null) {
                    recipeListener.onRecipeDetail((String) v.getTag());
                }
            }
        }
    };

    /**
     * Constructs a RecipePagerAdapter with a context, the list of recipes and a callback
     *
     * @param mContext       the application context
     * @param mRecipeList    the recipe list
     * @param recipeListener the recipe callback
     */
    public RecipePagerAdapter(Context mContext, List<RecipeModel> mRecipeList, OnRecipeDetailListener recipeListener) {
        this.mContext = mContext;
        this.mRecipeList = mRecipeList;
        this.recipeListener = recipeListener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        RecipeModel recipe = mRecipeList.get(position);

        View parentView = View.inflate(mContext, R.layout.page_recipe, null);

        ImageView recipeImage = (ImageView) parentView.findViewById(R.id.page_recipe_iv_image);
        TextView recipeTitle = (TextView) parentView.findViewById(R.id.page_recipe_tv_title);


        recipeImage.setTag(recipe.getUrl());

        recipeImage.setOnClickListener(recipeClickListener);

        Glide.with(mContext).load(recipe.getImage()).into(recipeImage);
        recipeTitle.setText(recipe.getLabel());

        container.addView(parentView);


        return parentView;
    }

    @Override
    public int getCount() {
        return mRecipeList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
